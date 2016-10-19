/**
 * 
 */
package com.smartchemical.common.util;

/**
 * @author Jephy
 *
 */
public class StringUtil {
	public static String trimLongDesc(String longDesc){
		String result = longDesc.replaceAll("\t", "");
		result = result.replaceAll("\r", "");
		return result;
	} 
}
