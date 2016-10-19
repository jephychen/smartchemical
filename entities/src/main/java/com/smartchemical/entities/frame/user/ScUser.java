/**
 * 
 */
package com.smartchemical.entities.frame.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.region.City;
/**
 * @author Jephy
 *
 */
@Entity
public class ScUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;
	
	private String userName;
	
	private String realName;
	
	private String password;
	
	private Date birthday;
	
	private Company company;
	
	private String externalCompanyName;
	
	/**
	 * 用户角色，链接角色表
	 */
	private Role role;
	
	private String email;
	
	private City city;
	
	private String userAddress;
	
	private String mobileNo;
	
	private int cartQuantity = 0;
	
	private float deposit = 0;
	
	private String depositStr;
	
	/**
	 * 0 - 待提交公司证件信息， 1 - 公司证件信息审核中， 2 - 已认证
	 */
	private int companyLicenseStatus = 0;
	
	private Timestamp createTime;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(nullable=false, length=255, unique=true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(nullable=true, length=255)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(nullable=false, length=255)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "companyId")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(nullable=false, length=255)
	public String getExternalCompanyName() {
		return externalCompanyName;
	}

	public void setExternalCompanyName(String externalCompanyName) {
		this.externalCompanyName = externalCompanyName;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "roleId")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(nullable=false, length=255, unique=true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "cityId")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	/*
	 * 目前不用这个字段
	 * */
	@Column(length=255)
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Column(nullable=false, length=64, unique=true)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(nullable=true)
	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	@Column(nullable=false)
	public float getDeposit() {
		return deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	@Transient
	public String getDepositStr() {
		return depositStr;
	}

	public void setDepositStr(String depositStr) {
		this.depositStr = depositStr;
	}

	@Column(nullable=false)
	public int getCompanyLicenseStatus() {
		return companyLicenseStatus;
	}

	public void setCompanyLicenseStatus(int companyLicenseStatus) {
		this.companyLicenseStatus = companyLicenseStatus;
	}

	@Column(nullable=false)
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}
