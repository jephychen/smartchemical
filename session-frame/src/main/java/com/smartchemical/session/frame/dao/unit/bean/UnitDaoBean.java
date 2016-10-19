/**
 * 
 */
package com.smartchemical.session.frame.dao.unit.bean;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.unit.UnitDao;
import com.smartchemical.entities.frame.unit.Unit;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({UnitDao.class})
@RemoteBinding(jndiBinding = "smart-chemical/UnitDaoBean/remote")
public class UnitDaoBean implements UnitDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;
	
	public boolean insertUnit(String unitName, String unitType) {
		try {
			Unit unit = new Unit();
			unit.setUnitName(unitName);
			unit.setUnitType(unitType);
			em.persist(unit);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeUnit(int unitId) {
		try {
			Unit unit = em.find(Unit.class, unitId);
			em.remove(unit);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editUnit(int unitId, String unitName, String unitType) {
		try {
			Unit unit = em.find(Unit.class, unitId);
			unit.setUnitName(unitName);
			unit.setUnitType(unitType);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Unit getUnitById(int unitId) {
		Unit unit = em.find(Unit.class, unitId);
		return unit;
	}

	public List<Unit> getUnits() {
		try {
			Query query = em.createQuery("select u from Unit u");
			@SuppressWarnings("unchecked")
			List<Unit> units = query.getResultList();
			return units;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Unit> getUnitsByType(String type) {
		try {
			Query query = em.createNativeQuery("select * from Unit where unitType in ('" + type + "')", Unit.class);
			@SuppressWarnings("unchecked")
			List<Unit> units = query.getResultList();
			return units;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Unit> getUnitsByNotType(String type) {
		try {
			Query query = em.createNativeQuery("select * from Unit where unitType not in ('" + type + "')", Unit.class);
			@SuppressWarnings("unchecked")
			List<Unit> units = query.getResultList();
			return units;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
