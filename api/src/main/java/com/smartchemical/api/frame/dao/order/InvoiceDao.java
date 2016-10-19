/**
 * 
 */
package com.smartchemical.api.frame.dao.order;

import com.smartchemical.entities.frame.order.Invoice;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public interface InvoiceDao {
	public Invoice insertInvoice(int invoiceType, ScUser user, String invoiceCompany, String invoiceAccountName, String invoiceAccountBank,
			String invoiceAddress, String invoiceTaxerId, String invoicePhone, long lastModified);
	
	public boolean editInvoice(int invoiceId, int invoiceType, ScUser user, String invoiceCompany, String invoiceAccountName, String invoiceAccountBank,
			String invoiceAddress, String invoiceTaxerId, String invoicePhone, long lastModified);
	
	public boolean editInvoice(Invoice invoice);
	
	public boolean removeInvoice(int invoiceId);
	
	public Invoice getInvoice(int invoiceId);
	
	public Invoice getLastInvoice(int userId);
}
