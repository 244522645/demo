package com.ybt.common.constant;

import wechat.util.ExpireSet;

/**
 * 微信常量
 * 
 * @author buddy
 * 
 */
public class Wechat {
	// 1.云备胎       应用
	 public static String TOKEN = "sunny";// 令牌
	 public static String EncodingAESKey = "7gEZwoSSQeMzipE2rnHsjy9C6y9aWOnJ4HD7FiYDfb9";// (消息加解密密钥)
//	 //芸备胎
//	 public static String APPID = "wxf4342627a3594c95";// (应用ID)
//	 public static String APPSECRET = "a5fb32d35b2ffc26a09a10daf8d43d76";// 密钥
////	 
//	 //服务测试
//	 public static String APPID = "wx42628b4a8c5d6ef0";// (应用ID)
//	 public static String APPSECRET = "2fb7878e7735cfd36b112082b674e3fb";// 密钥	 public static String EncodingAESKey = "7gEZwoSSQeMzipE2rnHsjy9C6y9aWOnJ4HD7FiYDfb9";// (消息加解密密钥)
	 
	 
	 //本机测试
//	 public static String APPID = "wxf6d999577f4187bb";// (应用ID)
//	 public static String APPSECRET = "bc5b0328665fc400c213414a065ac97d";// 密钥	 public static String EncodingAESKey = "7gEZwoSSQeMzipE2rnHsjy9C6y9aWOnJ4HD7FiYDfb9";// (消息加解密密钥)

//	 //bqy本机测试
	 public static String APPID = "wxb88f20f1d24b91a6";// (应用ID)
	 public static String APPSECRET = "9de5ac681488dc4ca97235f42a42b2c9";// 密钥	 public static String EncodingAESKey = "7gEZwoSSQeMzipE2rnHsjy9C6y9aWOnJ4HD7FiYDfb9";// (消息加解密密钥)

//	 
	 
	//用户微信登录号
	public static String WECATOPENID ="WECATOPENID";
	//code
	public static String WECHATCODE ="code";

	//经度 默认北京
	public static String LONGITUDE ="116.397428";
	//纬度  默认北京
	public static String LATITUDE ="39.90923";
	// 重复通知过滤 时效60秒
	public static ExpireSet<String> expireSet = new ExpireSet<String>(60);
	// 创建菜单（PODT）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 菜单查询（GET）
	public final static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 菜单删除（GET）
	public final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	// 获取access_token
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 提交预支付单网关
	public final static String gateUrl = "https://api.weixin.qq.com/pay/genprepay?access_token=ACCESS_TOKEN";
	// 验证notify支付订单网关
	public final static String notifyUrl = "https://gw.tenpay.com/gateway/simpleverifynotifyid.xml";

	
	//微信登了回调地址
	public static String REDIRECT_URI = "http://www.ybt.org/oauth/callback/wx";//应用对应的凭证
	//应用对应的密钥
	public static String APP_KEY = "L8LrMqqeGRxST5reouB0K66CaYAWpqhAVsq7ggKkxHCOastWksvuX1uvmvQclxaHoYd3ElNBrNO2DHnnzgfVG9Qs473M3DTOZug5er46FhuGofumV8H2FVR9qkjSlC5K";
	public static String PARTNER = "1286209201";//商户号
	public static String PARTNER_KEY = "e9b54cdf48444c29a7ae75fe8310c292";//商户号对应的密钥
	public static String TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";//获取access_token对应的url
	public static String GRANT_TYPE = "client_credential";//常量固定值 
	public static String EXPIRE_ERRCODE = "42001";//access_token失效后请求返回的errcode
	public static String FAIL_ERRCODE = "40001";//重复获取导致上一次获取的access_token失效,返回错误码
	public static String GATEURL = "https://api.weixin.qq.com/pay/genprepay?access_token=";//获取预支付id的接口url
	public static String ACCESS_TOKEN = "access_token";//access_token常量值
	public static String ERRORCODE = "errcode";//用来判断access_token是否失效的值
	public static String SIGN_METHOD = "sha1";//签名算法常量值

	//package常量值
	public static String packageValue = "bank_type=WX&body=%B2%E2%CA%D4&fee_type=1&input_charset=GBK&notify_url=http%3A%2F%2F127.0.0.1%3A8180%2Ftenpay_api_b2c%2FpayNotifyUrl.jsp&out_trade_no=2051571832&partner=1900000109&sign=10DA99BCB3F63EF23E4981B331B0A3EF&spbill_create_ip=127.0.0.1&time_expire=20131222091010&total_fee=1";
	public static String traceid = "testtraceid001";//测试用户id

}
