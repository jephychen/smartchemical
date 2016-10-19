/**
 * 
 */
package com.smartchemical.api.frame.dao.order;

import java.sql.Timestamp;
import java.util.List;

import com.smartchemical.entities.frame.order.Invoice;
import com.smartchemical.entities.frame.order.PurchaseOrder;
import com.smartchemical.entities.frame.user.CartGetter;
import com.smartchemical.entities.frame.user.CartReceiver;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 * 
 */
public interface PurchaseOrderDao {
	public PurchaseOrder insertPurchaseOrder(ScUser user, int deliverType,
			CartReceiver receiver, CartGetter getter, int paymentType,
			float logisticFee, float acBillServiceFee, float totalPrice,
			Invoice invoice, int status, int subStatus,
			Timestamp createTime, String remark);

	public boolean editPurchaseOrder(int purchaseOrderId, ScUser user,
			int deliverType, CartReceiver receiver, CartGetter getter,
			int paymentType, float logisticFee, float acBillServiceFee,
			float totalPrice, Invoice invoice, int status, 
			int subStatus,Timestamp createTime, Timestamp paidTime, Timestamp deliverTime,
			Timestamp receiveTime, String remark);

	public boolean editPurchaseOrder(PurchaseOrder newOrder);

	public boolean removePurchaseOrder(int purchaseOrderId);

	public PurchaseOrder getPurchaseOrderById(int purchaseOrderId);

	public PurchaseOrder getPurchaseOrderByNo(String purchaseOrderNo);

	public List<PurchaseOrder> getAllPurchaseOrderPaging(int pageSize,
			int pageIndex);

	public List<PurchaseOrder> getAllPurchaseOrderPaging(int pageSize,
			int pageIndex, Timestamp sinceDate);

	public int getAllPurchaseOrderCount();

	public int getAllPurchaseOrderCount(Timestamp sinceDate);

	public List<PurchaseOrder> getPurchaseOrdersByUser(int userId);

	public int getPOsCountByUser(int userId, Timestamp sinceDate);

	public List<PurchaseOrder> getPOsByUserWithItems(int userId, int pageSize,
			int pageIndex, Timestamp sinceDate);

	public List<PurchaseOrder> getPurchaseOrdersByUserWithItems(int userId,
			int status, int pageSize, int pageIndex);

	public List<PurchaseOrder> getPurchaseOrdersByUserWithItems(int userId,
			int status, int pageSize, int pageIndex, Timestamp sinceDate);

	public List<PurchaseOrder> getPurchaseOrdersByUserWithItems(int userId,
			int status, int subStatus);

	public int getPOsCountByUserAndStatus(int userId, int status);

	public int getPOsCountByUserAndStatus(int userId, int status,
			Timestamp sinceDate);

	public List<PurchaseOrder> getAllPOsWithItemsPagingByDeliverType(
			int status, String deliveryTypes, int pageSize, int pageIndex);

	public List<PurchaseOrder> getAllPOsWithItemsPagingByDeliverType(
			int status, String deliveryTypes, int pageSize, int pageIndex,
			Timestamp sinceDate);

	public int getAllPOsCountByDeliverType(int status, String deliveryTypes);

	public int getAllPOsCountByDeliverType(int status, String deliveryTypes,
			Timestamp sinceDate);

	public List<PurchaseOrder> getPOsByUserWithItemsByDeliverType(int userId,
			int status, String deliveryTypes, int pageSize, int pageIndex);

	public List<PurchaseOrder> getPOsByUserWithItemsByDeliverType(int userId,
			int status, String deliveryTypes, int pageSize, int pageIndex,
			Timestamp sinceDate);

	public int getPOsCountByDeliverType(int userId, int status,
			String deliveryTypes);

	public int getPOsCountByDeliverType(int userId, int status,
			String deliveryTypes, Timestamp sinceDate);

	public List<PurchaseOrder> getAllPurchaseOrderPagingByStatus(int status,
			int pageSize, int pageIndex);

	public List<PurchaseOrder> getAllPurchaseOrderPagingByStatus(int status,
			int pageSize, int pageIndex, Timestamp sinceDate);

	public int getAllPOCountByStatus(int status);

	public int getAllPOCountByStatus(int status, Timestamp sinceDate);

	public int getPOsCountByMerchantCompanyStatus(int companyId, String status, Timestamp sinceDate);
	
	public List<PurchaseOrder> getPOsByMerchantCompanyStatus(int companyId, String status, int pageSize, int pageIndex, Timestamp sinceDate);
	
	public int getPOsCountByMerchantCompanyStatusDeliveryType(int companyId, String status, String deliveryType, Timestamp sinceDate);
	
	public List<PurchaseOrder> getPOsByMerchantCompanyStatusDeliveryType(int companyId, String status, String deliveryType, int pageSize, int pageIndex, Timestamp sinceDate);
	
	public int getPOsCountBySupplierCompanyStatus(int companyId, String status, Timestamp sinceDate);
	
	public List<PurchaseOrder> getPOsBySupplierCompanyStatus(int companyId, String status, int pageSize, int pageIndex, Timestamp sinceDate);
	
	public int getPOsCountBySupplierCompanyStatusDeliveryType(int companyId, String status, String deliveryType, Timestamp sinceDate);
	
	public List<PurchaseOrder> getPOsBySupplierCompanyStatusDeliveryType(int companyId, String status, String deliveryType, int pageSize, int pageIndex, Timestamp sinceDate);
	
}
