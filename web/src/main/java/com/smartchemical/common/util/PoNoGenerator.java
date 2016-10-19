/**
 * 
 */
package com.smartchemical.common.util;

import java.sql.Date;

/**
 * @author Jephy
 *
 */
public class PoNoGenerator {
	@SuppressWarnings("deprecation")
	public static String makePurchaseOrderNo(int poId) {
		Date now = new Date(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		sb.append(now.getYear());
		sb.append(now.getMonth());
		sb.append(10000000 + poId);
		return sb.toString();
	}
	
	public static String makeAcceptanceBillPicName(int userId, String poNo, int seq){
		return userId + "_" + poNo + "_" + seq;
	}
	
	public static String makeCompanyLicensePicName(int userId, int seq){
		return userId + "_" + seq;
	}
}
