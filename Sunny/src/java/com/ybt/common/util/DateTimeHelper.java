package com.ybt.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 
 * Title: Deliver Store
 * 
 * Author: LiTian
 * 
 * Date: 2012-1-18
 * 
 * Description: 日期和时间帮助类
 * 
 */
public class DateTimeHelper {

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DEFAULT_DATE_TIME_FORMATE = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd
	 */
	public static final String DEFAULT_DATE_FORMATE = "yyyy-MM-dd";

	/**
	 * yyyy年MM月dd日
	 */
	public static final String DEFAULT_CN_DATE_FORMATE = "yyyy年MM月dd日";

	/**
	 * yyyy-MM
	 */
	public static final String DEFAULT_DATE_YM_FORMATE = "yyyy-MM";

	/**
	 * yyyy年MM月
	 */
	public static final String DEFAULT_CN_DATE_YM_FORMATE = "yyyy年MM月";

	/**
	 * yyyy-ww
	 */
	public static final String DEFAULT_DATE_YW_FORMATE = "yyyy-ww";

	/**
	 * yyyy年第ww周
	 */
	public static final String DEFAULT_CN_DATE_YW_FORMATE = "yyyy年第ww周";

	/**
	 * yyyy
	 */
	public static final String DEFAULT_DATE_Y_FORMATE = "yyyy";

	/**
	 * yyyy年
	 */
	public static final String DEFAULT_CN_DATE_Y_FORMATE = "yyyy年";

	/**
	 * yyyy-01-01
	 */
	public static final String DEFAULT_DATE_Y0101_FORMATE = "yyyy-01-01";

	/**
	 * yyyy-MM-dd 00:00:00
	 */
	public static final String DEFAULT_DATE_STAR_FORMATE = "yyyy-MM-dd 00:00:00";
	
	/**
	 * yyyy-MM-dd 23:59:59
	 */
	public static final String DEFAULT_DATE_END_FORMATE = "yyyy-MM-dd 23:59:59";
	
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String DEFAULT_DATE_HM_FORMATE = "yyyy-MM-dd HH:mm";
	
	/**
	 * yyyy-MM-DD
	 */
	public static final String DEFAULT_DATE_MD_FORMATE = "yyyy-MM-DD";
	
	/**
	 * yyyyMMdd
	 */
	public static final String DEFAULT_DATE_QD_FORMATE = "yyyyMMdd";
	
	/**
	 * yyyyMMDDHHmmss
	 */
	public static final String DEFAULT_DATE_MDS_FORMATE = "yyyyMMDDHHmmss";


	/**
	 * Date exchange String
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return String
	 */
	public static String dateTimeToStr(Date date, String format) {
		if(date==null){
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * String exchange Date
	 * 
	 * @param source
	 *            日期
	 * @param format
	 *            格式
	 * @return Date
	 */
	public static Date strToDatetime(String source, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);

		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 日期格式替换 String exchange Date
	 * 
	 * @param source
	 *            日期
	 * @param format
	 *            格式
	 * @return String
	 */
	public static String dateTimeFormatReplace(String source, String format) {
		StringBuffer sb = new StringBuffer();

		if (DEFAULT_CN_DATE_FORMATE.equals(format)) {
			sb.append(source.substring(0, 4)).append("年").append(source.substring(5, 7)).append("月").append(source.subSequence(8, 10)).append("日");
		} else if (DEFAULT_CN_DATE_YW_FORMATE.equals(format)) {
			sb.append(source.substring(0, 4)).append("年第").append(source.substring(5, 7)).append("周");
		} else if (DEFAULT_CN_DATE_YM_FORMATE.equals(format)) {
			sb.append(source.substring(0, 4)).append("年").append(source.substring(5, 7)).append("月");
		} else if (DEFAULT_CN_DATE_Y_FORMATE.equals(format)) {
			sb.append(source.substring(0, 4)).append("年");
		}

		return sb.toString();
	}

	/**
	 * 月份差值
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return int
	 */
	public static int getMonthDiff(Date beginTime, Date endTime) {
		Calendar beginCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		beginCalendar.setTime(beginTime);
		endCalendar.setTime(endTime);

		int years = endCalendar.get(Calendar.YEAR) - beginCalendar.get(Calendar.YEAR);
		int months = endCalendar.get(Calendar.MONTH) - beginCalendar.get(Calendar.MONTH);

		return 12 * years + months;
	}

	/**
	 * 日期差值 分
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return long
	 */
	public static long getDateDiff(Date beginTime, Date endTime) {
		return (endTime.getTime() - beginTime.getTime()) / (1000 * 60);
	}
	
	
	/**
	 *@description 获取时间的0点
	 *@time    创建时间:2016年3月19日上午11:51:15
	 *@author   zhz
	 */
	@SuppressWarnings("unused")
	private static Date getTimeOfZero(Date date) {
        long current=date.getTime();
		long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
		long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
		long yesterday=System.currentTimeMillis()-24*60*60*1000;//昨天的这一时间的毫秒数
        return new Date(zero);
    }
	/**
	 *@description 获取时间的23点59分59秒
	 *@time    创建时间:2016年3月19日上午11:51:15
	 *@author   zhz
	 */
	private static Date getTimeOf24(Date date) {
        long current=date.getTime();
		long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
		long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
        return new Date(twelve);
    }
	/**
	 *@description 当前时间是当天的多少秒
	 *@time    创建时间:2016年3月19日上午11:51:15
	 *@author   zhz
	 */
	public static Long getTimeSortSecond(Date date) {
        return getDateDiff(getTimeOfZero(date),date)*60;
    }
	
	
	/**
	 * 日期差值 (分、时、天)
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return long
	 */
	public static String getDateDiffFormat(Date beginTime, Date endTime) {
		Long l = getDateDiff(beginTime,endTime);
		String format = "分钟";
		if(l>60||l==60){
			l = l/60;
			format="小时";
			
			if(l>24||l==24){
				l = l/24;
				format="天";
			}
		}
		
		return l+format;
	}
	
	
	public static void main(String[] args) {
		Date d1 = new Date();
//		Date d2 = DateUtil.getBeforDayByDate(d1, 1);
		getDateDiff(getTimeOfZero(d1),d1);
		System.out.println(getTimeOfZero(d1));
		System.out.println(getTimeOf24(d1));
		System.out.println(getDateDiff(getTimeOfZero(d1),getTimeOf24(d1))*60);
	}
}
