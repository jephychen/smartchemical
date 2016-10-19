/**
 * 
 */
package com.smartchemical.usercenter.commonuser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.account.WithdrawRequestDao;
import com.smartchemical.api.frame.dao.user.BankAccountDao;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.lock.UserLock;
import com.smartchemical.common.util.DecimalFormatUtil;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.common.util.ScUtil;
import com.smartchemical.entities.frame.account.WithdrawRequest;
import com.smartchemical.entities.frame.user.BankAccount;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class DepositRequestManagementAction implements Action {

	/**
	 * 基础信息
	 * */
	private int queryType = 20;
	
	private static final int PAGE_SIZE = 5;
	
	private static final String ACTION_NAME = "AdminCompanyManagement";
	
	private int currentPage = 1;
	
	private Paginator paginator;
	
	private int lastMonth = 0;
	
	/**
	 * 扩展信息
	 * */
	private List<WithdrawRequest> requests;
	
	private List<BankAccount> accounts;
	
	private List<BankAccount> userAccounts;
	
	private List<BankAccount> userOtherAccounts;
	
	private int bankAccountId = 0;
	
	private int requestId;
	
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

	public int getLastMonth() {
		return lastMonth;
	}

	public void setLastMonth(int lastMonth) {
		this.lastMonth = lastMonth;
	}

	public List<WithdrawRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<WithdrawRequest> requests) {
		this.requests = requests;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
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

	public int getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	private void setPaginator(int totalRecordCount){
		paginator = new Paginator(totalRecordCount, currentPage, PAGE_SIZE, ACTION_NAME);
		currentPage = paginator.getCurrentPage();
	}
	
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	private void setAllRequestPaging() throws Exception {
		queryType = 7;
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		int userId = user.getUserId();
		Map<String, List<String>> filtersMap = null;
		if (bankAccountId != 0){
			filtersMap = new HashMap<String, List<String>>();
			List<String> accountIdFilterItem = new LinkedList<String>();
			accountIdFilterItem.add(bankAccountId + "");
			filtersMap.put("bankAccountId", accountIdFilterItem);
		}
		WithdrawRequestDao rDao = DaoFactory.getWithdrawRequestDao();
		int totalCount = rDao.getWithdrawRequestCountByUser(userId, ScUtil.makeSinceDate(lastMonth), filtersMap);
		setPaginator(totalCount);
		requests = rDao.getWithdrawRequestByUser(userId, ScUtil.makeSinceDate(lastMonth), filtersMap, PAGE_SIZE, paginator.getCurrentPage());
		BankAccountDao baDao = DaoFactory.getBankAccountDao();
		accounts = baDao.getBankAcountByUser(userId);
		userAccounts = baDao.getMainBankAcountByUser(userId);
		userOtherAccounts = baDao.getOtherBankAcountByUser(userId);
	}
	
	public String removeRequest() throws Exception{
		WithdrawRequestDao rDao = DaoFactory.getWithdrawRequestDao();
		WithdrawRequest request = rDao.getWithdrawRequestById(requestId);
		if (request == null){
			return ERROR;
		}
		if (request.getRequestStatus() == 2){
			return ERROR;
		}
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		if (request.getUser().getUserId() != user.getUserId()){
			return ERROR;
		}
		//如果成功则还原用户余额
		if (rDao.removeWithdrawRequest(requestId)){
			ScUserDao userDao = DaoFactory.getScUserDao();
			ScUser currentUser = userDao.getUserById(user.getUserId());
			synchronized (UserLock.class) {
				currentUser.setDeposit(currentUser.getDeposit() + request.getAmount());
				currentUser.setDepositStr(DecimalFormatUtil.formatFloat2String(currentUser.getDeposit()));
				userDao.editUser(currentUser);
				ctx.getSession().put("user", currentUser);
			}
		}
		setAllRequestPaging();
		return SUCCESS;
	}
	
	public String allRequest() throws Exception {
		setAllRequestPaging();
		return SUCCESS;
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	
}
