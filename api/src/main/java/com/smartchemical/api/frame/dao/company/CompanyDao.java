/**
 * 
 */
package com.smartchemical.api.frame.dao.company;

import java.sql.Timestamp;
import java.util.List;

import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.region.City;

/**
 * @author Jephy
 * 
 */
public interface CompanyDao {
	public boolean insertCompany(String companyName, String companyAlias,
			String companyFullName, int companyType, int companyStatus, String companySlogan, City companyCity,
			String companyAddress, String companyIcon, String companyUrl,
			double longitude, double latitude, String description, String longDescription, Timestamp lastModified);

	public boolean removeCompany(int companyId);

	public boolean editCompany(int companyId, String companyName,
			String companyAlias, String companyFullName, int companyType, int companyStatus,String companySlogan,
			City companyCity, String companyAddress, String companyIcon,
			String companyUrl, double longitude, double latitude,
			String description, String longDescription, Timestamp lastModified);
	
	public boolean editCompany(Company company);

	public Company getCompanyById(int companyId);

	public List<Company> getAllCompanies();
	
	public List<Company> getAllCompaniesNotMerchant();
	
	public List<Company> getAllCompaniesMerchant();
	
	public List<Company> getAllCompanies(int companyType, int pageSize, int pageIndex);
	
	public List<Company> getAllCompanies(String keyword, String companyType, int companyStatus, String sortby, int pageSize, int pageIndex);
	
	public int getAllCompaniesCount(String keyword, String companyType, int companyStatus);

	public List<String> getAllCompanyNames();

	public Company getCompanyByName(String companyName);
}
