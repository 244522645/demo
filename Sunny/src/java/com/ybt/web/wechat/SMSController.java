package com.ybt.web.wechat;



import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.bean.VerCodeBean;
import com.ybt.common.constant.chxtConstant;
import com.ybt.common.util.RandomCode;
import com.ybt.model.work.SunGySms;
import com.ybt.service.work.SmsService;



/**
 * 短信管理
 * 
 */
@Controller
@RequestMapping(value = "/wechat/sms")
public class SMSController {
	
	
	@Autowired
	SmsService smsService;
	@Resource
	public Map<String,String> constant;
	
	private String baseView() {
		return "/work/wechat";
	}
	/**
	 *@name    发送短信获取验证码
	 *@description 相关说明
	 *@time    创建时间:2016年1月29日上午11:18:24
	 *@param model
	 *@param request
	 *@param phone
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "/sendsms")
	public Result<SunGySms> sendSms(Model model,HttpServletRequest request,String phone) {
		VerCodeBean verCodeBean = new VerCodeBean();
		String code = RandomCode.getCode();//验证码
		verCodeBean.setPhone(phone);
		verCodeBean.setCode(code);
		verCodeBean.setType(chxtConstant.SMS_BDYZM);//注册绑定手机号
		
		//保存验证码信息
		StringBuffer content= new StringBuffer();
		content.append("【芸备胎】您的验证码是");
		content.append(code);
		content.append("，30分钟内有效。如非本人操作，请忽略本短信");
		SunGySms sms = smsService.sendSmsCode(verCodeBean,content.toString());
		if(sms!=null&&sms.getStates()==1){
			return new Result<SunGySms>("",sms);
		}else{
			return new Result<SunGySms>("发送异常",null);
		}
	}
	
}
