package cn.com.zsyk.crm.common.config.mybatis.interceptor;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.LogUtil;

/**
 * MyBatis性能拦截器，用于输出每条 SQL语句及其执行时间
 * @author
 */
@Intercepts({
        //由于PageHelper插件改变了默认的query方式，在PageHelper前加入的Interceptor必须拦截6个参数的query方法
		//@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class ExecutorInterceptor implements Interceptor {
	private final Log logger = LogUtil.getLogger(ExecutorInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
    	Object[] args = invocation.getArgs();
    	Object parameter = null;
    	MappedStatement ms = (MappedStatement) args[0];
        if (args.length > 1) {
            parameter = args[1];
        }

        logger.info("Mybatis执行ID：" + ms.getId());
        logger.info("Mybatis执行SQL：" + getSql(ms, parameter));
        long start = System.currentTimeMillis();
        
        Object result = invocation.proceed();

        logger.info("Mybatis执行结果："+ result);
        logger.info("Mybatis执行耗时："+ (System.currentTimeMillis()-start) +"ms");
        return result;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 获取实际执行带参数的SQL
     * @param ms
     * @param parameter
     * @return
     */
    private String getSql(MappedStatement ms, Object parameter) {
    	BoundSql boundSql = ms.getBoundSql(parameter);
    	Configuration configuration = ms.getConfiguration();
    	
    	String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        
        if (null != parameterMappings) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            for (ParameterMapping parameterMapping: parameterMappings) {
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                	Object value = null;
                    String propertyName = parameterMapping.getProperty();
                    
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameter == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameter.getClass())) {
                        value = parameter;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameter);
                        value = metaObject.getValue(propertyName);
                    }
                    sql = processValue(sql, value);
                }
            }
        }
        return sql;
    }

    private String processValue(String sql, Object propertyValue) {
        String result;
        if (propertyValue != null) {
            if (propertyValue instanceof String) {
                result = "'" + propertyValue + "'";
            } else if (propertyValue instanceof Date) {
                result = "'" + DateUtil.date2FormatString((Date)propertyValue) + "'";
            } else {
                result = propertyValue.toString();
            }
        } else {
            result = "null";
        }
        return sql.replaceFirst("\\?", result);
    }
}
