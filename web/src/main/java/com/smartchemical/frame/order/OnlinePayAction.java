/**
 * 
 */
package com.smartchemical.frame.order;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.order.OrderStatus;
import com.smartchemical.api.frame.dao.order.PaymentType;
import com.smartchemical.api.frame.dao.order.PurchaseOrderDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.order.PurchaseOrder;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class OnlinePayAction implements Action {

	private int poId;
	
	private String poNo;
	
	private float totalPrice;
	
	private String bank;

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String onlinePaySubmit() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder currentOrder = orderDao.getPurchaseOrderByNo(poNo);
		if (user.getUserId() != currentOrder.getUser().getUserId()){
			return ERROR;
		}
		return "topay";
	}

	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		if (user == null){
			return LOGIN;
		}
		PurchaseOrderDao purchaseOrderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder order = purchaseOrderDao.getPurchaseOrderById(poId);
		if (order == null || order.getStatus() != OrderStatus.WAITING_TO_BE_PAID.getIndex() || order.getPaymentType() != PaymentType.ONLINE.getIndex()){
			return ERROR;
		}
		poNo = order.getPurchaseOrderNo();
		totalPrice = order.getTotalPrice();
		return SUCCESS;
	}

}
