/**
 * 
 */
package com.smartchemical.usercenter.merchantuser;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.dao.company.BrandDao;
import com.smartchemical.api.frame.dao.product.ProductCategoryDao;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.api.frame.dao.product.ProductTypeDao;
import com.smartchemical.api.frame.dao.region.CityDao;
import com.smartchemical.api.frame.dao.region.ProvinceDao;
import com.smartchemical.api.frame.dao.region.RegionDao;
import com.smartchemical.api.frame.dao.unit.UnitDao;
import com.smartchemical.api.frame.dao.warehouse.WarehouseDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.entities.frame.company.Brand;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.product.ProductCategory;
import com.smartchemical.entities.frame.product.ProductType;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.region.Province;
import com.smartchemical.entities.frame.region.Region;
import com.smartchemical.entities.frame.unit.Unit;
import com.smartchemical.entities.frame.warehouse.Warehouse;

/**
 * @author Jephy
 *
 */
public class ProductEditAction implements Action {
	
	private int queryType;
	
	private int productId;
	
	private Product currentProduct;
	
	private List<Brand> brands;
	
	private List<ProductCategory> productCategories;
	
	private List<ProductType> productTypes;
	
	private List<Region> regions;
	
	private List<Province> provinces;
	
	private List<City> cities;
	
	private List<Unit> priceUnits;
	
	private List<Unit> quantityUnits;
	
	private List<Warehouse> warehouses;
	
	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Product getCurrentProduct() {
		return currentProduct;
	}

	public void setCurrentProduct(Product currentProduct) {
		this.currentProduct = currentProduct;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	public List<ProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
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

	public List<Unit> getPriceUnits() {
		return priceUnits;
	}

	public void setPriceUnits(List<Unit> priceUnits) {
		this.priceUnits = priceUnits;
	}

	public List<Unit> getQuantityUnits() {
		return quantityUnits;
	}

	public void setQuantityUnits(List<Unit> quantityUnits) {
		this.quantityUnits = quantityUnits;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	public String addProduct() throws Exception {
		ProductCategoryDao productCategoryDao = DaoFactory.getProductCategoryDao();
		RegionDao regionDao = DaoFactory.getRegionDao();
		UnitDao unitDao = DaoFactory.getUnitDao();
		BrandDao brandDao = DaoFactory.getBrandDao();
		brands = brandDao.getAllBrands();
		productCategories = productCategoryDao.getAllProductCategories();
		regions = regionDao.getAllRegions();
		priceUnits = unitDao.getUnitsByType("货币");
		quantityUnits = unitDao.getUnitsByNotType("货币");
		return SUCCESS;
	}

	public String execute() throws Exception {
		ProductDao productDao = DaoFactory.getProductDao();
		ProductCategoryDao productCategoryDao = DaoFactory.getProductCategoryDao();
		ProductTypeDao productTypeDao = DaoFactory.getProductTypeDao();
		RegionDao regionDao = DaoFactory.getRegionDao();
		ProvinceDao provinceDao = DaoFactory.getProvinceDao();
		CityDao cityDao = DaoFactory.getCityDao();
		UnitDao unitDao = DaoFactory.getUnitDao();
		WarehouseDao wDao = DaoFactory.getWarehouseDao();
		currentProduct = productDao.getProductById(productId);
		BrandDao brandDao = DaoFactory.getBrandDao();
		brands = brandDao.getAllBrands();
		productCategories = productCategoryDao.getAllProductCategories();
		productTypes = productTypeDao.getAllProductTypesByCategory(currentProduct.getProductType().getProductCategory().getProductCategoryId());
		regions = regionDao.getAllRegions();
		provinces = provinceDao.getAllProvincesByRegion(currentProduct.getWarehouse().getCity().getProvince().getRegion().getRegionId());
		cities = cityDao.getAllCitiesByProvince(currentProduct.getWarehouse().getCity().getProvince().getProvinceId());
		quantityUnits = unitDao.getUnitsByNotType("货币");
		warehouses = wDao.getAllWarehousesByCityId(currentProduct.getWarehouse().getCity().getCityId());
		return SUCCESS;
	}

}
