package cn.com.zsyk.crm.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证工具的结果对象
 * @author
 */
public class ValidationResult {  
    /**
     * 校验结果是否有错  
     */
    private boolean errors;  
      
    /**
     * 校验错误信息
     */
    private List<ValidationBean> errorList = new ArrayList<>();  
  
    public boolean isErrors() {  
        return errors;  
    }  
  
    public void setErrors(boolean errors) {  
        this.errors = errors;  
    }  
  
	public List<ValidationBean> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ValidationBean> errorList) {
		this.errorList = errorList;
	}
	
	public boolean addError(ValidationBean validationBean) {
		return errorList.add(validationBean);
	}

	@Override
	public String toString() {
		return "ValidationResult [errors=" + errors + ", errorList=" + errorList + "]";
	}
}