/**
 * 
 */
package com.smartchemical.usercenter;

import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.order.OrderItem;
import com.smartchemical.entities.frame.product.Product;

/**
 * @author Jephy
 *
 */
public class QueryOrder {
	protected void modifyProductSoldQuantityAndAmount(OrderItem currentItem) throws Exception {
		ProductDao productDao = DaoFactory.getProductDao();
		Product product = productDao.getProductById(currentItem.getProduct().getProductId());
		if (product == null){
			//TODO 记录日志
			return;
		}
		product.setTotalSoldQuantity(product.getTotalSoldQuantity() + currentItem.getQuantity());
		product.setTotalSoldAmount(product.getTotalSoldAmount() + currentItem.getQuantity() * currentItem.getProduct().getPrice());
		productDao.editProduct(product);
	}
}
