/**
 * 
 */
package com.smartchemical.api.frame.dao.user;

import com.smartchemical.entities.frame.user.SmsVerifyCode;

/**
 * @author Jephy
 *
 */
public interface SmsVerifyCodeDao {
	public boolean insertSmsVerifyCode(String mobile, String smsVerifyCode, int codeType, long generateDate);
	
	public SmsVerifyCode getSmsVerifyCodeByMobile(String mobile, int codeType);
	
	public boolean editSmsVerifyCode(int smsVerifyCodeId, String mobile, String smsVerifyCode, int codeType, long generateDate);
	
	public boolean removeSmsVerifyCodeByMobile(String mobile);
}
