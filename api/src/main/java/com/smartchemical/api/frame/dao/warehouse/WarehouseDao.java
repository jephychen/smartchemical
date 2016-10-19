/**
 * 
 */
package com.smartchemical.api.frame.dao.warehouse;

import java.util.List;

import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.user.ScUser;
import com.smartchemical.entities.frame.warehouse.Warehouse;

/**
 * @author Jephy
 *
 */
public interface WarehouseDao {
	public Warehouse insertWarehouse(String warehouseName,
			City city, String address, ScUser warehouseAdmin, double longitude, double latitude, int warehouseStatus);
	
	public boolean removeWarehouse(int warehouseId);
	
	public boolean editWarehouse(Warehouse warehouse);
	
	public Warehouse getWarehouseById(int warehouseId);
	
	public List<Warehouse> getAllWarehousesByCityId(int cityId);
	
	public List<Warehouse> getAllWarehouses();
	
	public int getAllWarehousesCount(String keyword);
	
	public List<Warehouse> getAllWarehouses(String keyword, int pageSize, int pageIndex, String orderby);
}
