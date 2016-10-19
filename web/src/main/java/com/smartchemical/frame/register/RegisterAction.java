/**
 * 
 */
package com.smartchemical.frame.register;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.api.frame.dao.order.InvoiceDao;
import com.smartchemical.api.frame.dao.region.CityDao;
import com.smartchemical.api.frame.dao.user.RoleDao;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.api.frame.dao.user.SmsVerifyCodeDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.lock.UserLock;
import com.smartchemical.common.util.DesUtil;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.user.Role;
import com.smartchemical.entities.frame.user.ScUser;
import com.smartchemical.entities.frame.user.SmsVerifyCode;

/**
 * @author Jephy
 *
 */
public class RegisterAction {
	
	public static long SMS_VERIFY_CODE_EXPIRE = 10 * 60 * 1000;
	
	private String userName;
	
	private String companyName;
	
	private String email;
	
	private String mobile;
	
	private String smsVerifyCode;
	
	private String password;
	
	private String passwordDup;
	
	private boolean isConsent;
	
	private int userType;
	
	private boolean smsCheckFirst = false;
	
	private boolean registerStatus = false;
	
	private int userId;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSmsVerifyCode() {
		return smsVerifyCode;
	}

	public void setSmsVerifyCode(String smsVerifyCode) {
		this.smsVerifyCode = smsVerifyCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordDup() {
		return passwordDup;
	}

	public void setPasswordDup(String passwordDup) {
		this.passwordDup = passwordDup;
	}

	public boolean getIsConsent() {
		return isConsent;
	}

	public void setIsConsent(boolean isConsent) {
		this.isConsent = isConsent;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public boolean getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(boolean registerStatus) {
		this.registerStatus = registerStatus;
	}

	public boolean getSmsCheckFirst() {
		return smsCheckFirst;
	}

	public void setSmsCheckFirst(boolean smsCheckFirst) {
		this.smsCheckFirst = smsCheckFirst;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	private boolean isValidate(){
		if (userName == null || userName.length() < 5 || userName.length() > 20){
			return false;
		}
		else {
			String reg = "[a-zA-Z\u4E00-\u9FA5\uF900-\uFA2D]{1}([a-zA-Z0-9\u4E00-\u9FA5\uF900-\uFA2D]|[._]){4,19}";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(userName);
			if (!matcher.matches()){
				return false;
			}
			try {
				ScUserDao scUserDao = DaoFactory.getScUserDao();
				ScUser scUser = scUserDao.getUserByUserName(userName);
				if (scUser != null){
					return false;
				}
			} catch (NamingException e) {
				e.printStackTrace();
				return false;
			}
		}
		if (companyName == null || companyName.length() < 6 || companyName.length() > 30){
			return false;
		}
		if (email == null || email.length() < 5 || email.length() > 50 || !email.contains("@") || email.indexOf("@") < 1 ){
			return false;
		}
		else {
			try {
				ScUserDao scUserDao = DaoFactory.getScUserDao();
				ScUser scUser = scUserDao.getUserByEmail(email);
				if (scUser != null){
					return false;
				}
			} catch (NamingException e) {
				e.printStackTrace();
				return false;
			}
		}
		if (mobile == null || mobile.length() != 11 ){
			return false;
		}
		else {
			String reg = "1[0-9]{10}";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(mobile);
			if (!matcher.matches()){
				return false;
			}
			try {
				ScUserDao scUserDao = DaoFactory.getScUserDao();
				ScUser scUser = scUserDao.getUserByMobile(mobile);
				if (scUser != null){
					return false;
				}
			} catch (NamingException e) {
				e.printStackTrace();
				return false;
			}
		}
		if (smsVerifyCode == null || smsVerifyCode.isEmpty()){
			return false;
		}
		else {
			try {
				SmsVerifyCodeDao smsVerifyCodeDao = DaoFactory.getSmsVerifyCodeDao();
				SmsVerifyCode code = smsVerifyCodeDao.getSmsVerifyCodeByMobile(mobile, 1);
				long now = System.currentTimeMillis();
				if (code == null || !code.getSmsVerifyCode().equals(smsVerifyCode) ){
					smsCheckFirst = true;
					return false;
				}
				if (now - code.getGenerateTime() > SMS_VERIFY_CODE_EXPIRE){
					smsCheckFirst = true;
					return false;
				}
			} catch (NamingException e) {
				e.printStackTrace();
				smsCheckFirst = true;
				return false;
			}
		}
		if (password == null || password.length() < 6 || password.length() > 20 || !password.equals(passwordDup)){
			return false;
		}
		return true;
	}
	
	public String register1() throws Exception {
		return "success";
	}
	
	public String submitRegister1() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ScUserDao scUserDao = DaoFactory.getScUserDao();
		ScUser currentUser = scUserDao.getUserById(user.getUserId());
		if (currentUser == null){
			return "error";
		}
		currentUser.setCompanyLicenseStatus(1);
		synchronized (UserLock.class) {
			if (!scUserDao.editUser(currentUser)){
				return "error";
			}
		}
		ctx.getSession().put("user", currentUser);
		registerStatus = true;
		return "success";
	}

	public String execute() throws Exception {
		if (!isValidate()){
			return "success";
		}
		RoleDao roleDao = DaoFactory.getRoleDao();
		Role role = roleDao.getRoleById(userType);
		CompanyDao companyDao = DaoFactory.getCompanyDao();
		Company company = companyDao.getCompanyById(1);
		CityDao cityDao = DaoFactory.getCityDao();
		City city = cityDao.getCityById(1);
		ScUserDao scUserDao = DaoFactory.getScUserDao();
		String encryptedPassword = DesUtil.encrypt(password, "matrix1986");
		ScUser newUser = scUserDao.insertUser(userName, null, encryptedPassword, null, company, companyName, role, email, city, null, 0, 0, mobile, new Timestamp(System.currentTimeMillis()));
		if (newUser != null){
			ScUser user = scUserDao.getUserByUserName(userName);
			ActionContext ctx = ActionContext.getContext();
			ctx.getSession().put("user", user);
			userId = user.getUserId();
			SmsVerifyCodeDao smsVerifyCodeDao = DaoFactory.getSmsVerifyCodeDao();
			smsVerifyCodeDao.removeSmsVerifyCodeByMobile(mobile);
			//创建用户成功后，添加一条发票信息
			InvoiceDao invoiceDao = DaoFactory.getInvoiceDao();
			invoiceDao.insertInvoice(1, user, user.getExternalCompanyName(), null, null, null, null, null, System.currentTimeMillis());
			return "toregister1";
		}
		else {
			return "error";
		}
	}
}
