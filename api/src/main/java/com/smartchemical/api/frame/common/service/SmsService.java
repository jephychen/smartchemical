/**
 * 
 */
package com.smartchemical.api.frame.common.service;

/**
 * @author Jephy
 *
 */
public interface SmsService {
	public void sendSms(String mobiles, String content);
	
	public void sendRegisterVerifyCodes(String mobile, String verifyCode);
	
	public void sendGetDepositVerifyCodes(String mobile, String verifyCode, String account, String amount);
}
