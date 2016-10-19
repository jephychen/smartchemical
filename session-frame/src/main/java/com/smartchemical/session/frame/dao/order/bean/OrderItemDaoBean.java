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

import com.smartchemical.api.frame.dao.order.OrderItemDao;
import com.smartchemical.entities.frame.order.OrderItem;
import com.smartchemical.entities.frame.order.PurchaseOrder;
import com.smartchemical.entities.frame.product.Product;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ OrderItemDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/OrderItemDaoBean/remote")
public class OrderItemDaoBean implements OrderItemDao {
	
	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public boolean insertOrderItem(PurchaseOrder order, Product product,
			float quantity, int orderItemStatus, Timestamp deliverTime, Timestamp receiveTime, Timestamp gotTime) {
		try {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(product);
			orderItem.setQuantity(quantity);
			orderItem.setOrderItemStatus(orderItemStatus);
			orderItem.setDeliverTime(deliverTime);
			orderItem.setReceiveTime(receiveTime);
			orderItem.setGotTime(gotTime);
			em.persist(orderItem);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editOrderItem(int orderItemId, PurchaseOrder order,
			Product product, float quantity, int orderItemStatus,
			Timestamp deliverTime, Timestamp receiveTime, Timestamp gotTime) {
		try {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderItemId(orderItemId);
			orderItem.setOrder(order);
			orderItem.setProduct(product);
			orderItem.setQuantity(quantity);
			orderItem.setOrderItemStatus(orderItemStatus);
			orderItem.setDeliverTime(deliverTime);
			orderItem.setReceiveTime(receiveTime);
			orderItem.setGotTime(gotTime);
			em.merge(orderItem);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<OrderItem> getOrderItemByOrderId(int orderId) {
		try {
			Query query = em.createNativeQuery("select * from OrderItem where orderId = ?1", OrderItem.class);
			query.setParameter(1, orderId);
			@SuppressWarnings("unchecked")
			List<OrderItem> items = query.getResultList();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeOrderItemsByOrderId(int orderId) {
		try {
			Query query = em.createNativeQuery("delete from OrderItem where orderId = ?1");
			query.setParameter(1, orderId);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public OrderItem getOrderItemByItemId(int itemId) {
		OrderItem item = em.find(OrderItem.class, itemId);
		return item;
	}

	public boolean editOrderItem(OrderItem item) {
		try{
			em.merge(item);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
