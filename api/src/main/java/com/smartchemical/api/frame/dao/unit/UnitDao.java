/**
 * 
 */
package com.smartchemical.api.frame.dao.unit;

import java.util.List;

import com.smartchemical.entities.frame.unit.Unit;

/**
 * @author Jephy
 *
 */
public interface UnitDao {
	public boolean insertUnit(String unitName, String unitType);
	
	public boolean removeUnit(int unitId);
	
	public boolean editUnit(int unitId, String unitName, String unitType);
	
	public Unit getUnitById(int unitId);
	
	public List<Unit> getUnits();
	
	public List<Unit> getUnitsByType(String type);
	
	public List<Unit> getUnitsByNotType(String type);
}
