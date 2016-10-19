/**
 * 
 */
package com.smartchemical.usercenter.merchantuser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.company.BrandDao;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.entities.frame.company.Brand;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class ProductManagementAction implements Action {
	
	private String brandIdSelected = "0";
	
	private int productId;
	
	private String pageTitle = "贸易商后台";
	
	private static final String ACTION_NAME = "MerchantProductManagement";
	
	private String keyword;
	
	private int queryType = 0;
	
	private int currentPage = 1;
	
	private List<Brand> brands;
	
	private List<Product> products;
	
	private static final int PAGE_SIZE = 5;
	
	private Paginator paginator;
	
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

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	private void setPaginator(int totalRecordCount){
		paginator = new Paginator(totalRecordCount, currentPage, PAGE_SIZE, ACTION_NAME);
		currentPage = paginator.getCurrentPage();
	}
	
	private void setBrandsDropdown() throws Exception {
		BrandDao brandDao = DaoFactory.getBrandDao();
		brands = brandDao.getAllBrands();
	}
	
	private void setAllProductsPaging() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ProductDao productDao = DaoFactory.getProductDao();
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> statusFilterItem = new LinkedList<String>();
		List<String> merchantCompanyFilterItem = new LinkedList<String>();
		statusFilterItem.add("1");
		merchantCompanyFilterItem.add(String.valueOf(user.getCompany().getCompanyId()));
		filtersMap.put("productStatus", statusFilterItem);
		filtersMap.put("merchantCompanyId", merchantCompanyFilterItem);
		if (!brandIdSelected.equals("0")){
			List<String> filterItem = new LinkedList<String>();
			filterItem.add(brandIdSelected);
			filtersMap.put("brandId", filterItem);
			int totalCount = productDao.getProductCountByKeyword(keyword, filtersMap);
			setPaginator(totalCount);
			setBrandsDropdown();
			products = productDao.getPagingProductByKeyword(keyword, filtersMap, PAGE_SIZE, paginator.getCurrentPage());
		}
		else {
			int totalCount = productDao.getProductCountByKeyword(keyword, filtersMap);
			setPaginator(totalCount);
			setBrandsDropdown();
			products = productDao.getPagingProductByKeyword(keyword, filtersMap, PAGE_SIZE, paginator.getCurrentPage());
		}
	}
	
	private void setDisabledProductsPaging() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ProductDao productDao = DaoFactory.getProductDao();
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> statusFilterItem = new LinkedList<String>();
		List<String> merchantCompanyFilterItem = new LinkedList<String>();
		statusFilterItem.add("0");
		merchantCompanyFilterItem.add(String.valueOf(user.getCompany().getCompanyId()));
		filtersMap.put("productStatus", statusFilterItem);
		filtersMap.put("merchantCompanyId", merchantCompanyFilterItem);
		if (!brandIdSelected.equals("0")){
			List<String> filterItem = new LinkedList<String>();
			filterItem.add(brandIdSelected);
			filtersMap.put("brandId", filterItem);
		}
		int totalCount = productDao.getProductCountByKeyword(keyword, filtersMap);
		setPaginator(totalCount);
		setBrandsDropdown();
		products = productDao.getPagingProductByKeyword(keyword, filtersMap, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	public String removeProduct() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ProductDao productDao = DaoFactory.getProductDao();
		Product product = productDao.getProductById(productId);
		if (product == null || product.getMerchantCompany().getCompanyId() != user.getCompany().getCompanyId()){
			return ERROR;
		}
		product.setProductStatus(0);
		productDao.editProduct(product);
		if (queryType == 0){
			setAllProductsPaging();
		}
		else if (queryType == 3){
			setDisabledProductsPaging();
		}
		else {
			setAllProductsPaging();
		}
		return SUCCESS;
	}
	
	public String restoreProduct() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ProductDao productDao = DaoFactory.getProductDao();
		Product product = productDao.getProductById(productId);
		if (product == null || product.getMerchantCompany().getCompanyId() != user.getCompany().getCompanyId()){
			return ERROR;
		}
		product.setProductStatus(1);
		productDao.editProduct(product);
		if (queryType == 0){
			setAllProductsPaging();
		}
		else if (queryType == 3){
			setDisabledProductsPaging();
		}
		else {
			setAllProductsPaging();
		}
		return SUCCESS;
	}
	
	public String allProducts() throws Exception{
		queryType = 0;
		setAllProductsPaging();
		return SUCCESS;
	}
	
	public String disabledProducts() throws Exception{
		queryType = 3;
		setDisabledProductsPaging();
		return SUCCESS;
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}

}
