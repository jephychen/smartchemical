/**
 * 
 */
package com.smartchemical.session.frame.dao.product.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.product.ParameterDao;
import com.smartchemical.entities.frame.product.Parameter;
import com.smartchemical.entities.frame.product.ProductType;

/**
 * @author Jephy
 * 
 */
@Stateless
@Remote({ ParameterDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/ParameterDaoBean/remote")
public class ParameterDaoBean implements ParameterDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public boolean insertParameter(ProductType productType, String parameterName,
			String parameterValue) {
		try {
			Parameter parameter = new Parameter();
			parameter.setProductType(productType);
			parameter.setParameterName(parameterName);
			parameter.setParameterValue(parameterValue);
			em.persist(parameter);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editParameter(int parameterId, ProductType productType,
			String parameterName, String parameterValue) {
		try {
			Parameter parameter = em.find(Parameter.class, parameterId);
			parameter.setProductType(productType);
			parameter.setParameterName(parameterName);
			parameter.setParameterValue(parameterValue);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeParameterById(int parameterId) {
		try {
			Parameter parameter = em.find(Parameter.class, parameterId);
			em.remove(parameter);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeParameterByProductTypeId(int productTypeId) {
		try {
			Query query = em.createQuery("delete from Parameter p where p.productTypeId = ?1");
			query.setParameter(1, productTypeId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Map<String, String> getParameterByProductTypeId(int productTypeId) {
		try {
			Query query = em.createQuery("select p from Parameter p where p.productTypeId = ?1");
			query.setParameter(1, productTypeId);
			@SuppressWarnings("unchecked")
			List<Parameter> parameters = query.getResultList();
			Map<String, String> paraMap = new HashMap<String, String>();
			//TODO 循环两次，可能会影响性能，回头考虑更好的办法
			for (Parameter p : parameters){
				paraMap.put(p.getParameterName(), p.getParameterValue());
			}
			return paraMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
