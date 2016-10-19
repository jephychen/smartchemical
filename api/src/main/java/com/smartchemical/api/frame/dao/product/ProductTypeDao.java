/**
 * 
 */
package com.smartchemical.api.frame.dao.product;

import java.util.List;

import com.smartchemical.entities.frame.product.ProductCategory;
import com.smartchemical.entities.frame.product.ProductType;

/**
 * @author Jephy
 *
 */
public interface ProductTypeDao {
	public boolean insertProductType(String productTypeName, ProductCategory productCategory, String description);
	
	public boolean removeProductType(int productTypeId);
	
	public boolean editProductType(int productTypeId, String productTypeName, ProductCategory productCategory, String description);
	
	public ProductType getProductTypeById(int productTypeId);
	
	public List<ProductType> getAllProductTypes();
	
	public List<ProductType> getAllProductTypesByCategory(int categoryId);
}
