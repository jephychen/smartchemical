/**
 * 
 */
package com.smartchemical.entities.frame.order;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.smartchemical.entities.frame.user.CartGetter;
import com.smartchemical.entities.frame.user.CartReceiver;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Entity
public class PurchaseOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int purchaseOrderId;
	
	private String purchaseOrderNo;
	
	private List<OrderItem> orderItems;

	private ScUser user;
	
	private int deliverType;
	
	private CartReceiver receiver;
	
	private CartGetter getter;
	
	private int paymentType;
	
	private float logisticFee = 0;
	
	private float acBillServiceFee = 0;
	
	private float totalPrice;
	
	private Invoice invoice;
	
	private String invoiceCompany;
	
	private int status;
	
	private int subStatus;
	
	private Timestamp createTime;
	
	private Timestamp paidTime;
	
	private Timestamp deliverTime;
	
	private Timestamp receiveTime;
	
	private String remark;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	@Column(length=255)
	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	@Transient
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "scUserId")
	public ScUser getUser() {
		return user;
	}

	public void setUser(ScUser user) {
		this.user = user;
	}

	@Column(nullable=false)
	public int getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(int deliverType) {
		this.deliverType = deliverType;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=true) 
	@JoinColumn(name = "receiverId")
	public CartReceiver getReceiver() {
		return receiver;
	}

	public void setReceiver(CartReceiver receiver) {
		this.receiver = receiver;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=true) 
	@JoinColumn(name = "getterId")
	public CartGetter getGetter() {
		return getter;
	}

	public void setGetter(CartGetter getter) {
		this.getter = getter;
	}

	@Column(nullable=false)
	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	@Column(nullable=false)
	public float getLogisticFee() {
		return logisticFee;
	}

	public void setLogisticFee(float logisticFee) {
		this.logisticFee = logisticFee;
	}

	@Column(nullable=false)
	public float getAcBillServiceFee() {
		return acBillServiceFee;
	}

	public void setAcBillServiceFee(float acBillServiceFee) {
		this.acBillServiceFee = acBillServiceFee;
	}

	@Column(nullable=false)
	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=true) 
	@JoinColumn(name = "invoiceId")
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String getInvoiceCompany() {
		return invoiceCompany;
	}

	public void setInvoiceCompany(String invoiceCompany) {
		this.invoiceCompany = invoiceCompany;
	}

	@Column(nullable=false)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(nullable=false)
	public int getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(int subStatus) {
		this.subStatus = subStatus;
	}

	@Column(nullable=false)
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(nullable=true)
	public Timestamp getPaidTime() {
		return paidTime;
	}

	public void setPaidTime(Timestamp paidTime) {
		this.paidTime = paidTime;
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

	@Column(nullable=true, length=1024)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
