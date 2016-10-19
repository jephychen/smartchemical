/**
 * 
 */
package com.smartchemical.api.frame.dao.user;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.user.Role;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public interface ScUserDao {
	public ScUser insertUser(String userName, String realName, String password, Date birthday, Company company, String externalCompanyName,
			Role role, String email, City city, String userAddress, float deposit, int companyLicenseStatus, 
			String mobileNo, Timestamp createTime);
	
	public boolean removeUser(int userId);
	
	public boolean editUser(int userId, String userName, String realName, String password, Date birthday, Company company, String externalCompanyName,
			Role role, String email, City city, String userAddress, float deposit, int companyLicenseStatus, 
			String mobileNo, Timestamp createTime);
	
	public boolean editUser(ScUser newUser);
	
	public ScUser getUserById(int userId);
	
	public ScUser getUserByUserName(String userName);
	
	public ScUser getUserByEmail(String email);
	
	public ScUser getUserByEmail(String email, String currentUserEmail);
	
	public ScUser getUserByMobile(String mobile);
	
	public ScUser getUserByMobile(String mobile, String currentUserEmail);
	
	public List<ScUser> getUsersByRole(int roleId);
	
	public List<ScUser> getUsersByCity(int cityId);
	
	public int getUserCountByCondition(String keyword, Map<String, List<String>> filters);
	
	public List<ScUser> getUserByCondition(String keyword, Map<String, List<String>> filters, String sortby, int pageSize, int pageIndex);
	
	public int getUserCartQuantity(int userId);
	
	public boolean setUserCartQuantity(int userId, int cartQuantity);
}
