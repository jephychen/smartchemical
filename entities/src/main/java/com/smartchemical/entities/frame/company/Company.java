/**
 * 
 */
package com.smartchemical.entities.frame.company;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int companyId;
	
	private String companyName;
	
	private String companyAlias;
	
	private String companyFullName;
	
	/**
	 * 1 - 供应商，2 - 贸易商
	 */
	private int companyType;
	
	/**
	 * 1 - 启用，0 - 禁用
	 */
	private int companyStatus;
	
	private String companySlogan;
	/**
	 * 公司所在城市
	 */
	private City companyCity;
	
	private String companyAddress;
	
	private String companyIcon;
	
	private String companyUrl;
	
	private double longitude = 0;
	
	private double latitude = 0;
	
	private String description;
	
	private String longDescription;
	
	/**
	 * 最近修改时间
	 */
	private Timestamp lastModified;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Column(nullable=false, length=255)
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(length=255)
	public String getCompanyAlias() {
		return companyAlias;
	}

	public void setCompanyAlias(String companyAlias) {
		this.companyAlias = companyAlias;
	}

	@Column(length=255)
	public String getCompanyFullName() {
		return companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	@Column(nullable=false)
	public int getCompanyType() {
		return companyType;
	}

	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}

	@Column(nullable=false)
	public int getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(int companyStatus) {
		this.companyStatus = companyStatus;
	}

	@Column(length=1024)
	public String getCompanySlogan() {
		return companySlogan;
	}

	public void setCompanySlogan(String companySlogan) {
		this.companySlogan = companySlogan;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "companyCityId")
	public City getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(City companyCity) {
		this.companyCity = companyCity;
	}

	@Column(nullable=false, length=512)
	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Column(nullable=false, length=255)
	public String getCompanyIcon() {
		return companyIcon;
	}

	public void setCompanyIcon(String companyIcon) {
		this.companyIcon = companyIcon;
	}

	@Column(nullable=false, length=512)
	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	@Column(nullable=true)
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Column(nullable=true)
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Column(length=1024)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(length=8000)
	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	@Column(nullable=false)
	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

}
