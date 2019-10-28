package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.ecif.entity.EcAudioVideo;
import cn.com.zsyk.crm.ecif.mapper.EcAudioVideoMapper;

@Service
@Transactional 
public class CustAudioVideoService {
	@Autowired
	private EcAudioVideoMapper ecAudioVideoMapper;

	/**
	 * Desc: 查询客户视频/音频列表
	 * @param ecAudioVideo
	 * @return
	 * @author
	 */
	public List<EcAudioVideo> getCustAudioVideoList(EcAudioVideo ecAudioVideo) {
		List<EcAudioVideo> ecAudioVideolst = ecAudioVideoMapper.selectAudioVideoList(ecAudioVideo);
		
		return ecAudioVideolst;
	}


	/**
	 * Desc: 查询客户视频/音频
	 * @param ecAudioVideo
	 * @return
	 * @author
	 */
	public EcAudioVideo getCustAudioVideoOne(String audioVideoCd) {
		EcAudioVideo ecAudioVideo= ecAudioVideoMapper.selectByPrimaryKey(audioVideoCd);
		
		return ecAudioVideo;
	}

}
