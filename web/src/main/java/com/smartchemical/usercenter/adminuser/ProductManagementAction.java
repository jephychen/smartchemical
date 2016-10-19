/**
 * 
 */
package com.smartchemical.usercenter.adminuser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.dao.company.BrandDao;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.entities.frame.company.Brand;
import com.smartchemical.entities.frame.product.Product;

/**
 * @author Jephy
 *
 */
public class ProductManagementAction implements Action {
	
	private String keyword;
	
	private String companyIdSelected = "0";
	
	private String brandIdSelected = "0";
	
	private int productId;
	
	private List<Brand> brands;
	
	private List<Product> products;
	
	private static final int PAGE_SIZE = 5;
	
	private static final String ACTION_NAME = "AdminProductManagement";
	
	private int queryType = 10;
	
	private int currentPage = 1;
	
	private Paginator paginator;
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCompanyIdSelected() {
		return companyIdSelected;
	}

	public void setCompanyIdSelected(String companyIdSelected) {
		this.companyIdSelected = companyIdSelected;
	}

	public String getBrandIdSelected() {
		return brandIdSelected;
	}

	public void setBrandIdSelected(String brandIdSelected) {
		this.brandIdSelected = brandIdSelected;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}
	
	private void setPaginator(int totalRecordCount){
		paginator = new Paginator(totalRecordCount, currentPage, PAGE_SIZE, ACTION_NAME);
		currentPage = paginator.getCurrentPage();
	}
	
	private void setCompanyDropdown() throws Exception {
		BrandDao bDao = DaoFactory.getBrandDao();
		brands = bDao.getAllBrands();
	}
	
	private void setAllProductsPaging() throws Exception{
		ProductDao productDao = DaoFactory.getProductDao();
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> statusFilterItem = new LinkedList<String>();
		statusFilterItem.add("1");
		filtersMap.put("productStatus", statusFilterItem);
		if (!brandIdSelected.equals("0")){
			List<String> brandFilterItem = new LinkedList<String>();
			brandFilterItem.add(brandIdSelected);
			filtersMap.put("brandId", brandFilterItem);
			int totalCount = productDao.getProductCountByKeyword(keyword, filtersMap);
			setPaginator(totalCount);
			setCompanyDropdown();
			products = productDao.getPagingProductByKeyword(keyword, filtersMap, PAGE_SIZE, paginator.getCurrentPage());
		}
		else {
			int totalCount = productDao.getProductCountByKeyword(keyword, filtersMap);
			setPaginator(totalCount);
			setCompanyDropdown();
			products = productDao.getPagingProductByKeyword(keyword, filtersMap, PAGE_SIZE, paginator.getCurrentPage());
		}
	}
	
	private void setSupplierProductsPaging() throws Exception{
		ProductDao productDao = DaoFactory.getProductDao();
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> filterItem = new LinkedList<String>();
		filterItem.add("isnull");
		filtersMap.put("merchantcompany", filterItem);
		List<String> statusFilterItem = new LinkedList<String>();
		statusFilterItem.add("1");
		filtersMap.put("productStatus", statusFilterItem);
		if (!brandIdSelected.equals("0")){
			List<String> brandFilterItem = new LinkedList<String>();
			brandFilterItem.add(brandIdSelected);
			filtersMap.put("brandId", brandFilterItem);
		}
		int totalCount = productDao.getProductCountByKeyword(keyword, filtersMap);
		setPaginator(totalCount);
		setCompanyDropdown();
		products = productDao.getPagingProductByKeyword(keyword, filtersMap, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	private void setMerchantProductsPaging() throws Exception{
		ProductDao productDao = DaoFactory.getProductDao();
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> filterItem = new LinkedList<String>();
		filterItem.add("notnull");
		filtersMap.put("merchantcompany", filterItem);
		List<String> statusFilterItem = new LinkedList<String>();
		statusFilterItem.add("1");
		filtersMap.put("productStatus", statusFilterItem);
		if (!brandIdSelected.equals("0")){
			List<String> brandFilterItem = new LinkedList<String>();
			brandFilterItem.add(brandIdSelected);
			filtersMap.put("brandId", brandFilterItem);
		}
		int totalCount = productDao.getProductCountByKeyword(keyword, filtersMap);
		setPaginator(totalCount);
		BrandDao bDao = DaoFactory.getBrandDao();
		brands = bDao.getAllBrands();
		products = productDao.getPagingProductByKeyword(keyword, filtersMap, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	private void setDisabledProductsPaging() throws Exception{
		ProductDao productDao = DaoFactory.getProductDao();
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> statusFilterItem = new LinkedList<String>();
		statusFilterItem.add("0");
		filtersMap.put("productStatus", statusFilterItem);
		if (!brandIdSelected.equals("0")){
			List<String> brandFilterItem = new LinkedList<String>();
			brandFilterItem.add(brandIdSelected);
			filtersMap.put("brandId", brandFilterItem);
		}
		int totalCount = productDao.getProductCountByKeyword(keyword, filtersMap);
		setPaginator(totalCount);
		BrandDao bDao = DaoFactory.getBrandDao();
		brands = bDao.getAllBrands();
		products = productDao.getPagingProductByKeyword(keyword, filtersMap, PAGE_SIZE, paginator.getCurrentPage());
	}

	public String allProducts() throws Exception{
		queryType = 10;
		setAllProductsPaging();
		return SUCCESS;
	}
	
	public String supplierProducts() throws Exception{
		queryType = 11;
		setSupplierProductsPaging();
		return SUCCESS;
	}
	
	public String merchantProducts() throws Exception{
		queryType = 12;
		setMerchantProductsPaging();
		return SUCCESS;
	}
	
	public String disabledProducts() throws Exception{
		queryType = 13;
		setDisabledProductsPaging();
		return SUCCESS;
	}
	
	public String removeProduct() throws Exception{
		ProductDao productDao = DaoFactory.getProductDao();
		Product product = productDao.getProductById(productId);
		if (product == null){
			return ERROR;
		}
		product.setProductStatus(0);
		productDao.editProduct(product);
		if (queryType == 10){
			setAllProductsPaging();
		}
		else if (queryType == 11){
			setSupplierProductsPaging();
		}
		else if (queryType == 12){
			setMerchantProductsPaging();
		}
		else if (queryType == 13){
			setDisabledProductsPaging();
		}
		else {
			setAllProductsPaging();
		}
		return SUCCESS;
	}
	
	public String restoreProduct() throws Exception{
		ProductDao productDao = DaoFactory.getProductDao();
		Product product = productDao.getProductById(productId);
		if (product == null){
			return ERROR;
		}
		product.setProductStatus(1);
		productDao.editProduct(product);
		if (queryType == 10){
			setAllProductsPaging();
		}
		else if (queryType == 11){
			setSupplierProductsPaging();
		}
		else if (queryType == 12){
			setMerchantProductsPaging();
		}
		else if (queryType == 13){
			setDisabledProductsPaging();
		}
		else {
			setAllProductsPaging();
		}
		return SUCCESS;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
