/**
 * 
 */
package com.smartchemical.entities.frame.region;

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
public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int cityId;
	
	private String cityName;
	
	private String cityAlias;
	
	/**
	 * 外键，链接Province的provinceId字段
	 */
	private Province province;
	
	private String description;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	@Column(nullable=false, length=64)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(length=64)
	public String getCityAlias() {
		return cityAlias;
	}

	public void setCityAlias(String cityAlias) {
		this.cityAlias = cityAlias;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "provinceId")
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Column(length=1024)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
