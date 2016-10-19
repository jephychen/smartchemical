/**
 * 
 */
package com.smartchemical.api.frame.dao.order;

/**
 * @author Jephy
 *
 */
public enum OrderStatus {
	WAITING_TO_BE_PAID("等待付款", 1), PAYED("已付款", 2), 
	RECEIVED("已收货", 3),
	
	SUB_EMPTY("空", -1),
	/**
	 * 承兑汇票状态属于等待付款的子状态
	 * */
	SUB_UPLOADING_ACCEPTANCEBILL("等待上传承兑汇票", 101),
	SUB_CHECKING_ACCEPTANCEBILL("审核承兑汇票中", 102),
	SUB_RECEIVING_ACCEPTANCEBILL("等待接收承兑汇票", 103);
	
	private String name;
	
	private int index;
	
	private OrderStatus(String name, int index){
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
