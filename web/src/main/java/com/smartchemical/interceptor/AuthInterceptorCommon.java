/**
 * 
 */
package com.smartchemical.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class AuthInterceptorCommon extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		if (user == null){
			ctx.getSession().put("tip", "只有登录之后才能使用所选功能");
			return Action.LOGIN;
		}
		return invocation.invoke();
	}

}
