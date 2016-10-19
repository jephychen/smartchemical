/**
 * 
 */
package com.smartchemical.usercenter.commonuser;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.user.BankAccountDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.user.BankAccount;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class BankAccountEditAction implements Action {
	
	private int queryType;
	
	/**
	 * 1 - 修改， 0 - 新增
	 * */
	private int edit = 0;
	
	private BankAccount currentAcount;
	
	private String companyName;
	
	private String accountNo;
	
	private String accountBank;
	
	private String bankAddress;
	
	private String mobile;
	
	private String tip;
	
	private String title;
	
	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public int getEdit() {
		return edit;
	}

	public void setEdit(int edit) {
		this.edit = edit;
	}

	public BankAccount getCurrentAcount() {
		return currentAcount;
	}

	public void setCurrentAcount(BankAccount currentAcount) {
		this.currentAcount = currentAcount;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String editBankAccount() throws Exception {
		queryType = 6;
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		BankAccountDao accountDao = DaoFactory.getBankAccountDao();
		List<BankAccount> accounts = accountDao.getMainBankAcountByUser(user.getUserId());
		if (accounts != null && !accounts.isEmpty()){
			edit = 1;
			currentAcount = accounts.get(0);
		}
		else{
			edit = 0;
		}
		return SUCCESS;
	}
	
	public String editBankAccountSubmit() throws Exception {
		queryType = 6;
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		BankAccountDao accountDao = DaoFactory.getBankAccountDao();
		if (edit == 0){
			title = "添加银行账户";
			List<BankAccount> accounts = accountDao.getMainBankAcountByUser(user.getUserId());
			if (accounts != null && !accounts.isEmpty()){
				return ERROR;
			}
			BankAccount newAccount = accountDao.insertBankAccount(user, companyName, accountNo, accountBank, 1, bankAddress, mobile, null);
			if (newAccount != null){
				tip = "添加账户成功！";
				return "toresult";
			}
			else{
				tip = "添加账户失败！";
				return "toresult";
			}
		}
		else{
			title = "修改账号";
			List<BankAccount> accounts = accountDao.getBankAcountByUser(user.getUserId());
			BankAccount oldAcount = null;
			if (accounts == null || accounts.isEmpty()){
				return ERROR;
			}
			oldAcount = accounts.get(0);
			oldAcount.setCompanyName(companyName);
			oldAcount.setAccountNo(accountNo);
			oldAcount.setAccountBank(accountBank);
			oldAcount.setBankAddress(bankAddress);
			oldAcount.setMobile(mobile);
			if (accountDao.editBankAcount(oldAcount)){
				tip = "修改账户成功！";
				return "toresult";
			}
			else{
				tip = "修改账户失败！";
				return "toresult";
			}
		}
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
	
}
