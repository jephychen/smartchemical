/**
 * 
 */
package com.smartchemical.frame.order;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.order.OrderStatus;
import com.smartchemical.api.frame.dao.order.PaymentType;
import com.smartchemical.api.frame.dao.order.PurchaseOrderDao;
import com.smartchemical.common.constant.PathConstant;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.order.PurchaseOrder;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class AcceptanceBillPayAction implements Action {

	private int poId;
	
	private String poNo;
	
	private float totalPrice;

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
	
	public String submitBill() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder currentOrder = orderDao.getPurchaseOrderByNo(poNo);
		if (user.getUserId() != currentOrder.getUser().getUserId()){
			return ERROR;
		}
		currentOrder.setStatus(OrderStatus.WAITING_TO_BE_PAID.getIndex());
		currentOrder.setSubStatus(OrderStatus.SUB_CHECKING_ACCEPTANCEBILL.getIndex());
		orderDao.editPurchaseOrder(currentOrder);
		return "tosuccess";
	}

	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		PurchaseOrderDao purchaseOrderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder order = purchaseOrderDao.getPurchaseOrderById(poId);
		if (order == null || order.getStatus() != OrderStatus.WAITING_TO_BE_PAID.getIndex() || order.getPaymentType() != PaymentType.ACCEPTANCE_BILL.getIndex()){
			return ERROR;
		}
		if (order.getUser().getUserId() != user.getUserId() && user.getRole().getRoleId() != 1){
			return ERROR;
		}
		poNo = order.getPurchaseOrderNo();
		totalPrice = order.getTotalPrice();
		deleteAllAcBill();
		return SUCCESS;
	}
	
	private void deleteAllAcBill(){
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.ACBILL_SAVE_FOLDER + poNo);
		File rootDir = new File(rootSave);
		if (rootDir.isDirectory()){
			File[] files = rootDir.listFiles();
			if (files == null || files.length == 0){
				return;
			}
			for (File file : files){
				if (file.isFile()){
					file.delete();
				}
			}
		}
	}
	
}
