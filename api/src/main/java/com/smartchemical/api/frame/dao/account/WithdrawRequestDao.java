/**
 * 
 */
package com.smartchemical.api.frame.dao.account;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.smartchemical.entities.frame.account.WithdrawRequest;
import com.smartchemical.entities.frame.user.BankAccount;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public interface WithdrawRequestDao {
	public WithdrawRequest insertWithdrawRequest(ScUser user, int requestStatus, BankAccount destAccount, float amount);
	
	public boolean removeWithdrawRequest(int requestId);
	
	public boolean editWithdrawRequest(WithdrawRequest request);
	
	public WithdrawRequest getWithdrawRequestById(int requestId);
	
	public int getWithdrawRequestCountByUser(int userId, Timestamp sinceDate, Map<String, List<String>> filters);
	
	public List<WithdrawRequest> getWithdrawRequestByUser(int userId, Timestamp sinceDate, Map<String, List<String>> filters, int pageSize, int pageIndex);
	
	public int getWithdrawRequestCount(Timestamp sinceDate, Map<String, List<String>> filters);
	
	public List<WithdrawRequest> getWithdrawRequest(Timestamp sinceDate, Map<String, List<String>> filters, int pageSize, int pageIndex);
}
