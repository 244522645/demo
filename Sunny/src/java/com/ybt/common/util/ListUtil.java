package com.ybt.common.util;

import java.util.List;

public class ListUtil {
	
	public static boolean isEmpty(List<?> list){
		if(list==null||list.isEmpty())
			return true;
		else 
			return false;
	}
	
	public static boolean isNotEmpty(List<?> list){
		if(list==null||list.isEmpty())
			return false;
		else 
			return true;
	}
}
