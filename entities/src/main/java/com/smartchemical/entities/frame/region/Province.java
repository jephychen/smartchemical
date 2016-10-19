/**
 * 
 */
package com.smartchemical.entities.frame.region;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Jephy
 *
 */
@Entity
public class Province implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int provinceId;
	
	private String provinceName;
	
	private String provinceAlias;
	
	/**
	 * 外键，链接Province的provinceId字段
	 */
	private Region region;
	
	private String description;
	
	/**
	 * 省份下所属的城市
	 */
	private List<City> cities;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	@Column(nullable=false, length=64)
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(length=64)
	public String getProvinceAlias() {
		return provinceAlias;
	}

	public void setProvinceAlias(String provinceAlias) {
		this.provinceAlias = provinceAlias;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "regionId")
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Column(length=1024)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(mappedBy="province",cascade = CascadeType.ALL, fetch = FetchType.EAGER ) 
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
}
