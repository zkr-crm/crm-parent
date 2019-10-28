package cn.com.zsyk.crm.common.config.mybatis.interceptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.common.util.ReflectUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;

/**
 * Mybatis公共字段赋值拦截器
 * @author
 */
@Intercepts({ @Signature(type=Executor.class, method="update", args={MappedStatement.class, Object.class}) })
public class PubColumnsInterceptor implements Interceptor {
	private final Log logger = LogUtil.getLogger(PubColumnsInterceptor.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object result = null;
		try {
			MappedStatement ms = (MappedStatement)invocation.getArgs()[0];
	        Object parameter = invocation.getArgs()[1];
	    	BoundSql boundSql = ms.getBoundSql(parameter);
	    	
		    //1、通过JSqlParser解析原始sql
		    Statement stmt = CCJSqlParserUtil.parse(boundSql.getSql());
		    
		    //2、获取包含公共字段的Sql
	    	String newSql = getPubColSql(stmt, ms);

    		//3、处理参数映射和动态参数
	    	if(null != newSql){
	    		processParameter(ms, parameter, boundSql, stmt, newSql);
			}
		}catch (Exception e) {
			logger.error("公共字段处理异常："+e.getMessage(), e);
		} finally {
			result = invocation.proceed();
		}
		
		return result;
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
	}

	@Override // 能读取Mybatis配置的参数
	public void setProperties(Properties properties) {

	}

	/**
	 * 获取包含公共字段的SQL
	 * 
	 * @param stmt
	 * @param ms
	 * @return 增加了公共字段后的新sql
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	private String getPubColSql(Statement stmt, MappedStatement ms) {
		String newSql = null; //包含公共字段sql
		String tabName = null;				   //sql中操作的表名
		List<Column> columns = null;		   //sql中操作的字段列表
		List<String> publicIDs = null;		   //新增或修改时使用的公共字段
		List<Expression> expressions = null;   //sql中操作的字段对应值
		
		List<Column> oldColumns = new ArrayList<>();			//备份原始sql中操作的字段列表
		List<Expression> oldExpressions =  new ArrayList<>();   //备份原始sql中操作的字段对应值
		
		if(stmt instanceof Insert) {
			Insert insert = ((Insert)stmt);
			
			//insert语句中不包含select的才做处理，例如insert into tab1 (select * from tab2)
			if(null == insert.getSelect()) {
				columns = insert.getColumns();		
				publicIDs = PubColumns.getInsertIDs();
				tabName = insert.getTable().getName().toLowerCase().replaceAll("_","");
				expressions =  ((ExpressionList) insert.getItemsList()).getExpressions();
			}
		}else {
			if(stmt instanceof Update) {
				Update update = (Update)stmt;
				
				//update语句中不包含select的、只操作一个表的才做处理
				if(!update.isUseSelect() && update.getTables().size()==1) {
					columns = update.getColumns();		
					publicIDs = PubColumns.getUpdateIDs();
					expressions =  update.getExpressions();
					tabName = update.getTables().get(0).getName().toLowerCase().replaceAll("_","");
				}
			}	
		}
			
		if(null!=tabName && null!=publicIDs && null!=columns && null!=expressions) {
			Map<String, Class<?>> typeAliases = ms.getConfiguration().getTypeAliasRegistry().getTypeAliases();
			 
			//Mybatis的类型别名中包含的表才做处理
			if (typeAliases.containsKey(tabName)) {
				//备份原始sql信息
				oldColumns.addAll(columns);
				oldExpressions.addAll(expressions);
				
				//统一字段顺序，移除原始sql中的包含的公共字段
				for (Iterator it1=columns.iterator(),it2=expressions.iterator(); it1.hasNext()&&it2.hasNext(); ) {
					Column columnID = (Column)it1.next(); 
					it2.next();
					if (publicIDs.contains(columnID.getColumnName().toLowerCase())) {
						it1.remove();
						it2.remove();
					}
				}
				
				//公共字段的插入、赋值
				for (String publicID : publicIDs) {
					Expression valueExp = null;
					if (StatementType.PREPARED.equals(ms.getStatementType())) {
						// 如果是PreparedStatement，插入“?”
						valueExp = new JdbcParameter();
					}else {
						// 如果是普通的Statement，直接插入值
						valueExp = getPubColExpression(publicID);
					}
					
					columns.add(new Column(publicID)); // adding column
					expressions.add(valueExp); // adding value (the easy way)//new JdbcParameter()=?;
				}
				
				//生成新的sql
				newSql = stmt.toString();
				
				//还原原始Statement信息
				if(stmt instanceof Insert) {
					((Insert)stmt).setColumns(oldColumns);
					((ExpressionList) ((Insert)stmt).getItemsList()).setExpressions(oldExpressions);
				}else {
					((Update)stmt).setColumns(oldColumns);
					((Update)stmt).setExpressions(oldExpressions);
				}
			}
		}

		return newSql;
	}
	
	/**
	 * 处理参数映射和新增公共字段的动态参数
	 * @param ms
	 * @param parameter
	 * @param boundSql
	 * @param stmt
	 * @param newSql
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	private void processParameter(MappedStatement ms, Object parameter, BoundSql boundSql, Statement stmt, String newSql) throws NoSuchFieldException, IllegalAccessException {
		//通过boundSql获取原始sql对应的参数映射  
		List<ParameterMapping> paramMappings = boundSql.getParameterMappings();
		
		//使用Statement时newSql中已拼入公共字段值
		if(StatementType.STATEMENT.equals(ms.getStatementType())){
			//覆盖mappedStatement的sqlSource，重载getBoundSql方法，返回新的BoundSql
			ReflectUtil.setValue(ms, "sqlSource", (SqlSource)(paramObj -> new BoundSql(ms.getConfiguration(), newSql, paramMappings, parameter)));
			return;
		}
		
		//使用preparedStatement时进行参数映射和动态参数的赋值
		if(StatementType.PREPARED.equals(ms.getStatementType())){
			//新的参数映射关系
			List<ParameterMapping> newMappings = new ArrayList<>();
			List<String> publicIDs = null;	//公共字段的列表
			int columsSize;				//原始sql中操作的所有列的数量
			
			if(stmt instanceof Insert) {
				Insert insert = ((Insert)stmt);
				publicIDs = PubColumns.getInsertIDs();
				columsSize = insert.getColumns().size();
				
				//去除操作列不是通过?传参的情况
				for(Expression expression : ((ExpressionList) insert.getItemsList()).getExpressions()) {
					if(!(expression instanceof JdbcParameter)) {
						columsSize--;
					}
				}
			}else {
				Update update = (Update)stmt;
				publicIDs = PubColumns.getUpdateIDs();
				columsSize = update.getColumns().size();

				//去除操作列不是通过?传参的情况
				for (Expression expression : update.getExpressions()) {
					if(!(expression instanceof JdbcParameter)) {
						columsSize--;
					}
				}
			}

			//原始映射名为timeStamp驼峰模式，系统中定义的模式为time_stamp下划线分割，需特殊处理
			List<String> tempIDS = new ArrayList<>();
			publicIDs.forEach((id) -> tempIDS.add(id.replaceAll("_", "")));
			
			//拷贝原始的映射关系，不能直接添加，因为返回的是加锁的List
			//需去除原始sql包含的公共字段映射，列名可能是驼峰或下划线形式
			//针对Where条件带参数的情况，先拷贝操作的列
			for(int i = 0; i < columsSize; i++){
				ParameterMapping mapping = paramMappings.get(i);
				String propertyName = mapping.getProperty().toLowerCase();
				if(!tempIDS.contains(propertyName) && !publicIDs.contains(propertyName)){
					newMappings.add(mapping);
				}
			}
			
			//增加公共字段的映射关系，公共字段的映射关系在操作列的最后
			publicIDs.forEach(pubColID -> newMappings.add(new ParameterMapping.Builder(ms.getConfiguration(), pubColID, String.class).build()));
			
			//针对Where条件带参数的情况，拷贝Where条件的参数映射
			for(int i=columsSize; i<paramMappings.size(); i++){
				newMappings.add(paramMappings.get(i));
			}
			
			//构建新的BoundSql
			BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), newSql, newMappings, parameter);
			
			//反射获取原始动态参数
		    Map<String, Object> additionalParams = (Map<String, Object>)ReflectUtil.getValue(boundSql, "additionalParameters");
		    //拷贝原始的动态参数
		    additionalParams.forEach((key, value) -> newBoundSql.setAdditionalParameter(key, value) );
			//添加公共字段的动态参数
		    publicIDs.forEach(pubColID -> newBoundSql.setAdditionalParameter(pubColID, getPubColValue(pubColID)));
			
			//覆写mappedStatement的sqlSource，重载getBoundSql方法，返回新构建的BoundSql
			ReflectUtil.setValue(ms, "sqlSource", (SqlSource)(paramObj -> newBoundSql));
		}
	}
	
	/**
	 * 通过公共字段的id获取公共字段的Expression
	 * @param colID 字段ID
	 * @return
	 */
	private Expression getPubColExpression(String colID){
		Object valueStr = getPubColValue(colID);
		if(null==valueStr){
			return new NullValue();
		}else{
			return new StringValue("'"+valueStr+"'");
		}
	}

	/**
	 * 获取公共字段的值
	 * @param colID 字段ID
	 * @return
	 */
	private String getPubColValue(String colID) {
		String value = null;
		switch (colID) {
			case "create_date":
			case "update_date":
				value =  DateUtil.nowDate();
				break;
			case "create_time":
			case "update_time":
				value =  DateUtil.nowTime();
				break;
			case "create_user":
			case "update_user":
				value = ContextContainer.getContext().getUserId();
				break;
			case "time_stamp":
				value = DateUtil.nowDateTimeStamp();
				break;
			case "rec_stat":
				value = "0";
				break;
			default:
				value = null;
		}
		return value;
	}
	
}