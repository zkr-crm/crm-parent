package cn.com.zsyk.crm.ecif.web.controller.product;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcProduct;
import cn.com.zsyk.crm.ecif.service.product.ProductImportService;


@RestController
public class ProductImportCtrl {
	@Autowired
	private ObjectMapper om;
	Log log = LogUtil.getLogger(ProductImportCtrl.class);
	@Autowired
	private ProductImportService service;
	@Autowired
	private CoreDaoImpl dao;

	/**
	 * @api {GET} /crm/ecif/productmng/productByEntity 查询产品列表
	 * @apiName getProductsByEntity
	 * @apiGroup productmng
	 *
	 * @apiSuccess {EcProduct} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ecif/productmng/productByEntity", method = RequestMethod.GET)
	public Result getProductsByEntity(EcProduct record) {
		log.info(">>>>>>>>>>getProductsByEntity start<<<<<<<<<<");
		log = LogUtil.getLogger(ProductImportCtrl.class);
		Result res = new Result();
		System.out.println("获取产品列表。");
		Map param = new HashMap();
		param.put("productCd", record.getProductCd());
		param.put("productNam", record.getProductNam());
		PageBean p = dao.selectPageById("cn.com.zsyk.crm.ecif.mapper.EcProductMapper.selectByEntity",param);
		res.setData(p);
		log.info(">>>>>>>>>>getProductsByEntity end<<<<<<<<<<");
		return res;
	}
	/**
	 * @api {GET} /crm/ecif/productmng/upload 上传产品
	 * @apiName uploadFile
	 * @apiGroup productmng
	 *
	 * @apiSuccess {succeedQry} 保存成功条数
	 */
	
	@RequestMapping(path = "/crm/ecif/productmng/upload", method = RequestMethod.POST)
    public int uploadFile(@RequestParam(value = "file" , required = true) MultipartFile file,@RequestParam(value = "fileName" , required = true) String filename) throws Exception {
        //deal with file
		 //得到文件名
        //String filename=file.getOriginalFilename();
		String path=System.getProperty("user.dir")+"/";
        InputStream inputStream = file.getInputStream();

        if(file.getSize()>0){
            try {
            	service.saveFileFromInputStream(file.getInputStream(),path,filename);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            throw new Exception("上传失败：上传文件不能为空");
        }
		int succeedQry = service.importExcel(filename);
		return succeedQry;
    }
	

}
