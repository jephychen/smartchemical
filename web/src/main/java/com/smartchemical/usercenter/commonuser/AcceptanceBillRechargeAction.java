/**
 * 
 */
package com.smartchemical.usercenter.commonuser;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.account.RechargeRequestDao;
import com.smartchemical.common.constant.PathConstant;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.CopyFileUtil;
import com.smartchemical.entities.frame.account.RechargeRequest;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class AcceptanceBillRechargeAction implements Action {
	
	private float amount;
	
	private int requestId;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	private boolean checkAuth() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		RechargeRequestDao rrDao = DaoFactory.getRechargeRequestDao();
		RechargeRequest request = rrDao.getRechargeRequestById(requestId);
		if (request == null || request.getUser().getUserId() != user.getUserId()){
			return false;
		}
		return true;
	}
	
	public String submitRequest() throws Exception {
		if (!checkAuth()){
			return ERROR;
		}
		RechargeRequestDao rrDao = DaoFactory.getRechargeRequestDao();
		RechargeRequest request = rrDao.getRechargeRequestById(requestId);
		request.setRequestStatus(5);
		rrDao.editRechargeRequest(request);
		return SUCCESS;
	}

	public String execute() throws Exception {
		if (!checkAuth()){
			return ERROR;
		}
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.RECHARGE_ACBILL_SAVE_FOLDER + "recharge-" +requestId);
		CopyFileUtil.removeDir(rootSave);
		return SUCCESS;
	}
	
}
