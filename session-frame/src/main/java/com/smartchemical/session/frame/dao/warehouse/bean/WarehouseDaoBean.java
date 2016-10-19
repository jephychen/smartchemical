/**
 * 
 */
package com.smartchemical.session.frame.dao.warehouse.bean;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.common.constant.SearchConstant;
import com.smartchemical.api.frame.dao.warehouse.WarehouseDao;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.user.ScUser;
import com.smartchemical.entities.frame.warehouse.Warehouse;

/**
 * @author Jephy
 * 
 */
@Stateless
@Remote({ WarehouseDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/WarehouseDaoBean/remote")
public class WarehouseDaoBean implements WarehouseDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;
	
	public Warehouse insertWarehouse(String warehouseName,
			City city, String address, ScUser warehouseAdmin, double longitude, double latitude, int warehouseStatus) {
		try {
			Warehouse warehouse = new Warehouse();
			warehouse.setWarehouseName(warehouseName);
			warehouse.setCity(city);
			warehouse.setAddress(address);
			warehouse.setWarehouseAdmin(warehouseAdmin);
			warehouse.setLongitude(longitude);
			warehouse.setLatitude(latitude);
			warehouse.setWarehouseStatus(warehouseStatus);
			warehouse.setLastModified(new Timestamp(System.currentTimeMillis()));
			em.persist(warehouse);
			return warehouse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeWarehouse(int warehouseId) {
		try {
			Warehouse warehouse = em.find(Warehouse.class, warehouseId);
			em.remove(warehouse);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editWarehouse(Warehouse warehouse) {
		try {
			Warehouse w = em.find(Warehouse.class, warehouse.getWarehouseId());
			w.setWarehouseName(warehouse.getWarehouseName());
			w.setCity(warehouse.getCity());
			w.setAddress(warehouse.getAddress());
			w.setWarehouseAdmin(warehouse.getWarehouseAdmin());
			w.setLongitude(warehouse.getLongitude());
			w.setLatitude(warehouse.getLatitude());
			w.setWarehouseStatus(warehouse.getWarehouseStatus());
			w.setLastModified(new Timestamp(System.currentTimeMillis()));
			em.merge(w);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Warehouse getWarehouseById(int warehouseId) {
		Warehouse warehouse = em.find(Warehouse.class, warehouseId);
		return warehouse;
	}

	public List<Warehouse> getAllWarehousesByCityId(int cityId) {
		try {
			Query query = em.createNativeQuery("select * from Warehouse where cityId = ?1", Warehouse.class);
			query.setParameter(1, cityId);
			@SuppressWarnings("unchecked")
			List<Warehouse> warehouses = query.getResultList();
			return warehouses;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Warehouse> getAllWarehouses() {
		try {
			Query query = em.createQuery("select w from Warehouse w where w.warehouseStatus = 1");
			@SuppressWarnings("unchecked")
			List<Warehouse> warehouses = query.getResultList();
			return warehouses;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getAllWarehousesCount(String keyword){
		try {
			Query query = em.createNativeQuery("select count(warehouseId) from Warehouse w where w.warehouseStatus = 1" + makeWhere(keyword));
			@SuppressWarnings("unchecked")
			List<Number> counts = query.getResultList();
			return counts.get(0).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<Warehouse> getAllWarehouses(String keyword, int pageSize, int pageIndex, String orderby) {
		try {
			Query query = em.createQuery("select w from Warehouse w where w.warehouseStatus = 1" + makeWhere(keyword) + makeOrderby(orderby));
			@SuppressWarnings("unchecked")
			List<Warehouse> warehouses = query.getResultList();
			return warehouses;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String makeWhere(String keyword){
		StringBuilder wheresql = new StringBuilder();
		if (keyword == null || keyword.isEmpty()){
			return wheresql.toString();
		}
		wheresql.append(" and w.warehouseName like '%" + keyword + "%'");
		return wheresql.toString();
	}
	
	private String makeOrderby(String sortby){
		StringBuilder orderbySql = new StringBuilder();
		if (sortby == null || sortby.isEmpty()){
			return orderbySql.toString();
		}
		if (sortby.equals(SearchConstant.SORTBY_LASTMODIFIED_DESC)){
			orderbySql.append(" order by w.lastModified DESC");
		}
		return orderbySql.toString();
	}

}
