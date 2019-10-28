package cn.com.zsyk.crm.common.config.mybatis.typehandler;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.ReflectionUtils;

/**
 * Mybatis的枚举类型处理器
 * 
 * @author
 */
@MappedTypes(Enum.class)
public class EnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

	private final Class<E> type;

	public EnumTypeHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.toString());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return toEnum(rs.getString(columnName));
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return toEnum(rs.getString(columnIndex));
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return toEnum(cs.getString(columnIndex));
	}
	
	@SuppressWarnings("unchecked")
	private E toEnum(String s) {
		if(null != s){
			//获取自定义枚举的toEnum方法
			Method toEnumMethod = ReflectionUtils.findMethod(type, "toEnum", String.class);
			if(null != toEnumMethod){
				return (E)ReflectionUtils.invokeMethod(toEnumMethod, null, s.trim());
			}
			
			return Enum.valueOf(type, s);
		}
		return null;
	}

}