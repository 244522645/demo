package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.UserInfo;

@Controller
@RequestMapping("/ScanQRCode")
public class QRCodeController extends BaseController {
	/*
	 * (������) ɨ���ά��ӿ�
	 */
	@RequestMapping(value = "QRCode", method = RequestMethod.POST)
	public String qrCode(String phone, String QRcode, String time, String imsi, String token,
			HttpServletRequest request) {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		log.info("ɨ��sessionID" + request.getSession().getId());
		log.info("ɨ���ά�룺�ֻ���" + phone + "�ֻ���ά������" + QRcode + "time" + time + "�ֻ�����" + imsi + "�ֻ�Ч����" + token);
		if (token == null || token.equals("")) {
			request.setAttribute("messageid", "1801");
			request.setAttribute("message", "Ч�����");

			return "404";
		}
		if (phone == null || phone.equals("")) {
			request.setAttribute("messageid", "1802");
			request.setAttribute("message", "�ֻ��Ŵ���");

			return "404";
		}
		if (!userInfo.getCinamausername().equals(phone)) {
			request.setAttribute("messageid", "1802");
			request.setAttribute("message", "�ֻ��Ŵ���");
			return "404";
		}
		if (QRcode == null || QRcode.equals("")) {
			request.setAttribute("messageid", "1803");
			request.setAttribute("message", "ɨ�����");
			return "404";
		}
		if (time == null || time.equals("")) {
			request.setAttribute("messageid", "1804");
			request.setAttribute("message", "ʱ�����");
			return "404";
		} else {
			Date date = new Date();
			Long min = ((Long.parseLong(date.getTime() + "") - Long.parseLong(time))) / (1000 * 60);
			if (-30 >= min || min >= 30) {
				request.setAttribute("messageid", "1805");
				request.setAttribute("message", "���ڱ���ʱ��У׼");

				return "404";
			}
		}
		if (imsi == null || imsi.equals("")) {
			request.setAttribute("messageid", "1806");
			request.setAttribute("message", "�ֻ��������");

			return "404";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(phone);
		buffer.append(QRcode);
		buffer.append(time);

		boolean isture = utils.equalsMD5(buffer.toString(), imsi, token);
		System.out.println("У�����Ƿ����" + isture);
		if (!isture) {
			request.setAttribute("messageid", "1807");
			request.setAttribute("message", "Ч����Ч�����");

			return "404";
		}
		int i = QRcode.indexOf("kangou.cn/");
		if (i < 0) {
			request.setAttribute("messageid", "1808");
			request.setAttribute("message", "��ά�����");

			return "404";
		}
		log.info("���ǽ�ȡ����ַ���" + QRcode.substring(19));
		String card = QRcode.substring(19).trim();
		log.info("���ǿ���" + card.substring(0, 11));
		String cardNum = card.substring(0, 11).trim();
		return getqRcodeService().qrCode(cardNum, QRcode, request);
	}

	/*
	 * @(������)
	 * 
	 * @��ȡ�һ��������룬��ѯ���ź�������Ϣ
	 */
	@RequestMapping("prepaidCode")
	@ResponseBody
	public Map<String,String> prepaidCard(String cardNum, String cardPass, HttpServletRequest request) {
		Map<String,String> map =new HashMap<String,String>();
		if (cardNum == null || cardNum.equals("")) {
			map.put("messageid", "1901");
			map.put("message", "���˺Ų���Ϊ��");
			return map;
		}
		if (cardPass == null || cardPass.equals("")) {
			
			map.put("messageid", "1902");
			map.put("message", "�����벻��Ϊ��");
			return map;
		}
		Pattern p = Pattern.compile("^\\d{11}$");
		if (!p.matcher(cardNum).find()) {
			map.put("messageid", "1903");
			map.put("message", "���Ų���ȷ");
			return map;
			
		}

		return getqRcodeService().prepaidCard(cardNum, cardPass, request);
	}

	/*
	 * ������ȡ����
	 */
	@RequestMapping("cardByUrl")
	@ResponseBody
	public Map<String, String> cardByUrl(String form_card, String form_cardnum, String form_time, String cardnumtext,
			String price, HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();
		try {

			String cardsNum = (String) request.getSession().getAttribute("cardNum");
			String cardPass = (String) request.getSession().getAttribute("cardpassword");
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
			if (cardsNum == null && "".equals(cardsNum)) {
				map.put("messageid", "2101");
				map.put("message", "����Ϊ��");
				return map;
			}
			if (cardPass == null && "".equals(cardPass)) {
				map.put("messageid", "2102");
				map.put("message", "����Ϊ��");
				return map;
			}
			String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
			Date date = df.parse(request.getSession().getAttribute("cardpasstime") + "");
			Date nowdate = new Date();
			if (nowdate.getTime() > date.getTime()) {
				map.put("messageid", "2105");
				map.put("message", "������");
				return map;
			}
			return getqRcodeService().cardByUrl(userInfo, cardsNum, cardPass, cardnumtext, price, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("messageid", "2106");
			map.put("message", "����ʧ�ܣ�����ϵ����Ա");
			return map;

		}

	}

	/*
	 * ���ܹ���
	 */
	@RequestMapping("ticket")
	@ResponseBody
	private Map<String, String> ticket(HttpServletRequest request) throws ParseException {
		Map<String, String> map = new HashMap<String, String>();
		try {

			String cardsNum = (String) request.getSession().getAttribute("cardNum");
			String cardPass = (String) request.getSession().getAttribute("cardpassword");
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
			String price = (String) request.getSession().getAttribute("price");
			String cardnumtext = (String) request.getSession().getAttribute("cardnumtext");
			if (cardsNum == null && "".equals(cardsNum)) {
				map.put("messageid", "2101");
				map.put("message", "����Ϊ��");
				return map;
			}
			if (cardPass == null && "".equals(cardPass)) {
				map.put("messageid", "2102");
				map.put("message", "����Ϊ��");
				return map;
			}
			String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
			Date date = df.parse(request.getSession().getAttribute("cardpasstime") + "");
			Date nowdate = new Date();
			if (nowdate.getTime() > date.getTime()) {
				map.put("messageid", "2105");
				map.put("message", "������");
				return map;
			}
			return getqRcodeService().ticket(userInfo, cardsNum, cardPass, cardnumtext, price, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("messageid", "2106");
			map.put("message", "����ʧ�ܣ�����ϵ����Ա");
			return map;

		}

	}
	/*
	 * ��ά�빺��,��ȡ����
	 */
	@RequestMapping("qrCodeByUrl")
	@ResponseBody
	private Map<String, String> qrCodeByUrl(String form_card, String form_cardnum, String form_time, String cardnumtext,
			String price, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String cardsNum = (String) request.getSession().getAttribute("cardNum");
			String cardPass = (String) request.getSession().getAttribute("cardpassword");
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		
			if (cardsNum == null && "".equals(cardsNum)) {
				map.put("messageid", "2101");
				map.put("message", "����Ϊ��");
				return map;
			}
			if (cardPass == null && "".equals(cardPass)) {
				map.put("messageid", "2102");
				map.put("message", "����Ϊ��");
				return map;
			}
			log.info(request.getSession().getAttribute("cardpasstime"));
			String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
			Date date = df.parse(request.getSession().getAttribute("cardpasstime") + "");
			Date nowdate = new Date();
			if (nowdate.getTime() > date.getTime()) {
				map.put("messageid", "2105");
				map.put("message", "������");
				return map;
			}
			return getqRcodeService().qrCodeByUrl(userInfo, cardsNum, cardPass, cardnumtext, price, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("messageid", "2106");
			map.put("message", "����ʧ�ܣ�����ϵ����Ա");
			return map;
		}

	}

	/*
	 * ��ά�빺��
	 */
	@RequestMapping("ticketQRcode")
	@ResponseBody
	private Map<String, String> ticketQRcode(HttpServletRequest request) throws ParseException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String cardsNum = (String) request.getSession().getAttribute("cardNum");
			String cardPass = (String) request.getSession().getAttribute("cardpassword");
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
			String price = (String) request.getSession().getAttribute("price");
			String cardnumtext = (String) request.getSession().getAttribute("cardnumtext");
			if (cardsNum == null && "".equals(cardsNum)) {
				map.put("messageid", "2101");
				map.put("message", "����Ϊ��");
				return map;
			}
			if (cardPass == null && "".equals(cardPass)) {
				map.put("messageid", "2102");
				map.put("message", "����Ϊ��");
				return map;
			}
			log.info(request.getSession().getAttribute("cardpasstime"));
			String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
			Date date = df.parse(request.getSession().getAttribute("cardpasstime") + "");
			Date nowdate = new Date();
			if (nowdate.getTime() > date.getTime()) {
				map.put("messageid", "2105");
				map.put("message", "������");
				return map;
			}
			return getqRcodeService().ticketQRcode(userInfo, cardsNum, cardPass, cardnumtext, price, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("messageid", "2106");
			map.put("message", "��Ʊʧ�ܣ�����ϵ����Ա");
			return map;
		}
	}
	
	
}
