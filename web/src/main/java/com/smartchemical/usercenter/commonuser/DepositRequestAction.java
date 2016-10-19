/**
 * 
 */
package com.smartchemical.usercenter.commonuser;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.account.WithdrawRequestDao;
import com.smartchemical.api.frame.dao.user.BankAccountDao;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.lock.UserLock;
import com.smartchemical.common.util.DecimalFormatUtil;
import com.smartchemical.entities.frame.account.WithdrawRequest;
import com.smartchemical.entities.frame.user.BankAccount;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class DepositRequestAction implements Action {
	
	private float amount;
	
	private int accountId;
	
	private String accountNo;
	
	private String accountName;
	
	private String accountBank;
	
	private String bankAddress;
	
	private String mobile;
	
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String addGetRequest() throws Exception {
		if (amount <= 0){
			return ERROR;
		}
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		
		//从数据库中取得用户余额
		ScUserDao userDao = DaoFactory.getScUserDao();
		ScUser currentUser = userDao.getUserById(user.getUserId());
		BankAccountDao baDao = DaoFactory.getBankAccountDao();
		BankAccount currentAccount = baDao.getBankAcountById(accountId);
		if (currentAccount == null){
			return ERROR;
		}
		WithdrawRequestDao wrDao = DaoFactory.getWithdrawRequestDao();
		synchronized (UserLock.class) {
			if (amount > currentUser.getDeposit()){
				return ERROR;
			}
			WithdrawRequest request = wrDao.insertWithdrawRequest(currentUser, 1, currentAccount, amount);
			if (request == null){
				return ERROR;
			}
			currentUser.setDeposit(currentUser.getDeposit() - amount);
			currentUser.setDepositStr(DecimalFormatUtil.formatFloat2String(currentUser.getDeposit()));
			userDao.editUser(currentUser);
			ctx.getSession().put("user", currentUser);
		}
		return SUCCESS;
	}
	
	public String addTransferRequest() throws Exception {
		if (amount <= 0){
			return ERROR;
		}
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		
		//从数据库中取得用户余额
		ScUserDao userDao = DaoFactory.getScUserDao();
		ScUser currentUser = userDao.getUserById(user.getUserId());
		BankAccountDao baDao = DaoFactory.getBankAccountDao();
		BankAccount currentAccount = baDao.getBankAcountById(accountId);
		//如果账户为空，则新建账户
		if (currentAccount == null){
			currentAccount = baDao.insertBankAccount(currentUser, accountName, accountNo, accountBank, 2, bankAddress, mobile, null);
			if (currentAccount == null){
				return ERROR;
			}
		}
		else {
			currentAccount.setUser(currentUser);
			currentAccount.setCompanyName(accountName);
			currentAccount.setAccountNo(accountNo);
			currentAccount.setAccountBank(accountBank);
			currentAccount.setBankAddress(bankAddress);
			currentAccount.setMobile(mobile);
			if (!baDao.editBankAcount(currentAccount)){
				return ERROR;
			}
		}
		WithdrawRequestDao wrDao = DaoFactory.getWithdrawRequestDao();
		synchronized (UserLock.class) {
			if (amount > currentUser.getDeposit()){
				return ERROR;
			}
			WithdrawRequest request = wrDao.insertWithdrawRequest(currentUser, 1, currentAccount, amount);
			if (request == null){
				return ERROR;
			}
			currentUser.setDeposit(currentUser.getDeposit() - amount);
			currentUser.setDepositStr(DecimalFormatUtil.formatFloat2String(currentUser.getDeposit()));
			userDao.editUser(currentUser);
			ctx.getSession().put("user", currentUser);
		}
		return SUCCESS;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
