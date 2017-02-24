package com.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.UserInfo;

public interface QRcodeService {
	public String qrCode(String cardNum, String cardPass, HttpServletRequest request);

	// 传卡号和密码过来验证
	public Map<String, String> prepaidCard(String cardNum, String cardPass, HttpServletRequest request);

	// 把卡号和密码传到接口，修改数据库的流水号和校验码
	public Map<String, String> ticket(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request);

	// 这是扫描二维码调用的方法
	public Map<String, String> ticketQRcode(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request);

	// 根据账号和密码获取连接
	public Map<String, String> cardByUrl(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request);

	// 二维码获取连接
	public Map<String, String> qrCodeByUrl(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request);

	//
	public Map<String, String> buyCard(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request);
}
