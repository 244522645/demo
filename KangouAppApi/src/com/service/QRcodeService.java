package com.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.UserInfo;

public interface QRcodeService {
	public String qrCode(String cardNum, String cardPass, HttpServletRequest request);

	// �����ź����������֤
	public Map<String, String> prepaidCard(String cardNum, String cardPass, HttpServletRequest request);

	// �ѿ��ź����봫���ӿڣ��޸����ݿ����ˮ�ź�У����
	public Map<String, String> ticket(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request);

	// ����ɨ���ά����õķ���
	public Map<String, String> ticketQRcode(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request);

	// �����˺ź������ȡ����
	public Map<String, String> cardByUrl(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request);

	// ��ά���ȡ����
	public Map<String, String> qrCodeByUrl(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request);

	//
	public Map<String, String> buyCard(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request);
}
