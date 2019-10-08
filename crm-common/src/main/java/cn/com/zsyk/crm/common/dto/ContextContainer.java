package cn.com.zsyk.crm.common.dto;

/**
 * 系统线程上线文
 * @author
 */
public class ContextContainer extends ThreadLocal<CoreContext> {

	private static ContextContainer container = new ContextContainer();

	public static CoreContext getContext() {
		return container.get();
	}

	public static void setContext(CoreContext cc) {
		container.set(cc);
	}

}
