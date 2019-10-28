package cn.com.zsyk.crm.common.config.mybatis.typehandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

/**
 * 重写Mybatis的Date类型处理器（GoldenDB时间类型想包含毫秒数，必须用setString或setDecimal设置）
 * @author
 */
@MappedTypes({Date.class})
public class DateTypeHandler extends org.apache.ibatis.type.DateTypeHandler {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
	      throws SQLException {
		ps.setString(i, DateFormatUtils.format(parameter,"yyyyMMddHHmmss.S"));
	}
}