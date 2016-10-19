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

import com.smartchemical.api.frame.dao.account.RechargeRequestDao;
import com.smartchemical.entities.frame.account.RechargeRequest;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ RechargeRequestDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/RechargeRequestDao/remote")
public class RechargeRequestDaoBean implements RechargeRequestDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;
	
	public RechargeRequest insertRechargeRequest(ScUser user, int requestType, int requestStatus,
			float amount, float actualamount) {
		try {
			RechargeRequest request = new RechargeRequest();
			request.setUser(user);
			request.setRequestType(requestType);
			request.setRequestStatus(requestStatus);
			request.setAmount(amount);
			request.setActualamount(actualamount);
			request.setLastmodified(new Timestamp(System.currentTimeMillis()));
			request.setCreatedtime(new Timestamp(System.currentTimeMillis()));
			em.persist(request);
			return request;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeRechargeRequest(int requestId) {
		try {
			RechargeRequest request = em.find(RechargeRequest.class, requestId);
			em.remove(request);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editRechargeRequest(RechargeRequest request) {
		try {
			RechargeRequest oldRequest = em.find(RechargeRequest.class, request.getRequestId());
			oldRequest.setUser(request.getUser());
			oldRequest.setRequestType(request.getRequestType());
			oldRequest.setAmount(request.getAmount());
			oldRequest.setLastmodified(new Timestamp(System.currentTimeMillis()));
			em.merge(request);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public RechargeRequest getRechargeRequestById(int requestId) {
		try {
			RechargeRequest request = em.find(RechargeRequest.class, requestId);
			return request;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getRechargeRequestCount(Timestamp sinceDate, Map<String, List<String>> filters) {
		try {
			Query query = em.createQuery("select count(r.requestId) from RechargeRequest r where r.createdtime > ?1" + makeWhere(filters));
			query.setParameter(1, sinceDate);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<RechargeRequest> getRechargeRequest(Timestamp sinceDate, Map<String, List<String>> filters, int pageSize, int pageIndex) {
		try {
			Query query = em.createQuery("select r from RechargeRequest r where r.createdtime > ?1" + makeWhere(filters) + " order by r.requestId DESC");
			query.setParameter(1, sinceDate);
			@SuppressWarnings("unchecked")
			List<RechargeRequest> requests = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			return requests;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getRechargeRequestCountByUser(int userId, Timestamp sinceDate, Map<String, List<String>> filters) {
		try {
			Query query = em.createQuery("select count(r.requestId) from RechargeRequest r where r.user.userId =?1 and r.createdtime > ?2" + makeWhere(filters));
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

	public List<RechargeRequest> getRechargeRequestByUser(int userId, Timestamp sinceDate, Map<String, List<String>> filters, int pageSize, int pageIndex) {
		try {
			Query query = em.createQuery("select r from RechargeRequest r where r.user.userId = ?1 and r.createdtime > ?2" + makeWhere(filters) + " order by r.requestId DESC");
			query.setParameter(1, userId);
			query.setParameter(2, sinceDate);
			@SuppressWarnings("unchecked")
			List<RechargeRequest> requests = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
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
				if (entry.getKey().equals("requestType")){
					whereSql.append(dealRequestType(entry.getValue()));
				}
				else if (entry.getKey().equals("requestStatus")){
					whereSql.append(dealRequestStatus(entry.getValue()));
				}
				else if (entry.getKey().equals("userName")){
					whereSql.append(dealUsername(entry.getValue()));
				}
			}
		}
		return whereSql.toString();
	}
	
	private String dealUsername(List<String> username) {
		if (username == null || username.size() < 1){
			return "";
		}
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" and r.user.userName like '%" + username.get(0) + "%'");
		return whereSql.toString();
	}

	private String dealRequestType(List<String> types){
		if (types == null || types.size() < 1 || types.contains("0")){
			return "";
		}
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" and r.requestType in (");
		int i;
		for (i = 0; i < types.size() - 1; i++){
			whereSql.append( types.get(i) + ",");
		}
		whereSql.append( types.get(i) + ")");
		return whereSql.toString();
	}
	
	private String dealRequestStatus(List<String> statuses){
		if (statuses == null || statuses.size() < 1 || statuses.contains("0")){
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

}
