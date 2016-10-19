/**
 * 
 */
package com.smartchemical.api.frame.dao.company;

import java.util.List;

import com.smartchemical.entities.frame.company.Brand;
import com.smartchemical.entities.frame.company.Company;

/**
 * @author Jephy
 *
 */
public interface BrandDao {
	public Brand insertBrand(String brandName, Company company, String logoPath);
	
	public boolean editBrand(Brand brand);
	
	public boolean removeBrand(int brandId);
	
	public Brand getBrandById(int brandId);
	
	public Brand getBrandByName(String brandName);
	
	public List<Brand> getAllBrands();
}
