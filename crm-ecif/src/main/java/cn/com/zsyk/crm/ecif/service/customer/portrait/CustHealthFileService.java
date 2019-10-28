package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcHealthFile;
import cn.com.zsyk.crm.ecif.mapper.EcHealthFileMapper;

@Service
@Transactional 
public class CustHealthFileService {
	/** logger */
	@SuppressWarnings("unused")
	private final Log logger = LogUtil.getLogger(CustHealthFileService.class);

	@Autowired
	private EcHealthFileMapper ecHealthFileMapper;

	/**
	 * Desc: 查询客户健康档案列表
	 * @param ecHealthFile
	 * @return
	 * @author
	 */
	public List<EcHealthFile> getCustHealthFileList(EcHealthFile ecHealthFile) {
		 List<EcHealthFile> ecHealthFilelst = ecHealthFileMapper.selectHealthFileList(ecHealthFile);
		return ecHealthFilelst;
	}


}
