/**
 * 
 */
package com.smartchemical.api.frame.dao.company;

/**
 * @author Jephy
 *
 */
public enum CompanyType {
	VENDOR("供应商", 1), MERCHANT("经销商", 2);
	
	private String name;
	
	private int index;
	
	private CompanyType(String name, int index){
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
