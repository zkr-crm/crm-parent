package cn.com.zsyk.crm.common.dao;

import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;

import cn.com.zsyk.crm.common.dto.PageBean;

/**
 * 数据库操作的公共类
 * 
 * @author
 */
public interface AbstractDao {

	// 批量提交的条数 
	int BATCH_DEAL_NUM = 500;  
	
	/**
	 * 获取默认的DataSource
	 */
	DataSource getDataSource();

	/**
	 * 获取默认的SqlSessionTemplate
	 */
	SqlSessionTemplate getSqlSessionTemplate();

	int insert(String statementId);
	int insert(String statementId, Object parameter);
	int batchInsert(String statementId, List<?> list);
	
	int delete(String statementId);
	int delete(String statementId, Object parameter);
	int batchDelete(String statementId, List<?> list);

	int update(String statementId);
	int update(String statementId, Object parameter);	
	int batchUpdate(String statementId, List<?> list);

	<T> T selectOne(String statementId);
	<T> T selectOne(String statementId, Object parameter);

	/**
	 * java8的语法，接口的默认实现
	 */
	default <T> List<T> selectList(String statementId){
		return null;
	}
	
	default <T> List<T> selectList(String statementId, Object parameter){
		return null;
	}

	/**
	 * 通过statementId进行分页查询
	 * 
	 * @param statementId
	 *            xml文件中的id
	 * @param params
	 *            参数
	 * @return分页对象
	 */
	<T> PageBean<T> selectPageById(String statementId, Object params);
	
	/**
	 * 通过statementId进行分页查询
	 * 
	 * @param statementId
	 *            xml文件中的id
	 * @param params
	 *            参数
	 * @param handMovement
	 *            手动分页
	 * @return分页对象
	 */
	<T> PageBean<T> selectPageById(String statementId, Object params, boolean handMovement);

	/**
	 * 通过Mapper的methodName方法进行分页查询
	 * 
	 * @param mapper
	 *            Mapper接口
	 * @param methodName
	 *            Mapper接口的对应的方法
	 * @param args
	 *            方法的参数
	 * @return
	 */
	<T> PageBean<T> selectPageByMapper(Object mapper, String methodName, Object... args);

}
