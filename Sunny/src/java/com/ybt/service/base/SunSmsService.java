package com.ybt.service.base;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.VerCodeBean;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunGySms;

@Component
public interface SunSmsService {
	/**
	 * 保存信息
	 * @param sms 短信对象
	 * @return SunGySms 信息
	 * */
	public SunGySms saveSms(SunGySms sms);
	
	/**
	 *@name    校验验证码是否正确
	 *@description 相关说明
	 *@time    创建时间:2015年8月20日下午4:18:07
	 *@param phone 手机号
	 *@param code 验证码
	 *@param time 有效时间
	 *@param type 验证码里类型
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public SunGySms getSmsByPhoneAndCodeAndCreateTime(String phone, String code,Date time,String type);

	/**
	 *@name    发送验证信息
	 *@description 根据类型不同发送不同的格式验证信息 
	 *type验证码类型：1商户注册验证，校验不能有已存在的账户信息；2商户绑定注册，校验被绑定的手机号必须有已存在的账户信息；3获取密码
	 *@time    创建时间:2015年8月20日下午2:46:37
	 *@param verCodeBean
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	SunGySms sendSmsCode(VerCodeBean verCodeBean);

	/**
	 *@name   订单短信通知 
	 *@description 相关说明
	 *@time    创建时间:2016年3月14日下午1:12:02
	 *@param order
	 *@param type 1下单通知
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	void sendSmsOrder(SunDdOrder order, String type);
	
	/**
	 *@name   催买家付款
	 *@description 相关说明
	 *@time    创建时间:2016年3月14日下午1:12:02
	 *@param order
	 *@param type 1.下单通知，2库房发货通知
	 *@author   高艳花
	 * @return 
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	int sendSmsRemind(SunDdOrder order, String type);
	
	
	/**
	 *@name   预订订单短信通知 
	 */
	public SunGySms sendSmsReserveOrder(String phone,String name,String urls);
}
