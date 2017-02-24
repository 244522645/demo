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
	 * (张卫恒) 扫描二维码接口
	 */
	@RequestMapping(value = "QRCode", method = RequestMethod.POST)
	public String qrCode(String phone, String QRcode, String time, String imsi, String token,
			HttpServletRequest request) {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		log.info("扫描sessionID" + request.getSession().getId());
		log.info("扫面二维码：手机号" + phone + "手机二维码数据" + QRcode + "time" + time + "手机串码" + imsi + "手机效验码" + token);
		if (token == null || token.equals("")) {
			request.setAttribute("messageid", "1801");
			request.setAttribute("message", "效验错误");

			return "404";
		}
		if (phone == null || phone.equals("")) {
			request.setAttribute("messageid", "1802");
			request.setAttribute("message", "手机号错误");

			return "404";
		}
		if (!userInfo.getCinamausername().equals(phone)) {
			request.setAttribute("messageid", "1802");
			request.setAttribute("message", "手机号错误");
			return "404";
		}
		if (QRcode == null || QRcode.equals("")) {
			request.setAttribute("messageid", "1803");
			request.setAttribute("message", "扫码错误");
			return "404";
		}
		if (time == null || time.equals("")) {
			request.setAttribute("messageid", "1804");
			request.setAttribute("message", "时间错误");
			return "404";
		} else {
			Date date = new Date();
			Long min = ((Long.parseLong(date.getTime() + "") - Long.parseLong(time))) / (1000 * 60);
			if (-30 >= min || min >= 30) {
				request.setAttribute("messageid", "1805");
				request.setAttribute("message", "请于北京时间校准");

				return "404";
			}
		}
		if (imsi == null || imsi.equals("")) {
			request.setAttribute("messageid", "1806");
			request.setAttribute("message", "手机串码错误");

			return "404";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(phone);
		buffer.append(QRcode);
		buffer.append(time);

		boolean isture = utils.equalsMD5(buffer.toString(), imsi, token);
		System.out.println("校验码是否相等" + isture);
		if (!isture) {
			request.setAttribute("messageid", "1807");
			request.setAttribute("message", "效验码效验错误");

			return "404";
		}
		int i = QRcode.indexOf("kangou.cn/");
		if (i < 0) {
			request.setAttribute("messageid", "1808");
			request.setAttribute("message", "二维码错误");

			return "404";
		}
		log.info("这是截取后的字符串" + QRcode.substring(19));
		String card = QRcode.substring(19).trim();
		log.info("这是卡号" + card.substring(0, 11));
		String cardNum = card.substring(0, 11).trim();
		return getqRcodeService().qrCode(cardNum, QRcode, request);
	}

	/*
	 * @(张卫恒)
	 * 
	 * @获取兑换卡号密码，查询卡号和密码信息
	 */
	@RequestMapping("prepaidCode")
	@ResponseBody
	public Map<String,String> prepaidCard(String cardNum, String cardPass, HttpServletRequest request) {
		Map<String,String> map =new HashMap<String,String>();
		if (cardNum == null || cardNum.equals("")) {
			map.put("messageid", "1901");
			map.put("message", "卡账号不能为空");
			return map;
		}
		if (cardPass == null || cardPass.equals("")) {
			
			map.put("messageid", "1902");
			map.put("message", "卡密码不能为空");
			return map;
		}
		Pattern p = Pattern.compile("^\\d{11}$");
		if (!p.matcher(cardNum).find()) {
			map.put("messageid", "1903");
			map.put("message", "卡号不正确");
			return map;
			
		}

		return getqRcodeService().prepaidCard(cardNum, cardPass, request);
	}

	/*
	 * 购卡获取连接
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
				map.put("message", "卡号为空");
				return map;
			}
			if (cardPass == null && "".equals(cardPass)) {
				map.put("messageid", "2102");
				map.put("message", "密码为空");
				return map;
			}
			String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
			Date date = df.parse(request.getSession().getAttribute("cardpasstime") + "");
			Date nowdate = new Date();
			if (nowdate.getTime() > date.getTime()) {
				map.put("messageid", "2105");
				map.put("message", "卡过期");
				return map;
			}
			return getqRcodeService().cardByUrl(userInfo, cardsNum, cardPass, cardnumtext, price, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("messageid", "2106");
			map.put("message", "购卡失败，请联系管理员");
			return map;

		}

	}

	/*
	 * 卡密购卡
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
				map.put("message", "卡号为空");
				return map;
			}
			if (cardPass == null && "".equals(cardPass)) {
				map.put("messageid", "2102");
				map.put("message", "密码为空");
				return map;
			}
			String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
			Date date = df.parse(request.getSession().getAttribute("cardpasstime") + "");
			Date nowdate = new Date();
			if (nowdate.getTime() > date.getTime()) {
				map.put("messageid", "2105");
				map.put("message", "卡过期");
				return map;
			}
			return getqRcodeService().ticket(userInfo, cardsNum, cardPass, cardnumtext, price, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("messageid", "2106");
			map.put("message", "购卡失败，请联系管理员");
			return map;

		}

	}
	/*
	 * 二维码购卡,获取链接
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
				map.put("message", "卡号为空");
				return map;
			}
			if (cardPass == null && "".equals(cardPass)) {
				map.put("messageid", "2102");
				map.put("message", "密码为空");
				return map;
			}
			log.info(request.getSession().getAttribute("cardpasstime"));
			String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
			Date date = df.parse(request.getSession().getAttribute("cardpasstime") + "");
			Date nowdate = new Date();
			if (nowdate.getTime() > date.getTime()) {
				map.put("messageid", "2105");
				map.put("message", "卡过期");
				return map;
			}
			return getqRcodeService().qrCodeByUrl(userInfo, cardsNum, cardPass, cardnumtext, price, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("messageid", "2106");
			map.put("message", "购卡失败，请联系管理员");
			return map;
		}

	}

	/*
	 * 二维码购卡
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
				map.put("message", "卡号为空");
				return map;
			}
			if (cardPass == null && "".equals(cardPass)) {
				map.put("messageid", "2102");
				map.put("message", "密码为空");
				return map;
			}
			log.info(request.getSession().getAttribute("cardpasstime"));
			String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
			Date date = df.parse(request.getSession().getAttribute("cardpasstime") + "");
			Date nowdate = new Date();
			if (nowdate.getTime() > date.getTime()) {
				map.put("messageid", "2105");
				map.put("message", "卡过期");
				return map;
			}
			return getqRcodeService().ticketQRcode(userInfo, cardsNum, cardPass, cardnumtext, price, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("messageid", "2106");
			map.put("message", "购票失败，请联系管理员");
			return map;
		}
	}
	
	
}
