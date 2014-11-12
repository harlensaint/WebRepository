package com.junsoftware.web.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.junsoftware.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 拦截所有的action，并判断当前用户是否拥有此action的访问权限
 * 
 * @author harlensaint
 * 
 *         2014年11月8日,下午9:28:06
 */
@SuppressWarnings("all")
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		// System.out.println("自定义的拦截器执行了！");

		// 获取用户访问的路径
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();

		if (actionName.endsWith("UI")) {
			actionName = actionName.substring(0, actionName.length() - 2);
		}

		// 拼接访问的url地址
		String url = namespace + actionName;
		// 获取session中的用户
		User loginUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("loginUser");

		
		List<String> allPrivileges = (List<String>) ServletActionContext
				.getServletContext().getAttribute("allPrivileges");

		// 说明访问的action是要进行拦截的
		if (loginUser == null) {
			// 说明用户没有登录，去登录（这里的访问login的action方法在拦截器的参数里面设置，此处不考虑）
			return "toLoginUI";
		} else {
			// 判断访问的action是否需要拦截
			if (allPrivileges.contains(url)) { //说明用户访问的action要进行拦截
				// 说明用户是登录状态，判断此用户是否拥有此权限
				Boolean hasPri = loginUser.checkPrivlegesUrl(url);
				if (hasPri) {
					// 放行
					return invocation.invoke();
				} else {
					// 说明没有权限访问，跳转到提示的错误页面
					return "noPrivilege";
				}
			}
		}
		// 说明访问的action是不要进行拦截的，直接放行
		return invocation.invoke();
	}

}
