/**
 * 
 */
package com.smartchemical.api.frame.dao.order;

/**
 * @author Jephy
 *
 */
public enum DeliverType {
	VERDOR("厂商配送", 1), ZHIXUAN("智选配送", 2), USER("用户自提", 3);
	
	private String name;
	
	private int index;
	
	private DeliverType(String name, int index){
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
