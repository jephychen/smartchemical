/**
 * 
 */
package com.smartchemical.usercenter.adminuser;

import java.io.File;
import java.sql.Timestamp;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.api.frame.dao.order.InvoiceDao;
import com.smartchemical.api.frame.dao.region.CityDao;
import com.smartchemical.api.frame.dao.user.RoleDao;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.api.frame.dao.user.SmsVerifyCodeDao;
import com.smartchemical.common.constant.PathConstant;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.lock.UserLock;
import com.smartchemical.common.util.CopyFileUtil;
import com.smartchemical.common.util.DesUtil;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.user.Role;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class UserAddEditSubmitAction implements Action {
	
	private int queryType;
	
	private String returnAction;
	
	private int userId;
	
	private String userName;
	
	private String realName;
	
	private String userpassword;
	
	private String externalCompany;
	
	private float deposit;
	
	private int cityId;
	
	private String address;
	
	private String email;
	
	private String mobile;
	
	private int roleId;
	
	private int supplierId;
	
	private int merchantId;
	
	private int statusId;

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public String getReturnAction() {
		return returnAction;
	}

	public void setReturnAction(String returnAction) {
		this.returnAction = returnAction;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getExternalCompany() {
		return externalCompany;
	}

	public void setExternalCompany(String externalCompany) {
		this.externalCompany = externalCompany;
	}

	public float getDeposit() {
		return deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	private void setReturnAction(){
		if (queryType == 10){
			returnAction = "allUsers";
		}
		else if (queryType == 11){
			returnAction = "merchantUsers";
		}
		else if (queryType == 12){
			returnAction = "supplierUsers";
		}
		else if (queryType == 13){
			returnAction = "commonUsers";
		}
		else if (queryType == 14){
			returnAction = "adminUsers";
		}
		else if (queryType == 15){
			returnAction = "warehouseAdminUsers";
		}
	}
	
	public String editSave() throws Exception {
		setReturnAction();
		
		CompanyDao companyDao = DaoFactory.getCompanyDao();
		Company userCompany = null;
		if (roleId == 3){
			userCompany = companyDao.getCompanyById(supplierId);
			if (userCompany == null){
				return ERROR;
			}
		}
		else if (roleId == 4){
			userCompany = companyDao.getCompanyById(merchantId);
			if (userCompany == null){
				return ERROR;
			}
		}
		else{
			userCompany = companyDao.getCompanyById(1);
		}
		RoleDao roleDao = DaoFactory.getRoleDao();
		Role userRole = roleDao.getRoleById(roleId);
		if (userRole == null){
			return ERROR;
		}
		CityDao cityDao = DaoFactory.getCityDao();
		City userCity = cityDao.getCityById(cityId);
		ScUserDao scUserDao = DaoFactory.getScUserDao();
		String encryptedPassword = DesUtil.encrypt(userpassword, "matrix1986");
		if (userId == 0){
			ScUser newUser = scUserDao.insertUser(userName, realName, encryptedPassword, null, userCompany, externalCompany, userRole, email, userCity, address, 0, 0, mobile, new Timestamp(System.currentTimeMillis()));
			if (newUser != null){
				SmsVerifyCodeDao smsVerifyCodeDao = DaoFactory.getSmsVerifyCodeDao();
				smsVerifyCodeDao.removeSmsVerifyCodeByMobile(mobile);
				//创建用户成功后，添加一条发票信息
				InvoiceDao invoiceDao = DaoFactory.getInvoiceDao();
				invoiceDao.insertInvoice(1, newUser, newUser.getExternalCompanyName(), null, null, null, null, null, System.currentTimeMillis());
				return SUCCESS;
			}
			else {
				return ERROR;
			}
		}
		else{
			ScUser currentUser = scUserDao.getUserById(userId);
			if (currentUser == null){
				return ERROR;
			}
			currentUser.setRealName(realName);
			currentUser.setPassword(encryptedPassword);
			currentUser.setCompany(userCompany);
			currentUser.setExternalCompanyName(externalCompany);
			currentUser.setRole(userRole);
			currentUser.setEmail(email);
			currentUser.setCity(userCity);
			currentUser.setUserAddress(address);
			currentUser.setDeposit(deposit);
			currentUser.setCompanyLicenseStatus(statusId);
			if (statusId == 0){
				removeCompanyLicense(currentUser);
			}
			currentUser.setMobileNo(mobile);
			synchronized (UserLock.class) {
				scUserDao.editUser(currentUser);
			}
		}
		return SUCCESS;
	}
	
	private void removeCompanyLicense(ScUser user){
		String dirPath = ServletActionContext.getServletContext().getRealPath(PathConstant.COMPANY_LICENSE_SAVE_FOLDER + CopyFileUtil.makeCompanyLicensePath(user));
		String backupPath = ServletActionContext.getServletContext().getRealPath(PathConstant.COMPANY_LICENSE_BACKUP_FOLDER);
		File licenseDir = new File(dirPath);
		if (!licenseDir.isDirectory()){
			return;
		}
		CopyFileUtil.copyDirectory(dirPath, backupPath, true);
		for (File file : licenseDir.listFiles()){
			if (file.isFile()){
				file.delete();
			}
		}
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
