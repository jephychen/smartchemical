/**
 * 
 */
package com.smartchemical.usercenter.adminuser;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.api.frame.dao.region.CityDao;
import com.smartchemical.api.frame.dao.region.ProvinceDao;
import com.smartchemical.api.frame.dao.region.RegionDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.StringUtil;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.region.Province;
import com.smartchemical.entities.frame.region.Region;

/**
 * @author Jephy
 *
 */
public class CompanyEditAction implements Action {

	private int queryType;
	
	private int companyId;
	
	private Company company;
	
	private List<Region> regions;
	
	private List<Province> provinces;
	
	private List<City> cities;
	
	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

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

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<Province> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	public String addCompany() throws Exception {
		RegionDao regionDao = DaoFactory.getRegionDao();
		regions = regionDao.getAllRegions();
		return SUCCESS;
	}

	public String execute() throws Exception {
		CompanyDao cDao = DaoFactory.getCompanyDao();
		company = cDao.getCompanyById(companyId);
		if (company == null){
			return ERROR;
		}
		company.setLongDescription(StringUtil.trimLongDesc(company.getLongDescription()));
		RegionDao regionDao = DaoFactory.getRegionDao();
		ProvinceDao provinceDao = DaoFactory.getProvinceDao();
		CityDao cityDao = DaoFactory.getCityDao();
		regions = regionDao.getAllRegions();
		provinces = provinceDao.getAllProvincesByRegion(company.getCompanyCity().getProvince().getRegion().getRegionId());
		cities = cityDao.getAllCitiesByProvince(company.getCompanyCity().getProvince().getProvinceId());
		return SUCCESS;
	}

}
