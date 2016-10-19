/**
 * 
 */
package com.smartchemical.api.frame.dao.user;

import java.util.List;

import com.smartchemical.entities.frame.user.CartGetter;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public interface CartGetterDao {
	public boolean insertGetter(ScUser user, String getterName, String getterIdNo, String getterMobileNo, String getterEmail, String truckLicenseNo, long lastSelected, String remark);
	
	public boolean editGetter(int getterId, ScUser user, String getterName, String getterIdNo, String getterMobileNo, String getterEmail, String truckLicenseNo, long lastSelected, String remark);
	
	public boolean editGetter(CartGetter getter);
	
	public boolean disableGetter(int getterId);
	
	public boolean removeGetter(int getterId);
	
	public boolean removeGetterByUser(int userId);
	
	public List<CartGetter> getGetterByUser(int userId);
	
	public List<CartGetter> getEnableGetterByUser(int userId);
	
	public CartGetter getGetterById(int getterId);
	
	public CartGetter getGetterByLastselected(int userId, long lastSelected);
}
