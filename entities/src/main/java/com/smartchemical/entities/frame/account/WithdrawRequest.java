/**
 * 
 */
package com.smartchemical.entities.frame.account;

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

import com.smartchemical.entities.frame.user.BankAccount;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Entity
public class WithdrawRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int requestId;
	
	private ScUser user;
	
	/**
	 * 1 - 正在提现, 2 - 提现完成
	 */
	private int requestStatus;
	
	private BankAccount destAccount;
	
	/**
	 * 提现金额
	 */
	private float amount;
	
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
	public int getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "bankAccountId")
	public BankAccount getDestAccount() {
		return destAccount;
	}

	public void setDestAccount(BankAccount destAccount) {
		this.destAccount = destAccount;
	}

	@Column(nullable=false)
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
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
