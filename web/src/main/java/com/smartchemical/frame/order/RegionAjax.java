/**
 * 
 */
package com.smartchemical.frame.order;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.dao.product.ProductTypeDao;
import com.smartchemical.api.frame.dao.region.CityDao;
import com.smartchemical.api.frame.dao.region.ProvinceDao;
import com.smartchemical.api.frame.dao.warehouse.WarehouseDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.product.ProductType;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.region.Province;
import com.smartchemical.entities.frame.warehouse.Warehouse;

/**
 * @author Jephy
 *
 */
@SuppressWarnings("deprecation")
public class RegionAjax implements Action {
	
	private int regionId;
	
	private int provinceId;
	
	private int cityId;
	
	private int productCategoryId;
	
	private InputStream inputStream;
	
	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String getProvince() throws Exception {
		ProvinceDao provinceDao = DaoFactory.getProvinceDao();
		List<Province> provinces = provinceDao.getAllProvincesByRegion(regionId);
		setInputStream(new StringBufferInputStream(parseProvince(provinces)));
		return SUCCESS;
	}
	
	public String getCity() throws Exception {
		CityDao cityDao = DaoFactory.getCityDao();
		List<City> cities = cityDao.getAllCitiesByProvince(provinceId);
		setInputStream(new StringBufferInputStream(parseCities(cities)));
		return SUCCESS;
	}
	
	public String getWarehouse() throws Exception {
		WarehouseDao warehouseDao = DaoFactory.getWarehouseDao();
		List<Warehouse> whouses = warehouseDao.getAllWarehousesByCityId(cityId);
		setInputStream(new StringBufferInputStream(parseWarehouses(whouses)));
		return SUCCESS;
	}
	
	public String getProductType() throws Exception {
		ProductTypeDao ptDao = DaoFactory.getProductTypeDao();
		List<ProductType> types = ptDao.getAllProductTypesByCategory(productCategoryId);
		setInputStream(new StringBufferInputStream(parseProductTypes(types)));
		return SUCCESS;
	}
	
	private String parseProvince(List<Province> provinces) throws Exception {
		if (provinces == null || provinces.isEmpty()){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Province province : provinces){
			sb.append(province.getProvinceId() + ":" + province.getProvinceName() + ",");
		}
		String result = sb.substring(0, sb.length() - 1);
		String resultWrapper = new String(result.getBytes("UTF-8"), "iso-8859-1");
		return resultWrapper;
	}
	
	private String parseCities(List<City> cities) throws Exception {
		if (cities == null || cities.isEmpty()){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (City city : cities){
			sb.append(city.getCityId() + ":" + city.getCityName() + ",");
		}
		String result = sb.substring(0, sb.length() - 1);
		String resultWrapper = new String(result.getBytes("UTF-8"), "iso-8859-1");
		return resultWrapper;
	}
	
	private String parseWarehouses(List<Warehouse> whouses) throws Exception {
		if (whouses == null || whouses.isEmpty()){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Warehouse house : whouses){
			sb.append(house.getWarehouseId() + ":" + house.getWarehouseName() + ",");
		}
		String result = sb.substring(0, sb.length() - 1);
		String resultWrapper = new String(result.getBytes("UTF-8"), "iso-8859-1");
		return resultWrapper;
	}
	
	private String parseProductTypes(List<ProductType> types) throws Exception {
		if (types == null || types.isEmpty()){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (ProductType type : types){
			sb.append(type.getProductTypeId() + ":" + type.getProductTypeName() + ",");
		}
		String result = sb.substring(0, sb.length() - 1);
		String resultWrapper = new String(result.getBytes("UTF-8"), "iso-8859-1");
		return resultWrapper;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
