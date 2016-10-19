/**
 * 
 */
package com.smartchemical.test;

import com.opensymphony.xwork2.Action;

/**
 * @author Jephy
 *
 */
public class VerifyAction implements Action {
	
	private String verifyCode;
	
	private String tip;

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String execute() throws Exception {
		if (!verifyCode.equalsIgnoreCase("sc2016zhixuan")){
			tip = "邀请码错误！";
			return ERROR;
		}
		return SUCCESS;
	}

}
