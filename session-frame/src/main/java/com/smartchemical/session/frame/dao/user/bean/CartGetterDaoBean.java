/**
 * 
 */
package com.smartchemical.session.frame.dao.user.bean;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.user.CartGetterDao;
import com.smartchemical.entities.frame.user.CartGetter;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ CartGetterDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/CartGetterDaoBean/remote")
public class CartGetterDaoBean implements CartGetterDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;
	
	public boolean insertGetter(ScUser user, String getterName,
			String getterIdNo, String getterMobileNo, String getterEmail,
			String truckLicenseNo, long lastSelected, String remark) {
		try {
			CartGetter getter = new CartGetter();
			getter.setUser(user);
			getter.setGetterName(getterName);
			getter.setGetterIdNo(getterIdNo);
			getter.setGetterMobileNo(getterMobileNo);
			getter.setGetterEmail(getterEmail);
			getter.setTruckLicenseNo(truckLicenseNo);
			getter.setLastSelected(lastSelected);
			getter.setRemark(remark);
			em.persist(getter);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editGetter(int getterId, ScUser user, String getterName,
			String getterIdNo, String getterMobileNo, String getterEmail,
			String truckLicenseNo, long lastSelected, String remark) {
		try {
			CartGetter getter = em.find(CartGetter.class, getterId);
			getter.setUser(user);
			getter.setGetterName(getterName);
			getter.setGetterIdNo(getterIdNo);
			getter.setGetterMobileNo(getterMobileNo);
			getter.setGetterEmail(getterEmail);
			getter.setTruckLicenseNo(truckLicenseNo);
			getter.setLastSelected(lastSelected);
			getter.setRemark(remark);
			em.persist(getter);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeGetter(int getterId) {
		try {
			CartGetter getter = em.find(CartGetter.class, getterId);
			em.remove(getter);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeGetterByUser(int userId) {
		try {
			Query query = em.createQuery("delete from CartGetter r where r.scUserId = ?1");
			query.setParameter(1, userId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<CartGetter> getGetterByUser(int userId) {
		try {
			Query query = em.createNativeQuery("select * from CartGetter where scUserId = ?1 order by lastSelected DESC", CartGetter.class);
			query.setParameter(1, userId);
			@SuppressWarnings("unchecked")
			List<CartGetter> getters = query.getResultList();
			return getters;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<CartGetter> getEnableGetterByUser(int userId) {
		try {
			Query query = em.createNativeQuery("select * from CartGetter where scUserId = ?1 and enable = 1 order by lastSelected DESC", CartGetter.class);
			query.setParameter(1, userId);
			@SuppressWarnings("unchecked")
			List<CartGetter> getters = query.getResultList();
			return getters;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public CartGetter getGetterById(int getterId) {
		CartGetter getter = em.find(CartGetter.class, getterId);
		return getter;
	}

	public CartGetter getGetterByLastselected(int userId, long lastSelected) {
		try {
			Query query = em.createNativeQuery("select * from CartGetter where scUserId = ?1 and lastSelected = ?2", CartGetter.class);
			query.setParameter(1, userId);
			query.setParameter(2, lastSelected);
			@SuppressWarnings("unchecked")
			List<CartGetter> getters = query.getResultList();
			if (getters == null | getters.isEmpty()){
				return null;
			}
			return getters.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean disableGetter(int getterId) {
		try {
			CartGetter getter = em.find(CartGetter.class, getterId);
			getter.setEnable(0);
			em.merge(getter);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editGetter(CartGetter getter) {
		try {
			em.merge(getter);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
