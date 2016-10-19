/**
 * 
 */
package com.smartchemical.api.frame.dao.product;

import java.util.List;

import com.smartchemical.entities.frame.product.ProductCategory;

/**
 * @author Jephy
 *
 */
public interface ProductCategoryDao {
	public boolean insertProductCategory(String productCategoryName, String descritpion);
	
	public boolean removeProductCategory(int productCategoryId);
	
	public boolean editProductCategory(int productCategoryId, String productCategoryName, String descritpion);
	
	public ProductCategory getProductCategoryById(int productCategoryId);
	
	public List<ProductCategory> getAllProductCategories();
}
