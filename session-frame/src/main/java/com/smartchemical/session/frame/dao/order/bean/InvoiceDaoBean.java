/**
 * 
 */
package com.smartchemical.session.frame.dao.order.bean;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.order.InvoiceDao;
import com.smartchemical.entities.frame.order.Invoice;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ InvoiceDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/InvoiceDaoBean/remote")
public class InvoiceDaoBean implements InvoiceDao {
	
	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public Invoice insertInvoice(int invoiceType, ScUser user, String invoiceCompany,
			String invoiceAccountName, String invoiceAccountBank,
			String invoiceAddress, String invoiceTaxerId, String invoicePhone,
			long lastModified) {
		try {
			Invoice invoice = new Invoice();
			invoice.setInvoiceType(invoiceType);
			invoice.setUser(user);
			invoice.setInvoiceCompany(invoiceCompany);
			invoice.setInvoiceAccountName(invoiceAccountName);
			invoice.setInvoiceAccountBank(invoiceAccountBank);
			invoice.setInvoiceAddress(invoiceAddress);
			invoice.setInvoiceTaxerId(invoiceTaxerId);
			invoice.setInvoicePhone(invoicePhone);
			invoice.setLastModified(lastModified);
			em.persist(invoice);
			return invoice;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean editInvoice(int invoiceId, int invoiceType, ScUser user,
			String invoiceCompany, String invoiceAccountName,
			String invoiceAccountBank, String invoiceAddress,
			String invoiceTaxerId, String invoicePhone, long lastModified) {
		try {
			Invoice invoice = new Invoice();
			invoice.setInvoiceId(invoiceId);
			invoice.setInvoiceType(invoiceType);
			invoice.setUser(user);
			invoice.setInvoiceCompany(invoiceCompany);
			invoice.setInvoiceAccountName(invoiceAccountName);
			invoice.setInvoiceAccountBank(invoiceAccountBank);
			invoice.setInvoiceAddress(invoiceAddress);
			invoice.setInvoiceTaxerId(invoiceTaxerId);
			invoice.setInvoicePhone(invoicePhone);
			invoice.setLastModified(lastModified);
			em.merge(invoice);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editInvoice(Invoice invoice) {
		try {
			em.merge(invoice);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeInvoice(int invoiceId) {
		try {
			Query query = em.createQuery("delete from Invoice p where p.invoiceId = ?1");
			query.setParameter(1, invoiceId);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Invoice getInvoice(int invoiceId) {
		Invoice invoice = em.find(Invoice.class, invoiceId);
		return invoice;
	}

	public Invoice getLastInvoice(int userId) {
		try {
			Query query = em.createNativeQuery("select * from Invoice where scUserId = ?1 order by lastModified DESC", Invoice.class);
			query.setParameter(1, userId);
			@SuppressWarnings("unchecked")
			List<Invoice> invoices = query.getResultList();
			if (invoices != null && !invoices.isEmpty()){
				return invoices.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
