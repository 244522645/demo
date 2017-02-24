package wechat.api;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

public abstract class BaseAPI {
	public static final String BASE_URI = "https://api.weixin.qq.com";
	public static final String MEDIA_URI = "http://file.api.weixin.qq.com";
	public static final String QRCODE_DOWNLOAD_URI = "https://mp.weixin.qq.com";
	public static final String MCH_URI = "https://api.mch.weixin.qq.com";
	public static final String OAUTH2_URI = "https://open.weixin.qq.com";
	public static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_JSON.toString());
	public static Header xmlHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_XML.toString());

	//初始化
		public static String APP_ID = "wxd930ea5d5a258f4f";//微信开发平台应用id
		public static String APP_SECRET = "db426a9829e4b49a0dcac7b4162da6b6";//应用对应的凭证
		//应用对应的密钥
		public static String APP_KEY = "L8LrMqqeGRxST5reouB0K66CaYAWpqhAVsq7ggKkxHCOastWksvuX1uvmvQclxaHoYd3ElNBrNO2DHnnzgfVG9Qs473M3DTOZug5er46FhuGofumV8H2FVR9qkjSlC5K";
		public static String PARTNER = "1900000109";//财付通商户号
		public static String PARTNER_KEY = "8934e7d15453e97507ef794cf7b0519d";//商户号对应的密钥
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
