/**
 * 
 */
package com.smartchemical.frame.order;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.order.PurchaseOrderDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.order.OrderItem;
import com.smartchemical.entities.frame.order.PurchaseOrder;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class OrderDetailAction implements Action {
	
	private String poNo;
	
	private PurchaseOrder order;

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public PurchaseOrder getOrder() {
		return order;
	}

	public void setOrder(PurchaseOrder order) {
		this.order = order;
	}

	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		PurchaseOrderDao orderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder currentOrder = orderDao.getPurchaseOrderByNo(poNo);
		boolean merchantAuth = false;
		for (OrderItem orderItem : currentOrder.getOrderItems()){
			Company merchantCompany = orderItem.getProduct().getMerchantCompany();
			if (merchantCompany == null){
				continue;
			}
			int itemCompanyId = merchantCompany.getCompanyId();
			int userCompanyId = user.getCompany().getCompanyId();
			if (itemCompanyId == userCompanyId){
				merchantAuth = true;
				break;
			}
		}
		if (currentOrder.getUser().getUserId() != user.getUserId() && user.getRole().getRoleId() != 1 && !merchantAuth){
			ctx.getSession().clear();
			return LOGIN;
		}
		order = currentOrder;
		return SUCCESS;
	}
	
}
