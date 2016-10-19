/**
 * 
 */
package com.smartchemical.entities.frame.user;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Jephy
 *
 */
@Entity
public class CartGetter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int getterId;
	
	private ScUser user;
	
	private String getterName;
	
	private String getterIdNo;
	
	private String getterMobileNo;
	
	private String getterEmail;
	
	private String truckLicenseNo;
	
	private long lastSelected;
	
	/**
	 * 1 - enable, 0 - disable
	 * */
	private int enable = 1;
	
	private String remark;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getGetterId() {
		return getterId;
	}

	public void setGetterId(int getterId) {
		this.getterId = getterId;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "scUserId")
	public ScUser getUser() {
		return user;
	}

	public void setUser(ScUser user) {
		this.user = user;
	}

	@Column(nullable=false, length=64)
	public String getGetterName() {
		return getterName;
	}

	public void setGetterName(String getterName) {
		this.getterName = getterName;
	}

	@Column(nullable=false, length=64)
	public String getGetterIdNo() {
		return getterIdNo;
	}

	public void setGetterIdNo(String getterIdNo) {
		this.getterIdNo = getterIdNo;
	}

	@Column(nullable=false, length=64)
	public String getGetterMobileNo() {
		return getterMobileNo;
	}

	public void setGetterMobileNo(String getterMobileNo) {
		this.getterMobileNo = getterMobileNo;
	}

	@Column(length=255)
	public String getGetterEmail() {
		return getterEmail;
	}

	public void setGetterEmail(String getterEmail) {
		this.getterEmail = getterEmail;
	}

	@Column(nullable=false, length=64)
	public String getTruckLicenseNo() {
		return truckLicenseNo;
	}

	public void setTruckLicenseNo(String truckLicenseNo) {
		this.truckLicenseNo = truckLicenseNo;
	}

	@Column(nullable=false)
	public long getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(long lastSelected) {
		this.lastSelected = lastSelected;
	}

	@Column(nullable=false)
	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	@Column(length=1024)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
