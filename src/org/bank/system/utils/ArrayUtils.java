package org.bank.system.utils;

public class ArrayUtils {
	
	/**
	 * 查询字符是否存在数组中
	 * @return
	 */
	public static boolean contains(String[] strs,String item) {
		if(null == strs || strs.length == 0)return false;
		for (String str : strs) {
			if(str.equals(item))return true;
		}
		return false;
	}

}
