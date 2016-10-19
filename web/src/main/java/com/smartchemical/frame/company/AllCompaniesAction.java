/**
 * 
 */
package com.smartchemical.frame.company;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.company.Company;

/**
 * @author Jephy
 *
 */
public class AllCompaniesAction implements Action {

	private List<Company> companies;
	
	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public String execute() throws Exception {
		CompanyDao companyDao = DaoFactory.getCompanyDao();
		companies = companyDao.getAllCompanies();
		return SUCCESS;
	}

}
