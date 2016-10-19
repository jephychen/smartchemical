/**
 * 
 */
package com.smartchemical.usercenter.adminuser;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.common.constant.SearchConstant;
import com.smartchemical.api.frame.dao.warehouse.WarehouseDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.entities.frame.warehouse.Warehouse;

/**
 * @author Jephy
 *
 */
public class WarehouseManagementAction implements Action {
	
	/**
	 * 基础信息
	 * */
	private int queryType = 30;
	
	private static final int PAGE_SIZE = 5;
	
	private static final String ACTION_NAME = "AdminWarehouseManagement";
	
	private int currentPage = 1;
	
	private Paginator paginator;
	
	
	//扩展信息
	private String keyword;
	
	private int warehouseId;
	
	private List<Warehouse> warehouses;

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	private void setPaginator(int totalRecordCount){
		paginator = new Paginator(totalRecordCount, currentPage, PAGE_SIZE, ACTION_NAME);
		currentPage = paginator.getCurrentPage();
	}
	
	private void setAllWarehousesPaging() throws Exception {
		WarehouseDao wDao = DaoFactory.getWarehouseDao();
		int totalCount = wDao.getAllWarehousesCount(keyword);
		setPaginator(totalCount);
		warehouses = wDao.getAllWarehouses(keyword, PAGE_SIZE, paginator.getCurrentPage(), SearchConstant.SORTBY_LASTMODIFIED_DESC);
	}
	
	public String allWarehouses() throws Exception {
		queryType = 30;
		setAllWarehousesPaging();
		return SUCCESS;
	}
	
	public String removeWarehouse() throws Exception{
		WarehouseDao wDao = DaoFactory.getWarehouseDao();
		Warehouse warehouse = wDao.getWarehouseById(warehouseId);
		if (warehouse == null){
			return null;
		}
		warehouse.setWarehouseStatus(0);
		wDao.editWarehouse(warehouse);
		if (queryType == 30){
			setAllWarehousesPaging();
		}
		else {
			setAllWarehousesPaging();
		}
		return SUCCESS;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
