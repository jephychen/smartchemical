/**
 * 
 */
package com.smartchemical.session.frame.dao.order.bean;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.order.CartDao;
import com.smartchemical.entities.frame.order.Cart;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ CartDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/CartDaoBean/remote")
public class CartDaoBean implements CartDao {
	
	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public boolean insertCart(ScUser user, Product product, float quantity) {
		try {
			Cart cart = new Cart();
			cart.setUser(user);
			cart.setProduct(product);
			cart.setQuantity(quantity);
			em.persist(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeCart(int cartId) {
		try {
			Cart cart = em.find(Cart.class, cartId);
			em.remove(cart);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editCart(int cartId, ScUser user, Product product,
			float quantity) {
		try {
			Cart cart = new Cart();
			cart.setCartId(cartId);
			cart.setUser(user);
			cart.setProduct(product);
			cart.setQuantity(quantity);
			em.merge(cart);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Cart> getCartByUser(int userId) {
		try {
			Query query = em.createNativeQuery("select * from Cart where scUserId = ?1", Cart.class);
			query.setParameter(1, userId);
			@SuppressWarnings("unchecked")
			List<Cart> carts = query.getResultList();
			return carts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public List<Cart> getCartByUserAndProductIds(int userId, String ids) {
		try {
			Query query = em.createNativeQuery("select * from Cart where scUserId = ?1 and productId in " + ids, Cart.class);
			query.setParameter(1, userId);
			@SuppressWarnings("unchecked")
			List<Cart> carts = query.getResultList();
			return carts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean removeCartByUser(int userId) {
		try {
			Query query = em.createQuery("delete from Cart p where p.userId = ?1");
			query.setParameter(1, userId);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Cart getCartByUserProduct(int userId, int productId) {
		try {
			Query query = em.createNativeQuery("select * from Cart where scUserId = ?1 and productId = ?2", Cart.class);
			query.setParameter(1, userId);
			query.setParameter(2, productId);
			@SuppressWarnings("unchecked")
			List<Cart> carts = query.getResultList();
			if (carts == null || carts.isEmpty()){
				return null;
			}
			return carts.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeCartByUserProduct(int userId, int productId) {
		try {
			Query query = em.createNativeQuery("delete from Cart where scUserId = ?1 and productId = ?2");
			query.setParameter(1, userId);
			query.setParameter(2, productId);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeCartByUserProductIds(int userId, String ids) {
		try {
			Query query = em.createNativeQuery("delete from Cart where scUserId = " + userId + " and productId in " + ids);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editProductQuantity(int userId, int productId,
			float quantity) {
		try {
			Query query = em.createNativeQuery("update Cart set quantity = ?1 where scUserId = " + userId + " and productId = " + productId);
			query.setParameter(1, quantity);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
