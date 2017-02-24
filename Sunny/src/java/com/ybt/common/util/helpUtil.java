package com.ybt.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class helpUtil {
	
	/**
	 *@name    判空
	 *@description 相关说明
	 *@time    创建时间:2016年2月14日下午3:35:55
	 *@param obj
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public static boolean isEmpty(Object obj){
		if(obj==null){
			return true;
		}
		if (obj instanceof List<?>) {
		return ((List<?>) obj).isEmpty();
		} 
		else if (obj instanceof String) {
		   return ((String) obj).trim().equals("");
		}
		else {
			return false;
		}
	}
	
    /**
     *@name    根据地址查询坐标
     *@description 相关说明
     *@time    创建时间:2016年2月14日下午3:33:48
     *@param addr
     *@return lng经度 lat纬度
     *@author   高艳花
     *@history 修订历史（历次修订内容、修订人、修订时间等）
     */
    public static Object[] getCoordinate(String addr){ 
        String lng = null;//经度
        String lat = null;//纬度
        try {
        	String address = java.net.URLEncoder.encode(addr, "UTF-8"); 
	        String key = "8ef6e12f263cb7044e40a51fa6815f88"; 
	        String url = String .format("http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s", address, key);
	        URL myURL = null; 
	        URLConnection httpsConn = null; 
	        myURL = new URL(url); 
	        InputStreamReader insr = null;
	        BufferedReader br = null;
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理 
            if (httpsConn != null) { 
                insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8"); 
                br = new BufferedReader(insr); 
                String data = null; 
                int count = 1;
                while((data= br.readLine())!=null){ 
                    if(count==5){
                        lng = (String)data.subSequence(data.indexOf(":")+1, data.indexOf(","));//经度
                        count++;
                    }else if(count==6){
                        lat = data.substring(data.indexOf(":")+1);//纬度
                        count++;
                    }else{
                        count++;
                    }
                } 
            } 
        } catch (Exception e) {
        	lng="";
        	lat="";
            e.printStackTrace();
        }
        return new Object[]{lng,lat}; 
    } 
    
    /**
     *@name    转成json格式
     *@description 相关说明
     *@time    创建时间:2016年2月26日下午1:53:37
     *@param obj
     *@return
     *@author   高艳花
     *@history 修订历史（历次修订内容、修订人、修订时间等）
     */
    public static Object toJson(Object obj){ 
    	 net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(obj);
    	 return jsonArray;
    }

    /**
     *@name    获取订单号
     *@description 当前时间戳加三位随机数
     *@time    创建时间:2016年2月26日下午2:06:13
     *@return
     *@author   高艳花
     *@history 修订历史（历次修订内容、修订人、修订时间等）
     */
    public static String getOrderNo(){
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	String str1 = sdf.format(date);
    	Random r =new Random();
    	int a = r.nextInt(1000);
    	DecimalFormat df=new DecimalFormat("000");
        String str2=df.format(a);
    	return str1+str2;
    }
    
    /**
     *@name    计算两店铺间距离
     *@description 当前时间戳加三位随机数
     *@time    创建时间:2016年2月26日下午2:06:13
     *@return
     *@author   高艳花
     *@history 修订历史（历次修订内容、修订人、修订时间等）
     */
    /*private final static double PI = 3.14159265358979323; // 圆周率
    private final static double R = 6371229; // 地球的半径
    
    public static String getDistance(YbtBusiness myBus,YbtBusiness bus) {
    	String mylon=myBus.getLongitude();
    	String mylat=myBus.getLatitude();
    	String lon=bus.getLongitude();
    	String lat=bus.getLatitude();
    	double longt1= isEmpty(mylon)?0:Double.valueOf(mylon);
    	double lat1= isEmpty(mylat)?0:Double.valueOf(mylat);
    	double longt2= isEmpty(lon)?0:Double.valueOf(lon);
    	double lat2= isEmpty(lat)?0:Double.valueOf(lat);
        double x, y, distance;
        x = (longt2 - longt1) * PI * R
                * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
        y = (lat2 - lat1) * PI * R / 180;
        distance = Math.hypot(x, y);
        DecimalFormat df =new DecimalFormat("#.00");
        //单位为km,保留两位小数
        return df.format(distance/1000);
    }
    */
}
