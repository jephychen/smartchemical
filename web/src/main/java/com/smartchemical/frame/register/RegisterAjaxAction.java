/**
 * 
 */
package com.smartchemical.frame.register;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Random;

import com.opensymphony.xwork2.ActionSupport;
import com.smartchemical.api.frame.common.service.SmsService;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.api.frame.dao.user.SmsVerifyCodeDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.user.ScUser;
import com.smartchemical.entities.frame.user.SmsVerifyCode;

/**
 * @author Jephy
 *
 */
@SuppressWarnings("deprecation")
public class RegisterAjaxAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InputStream inputStream;
	
	private String keyWord;
	
	/**
	 * 1 - 修改用户， 2 - 添加用户
	 * */
	private int isEdit;
	
	private int userId;
	
	private String mobile;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String checkUserName() throws Exception {
		ScUserDao scUserDao = DaoFactory.getScUserDao();
		ScUser scUser = scUserDao.getUserByUserName(keyWord);
		if (scUser == null){
			inputStream = new StringBufferInputStream("unexisted");
		}else{
			inputStream = new StringBufferInputStream("existed");
		}
		return SUCCESS;
	}
	
	public String checkEmail() throws Exception {
		ScUserDao scUserDao = DaoFactory.getScUserDao();
		ScUser scUser = null;
		if (isEdit == 0){
			scUser = scUserDao.getUserByEmail(keyWord);
		}
		else if (isEdit == 1) {
			ScUser currentUser = scUserDao.getUserById(userId);
			scUser = scUserDao.getUserByEmail(keyWord, currentUser.getEmail());
		}
		else {
			scUser = scUserDao.getUserByEmail(keyWord);
		}
		if (scUser == null){
			inputStream = new StringBufferInputStream("unexisted");
		}else{
			inputStream = new StringBufferInputStream("existed");
		}
		return SUCCESS;
	}
	
	public String checkMobile() throws Exception {
		ScUserDao scUserDao = DaoFactory.getScUserDao();
		ScUser scUser = null;
		if (isEdit == 0){
			scUser = scUserDao.getUserByMobile(keyWord);
		}
		else if (isEdit == 1) {
			ScUser currentUser = scUserDao.getUserById(userId);
			scUser = scUserDao.getUserByMobile(keyWord, currentUser.getMobileNo());
		}
		else {
			scUser = scUserDao.getUserByMobile(keyWord);
		}
		if (scUser == null){
			inputStream = new StringBufferInputStream("unexisted");
		}else{
			inputStream = new StringBufferInputStream("existed");
		}
		return SUCCESS;
	}
	
	public String sendVerifyCode() throws Exception {
		SmsVerifyCodeDao smsVerifyCodeDao = DaoFactory.getSmsVerifyCodeDao();
		SmsVerifyCode code = smsVerifyCodeDao.getSmsVerifyCodeByMobile(keyWord, 1);
		String newVerifyCode = generateAndSendVerifyCode(keyWord);
		if (code != null){
			smsVerifyCodeDao.editSmsVerifyCode(code.getSmsVerifyCodeId(), code.getMobileNo(), newVerifyCode, 1, System.currentTimeMillis());
		}
		else {
			smsVerifyCodeDao.insertSmsVerifyCode(keyWord, newVerifyCode, 1, System.currentTimeMillis());
		}
		inputStream = new StringBufferInputStream(SUCCESS);
		return SUCCESS;
	} 
	
	public String checkSmsVerifyCode() throws Exception {
		long now = System.currentTimeMillis();
		SmsVerifyCodeDao smsVerifyCodeDao = DaoFactory.getSmsVerifyCodeDao();
		SmsVerifyCode code = smsVerifyCodeDao.getSmsVerifyCodeByMobile(mobile, 1);
		if (code == null || !code.getSmsVerifyCode().equals(keyWord)){
			inputStream = new StringBufferInputStream("illegal");
		}else if (now - code.getGenerateTime() > RegisterAction.SMS_VERIFY_CODE_EXPIRE){
			inputStream = new StringBufferInputStream("expired");
		}else {
			inputStream = new StringBufferInputStream("legal");
		}
		return SUCCESS;
	}
	
	private String generateAndSendVerifyCode(String mobile) throws Exception {
		Random random = new Random();
		int s = random.nextInt(999999);
		String verifyCode = String.valueOf(s);
		SmsService smsService = DaoFactory.getSmsServiceBean();
		smsService.sendRegisterVerifyCodes(mobile, verifyCode);
		return verifyCode;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
}
