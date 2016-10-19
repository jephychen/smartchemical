/**
 * 
 */
package com.smartchemical.session.frame.dao.account.bean;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.account.WithdrawRequestDao;
import com.smartchemical.entities.frame.account.WithdrawRequest;
import com.smartchemical.entities.frame.user.BankAccount;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ WithdrawRequestDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/WithdrawRequestDaoBean/remote")
public class WithdrawRequestDaoBean implements WithdrawRequestDao {
	
	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public WithdrawRequest insertWithdrawRequest(ScUser user,
			int requestStatus, BankAccount destAccount, float amount) {
		try {
			WithdrawRequest request = new WithdrawRequest();
			request.setUser(user);
			request.setRequestStatus(requestStatus);
			request.setDestAccount(destAccount);
			request.setAmount(amount);
			request.setLastmodified(new Timestamp(System.currentTimeMillis()));
			request.setCreatedtime(new Timestamp(System.currentTimeMillis()));
			em.persist(request);
			return request;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeWithdrawRequest(int requestId) {
		try {
			WithdrawRequest request = em.find(WithdrawRequest.class, requestId);
			em.remove(request);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editWithdrawRequest(WithdrawRequest request) {
		try {
			WithdrawRequest newRequest = em.find(WithdrawRequest.class, request.getRequestId());
			newRequest.setUser(request.getUser());
			newRequest.setRequestStatus(request.getRequestStatus());
			newRequest.setDestAccount(request.getDestAccount());
			newRequest.setAmount(request.getAmount());
			newRequest.setLastmodified(new Timestamp(System.currentTimeMillis()));
			em.merge(newRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public WithdrawRequest getWithdrawRequestById(int requestId) {
		try {
			WithdrawRequest request = em.find(WithdrawRequest.class, requestId);
			return request;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getWithdrawRequestCountByUser(int userId, Timestamp sinceDate, Map<String, List<String>> filters) {
		try {
			Query query = em.createNativeQuery("select count(requestId) from WithdrawRequest where scUserId =?1 and createdtime > ?2" + makeWhere(filters));
			query.setParameter(1, userId);
			query.setParameter(2, sinceDate);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<WithdrawRequest> getWithdrawRequestByUser(int userId, Timestamp sinceDate, Map<String, List<String>> filters, int pageSize, int pageIndex) {
		try {
			Query query = em.createNativeQuery("select * from WithdrawRequest where scUserId = ?1 and createdtime > ?2" + makeWhere(filters) + " order by requestId DESC", WithdrawRequest.class);
			query.setParameter(1, userId);
			query.setParameter(2, sinceDate);
			@SuppressWarnings("unchecked")
			List<WithdrawRequest> requests = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			return requests;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getWithdrawRequestCount(Timestamp sinceDate, Map<String, List<String>> filters) {
		try {
			Query query = em.createQuery("select count(requestId) from WithdrawRequest r where r.createdtime > ?1" + makeWhere(filters));
			query.setParameter(1, sinceDate);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<WithdrawRequest> getWithdrawRequest(Timestamp sinceDate, Map<String, List<String>> filters, int pageSize, int pageIndex) {
		try {
			Query query = em.createQuery("select r from WithdrawRequest r where r.createdtime > ?1" + makeWhere(filters) + " order by requestId DESC");
			query.setParameter(1, sinceDate);
			@SuppressWarnings("unchecked")
			List<WithdrawRequest> requests = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			return requests;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String makeWhere(Map<String, List<String>> filters) {
		StringBuilder whereSql = new StringBuilder();
		if (filters != null && !filters.isEmpty()){
			for (Entry<String, List<String>> entry : filters.entrySet()){
				if (entry.getKey().equals("bankAccountId")){
					whereSql.append(dealBankAccountId(entry.getValue()));
				}
				else if (entry.getKey().equals("requestStatus")){
					whereSql.append(dealStatus(entry.getValue()));
				}
				else if (entry.getKey().equals("username")){
					whereSql.append(dealUsername(entry.getValue()));
				}
			}
		}
		return whereSql.toString();
	}
	
	private String dealBankAccountId(List<String> ids){
		if (ids.contains("all") || ids.size() < 1){
			return "";
		}
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" and bankAccountId in (");
		int i;
		for (i = 0; i < ids.size() - 1; i++){
			whereSql.append( ids.get(i) + ",");
		}
		whereSql.append( ids.get(i) + ")");
		return whereSql.toString();
	}
	
	private String dealStatus(List<String> statuses){
		if (statuses == null || statuses.size() < 1){
			return "";
		}
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" and r.requestStatus in (");
		int i;
		for (i = 0; i < statuses.size() - 1; i++){
			whereSql.append( statuses.get(i) + ",");
		}
		whereSql.append( statuses.get(i) + ")");
		return whereSql.toString();
	}
	
	private String dealUsername(List<String> username){
		if (username == null || username.size() < 1){
			return "";
		}
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" and r.user.userName like '%" + username.get(0) + "%'");
		return whereSql.toString();
	}

}
