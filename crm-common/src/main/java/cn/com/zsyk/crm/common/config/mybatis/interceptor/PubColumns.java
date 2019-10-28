package cn.com.zsyk.crm.common.config.mybatis.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 公共字段集合（作用于：1生成表的公共字段；2sql拦截中公共字段赋值）
 * @author
 */
public class PubColumns {
	private static final List<String> insertIDs = new ArrayList<>();
	private static final List<String> updateIDs = new ArrayList<>();
	
	static{
		insertIDs.add("create_date"); //创建日期varchar(8)
		insertIDs.add("create_time"); //创建时间varchar(8)
		insertIDs.add("create_user"); //创建用户varchar(30)
		insertIDs.add("time_stamp");  //时间戳timestamp
		insertIDs.add("rec_stat");    //记录状态varchar(1),新增修改都为0，删除为1
		
		updateIDs.add("update_date"); //修改日期varchar(8)
		updateIDs.add("update_time"); //修改时间varchar(8)
		updateIDs.add("update_user"); //修改用户varchar(30)
		updateIDs.add("time_stamp");  //时间戳timestamp
	}
	
	/**
	 * @return 公共新增字段ID的克隆集合，可用来随意操作
	 */
	public static List<String> getInsertIDs() {
		return Collections.unmodifiableList(insertIDs);
	}
	
	/**
	 * @return 公共修改字段ID的克隆集合，可用来随意操作
	 */
	public static List<String> getUpdateIDs() {
		return Collections.unmodifiableList(updateIDs);
	}
	
}
