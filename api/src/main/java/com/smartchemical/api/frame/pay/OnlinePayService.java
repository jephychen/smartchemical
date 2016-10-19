/**
 * 
 */
package com.smartchemical.api.frame.pay;

/**
 * @author Jephy
 *
 */
public interface OnlinePayService {
	public boolean pay(String poNo, String productName, float totalAmount);
}
