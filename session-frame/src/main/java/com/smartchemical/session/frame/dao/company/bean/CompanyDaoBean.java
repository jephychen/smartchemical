/**
 * 
 */
package com.smartchemical.session.frame.dao.company.bean;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.common.constant.SearchConstant;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.region.City;

/**
 * @author Jephy
 * 
 */
@Stateless
@Remote({ CompanyDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/CompanyDaoBean/remote")
public class CompanyDaoBean implements CompanyDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public boolean insertCompany(String companyName, String companyAlias,
			String companyFullName, int companyType, int companyStatus, String companySlogan, City companyCity,
			String companyAddress, String companyIcon, String companyUrl,
			double longitude, double latitude, String description, String longDescription, Timestamp lastModified) {
		try {
			Company company = new Company();
			company.setCompanyName(companyName);
			company.setCompanyAlias(companyAlias);
			company.setCompanyFullName(companyFullName);
			company.setCompanyType(companyType);
			company.setCompanyStatus(companyStatus);
			company.setCompanySlogan(companySlogan);
			company.setCompanyCity(companyCity);
			company.setCompanyAddress(companyAddress);
			company.setCompanyIcon(companyIcon);
			company.setCompanyUrl(companyUrl);
			company.setLongitude(longitude);
			company.setLatitude(latitude);
			company.setDescription(description);
			company.setLongDescription(longDescription);
			company.setLastModified(lastModified);
			em.persist(company);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeCompany(int companyId) {
		try {
			Company company = em.find(Company.class, companyId);
			em.remove(company);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editCompany(int companyId, String companyName,
			String companyAlias, String companyFullName, int companyType, int companyStatus, String companySlogan,
			City companyCity, String companyAddress, String companyIcon,
			String companyUrl, double longitude, double latitude,
			String descrition, String longDescription, Timestamp lastModified) {
		try {
			Company company = em.find(Company.class, companyId);
			company.setCompanyName(companyName);
			company.setCompanyAlias(companyAlias);
			company.setCompanyFullName(companyFullName);
			company.setCompanyType(companyType);
			company.setCompanyStatus(companyStatus);
			company.setCompanySlogan(companySlogan);
			company.setCompanyCity(companyCity);
			company.setCompanyAddress(companyAddress);
			company.setCompanyIcon(companyIcon);
			company.setCompanyUrl(companyUrl);
			company.setLongitude(longitude);
			company.setLatitude(latitude);
			company.setDescription(descrition);
			company.setLongDescription(longDescription);
			company.setLastModified(lastModified);
			em.merge(company);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean editCompany(Company company) {
		try {
			Company c = em.find(Company.class, company.getCompanyId());
			c.setCompanyName(company.getCompanyName());
			c.setCompanyAlias(company.getCompanyAlias());
			c.setCompanyFullName(company.getCompanyFullName());
			c.setCompanyType(company.getCompanyType());
			c.setCompanyStatus(company.getCompanyStatus());
			c.setCompanySlogan(company.getCompanySlogan());
			c.setCompanyCity(company.getCompanyCity());
			c.setCompanyAddress(company.getCompanyAddress());
			c.setCompanyIcon(company.getCompanyIcon());
			c.setCompanyUrl(company.getCompanyUrl());
			c.setLongitude(company.getLongitude());
			c.setLatitude(company.getLatitude());
			c.setDescription(company.getDescription());
			c.setLongDescription(company.getLongDescription());
			em.merge(company);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Company getCompanyById(int companyId) {
		Company company = em.find(Company.class, companyId);
		return company;
	}

	public List<Company> getAllCompanies() {
		try {
			Query query = em.createQuery("select c from Company c where c.companyStatus = 1");
			@SuppressWarnings("unchecked")
			List<Company> companys = query.getResultList();
			return companys;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Company> getAllCompaniesNotMerchant() {
		try {
			Query query = em.createQuery("select c from Company c where c.companyType = 1 and c.companyStatus = 1");
			@SuppressWarnings("unchecked")
			List<Company> companys = query.getResultList();
			return companys;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Company> getAllCompaniesMerchant() {
		try {
			Query query = em.createQuery("select c from Company c where c.companyType = 2 and c.companyStatus = 1");
			@SuppressWarnings("unchecked")
			List<Company> companys = query.getResultList();
			return companys;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getAllCompanyNames() {
		try {
			Query query = em.createQuery("select c.companyName from Company c where c.companyStatus = 1");
			@SuppressWarnings("unchecked")
			List<String> companyNames = query.getResultList();
			return companyNames;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Company getCompanyByName(String companyName) {
		try {
			Query query = em
					.createQuery("select c from Company c where c.companyName = ?1 and c.companyStatus = 1");
			query.setParameter(1, companyName);
			@SuppressWarnings("unchecked")
			List<Company> companys = query.getResultList();
			return companys.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Company> getAllCompanies(int companyType, int pageSize, int pageIndex) {
		try {
			Query query = em.createNativeQuery(
					"select * from Company where companyType = ?1 and companyStatus = 1", Company.class);
			query.setParameter(1, companyType);
			@SuppressWarnings("unchecked")
			List<Company> companies = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			if (companies == null || companies.isEmpty()){
				return null;
			}
			return companies;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getAllCompaniesCount(String keyword, String companyType, int companyStatus) {
		if (keyword == null){
			keyword = "";
		}
		try {
			Query query = em.createQuery(
					"select count(c.companyId) from Company c where c.companyFullName like '%" + keyword + "%' and c.companyType in " + companyType
					+ " and c.companyStatus = " + companyStatus);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<Company> getAllCompanies(String keyword, String companyType, int companyStatus, String sortby, int pageSize, int pageIndex) {
		if (keyword == null){
			keyword = "";
		}
		try {
			Query query = em.createNativeQuery(
					"select * from Company where companyFullName like '%" + keyword + "%' and companyType in " + companyType
					+ " and companyStatus = " + companyStatus + makeOrderby(sortby), Company.class);
			@SuppressWarnings("unchecked")
			List<Company> companies = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			if (companies == null || companies.isEmpty()){
				return null;
			}
			return companies;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String makeOrderby(String sortby){
		StringBuilder orderbySql = new StringBuilder();
		if (sortby == null || sortby.isEmpty()){
			return orderbySql.toString();
		}
		if (sortby.equals(SearchConstant.SORTBY_LASTMODIFIED_DESC)){
			orderbySql.append(" order by lastModified DESC");
		}
		else if (sortby.equals(SearchConstant.SORTBY_ID_DESC)){
			orderbySql.append(" order by companyId DESC");
		}
		return orderbySql.toString();
	}

}
