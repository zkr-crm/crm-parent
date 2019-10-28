package cn.com.zsyk.crm.common.config.mvc.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.common.dto.PageContainer;
import cn.com.zsyk.crm.common.util.JsonUtil;

/**
 * 系统上线文拦截器
 * @author
 */
@Component
public class ContextInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		CoreContext cc = new CoreContext();
		if(request.getParameter("sys") != null) {
			Map<?, ?> map = JsonUtil.parseObject(request.getParameter("sys"), Map.class);
			cc.putAll(map);
			
			//分页信息上下文赋值,  
			PageContainer.setPageNum(getPageNum(map));
			PageContainer.setPageSize(getPageSize(map));
		}
		
		ContextContainer.setContext(cc);
		return super.preHandle(request, response, handler);
	} 
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//清空上下文信息
		PageContainer.removePageNum();
		PageContainer.removePageSize();
		ContextContainer.setContext(null);
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	 /**
     * 获得pageNum参数的值
     * 
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
	private int getPageNum(Map sys) {
        int pageNum = 1;
        try {
            String pageNums = String.valueOf(sys.get("pageNum")) ;
            if (null!=pageNums && StringUtils.isNumeric(pageNums)) {
                pageNum = Integer.valueOf(pageNums);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return pageNum;
    }

    /**
     * 设置默认每页大小
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
	private int getPageSize(Map sys) {
        int pageSize = 10;    // 默认每页10条记录
        try {
            String pageSizes = String.valueOf(sys.get("pageSize")) ;
            if (null!=pageSizes && StringUtils.isNumeric(pageSizes)) {
                pageSize = Integer.parseInt(pageSizes);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        return pageSize;
    }
	

}
