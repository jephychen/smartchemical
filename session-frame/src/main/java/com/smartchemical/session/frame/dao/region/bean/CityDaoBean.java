/**
 * 
 */
package com.smartchemical.session.frame.dao.region.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.region.CityDao;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.region.Province;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({CityDao.class})
@RemoteBinding(jndiBinding = "smart-chemical/CityDaoBean/remote")
public class CityDaoBean implements CityDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public City getCityById(int cityId) {
		City city = em.find(City.class, cityId);
		return city;
	}

	public boolean insertCity(String cityName, String cityAlias,
			Province province, String description) {
		try {
			City city = new City();
			city.setCityName(cityName);
			city.setCityAlias(cityAlias);
			city.setProvince(province);
			city.setDescription(description);
			em.persist(city);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editCity(String cityId, String cityName,
			String cityAlias, Province province, String description) {
		try {
			City city = em.find(City.class, cityId);
			city.setCityName(cityName);
			city.setCityAlias(cityAlias);
			city.setProvince(province);
			city.setDescription(description);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeCity(int cityId) {
		try {
			City city = em.find(City.class, cityId);
			em.remove(city);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<City> getAllCities() {
		try {
			Query query = em.createQuery("select p from City p");
			@SuppressWarnings("unchecked")
			List<City> citys = query.getResultList();
			return citys;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<City> getAllCitiesByProvince(int provinceId) {
		try {
			Query query = em.createNativeQuery("select * from City where provinceId = ?1", City.class);
			query.setParameter(1, provinceId);
			@SuppressWarnings("unchecked")
			List<City> citys = query.getResultList();
			return citys;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
