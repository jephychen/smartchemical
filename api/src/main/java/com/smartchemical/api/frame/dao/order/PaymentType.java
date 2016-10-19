/**
 * 
 */
package com.smartchemical.api.frame.dao.order;

/**
 * @author Jephy
 *
 */
public enum PaymentType {
	ONLINE("在线支付", 1), TRANSFER("银行转账", 2), ACCEPTANCE_BILL("银行承兑汇票", 3)
	, DEPOSIT("余额支付", 4);
	
	private String name;
	
	private int index;
	
	private PaymentType(String name, int index){
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
