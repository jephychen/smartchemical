/**
 * 
 */
package com.smartchemical.api.frame.dao.region;

import java.util.List;

import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.region.Province;

/**
 * @author Jephy
 *
 */
public interface CityDao {
	public City getCityById(int cityId);
	
	public boolean insertCity(String cityName, String cityAlias, Province province, String description);
	
	public boolean editCity(String cityId, String cityName, String cityAlias, Province province, String description);
	
	public boolean removeCity(int cityId);
	
	public List<City> getAllCities();
	
	public List<City> getAllCitiesByProvince(int provinceId);
}
