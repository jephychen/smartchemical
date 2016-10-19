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

import com.smartchemical.api.frame.dao.product.ProductCategoryDao;
import com.smartchemical.entities.frame.product.ProductCategory;

/**
 * @author Jephy
 * 
 */
@Stateless
@Remote({ ProductCategoryDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/ProductCategoryDaoBean/remote")
public class ProductCategoryDaoBean implements ProductCategoryDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;
	
	public boolean insertProductCategory(String productCategoryName,
			String descritpion) {
		try {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryName(productCategoryName);
			productCategory.setDescription(descritpion);
			em.persist(productCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean removeProductCategory(int productCategoryId) {
		try {
			ProductCategory productCategory = em.find(ProductCategory.class, productCategoryId);
			em.remove(productCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean editProductCategory(int productCategoryId,
			String productCategoryName, String descritpion) {
		try {
			ProductCategory productCategory = em.find(ProductCategory.class, productCategoryId);
			productCategory.setProductCategoryName(productCategoryName);
			productCategory.setDescription(descritpion);
			em.persist(productCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public ProductCategory getProductCategoryById(int productCategoryId) {
		ProductCategory productCategory = em.find(ProductCategory.class, productCategoryId);
		return productCategory;
	}

	public List<ProductCategory> getAllProductCategories() {
		try {
			Query query = em.createQuery("select c from ProductCategory c");
			@SuppressWarnings("unchecked")
			List<ProductCategory> productCategories = query.getResultList();
			return productCategories;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
