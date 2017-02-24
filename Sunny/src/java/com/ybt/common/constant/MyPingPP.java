package com.ybt.common.constant;

import java.util.HashMap;
import java.util.Map;



/**
 * Ping++常量
 * 
 * @author zhz
 * 
 */
public class MyPingPP {
	 public static String Ping_AppId = "app_bjbnjH9mzjTGrT0u";// ping++ appId
	 public static String Ping_ApiKey_Test = "sk_test_ar1abHb1iXz1L84izDS4mb1G";// ping++ apiKey 测试
	 public static String Ping_ApiKey_Live = "sk_live_Di10KOHaf1mH00aDWLWXPOyH";//  ping++ apiKey 生产 
	 public static String ENCODINGAESKEY ="7gEZwoSSQeMzipE2rnHsjy9C6y9aWOnJ4HD7FiYDfb9";// (消息加解密密钥)
	 public static String YBT_PAY = "ybt_pay";//芸备胎余额支付-代码
	 public static String YBT_TRANSFER_PAY = "ybt_transfer_pay";//芸备胎转账
	 public static String YBT_ACCOUNT_ID = "YBT_ZZH";//芸备胎总账户id
	 
	 public static String SUN_CARD = "SUNCARD";//阳光卡
	 
	 public final static Map<String,String> CHANNEL_MAP = new HashMap<String,String>() {
		private static final long serialVersionUID = 3765747270724503207L;
		{    
		    put("alipay", "支付宝手机支付");    
		    put("alipay_wap", "支付宝手机网页支付"); 
		    put("alipay_pc_direct", "支付宝 PC 网页支付");    
		    put("alipay_qr", "支付宝扫码支付"); 
		    put("bfb", "百度钱包移动快捷支付");    
		    put("bfb_wap", "百度钱包手机网页支付"); 
		    put("upacp", "银联全渠道支付");    
		    put("upacp_wap", "银联全渠道手机网页支付"); 
		    put("upacp_pc", "银联 PC 网页支付");    
		    put("cp_b2b", "银联企业网银支付"); 
		    put("wx", "微信支付");    
		    put("wx_pub", "微信公众账号支付"); 
		    put("wx_pub_qr", "微信公众账号扫码支付");    
		    put("yeepay_wap", "易宝手机网页支付"); 
		    put("jdpay_wap", "京东手机网页支付");    
		    put("cnp_u", "应用内快捷支付（银联）"); 
		    put("cnp_f", "应用内快捷支付（外卡）");    
		    put("applepay_upacp", "Apple Pay"); 
		    put(YBT_PAY, "芸备胎支付");
		    put(YBT_TRANSFER_PAY, "芸备胎自动转账");
		}};
}
