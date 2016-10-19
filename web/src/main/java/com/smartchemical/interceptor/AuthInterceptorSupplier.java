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
public class AuthInterceptorSupplier extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		if (user == null || (user.getRole().getRoleId() != 1 && user.getRole().getRoleId() != 3)){
			ctx.getSession().clear();
			return Action.LOGIN;
		}
		return invocation.invoke();
	}

}
