package com.ybt.common.util;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.ybt.common.bean.SmsResultBean;
import com.ybt.common.constant.Juhe;

/**
 * 发送短信
 * @author buddy
 *
 */
public class SMSUtil {
	private static Logger logger = Logger.getLogger(SMSUtil.class);
	
	//发送短信
	public static boolean sendCode(String url,String charset){
		String jsonResult = HttpConnectionUtil.get(url, charset);
		if (jsonResult != null) {
			logger.info("发送短信返回值："+jsonResult);
			SmsResultBean resultBean = new Gson().fromJson(jsonResult,SmsResultBean.class);
			if (resultBean.getError_code() == 0) {
				logger.info("验证码发送成功:"+resultBean.getResult()+"   reason:"+resultBean.getReason());
				return  true;
			}
		} else {
			logger.error("短信API请求失败！"+url);
		}
		return false;
	}
	
	//发送验证码
	public static boolean sendVerifyCode(String phone,String code){
		// "?mobile=手机号码&tpl_id=短信模板ID&tpl_value=%23code%23%3D654654&key=";
				String url = new StringBuffer(Juhe.JUHE_SMS_URL).append("?mobile=")
						.append(phone).append("&tpl_id=").append(Juhe.JUHE_SMS_SUN_TPL)
						.append("&tpl_value=%23code%23%3D").append(code)
						.append("&key=").append(Juhe.JUHE_SMS_KEY).toString();
		return sendCode(url,"UTF-8");
	}
	//发送订单提醒
	public static boolean sendOrderWarn(String phone,String code){
		// "?mobile=手机号码&tpl_id=短信模板ID&tpl_value=%23code%23%3D654654&key=";
				String url = new StringBuffer(Juhe.JUHE_SMS_URL).append("?mobile=")
						.append(phone).append("&tpl_id=").append(Juhe.JUHE_SMS_TPL_PD)
						.append("&tpl_value=%23code%23%3D").append(code)
						.append("&key=").append(Juhe.JUHE_SMS_KEY).toString();
		return sendCode(url,"UTF-8");
	}
	//发送串货下单提醒短信
	//【芸备胎】您有一个新订单！#orderId#，商品名称：#name#，数量：#num#个，请及时处理。详情查看芸备胎串货系统（www.yunbeitai.com/ych）-“销售列表"。
	public static boolean sendOrderXd(String phone,String value,String juhe){
				String url = new StringBuffer(Juhe.JUHE_SMS_URL).append("?mobile=")
						.append(phone).append("&tpl_id=").append(juhe)
						.append("&tpl_value=").append(Encodes.urlEncode(value))
						.append("&key=").append(Juhe.JUHE_SMS_KEY).toString();
		return sendCode(url,"UTF-8");
	}
	
	//发送验证码
	public static boolean sendYudingTongzhi(String phone,String name,String urls){
				String url = new StringBuffer(Juhe.JUHE_SMS_URL).append("?mobile=")
						.append(phone).append("&tpl_id=").append(Juhe.JUHE_SMS_YUDING)
						.append("&tpl_value=").append(Encodes.urlEncode("#person#="+name+"&#url#="+urls+""))
						.append("&key=").append(Juhe.JUHE_SMS_KEY).toString();
		return sendCode(url,"UTF-8");
	}
	
	//发送验证码
	public static boolean sendSendPhotoDownRequest(String phone,String photo,String user){
				String url = new StringBuffer(Juhe.JUHE_SMS_URL).append("?mobile=")
						.append(phone).append("&tpl_id=").append(Juhe.JUHE_SMS_YUDING_XIAZAI)
						.append("&tpl_value=").append(Encodes.urlEncode("#photoInfo#="+photo+"&#userInfo#="+user+""))
						.append("&key=").append(Juhe.JUHE_SMS_KEY).toString();
		return sendCode(url,"UTF-8");
	}
	
	public static void main(String args[]){
		//sendVerifyCode("18618212356","测试13363233635");
		//sendSendPhotoDownRequest("18618212356","测试","用户");
	}
}
