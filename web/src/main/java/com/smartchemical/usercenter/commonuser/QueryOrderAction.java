/**
 * 
 */
package com.smartchemical.usercenter.commonuser;

import java.sql.Timestamp;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.order.DeliverType;
import com.smartchemical.api.frame.dao.order.OrderItemDao;
import com.smartchemical.api.frame.dao.order.OrderItemStatus;
import com.smartchemical.api.frame.dao.order.OrderStatus;
import com.smartchemical.api.frame.dao.order.PurchaseOrderDao;
import com.smartchemical.api.frame.dao.user.BankAccountDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.common.util.ScUtil;
import com.smartchemical.entities.frame.order.OrderItem;
import com.smartchemical.entities.frame.order.PurchaseOrder;
import com.smartchemical.entities.frame.user.BankAccount;
import com.smartchemical.entities.frame.user.ScUser;
import com.smartchemical.usercenter.QueryOrder;

/**
 * @author Jephy
 *
 */
public class QueryOrderAction extends QueryOrder implements Action {
	
	/**
	 * 1 - 未付款的订单， 2 - 待收货的订单， 3 - 待提货的订单， 4 - 已完成的订单
	 * */
	private int queryType;
	
	private List<PurchaseOrder> orders;
	
	private int orderItemId;
	
	private static final int PAGE_SIZE = 5;
	
	private int currentPage = 1;
	
	private Paginator paginator;
	
	private int lastMonth = 0;
	
	//以下是我的智选页面参数
	private int unpaidOrdersCount = 0;
	
	private int waitingReceiveOrdersCount = 0;
	
	private int waitingGetOrdersCount = 0;
	
	private int doneOrdersCount = 0;
	
	private int poId;
	
	private List<BankAccount> userAccounts;
	
	private List<BankAccount> userOtherAccounts;

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public List<PurchaseOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<PurchaseOrder> orders) {
		this.orders = orders;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
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
	
	public int getUnpaidOrdersCount() {
		return unpaidOrdersCount;
	}

	public void setUnpaidOrdersCount(int unpaidOrdersCount) {
		this.unpaidOrdersCount = unpaidOrdersCount;
	}

	public int getWaitingReceiveOrdersCount() {
		return waitingReceiveOrdersCount;
	}

	public void setWaitingReceiveOrdersCount(int waitingReceiveOrdersCount) {
		this.waitingReceiveOrdersCount = waitingReceiveOrdersCount;
	}

	public int getWaitingGetOrdersCount() {
		return waitingGetOrdersCount;
	}

	public void setWaitingGetOrdersCount(int waitingGetOrdersCount) {
		this.waitingGetOrdersCount = waitingGetOrdersCount;
	}

	public int getDoneOrdersCount() {
		return doneOrdersCount;
	}

	public void setDoneOrdersCount(int doneOrdersCount) {
		this.doneOrdersCount = doneOrdersCount;
	}

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public List<BankAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(List<BankAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public List<BankAccount> getUserOtherAccounts() {
		return userOtherAccounts;
	}

	public void setUserOtherAccounts(List<BankAccount> userOtherAccounts) {
		this.userOtherAccounts = userOtherAccounts;
	}

	private void setPaginator(int totalRecordCount){
		paginator = new Paginator(totalRecordCount, currentPage, PAGE_SIZE);
		currentPage = paginator.getCurrentPage();
	}
	
	private void setMyzhixuanAllOrdersPaging(int userId) throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		int totalCount = orderDao.getPOsCountByUser(userId, ScUtil.makeSinceDate(0));
		setPaginator(totalCount);
		orders = orderDao.getPOsByUserWithItems(userId, PAGE_SIZE, paginator.getCurrentPage(), ScUtil.makeSinceDate(0));
		BankAccountDao baDao = DaoFactory.getBankAccountDao();
		userAccounts = baDao.getMainBankAcountByUser(userId);
		userOtherAccounts = baDao.getOtherBankAcountByUser(userId);
	}
	
	private void setAllOrdersPaging(int userId) throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		int totalCount = orderDao.getPOsCountByUser(userId, ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalCount);
		orders = orderDao.getPOsByUserWithItems(userId, PAGE_SIZE, paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	private void setUnpaidPaging(int userId) throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		int totalCount = orderDao.getPOsCountByUserAndStatus(userId, OrderStatus.WAITING_TO_BE_PAID.getIndex(), ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalCount);
		orders = orderDao.getPurchaseOrdersByUserWithItems(userId, OrderStatus.WAITING_TO_BE_PAID.getIndex(), PAGE_SIZE, paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	private void setWaitingReceivedPaging(int userId) throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		int totalCount = orderDao.getPOsCountByDeliverType(userId, OrderStatus.PAYED.getIndex(), "(" + DeliverType.VERDOR.getIndex() + "," + DeliverType.ZHIXUAN.getIndex() + ")", ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalCount);
		orders = orderDao.getPOsByUserWithItemsByDeliverType(userId, OrderStatus.PAYED.getIndex(), "(" + DeliverType.VERDOR.getIndex() + "," + DeliverType.ZHIXUAN.getIndex() + ")",
				PAGE_SIZE, paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	private void setWaitingGotPaging(int userId) throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		int totalCount = orderDao.getPOsCountByDeliverType(userId, OrderStatus.PAYED.getIndex(), "(" + DeliverType.USER.getIndex() + ")", ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalCount);
		orders = orderDao.getPOsByUserWithItemsByDeliverType(userId, OrderStatus.PAYED.getIndex(), "(" + DeliverType.USER.getIndex() + ")" ,
				PAGE_SIZE, paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	private void setDoneOrderPaging(int userId) throws Exception {
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		int totalCount = orderDao.getPOsCountByUserAndStatus(userId, OrderStatus.RECEIVED.getIndex(), ScUtil.makeSinceDate(lastMonth));
		setPaginator(totalCount);
		orders = orderDao.getPurchaseOrdersByUserWithItems(userId, OrderStatus.RECEIVED.getIndex(), PAGE_SIZE, paginator.getCurrentPage(), ScUtil.makeSinceDate(lastMonth));
	}
	
	public String allOrders() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		queryType = 0;
		setAllOrdersPaging(user.getUserId());
		return SUCCESS;
	}

	public String unpaid() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		queryType = 1;
		setUnpaidPaging(user.getUserId());
		return SUCCESS;
	}
	
	public String waitingReceived() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		queryType = 2;
		setWaitingReceivedPaging(user.getUserId());
		return SUCCESS;
	}
	
	public String waitingGot() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		queryType = 3;
		setWaitingGotPaging(user.getUserId());
		return SUCCESS;
	}
	
	public String doneOrder() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		queryType = 4;
		setDoneOrderPaging(user.getUserId());
		return SUCCESS;
	}
	
	public String confirmReceive() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		OrderItemDao itemDao = DaoFactory.getOrderItemDao();
		OrderItem currentItem = itemDao.getOrderItemByItemId(orderItemId);
		if (currentItem == null){
			return ERROR;
		}
		if (currentItem.getOrder().getUser().getUserId() != user.getUserId()){
			ctx.getSession().clear();
			return LOGIN;
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
			setAllOrdersPaging(user.getUserId());
		}
		else if (queryType == 1){
			setUnpaidPaging(user.getUserId());
		}
		else if (queryType == 2){
			setWaitingReceivedPaging(user.getUserId());
		}
		else if (queryType == 3){
			setWaitingGotPaging(user.getUserId());
		}
		else if (queryType == 4){
			setDoneOrderPaging(user.getUserId());
		}
		return SUCCESS;
	}
	
	public String confirmGet() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		OrderItemDao itemDao = DaoFactory.getOrderItemDao();
		OrderItem currentItem = itemDao.getOrderItemByItemId(orderItemId);
		if (currentItem.getOrder().getUser().getUserId() != user.getUserId()){
			ctx.getSession().clear();
			return LOGIN;
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
			setAllOrdersPaging(user.getUserId());
		}
		else if (queryType == 1){
			setUnpaidPaging(user.getUserId());
		}
		else if (queryType == 2){
			setWaitingReceivedPaging(user.getUserId());
		}
		else if (queryType == 3){
			setWaitingGotPaging(user.getUserId());
		}
		else if (queryType == 4){
			setDoneOrderPaging(user.getUserId());
		}
		return SUCCESS;
	}
	
	public String cancelOrder() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder currentOrder = orderDao.getPurchaseOrderById(poId);
		if (currentOrder == null || currentOrder.getUser().getUserId() != user.getUserId()){
			return ERROR;
		}
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
			setAllOrdersPaging(user.getUserId());
		}
		else if (queryType == 1){
			setUnpaidPaging(user.getUserId());
		}
		else if (queryType == 2){
			setWaitingReceivedPaging(user.getUserId());
		}
		else if (queryType == 3){
			setWaitingGotPaging(user.getUserId());
		}
		else if (queryType == 4){
			setDoneOrderPaging(user.getUserId());
		}
		return SUCCESS;
	}
	
	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		int userId = user.getUserId();
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		unpaidOrdersCount = orderDao.getPOsCountByUserAndStatus(userId, OrderStatus.WAITING_TO_BE_PAID.getIndex(), ScUtil.makeSinceDate(0));
		waitingReceiveOrdersCount = orderDao.getPOsCountByDeliverType(userId, OrderStatus.PAYED.getIndex(), "(" + DeliverType.VERDOR.getIndex() + "," + DeliverType.ZHIXUAN.getIndex() + ")", ScUtil.makeSinceDate(0));
		waitingGetOrdersCount = orderDao.getPOsCountByDeliverType(userId, OrderStatus.PAYED.getIndex(), "(" + DeliverType.USER.getIndex() + ")", ScUtil.makeSinceDate(0));
		doneOrdersCount = orderDao.getPOsCountByUserAndStatus(userId, OrderStatus.RECEIVED.getIndex(), ScUtil.makeSinceDate(0));
		setMyzhixuanAllOrdersPaging(userId);
		return SUCCESS;
	}
	
}
