/**
 * 
 */
package com.smartchemical.entities.frame.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Jephy
 *
 */
@Entity
public class ProductNo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int productNoId;
	
	private String productNoName;
	
	private String description;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getProductNoId() {
		return productNoId;
	}

	public void setProductNoId(int productNoId) {
		this.productNoId = productNoId;
	}

	@Column(nullable=false, unique=true, length=64)
	public String getProductNoName() {
		return productNoName;
	}

	public void setProductNoName(String productNoName) {
		this.productNoName = productNoName;
	}

	@Column(length=1024)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
