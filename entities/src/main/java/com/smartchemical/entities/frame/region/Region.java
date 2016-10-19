/**
 * 
 */
package com.smartchemical.entities.frame.region;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Jephy
 *
 */
@Entity
public class Region implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer regionId;
	
	private String regionName;
	
	private String regionAlias;
	
	private String description;
	
	/**
	 * 区域下所属的省份
	 */
	private List<Province> provinces;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getRegionId() {
		return regionId;
	}
	
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	@Column(nullable=false, length=64)
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	@Column(length=64)
	public String getRegionAlias() {
		return regionAlias;
	}

	public void setRegionAlias(String regionAlias) {
		this.regionAlias = regionAlias;
	}

	@Column(length=1024)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	public List<Province> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}
	
}
