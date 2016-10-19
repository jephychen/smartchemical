/**
 * 
 */
package com.smartchemical.entities.frame.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Jephy
 *
 */
@Entity
public class SmsVerifyCode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int smsVerifyCodeId;
	
	private String mobileNo;
	
	private String smsVerifyCode;
	
	/**
	 * 1 - 注册验证，2 - 提现验证
	 */
	private int codeType;
	
	private long generateTime;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getSmsVerifyCodeId() {
		return smsVerifyCodeId;
	}

	public void setSmsVerifyCodeId(int smsVerifyCodeId) {
		this.smsVerifyCodeId = smsVerifyCodeId;
	}

	@Column(nullable=false, length=64, unique=true)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(nullable=false, length=64)
	public String getSmsVerifyCode() {
		return smsVerifyCode;
	}

	public void setSmsVerifyCode(String smsVerifyCode) {
		this.smsVerifyCode = smsVerifyCode;
	}

	@Column(nullable=false)
	public int getCodeType() {
		return codeType;
	}

	public void setCodeType(int codeType) {
		this.codeType = codeType;
	}

	@Column(nullable=false)
	public long getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(long generateTime) {
		this.generateTime = generateTime;
	}
	
}
