/**
 * 
 */
package com.smartchemical.session.frame.dao.user.bean;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.user.RoleDao;
import com.smartchemical.entities.frame.user.Role;

/**
 * @author Jephy
 * 
 */
@Stateless
@Remote({ RoleDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/RoleDaoBean/remote")
public class RoleDaoBean implements RoleDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public boolean insertRole(String roleName, String description) {
		try {
			Role role = new Role();
			role.setRoleName(roleName);
			role.setDescription(description);
			em.persist(role);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeRole(int roleId) {
		try {
			Role role = em.find(Role.class, roleId);
			em.remove(role);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editRole(int roleId, String roleName, String description) {
		try {
			Role role = em.find(Role.class, roleId);
			role.setRoleName(roleName);
			role.setDescription(description);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Role getRoleById(int roleId) {
		Role role = em.find(Role.class, roleId);
		return role;
	}

	public Role getRoleByName(String roleName){
		try {
			Query query = em.createQuery("select r from Role r where r.roleName = ?1");
			query.setParameter(1, roleName);
			@SuppressWarnings("unchecked")
			List<Role> roles = query.getResultList();
			if (roles == null || roles.isEmpty()){
				return null;
			}
			return roles.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Role> getAllRoles() {
		try {
			Query query = em.createQuery("select r from Role r");
			@SuppressWarnings("unchecked")
			List<Role> roles = query.getResultList();
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
