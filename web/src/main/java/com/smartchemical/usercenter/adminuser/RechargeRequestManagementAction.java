/**
 * 
 */
package com.smartchemical.usercenter.adminuser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.dao.account.RechargeRequestDao;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.common.constant.PathConstant;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.lock.UserLock;
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
	
	private String userNameFilter = "";
	
	private int rechargetype;
	
	private float amount;
	
	private float actualamount;
	
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

	public String getUserNameFilter() {
		return userNameFilter;
	}

	public void setUserNameFilter(String userNameFilter) {
		this.userNameFilter = userNameFilter;
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

	public float getActualamount() {
		return actualamount;
	}

	public void setActualamount(float actualamount) {
		this.actualamount = actualamount;
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
		queryType = 6;
		
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> requestTypeFilterItem = new LinkedList<String>();
		requestTypeFilterItem.add(requestTypeFilter + "");
		filtersMap.put("requestType", requestTypeFilterItem);
		List<String> requestStatusFilterItem = new LinkedList<String>();
		requestStatusFilterItem.add(requestStatusFilter + "");
		filtersMap.put("requestStatus", requestStatusFilterItem);
		if (userNameFilter != null && !userNameFilter.isEmpty()){
			List<String> usernameFilterItem = new LinkedList<String>();
			usernameFilterItem.add(userNameFilter + "");
			filtersMap.put("userName", usernameFilterItem);
		}
		
		RechargeRequestDao rrDao = DaoFactory.getRechargeRequestDao();
		int totalCount = rrDao.getRechargeRequestCount(ScUtil.makeSinceDate(lastMonth), filtersMap);
		setPaginator(totalCount);
		requests = rrDao.getRechargeRequest(ScUtil.makeSinceDate(lastMonth), filtersMap, PAGE_SIZE, paginator.getCurrentPage());
		for (RechargeRequest request : requests){
			request.getAcBillPaths().addAll(CopyFileUtil.getAcBillPic(request.getRequestId()));
		}
	}
	
	public String removeRequest() throws Exception{
		RechargeRequestDao rDao = DaoFactory.getRechargeRequestDao();
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.RECHARGE_ACBILL_SAVE_FOLDER + "recharge-" +requestId);
		CopyFileUtil.removeDir(rootSave);
		rDao.removeRechargeRequest(requestId);
		setAllRequestPaging();
		return "allrequests";
	}
	
	public String receiveRecharge() throws Exception{
		RechargeRequestDao rDao = DaoFactory.getRechargeRequestDao();
		RechargeRequest currentRequest = rDao.getRechargeRequestById(requestId);
		if (currentRequest == null || currentRequest.getRequestStatus() != 1){
			return ERROR;
		}
		ScUserDao userDao = DaoFactory.getScUserDao();
		synchronized (UserLock.class) {
			ScUser requestUser = userDao.getUserById(currentRequest.getUser().getUserId());
			currentRequest.setRequestStatus(127);
			rDao.editRechargeRequest(currentRequest);
			requestUser.setDeposit(requestUser.getDeposit() + currentRequest.getAmount());
			userDao.editUser(requestUser);
		}
		setAllRequestPaging();
		return "allrequests";
	}
	
	public String approveAcBill() throws Exception{
		RechargeRequestDao rDao = DaoFactory.getRechargeRequestDao();
		RechargeRequest currentRequest = rDao.getRechargeRequestById(requestId);
		if (currentRequest == null || currentRequest.getRequestStatus() != 5){
			return ERROR;
		}
		currentRequest.setRequestStatus(3);
		rDao.editRechargeRequest(currentRequest);
		setAllRequestPaging();
		return "allrequests";
	}
	
	public String rejectAcBill() throws Exception{
		RechargeRequestDao rDao = DaoFactory.getRechargeRequestDao();
		RechargeRequest currentRequest = rDao.getRechargeRequestById(requestId);
		if (currentRequest == null || currentRequest.getRequestStatus() != 5){
			return ERROR;
		}
		currentRequest.setRequestStatus(2);
		rDao.editRechargeRequest(currentRequest);
		setAllRequestPaging();
		return "allrequests";
	}
	
	public String receiveAcBill() throws Exception{
		RechargeRequestDao rDao = DaoFactory.getRechargeRequestDao();
		RechargeRequest currentRequest = rDao.getRechargeRequestById(requestId);
		if (currentRequest == null || currentRequest.getRequestStatus() != 3){
			return ERROR;
		}
		currentRequest.setRequestStatus(4);
		rDao.editRechargeRequest(currentRequest);
		setAllRequestPaging();
		return "allrequests";
	}
	
	public String resendAcBill() throws Exception{
		RechargeRequestDao rDao = DaoFactory.getRechargeRequestDao();
		RechargeRequest currentRequest = rDao.getRechargeRequestById(requestId);
		if (currentRequest == null || currentRequest.getRequestStatus() != 3){
			return ERROR;
		}
		setAllRequestPaging();
		return "allrequests";
	}
	
	public String exchangeSuccess() throws Exception{
		RechargeRequestDao rDao = DaoFactory.getRechargeRequestDao();
		RechargeRequest currentRequest = rDao.getRechargeRequestById(requestId);
		if (currentRequest == null || currentRequest.getRequestStatus() != 4 || actualamount < 0){
			return ERROR;
		}
		ScUserDao userDao = DaoFactory.getScUserDao();
		synchronized (UserLock.class) {
			ScUser requestUser = userDao.getUserById(currentRequest.getUser().getUserId());
			currentRequest.setRequestStatus(127);
			currentRequest.setActualamount(actualamount);
			rDao.editRechargeRequest(currentRequest);
			requestUser.setDeposit(requestUser.getDeposit() + actualamount);
			userDao.editUser(requestUser);
		}
		setAllRequestPaging();
		return "allrequests";
	}
	
	public String exchangeFailed() throws Exception{
		RechargeRequestDao rDao = DaoFactory.getRechargeRequestDao();
		RechargeRequest currentRequest = rDao.getRechargeRequestById(requestId);
		if (currentRequest == null || currentRequest.getRequestStatus() != 4){
			return ERROR;
		}
		currentRequest.setRequestStatus(3);
		rDao.editRechargeRequest(currentRequest);
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
