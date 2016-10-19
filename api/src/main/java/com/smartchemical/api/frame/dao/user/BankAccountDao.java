/**
 * 
 */
package com.smartchemical.api.frame.dao.user;

import java.util.List;

import com.smartchemical.entities.frame.user.BankAccount;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public interface BankAccountDao {
	public BankAccount insertBankAccount(ScUser user, String companyName, String accountNo, String accountBank,
			int accountType, String bankAddress, String mobile, String remark);
	
	public boolean removeBankAccount(int accountId);
	
	public boolean editBankAcount(BankAccount account);
	
	public BankAccount getBankAcountById(int accountId);
	
	public List<BankAccount> getBankAcountByUser(int userId);
	
	public List<BankAccount> getMainBankAcountByUser(int userId);
	
	public List<BankAccount> getOtherBankAcountByUser(int userId);
}
