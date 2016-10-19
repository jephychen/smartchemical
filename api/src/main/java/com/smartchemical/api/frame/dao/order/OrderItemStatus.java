/**
 * 
 */
package com.smartchemical.api.frame.dao.order;

/**
 * @author Jephy
 *
 */
public enum OrderItemStatus {
	
	WAITING_TO_BE_DELIVERED("正在发货", 1), WAITING_TO_BE_RECEIVED("等待收货", 2), 
	WAITING_TO_BE_GOT("等待提货", 3), RECEIVED("已收货", 4);
	
	private String name;
	
	private int index;
	
	private OrderItemStatus(String name, int index){
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
