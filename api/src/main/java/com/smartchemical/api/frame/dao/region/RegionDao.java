/**
 * 
 */
package com.smartchemical.api.frame.dao.region;

import java.util.List;

import com.smartchemical.entities.frame.region.Region;

/**
 * @author Jephy
 *
 */
public interface RegionDao {
	
	public Region getRegionById(int regionId);
	
	public boolean insertRegion(String regionName, String regionAlias, String description);
	
	public boolean editRegion(String regionId, String regionName, String regionAlias, String description);
	
	public boolean removeRegion(int regionId);
	
	public List<Region> getAllRegions();
	
	/**
	 * 取得带省份和城市的区域列表。为了提高效率，区域会被缓存，如果需要从数据库获取最新信息，请调用getAllRegionsWithProvincesDirectly()
	 */
	public List<Region> getAllRegionsWithProvinces();
	
	/**
	 * 直接从数据库取得带省份和城市的区域列表，同时更新缓存。区域是多层结构，效率较低。
	 */
	public List<Region> getAllRegionsWithProvincesDirectly();
}
