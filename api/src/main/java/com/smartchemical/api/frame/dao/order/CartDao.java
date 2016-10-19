/**
 * 
 */
package com.smartchemical.api.frame.dao.order;

import java.util.List;

import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.user.ScUser;
import com.smartchemical.entities.frame.order.Cart;

/**
 * @author Jephy
 *
 */
public interface CartDao {
	public boolean insertCart(ScUser user, Product product, float quantity);
	
	public boolean removeCart(int cartId);
	
	public boolean editCart(int cartId, ScUser user, Product product, float quantity);
	
	public List<Cart> getCartByUser(int userId);
	
	public List<Cart> getCartByUserAndProductIds(int userId, String ids);
	
	public Cart getCartByUserProduct(int userId, int productId);
	
	public boolean removeCartByUser(int userId);
	
	public boolean removeCartByUserProduct(int userId, int productId);
	
	public boolean removeCartByUserProductIds(int userId, String ids);
	
	public boolean editProductQuantity(int userId, int productId, float quantity);
}
