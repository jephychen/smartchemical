/**
 * 
 */
package com.smartchemical.common.util;

import java.text.DecimalFormat;

/**
 * @author Jephy
 *
 */
public class DecimalFormatUtil {
	public static float formatFloat(float src){
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		return Float.parseFloat(decimalFormat.format(src));
	}
	
	public static String formatFloat2String(float src){
		if (src == 0){
			return "0.00";
		}
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		return decimalFormat.format(src);
	}
}
