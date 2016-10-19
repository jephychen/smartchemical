/**
 * 
 */
package com.smartchemical.common.util;

import java.sql.Timestamp;

/**
 * @author Jephy
 *
 */
public class ScUtil {
	private static final long ONE_DAY = 24 * 60 * 60 * 1000;
	
	public static Timestamp makeSinceDate(int lastMonth){
		long now = System.currentTimeMillis();
		long since;
		switch (lastMonth){
			case 1:
				since = now - ONE_DAY * 31;
				break;
			case 3:
				since = now - ONE_DAY * 92;
				break;
			case 6:
				since = now - ONE_DAY * 183;
				break;
			case 12:
				since = now - ONE_DAY * 366;
				break;
			case 0:
				since = 0;
				break;
			default:
				since = now - ONE_DAY * 92;
				break;
		}
		return new Timestamp(since);
	}
}
