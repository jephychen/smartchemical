/**
 * 
 */
package com.smartchemical.entities.frame.unit;

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
public class Unit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int unitId;
	
	private String unitName;
	
	private String unitType;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	@Column(nullable=false, unique=true, length=64)
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Column(nullable=false, length=64)
	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	
}
