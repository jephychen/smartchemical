/**
 * 
 */
package com.smartchemical.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.common.constant.SessionConstant;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.DecimalFormatUtil;
import com.smartchemical.common.util.DesUtil;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class AutoLoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		if (user == null){
			autoLogin();
		}
		return invocation.invoke();
	}
	
	private void autoLogin() throws Exception{
		String username = getCookie(SessionConstant.COOKIE_USERNAME);
		String password = getCookie(SessionConstant.COOKIE_PASSWORD);
		password = DesUtil.decrypt(password, "matrix1986");
		ScUserDao scUserDao = DaoFactory.getScUserDao();
		ScUser user = scUserDao.getUserByUserName(username);
		if (user == null || user.getPassword() == null){
			return;
		}
		String passwordFromDB = user.getPassword();
		String decryptedPassword = DesUtil.decrypt(passwordFromDB, "matrix1986");
		if (!decryptedPassword.equals(password)){
			return;
		}
		user.setDepositStr(DecimalFormatUtil.formatFloat2String(user.getDeposit()));
		ActionContext ctx = ActionContext.getContext();
		ctx.getSession().put("user", user);
		addCookie(SessionConstant.COOKIE_USERNAME, username);
		addCookie(SessionConstant.COOKIE_PASSWORD, DesUtil.encrypt(password, "matrix1986"));
	}
	
    /**
     * Cookieの取得
     * @return
     * @throws Exception
     */
    private String getCookie(String name){
        HttpServletRequest request = ServletActionContext.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
        	return null;
        }
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals(name))
            {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * Cookieの追加
     * @return
     * @throws Exception
     */
    private void addCookie(String name, String value){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60 * 60 * 24 * 15);
        ServletActionContext.getResponse().addCookie(cookie);
    }
}
