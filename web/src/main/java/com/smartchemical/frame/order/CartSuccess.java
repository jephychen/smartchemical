/**
 * 
 */
package com.smartchemical.frame.order;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.order.CartDao;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.user.ScUser;
import com.smartchemical.entities.frame.order.Cart;

/**
 * @author Jephy
 *
 */
public class CartSuccess implements Action {
	
	private int productId;
	
	private float quantity;
	
	private String loginTip;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public String getLoginTip() {
		return loginTip;
	}

	public void setLoginTip(String loginTip) {
		this.loginTip = loginTip;
	}

	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ProductDao productDao = DaoFactory.getProductDao();
		Product product = productDao.getProductById(productId);
		if (product == null || product.getProductStatus() != 1){
			return ERROR;
		}
		if (quantity > product.getStockLevel()){
			return ERROR;
		}
		CartDao cartDao = DaoFactory.getCartDao();
		Cart cart = cartDao.getCartByUserProduct(user.getUserId(), productId);
		if (cart != null){
			if (cart.getQuantity() + quantity > product.getStockLevel()){
				cartDao.editCart(cart.getCartId(), cart.getUser(), cart.getProduct(), product.getStockLevel());
			}
			else{
				cartDao.editCart(cart.getCartId(), cart.getUser(), cart.getProduct(), cart.getQuantity() + quantity);
			}
			return SUCCESS;
		}
		cartDao.insertCart(user, product, quantity);
		List<Cart> carts = cartDao.getCartByUser(user.getUserId());
		
		ScUserDao scUserDao = DaoFactory.getScUserDao();
		if (carts == null){
			user.setCartQuantity(0);
			scUserDao.setUserCartQuantity(user.getUserId(), 0);
		}else {
			user.setCartQuantity(carts.size());
			scUserDao.setUserCartQuantity(user.getUserId(), carts.size());
		}
		ctx.getSession().put("user", user);
		return SUCCESS;
	}

}
