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

import com.smartchemical.api.frame.dao.user.CartReceiverDao;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.user.CartReceiver;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ CartReceiverDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/CartReceiverDaoBean/remote")
public class CartReceiverDaoBean implements CartReceiverDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;
	
	public boolean insertCartReceiver(String receiverName, ScUser user,
			City city, String address, String mobileNo, String email,
			long lastSelected, String remark) {
		try {
			CartReceiver receiver = new CartReceiver();
			receiver.setReceiverName(receiverName);
			receiver.setUser(user);
			receiver.setCity(city);
			receiver.setAddress(address);
			receiver.setMobileNo(mobileNo);
			receiver.setEmail(email);
			receiver.setLastSelected(lastSelected);
			receiver.setRemark(remark);
			em.persist(receiver);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editCartReceiver(int receiverId, String receiverName,
			ScUser user, City city, String address, String mobileNo,
			String email, long lastSelected, String remark) {
		try {
			CartReceiver receiver = em.find(CartReceiver.class, receiverId);
			receiver.setReceiverName(receiverName);
			receiver.setUser(user);
			receiver.setCity(city);
			receiver.setAddress(address);
			receiver.setMobileNo(mobileNo);
			receiver.setEmail(email);
			receiver.setLastSelected(lastSelected);
			receiver.setRemark(remark);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeReceiver(int receiverId) {
		try {
			CartReceiver receiver = em.find(CartReceiver.class, receiverId);
			em.remove(receiver);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeReceiverByUser(int userId) {
		try {
			Query query = em.createQuery("delete from CartReceiver r where r.scUserId = ?1");
			query.setParameter(1, userId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<CartReceiver> getReceiverByUser(int userId) {
		try {
			Query query = em.createNativeQuery("select * from CartReceiver where scUserId = ?1 order by lastSelected DESC", CartReceiver.class);
			query.setParameter(1, userId);
			@SuppressWarnings("unchecked")
			List<CartReceiver> receivers = query.getResultList();
			return receivers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<CartReceiver> getEnableReceiverByUser(int userId) {
		try {
			Query query = em.createNativeQuery("select * from CartReceiver where scUserId = ?1 and enable = 1 order by lastSelected DESC", CartReceiver.class);
			query.setParameter(1, userId);
			@SuppressWarnings("unchecked")
			List<CartReceiver> receivers = query.getResultList();
			return receivers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public CartReceiver getReceiverById(int receiverId) {
		CartReceiver cartReceiver = em.find(CartReceiver.class, receiverId);
		return cartReceiver;
	}

	public CartReceiver getReceiverByIdLastselected(int userId,
			long lastSelected) {
		try {
			Query query = em.createNativeQuery("select * from CartReceiver where scUserId = ?1 and lastSelected = ?2", CartReceiver.class);
			query.setParameter(1, userId);
			query.setParameter(2, lastSelected);
			@SuppressWarnings("unchecked")
			List<CartReceiver> receivers = query.getResultList();
			if (receivers == null || receivers.isEmpty()){
				return null;
			}
			return receivers.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean disableReceiver(int receiverId) {
		try {
			CartReceiver receiver = em.find(CartReceiver.class, receiverId);
			receiver.setEnable(0);
			em.merge(receiver);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editCartReceiver(CartReceiver receiver) {
		try {
			em.merge(receiver);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
