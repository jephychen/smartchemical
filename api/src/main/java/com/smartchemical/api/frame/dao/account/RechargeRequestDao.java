/**
 * 
 */
package com.smartchemical.api.frame.dao.account;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.smartchemical.entities.frame.account.RechargeRequest;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public interface RechargeRequestDao {
	public RechargeRequest insertRechargeRequest(ScUser user, int requestType, int requestStatus, float amount, float actualamount);
	
	public boolean removeRechargeRequest(int requestId);
	
	public boolean editRechargeRequest(RechargeRequest request);
	
	public RechargeRequest getRechargeRequestById(int requestId);
	
	public int getRechargeRequestCount(Timestamp sinceDate, Map<String, List<String>> filters);
	
	public List<RechargeRequest> getRechargeRequest(Timestamp sinceDate, Map<String, List<String>> filters, int pageSize, int pageIndex);

	
	public int getRechargeRequestCountByUser(int userId, Timestamp sinceDate, Map<String, List<String>> filters);
	
	public List<RechargeRequest> getRechargeRequestByUser(int userId, Timestamp sinceDate, Map<String, List<String>> filters, int pageSize, int pageIndex);
}
