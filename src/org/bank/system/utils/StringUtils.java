package org.bank.system.utils;

public class StringUtils {
	
	public static boolean isNotBlank(Object item) {
		if(null == item || item.toString().length() == 0 || "".equals(item))
			return false;
		return true;
			
	}
	
	public static boolean isBlank(Object item) {
		if(null == item || item.toString().length() == 0 || "".equals(item))
			return true;
		return false;
			
	}


}
