/**
 * 
 */
package com.smartchemical.entities.frame.order;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Entity
public class Invoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int invoiceId;
	
	/**
	 * 1 - 普通发票， 2 - 增值税发票
	 * */
	private int invoiceType;
	
	private ScUser user;
	
	private String invoiceCompany;
	
	private String invoiceAccountName;
	
	private String invoiceAccountBank;
	
	private String invoiceAddress;
	
	private String invoiceTaxerId;
	
	private String invoicePhone;
	
	private long lastModified;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Column(nullable=false)
	public int getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "scUserId")
	public ScUser getUser() {
		return user;
	}

	public void setUser(ScUser user) {
		this.user = user;
	}

	@Column(length=255)
	public String getInvoiceCompany() {
		return invoiceCompany;
	}

	public void setInvoiceCompany(String invoiceCompany) {
		this.invoiceCompany = invoiceCompany;
	}

	@Column(length=255)
	public String getInvoiceAccountName() {
		return invoiceAccountName;
	}

	public void setInvoiceAccountName(String invoiceAccountName) {
		this.invoiceAccountName = invoiceAccountName;
	}

	@Column(length=255)
	public String getInvoiceAccountBank() {
		return invoiceAccountBank;
	}

	public void setInvoiceAccountBank(String invoiceAccountBank) {
		this.invoiceAccountBank = invoiceAccountBank;
	}

	@Column(length=512)
	public String getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	@Column(length=255)
	public String getInvoiceTaxerId() {
		return invoiceTaxerId;
	}

	public void setInvoiceTaxerId(String invoiceTaxerId) {
		this.invoiceTaxerId = invoiceTaxerId;
	}

	@Column(length=255)
	public String getInvoicePhone() {
		return invoicePhone;
	}

	public void setInvoicePhone(String invoicePhone) {
		this.invoicePhone = invoicePhone;
	}

	@Column(nullable=false)
	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}
	
}
