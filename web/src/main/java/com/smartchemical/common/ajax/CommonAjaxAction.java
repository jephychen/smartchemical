/**
 * 
 */
package com.smartchemical.common.ajax;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.List;
import java.util.Random;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.common.service.SmsService;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.api.frame.dao.user.BankAccountDao;
import com.smartchemical.api.frame.dao.user.SmsVerifyCodeDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.DecimalFormatUtil;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.user.BankAccount;
import com.smartchemical.entities.frame.user.SmsVerifyCode;
import com.smartchemical.frame.register.RegisterAction;

/**
 * @author Jephy
 *
 */
@SuppressWarnings("deprecation")
public class CommonAjaxAction implements Action {
	
	private int roleId;
	
	private int accountId;
	
	private String mobileNo;
	
	private int account;
	
	private String accountNo;
	
	private float amount;
	
	private String verifycode;
	
	private InputStream inputStream;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String getCompanyByRole() throws Exception{
		CompanyDao cDao = DaoFactory.getCompanyDao();
		if (roleId == 3){
			List<Company> companies = cDao.getAllCompaniesNotMerchant();
			setInputStream(new StringBufferInputStream(parseCompany(companies)));
		}
		else if (roleId == 4){
			List<Company> companies = cDao.getAllCompaniesMerchant();
			setInputStream(new StringBufferInputStream(parseCompany(companies)));
		}
		return SUCCESS;
	}
	
	public String getAccountById() throws Exception{
		BankAccountDao baDao = DaoFactory.getBankAccountDao();
		BankAccount account = baDao.getBankAcountById(accountId);
		setInputStream(new StringBufferInputStream(parseAccount(account)));
		return SUCCESS;
	}

	private String parseAccount(BankAccount account) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(account.getAccountNo());
		sb.append("," + account.getCompanyName());
		sb.append("," + account.getAccountBank());
		sb.append("," + account.getBankAddress());
		sb.append("," + account.getMobile());
		String resultWrapper = new String(sb.toString().getBytes("UTF-8"), "iso-8859-1");
		return resultWrapper;
	}

	private String parseCompany(List<Company> companies) throws Exception {
		if (companies == null || companies.isEmpty()){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Company company : companies){
			sb.append(company.getCompanyId() + ":" + company.getCompanyName() + ",");
		}
		String result = sb.substring(0, sb.length() - 1);
		String resultWrapper = new String(result.getBytes("UTF-8"), "iso-8859-1");
		return resultWrapper;
	}
	
	public String sendDepositVerifyCode() throws Exception {
		if (account == 0){
			inputStream = new StringBufferInputStream("failed");
			return SUCCESS;
		}
		BankAccountDao baDao = DaoFactory.getBankAccountDao();
		BankAccount accountObj = baDao.getBankAcountById(account);
		if (accountObj == null){
			inputStream = new StringBufferInputStream("failed");
			return SUCCESS;
		}
		SmsVerifyCodeDao smsVerifyCodeDao = DaoFactory.getSmsVerifyCodeDao();
		SmsVerifyCode code = smsVerifyCodeDao.getSmsVerifyCodeByMobile(mobileNo, 2);
		String newVerifyCode = generateAndSendVerifyCode(mobileNo, accountObj.getAccountNo(), amount);
		if (code != null){
			smsVerifyCodeDao.editSmsVerifyCode(code.getSmsVerifyCodeId(), code.getMobileNo(), newVerifyCode, 2, System.currentTimeMillis());
		}
		else {
			smsVerifyCodeDao.insertSmsVerifyCode(mobileNo, newVerifyCode, 2, System.currentTimeMillis());
		}
		inputStream = new StringBufferInputStream(SUCCESS);
		return SUCCESS;
	}
	
	public String sendDepositTransferVerifyCode() throws Exception {
		if (accountNo == null || accountNo.isEmpty()){
			inputStream = new StringBufferInputStream("failed");
			return SUCCESS;
		}
		SmsVerifyCodeDao smsVerifyCodeDao = DaoFactory.getSmsVerifyCodeDao();
		SmsVerifyCode code = smsVerifyCodeDao.getSmsVerifyCodeByMobile(mobileNo, 2);
		String newVerifyCode = generateAndSendVerifyCode(mobileNo, accountNo, amount);
		if (code != null){
			smsVerifyCodeDao.editSmsVerifyCode(code.getSmsVerifyCodeId(), code.getMobileNo(), newVerifyCode, 2, System.currentTimeMillis());
		}
		else {
			smsVerifyCodeDao.insertSmsVerifyCode(mobileNo, newVerifyCode, 2, System.currentTimeMillis());
		}
		inputStream = new StringBufferInputStream(SUCCESS);
		return SUCCESS;
	}
	
	public String checkSmsVerifyCode() throws Exception {
		long now = System.currentTimeMillis();
		SmsVerifyCodeDao smsVerifyCodeDao = DaoFactory.getSmsVerifyCodeDao();
		SmsVerifyCode code = smsVerifyCodeDao.getSmsVerifyCodeByMobile(mobileNo, 2);
		if (code == null || !code.getSmsVerifyCode().equals(verifycode)){
			inputStream = new StringBufferInputStream("illegal");
		}else if (now - code.getGenerateTime() > RegisterAction.SMS_VERIFY_CODE_EXPIRE){
			inputStream = new StringBufferInputStream("expired");
		}else {
			inputStream = new StringBufferInputStream("legal");
		}
		return SUCCESS;
	}
	
	private String generateAndSendVerifyCode(String mobile, String account, float amount) throws Exception {
		Random random = new Random();
		int s = random.nextInt(999999);
		String verifyCode = String.valueOf(s);
		SmsService smsService = DaoFactory.getSmsServiceBean();
		smsService.sendGetDepositVerifyCodes(mobile, verifyCode, account, DecimalFormatUtil.formatFloat2String(amount));
		return verifyCode;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
	
}
