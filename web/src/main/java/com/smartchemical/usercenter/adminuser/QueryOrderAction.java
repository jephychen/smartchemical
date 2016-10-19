/**
 * 
 */
package com.smartchemical.usercenter.adminuser;

import java.sql.Timestamp;
import java.util.List;

import com.opensymphony.xwork2.Action;
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
import com.smartchemical.usercenter.QueryOrder;

/**
 * @author Jephy
 * 
 */
public class QueryOrderAction extends QueryOrder implements Action {
	/**
	 * 0 - 所有订单，1 - 未付款的订单， 2 - 待收货的订单， 3 - 待提货的订单， 4 - 已完成的订单
	 * */
	private int queryType;

	private int poId;
	
	private int orderItemId;

	private List<PurchaseOrder> orders;
	
	private static final int PAGE_SIZE = 5;
	
	private int currentPage = 1;
	
	private Paginator paginator;
	
	private int lastMonth = 0;

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public int getPoId() {
		return poId;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public List<PurchaseOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<PurchaseOrder> orders) {
		this.orders = orders;
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
	
	public int getLastMonth() {
		return lastMonth;
	}

	public void setLastMonth(int lastMonth) {
		this.lastMonth = lastMonth;
	}
	
	private void setPaginator(int totalRecordCount){
		paginator = new Paginator(totalRecordCount, currentPage, PAGE_SIZE);
		currentPage = paginator.getCurrentPage();
	}
	
	private void setAllOrderPaging() throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		int totalRecordCount = orderDao.getAllPurchaseOrderCount(ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalRecordCount);
		orders = orderDao.getAllPurchaseOrderPaging(PAGE_SIZE, paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}

	private void setUnpaidPaging() throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		int totalCount = orderDao.getAllPOCountByStatus(OrderStatus.WAITING_TO_BE_PAID.getIndex(), ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalCount);
		orders = orderDao.getAllPurchaseOrderPagingByStatus(OrderStatus.WAITING_TO_BE_PAID.getIndex(), PAGE_SIZE, paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	private void setWaitingReceivedPaging() throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		int totalCount = orderDao.getAllPOsCountByDeliverType(OrderStatus.PAYED.getIndex(), "(" + DeliverType.VERDOR.getIndex() + "," + DeliverType.ZHIXUAN.getIndex() + ")", ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalCount);
		orders = orderDao.getAllPOsWithItemsPagingByDeliverType(OrderStatus.PAYED.getIndex(), "(" + DeliverType.VERDOR.getIndex() + "," + DeliverType.ZHIXUAN.getIndex() + ")", 
				PAGE_SIZE, paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	private void setWaitingGotPaging() throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		int totalCount = orderDao.getAllPOsCountByDeliverType(OrderStatus.PAYED.getIndex(), "(" + DeliverType.USER.getIndex() + ")", ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalCount);
		orders = orderDao.getAllPOsWithItemsPagingByDeliverType(OrderStatus.PAYED.getIndex(), "(" + DeliverType.USER.getIndex() + ")" ,
				PAGE_SIZE, paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	private void setDoneOrderPaging() throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		int totalCount = orderDao.getAllPOCountByStatus(OrderStatus.RECEIVED.getIndex(), ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalCount);
		orders = orderDao.getAllPurchaseOrderPagingByStatus(OrderStatus.RECEIVED.getIndex(), PAGE_SIZE, paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}

	public String allOrders() throws Exception {
		queryType = 0;
		setAllOrderPaging();
		return SUCCESS;
	}
	
	public String unpaid() throws Exception {
		queryType = 1;
		setUnpaidPaging();
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

	public String setToPaid() throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder currentOrder = orderDao.getPurchaseOrderById(poId);
		currentOrder.setStatus(OrderStatus.PAYED.getIndex());
		orderDao.editPurchaseOrder(currentOrder);
		// 如果是上门取货，设置子订单状态为等待取货
		OrderItemDao itemDao = DaoFactory.getOrderItemDao();
		List<OrderItem> items = itemDao.getOrderItemByOrderId(currentOrder
				.getPurchaseOrderId());
		if (items == null || items.isEmpty()) {
			return ERROR;
		}
		if (currentOrder.getDeliverType() == DeliverType.USER.getIndex()) {
			for (OrderItem item : items) {
				item.setOrderItemStatus(OrderItemStatus.WAITING_TO_BE_GOT
						.getIndex());
				itemDao.editOrderItem(item);
			}
		}
		if (queryType == 0){
			setAllOrderPaging();
		}
		else if (queryType == 1){
			setUnpaidPaging();
		}
		else{
			orders = orderDao.getAllPurchaseOrderPaging(PAGE_SIZE, 1, ScUtil.makeSinceDate(lastMonth));
		}
		
		return SUCCESS;
	}
	
	public String confirmReceive() throws Exception {
		OrderItemDao itemDao = DaoFactory.getOrderItemDao();
		OrderItem currentItem = itemDao.getOrderItemByItemId(orderItemId);
		if (currentItem == null){
			return ERROR;
		}
		currentItem.setOrderItemStatus(OrderItemStatus.RECEIVED.getIndex());
		currentItem.setReceiveTime(new Timestamp(System.currentTimeMillis()));
		itemDao.editOrderItem(currentItem);
		modifyProductSoldQuantityAndAmount(currentItem);
		List<OrderItem> items = itemDao.getOrderItemByOrderId(currentItem.getOrder().getPurchaseOrderId());
		if (items == null || items.isEmpty()){
			return ERROR;
		}
		boolean allReceived = true;
		for (OrderItem orderItem : items){
			if (orderItem.getOrderItemStatus() != OrderItemStatus.RECEIVED.getIndex()){
				allReceived = false;
				break;
			}
		}
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		if (allReceived == true){
			PurchaseOrder currentOrder = currentItem.getOrder();
			currentOrder.setStatus(OrderStatus.RECEIVED.getIndex());
			currentOrder.setReceiveTime(new Timestamp(System.currentTimeMillis()));
			orderDao.editPurchaseOrder(currentOrder);
		}
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
	
	public String confirmGet() throws Exception {
		OrderItemDao itemDao = DaoFactory.getOrderItemDao();
		OrderItem currentItem = itemDao.getOrderItemByItemId(orderItemId);
		if (currentItem == null){
			return ERROR;
		}
		currentItem.setOrderItemStatus(OrderItemStatus.RECEIVED.getIndex());
		currentItem.setGotTime(new Timestamp(System.currentTimeMillis()));
		itemDao.editOrderItem(currentItem);
		modifyProductSoldQuantityAndAmount(currentItem);
		List<OrderItem> items = itemDao.getOrderItemByOrderId(currentItem.getOrder().getPurchaseOrderId());
		if (items == null || items.isEmpty()){
			return ERROR;
		}
		boolean allReceived = true;
		for (OrderItem orderItem : items){
			if (orderItem.getOrderItemStatus() != OrderItemStatus.RECEIVED.getIndex()){
				allReceived = false;
				break;
			}
		}
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		if (allReceived == true){
			PurchaseOrder currentOrder = currentItem.getOrder();
			currentOrder.setStatus(OrderStatus.RECEIVED.getIndex());
			currentOrder.setReceiveTime(new Timestamp(System.currentTimeMillis()));
			orderDao.editPurchaseOrder(currentOrder);
		}
		if (queryType == 0){
			setAllOrderPaging();
		}
		else if (queryType == 3){
			setWaitingGotPaging();
		}
		else{
			orders = orderDao.getAllPurchaseOrderPaging(PAGE_SIZE, 1, ScUtil.makeSinceDate(lastMonth));
		}
		return SUCCESS;
	}

	public String confirmDeliver() throws Exception {
		OrderItemDao itemDao = DaoFactory.getOrderItemDao();
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		OrderItem currentItem = itemDao.getOrderItemByItemId(orderItemId);
		PurchaseOrder currentOrder = orderDao.getPurchaseOrderById(currentItem.getOrder().getPurchaseOrderId());
		//如果订单状态为待付款，则不进行动作。
		if (currentOrder.getStatus() == OrderStatus.WAITING_TO_BE_PAID.getIndex()){
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
	
	public String checkBillValid() throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder currentOrder = orderDao.getPurchaseOrderById(poId);
		currentOrder.setStatus(OrderStatus.WAITING_TO_BE_PAID.getIndex());
		currentOrder.setSubStatus(OrderStatus.SUB_RECEIVING_ACCEPTANCEBILL.getIndex());
		orderDao.editPurchaseOrder(currentOrder);
		if (queryType == 0){
			setAllOrderPaging();
		}
		else if (queryType == 1){
			setUnpaidPaging();
		}
		else{
			orders = orderDao.getAllPurchaseOrderPaging(PAGE_SIZE, 1, ScUtil.makeSinceDate(lastMonth));
		}
		return SUCCESS;
	}
	
	public String checkBillInvalid() throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder currentOrder = orderDao.getPurchaseOrderById(poId);
		currentOrder.setStatus(OrderStatus.WAITING_TO_BE_PAID.getIndex());
		currentOrder.setSubStatus(OrderStatus.SUB_UPLOADING_ACCEPTANCEBILL.getIndex());
		orderDao.editPurchaseOrder(currentOrder);
		if (queryType == 0){
			setAllOrderPaging();
		}
		else if (queryType == 1){
			setUnpaidPaging();
		}
		else{
			orders = orderDao.getAllPurchaseOrderPaging(PAGE_SIZE, 1, ScUtil.makeSinceDate(lastMonth));
		}
		return SUCCESS;
	}
	
	public String receiveBill() throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder currentOrder = orderDao.getPurchaseOrderById(poId);
		currentOrder.setStatus(OrderStatus.PAYED.getIndex());
		currentOrder.setSubStatus(OrderStatus.SUB_EMPTY.getIndex());
		orderDao.editPurchaseOrder(currentOrder);
		
		// 如果是上门取货，设置子订单状态为等待取货
		OrderItemDao itemDao = DaoFactory.getOrderItemDao();
		List<OrderItem> items = itemDao.getOrderItemByOrderId(currentOrder.getPurchaseOrderId());
		if (items == null || items.isEmpty()) {
			return ERROR;
		}
		if (currentOrder.getDeliverType() == DeliverType.USER.getIndex()) {
			for (OrderItem item : items) {
				item.setOrderItemStatus(OrderItemStatus.WAITING_TO_BE_GOT
						.getIndex());
				itemDao.editOrderItem(item);
			}
		}
		if (queryType == 0){
			setAllOrderPaging();
		}
		else if (queryType == 1){
			setUnpaidPaging();
		}
		else{
			orders = orderDao.getAllPurchaseOrderPaging(PAGE_SIZE, 1, ScUtil.makeSinceDate(lastMonth));
		}
		return SUCCESS;
	}
	
	public String cancelOrder() throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder currentOrder = orderDao.getPurchaseOrderById(poId);
		OrderItemDao itemDao = DaoFactory.getOrderItemDao();
		if (!itemDao.removeOrderItemsByOrderId(currentOrder.getPurchaseOrderId())){
			//TODO 记录日志
			//TODO 恢复库存量
		}
		if (!orderDao.removePurchaseOrder(currentOrder.getPurchaseOrderId())){
			//TODO 记录日志
			//TODO 恢复库存量
		}
		if (queryType == 0){
			setAllOrderPaging();
		}
		else if (queryType == 1){
			setUnpaidPaging();
		}
		else if (queryType == 2){
			setWaitingReceivedPaging();
		}
		else if (queryType == 3){
			setWaitingGotPaging();
		}
		else if (queryType == 4){
			setDoneOrderPaging();
		}
		return SUCCESS;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
