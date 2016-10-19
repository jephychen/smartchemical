/**
 * 
 */
package com.smartchemical.api.frame.dao.product;

import java.util.List;

import com.smartchemical.entities.frame.product.ProductNo;

/**
 * @author Jephy
 *
 */
public interface ProductNoDao {
	public ProductNo insertProductNo(String productNoName, String description);
	
	public boolean removeProductNo(int productNoId);
	
	public boolean editProductNo(ProductNo product);
	
	public ProductNo getProductNo(int productNoId);
	
	public ProductNo getProductByName(String productName);
	
	public List<ProductNo> getAllProductNos();
}
