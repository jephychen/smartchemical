/**
 * 
 */
package com.smartchemical.session.frame.dao.region.bean;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.region.RegionDao;
import com.smartchemical.entities.frame.region.Province;
import com.smartchemical.entities.frame.region.Region;

/**
 * @author Jephy
 * 
 */

@Stateless
@Remote({ RegionDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/RegionDaoBean/remote")
public class RegionDaoBean implements RegionDao {
	
	private static List<Region> regionCache = null;

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public Region getRegionById(int regionId) {
		Region region = em.find(Region.class, regionId);
		return region;
	}

	public boolean insertRegion(String regionName, String regionAlias,
			String description) {
		try {
			Region region = new Region();
			region.setRegionName(regionName);
			region.setRegionAlias(regionAlias);
			region.setDescription(description);
			em.persist(region);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editRegion(String regionId, String regionName,
			String regionAlias, String description) {
		try {
			Region region = em.find(Region.class, regionId);
			region.setRegionName(regionName);
			region.setRegionAlias(regionAlias);
			region.setDescription(description);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeRegion(int regionId) {
		try {
			Region region = em.find(Region.class, regionId);
			em.remove(region);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Region> getAllRegions() {
		try {
			Query query = em.createQuery("select r from Region r");
			@SuppressWarnings("unchecked")
			List<Region> regions = query.getResultList();
			return regions;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Region> getAllRegionsWithProvinces() {
		if (regionCache != null && !regionCache.isEmpty()){
			return regionCache;
		}
		else{
			try {
				Query query = em.createQuery("select r from Region r");
				@SuppressWarnings("unchecked")
				List<Region> regions = query.getResultList();
				enrichRegionWithProvinces(regions);
				regionCache = regions;
				return regions;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void enrichRegionWithProvinces(List<Region> regions){
		for (Region region : regions){
			Query query = em.createNativeQuery("select * from Province where regionId = ?1", Province.class);
			query.setParameter(1, region.getRegionId());
			region.setProvinces(query.getResultList());
		}
	}

	public List<Region> getAllRegionsWithProvincesDirectly() {
		try {
			Query query = em.createQuery("select r from Region r");
			@SuppressWarnings("unchecked")
			List<Region> regions = query.getResultList();
			enrichRegionWithProvinces(regions);
			if (regionCache != null){
				regionCache.clear();
			}
			regionCache = regions;
			return regions;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
