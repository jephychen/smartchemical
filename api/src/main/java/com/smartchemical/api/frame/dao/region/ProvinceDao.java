/**
 * 
 */
package com.smartchemical.api.frame.dao.region;

import java.util.List;

import com.smartchemical.entities.frame.region.Province;
import com.smartchemical.entities.frame.region.Region;

/**
 * @author Jephy
 *
 */
public interface ProvinceDao {
	public Province getProvinceById(int provinceId);
	
	public boolean insertProvince(String provinceName, String provinceAlias, Region region, String description);
	
	public boolean editProvince(String provinceId, String provinceName, String provinceAlias, Region region, String description);
	
	public boolean removeProvince(int provinceId);
	
	public List<Province> getAllProvinces();
	
	public List<Province> getAllProvincesByRegion(int regionId);
}
