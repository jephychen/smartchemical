/**
 * 
 */
package com.smartchemical.frame.order;

import java.sql.Timestamp;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.dao.order.DeliverType;
import com.smartchemical.api.frame.dao.order.OrderItemDao;
import com.smartchemical.api.frame.dao.order.OrderItemStatus;
import com.smartchemical.api.frame.dao.order.OrderStatus;
import com.smartchemical.api.frame.dao.order.PurchaseOrderDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.order.OrderItem;
import com.smartchemical.entities.frame.order.PurchaseOrder;
import com.smartchemical.thirdparty.payapi.mobaopay.Mobo360SignUtil;

/**
 * @author Jephy
 * 
 */
public class OnlinePayCallback implements Action {

	private String apiName;

	private String notifyTime;

	private float tradeAmt;

	private String merchNo;

	private String merchParam;

	private String orderNo;

	private String tradeDate;

	private String accNo;

	private String accDate;

	private int orderStatus;

	private String signMsg;
	
	private int notifyType;

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	public float getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(float tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public String getMerchParam() {
		return merchParam;
	}

	public void setMerchParam(String merchParam) {
		this.merchParam = merchParam;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccDate() {
		return accDate;
	}

	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public int getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(int notifyType) {
		this.notifyType = notifyType;
	}

	public String execute() throws Exception {
		System.out.println("INFO - notifyType is " + notifyType);
		Mobo360SignUtil.init();
		String srcMsg = String
				.format("apiName=%s&notifyTime=%s&tradeAmt=%s&merchNo=%s&merchParam=%s&orderNo=%s&tradeDate=%s&accNo=%s&accDate=%s&orderStatus=%s",
						apiName, notifyTime, tradeAmt, merchNo, merchParam,
						orderNo, tradeDate, accNo, accDate, orderStatus);
		System.out.println("INFO - Callback params is " + srcMsg);
		boolean verifyRst = Mobo360SignUtil.verifyData(signMsg, srcMsg);
		if (verifyRst){
			if (orderStatus != 1){
				System.out.println("ERROR - orderStatus is " + orderStatus);
				return "payerror";
			}
			PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao(); 
			PurchaseOrder currentOrder = orderDao.getPurchaseOrderByNo(orderNo);
			if (currentOrder.getStatus() == OrderStatus.PAYED.getIndex() && notifyType == 0){
				return "successpage";
			}
			else if (currentOrder.getStatus() == OrderStatus.PAYED.getIndex() && notifyType == 1){
				return SUCCESS;
			}
			if (currentOrder.getTotalPrice() != tradeAmt){
				System.out.println("ERROR - Order amount error. Order amount in zhixuan:" 
						+ currentOrder.getTotalPrice() + ", amount paid:" + tradeAmt);
				return "payerror";
			}
			currentOrder.setStatus(OrderStatus.PAYED.getIndex());
			currentOrder.setSubStatus(OrderStatus.SUB_EMPTY.getIndex());
			currentOrder.setPaidTime(new Timestamp(System.currentTimeMillis()));
			orderDao.editPurchaseOrder(currentOrder);
			//如果是上门取货，设置子订单状态为等待取货
			OrderItemDao itemDao = DaoFactory.getOrderItemDao();
			List<OrderItem> items = itemDao.getOrderItemByOrderId(currentOrder.getPurchaseOrderId());
			if (items == null || items.isEmpty()){
				return "payerror";
			}
			if (currentOrder.getDeliverType() == DeliverType.USER.getIndex()){
				for (OrderItem item : items){
					item.setOrderItemStatus(OrderItemStatus.WAITING_TO_BE_GOT.getIndex());
					itemDao.editOrderItem(item);
				}
			}
			System.out.println("INFO - Order:" + orderNo + " paid successfully.");
			if (notifyType == 0){
				return "successpage";
			}
			else if (notifyType == 1){
				return SUCCESS;
			}
			else {
				return "successpage";
			}
		}
		else {
			System.out.println("ERROR - Order:" + orderNo + " verify error.");
			return "payerror";
		}
	}

}
