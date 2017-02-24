package com.ybt.common.util;    

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 共同方法
 */
public class CommonUtil {

	
	
	/**
	 * 比较
	 * 
	 * @param str1
	 * @param str2
	 * @return 
	 * 0 : str1 == str2 
	 * 1 : str1 > str2 
	 * -1 : str1 < str2
	 */
	public static int compare(String str1, String str2) {
		if (str1 == null || "".equals(str1) || "null".equals(str1)) {
			str1 = "0";
		}
		if (str2 == null || "".equals(str2) || "null".equals(str2)) {
			str2 = "0";
		}
		BigDecimal big1 = new BigDecimal(str1);
		BigDecimal big2 = new BigDecimal(str2);
		int iRet = big1.compareTo(big2);
		if (iRet > 0) {
			return 1;
		} else if (iRet < 0) {
			return -1;
		}

		return 0;
	}
	
	/**
	 * obj to String
	 * 
	 * @param obj
	 * @return
	 */
	public static String objToString(Object pObject) {
		String strValue = "";
		try {
			strValue = pObject.toString();
		} catch (Exception e) {
			strValue = "";
		}
		if ("null".equals(strValue)) {
			strValue = "";
		}
		return strValue;
	}

	/**
	 * 
	 * @param pObject
	 * @return
	 */
	public static BigDecimal objToBigDecimal(final Object pObject) {
		BigDecimal result = null;
		String str = "";

		if (pObject != null) {
			str = pObject.toString();
			str = str.replace(",", "");
		} else {
			str = "";
		}

		try {
			if ("".equals(str)) {
				return new BigDecimal(0);
			} else {
				result = new BigDecimal(str);
			}
		} catch (NumberFormatException e) {
			return BigDecimal.ZERO;
		}
		result = result.setScale(2, RoundingMode.HALF_UP);
		return result;
	}

	public static int objToint(final Object pObject) {
		int intReturn = 0;
		int intIndex = 0;
		try {
			if (pObject != null && !("".equals(pObject.toString().trim()))) {
				String strObject = pObject.toString().trim();
				intIndex = strObject.indexOf(".");
				if (intIndex == -1) {
					intReturn = new Integer(strObject).intValue();
				} else {
					intReturn = new Integer(strObject.substring(0, intIndex))
							.intValue();
				}
			} else {
				intReturn = 0;
			}
		} catch (Exception e) {
			intReturn = 0;
		}
		return intReturn;
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str) || "null".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * str1 加上 str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String add(Object str1, Object str2) {
		return objToString(objToBigDecimal(str1).add(objToBigDecimal(str2)));
	}
	/**
	 * str1 - str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String subtract(Object str1, Object str2) {
		return objToString(objToBigDecimal(str1).subtract(objToBigDecimal(str2)));
	}
	/**
	 * int类型相加
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String add2int(Object str1, Object str2) {
		return objToString(objToint(str1) + (objToint(str2)));
	}
	
	/**
	 * str1 加上 str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String add(String str1, String str2) {
		return objToString(objToBigDecimal(str1).add(objToBigDecimal(str2)));
	}

	/**
	 * str1 减去 str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String subtract(String str1, String str2) {
		return objToString(objToBigDecimal(str1)
				.subtract(objToBigDecimal(str2)));
	}
	
	/**
	 * str1 减去 str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal str1, BigDecimal str2) {
		return objToBigDecimal(str1)
				.subtract(objToBigDecimal(str2));
	}
	
	
}
