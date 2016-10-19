/**
 * 
 */
package com.smartchemical.entities.frame.configuration;

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
public class SmsConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int smsConfId;
	
	private String smsConfName;
	
	private String url;
	
	private String appkey;
	
	private String secret;
	
	private String extend;
	
	private String smsType;
	
	private String smsFreeSignName;
	
	private String smsParamString;
	
	private String smsTemplateCode;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getSmsConfId() {
		return smsConfId;
	}

	public void setSmsConfId(int smsConfId) {
		this.smsConfId = smsConfId;
	}

	@Column(length=255, unique=true)
	public String getSmsConfName() {
		return smsConfName;
	}

	public void setSmsConfName(String smsConfName) {
		this.smsConfName = smsConfName;
	}

	@Column(length=255, nullable=false)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(length=255, nullable=false)
	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	@Column(length=255, nullable=false)
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Column(length=255)
	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	@Column(length=255)
	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	@Column(length=255)
	public String getSmsFreeSignName() {
		return smsFreeSignName;
	}

	public void setSmsFreeSignName(String smsFreeSignName) {
		this.smsFreeSignName = smsFreeSignName;
	}

	@Column(length=255)
	public String getSmsParamString() {
		return smsParamString;
	}

	public void setSmsParamString(String smsParamString) {
		this.smsParamString = smsParamString;
	}

	@Column(length=255)
	public String getSmsTemplateCode() {
		return smsTemplateCode;
	}

	public void setSmsTemplateCode(String smsTemplateCode) {
		this.smsTemplateCode = smsTemplateCode;
	}
	
}
