package com.ybt.common.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	
	public static Properties prop ;
			
	 static {   
		prop =  new Properties();   
        InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");   
        try {   
            prop.load(in);   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
    }   
	 
	 
	 
	 public  static String getParam(String key){
		try {
			return  prop.getProperty(key).trim();   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	 } 
	 
	 public static void main(String argts[]){
		 System.out.println(getParam("path"));
	 }
}
