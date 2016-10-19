/**
 * 
 */
package com.smartchemical.entities.frame.warehouse;

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
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Entity
public class Warehouse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int warehouseId;
	
	private String warehouseName;
	
	private City city;
	
	private String address;
	
	private double longitude = 0;
	
	private double latitude = 0;

	/**
	 * 仓库管理员，链接用户表
	 */
	private ScUser warehouseAdmin;
	
	/**
	 * 仓库状态 1 - 可用， 2 - 不可用
	 */
	private int warehouseStatus;
	
	/**
	 * 最近修改时间
	 */
	private Timestamp lastModified;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	@Column(nullable=false, length=255)
	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "cityId")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(nullable=false, length=1024)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "warehouseAdminId")
	public ScUser getWarehouseAdmin() {
		return warehouseAdmin;
	}

	public void setWarehouseAdmin(ScUser warehouseAdmin) {
		this.warehouseAdmin = warehouseAdmin;
	}
	
	@Column(nullable=false)
	public int getWarehouseStatus() {
		return warehouseStatus;
	}

	public void setWarehouseStatus(int warehouseStatus) {
		this.warehouseStatus = warehouseStatus;
	}
	
	@Column(nullable=false)
	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}
	
}
