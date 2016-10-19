/**
 * 
 */
package com.smartchemical.usercenter.supplieruser;

import java.sql.Timestamp;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.order.DeliverType;
import com.smartchemical.api.frame.dao.order.OrderItemDao;
import com.smartchemical.api.frame.dao.order.OrderItemStatus;
import com.smartchemical.api.frame.dao.order.OrderStatus;
import com.smartchemical.api.frame.dao.order.PurchaseOrderDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.common.util.ScUtil;
import com.smartchemical.entities.frame.order.OrderItem;
import com.smartchemical.entities.frame.order.PurchaseOrder;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class QueryOrderAction implements Action {
	
	private String pageTitle = "供应商后台";
	
	private String pageActionName = "SupplierQueryOrder";
	
	private int queryType;
	
	private List<PurchaseOrder> orders;
	
	private int orderItemId;
	
	private int currentPage = 1;
	
	private static final int PAGE_SIZE = 5;
	
	private Paginator paginator;
	
	private int lastMonth = 0;

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getPageActionName() {
		return pageActionName;
	}

	public void setPageActionName(String pageActionName) {
		this.pageActionName = pageActionName;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public List<PurchaseOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<PurchaseOrder> orders) {
		this.orders = orders;
	}

	public int getLastMonth() {
		return lastMonth;
	}

	public void setLastMonth(int lastMonth) {
		this.lastMonth = lastMonth;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}
	
	private void setPaginator(int totalRecordCount){
		paginator = new Paginator(totalRecordCount, currentPage, PAGE_SIZE);
		currentPage = paginator.getCurrentPage();
	}
	
	private void setAllOrderPaging() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		String status = "(" + OrderStatus.PAYED.getIndex() + "," + OrderStatus.RECEIVED.getIndex() + ")";
		int totalRecordCount = orderDao.getPOsCountBySupplierCompanyStatus(user.getCompany().getCompanyId(), status, ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalRecordCount);
		orders = orderDao.getPOsBySupplierCompanyStatus(user.getCompany().getCompanyId(), status, PAGE_SIZE, 
				paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	private void setWaitingReceivedPaging() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		String status = "(" + OrderStatus.PAYED.getIndex() + ")";
		String deliveryType = "(" + DeliverType.VERDOR.getIndex() + "," + DeliverType.ZHIXUAN.getIndex() + ")";
		int totalRecordCount = orderDao.getPOsCountBySupplierCompanyStatusDeliveryType(user.getCompany().getCompanyId(), status, deliveryType, ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalRecordCount);
		orders = orderDao.getPOsBySupplierCompanyStatusDeliveryType(user.getCompany().getCompanyId(), status, deliveryType, PAGE_SIZE, 
				paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	private void setWaitingGotPaging() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		String status = "(" + OrderStatus.PAYED.getIndex() + ")";
		String deliveryType = "(" + DeliverType.USER.getIndex() + ")";
		int totalRecordCount = orderDao.getPOsCountBySupplierCompanyStatusDeliveryType(user.getCompany().getCompanyId(), status, deliveryType, ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalRecordCount);
		orders = orderDao.getPOsBySupplierCompanyStatusDeliveryType(user.getCompany().getCompanyId(), status, deliveryType, PAGE_SIZE, 
				paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	private void setDoneOrderPaging() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		String status = "(" + OrderStatus.RECEIVED.getIndex() + ")";
		int totalRecordCount = orderDao.getPOsCountBySupplierCompanyStatus(user.getCompany().getCompanyId(), status, ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalRecordCount);
		orders = orderDao.getPOsBySupplierCompanyStatus(user.getCompany().getCompanyId(), status, PAGE_SIZE, 
				paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	public String confirmDeliver() throws Exception {
		OrderItemDao itemDao = DaoFactory.getOrderItemDao();
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		OrderItem currentItem = itemDao.getOrderItemByItemId(orderItemId);
		PurchaseOrder currentOrder = orderDao.getPurchaseOrderById(currentItem.getOrder().getPurchaseOrderId());
		//如果订单状态为待付款，则不进行动作。
		if (currentOrder.getStatus() == OrderStatus.WAITING_TO_BE_PAID.getIndex() 
				|| currentOrder.getStatus() == OrderStatus.RECEIVED.getIndex()){
			return SUCCESS;
		}
		if (currentOrder.getDeliverType() == DeliverType.USER.getIndex()){
			currentItem.setOrderItemStatus(OrderItemStatus.WAITING_TO_BE_GOT.getIndex());
		}
		else{
			currentItem.setOrderItemStatus(OrderItemStatus.WAITING_TO_BE_RECEIVED.getIndex());
			currentItem.setDeliverTime(new Timestamp(System.currentTimeMillis()));
		}
		itemDao.editOrderItem(currentItem);
		if (queryType == 0){
			setAllOrderPaging();
		}
		else if (queryType == 2){
			setWaitingReceivedPaging();
		}
		else{
			orders = orderDao.getAllPurchaseOrderPaging(PAGE_SIZE, 1, ScUtil.makeSinceDate(lastMonth));
		}
		return SUCCESS;
	}

	public String allOrders() throws Exception {
		queryType = 0;
		setAllOrderPaging();
		return SUCCESS;
	}
	
	public String waitingReceived() throws Exception {
		queryType = 2;
		setWaitingReceivedPaging();
		return SUCCESS;
	}
	
	public String waitingGot() throws Exception {
		queryType = 3;
		setWaitingGotPaging();
		return SUCCESS;
	}
	
	public String doneOrder() throws Exception {
		queryType = 4;
		setDoneOrderPaging();
		return SUCCESS;
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}
}
