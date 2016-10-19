/**
 * 
 */
package com.smartchemical.usercenter.adminuser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.smartchemical.api.frame.common.constant.SearchConstant;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.Paginator;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class UserManagementAction implements Action {
	
	/**
	 * 基础信息
	 * */
	private int queryType = 20;
	
	private static final int PAGE_SIZE = 5;
	
	private static final String ACTION_NAME = "AdminUserManagement";
	
	private int currentPage = 1;
	
	private Paginator paginator;
	
	/**
	 * 页面扩展信息
	 * */
	private String keyword;
	
	private List<ScUser> users;

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<ScUser> getUsers() {
		return users;
	}

	public void setUsers(List<ScUser> users) {
		this.users = users;
	}

	private void setPaginator(int totalRecordCount){
		paginator = new Paginator(totalRecordCount, currentPage, PAGE_SIZE, ACTION_NAME);
		currentPage = paginator.getCurrentPage();
	}
	
	private void setWarehouseAdminUsersPaging() throws Exception{
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> roleFilterItem = new LinkedList<String>();
		roleFilterItem.add("5");
		filtersMap.put("roleId", roleFilterItem);
		ScUserDao userDao = DaoFactory.getScUserDao();
		int totalCount = userDao.getUserCountByCondition(keyword, filtersMap);
		setPaginator(totalCount);
		users = userDao.getUserByCondition(keyword, filtersMap, SearchConstant.SORTBY_ID_DESC, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	private void setAdminUsersPaging() throws Exception{
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> roleFilterItem = new LinkedList<String>();
		roleFilterItem.add("1");
		filtersMap.put("roleId", roleFilterItem);
		ScUserDao userDao = DaoFactory.getScUserDao();
		int totalCount = userDao.getUserCountByCondition(keyword, filtersMap);
		setPaginator(totalCount);
		users = userDao.getUserByCondition(keyword, filtersMap, SearchConstant.SORTBY_ID_DESC, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	private void setCommonUsersPaging() throws Exception{
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> roleFilterItem = new LinkedList<String>();
		roleFilterItem.add("2");
		filtersMap.put("roleId", roleFilterItem);
		ScUserDao userDao = DaoFactory.getScUserDao();
		int totalCount = userDao.getUserCountByCondition(keyword, filtersMap);
		setPaginator(totalCount);
		users = userDao.getUserByCondition(keyword, filtersMap, SearchConstant.SORTBY_ID_DESC, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	private void setSupplierUsersPaging() throws Exception{
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> roleFilterItem = new LinkedList<String>();
		roleFilterItem.add("3");
		filtersMap.put("roleId", roleFilterItem);
		ScUserDao userDao = DaoFactory.getScUserDao();
		int totalCount = userDao.getUserCountByCondition(keyword, filtersMap);
		setPaginator(totalCount);
		users = userDao.getUserByCondition(keyword, filtersMap, SearchConstant.SORTBY_ID_DESC, PAGE_SIZE, paginator.getCurrentPage());

	}
	
	private void setMerchantUsersPaging() throws Exception{
		Map<String, List<String>> filtersMap = new HashMap<String, List<String>>();
		List<String> roleFilterItem = new LinkedList<String>();
		roleFilterItem.add("4");
		filtersMap.put("roleId", roleFilterItem);
		ScUserDao userDao = DaoFactory.getScUserDao();
		int totalCount = userDao.getUserCountByCondition(keyword, filtersMap);
		setPaginator(totalCount);
		users = userDao.getUserByCondition(keyword, filtersMap, SearchConstant.SORTBY_ID_DESC, PAGE_SIZE, paginator.getCurrentPage());
	}

	private void setAllUsersPaging() throws Exception{
		ScUserDao userDao = DaoFactory.getScUserDao();
		int totalCount = userDao.getUserCountByCondition(keyword, null);
		setPaginator(totalCount);
		users = userDao.getUserByCondition(keyword, null, SearchConstant.SORTBY_ID_DESC, PAGE_SIZE, paginator.getCurrentPage());
	}
	
	public String warehouseAdminUsers() throws Exception {
		queryType = 15;
		setWarehouseAdminUsersPaging();
		return SUCCESS;
	}
	
	public String adminUsers() throws Exception {
		queryType = 14;
		setAdminUsersPaging();
		return SUCCESS;
	}
	
	public String commonUsers() throws Exception {
		queryType = 13;
		setCommonUsersPaging();
		return SUCCESS;
	}
	
	public String supplierUsers() throws Exception {
		queryType = 12;
		setSupplierUsersPaging();
		return SUCCESS;
	}
	
	public String merchantUsers() throws Exception {
		queryType = 11;
		setMerchantUsersPaging();
		return SUCCESS;
	}
	
	public String allUsers() throws Exception{
		queryType = 10;
		setAllUsersPaging();
		return SUCCESS;
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}

}
