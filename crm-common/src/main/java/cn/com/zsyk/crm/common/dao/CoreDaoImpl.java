package cn.com.zsyk.crm.common.dao;

import java.util.List;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.PageContainer;

/**
 * @author
 */
@Repository
public class CoreDaoImpl implements AbstractDao {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Override
	public int insert(String statementId) {
		return sqlSessionTemplate.insert(statementId);
	}

	@Override
	public int insert(String statementId, Object parameter) {
		return sqlSessionTemplate.insert(statementId, parameter);
	}

	@Override
	public int delete(String statementId) {
		return sqlSessionTemplate.delete(statementId);
	}

	@Override
	public int delete(String statementId, Object parameter) {
		return sqlSessionTemplate.delete(statementId, parameter);
	}

	@Override
	public int update(String statementId) {
		return sqlSessionTemplate.update(statementId);
	}

	@Override
	public int update(String statementId, Object parameter) {
		return sqlSessionTemplate.update(statementId, parameter);
	}

	@Override
	public <T> T selectOne(String statementId) {
		return sqlSessionTemplate.selectOne(statementId);
	}

	@Override
	public <T> T selectOne(String statementId, Object parameter) {
		return sqlSessionTemplate.selectOne(statementId, parameter);
	}

	@Override
	public <T> List<T> selectList(String statementId) {
		return sqlSessionTemplate.selectList(statementId);
	}

	@Override
	public <T> List<T> selectList(String statementId, Object parameter) {
		return sqlSessionTemplate.selectList(statementId, parameter);
	}
	
	@Override
	public int batchInsert(String statementId, List<?> list) {
		int i = 0;  
		SqlSession batchSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			for(int cnt = list.size(); i < cnt; i++) {  
		        batchSession.insert(statementId, list.get(i));  
		        if((i + 1) % BATCH_DEAL_NUM == 0) {  
		            batchSession.flushStatements();  
		        }  
		    }  
		    batchSession.flushStatements();  
		}catch (Exception e) {
			batchSession.rollback();
			e.printStackTrace();
		}finally {
		    batchSession.close();  
		}
		
	    return i;  
	}

	@Override
	public int batchDelete(String statementId, List<?> list) {
		int i = 0;  
		SqlSession batchSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			for(int cnt = list.size(); i < cnt; i++) {  
		        batchSession.delete(statementId, list.get(i));  
		        if((i + 1) % BATCH_DEAL_NUM == 0) {  
		            batchSession.flushStatements();  
		        }  
		    }  
		    batchSession.flushStatements();  
		}catch (Exception e) {
			batchSession.rollback();
			e.printStackTrace();
		}finally {
		    batchSession.close();  
		}
		
	    return i;  
	}

	@Override
	public int batchUpdate(String statementId, List<?> list) {
		int i = 0;  
		SqlSession batchSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			for(int cnt = list.size(); i < cnt; i++) {  
		        batchSession.update(statementId, list.get(i));  
		        if((i + 1) % BATCH_DEAL_NUM == 0) {  
		            batchSession.flushStatements();  
		        }  
		    }  
		    batchSession.flushStatements();  
		}catch (Exception e) {
			batchSession.rollback();
			e.printStackTrace();
		}finally {
		    batchSession.close();  
		}
		
	    return i;  
	}
	
	
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageBean<?> selectPageById(String statementId, Object params) {
		PageHelper.startPage(PageContainer.getPageNum(), PageContainer.getPageSize());
		List<Object> result = sqlSessionTemplate.selectList(statementId, params);
		
		if (result instanceof Page) {
			return new PageBean((Page)result);
		}

		return null;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageBean<?> selectPageById(String statementId, Object params, boolean handMovement) {
		if(!handMovement) {
			PageHelper.startPage(PageContainer.getPageNum(), PageContainer.getPageSize());
		}else {
			PageHelper.startPage(1, 8);
		}
		List<Object> result = sqlSessionTemplate.selectList(statementId, params);
		
		if (result instanceof Page) {
			return new PageBean((Page)result);
		}

		return null;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageBean<?> selectPageByMapper(Object mapper, String methodName, Object... args) {
		PageHelper.startPage(PageContainer.getPageNum(), PageContainer.getPageSize());

		Object result = null;
		try {
			result = MethodUtils.invokeMethod(mapper, methodName, args);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		if (result instanceof Page) {
			return new PageBean((Page) result);
		}

		return null;

	}

}
