/**
 * 
 */
package com.smartchemical.usercenter.commonuser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.account.RechargeRequestDao;
import com.smartchemical.common.constant.PathConstant;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.CopyFileUtil;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.common.util.ScUtil;
import com.smartchemical.entities.frame.account.RechargeRequest;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class RechargeRequestManagementAction implements Action {
	
	/**
	 * 基础信息
	 * */
	private int queryType;
	
	private static final int PAGE_SIZE = 5;
	
	private static final String ACTION_NAME = "RechargeRequestManagement";
	
	private int currentPage = 1;
	
	private Paginator paginator;
	
	private int lastMonth = 0;

	/**
	 * 扩展信息
	 * */
	private List<RechargeRequest> requests;
	
	private int requestTypeFilter;
	
	private int requestStatusFilter;
	
	private int rechargetype;
	
	private float amount;
	
	private int requestId;
	
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
	
	public List<RechargeRequest> getRequests() {
		return requests;
	}
	
	public void setRequests(List<RechargeRequest> requests) {
		this.requests = requests;
	}
	
	public int getQueryType() {
		return queryType;
	}
	
	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}
	
	public int getRequestTypeFilter() {
		return requestTypeFilter;
	}

	public void setRequestTypeFilter(int requestTypeFilter) {
		this.requestTypeFilter = requestTypeFilter;
	}

	public int getRequestStatusFilter() {
		return requestStatusFilter;
	}

	public void setRequestStatusFilter(int requestStatusFilter) {
		this.requestStatusFilter = requestStatusFilter;
	}

	public int getRechargetype() {
		return rechargetype;
	}

	public void setRechargetype(int rechargetype) {
		this.rechargetype = rechargetype;
	}

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

	private void setPaginator(int totalRecordCount){
		paginator = new Paginator(totalRecordCount, currentPage, PAGE_SIZE, ACTION_NAME);
		currentPage = paginator.getCurrentPage();
	}
	
	private void setAllRequestPaging() throws Exception {
		queryType = 8;
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> requestTypeFilterItem = new LinkedList<String>();
		requestTypeFilterItem.add(requestTypeFilter + "");
		filtersMap.put("requestType", requestTypeFilterItem);
		List<String> requestStatusFilterItem = new LinkedList<String>();
		requestStatusFilterItem.add(requestStatusFilter + "");
		filtersMap.put("requestStatus", requestStatusFilterItem);
		
		RechargeRequestDao rrDao = DaoFactory.getRechargeRequestDao();
		int totalCount = rrDao.getRechargeRequestCountByUser(user.getUserId(), ScUtil.makeSinceDate(lastMonth), filtersMap);
		setPaginator(totalCount);
		requests = rrDao.getRechargeRequestByUser(user.getUserId(), ScUtil.makeSinceDate(lastMonth), filtersMap, PAGE_SIZE, paginator.getCurrentPage());
		for (RechargeRequest request : requests){
			request.getAcBillPaths().addAll(CopyFileUtil.getAcBillPic(request.getRequestId()));
		}
	}
	
	public String newRequest() throws Exception {
		queryType = 8;
		return "newrequest";
	}
	
	public String newRequestSubmit() throws Exception {
		if (rechargetype == 1){
			ActionContext ctx = ActionContext.getContext();
			ScUser user = (ScUser) ctx.getSession().get("user");
			RechargeRequestDao rDao = DaoFactory.getRechargeRequestDao();
			//将状态设置为等待转账
			RechargeRequest request = rDao.insertRechargeRequest(user, 1, 2, amount, 0);
			requestId = request.getRequestId();
			return "toacceptancebillrecharge";
		}
		else if (rechargetype == 2){
			ActionContext ctx = ActionContext.getContext();
			ScUser user = (ScUser) ctx.getSession().get("user");
			RechargeRequestDao rDao = DaoFactory.getRechargeRequestDao();
			//将状态设置为等待转账
			rDao.insertRechargeRequest(user, 2, 1, amount, 0);
			setAllRequestPaging();
			return "allrequests";
		}
		return ERROR;
	}
	
	public String removeRequest() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		RechargeRequestDao rDao = DaoFactory.getRechargeRequestDao();
		RechargeRequest currentRequest = rDao.getRechargeRequestById(requestId);
		if (currentRequest == null || currentRequest.getUser().getUserId() != user.getUserId()){
			return ERROR;
		}
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.RECHARGE_ACBILL_SAVE_FOLDER + "recharge-" +requestId);
		CopyFileUtil.removeDir(rootSave);
		rDao.removeRechargeRequest(requestId);
		setAllRequestPaging();
		return "allrequests";
	}
	
	public String allRequests() throws Exception {
		setAllRequestPaging();
		return "allrequests";
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}

}
