/**
 * 
 */
package com.smartchemical.usercenter.adminuser;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.common.constant.SearchConstant;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.entities.frame.company.Company;

/**
 * @author Jephy
 *
 */
public class CompanyManagementAction implements Action {
	
	/**
	 * 基础信息
	 * */
	private int queryType = 20;
	
	private static final int PAGE_SIZE = 5;
	
	private static final String ACTION_NAME = "AdminCompanyManagement";
	
	private int currentPage = 1;
	
	private Paginator paginator;
	
	/**
	 * 页面扩展信息
	 * */
	private String keyword;
	
	private List<Company> companies;
	
	private int companyId;
	
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
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	private void setPaginator(int totalRecordCount){
		paginator = new Paginator(totalRecordCount, currentPage, PAGE_SIZE, ACTION_NAME);
		currentPage = paginator.getCurrentPage();
	}
	
	private void setMerchantCompaniesPaging() throws Exception{
		CompanyDao cDao = DaoFactory.getCompanyDao();
		int totalCount = cDao.getAllCompaniesCount(keyword, "(2)", 1);
		setPaginator(totalCount);
		companies = cDao.getAllCompanies(keyword, "(2)", 1, SearchConstant.SORTBY_ID_DESC, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	private void setSupplierCompaniesPaging() throws Exception{
		CompanyDao cDao = DaoFactory.getCompanyDao();
		int totalCount = cDao.getAllCompaniesCount(keyword, "(1)", 1);
		setPaginator(totalCount);
		companies = cDao.getAllCompanies(keyword, "(1)", 1, SearchConstant.SORTBY_ID_DESC, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	private void setAllCompaniesPaging() throws Exception{
		CompanyDao cDao = DaoFactory.getCompanyDao();
		int totalCount = cDao.getAllCompaniesCount(keyword, "(1, 2)", 1);
		setPaginator(totalCount);
		companies = cDao.getAllCompanies(keyword, "(1, 2)", 1, SearchConstant.SORTBY_ID_DESC, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	public String merchantCompanies() throws Exception{
		queryType = 22;
		setMerchantCompaniesPaging();
		return SUCCESS;
	}
	
	public String supplierCompanies() throws Exception{
		queryType = 21;
		setSupplierCompaniesPaging();
		return SUCCESS;
	}

	public String allCompanies() throws Exception{
		queryType = 20;
		setAllCompaniesPaging();
		return SUCCESS;
	}
	
	public String removeCompany() throws Exception{
		CompanyDao cDao = DaoFactory.getCompanyDao();
		Company company = cDao.getCompanyById(companyId);
		if (company == null){
			return ERROR;
		}
		company.setCompanyStatus(0);
		cDao.editCompany(company);
		if (queryType == 20){
			setAllCompaniesPaging();
		}
		else if (queryType == 21){
			setSupplierCompaniesPaging();
		}
		else if (queryType == 22){
			setMerchantCompaniesPaging();
		}
		else {
			setAllCompaniesPaging();
		}
		return SUCCESS;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
