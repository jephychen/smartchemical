/**
 * 
 */
package com.smartchemical.session.frame.dao.company.bean;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.company.BrandDao;
import com.smartchemical.entities.frame.company.Brand;
import com.smartchemical.entities.frame.company.Company;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ BrandDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/BrandDaoBean/remote")
public class BrandDaoBean implements BrandDao {
	
	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public Brand insertBrand(String brandName, Company company, String logoPath) {
		try {
			Brand brand = new Brand();
			brand.setBrandName(brandName);
			brand.setBrandCompany(company);
			brand.setLogoPath(logoPath);
			em.persist(brand);
			return brand;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean editBrand(Brand brand) {
		try {
			Brand currentBrand = em.find(Brand.class, brand.getBrandId());
			currentBrand.setBrandName(brand.getBrandName());
			currentBrand.setBrandCompany(brand.getBrandCompany());
			currentBrand.setLogoPath(brand.getLogoPath());
			em.merge(currentBrand);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeBrand(int brandId) {
		try {
			Brand currentBrand = em.find(Brand.class, brandId);
			em.remove(currentBrand);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Brand getBrandById(int brandId) {
		Brand currentBrand = em.find(Brand.class, brandId);
		return currentBrand;
	}

	public List<Brand> getAllBrands() {
		try {
			Query query = em.createQuery("select b from Brand b");
			@SuppressWarnings("unchecked")
			List<Brand> brands = query.getResultList();
			return brands;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Brand getBrandByName(String brandName) {
		try {
			Query query = em.createQuery("select b from Brand b where b.brandName = ?1");
			query.setParameter(1, brandName);
			@SuppressWarnings("unchecked")
			List<Brand> brands = query.getResultList();
			if (brands == null || brands.isEmpty()){
				return null;
			}
			return brands.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
