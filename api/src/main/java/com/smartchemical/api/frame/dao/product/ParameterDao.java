/**
 * 
 */
package com.smartchemical.api.frame.dao.product;

import java.util.Map;

import com.smartchemical.entities.frame.product.ProductType;


/**
 * @author Jephy
 *
 */
public interface ParameterDao {
	public boolean insertParameter(ProductType productType, String parameterName, String parameterValue);
	
	public boolean editParameter(int parameterId, ProductType productType, String parameterName, String parameterValue);
	
	public boolean removeParameterById(int parameterId);
	
	public boolean removeParameterByProductTypeId(int productTypeId);
	
	public Map<String, String> getParameterByProductTypeId(int productTypeId);
}
