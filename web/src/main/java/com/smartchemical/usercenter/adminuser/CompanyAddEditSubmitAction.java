/**
 * 
 */
package com.smartchemical.usercenter.adminuser;

import java.sql.Timestamp;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.api.frame.dao.region.CityDao;
import com.smartchemical.common.constant.PathConstant;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.CopyFileUtil;
import com.smartchemical.common.util.StringUtil;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.region.City;

/**
 * @author Jephy
 *
 */
public class CompanyAddEditSubmitAction implements Action {
	
	private int queryType;
	
	private String returnAction;
	
	private int companyId;
	
	private String companyName;
	
	private String companyFullName;
	
	private int companyType;
	
	private String companyMainPage;
	
	private String companyShortDesc;
	
	private int companyCityId;
	
	private String companyAddress;
	
	private double longitude;
	
	private double latitude;
	
	private String companySlogan;
	
	private String companyPicPath;
	
	private String companyLongDesc;

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public String getReturnAction() {
		return returnAction;
	}

	public void setReturnAction(String returnAction) {
		this.returnAction = returnAction;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyFullName() {
		return companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	public int getCompanyType() {
		return companyType;
	}

	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}

	public String getCompanyMainPage() {
		return companyMainPage;
	}

	public void setCompanyMainPage(String companyMainPage) {
		this.companyMainPage = companyMainPage;
	}

	public String getCompanyShortDesc() {
		return companyShortDesc;
	}

	public void setCompanyShortDesc(String companyShortDesc) {
		this.companyShortDesc = companyShortDesc;
	}

	public int getCompanyCityId() {
		return companyCityId;
	}

	public void setCompanyCityId(int companyCityId) {
		this.companyCityId = companyCityId;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getCompanySlogan() {
		return companySlogan;
	}

	public void setCompanySlogan(String companySlogan) {
		this.companySlogan = companySlogan;
	}

	public String getCompanyPicPath() {
		return companyPicPath;
	}

	public void setCompanyPicPath(String companyPicPath) {
		this.companyPicPath = companyPicPath;
	}

	public String getCompanyLongDesc() {
		return companyLongDesc;
	}

	public void setCompanyLongDesc(String companyLongDesc) {
		this.companyLongDesc = companyLongDesc;
	}
	
	public String editSave() throws Exception {
		if (queryType == 20){
			returnAction = "allCompanies";
		}
		else if (queryType == 21){
			returnAction = "supplierCompanies";
		}
		else if (queryType == 22){
			returnAction = "merchantCompanies";
		}
		CityDao cityDao = DaoFactory.getCityDao();
		City _companyCity = cityDao.getCityById(companyCityId);
		if (_companyCity == null){
			return ERROR;
		}
		String formalPath = copyPicToImg(companyPicPath);
		CompanyDao cDao = DaoFactory.getCompanyDao();
		Company c = cDao.getCompanyById(companyId);
		companyLongDesc = StringUtil.trimLongDesc(companyLongDesc);
		Timestamp lastmodifed = new Timestamp(System.currentTimeMillis());
		if (c == null){
			cDao.insertCompany(companyName, companyName, companyFullName, companyType, 1, 
					companySlogan, _companyCity, companyAddress, formalPath, companyMainPage, 
					longitude, latitude, companyShortDesc, companyLongDesc, lastmodifed);
		}
		else
		{
			c.setCompanyName(companyName);
			c.setCompanyAlias(companyName);
			c.setCompanyFullName(companyFullName);
			c.setCompanyType(companyType);
//			c.setCompanyStatus(1);
			c.setCompanySlogan(companySlogan);
			c.setCompanyCity(_companyCity);
			c.setCompanyAddress(companyAddress);
			c.setCompanyIcon(formalPath);
			c.setCompanyUrl(companyMainPage);
			c.setLongitude(longitude);
			c.setLatitude(latitude);
			c.setDescription(companyShortDesc);
			c.setLongDescription(companyLongDesc);
			c.setLastModified(lastmodifed);
			cDao.editCompany(c);
		}
		return SUCCESS;
	}
	
	private String getFileName(String tempPath){
		int index = tempPath.lastIndexOf("/");
		if (index == -1){
			return tempPath;
		}
		return tempPath.substring(index + 1);
	}
	
	private String copyPicToImg(String tempPath) {
		String filename = getFileName(tempPath);
		String srcFile = PathConstant.COMPANY_PIC_TEMP_FOLDER + filename;
		String destFile = PathConstant.COMPANY_PIC_SAVE_FOLDER + filename;
		String srcFileAbsolute = ServletActionContext.getServletContext().getRealPath(srcFile);
		String destFileAbsolute = ServletActionContext.getServletContext().getRealPath(destFile);
		if (CopyFileUtil.copyFile(srcFileAbsolute, destFileAbsolute, true)){
			return PathConstant.PROJECTNAME + destFile;
		}
		else{
			return tempPath;
		}
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
