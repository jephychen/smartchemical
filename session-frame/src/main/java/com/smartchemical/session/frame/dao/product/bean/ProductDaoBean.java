/**
 * 
 */
package com.smartchemical.session.frame.dao.product.bean;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.common.constant.SearchConstant;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.entities.frame.company.Brand;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.product.Parameter;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.product.ProductNo;
import com.smartchemical.entities.frame.product.ProductType;
import com.smartchemical.entities.frame.unit.Unit;
import com.smartchemical.entities.frame.warehouse.Warehouse;

/**
 * @author Jephy
 * 
 */
@Stateless
@Remote({ ProductDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/ProductDaoBean/remote")
public class ProductDaoBean implements ProductDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public boolean insertProduct(String productName, ProductType productType, int productStatus,
			ProductNo productNo, Brand brand, Company company, Company merchantCompany, float price, Unit priceUnit, 
			float totalSoldQuantity, Unit quantityUnit, float totalSoldAmount, String pictureUrl, 
			float stockLevel, float minSoldQunatity, Warehouse warehouse, String description,
			Timestamp lastModified, String contentDetail) {
		try {
			Product product = new Product();
			product.setProductName(productName);
			product.setProductType(productType);
			product.setProductStatus(productStatus);
			product.setProductNo(productNo);
			product.setBrand(brand);
			product.setCompany(company);
			product.setMerchantCompany(merchantCompany);
			product.setPrice(price);
			product.setPriceUnit(priceUnit);
			product.setTotalSoldQuantity(totalSoldQuantity);
			product.setQuantityUnit(quantityUnit);
			product.setTotalSoldAmount(totalSoldAmount);
			product.setPictureUrl(pictureUrl);
			product.setStockLevel(stockLevel);
			product.setMinSoldQunatity(minSoldQunatity);
			product.setWarehouse(warehouse);
			product.setDescription(description);
			product.setLastModified(lastModified);
			product.setContentDetail(contentDetail);
			em.persist(product);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean removeProduct(int productId) {
		try {
			Product product = em.find(Product.class, productId);
			em.remove(product);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean editProduct(Product product) {
		try {
			Product p = em.find(Product.class, product.getProductId());
			p.setProductId(product.getProductId());
			p.setProductName(product.getProductName());
			p.setProductType(product.getProductType());
			p.setProductStatus(product.getProductStatus());
			p.setProductNo(product.getProductNo());
			p.setBrand(product.getBrand());
			p.setCompany(product.getCompany());
			p.setMerchantCompany(product.getMerchantCompany());
			p.setPrice(product.getPrice());
			p.setPriceUnit(product.getPriceUnit());
			p.setTotalSoldQuantity(product.getTotalSoldQuantity());
			p.setQuantityUnit(product.getQuantityUnit());
			p.setTotalSoldAmount(product.getTotalSoldAmount());
			p.setPictureUrl(product.getPictureUrl());
			p.setStockLevel(product.getStockLevel());
			p.setMinSoldQunatity(product.getMinSoldQunatity());
			p.setWarehouse(product.getWarehouse());
			p.setDescription(product.getDescription());
			p.setLastModified(product.getLastModified());
			p.setContentDetail(product.getContentDetail());
			em.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Product getProductById(int productId) {
		try {
			Product product = em.find(Product.class, productId);
			if (product == null){
				return null;
			}
			Query query = em.createNativeQuery("select * from Parameter where productTypeId = ?1", Parameter.class);
			query.setParameter(1, product.getProductType().getProductTypeId());
			@SuppressWarnings("unchecked")
			List<Parameter> parameters = query.getResultList();
			product.setParameters(parameters);
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public List<Product> getProductByCompanyId(int companyId) {
		try {
			Query query = em.createQuery("select p from Product p where p.companyId = ?1");
			query.setParameter(1, companyId);
			@SuppressWarnings("unchecked")
			List<Product> products = query.getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getProductsCountByCompanyId(int companyId) {
		try {
			Query query = em.createNativeQuery("select count(productId) from Product p where p.companyId = ?1");
			query.setParameter(1, companyId);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getProductsCountByMerchantCompanyId(int companyId) {
		try {
			Query query = em.createNativeQuery("select count(productId) from Product p where p.merchantCompanyId = ?1");
			query.setParameter(1, companyId);
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<Product> getProductByCompanyIdPaging(int companyId, int pageSize, int pageIndex) {
		try {
			Query query = em.createNativeQuery("select * from Product where companyId = ?1", Product.class);
			query.setParameter(1, companyId);
			@SuppressWarnings("unchecked")
			List<Product> products = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Product> getProductByMerchantCompanyIdPaging(int companyId, int pageSize, int pageIndex) {
		try {
			Query query = em.createNativeQuery("select * from Product where merchantCompanyId = ?1", Product.class);
			query.setParameter(1, companyId);
			@SuppressWarnings("unchecked")
			List<Product> products = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> getProductByCityId(int cityId) {
		try {
			Query query = em.createQuery("select p from Product p where p.cityId = ?1");
			query.setParameter(1, cityId);
			@SuppressWarnings("unchecked")
			List<Product> products = query.getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> getProductByProductTypeId(int productTypeId) {
		try {
			Query query = em.createQuery("select p from Product p where p.productTypeId = ?1");
			query.setParameter(1, productTypeId);
			@SuppressWarnings("unchecked")
			List<Product> products = query.getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> getProductByProductNo(String productNo) {
		try {
			Query query = em.createQuery("select p from Product p where p.productNo = ?1");
			query.setParameter(1, productNo);
			@SuppressWarnings("unchecked")
			List<Product> products = query.getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> getProductByWarehouseId(int warehouseId) {
		try {
			Query query = em.createQuery("select p from Product p where p.warehouseId = ?1");
			query.setParameter(1, warehouseId);
			@SuppressWarnings("unchecked")
			List<Product> products = query.getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Product> getProductByKeyword(String keyword, Map<String, List<String>> filters) {
		String querySql = "select p from Product p where 1 = 1" + makeWhere(keyword, filters);
		try {
			Query query = em.createQuery(querySql);
			List<Product> products = query.getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getProductCountByKeyword(String keyword,
			Map<String, List<String>> filters) {
		try {
			Query query = em.createQuery(
					"select count(p.productId) from Product p where 1 = 1" + makeWhere(keyword, filters));
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getPagingProductByKeyword(String keyword, Map<String, List<String>> filters, int pageSize, int pageIndex) {
		String querySql = "select p from Product p where 1 = 1" + makeWhere(keyword, filters) + makeOrderby(SearchConstant.SORTBY_ID_DESC);
		try {
			Query query = em.createQuery(querySql);
			List<Product> products = null;
			if (pageSize <= 0 || pageIndex <= 0){
				products = query.getResultList();
			}
			else {
				products = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getPagingProductByKeywordSortby(String keyword, String sortby, Map<String, List<String>> filters, int pageSize, int pageIndex) {
		String querySql = "select p from Product p where 1 = 1" + makeWhere(keyword, filters) + makeOrderby(sortby);
		try {
			Query query = em.createQuery(querySql);
			List<Product> products = null;
			if (pageSize <= 0 || pageIndex <= 0){
				products = query.getResultList();
			}
			else {
				products = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts(){
		try {
			Query query = em.createQuery("select p from Product p");
			List<Product> products = query.getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getAllPagingProducts(int pageSize, int pageIndex){
		try {
			Query query = em.createQuery("select p from Product p");
			List<Product> products = null;
			if (pageSize <= 0 || pageIndex <= 0){
				products = query.setMaxResults(50).setFirstResult(0).getResultList();
			}
			else {
				products = query.setMaxResults(pageSize).setFirstResult((pageIndex - 1) * pageSize).getResultList();
			}
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String makeWhere(String keyword, Map<String, List<String>> filters) {
		StringBuilder whereSql = new StringBuilder();
		if (keyword != null && !keyword.isEmpty()){
			whereSql.append(" and p.productName like '%" + keyword + "%'");
		}
		if (filters != null && !filters.isEmpty()){
			for (Entry<String, List<String>> entry : filters.entrySet()){
				if (entry.getKey().equals("region")){
					whereSql.append(dealRegion(entry.getValue()));
				}
				else if (entry.getKey().equals("brandId")){
					whereSql.append(dealBrandId(entry.getValue()));
				}
				else if (entry.getKey().equals("brandName")){
					whereSql.append(dealBrandName(entry.getValue()));
				}
				else if (entry.getKey().equals("company")){
					whereSql.append(dealCompany(entry.getValue()));
				}
				else if (entry.getKey().equals("companyId")){
					whereSql.append(dealCompanyId(entry.getValue()));
				}
				else if (entry.getKey().equals("merchantCompanyId")){
					whereSql.append(dealMerchantCompanyId(entry.getValue()));
				}
				else if (entry.getKey().equals("isMerchantProduct")){
					whereSql.append(dealIsMerchantProduct(entry.getValue()));
				}
				else if (entry.getKey().equals("merchantcompany")){
					whereSql.append(dealMerchantCompany(entry.getValue()));
				}
				else if (entry.getKey().equals("productNo")){
					whereSql.append(dealProductNo(entry.getValue()));
				}
				else if (entry.getKey().equals("productStatus")){
					whereSql.append(dealProductStatus(entry.getValue()));
				}
				else if (entry.getKey().equals("productCategory")){
					whereSql.append(dealProductCategory(entry.getValue()));
				}
			}
		}
		return whereSql.toString();
	}
	
	private String makeOrderby(String sortby){
		StringBuilder orderbySql = new StringBuilder();
		if (sortby == null || sortby.isEmpty()){
			return orderbySql.toString();
		}
		if (sortby.equals(SearchConstant.SORTBY_QUANTITY_DESC)){
			orderbySql.append(" order by totalSoldQuantity DESC");
		}
		else if (sortby.equals(SearchConstant.SORTBY_LASTMODIFIED_DESC)){
			orderbySql.append(" order by lastModified DESC");
		}
		else if (sortby.equals(SearchConstant.SORTBY_ID_DESC)){
			orderbySql.append(" order by p.productId DESC");
		}
		return orderbySql.toString();
	}

	private String dealRegion(List<String> regions){
		if (regions.contains("all") || regions.size() < 1){
			return "";
		}
		String regionSql = null;
		String provinceSql = null;
		String citySql = null;
		Map<String, Set<String>> cRegions = compressRegions(regions);
		for (Entry<String, Set<String>> entry : cRegions.entrySet()){
			if (entry.getKey().equals("region") && !entry.getValue().isEmpty()){
				StringBuilder sql = new StringBuilder();
				for (String region : entry.getValue()){
					sql.append("'" + region + "',");
				}
				regionSql = " or p.warehouse.city.province.region.regionName in (" + sql.substring(0, sql.length() - 1) + ")";
			}
			else if (entry.getKey().equals("province") && !entry.getValue().isEmpty()){
				StringBuilder sql = new StringBuilder();
				for (String province : entry.getValue()){
					sql.append("'" + province + "',");
				}
				provinceSql = " or p.warehouse.city.province.provinceName in (" + sql.substring(0, sql.length() - 1) + ")";
			}
			else if (entry.getKey().equals("city") && !entry.getValue().isEmpty()){
				StringBuilder sql = new StringBuilder();
				for (String city : entry.getValue()){
					sql.append("'" + city + "',");
				}
				citySql = " or p.warehouse.city.cityName in (" + sql.substring(0, sql.length() - 1) + ")";
			}
		}
		StringBuilder whereSql = new StringBuilder();
		if (regionSql == null && provinceSql == null && citySql == null){
			return "";
		}
		if (regionSql != null){
			whereSql.append(regionSql);
		}
		if (provinceSql != null){
			whereSql.append(provinceSql);
		}
		if (citySql != null){
			whereSql.append(citySql);
		}
		String result = " and (" + whereSql.toString().replaceFirst("or", "") + ")";
		return result;
	}
	
	private Map<String, Set<String>> compressRegions(List<String> regions) {
		Map<String, Set<String>> regionMap = new HashMap<String, Set<String>>();
		Set<String> regionList = new HashSet<String>();
		Set<String> provinceList = new HashSet<String>();
		Set<String> cityList = new HashSet<String>();
		for (String region : regions){
			String[] regionItems = region.split("-");
			if (regionItems.length == 1){
				regionList.add(regionItems[0]);
			}
			else if (regionItems.length == 2){
				provinceList.add(regionItems[1]);
			}
			else if (regionItems.length == 3){
				cityList.add(regionItems[2]);
			}
		}
		regionMap.put("region", regionList);
		regionMap.put("province", provinceList);
		regionMap.put("city", cityList);
		return regionMap;
	}
	
	private String dealBrandId(List<String> brands){
		if (brands.contains("all") || brands.size() < 1){
			return "";
		}
		StringBuilder brandWhereSql = new StringBuilder();
		brandWhereSql.append(" and p.brand.brandId in (");
		
		int i;
		for (i = 0; i < brands.size() - 1; i++){
			brandWhereSql.append( brands.get(i) + ",");
		}
		brandWhereSql.append( brands.get(i) + ")");
		
		return brandWhereSql.toString();
	}
	
	private String dealBrandName(List<String> brands){
		if (brands.contains("all") || brands.size() < 1){
			return "";
		}
		StringBuilder brandWhereSql = new StringBuilder();
		brandWhereSql.append(" and p.brand.brandName in (");
		
		int i;
		for (i = 0; i < brands.size() - 1; i++){
			brandWhereSql.append("'" + brands.get(i) + "',");
		}
		brandWhereSql.append("'" + brands.get(i) + "')");
		
		return brandWhereSql.toString();
	}

	private String dealCompany(List<String> companies){
		if (companies.contains("all") || companies.size() < 1){
			return "";
		}
		StringBuilder companyWhereSql = new StringBuilder();
		companyWhereSql.append(" and p.company.companyName in (");
		
		int i;
		for (i = 0; i < companies.size() - 1; i++){
			companyWhereSql.append( "'" + companies.get(i) + "',");
		}
		companyWhereSql.append( "'" + companies.get(i) + "')");
		
		return companyWhereSql.toString();
	}
	
	private String dealCompanyId(List<String> companyIds){
		if (companyIds.contains("all") || companyIds.size() < 1){
			return "";
		}
		StringBuilder companyWhereSql = new StringBuilder();
		companyWhereSql.append(" and p.company.companyId in (");
		
		int i;
		for (i = 0; i < companyIds.size() - 1; i++){
			companyWhereSql.append( "'" + companyIds.get(i) + "',");
		}
		companyWhereSql.append( "'" + companyIds.get(i) + "')");
		
		return companyWhereSql.toString();
	}
	
	private String dealIsMerchantProduct(List<String> isMerchantCompany){
		if (isMerchantCompany.contains("all") || isMerchantCompany.size() < 1){
			return "";
		}
		StringBuilder companyWhereSql = new StringBuilder();
		String trueorfalse = isMerchantCompany.get(0);
		if (trueorfalse.equalsIgnoreCase("true")){
			companyWhereSql.append(" and p.merchantCompany.companyId is not null");
		}
		else if (trueorfalse.equalsIgnoreCase("false")){
			companyWhereSql.append(" and p.merchantCompany.companyId is null");
		}
		else{
			companyWhereSql.append(" and p.merchantCompany.companyId is null");
		}
		return companyWhereSql.toString();
	}
	
	private String dealMerchantCompanyId(List<String> companyIds){
		if (companyIds.contains("all") || companyIds.size() < 1){
			return "";
		}
		StringBuilder companyWhereSql = new StringBuilder();
		companyWhereSql.append(" and p.merchantCompany.companyId in (");
		
		int i;
		for (i = 0; i < companyIds.size() - 1; i++){
			companyWhereSql.append( "'" + companyIds.get(i) + "',");
		}
		companyWhereSql.append( "'" + companyIds.get(i) + "')");
		
		return companyWhereSql.toString();
	}
	
	private String dealMerchantCompany(List<String> companies){
		if (companies == null || companies.contains("all") || companies.size() < 1){
			return "";
		}
		if (companies.contains("notnull")){
			return " and p.merchantCompany is not null";
		}
		else if (companies.contains("isnull")){
			return " and p.merchantCompany is null";
		}
		
		StringBuilder companyWhereSql = new StringBuilder();
		companyWhereSql.append(" and p.merchantCompany.companyName in (");
		
		int i;
		for (i = 0; i < companies.size() - 1; i++){
			companyWhereSql.append( "'" + companies.get(i) + "',");
		}
		companyWhereSql.append( "'" + companies.get(i) + "')");
		
		return companyWhereSql.toString();
	}
	
	private String dealProductNo(List<String> productNos){
		if (productNos.contains("all") || productNos.size() < 1){
			return "";
		}
		StringBuilder productNoSql = new StringBuilder();
		productNoSql.append(" and p.productNo.productNoName in (");
		
		int i;
		for (i = 0; i < productNos.size() - 1; i++){
			productNoSql.append( "'" + productNos.get(i) + "',");
		}
		productNoSql.append( "'" + productNos.get(i) + "')");
		
		return productNoSql.toString();
	}
	
	private String dealProductStatus(List<String> productStatus){
		if (productStatus == null || productStatus.isEmpty()){
			return "";
		}
		StringBuilder productNoSql = new StringBuilder();
		productNoSql.append(" and p.productStatus in (");
		
		int i;
		for (i = 0; i < productStatus.size() - 1; i++){
			productNoSql.append( productStatus.get(i) + ",");
		}
		productNoSql.append( productStatus.get(i) + ")");
		
		return productNoSql.toString();
	}
	
	private String dealProductCategory(List<String> categories){
		if (categories.contains("all") || categories.size() < 1){
			return "";
		}
		StringBuilder categoryNameSql = new StringBuilder();
		categoryNameSql.append(" and p.productType.productCategory.productCategoryName in (");
		
		int i;
		for (i = 0; i < categories.size() - 1; i++){
			categoryNameSql.append( "'" + categories.get(i) + "',");
		}
		categoryNameSql.append( "'" + categories.get(i) + "')");
		
		return categoryNameSql.toString();
	}

	public Set<String> getCurrentPoNos() {
		List<Product> ps = getAllProducts();
		if (ps == null){
			return null;
		}
		Set<String> poSet = new HashSet<String>();
		for (Product p : ps){
			poSet.add(p.getProductNo().getProductNoName());
		}
		return poSet;
	}

}
