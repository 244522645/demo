package com.ybt.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DataFormatUtil {
    

	public static Date objectToDate(SimpleDateFormat format, Object time) {
		Date dateTime = null;
		try {
			dateTime = format.parse(time == null ? "" : time.toString());
		} catch (Exception e) {

		}
		return dateTime;
	}
	public static String objectToString(Object obj) {
		String Str = obj == null ? "" : obj.toString();
		return Str;
	}
	
	
	//数据库查询数值类型转Integer
	public static Integer objectToInt(Object d) {
		if(d!=null){
			if(d.getClass().equals(Integer.class)){
				return (Integer)d;
			}
			return new Double((Double)d).intValue();
		}
		return null;
	}
	
	//数据库查询数值类型转Integer
	public static Double objectToDouble(Object d) {
		if(d!=null){
			if(d.getClass().equals(Double.class)){
				return (Double)d;
			}
			return (Double)d;
		}
		return null;
	}
	
	//去除数组中重复的记录  
	public static String[] arrayUnique(String[] a) {  
	    // array_unique  
	    List<String> list = new LinkedList<String>();  
	    for(int i = 0; i < a.length; i++) {  
	        if(!list.contains(a[i])) {  
	            list.add(a[i]);  
	        }  
	    }  
	    return (String[])list.toArray(new String[list.size()]);  
	}  
	
	public static Double formatDouble(double d,String format){
		DecimalFormat df = new DecimalFormat(format);    
		d = Double.parseDouble(df.format(d)); 
		return d;
	}
	
}
