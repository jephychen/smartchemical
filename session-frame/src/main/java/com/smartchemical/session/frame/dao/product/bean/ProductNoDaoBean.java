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

import com.smartchemical.api.frame.dao.product.ProductNoDao;
import com.smartchemical.entities.frame.product.ProductNo;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ ProductNoDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/ProductNoDaoBean/remote")
public class ProductNoDaoBean implements ProductNoDao {
	
	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public ProductNo insertProductNo(String productNoName,
			String description) {
		try {
			ProductNo productNo = new ProductNo();
			productNo.setProductNoName(productNoName);
			productNo.setDescription(description);
			em.persist(productNo);
			return productNo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeProductNo(int productNoId) {
		try {
			ProductNo productNo = em.find(ProductNo.class, productNoId);
			em.remove(productNo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean editProductNo(ProductNo productNo) {
		try {
			em.merge(productNo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public ProductNo getProductNo(int productNoId) {
		ProductNo productNo = em.find(ProductNo.class, productNoId);
		return productNo;
	}

	public List<ProductNo> getAllProductNos() {
		try {
			Query query = em.createQuery("select p from ProductNo p");
			@SuppressWarnings("unchecked")
			List<ProductNo> productNos = query.getResultList();
			return productNos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ProductNo getProductByName(String productName) {
		try {
			Query query = em.createNativeQuery("select * from ProductNo where productNoName = ?1", ProductNo.class);
			query.setParameter(1, productName);
			@SuppressWarnings("unchecked")
			List<ProductNo> productNos = query.getResultList();
			if (productNos == null || productNos.isEmpty()){
				return null;
			}
			return productNos.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
