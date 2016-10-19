/**
 * 
 */
package com.smartchemical.frame.order;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.order.CartDao;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.order.Cart;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class CartProcessor implements Action {
	
	private int productId;
	
	private float quantity;
	
	private String loginTip;
	
	private List<Cart> carts;
	
	private int removeProductId = -1;
	
	private String removeSelectedIds;
	
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

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public int getRemoveProductId() {
		return removeProductId;
	}

	public void setRemoveProductId(int removeProductId) {
		this.removeProductId = removeProductId;
	}

	public String getRemoveSelectedIds() {
		return removeSelectedIds;
	}

	public void setRemoveSelectedIds(String removeSelectedIds) {
		this.removeSelectedIds = removeSelectedIds;
	}

	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		CartDao cartDao = DaoFactory.getCartDao();
		if (removeProductId != -1){
			cartDao.removeCartByUserProduct(user.getUserId(), removeProductId);
		}
		if (removeSelectedIds != null && !removeSelectedIds.isEmpty()){
			removeSelected(cartDao, user.getUserId(), removeSelectedIds);
		}
		carts = cartDao.getCartByUser(user.getUserId());
		
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

	private void removeSelected(CartDao cartDao, int userId, String removeSelectedIds2) {
		String ids = "(" + removeSelectedIds2.substring(0, removeSelectedIds2.length() - 1) + ")";
		cartDao.removeCartByUserProductIds(userId, ids);
	}
}
