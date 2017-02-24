package com.ybt.service.work;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunDdOrder;
import com.ybt.service.base.IBaseService;

/**
 * @module 支付管理
 * @author bqy  @time 下午12:28:41
 */
@Component
public interface IPaymentService extends IBaseService<SunDdOrder,String>{
	/**
	 * 钱包支付
	 * @param mid
	 * @return
	 */
	public String walletPay(String tradeNo,String subject,Double totalFee,String attach);
	
	/**
	 * 支付宝
	 * @param tags works
	 * @return
	 */
	public String aliPay(String tradeNo,String subject,Double totalFee,String attach);
	/**
	 * 生成微信支付
	 * @param tags works
	 * @return
	 */
	public String wechatPay(String tradeNo,String subject,Double totalFee,String attach);
	
	/**
	 * pingxx 支付
	 * @param tags works
	 * @return
	 * @throws Exception 
	 */
	public String pingxxGetCharge(Map<String, Object> jo) throws Exception;
}
