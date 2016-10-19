/**
 * 
 */
package com.smartchemical.entities.frame.account;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
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

import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Entity
public class RechargeRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int requestId;
	
	private ScUser user;
	
	/**
	 * 1 - 承兑充值， 2 - 转账充值, 3 - 支付充值
	 * */
	private int requestType;
	
	/**
	 * 1 - 等待转账, 2 - 等待上传承兑汇票, 5 - 承兑汇票审批中, 3 - 等待邮寄承兑汇票, 4 - 正在兑换承兑汇票, 127 - 充值完成
	 * */
	private int requestStatus;
	
	private float amount;
	
	private float actualamount;
	
	private List<String> acBillPaths = new LinkedList<String>();
	
	private Timestamp lastmodified;
	
	private Timestamp createdtime;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
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
	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	@Column(nullable=false)
	public int getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}

	@Column(nullable=false)
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Column(nullable=true)
	public float getActualamount() {
		return actualamount;
	}

	public void setActualamount(float actualamount) {
		this.actualamount = actualamount;
	}

	@Transient
	public List<String> getAcBillPaths() {
		return acBillPaths;
	}

	public void setAcBillPaths(List<String> acBillPaths) {
		this.acBillPaths = acBillPaths;
	}

	@Column(nullable=false)
	public Timestamp getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(Timestamp lastmodified) {
		this.lastmodified = lastmodified;
	}

	@Column(nullable=false)
	public Timestamp getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}
	
}
