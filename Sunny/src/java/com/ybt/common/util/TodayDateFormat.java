package com.ybt.common.util;

import java.util.Calendar;
import java.util.Date;

public class TodayDateFormat {

  
    public static String format(Date date) {  
		
		Calendar today = Calendar.getInstance();	//今天
		//  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set( Calendar.HOUR_OF_DAY, 0);
		today.set( Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		
		Calendar yesterday = Calendar.getInstance();	//昨天
		yesterday.set(Calendar.DAY_OF_MONTH,yesterday.get(Calendar.DAY_OF_MONTH)-1);
		yesterday.set( Calendar.HOUR_OF_DAY, 0);
		yesterday.set( Calendar.MINUTE, 0);
		yesterday.set(Calendar.SECOND, 0);
		
		Calendar current = Calendar.getInstance();
		current.setTime(date);
		current.set( Calendar.HOUR_OF_DAY, 0);
		current.set( Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 1);

		if(!current.before(today)){
			return "今天 ";
		}else if(current.before(today) && current.after(yesterday)){
			
			return "昨天 ";
		}else{
			return DateUtil.getDateFormat(date, "MM/dd");
		}
    }  
  
  
}
