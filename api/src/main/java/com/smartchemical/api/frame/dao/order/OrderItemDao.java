/**
 * 
 */
package com.smartchemical.api.frame.dao.order;

import java.sql.Timestamp;
import java.util.List;

import com.smartchemical.entities.frame.order.PurchaseOrder;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.order.OrderItem;

/**
 * @author Jephy
 *
 */
public interface OrderItemDao {
	public boolean insertOrderItem(PurchaseOrder order, Product product, float quantity, int orderItemStatus, 
			Timestamp deliverTime, Timestamp receiveTime, Timestamp gotTime);
	
	public boolean editOrderItem(int orderItemId, PurchaseOrder order, Product product, float quantity, int orderItemStatus,
			Timestamp deliverTime, Timestamp receiveTime, Timestamp gotTime);
	
	public boolean editOrderItem(OrderItem item);
	
	public OrderItem getOrderItemByItemId(int itemId);
	
	public List<OrderItem> getOrderItemByOrderId(int orderId);
	
	public boolean removeOrderItemsByOrderId(int orderId);
}
