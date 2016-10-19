/**
 * 
 */
package com.smartchemical.session.frame.dao.product.bean;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.product.ProductTypeDao;
import com.smartchemical.entities.frame.product.ProductCategory;
import com.smartchemical.entities.frame.product.ProductType;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ ProductTypeDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/ProductTypeDaoBean/remote")
public class ProductTypeDaoBean implements ProductTypeDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;
	
	public boolean insertProductType(String productTypeName,
			ProductCategory productCategory, String description) {
		try {
			ProductType productType = new ProductType();
			productType.setProductTypeName(productTypeName);
			productType.setProductCategory(productCategory);
			productType.setDescription(description);
			em.persist(productType);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeProductType(int productTypeId) {
		try {
			ProductType productType = em.find(ProductType.class, productTypeId);
			em.remove(productType);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editProductType(int productTypeId, String productTypeName,
			ProductCategory productCategory, String description) {
		try {
			ProductType productType = em.find(ProductType.class, productTypeId);
			productType.setProductTypeName(productTypeName);
			productType.setProductCategory(productCategory);
			productType.setDescription(description);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ProductType getProductTypeById(int productTypeId) {
		ProductType productType = em.find(ProductType.class, productTypeId);
		return productType;
	}

	public List<ProductType> getAllProductTypes() {
		try {
			Query query = em.createQuery("select t from ProductType t");
			@SuppressWarnings("unchecked")
			List<ProductType> productTypes = query.getResultList();
			return productTypes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ProductType> getAllProductTypesByCategory(int categoryId) {
		try {
			Query query = em.createNativeQuery("select * from ProductType where productCategoryId = ?1", ProductType.class);
			query.setParameter(1, categoryId);
			@SuppressWarnings("unchecked")
			List<ProductType> productTypes = query.getResultList();
			return productTypes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
