/**
 * 
 */
package com.smartchemical.usercenter.adminuser;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.api.frame.dao.region.CityDao;
import com.smartchemical.api.frame.dao.region.ProvinceDao;
import com.smartchemical.api.frame.dao.region.RegionDao;
import com.smartchemical.api.frame.dao.user.RoleDao;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.common.constant.PathConstant;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.CopyFileUtil;
import com.smartchemical.common.util.DesUtil;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.region.Province;
import com.smartchemical.entities.frame.region.Region;
import com.smartchemical.entities.frame.user.Role;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class UserEditAction implements Action {
	
	private int queryType;
	
	private int userId;
	
	private ScUser currentUser;
	
	private List<Role> roles;
	
	private List<Region> regions;
	
	private List<Province> provinces;
	
	private List<City> cities;
	
	private List<Company> supplierCompanies;
	
	private List<Company> merchantCompanies;
	
	private List<String> companyLicensePics = new LinkedList<String>();

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ScUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(ScUser currentUser) {
		this.currentUser = currentUser;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	public List<Company> getSupplierCompanies() {
		return supplierCompanies;
	}

	public void setSupplierCompanies(List<Company> supplierCompanies) {
		this.supplierCompanies = supplierCompanies;
	}

	public List<Company> getMerchantCompanies() {
		return merchantCompanies;
	}

	public void setMerchantCompanies(List<Company> merchantCompanies) {
		this.merchantCompanies = merchantCompanies;
	}

	public List<String> getCompanyLicensePics() {
		return companyLicensePics;
	}

	public void setCompanyLicensePics(List<String> companyLicensePics) {
		this.companyLicensePics = companyLicensePics;
	}

	public String addUser() throws Exception {
		setDropdowns();
		return SUCCESS;
	}
	
	public String execute() throws Exception {
		setDropdowns();
		ScUserDao userDao = DaoFactory.getScUserDao();
		currentUser = userDao.getUserById(userId);
		currentUser.setPassword(DesUtil.decrypt(currentUser.getPassword(), "matrix1986"));
		ProvinceDao provinceDao = DaoFactory.getProvinceDao();
		provinces = provinceDao.getAllProvincesByRegion(currentUser.getCity().getProvince().getRegion().getRegionId());
		CityDao cDao = DaoFactory.getCityDao();
		cities = cDao.getAllCitiesByProvince(currentUser.getCity().getProvince().getProvinceId());
		if (currentUser.getRole().getRoleId() == 3){
			CompanyDao companyDao = DaoFactory.getCompanyDao();
			supplierCompanies = companyDao.getAllCompaniesNotMerchant();
		}
		else{
			CompanyDao companyDao = DaoFactory.getCompanyDao();
			merchantCompanies = companyDao.getAllCompaniesMerchant();
		}
		if (currentUser.getCompanyLicenseStatus() == 1 || currentUser.getCompanyLicenseStatus() == 2){
			getCompanyLicensePicPath(currentUser);
		}
		return SUCCESS;
	}
	
	private void getCompanyLicensePicPath(ScUser user){
		String dirPath = ServletActionContext.getServletContext().getRealPath(PathConstant.COMPANY_LICENSE_SAVE_FOLDER + CopyFileUtil.makeCompanyLicensePath(user));
		File licenseDir = new File(dirPath);
		if (!licenseDir.isDirectory()){
			return;
		}
		for (File file : licenseDir.listFiles()){
			if (file.isFile()){
				companyLicensePics.add(PathConstant.PROJECTNAME + PathConstant.COMPANY_LICENSE_SAVE_FOLDER + CopyFileUtil.makeCompanyLicensePath(user) + file.getName());
			}
		}
	}
	
	private void setDropdowns() throws NamingException{
		RegionDao regionDao = DaoFactory.getRegionDao();
		regions = regionDao.getAllRegions();
		RoleDao roleDao = DaoFactory.getRoleDao();
		roles = roleDao.getAllRoles();
	}

}
