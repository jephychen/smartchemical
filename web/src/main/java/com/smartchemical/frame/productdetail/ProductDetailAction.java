/**
 * 
 */
package com.smartchemical.frame.productdetail;

import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.product.Product;

/**
 * @author Jephy
 * 
 */
public class ProductDetailAction {

	private String productId;

	private Product product;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String execute() throws Exception {
		try {
			ProductDao productDao = DaoFactory.getProductDao();
			if (productId == null){
				return "success";
			}
			product = productDao.getProductById(Integer.parseInt(productId));
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}
}
