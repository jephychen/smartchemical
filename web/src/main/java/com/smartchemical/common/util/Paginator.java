/**
 * 
 */
package com.smartchemical.common.util;

/**
 * @author Jephy
 *
 */
public class Paginator {
	private int totalRecordCount;
	
	private int pageSize;
	
	private int totalPage;
	
	private int prePage;
	
	private int currentPage;
	
	private int nextPage;
	
	private int startPage;
	
	private int endPage;
	
	private String actionName;

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Paginator(int totalRecordCount, int currentPage, int pageSize){
		this.totalRecordCount = totalRecordCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		fillAttr();
	}
	
	public Paginator(int totalRecordCount, int currentPage, int pageSize, String actionName){
		this.totalRecordCount = totalRecordCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.actionName = actionName;
		fillAttr();
	}
	
	protected void fillAttr(){
		totalPage = totalRecordCount / pageSize + (((totalRecordCount % pageSize) != 0) ? 1 : 0);
		totalPage = totalPage < 1 ? 1 : totalPage;
		prePage = (currentPage - 1 < 1) ? 1 : (currentPage - 1);
		prePage = (prePage > totalPage) ? totalPage : prePage;
		nextPage = (currentPage + 1 > totalPage) ? totalPage : (currentPage + 1);
		nextPage = (nextPage < 1) ? 1 : nextPage;
		startPage = (currentPage - 2 < 2) ? 2 : (currentPage - 2);
		endPage = (currentPage + 2 > (totalPage - 1)) ? (totalPage - 1) : (currentPage + 2);
		currentPage = currentPage < 1 ? 1 : currentPage;
		currentPage = currentPage > totalPage ? totalPage : currentPage;
	}
}
