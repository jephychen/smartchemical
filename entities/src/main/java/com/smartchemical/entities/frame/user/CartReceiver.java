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

import com.smartchemical.entities.frame.region.City;

/**
 * @author Jephy
 *
 */
@Entity
public class CartReceiver implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int receiverId;
	
	private String receiverName;
	
	private ScUser user;
	
	private City city;
	
	private String address;
	
	private String mobileNo;
	
	private String email;
	
	private long lastSelected;
	
	/**
	 * 1 - enable, 0 - disable
	 * */
	private int enable = 1;

	private String remark;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	@Column(nullable=false, length=64)
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "scUserId")
	public ScUser getUser() {
		return user;
	}

	public void setUser(ScUser user) {
		this.user = user;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "cityId")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(nullable=false, length=512)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(nullable=false, length=64)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(length=255)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
