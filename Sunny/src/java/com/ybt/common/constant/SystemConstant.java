package com.ybt.common.constant;

/**
 * @module 系统常量
 * @author bqy  @time 下午5:23:23
 */
public class SystemConstant {
	//cookie
	public static String COOKIE_LOGINUSER = "ybtUname";    //客户端cookie        
	public static String COOKIE_LOGINPWD = "ybtPword";	  //客户端cookie
	
	//session
	public static String SESSION_USER = "ybt_U";	  //客户端session 会员信息
	
	
	//upyun 
	public static final String BUCKET_NAME = "ybt";
	public static final String OPERATOR_NAME = "ybt1";
	public static final String OPERATOR_PWD = "bjhylckj";
	
	//模板id  
	//本地
//	public static final String TEMPLATE_LIPIN = "2DK0lUQ2znqaStua8J2GqWoHvi0V4yeebZ2-1eD6mzg";  //活动
//	public static final String TEMPLATE_HUODONG = "-xCnqkXz4OA8KRongeVL7PdUlySetehJqMNmRBDuyP0";  //礼品
	// 测试 升日
//	public static final String TEMPLATE_LIPIN = "Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y";  //活动
//	public static final String TEMPLATE_HUODONG = "z7iSiyzgACFapsDgp-U1voqPsllWfLdrC0AoWQShL2Q";  //礼品
	// 生产给点儿
	public static final String TEMPLATE_LIPIN = "85yxfJoT9Sn5Y3vouCmsb5kui7U-G_B4G42f82eiSk4";  //礼品
	public static final String TEMPLATE_HUODONG = "5pSfJ2fHCpzcm7slTZlCj51QhjV6hQIzqFcYyuQxF8M";  //活动
	
	public static final String TEMPLATE_ZHANGHU = "rzUDtj7V-Pj71p6UOmTpGhuYN48Dbfpgaml5VewJDMI";  //账户资金变动
	
	//关注
	public static final boolean    CODE_CARD_E_GIFT_GUANZHU_OPEN = true;  //关注 送卡开关
	public static final String CODE_CARD_E_GIFT_GUANZHU = "CARD_E_GIFT_GUANZHU";// 关注
	
	//送卡活动代号
	public static final boolean    CODE_CARD_E_GIFT_DAIYAN_OPEN = true;  //代言开关
	public static final String CODE_CARD_E_GIFT_20161015 = "CARD_E_GIFT_20161015";  //20161015活动
	public static final String CODE_CARD_E_GIFT_DAIYAN = "CARD_E_GIFT_DAIYAN";		// 代言活动
	public static final String CODE_CARD_E_GIFT_DAIYAN_GUANZHU = "CARD_E_GIFT_DAIYAN_GUANZHU";// 代言活动
	public static final int    CODE_CARD_E_GIFT_DAIYAN_NUM = 1;  //送几个
	
	
	//2017-1-1送卡
//	public static final boolean    CODE_CARD_E_GIFT_20170101_OPEN = true;  
//	public static final String    CODE_CARD_E_GIFT_20170101_MSG = "阳光灿烂的2017";  
	//2017年1月6日11:00:27
	public static final boolean    CODE_CARD_E_GIFT_20170101_OPEN = true;  
	public static final String    CODE_CARD_E_GIFT_20170101_MSG = "阳光好礼";  
	public static final String    CODE_CARD_E_GIFT_20170101_CODE = "CARD_E_GIFT_20170106"; 
	
	//游戏A-88888888
	public static final boolean    CODE_CARD_E_GIFT_YOUCI_A_8_OPEN = true;  
	public static final String    CODE_CARD_E_GIFT_YOUCI_A_8_MSG = "A-88888888";  
	public static final String    CODE_CARD_E_GIFT_YOUCI_A_8_CODE = "CARD_E_GIFT_YOUXI_A-88888888"; 
	//游戏----十位数
	public static final boolean    CODE_CARD_E_GIFT_YOUXI_10WEI_OPEN = true;  
	public static final String    CODE_CARD_E_GIFT_YOUXI_10WEI_MSG = "十位数";  
	public static final String    CODE_CARD_E_GIFT_YOUXI_10WEI_CODE = "CARD_E_GIFT_YOUXI_10WEI"; 

	//新年快乐
	public static final boolean    CODE_CARD_E_GIFT_CHUNJIE_OPEN = true;  
	public static final String    CODE_CARD_E_GIFT_CHUNJIE_MSG = "新年快乐";  
	public static final String    CODE_CARD_E_GIFT_CHUNJIE_CODE = "CARD_E_GIFT_CHUNJIE"; 
	
	//没有照片（预定日出功能：已选择的五个城市和其他城市都没有照片时，送卡）
	public static final boolean    CODE_CARD_E_GIFT_NOPHOTO_OPEN = true;  
	public static final String    CODE_CARD_E_GIFT_NOPHOTO_MSG = "没有照片";  
	public static final String    CODE_CARD_E_GIFT_NOPHOTO_CODE = "CARD_E_GIFT_NOPHOTO_";

	//2017年春节活动(时间范围在2017-01-28日前)
	public static final boolean    CODE_CARD_E_GIFT_2017NEWYEAR_OPEN = true;  
	public static final String    CODE_CARD_E_GIFT_2017NEWYEAR_MSG = "2017年春节活动";  
	public static final String    CODE_CARD_E_GIFT_2017NEWYEAR_CODE = "CARD_E_GIFT_2017NEWYEAR_20170128";
}
