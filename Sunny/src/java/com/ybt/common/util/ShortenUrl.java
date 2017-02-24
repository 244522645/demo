package com.ybt.common.util;
/*
 * 百度生成短网址  请参见：http://dwz.cn/api
 * 
 * 2.怎样生成一个短网址？
	请求：向dwz.cn/create.php发送post请求，发送数据包括url=长网址
	返回：json格式的数据
	status!=0 出错，查看err_msg获得错误信息（UTF-8编码）
	成功，返回生成的短网址 tinyurl字段
	
	3.自定义短网址
	请求：向dwz.cn/create.php发送post请求，发送数据包括url=长网址&alias=自定义网址
	返回：json格式的数据
	Status!=0 出错，查看err_msg获得错误信息（UTF-8编码）
	成功，返回生成的短网址 tinyurl字段
	
	4.显示原网址
	请求：向dwz.cn/query.php发送post请求，发送数据包括tinyurl=查询的短地址
	返回：json格式的数据
	status!=0 出错，查看err_msg获得错误信息（UTF-8编码）
	成功，返回原网址 longurl字段
 */
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.ybt.common.bean.BaiduShortUrlBean;

/**
 * @file ShortenUrl.java 创建时间:2015年11月27日上午10:55:12
 * @description 百度生成短网址 
 * @module 模块: 模块名称
 * @author   张洪征
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
public class ShortenUrl {
	private static Logger logger = Logger.getLogger(ShortenUrl.class);
	
	/**
	 *@description 2.怎样生成一个短网址？和自定义短网址(非自定义默认dwz.cn)
					请求：向dwz.cn/create.php发送post请求，发送数据包括url=长网址 &alias=自定义网址(无论写什么百度都提示 已存在，估计是取消了自定义功能)
					返回：json格式的数据
					status!=0 出错，查看err_msg获得错误信息（UTF-8编码）
					成功，返回生成的短网址 tinyurl字段
	 *@time    创建时间:2015年11月27日上午10:59:10
	 *@param url   长网址
	 *@param alias  自定义网址 可为空
	 *@return  
	 *@author   张洪征
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public static String getShortenUrl(String url,String alias) {
		
		String charset = "UTF-8";
		String APIUrl = "http://dwz.cn/create.php";
		Map<String, String> paramsMap = new HashMap<String, String>();
		if(url!=null && !"".equals(url))
			paramsMap.put("url", url);
		if(alias!=null && !"".equals(alias))
			paramsMap.put("alias", alias);
		
		String jsonResult = HttpConnectionUtil.httpPost(APIUrl, paramsMap,charset);
		
		if (jsonResult != null) {
			try {
				Gson gson = new Gson();
				BaiduShortUrlBean bean = gson.fromJson(jsonResult,BaiduShortUrlBean.class);
				if (bean != null && bean.getStatus() == 0) {//成功
					if(bean.getTinyurl()!=null && !"".equals(bean.getTinyurl())){
						return bean.getTinyurl();//返回短连接
					}
				}else{
					logger.error("百度短连接接口返回错误！" + bean.getErr_msg());
				}
			} catch (Exception e) {
				logger.error("百度短连接接口数据有误！" + url+e);
			}
		} else {
			logger.error("百度短连接调用失败！" + url);
		}
				
		return null;
	}
	
	/**
	 *@description 4.显示原网址
					请求：向dwz.cn/query.php发送post请求，发送数据包括tinyurl=查询的短地址
					返回：json格式的数据
					status!=0 出错，查看err_msg获得错误信息（UTF-8编码）
					成功，返回原网址 longurl字段
	 *@time    创建时间:2015年11月27日上午10:59:10
	 *@param url   长网址
	 *@param alias  自定义网址 可为空
	 *@return  
	 *@author   张洪征
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public static String queryUrl(String tinyurl) {
		
		String charset = "UTF-8";
		String APIUrl = "http://dwz.cn/query.php";
		Map<String, String> paramsMap = new HashMap<String, String>();
		if(tinyurl!=null && !"".equals(tinyurl))
			paramsMap.put("tinyurl", tinyurl);
		
		String jsonResult = HttpConnectionUtil.httpPost(APIUrl, paramsMap,charset);
		
		if (jsonResult != null) {
			try {
				Gson gson = new Gson();
				BaiduShortUrlBean bean = gson.fromJson(jsonResult,BaiduShortUrlBean.class);
				if (bean != null && bean.getStatus() == 0) {//成功
					if(bean.getLongurl()!=null && !"".equals(bean.getLongurl())){
						return bean.getLongurl();//返回短连接
					}
				}
			} catch (Exception e) {
				logger.error("百度短连接接口数据有误！" + tinyurl+e);
			}
		} else {
			logger.error("百度短连接调用失败！" + tinyurl);
		}
				
		return null;
	}
	

}
