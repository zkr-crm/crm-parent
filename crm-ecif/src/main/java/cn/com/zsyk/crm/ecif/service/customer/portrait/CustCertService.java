package cn.com.zsyk.crm.ecif.service.customer.portrait;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustBaseInfo;
import cn.com.zsyk.crm.ecif.entity.EcCustCert;
import cn.com.zsyk.crm.ecif.mapper.EcCustCertMapper;
import cn.com.zsyk.crm.ecif.service.customer.baseinfo.CustService;
import cn.com.zsyk.crm.generator.EnumType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional 
public class CustCertService {
	@Autowired
	private EcCustCertMapper ecCustCertMapper;
	@Autowired
	private CustService custService;

	/**
	 * Desc: 查询客户证件列表
	 * @param ecCustCert
	 * @return
	 * @author
	 */
	public List<EcCustCert> selectCustCertList(EcCustCert ecCustCert) {
		List<EcCustCert> ecCustCertlst = ecCustCertMapper.selectCustCertList(ecCustCert);
		return ecCustCertlst;
	}

	/**
	 * Desc: 查询客户证件信息(单条)
	 * @param custNo
	 * @param certSqn
	 * @return
	 * @author
	 */
	public EcCustCert selectCustCertOne(String custNo, int certSqn) {
		EcCustCert ecCustCert = ecCustCertMapper.selectByPrimaryKey(custNo, certSqn);
		if (ecCustCert != null) {
			ecCustCert.setCustNam(custService.getPerCustName(custNo));
		}
		return ecCustCert;
	}

	/**
	 * Desc: 查询客户证件信息(单条)
	 * @param custNo
	 * @param certTyp
	 * @param certNo
	 * @return
	 * @author
	 */
	public EcCustCert selectCustCertByCertNo(String custNo, String certTyp, String certNo) {
		EcCustCert ecCustCert = ecCustCertMapper.selectCustCertByCertNoTyp(custNo, certTyp, certNo);
		return ecCustCert;
	}

	/**
	 * Desc: 更新证件信息
	 * @param record
	 * @return
	 * @author
	 */
	public int uptCustCert(EcCustCert record) {
		int flag = ecCustCertMapper.updateByPrimaryKey(record);
		return flag;
	}

	/**
	 * Desc: 新增证件信息
	 * @param certObj
	 * @return
	 * @author
	 */
	public int addCustCert(EcCustCert certObj) {
		certObj.setCertSqn(getNextSqnByCustNo(certObj.getCustNo()));
		int flag = ecCustCertMapper.insert(certObj);
		return flag;
	}

	/**
	 * Desc: 获取下一个Sqn
	 * @param custNo
	 * @return
	 * @author
	 */
	public int getNextSqnByCustNo(String custNo) {
		int nextSqn = ecCustCertMapper.getNextSqnByCustNo(custNo);
		return nextSqn;
	}

	/**
	 * Desc: 保存或更新证件信息
	 * @param perCustBaseInfo
	 * @author
	 */
	public void saveOrUpdateCert(PerCustBaseInfo perCustBaseInfo) {
		// 客户号
		if (StringUtils.isEmpty(perCustBaseInfo.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if(!EnumType.MergeSplitAction.merge_approv_pass.desc.equals(perCustBaseInfo.getMergeMark())) {
			// 证件类型和证件号码
			if (StringUtils.isEmpty(perCustBaseInfo.getCertTyp())) {
				throw new ServiceException("证件类型不能为空");
			} else {
				if (EnumType.CustType.per_latent_cust.getValue().equals(perCustBaseInfo.getCustTyp())) {

				}else{
					if (perCustBaseInfo.getCertNo() == null) {
						throw new ServiceException("证件号码不能为空");
					}
				}
			}
		}

		// 查询证件信息
		EcCustCert obj = new EcCustCert();
		obj.setCustNo(perCustBaseInfo.getCustNo());
		List<EcCustCert> ecCustCertlst = this.selectCustCertList(obj);
		EcCustCert ecCustCert_old = null;
		if (ecCustCertlst != null && ecCustCertlst.size() >0) {
			// 取得原证件信息
			ecCustCert_old = ecCustCertlst.get(0);
			// 查询新证件信息
			EcCustCert ecCustCert_new = this.selectCustCertByCertNo(perCustBaseInfo.getCustNo(), 
					perCustBaseInfo.getCertTyp(), perCustBaseInfo.getCertNo());

			// 新证件类型、号码存在
			if (ecCustCert_new != null) {
				// 对象不相等时，更新信息；否则不更新
				if (!ecCustCert_old.equals(ecCustCert_new)) {

					// 新的证件类型和号码存在，逻辑删除原证件信息
					ecCustCert_old.setRecStat(EnumType.RecStat.delete.getValue());
					this.uptCustCert(ecCustCert_old);

					// 判断新证件信息，如果删除，更新记录状态；否则不操作
					if (ecCustCert_new.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
						ecCustCert_new.setRecStat(EnumType.RecStat.normal.getValue());
						this.uptCustCert(ecCustCert_new);
					}
				}else{
					if(perCustBaseInfo.getCertDate()!=null) {
						ecCustCert_new.setCertEffDate(new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD_LONG).format(perCustBaseInfo.getCertDate()));
					}
					this.uptCustCert(ecCustCert_new);
				}
			} else { // 新证件类型、号码不存在，更新原证件信息
				ecCustCert_old.setCertTyp(perCustBaseInfo.getCertTyp());
				ecCustCert_old.setCertNo(perCustBaseInfo.getCertNo());
                if(perCustBaseInfo.getCertDate()!=null) {
                    ecCustCert_old.setCertEffDate(new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD_LONG).format(perCustBaseInfo.getCertDate()));
                }
				this.uptCustCert(ecCustCert_old);
			}
		} else {
			// 新的证件类型和号码不存在
			EcCustCert certObj = new EcCustCert();
			// 客户号		cust_no
			certObj.setCustNo(perCustBaseInfo.getCustNo());
			// 证件类型		cert_typ
			certObj.setCertTyp(perCustBaseInfo.getCertTyp());
			// 证件号码		cert_no
			certObj.setCertNo(perCustBaseInfo.getCertNo());
			// 激活日期		acti_date
			certObj.setActiDate(DateUtil.getNow(DateUtil.DATE_FORMAT_YMD));
			// 停用日期		stop_use_date
			// 证件有效期		cert_eff_date add lijiangcheng 2019-08-21
			if(perCustBaseInfo.getCertDate()!=null) {
				certObj.setCertEffDate(new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD_LONG).format(perCustBaseInfo.getCertDate()));
			}
			// 描述		desc_content
			// 证件发放地		cert_issued_plece
			// 源系统标识符		orig_sys_flg
			certObj.setOrigSysFlg(EnumType.DataSource.ecif.getValue());
			// 证件状态		cert_sts
			certObj.setCertSts(EnumType.ValidFlg.valid.getValue());
			certObj.setRecStat(EnumType.RecStat.normal.getValue());
			this.addCustCert(certObj);
		}
	}

	/**
	 * Desc:删除证件
	 * @param ecCustCert
	 * @author
	 */
	public void delCustCert(EcCustCert ecCustCert) {
		if (ecCustCert == null) {
			throw new ServiceException("证件参数不能为空");
		}

		if (StringUtils.isEmpty(ecCustCert.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}

		if (ecCustCert.getCertSqn() == null) {
			throw new ServiceException("证件序号不能为空");
		}
		ecCustCertMapper.deleteByPrimaryKey(ecCustCert.getCustNo(), ecCustCert.getCertSqn());
	}
}
