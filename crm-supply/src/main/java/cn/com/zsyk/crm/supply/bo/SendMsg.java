package cn.com.zsyk.crm.supply.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
@Data
public class SendMsg implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Map<String, String>> msgList;

}
