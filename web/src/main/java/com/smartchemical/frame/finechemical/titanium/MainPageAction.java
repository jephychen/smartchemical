/**
 * 
 */
package com.smartchemical.frame.finechemical.titanium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.smartchemical.api.frame.common.constant.SearchConstant;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.api.frame.dao.company.CompanyType;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.product.Product;

/**
 * @author Jephy
 *
 */
public class MainPageAction {
	
	private List<Product> recommendProducts;
	
	private List<Product> hotProducts;
	
	private List<Company> companies;
	
	private List<Company> merchantCompanies;
	
	public List<Product> getHotProducts() {
		return hotProducts;
	}

	public void setHotProducts(List<Product> hotProducts) {
		this.hotProducts = hotProducts;
	}

	public List<Product> getRecommendProducts() {
		return recommendProducts;
	}

	public void setRecommendProducts(List<Product> recommendProducts) {
		this.recommendProducts = recommendProducts;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public List<Company> getMerchantCompanies() {
		return merchantCompanies;
	}

	public void setMerchantCompanies(List<Company> merchantCompanies) {
		this.merchantCompanies = merchantCompanies;
	}

	public String execute() throws Exception {
		ProductDao productDao = DaoFactory.getProductDao();
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> statusFilterItem = new LinkedList<String>();
		statusFilterItem.add("1");
		filtersMap.put("productStatus", statusFilterItem);
		hotProducts = productDao.getPagingProductByKeywordSortby(null, SearchConstant.SORTBY_QUANTITY_DESC, filtersMap, 5, 1);
		//应用户要求，把他的产品排到首页
		List<String> merchantFilterItem = new LinkedList<String>();
		merchantFilterItem.add("27");
		merchantFilterItem.add("17");
		filtersMap.put("merchantCompanyId", merchantFilterItem);
		recommendProducts = productDao.getPagingProductByKeywordSortby(null, null, filtersMap, 5, 1);
		CompanyDao companyDao = DaoFactory.getCompanyDao();
		companies = companyDao.getAllCompanies(CompanyType.VENDOR.getIndex(), 8, 1);
		merchantCompanies = companyDao.getAllCompanies(CompanyType.MERCHANT.getIndex(), 8, 1);
		return "success";
	}
}
