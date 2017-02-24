/*   
 * Copyright (c) 2015-2020 *** Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * ***. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with ***.   
 *   
 */     
package test.com.ybt.base;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.ybt.common.util.DateUtil;

public class StringTest {

	public static void main(String[] args) {
		/*String[] strArr = new String[]{"aa","bb","cc","dd","ee","ff"};
		System.out.println(Arrays.toString(strArr).replace("[", "").replace("]", ""));
		
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); 
		calendar.add(Calendar.MINUTE, 1440); //设定绑定时间24小时后
	    System.out.println(DateUtil.getDateFormat(calendar.getTime(), "yyyy-MM-dd hh:mm:ss"));
	    
	    Date d = DateUtil.StringToDate(DateUtil.getDateFormat(new Date(), "yyyy-MM-dd")+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
	    System.out.println(DateUtil.getDateFormat(d, "yyyy-MM-dd HH:mm:ss"));
	    
	    */
	    int expire = 2592000;
		Date expireTime = new Date(System.currentTimeMillis()+1000L*expire);
		
		System.out.println(DateUtil.getDateFormat(expireTime, "yyyy-MM-dd hh:mm:ss"));
		
		
		 Random rd = new Random(); //创建一个Random类对象实例
	     int x = rd.nextInt(3);
	     System.out.println(x);
	}

}
  