//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.github.pagehelper.dialect.helper;

import com.github.pagehelper.Page;
import com.github.pagehelper.dialect.AbstractHelperDialect;
import java.util.Map;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

public class OracleDialect extends AbstractHelperDialect {
	public OracleDialect() {
	}

	public Object processPageParameter(MappedStatement ms, Map<String, Object> paramMap, Page page, BoundSql boundSql, CacheKey pageKey) {
		paramMap.put("First_PageHelper", page.getEndRow());
		paramMap.put("Second_PageHelper", page.getStartRow());
		pageKey.update(page.getEndRow());
		pageKey.update(page.getStartRow());
		this.handleParameter(boundSql, ms);
		return paramMap;
	}

	public String getPageSql(String sql, Page page, CacheKey pageKey) {
		StringBuilder sqlBuilder = new StringBuilder(sql.length() + 120);
		sqlBuilder.append("select * from ( select tmp_page.*, rownum row_id from ( ");
		sqlBuilder.append(sql);
		sqlBuilder.append(" ) tmp_page where rownum <= ? ) where row_id > ?");
		return sqlBuilder.toString();
	}
}
