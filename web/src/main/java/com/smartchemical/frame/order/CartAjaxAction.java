/**
 * 
 */
package com.smartchemical.frame.order;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.order.CartDao;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@SuppressWarnings("deprecation")
public class CartAjaxAction implements Action {
	
	private int productId;
	
	private float quantity;

	private InputStream inputStream;
	
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

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String editProductQuantity() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ProductDao pDao = DaoFactory.getProductDao();
		Product p = pDao.getProductById(productId);
		if (p == null){
			return ERROR;
		}
		if (quantity < p.getMinSoldQunatity() || quantity > p.getStockLevel()){
			return ERROR;
		}
		CartDao cartDao = DaoFactory.getCartDao();
		cartDao.editProductQuantity(user.getUserId(), productId, quantity);
		setInputStream(new StringBufferInputStream("success"));
		return SUCCESS;
	}	
	
	public String execute() throws Exception {
		return SUCCESS;
	}

}
