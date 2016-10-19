/**
 * 
 */
package com.smartchemical.api.frame.dao.product;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smartchemical.entities.frame.company.Brand;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.product.ProductNo;
import com.smartchemical.entities.frame.product.ProductType;
import com.smartchemical.entities.frame.unit.Unit;
import com.smartchemical.entities.frame.warehouse.Warehouse;

/**
 * @author Jephy
 * 
 */
public interface ProductDao {
	public boolean insertProduct(String productName, ProductType productType, int productStatus,
			ProductNo productNo, Brand brand, Company company, Company merchantCompany, float price, Unit priceUnit,
			float totalSoldQuantity, Unit quantityUnit, float totalSoldAmount, String pictureUrl,
			float stockLevel, float minSoldQunatity, Warehouse warehouse, String description,
			Timestamp lastModified, String contentDetail);

	public boolean removeProduct(int productId);

	public boolean editProduct(Product product);
	
	public Product getProductById(int productId);
	
	public List<Product> getProductByCompanyId(int companyId);
	
	public int getProductsCountByCompanyId(int companyId);
	
	public int getProductsCountByMerchantCompanyId(int companyId);
	
	public List<Product> getProductByCompanyIdPaging(int companyId, int pageSize, int pageIndex);
	
	public List<Product> getProductByMerchantCompanyIdPaging(int companyId, int pageSize, int pageIndex);
	
	public List<Product> getProductByCityId(int cityId);
	
	public List<Product> getProductByProductTypeId(int productTypeId);
	
	public List<Product> getProductByProductNo(String productNo);
	
	public List<Product> getProductByWarehouseId(int warehouseId);
	
	public List<Product> getProductByKeyword(String keyword, Map<String, List<String>> filters);
	
	public int getProductCountByKeyword(String keyword, Map<String, List<String>> filters);
	
	public List<Product> getPagingProductByKeyword(String keyword, Map<String, List<String>> filters, int pageSize, int pageIndex);
	
	public List<Product> getPagingProductByKeywordSortby(String keyword, String sortby, Map<String, List<String>> filters, int pageSize, int pageIndex);
	
	public List<Product> getAllProducts();
	
	public List<Product> getAllPagingProducts(int pageSize, int pageIndex);
	
	public Set<String> getCurrentPoNos();
	
}
