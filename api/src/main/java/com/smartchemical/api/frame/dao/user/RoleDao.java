package com.smartchemical.api.frame.dao.user;

import java.util.List;

import com.smartchemical.entities.frame.user.Role;

public interface RoleDao {
	public boolean insertRole(String roleName, String description);
	
	public boolean removeRole(int roleId);
	
	public boolean editRole(int roleId, String roleName, String description);
	
	public Role getRoleById(int roleId);
	
	public Role getRoleByName(String roleName);
	
	public List<Role> getAllRoles();
}
