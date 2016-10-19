/**
 * 
 */
package com.smartchemical.session.frame.dao.user.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.common.constant.SearchConstant;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.user.Role;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 * 
 */
@Stateless
@Remote({ ScUserDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/ScUserDaoBean/remote")
public class ScUserDaoBean implements ScUserDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;
	
	public ScUser insertUser(String userName, String realName, String password, Date birthday, Company company, String externalCompanyName, 
			Role role, String email, City city, String userAddress, float deposit, int companyLicenseStatus, 
			String mobileNo, Timestamp createTime) {
		try {
			ScUser user = new ScUser();
			user.setUserName(userName);
			user.setRealName(realName);
			user.setPassword(password);
			user.setBirthday(birthday);
			user.setCompany(company);
			user.setExternalCompanyName(externalCompanyName);
			user.setRole(role);
			user.setEmail(email);
			user.setCity(city);
			user.setUserAddress(userAddress);
			user.setDeposit(deposit);
			user.setCompanyLicenseStatus(companyLicenseStatus);
			user.setMobileNo(mobileNo);
			user.setCreateTime(createTime);
			em.persist(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeUser(int userId) {
		try {
			ScUser user = em.find(ScUser.class, userId);
			em.remove(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editUser(int userId, String userName, String realName, String password, Date birthday, Company company, String externalCompanyName,
			Role role, String email, City city, String userAddress, float deposit, int companyLicenseStatus, 
			String mobileNo, Timestamp createTime) {
		try {
			ScUser user = em.find(ScUser.class, userId);
			user.setUserName(userName);
			user.setRealName(realName);
			user.setPassword(password);
			user.setBirthday(birthday);
			user.setCompany(company);
			user.setExternalCompanyName(externalCompanyName);
			user.setRole(role);
			user.setEmail(email);
			user.setCity(city);
			user.setUserAddress(userAddress);
			user.setDeposit(deposit);
			user.setCompanyLicenseStatus(companyLicenseStatus);
			user.setCreateTime(createTime);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ScUser getUserById(int userId) {
		ScUser user = em.find(ScUser.class, userId);
		return user;
	}
	
	public ScUser getUserByUserName(String userName) {
		try {
			Query query = em.createQuery("select u from ScUser u where u.userName = ?1");
			query.setParameter(1, userName);
			@SuppressWarnings("unchecked")
			List<ScUser> users = query.getResultList();
			if (users == null || users.isEmpty()){
				return null;
			}
			return users.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ScUser getUserByEmail(String email) {
		try {
			Query query = em.createQuery("select u from ScUser u where u.email = ?1");
			query.setParameter(1, email);
			@SuppressWarnings("unchecked")
			List<ScUser> users = query.getResultList();
			if (users == null || users.isEmpty()){
				return null;
			}
			return users.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ScUser getUserByEmail(String email, String currentUserEmail) {
		try {
			Query query = em.createQuery("select u from ScUser u where u.email = ?1 and u.email <> ?2");
			query.setParameter(1, email);
			query.setParameter(2, currentUserEmail);
			@SuppressWarnings("unchecked")
			List<ScUser> users = query.getResultList();
			if (users == null || users.isEmpty()){
				return null;
			}
			return users.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ScUser getUserByMobile(String mobileNo) {
		try {
			Query query = em.createQuery("select u from ScUser u where u.mobileNo = ?1");
			query.setParameter(1, mobileNo);
			@SuppressWarnings("unchecked")
			List<ScUser> users = query.getResultList();
			if (users == null || users.isEmpty()){
				return null;
			}
			return users.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ScUser getUserByMobile(String mobileNo, String currentUserEmail) {
		try {
			Query query = em.createQuery("select u from ScUser u where u.mobileNo = ?1 and u.mobileNo <> ?2");
			query.setParameter(1, mobileNo);
			query.setParameter(2, currentUserEmail);
			@SuppressWarnings("unchecked")
			List<ScUser> users = query.getResultList();
			if (users == null || users.isEmpty()){
				return null;
			}
			return users.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ScUser> getUsersByRole(int roleId) {
		try {
			Query query = em.createQuery("select u from ScUser u where u.roleId = ?1");
			query.setParameter(1, roleId);
			@SuppressWarnings("unchecked")
			List<ScUser> users = query.getResultList();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ScUser> getUsersByCity(int cityId) {
		try {
			Query query = em.createQuery("select u from ScUser u where u.cityId = ?1");
			query.setParameter(1, cityId);
			@SuppressWarnings("unchecked")
			List<ScUser> users = query.getResultList();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getUserCartQuantity(int userId) {
		ScUser user = em.find(ScUser.class, userId);
		return user.getCartQuantity();
	}

	public boolean setUserCartQuantity(int userId, int cartQuantity) {
		try {
			ScUser user = em.find(ScUser.class, userId);
			user.setCartQuantity(cartQuantity);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editUser(ScUser newUser) {
		em.merge(newUser);
		return true;
	}

	public int getUserCountByCondition(String keyword,
			Map<String, List<String>> filters) {
		try {
			Query query = em.createQuery(
					"select count(u.userId) from ScUser u where 1 = 1" + makeWhere(keyword, filters));
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ScUser> getUserByCondition(String keyword, 
			Map<String, List<String>> filters, String sortby, int pageSize, int pageIndex) {
		String querySql = "select u from ScUser u where 1 = 1" + makeWhere(keyword, filters) + makeOrderby(sortby);
		try {
			Query query = em.createQuery(querySql);
			List<ScUser> users = null;
			if (pageSize <= 0 || pageIndex <= 0){
				users = query.getResultList();
			}
			else {
				users = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String makeWhere(String keyword, Map<String, List<String>> filters) {
		StringBuilder whereSql = new StringBuilder();
		if (keyword != null && !keyword.isEmpty()){
			whereSql.append(" and u.userName like '%" + keyword + "%'");
		}
		if (filters != null && !filters.isEmpty()){
			for (Entry<String, List<String>> entry : filters.entrySet()){
				if (entry.getKey().equals("roleId")){
					whereSql.append(dealRoleId(entry.getValue()));
				}
			}
		}
		whereSql.append(" and u.userName <> 'admin'");
		return whereSql.toString();
	}

	private Object dealRoleId(List<String> value) {
		if (value == null || value.isEmpty()){
			return "";
		}
		StringBuilder subSql = new StringBuilder();
		subSql.append(" and u.role.roleId in (");
		
		int i;
		for (i = 0; i < value.size() - 1; i++){
			subSql.append( value.get(i) + ",");
		}
		subSql.append( value.get(i) + ")");
		return subSql.toString();
	}

	private String makeOrderby(String sortby) {
		StringBuilder orderbySql = new StringBuilder();
		if (sortby == null || sortby.isEmpty()){
			return orderbySql.toString();
		}
		if (sortby.equals(SearchConstant.SORTBY_LASTMODIFIED_DESC)){
			orderbySql.append(" order by lastModified DESC");
		}
		else if (sortby.equals(SearchConstant.SORTBY_ID_DESC)){
			orderbySql.append(" order by u.userId DESC");
		}
		return orderbySql.toString();
	}

}
