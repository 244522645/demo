package com.ybt.common.constant;


/**
 * @module 系统常量
 * @author bqy  @time 下午5:23:23
 */
public class chxtConstant {
	//cookie
	public static String COOKIE_LOGINUSER = "chxtUserName";    //串货系统用户名        
	public static String COOKIE_LOGINPWD = "chxtPassWord";	  //串货系统密码
	
	public static String BUSINESS_ID = "businessId";	  //串货系统商户ID
	public static String ACCOUNT_ID = "accountId";	  //串货系统账户ID
	public static String WECHATOPENDID = "wechatOpenId";	  //串货系统微信openID

	//密码加密用到的常量
	public static final String HASH_ALGORITHM = "SHA-1";//哈希算法
	public static final int SALT_SIZE = 8;//加密字段长度
	public static final int HASH_INTERATIONS = 1024;//加密循环次数
	
	
	public static final String SMS_BDYZM = "1";//短信类型--绑定验证

	public static final String ORDER_STUTAS_QX0 = "0";//取消状态
	public static final String ORDER_STUTAS_DFK13 = "13";//待付款状态
	public static final String ORDER_STUTAS_DFK14 = "14";//待付款状态
	public static final String ORDER_STUTAS_YFK23 = "23";//已付款状态
	public static final String ORDER_STUTAS_YFK24 = "24";//已付款状态
	public static final String ORDER_STUTAS_WC100 = "100";//完成状态
	
}
