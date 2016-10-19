/**
 * 
 */
package com.smartchemical.frame.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
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
public class LoginAction implements Action, ServletRequestAware {
	
	private String username;
	
	private String password;
	
	private boolean isConsent;
	
	private String loginTip;
	
	private String refererUrl;
	
	private HttpServletRequest request;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsConsent() {
		return isConsent;
	}

	public void setIsConsent(boolean isConsent) {
		this.isConsent = isConsent;
	}
	
	public String getLoginTip() {
		return loginTip;
	}

	public void setLoginTip(String loginTip) {
		this.loginTip = loginTip;
	}

	public String getRefererUrl() {
		return refererUrl;
	}

	public void setRefererUrl(String refererUrl) {
		this.refererUrl = refererUrl;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String logout() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ctx.getSession().clear();
		addCookie("username", "");
		addCookie("password", "");
		if (refererUrl == null){
			refererUrl = request.getHeader("referer");
		}
		if (refererUrl == null || refererUrl.isEmpty() || refererUrl.contains("Login.action") || refererUrl.contains("Register.action")){
			refererUrl = "TitaniumMainPage.action";
		}
		return "back";
	}

	public String execute() throws Exception {
		if (refererUrl == null){
			refererUrl = request.getHeader("referer");
		}
		if (refererUrl == null || refererUrl.isEmpty() || refererUrl.contains("Login.action") 
				|| refererUrl.contains("Register.action") || refererUrl.contains("Logout.action")){
			refererUrl = "MyZhixuan.action";
		}
		if (username == null || password == null){
			return INPUT;
		}
		ActionContext ctx = ActionContext.getContext();
		ScUserDao scUserDao = DaoFactory.getScUserDao();
		ScUser user = scUserDao.getUserByUserName(username);
		if (user == null || user.getPassword() == null){
			ctx.getSession().put("tip", "用户名或密码错误，请重新输入");
			return INPUT;
		}
		String passwordFromDB = user.getPassword();
		String decryptedPassword = DesUtil.decrypt(passwordFromDB, "matrix1986");
		if (!decryptedPassword.equals(password)){
			ctx.getSession().put("tip", "用户名或密码错误，请重新输入");
			return INPUT;
		}
		else {
			user.setDepositStr(DecimalFormatUtil.formatFloat2String(user.getDeposit()));
			ctx.getSession().put("user", user);
			if (isConsent){
				addCookie(SessionConstant.COOKIE_USERNAME, username);
				addCookie(SessionConstant.COOKIE_PASSWORD, passwordFromDB);
			}
			return "back";
		}
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
