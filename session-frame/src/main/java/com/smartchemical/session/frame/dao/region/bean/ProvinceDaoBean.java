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

import com.smartchemical.api.frame.dao.region.ProvinceDao;
import com.smartchemical.entities.frame.region.Province;
import com.smartchemical.entities.frame.region.Region;

/**
 * @author Jephy
 * 
 */
@Stateless
@Remote({ ProvinceDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/ProvinceDaoBean/remote")
public class ProvinceDaoBean implements ProvinceDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public Province getProvinceById(int provinceId) {
		Province province = em.find(Province.class, provinceId);
		return province;
	}

	public boolean insertProvince(String provinceName, String provinceAlias,
			Region region, String description) {
		try {
			Province province = new Province();
			province.setProvinceName(provinceName);
			province.setProvinceAlias(provinceAlias);
			province.setRegion(region);
			province.setDescription(description);
			em.persist(province);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editProvince(String provinceId, String provinceName,
			String provinceAlias, Region region, String description) {
		try {
			Province province = em.find(Province.class, provinceId);
			province.setProvinceName(provinceName);
			province.setProvinceAlias(provinceAlias);
			province.setRegion(region);
			province.setDescription(description);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeProvince(int provinceId) {
		try {
			Province province = em.find(Province.class, provinceId);
			em.remove(province);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Province> getAllProvinces() {
		try {
			Query query = em.createQuery("select p from Province p");
			@SuppressWarnings("unchecked")
			List<Province> provinces = query.getResultList();
			return provinces;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Province> getAllProvincesByRegion(int regionId) {
		try {
			Query query = em.createNativeQuery("select * from Province where regionId = ?1", Province.class);
			query.setParameter(1, regionId);
			@SuppressWarnings("unchecked")
			List<Province> provinces = query.getResultList();
			return provinces;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
