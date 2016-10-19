/**
 * 
 */
package com.smartchemical.api.frame.dao.user;

/**
 * @author Jephy
 *
 */
public enum CompanyLicenseStatus {
	WAITING_TO_UPLOAD("未上传三证", 0),
	CHECKING_LICENSE("三证审核中", 1),
	CHECKED("三证审核通过", 2);
	
	private String name;
	
	private int index;
	
	private CompanyLicenseStatus(String name, int index){
		this.name = name;
		this.index = index;
	}
	
	public int getIndex(){
		return index;
	}
	
	@Override
	public String toString(){
		return this.name + "_" + this.index;
	}
}
