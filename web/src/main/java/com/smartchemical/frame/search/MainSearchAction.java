/**
 * 
 */
package com.smartchemical.frame.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smartchemical.api.frame.dao.company.BrandDao;
import com.smartchemical.api.frame.dao.product.ProductCategoryDao;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.api.frame.dao.region.RegionDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.entities.frame.company.Brand;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.product.ProductCategory;
import com.smartchemical.entities.frame.region.Region;

/**
 * @author Jephy
 * 
 */
public class MainSearchAction {
	private static final int PAGE_SIZE = 80;
	
	private String keyword;
	
	private List<ProductCategory> categories;
	
	private Set<String> productNos;

	private List<Product> products;
	
	private List<Brand> brands;
	
	private List<Region> regions;
	
	private List<FilterItem> filters = new LinkedList<FilterItem>();
	
	private Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
	
	private String filter;
	
	private String removeFilter;
	
	private String sortby;
	
	private int currentPage = 1;
	
	private Paginator paginator;
	
	private int totalRecordCount;
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public Set<String> getProductNos() {
		return productNos;
	}

	public void setProductNos(Set<String> productNos) {
		this.productNos = productNos;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public List<FilterItem> getFilters() {
		return filters;
	}

	public void setFilters(List<FilterItem> filters) {
		this.filters = filters;
	}

	public String getRemoveFilter() {
		return removeFilter;
	}

	public void setRemoveFilter(String removeFilter) {
		this.removeFilter = removeFilter;
	}

	public String getSortby() {
		return sortby;
	}

	public void setSortby(String sortby) {
		this.sortby = sortby;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public String execute() throws Exception {
		filters.clear();
		fillFilters();
		//只取没有下架的商品
		List<String> statusFilterItem = new LinkedList<String>();
		statusFilterItem.add("1");
		filtersMap.put("productStatus", statusFilterItem);
		try {
			BrandDao brandDao = DaoFactory.getBrandDao();
			brands = brandDao.getAllBrands();
			ProductDao productDao = DaoFactory.getProductDao();
			totalRecordCount = productDao.getProductCountByKeyword(keyword, filtersMap);
			paginator = new Paginator(totalRecordCount, currentPage, PAGE_SIZE);
			currentPage = paginator.getCurrentPage();
			ProductCategoryDao categoryDao = DaoFactory.getProductCategoryDao();
			categories = categoryDao.getAllProductCategories();
			products = productDao.getPagingProductByKeywordSortby(keyword, sortby, filtersMap, PAGE_SIZE, paginator.getCurrentPage());
			productNos = productDao.getCurrentPoNos();
			RegionDao regionDao = DaoFactory.getRegionDao();
			regions = regionDao.getAllRegionsWithProvinces();
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}

	private void fillFilters(){
		if (filter == null){
			return;
		}
		else if (filter.startsWith(",")){
			filter = filter.substring(1);
		}
		for (String f : filter.split(",")){
			if (!isContainFilter(f)){
				addToFilters(f);
				addToFiltersMap(f);
			}
		}
		if (removeFilter != null){
			removeFromFilters(removeFilter);
			removeFromFiltersMap(removeFilter);
			filter = mergeFilters(filters);
		}
	}
	
	private void addToFilters(String f){
		if (f == null || !f.contains(":") || f.split(":").length < 2){
			return;
		}
		FilterItem item = new FilterItem(f);
		filters.add(item);
	}
	
	private void removeFromFilters(String removeFilter){
		for (int i = 0; i < filters.size(); i++){
			if (filters.get(i).getOriginalFilter().equals(removeFilter)){
				filters.remove(i);
				break;
			}
		}
	}
	
	private boolean isContainFilter(String filter){
		for (FilterItem item : filters){
			if (item.getOriginalFilter().equals(filter)){
				return true;
			}
		}
		return false;
	}

	private void addToFiltersMap(String f) {
		if (f == null || !f.contains(":") || f.split(":").length < 2){
			return;
		}
		String[] entry = f.split(":");
		List<String> values = null;
		if (filtersMap.containsKey(entry[0])){
			values = filtersMap.get(entry[0]);
		}
		else{
			values = new LinkedList<String>();
		}
		values.add(entry[1]);
		filtersMap.put(entry[0], values);
	}
	
	private void removeFromFiltersMap(String f) {
		if (f == null || !f.contains(":") || f.split(":").length < 2){
			return;
		}
		String[] entry = f.split(":");
		if (filtersMap.containsKey(entry[0])){
			filtersMap.get(entry[0]).remove(entry[1]);
		}
	}

	private String mergeFilters(List<FilterItem> filters) {
		StringBuffer str = new StringBuffer();
		for (FilterItem item : filters){
			str.append(item.getOriginalFilter() + ",");
		}
		String result = null;
		if (str.lastIndexOf(",") != -1 && (str.lastIndexOf(",") == str.length() - 1)){
			result = str.substring(0, str.length() -1);
		}
		return result;
	}
	
}
