/**
 * 
 */
package com.smartchemical.frame.company;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.api.frame.dao.company.CompanyType;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.product.Product;

/**
 * @author Jephy
 *
 */
public class CompanyAction implements Action {
	
	private static final int PAGE_SIZE = 5;
	
	private int companyId;
	
	private Company company;
	
	private List<Product> products;
	
	private Paginator paginator;
	
	private int currentPage = 1;
	
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String execute() throws Exception {
		CompanyDao companyDao = DaoFactory.getCompanyDao();
		company = companyDao.getCompanyById(companyId);
		ProductDao productDao = DaoFactory.getProductDao();
		int productsCount = 0;
		if (company.getCompanyType() == CompanyType.VENDOR.getIndex()){
			productsCount = productDao.getProductsCountByCompanyId(companyId);
		}
		else if (company.getCompanyType() == CompanyType.MERCHANT.getIndex()){
			productsCount = productDao.getProductsCountByMerchantCompanyId(companyId);
		}
		paginator = new Paginator(productsCount, currentPage, PAGE_SIZE);
		currentPage = paginator.getCurrentPage();
		if (company.getCompanyType() == CompanyType.VENDOR.getIndex()){
			products = productDao.getProductByCompanyIdPaging(companyId, PAGE_SIZE, currentPage);
		}
		else if (company.getCompanyType() == CompanyType.MERCHANT.getIndex()){
			products = productDao.getProductByMerchantCompanyIdPaging(companyId, PAGE_SIZE, currentPage);
		}
		return SUCCESS;
	}

}
