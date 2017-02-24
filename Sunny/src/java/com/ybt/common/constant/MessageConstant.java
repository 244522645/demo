package com.ybt.common.constant;

import com.ybt.common.util.PropertiesUtil;

public class MessageConstant {
	/**
	 * @description 关注云备胎商户版时提示
	 * @value value:YBT_WELCOME
	 */
	public static String YBT_WELCOME = "欢迎加入芸备胎商户平台!\n\t您可通过点击下方菜单，完善店铺信息，为您的客户提供更好的服务。 ";
	/**
	 * @description 发送你好时回复
	 * @value value:YBT_HELLO
	 */
	public static String YBT_HELLO = "您好！欢迎加入芸备胎采购平台！";
	/**
	 * @description 其他提示
	 * @value value:YBT_BUSY
	 */
	public static String YBT_BUSY ="感谢关注云备胎，我们将竭诚为您服务!";
	/**
	 * @description 菜单提示
	 * @value value:YBT_BUSY
	 */
	public static String YBT_MENU ="公众号回复功能：\n回复91-管理员登录\n回复92-注销管理员登录\n回复93-登录商户管理\n回复94-注销商户管理\n回复0-商户演示";
	/**
	 * @description 菜单提示
	 * @value value:YBT_BUSY
	 */
	public static String YBT_WEB_MENU ="公众号回复功能：<br>回复91-管理员登录<br>回复92-注销管理员登录<br>回复93-登录商户管理<br>回复94-注销商户管理<br>回复0-商户演示";
	
	/**
	 * @description 登录错误提示
	 * @value value:YBT_LOGO_MESSAGE
	 */
	public static String YBT_LOGO_MESSAGE ="账号错误";
	/**
	 * @description 登录错误提示
	 * @value value:YBT_LOGO_CONTENT1
	 */
	public static String YBT_LOGO_CONTENT1 ="您的店铺已关闭，如果想要重新开启店铺，请联系云备胎客服。";
	/**
	 * @description 登录错误提示
	 * @value value:YBT_LOGO_CONTENT2
	 */
	public static String YBT_LOGO_CONTENT2 ="您已注册但未创建店铺，请联系云备胎客服。";
	
	/**
	 * @description 登录错误提示
	 * @value value:YBT_LOGO_CONTENT3
	 */
	public static String YBT_LOGO_CONTENT3 ="系统错误，请重新打开APP或重新关注微信公众号，给您带来的不变尽请谅解";
	
	/**
	 * @description 关注公众号或重新打开app提示
	 * @value value:YBT_LOGO_CONTENT4
	 */
	public static String YBT_LOGO_CONTENT4 ="未获取到您的微信信息，请关注芸备胎，给您带来的不变尽请谅解";
	
	/**
	 * @description 登录错误提示
	 * @value value:YBT_LOGO_CONTENT3
	 */
	public static String YBT_WEIXIN_BEFORE ="<a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Wechat.APPID+"&redirect_uri="+ PropertiesUtil.getProperty("domainName");
	/**
	 * @description 登录错误提示
	 * @value value:YBT_LOGO_CONTENT3
	 */
	public static String YBT_WEIXIN_AFRTER ="&response_type=code&scope=snsapi_base#wechat_redirect'>";
	
	
	/**
	 * @description 地推时商户扫描推广二维码后提示
	 * @value value:YBT_DT_WELCOME
	 */
	public static String YBT_DT_WELCOME = "欢迎加入云备胎商户平台!\n\t我的服务人员正在为您开通账户，录入信息,谢谢您的配合。我们将竭诚为您服务!";
	/**
	 * @description 管理员登录
	 * @value value:YBT_91
	 */
	public static String YBT_DT_91 =YBT_WEIXIN_BEFORE+"/console/wechat/admin/apply/adminLoginView"+YBT_WEIXIN_AFRTER+"点击管理员登录..</a>\n\n "+YBT_MENU;
	/**
	 * @description 注销管理员登录
	 * @value value:YBT_92
	 */
	public static String YBT_DT_92 ="\n\t "+YBT_WEIXIN_BEFORE+"/console/wechat/admin/apply/adminLogout"+YBT_WEIXIN_AFRTER+"点击注销管理员登录..</a>\n\t";
	/**
	 * @description 商户管理
	 * @value value:YBT_93
	 */
	public static String YBT_DT_93 =YBT_WEIXIN_BEFORE+"/console/wechat/admin/apply/setAccountNo"+YBT_WEIXIN_AFRTER+"点击添加商户..</a>\n\n "+YBT_MENU;
     /**
     * @description 注销商户管理
	 * @value value:YBT_94
	 */
	public static String YBT_DT_94 ="\n\t <a href='"+ PropertiesUtil.getProperty("domainName")+"/console/wechat/admin/apply/accountLogout'>点击注销商户管理</a>\n\t";
	
	/**
	 * @description 商户演示
	 * @value value:YBT_0
	 */
	public static String YBT_DT_0 ="\n\t "+YBT_WEIXIN_BEFORE+"/console/wechat/admin/apply/demo"+YBT_WEIXIN_AFRTER+"点击进入商户演示</a>\n\n"+YBT_MENU;


	public static final String IMG_SAVE_FAILURE = "图片保存失败";
	public static final String IMG_DOES_NOT_SUPPORT_EXTENSION = "不支持扩展名";
	public static final String IMG_NO_SELECT = "请选择图片";
	public static final String IMG_TOO_MAX = "图片过大";
	public static final String IMG_TOO_MIN = "图片太小";
	public static final String WORKS_UP_OK = "发布成功";
	public static final String WORKS_PEND_OK = "审批成功";
	public static final String SUBMIT_OK = "提交成功";
	
	
}
