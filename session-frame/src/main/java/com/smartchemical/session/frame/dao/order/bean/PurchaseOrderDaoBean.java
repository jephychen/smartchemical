/**
 * 
 */
package com.smartchemical.session.frame.dao.order.bean;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.order.PurchaseOrderDao;
import com.smartchemical.entities.frame.order.Invoice;
import com.smartchemical.entities.frame.order.OrderItem;
import com.smartchemical.entities.frame.order.PurchaseOrder;
import com.smartchemical.entities.frame.user.CartGetter;
import com.smartchemical.entities.frame.user.CartReceiver;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 * 
 */
@Stateless
@Remote({ PurchaseOrderDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/PurchaseOrderDaoBean/remote")
public class PurchaseOrderDaoBean implements PurchaseOrderDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public PurchaseOrder insertPurchaseOrder(ScUser user, int deliverType,
			CartReceiver receiver, CartGetter getter, int paymentType, float logisticFee, float acBillServiceFee,
			float totalPrice, Invoice invoice, int status, int subStatus, Timestamp createTime, String remark) {
		try {
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			purchaseOrder.setUser(user);
			purchaseOrder.setDeliverType(deliverType);
			purchaseOrder.setReceiver(receiver);
			purchaseOrder.setGetter(getter);
			purchaseOrder.setPaymentType(paymentType);
			purchaseOrder.setLogisticFee(logisticFee);
			purchaseOrder.setAcBillServiceFee(acBillServiceFee);
			purchaseOrder.setTotalPrice(totalPrice);
			purchaseOrder.setInvoice(invoice);
			purchaseOrder.setInvoiceCompany("dummy");
			purchaseOrder.setStatus(status);
			purchaseOrder.setSubStatus(subStatus);
			purchaseOrder.setCreateTime(createTime);
			purchaseOrder.setRemark(remark);
			em.persist(purchaseOrder);
			return purchaseOrder;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean editPurchaseOrder(int purchaseOrderId, ScUser user,
			int deliverType, CartReceiver receiver, CartGetter getter, int paymentType, 
			float logisticFee, float acBillServiceFee, float totalPrice, Invoice invoice, int status, int subStatus, Timestamp createTime,
			Timestamp paidTime, Timestamp deliverTime, Timestamp receiveTime, String remark) {
		try {
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			purchaseOrder.setPurchaseOrderId(purchaseOrderId);
			purchaseOrder.setUser(user);
			purchaseOrder.setDeliverType(deliverType);
			purchaseOrder.setReceiver(receiver);
			purchaseOrder.setGetter(getter);
			purchaseOrder.setPaymentType(paymentType);
			purchaseOrder.setLogisticFee(logisticFee);
			purchaseOrder.setAcBillServiceFee(acBillServiceFee);
			purchaseOrder.setTotalPrice(totalPrice);
			purchaseOrder.setInvoice(invoice);
			purchaseOrder.setInvoiceCompany("dummy");
			purchaseOrder.setStatus(status);
			purchaseOrder.setSubStatus(subStatus);
			purchaseOrder.setCreateTime(createTime);
			purchaseOrder.setPaidTime(paidTime);
			purchaseOrder.setDeliverTime(deliverTime);
			purchaseOrder.setReceiveTime(receiveTime);
			purchaseOrder.setRemark(remark);
			em.merge(purchaseOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean editPurchaseOrder(PurchaseOrder newOrder) {
		try {
			em.merge(newOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public boolean removePurchaseOrder(int purchaseOrderId) {
		try {
			PurchaseOrder purchaseOrder = em.find(PurchaseOrder.class,
					purchaseOrderId);
			em.remove(purchaseOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public PurchaseOrder getPurchaseOrderById(int purchaseOrderId) {
		PurchaseOrder purchaseOrder = em.find(PurchaseOrder.class,
				purchaseOrderId);
		return purchaseOrder;
	}

	public List<PurchaseOrder> getPurchaseOrdersByUser(int userId) {
		try {
			Query query = em.createNativeQuery(
					"select * from PurchaseOrder where scUserId = ?1",
					PurchaseOrder.class);
			query.setParameter(1, userId);
			@SuppressWarnings("unchecked")
			List<PurchaseOrder> orders = query.getResultList();
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<PurchaseOrder> getPurchaseOrdersByUserWithItems(int userId, int status, int pageSize, int pageIndex) {
		try {
			Query query = em.createNativeQuery(
					"select * from PurchaseOrder where scUserId = ?1 and status = ?2 order by createTime DESC",
					PurchaseOrder.class);
			query.setParameter(1, userId);
			query.setParameter(2, status);
			@SuppressWarnings("unchecked")
			List<PurchaseOrder> orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<PurchaseOrder> getPurchaseOrdersByUserWithItems(int userId, int status, int pageSize, int pageIndex, Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery(
					"select * from PurchaseOrder where scUserId = ?1 and status = ?2 and createTime > ?3 order by createTime DESC",
					PurchaseOrder.class);
			query.setParameter(1, userId);
			query.setParameter(2, status);
			query.setParameter(3, sinceDate);
			@SuppressWarnings("unchecked")
			List<PurchaseOrder> orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<PurchaseOrder> getPurchaseOrdersByUserWithItems(int userId, int status, int subStatus) {
		try {
			Query query = em.createNativeQuery(
					"select * from PurchaseOrder where scUserId = ?1 and status = ?2 and subStatus = ?3 order by createTime DESC",
					PurchaseOrder.class);
			query.setParameter(1, userId);
			query.setParameter(2, status);
			query.setParameter(3, subStatus);
			@SuppressWarnings("unchecked")
			List<PurchaseOrder> orders = query.getResultList();
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getPOsCountByUserAndStatus(int userId, int status) {
		try {
			Query query = em.createNativeQuery("select count(purchaseOrderId) from PurchaseOrder where scUserId = ?1 and status = ?2");
			query.setParameter(1, userId);
			query.setParameter(2, status);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getPOsCountByUserAndStatus(int userId, int status, Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery("select count(purchaseOrderId) from PurchaseOrder where scUserId = ?1 and status = ?2 and createTime > ?3");
			query.setParameter(1, userId);
			query.setParameter(2, status);
			query.setParameter(3, sinceDate);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<PurchaseOrder> getAllPOsWithItemsPagingByDeliverType(int status, String deliveryTypes, int pageSize, int pageIndex) {
		try {
			Query query = em.createNativeQuery(
					"select * from PurchaseOrder where status = ?1 and deliverType in " + deliveryTypes + " order by createTime DESC",
					PurchaseOrder.class);
			query.setParameter(1, status);
			@SuppressWarnings("unchecked")
			List<PurchaseOrder> orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<PurchaseOrder> getAllPOsWithItemsPagingByDeliverType(int status, String deliveryTypes, int pageSize, int pageIndex, Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery(
					"select * from PurchaseOrder where status = ?1 and createTime > ?2 and deliverType in " + deliveryTypes + " order by createTime DESC",
					PurchaseOrder.class);
			query.setParameter(1, status);
			query.setParameter(2, sinceDate);
			@SuppressWarnings("unchecked")
			List<PurchaseOrder> orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getAllPOsCountByDeliverType(int status, String deliveryTypes) {
		try {
			Query query = em.createNativeQuery("select count(purchaseOrderId) from PurchaseOrder where status =?1 and deliverType in " + deliveryTypes);
			query.setParameter(1, status);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getAllPOsCountByDeliverType(int status, String deliveryTypes, Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery("select count(purchaseOrderId) from PurchaseOrder where status =?1 and createTime > ?2 and deliverType in " + deliveryTypes);
			query.setParameter(1, status);
			query.setParameter(2, sinceDate);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<PurchaseOrder> getPOsByUserWithItemsByDeliverType(int userId, int status, String deliveryTypes, int pageSize, int pageIndex) {
		try {
			Query query = em.createNativeQuery(
					"select * from PurchaseOrder where scUserId = ?1 and status = ?2 and deliverType in " + deliveryTypes + " order by createTime DESC",
					PurchaseOrder.class);
			query.setParameter(1, userId);
			query.setParameter(2, status);
			@SuppressWarnings("unchecked")
			List<PurchaseOrder> orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<PurchaseOrder> getPOsByUserWithItemsByDeliverType(int userId, int status, String deliveryTypes, int pageSize, int pageIndex, 
			Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery(
					"select * from PurchaseOrder where scUserId = ?1 and status = ?2 and createTime > ?3 and deliverType in " + deliveryTypes + " order by createTime DESC",
					PurchaseOrder.class);
			query.setParameter(1, userId);
			query.setParameter(2, status);
			query.setParameter(3, sinceDate);
			@SuppressWarnings("unchecked")
			List<PurchaseOrder> orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getPOsCountByDeliverType(int userId, int status, String deliveryTypes) {
		try {
			Query query = em.createNativeQuery("select count(purchaseOrderId) from PurchaseOrder where scUserId = ?1 and status = ?2 and deliverType in " + deliveryTypes);
			query.setParameter(1, userId);
			query.setParameter(2, status);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getPOsCountByDeliverType(int userId, int status, String deliveryTypes, Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery("select count(purchaseOrderId) from PurchaseOrder where scUserId = ?1 and status = ?2 and createTime > ?3 " +
					" and deliverType in " + deliveryTypes);
			query.setParameter(1, userId);
			query.setParameter(2, status);
			query.setParameter(3, sinceDate);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private List<OrderItem> getOrderItems(int purchaseOrderId) {
		try {
			Query query = em.createNativeQuery("select * from OrderItem where orderId = ?1", OrderItem.class);
			query.setParameter(1, purchaseOrderId);
			@SuppressWarnings("unchecked")
			List<OrderItem> items = query.getResultList();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	private List<OrderItem> getOrderItemsByMerchantCompany(int purchaseOrderId, int companyId) {
//		try {
//			Query query = em.createQuery("select i from OrderItem i where i.order.purchaseOrderId = ?1 and i.product.merchantCompany.companyId = ?2");
//			query.setParameter(1, purchaseOrderId);
//			query.setParameter(2, companyId);
//			@SuppressWarnings("unchecked")
//			List<OrderItem> items = query.getResultList();
//			return items;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	private List<OrderItem> getOrderItemsBySupplierCompany(int purchaseOrderId, int companyId) {
//		try {
//			Query query = em.createQuery("select i from OrderItem i where i.order.purchaseOrderId = ?1 and i.product.company.companyId = ?2");
//			query.setParameter(1, purchaseOrderId);
//			query.setParameter(2, companyId);
//			@SuppressWarnings("unchecked")
//			List<OrderItem> items = query.getResultList();
//			return items;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	public PurchaseOrder getPurchaseOrderByNo(String purchaseOrderNo) {
		try {
			Query query = em.createNativeQuery(
					"select * from PurchaseOrder where purchaseOrderNo = ?1",
					PurchaseOrder.class);
			query.setParameter(1, purchaseOrderNo);
			@SuppressWarnings("unchecked")
			List<PurchaseOrder> orders = query.getResultList();
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PurchaseOrder> getAllPurchaseOrderPaging(int pageSize, int pageIndex) {
		try {
			Query query = em.createNativeQuery("select * from PurchaseOrder order by createTime DESC", PurchaseOrder.class);
			List<PurchaseOrder> orders = null;
			if (pageSize <= 0 || pageIndex <= 0){
				orders = query.setMaxResults(50).setFirstResult(0).getResultList();
			}
			else {
				orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PurchaseOrder> getAllPurchaseOrderPaging(int pageSize, int pageIndex, Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery("select * from PurchaseOrder where createTime > ?1 order by createTime DESC", PurchaseOrder.class);
			query.setParameter(1, sinceDate);
			List<PurchaseOrder> orders = null;
			if (pageSize <= 0 || pageIndex <= 0){
				orders = query.setMaxResults(50).setFirstResult(0).getResultList();
			}
			else {
				orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PurchaseOrder> getAllPurchaseOrderPagingByStatus(int status, int pageSize, int pageIndex) {
		try {
			Query query = em.createNativeQuery("select * from PurchaseOrder where status =?1 order by createTime DESC", PurchaseOrder.class);
			query.setParameter(1, status);
			List<PurchaseOrder> orders = null;
			if (pageSize <= 0 || pageIndex <= 0){
				orders = query.setMaxResults(50).setFirstResult(0).getResultList();
			}
			else {
				orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PurchaseOrder> getAllPurchaseOrderPagingByStatus(int status, int pageSize, int pageIndex, Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery("select * from PurchaseOrder where status =?1 and createTime > ?2 order by createTime DESC", PurchaseOrder.class);
			query.setParameter(1, status);
			query.setParameter(2, sinceDate);
			List<PurchaseOrder> orders = null;
			if (pageSize <= 0 || pageIndex <= 0){
				orders = query.setMaxResults(50).setFirstResult(0).getResultList();
			}
			else {
				orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getAllPOCountByStatus(int status) {
		try {
			Query query = em.createNativeQuery("select count(purchaseOrderId) from PurchaseOrder where status =?1");
			query.setParameter(1, status);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getAllPOCountByStatus(int status, Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery("select count(purchaseOrderId) from PurchaseOrder where status =?1 and createTime > ?2");
			query.setParameter(1, status);
			query.setParameter(2, sinceDate);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getAllPurchaseOrderCount() {
		try {
			Query query = em.createNativeQuery("select count(purchaseOrderId) from PurchaseOrder");
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getAllPurchaseOrderCount(Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery("select count(purchaseOrderId) from PurchaseOrder where createTime > ?1");
			query.setParameter(1, sinceDate);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getPOsCountByUser(int userId, Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery("select count(purchaseOrderId) from PurchaseOrder where scUserId = ?1 and createTime > ?2");
			query.setParameter(1, userId);
			query.setParameter(2, sinceDate);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PurchaseOrder> getPOsByUserWithItems(int userId, int pageSize,
			int pageIndex, Timestamp sinceDate) {
		try {
			Query query = em.createNativeQuery("select * from PurchaseOrder where scUserId =?1 and createTime > ?2 order by createTime DESC", PurchaseOrder.class);
			query.setParameter(1, userId);
			query.setParameter(2, sinceDate);
			List<PurchaseOrder> orders = null;
			if (pageSize <= 0 || pageIndex <= 0){
				orders = query.setMaxResults(50).setFirstResult(0).getResultList();
			}
			else {
				orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public int getPOsCountByMerchantCompanyStatus(int companyId, String status, Timestamp sinceDate) {
		try {
			Query itemQuery = em.createQuery("select i.order.purchaseOrderId from OrderItem i where i.product.merchantCompany.companyId = ?1");
			itemQuery.setParameter(1, companyId);
			List<Number> items = itemQuery.getResultList();
			if (items == null || items.isEmpty()){
				return 0;
			}
			Query query = em.createQuery("select count(p.purchaseOrderId) from PurchaseOrder p where p.purchaseOrderId in " + makeIdsStr(items) 
					+ " and p.status in " + status + " and p.createTime > ?1");
			query.setParameter(1, sinceDate);
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PurchaseOrder> getPOsByMerchantCompanyStatus(int companyId, String status,
			int pageSize, int pageIndex, Timestamp sinceDate) {
		try {
			//先取出对应公司的产品的orderItem，找出对应的PurchaseOrder
			Query itemQuery = em.createQuery("select i.order.purchaseOrderId from OrderItem i where i.product.merchantCompany.companyId = ?1");
			itemQuery.setParameter(1, companyId);
			List<Number> items = itemQuery.getResultList();
			if (items == null || items.isEmpty()){
				return null;
			}
			Query query = em.createNativeQuery("select * from PurchaseOrder where purchaseOrderId in " + makeIdsStr(items) 
					+ " and status in " + status + " and createTime > ?1 order by createTime DESC", PurchaseOrder.class);
			query.setParameter(1, sinceDate);
			List<PurchaseOrder> orders = null;
			if (pageSize <= 0 || pageIndex <= 0){
				orders = query.setMaxResults(50).setFirstResult(0).getResultList();
			}
			else {
				orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public int getPOsCountByMerchantCompanyStatusDeliveryType(int companyId,
			String status, String deliveryType, Timestamp sinceDate) {
		try {
			Query itemQuery = em.createQuery("select i.order.purchaseOrderId from OrderItem i where i.product.merchantCompany.companyId = ?1");
			itemQuery.setParameter(1, companyId);
			List<Number> items = itemQuery.getResultList();
			if (items == null || items.isEmpty()){
				return 0;
			}
			Query query = em.createQuery("select count(p.purchaseOrderId) from PurchaseOrder p where p.purchaseOrderId in " + makeIdsStr(items) 
					+ " and p.status in " + status + " and p.deliverType in " + deliveryType + " and p.createTime > ?1");
			query.setParameter(1, sinceDate);
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PurchaseOrder> getPOsByMerchantCompanyStatusDeliveryType(
			int companyId, String status, String deliveryType, int pageSize,
			int pageIndex, Timestamp sinceDate) {
		try {
			//先取出对应公司的产品的orderItem，找出对应的PurchaseOrder
			Query itemQuery = em.createQuery("select i.order.purchaseOrderId from OrderItem i where i.product.merchantCompany.companyId = ?1");
			itemQuery.setParameter(1, companyId);
			List<Number> items = itemQuery.getResultList();
			if (items == null || items.isEmpty()){
				return null;
			}
			Query query = em.createNativeQuery("select * from PurchaseOrder where purchaseOrderId in " + makeIdsStr(items) 
					+ " and status in " + status + " and deliverType in " + deliveryType + " and createTime > ?1 order by createTime DESC", PurchaseOrder.class);
			query.setParameter(1, sinceDate);
			List<PurchaseOrder> orders = null;
			if (pageSize <= 0 || pageIndex <= 0){
				orders = query.setMaxResults(50).setFirstResult(0).getResultList();
			}
			else {
				orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public int getPOsCountBySupplierCompanyStatus(int companyId, String status,
			Timestamp sinceDate) {
		try {
			Query itemQuery = em.createQuery("select i.order.purchaseOrderId from OrderItem i where i.product.company.companyId = ?1");
			itemQuery.setParameter(1, companyId);
			List<Number> items = itemQuery.getResultList();
			if (items == null || items.isEmpty()){
				return 0;
			}
			Query query = em.createQuery("select count(p.purchaseOrderId) from PurchaseOrder p where p.purchaseOrderId in " + makeIdsStr(items) 
					+ " and p.status in " + status + " and p.createTime > ?1");
			query.setParameter(1, sinceDate);
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PurchaseOrder> getPOsBySupplierCompanyStatus(int companyId,
			String status, int pageSize, int pageIndex, Timestamp sinceDate) {
		try {
			//先取出对应公司的产品的orderItem，找出对应的PurchaseOrder
			Query itemQuery = em.createQuery("select i.order.purchaseOrderId from OrderItem i where i.product.company.companyId = ?1");
			itemQuery.setParameter(1, companyId);
			List<Number> items = itemQuery.getResultList();
			if (items == null || items.isEmpty()){
				return null;
			}
			Query query = em.createNativeQuery("select * from PurchaseOrder where purchaseOrderId in " + makeIdsStr(items) 
					+ " and status in " + status + " and createTime > ?1 order by createTime DESC", PurchaseOrder.class);
			query.setParameter(1, sinceDate);
			List<PurchaseOrder> orders = null;
			if (pageSize <= 0 || pageIndex <= 0){
				orders = query.setMaxResults(50).setFirstResult(0).getResultList();
			}
			else {
				orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public int getPOsCountBySupplierCompanyStatusDeliveryType(int companyId,
			String status, String deliveryType, Timestamp sinceDate) {
		try {
			Query itemQuery = em.createQuery("select i.order.purchaseOrderId from OrderItem i where i.product.company.companyId = ?1");
			itemQuery.setParameter(1, companyId);
			List<Number> items = itemQuery.getResultList();
			if (items == null || items.isEmpty()){
				return 0;
			}
			Query query = em.createQuery("select count(p.purchaseOrderId) from PurchaseOrder p where p.purchaseOrderId in " + makeIdsStr(items) 
					+ " and p.status in " + status + " and p.deliverType in " + deliveryType + " and p.createTime > ?1");
			query.setParameter(1, sinceDate);
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PurchaseOrder> getPOsBySupplierCompanyStatusDeliveryType(
			int companyId, String status, String deliveryType, int pageSize,
			int pageIndex, Timestamp sinceDate) {
		try {
			//先取出对应公司的产品的orderItem，找出对应的PurchaseOrder
			Query itemQuery = em.createQuery("select i.order.purchaseOrderId from OrderItem i where i.product.company.companyId = ?1");
			itemQuery.setParameter(1, companyId);
			List<Number> items = itemQuery.getResultList();
			if (items == null || items.isEmpty()){
				return null;
			}
			Query query = em.createNativeQuery("select * from PurchaseOrder where purchaseOrderId in " + makeIdsStr(items) 
					+ " and status in " + status + " and deliverType in " + deliveryType + " and createTime > ?1 order by createTime DESC", PurchaseOrder.class);
			query.setParameter(1, sinceDate);
			List<PurchaseOrder> orders = null;
			if (pageSize <= 0 || pageIndex <= 0){
				orders = query.setMaxResults(50).setFirstResult(0).getResultList();
			}
			else {
				orders = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			if (orders == null || orders.isEmpty()){
				return null;
			}
			for (PurchaseOrder order : orders){
				order.setOrderItems(getOrderItems(order.getPurchaseOrderId()));
			}
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String makeIdsStr(List<Number> items){
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for (int i = 0; i< items.size(); i++){
			sb.append(items.get(i));
			if (i < (items.size() - 1)){
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}
}
