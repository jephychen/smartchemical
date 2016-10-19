/**
 * 
 */
package com.smartchemical.entities.frame.order;

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

import com.smartchemical.entities.frame.product.Product;

/**
 * @author Jephy
 *
 */
@Entity
public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int orderItemId;
	
	private PurchaseOrder order;
	
	private Product product;
	
	private float quantity;
	
	private int orderItemStatus = 1;
	
	private Timestamp deliverTime;
	
	private Timestamp receiveTime;
	
	private Timestamp gotTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "orderId")
	public PurchaseOrder getOrder() {
		return order;
	}

	public void setOrder(PurchaseOrder order) {
		this.order = order;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "productId")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(nullable=false)
	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	@Column(nullable=false)
	public int getOrderItemStatus() {
		return orderItemStatus;
	}

	public void setOrderItemStatus(int orderItemStatus) {
		this.orderItemStatus = orderItemStatus;
	}

	@Column(nullable=true)
	public Timestamp getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Timestamp deliverTime) {
		this.deliverTime = deliverTime;
	}

	@Column(nullable=true)
	public Timestamp getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}

	@Column(nullable=true)
	public Timestamp getGotTime() {
		return gotTime;
	}

	public void setGotTime(Timestamp gotTime) {
		this.gotTime = gotTime;
	}

}
