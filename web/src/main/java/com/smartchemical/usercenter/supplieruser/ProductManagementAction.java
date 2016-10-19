/**
 * 
 */
package com.smartchemical.usercenter.supplieruser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class ProductManagementAction implements Action {
	
	private String companyIdSelected = "0";
	
	private int productId;
	
	private String pageTitle = "供应商后台";
	
	private static final String ACTION_NAME = "SupplierProductManagement";
	
	private String keyword;
	
	private int queryType = 0;
	
	private int currentPage = 1;
	
	private List<Company> companies;
	
	private List<Product> products;
	
	private static final int PAGE_SIZE = 5;
	
	private Paginator paginator;
	
	public String getCompanyIdSelected() {
		return companyIdSelected;
	}

	public void setCompanyIdSelected(String companyIdSelected) {
		this.companyIdSelected = companyIdSelected;
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

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
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
	
	private void setCompanyDropdown() throws Exception {
		CompanyDao companyDao = DaoFactory.getCompanyDao();
		companies = companyDao.getAllCompaniesNotMerchant();
	}
	
	private void setAllProductsPaging() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ProductDao productDao = DaoFactory.getProductDao();
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> statusFilterItem = new LinkedList<String>();
		List<String> companyFilterItem = new LinkedList<String>();
		List<String> isMerchantProductFilterItem = new LinkedList<String>();
		statusFilterItem.add("1");
		companyFilterItem.add(String.valueOf(user.getCompany().getCompanyId()));
		isMerchantProductFilterItem.add("false");
		filtersMap.put("productStatus", statusFilterItem);
		filtersMap.put("companyId", companyFilterItem);
		filtersMap.put("isMerchantProduct", isMerchantProductFilterItem);
		int totalCount = productDao.getProductCountByKeyword(keyword, filtersMap);
		setPaginator(totalCount);
		setCompanyDropdown();
		products = productDao.getPagingProductByKeyword(keyword, filtersMap, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	private void setDisabledProductsPaging() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ProductDao productDao = DaoFactory.getProductDao();
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> statusFilterItem = new LinkedList<String>();
		List<String> companyFilterItem = new LinkedList<String>();
		List<String> isMerchantProductFilterItem = new LinkedList<String>();
		statusFilterItem.add("0");
		companyFilterItem.add(String.valueOf(user.getCompany().getCompanyId()));
		isMerchantProductFilterItem.add("false");
		filtersMap.put("productStatus", statusFilterItem);
		filtersMap.put("companyId", companyFilterItem);
		filtersMap.put("isMerchantProduct", isMerchantProductFilterItem);
		int totalCount = productDao.getProductCountByKeyword(keyword, filtersMap);
		setPaginator(totalCount);
		CompanyDao companyDao = DaoFactory.getCompanyDao();
		companies = companyDao.getAllCompanies();
		products = productDao.getPagingProductByKeyword(keyword, filtersMap, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	public String removeProduct() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ProductDao productDao = DaoFactory.getProductDao();
		Product product = productDao.getProductById(productId);
		if (product == null || product.getCompany().getCompanyId() != user.getCompany().getCompanyId()){
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
		if (product == null || product.getCompany().getCompanyId() != user.getCompany().getCompanyId()){
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
