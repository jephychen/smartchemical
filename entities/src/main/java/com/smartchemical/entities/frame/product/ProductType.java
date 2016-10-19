/**
 * 
 */
package com.smartchemical.entities.frame.product;

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
public class ProductType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int productTypeId;
	
	private String productTypeName;
	
	/**
	 * 产品类型的大类，例如钛白粉属于涂料
	 */
	private ProductCategory productCategory;
	
	private String description;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}

	@Column(nullable=false, length=255)
	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "productCategoryId")
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	@Column(length=1024)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
