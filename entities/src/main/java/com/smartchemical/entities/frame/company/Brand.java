/**
 * 
 */
package com.smartchemical.entities.frame.company;

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
public class Brand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int brandId;
	
	private String brandName;
	
	private Company brandCompany;
	
	private String logoPath;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	@Column(nullable=false, length=255)
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=true) 
	@JoinColumn(name = "companyId")
	public Company getBrandCompany() {
		return brandCompany;
	}

	public void setBrandCompany(Company brandCompany) {
		this.brandCompany = brandCompany;
	}

	@Column(length=255)
	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
}
