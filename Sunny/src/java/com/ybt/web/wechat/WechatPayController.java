package com.ybt.web.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 微信
 * */
@Controller
@RequestMapping(value = "/wechat/pay")
public class WechatPayController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(WechatPayController.class);

	/*
	 * 救援支付请求 生成预支付订单
	 * @param productId 产品id
	 * @param productType 产品类型
	 * @param totalFee  金额
	 * @param feeFype 币种 1人民
	 * @param userName 用户phone
	 * @param token 
	 * 返回prepayid
	 */
	@RequestMapping(value = "/resolePay", method = RequestMethod.POST)
	@ResponseBody
	public String resolePay(@RequestParam(value = "productId") String productId,
			@RequestParam(value = "productType") String productType,
			@RequestParam(value = "totalFee") String totalFee,
			@RequestParam(value = "feeFype") String feeFype,
			@RequestParam(value = "userName") String userName,
			@RequestParam(value = "token") String token,HttpServletRequest request,
			HttpServletResponse response) {

		return "";
	}
	/*
	 * 救援支付请求 回调处理
	 * @param productId 产品id
	 * @param productType 产品类型
	 * @param totalFee  金额
	 * @param feeFype 币种 1人民
	 * @param userName 用户phone
	 * @param token 
	 * 返回prepayid
	 */
	@RequestMapping(value = "/payNotify", method = RequestMethod.POST)
	@ResponseBody
	public String payNotify(@RequestParam(value = "productId") String productId,
			@RequestParam(value = "productType") String productType,
			@RequestParam(value = "totalFee") String totalFee,
			@RequestParam(value = "feeFype") String feeFype,
			@RequestParam(value = "userName") String userName,
			@RequestParam(value = "token") String token,HttpServletRequest request,
			HttpServletResponse response) {

		return "";
	}
}