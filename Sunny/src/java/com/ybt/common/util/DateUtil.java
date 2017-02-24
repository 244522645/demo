package com.ybt.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	/*
	 * 比较日期
	 */
	public static boolean compareDate(Date a, Date b) {
		boolean result = false;
		if (a.getTime() > b.getTime()) {
			result = true;
		}
		return result;
	}

	public static Date StringToDate(String dateStr, String formatStr) {
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static String getTime(Date dt){
		DateFormat df = new SimpleDateFormat("HH:mm");//设置显示格式
		String nowTime="";
		nowTime= df.format(dt);
		return nowTime;
	}
	public static String getDateTime(String format,Date dt){
		DateFormat df = new SimpleDateFormat(format);//设置显示格式
		String nowTime="";
		nowTime= df.format(dt);
		return nowTime;
	}
	
	
	/**
	 * 取月份最后日期
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastDayOfDate(int year,int month) {
		int kLastDates[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if(month>12 || month<0){
            month = 0;
        }
        if(month==2 && isLeapYear(year)){
            return kLastDates[month] + 1;
        }else{
            return kLastDates[month]; 
       }
	}

	/**
	 * 遍历两日期间所有日期
	 * 
	 * @param date1
	 * @param date2
	 */
	@SuppressWarnings("deprecation")
	public static void getDatesByDate(Date beginDate, Date endDate) {

		// TODO Auto-generated method stub
		GregorianCalendar gc = new GregorianCalendar();
		if (beginDate.getMonth() == endDate.getMonth()) {
			for (int i = beginDate.getDate(); i <= endDate.getDate(); i++) {
				Date date = new Date();
				date.setDate(i);
			}
		} else {
			gc.setTime(beginDate);
			int i = 1;
			while (!beginDate.equals(endDate)) {
				gc.add(Calendar.DAY_OF_MONTH, i);
				beginDate = gc.getTime();
			}
		}
	}

	/**
	 * 某日期几天前
	 * 
	 * @return
	 */
	public static Date getBeforDayByDate(Date date, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -day);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//return sdf.format(calendar.getTime());
		return calendar.getTime();
	}
	/**
	 * 某日期几月前
	 * 
	 * @return
	 */
	public static Date getBeforMonthByDate(Date date, int month) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -month);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//return sdf.format(calendar.getTime());
		return calendar.getTime();
	}
	/**
	 * 某日期几年前
	 * 
	 * @return
	 */
	public static Date getBeforYearByDate(Date date, int year) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, -year);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//return sdf.format(calendar.getTime());
		return calendar.getTime();
	}
	/**
	 * 某日期几天后
	 * 
	 * @return
	 */
	public static Date getDeforDayByDate(Date date, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, +day);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//return sdf.format(calendar.getTime());
		return calendar.getTime();
	}

	/**
	 * 过去几天前的日期
	 * 
	 * @return
	 */
	public static String getBeforDay(int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -day);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}

	/**
	 * 取30天前的日期
	 * 
	 * @return
	 */
	public static String getLastMonthDay() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -30);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}
	public static Date get30Day() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -29);
		return calendar.getTime();
	}
	public static Date get7Day() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -6);
		return calendar.getTime();
	}
	/**
	 * 获取时间-精确到下几小时
	 * 
	 * @param hours
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getTimeForNextHour(int hours) {
		Date date = new Date();
		date.setHours(date.getHours() + hours);
		return getDateFormat(date, "yyyy-MM-dd HH:00:00");
	}

	/**
	 * 获取日期几星期
	 * 
	 * @return 2012-04-27 星期五
	 */
	public static String getWeekAndMonth() {
		Date date = new Date();
		String d = getDateFormat(date, "yyyy-MM-dd");
		String week = getDayOfWeek(getWeekByDate(date));
		return d + " " + week;
	}

	/**
	 * 获取当月第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		Calendar curCal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		curCal.set(Calendar.DAY_OF_MONTH, 1);
		Date date = curCal.getTime();
		return datef.format(date);
	}

	/**
	 * 获取月第一天
	 * 
	 * @param curCal
	 * @return
	 */
	public static String getFirstDayOfMonth(Calendar curCal) {
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		curCal.set(Calendar.DAY_OF_MONTH, 1);
		Date date = curCal.getTime();
		return datef.format(date);
	}

	/**
	 * 获取当月最后一天
	 * 
	 * @return
	 */
	public static String getLastDayOfMonth() {
		Calendar curCal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");

		curCal.set(Calendar.DATE, 1);
		curCal.roll(Calendar.DATE, -1);
		Date date = curCal.getTime();
		return datef.format(date);
	}

	/**
	 * 获取月最后一天
	 * 
	 * @param curCal
	 * @return
	 */
	public static String getLastDayOfMonth(Calendar curCal) {
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		curCal.set(Calendar.DATE, 1);
		curCal.roll(Calendar.DATE, -1);
		Date date = curCal.getTime();
		return datef.format(date);
	}

	/**
	 * 获取当前日期、时间
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getCurrentTime(String pattern) {
		SimpleDateFormat date = new SimpleDateFormat(pattern);
		Date current = new Date();
		return date.format(current);
	}

	/**
	 * 根据日期获取星期
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0)
			week = 7;
		return week;
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getDateFormat(java.util.Date date, String pattern) {
		if(date==null&&pattern!=null&&pattern.equals("1"))
			return "";
		if(date==null)
			return "0";
		if(pattern!=null&&pattern.equals("1")){
			if(date==null)
			return "";
			else{
				pattern = "yyyy-MM-dd";
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				sdf.applyPattern(pattern);
				return sdf.format(date);
			}
		}
		
		if(pattern==null || pattern.length()==0)
			pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}

	/**
	 * 获取星期
	 * 
	 * @param day
	 * @return
	 */
	public static String getDayOfWeek(int day) {
		String w = "星期日";
		switch (day) {
		case 1:
			w = "星期一";
			break;
		case 2:
			w = "星期二";
			break;
		case 3:
			w = "星期三";
			break;
		case 4:
			w = "星期四";
			break;
		case 5:
			w = "星期五";
			break;
		case 6:
			w = "星期六";
			break;
		}
		return w;
	}
	
	/**
	 * 判断是不是闰年
	 * @param year
	 * @return
	 */
    public static boolean isLeapYear(int year){
        if (year % 4 != 0) 
           return false;
          if (year % 400 == 0) 
           return true;
          return year % 100 != 0;
       }       

    public static long daySum(Date beginDate ,Date endDate){
    	long between = (endDate.getTime() - beginDate.getTime()) / 1000;// 除以1000是为了转换成秒
    	long day = between / (24 * 3600);//周期间距多少天
    	return day;
    }
    /**
     * 距离生日还有多少天(String类型，日期格式yyyyMMdd)
     * @return int   天数
     * @ParseException  -1  //日期格式错误，或者转换异常
     */
    public static int todayBetweenBirthday(String birthday){
    	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
        //String birthday = "19780104";
        Calendar cToday = Calendar.getInstance(); // 存今天
        Calendar cBirth = Calendar.getInstance(); // 存生日
        try {
			cBirth.setTime(myFormatter.parse(birthday)); // 设置生日
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;   //日期格式错误，或者转换异常
		}
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
        int days; 
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
            // 生日已经过了，要算明年的了
            days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            days += cBirth.get(Calendar.DAY_OF_YEAR);
        } else {
            // 生日还没过
            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }
       /* // 输出结果
        if (days == 0) {
            System.out.println("今天生日");
        } else {
            System.out.println("距离生日还有：" + days + "天");
        }*/
        return days;
    }
    
    /**
     * 距离生日还有多少天
     * @return int   天数
     * @ParseException  -1  //日期格式错误，或者转换异常
     */
    public static int getBirthCount(Date d1,Date d2){
    	String formatStr ="yyyy-MM-dd";
    	int days = (int)((DateUtil.StringToDate(DateUtil.getDateFormat(d1, formatStr), formatStr).getTime() - DateUtil.StringToDate(DateUtil.getDateFormat(d2, formatStr), formatStr).getTime())/86400000);
    	System.out.println(days);
        return days;
    }
    
	public static Date getBirthDay(Date birthDay) {
		Date d = new Date();
		String year=getDateFormat(d, "yyyy");
		String birthstr=getDateFormat(birthDay, "MMdd");
		Date birthtest = StringToDate(year+birthstr, "yyyyMMdd");
		String todystr=getDateFormat(d, "MMdd");
		Date todaytest = StringToDate(year+todystr, "yyyyMMdd");
		
		if(!compareDate(birthtest,todaytest)){
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(birthtest);
			calendar.add(Calendar.YEAR, +1);
			return calendar.getTime();
		}
			
		return birthtest;
	}
    
	public static void main(String[] args){
		
	}
}
