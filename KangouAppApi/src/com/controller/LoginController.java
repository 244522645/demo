package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Notices;
import com.model.UserInfo;
import com.model.Version;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	/*
	 * @author 张卫恒
	 * 
	 * @注释：登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "login.do",method = RequestMethod.POST) // post提交
	@ResponseBody
	public Map<String, String> login(String phone, String password, String mobileType, String time, String imsi,
			String version, String token, HttpServletRequest request) {
		log.info("登陆sessionID" + request.getSession().getId());
		log.info("登录手机号:" + phone + "登录密码:" + password + "手机类型:" + mobileType + "登录时间：" + time + "手机串码：" + imsi
				+ "md5校验码：" + token);
		Long longTime = System.currentTimeMillis();
		Map<String, String> map = new HashMap<String, String>();
		map.put("Time", longTime + "");
		try {
			if (!mobileType.equals("ios") && !mobileType.equals("android")) {
				map.put("message", "手机类型错误");
				map.put("Status", "1011");
				map.put("Phone", phone);
				map.put("POPMessage", "");
				map.put("LastVersion", "");
				return map;
			}
			Version versions = getLoginService().checkVersion(mobileType);
			double dversion = 0;
			if (version != null && !version.equals("")) {
				try {
					dversion = Double.parseDouble(version);
				} catch (NumberFormatException e) {
					map.put("message", "手机版本错误");
					map.put("Status", "1012");
					map.put("Phone", phone);
					map.put("POPMessage", "");

					map.put("LastVersion", "");
					return map;
				}
			} else {
				map.put("message", "手机版本错误");
				map.put("Status", "1012");
				map.put("Phone", phone);
				map.put("POPMessage", "");
				map.put("LastVersion", "");
				return map;
			}
			Double versionnum = Double.parseDouble(versions.getVersionnum());
			if (versionnum <= dversion) {
				map.put("LastVersion", version);
			}
			if (versionnum > dversion) {
				map.put("LastVersion", versionnum + "");
			}

			if (phone == null || phone.equals("")) {
				map.put("Status", "1003");
				map.put("message", "请输入手机号");
				map.put("Phone", "");
				map.put("POPMessage", "");
				return map;
			}

			if (token == null || token.equals("")) {

				map.put("message", "校验码错误");
				map.put("Status", "1001");
				map.put("Phone", phone);
				map.put("POPMessage", "");
				return map;
			}

			if (time == null || time.equals("")) {
				map.put("Status", "1006");
				map.put("message", "时间错误");
				map.put("Phone", phone);
				map.put("POPMessage", "");
				return map;
			} else {
				Date date = new Date();
				Long min = ((Long.parseLong(date.getTime() + "") - Long.parseLong(time))) / (1000 * 60);
				if (-30 >= min || min >= 30) {
					request.setAttribute("message", "请于北京时间校准");
					map.put("Status", "1006");
					map.put("message", "时间错误");
					map.put("Phone", phone);
					map.put("POPMessage", "");
					return map;
				}
			}

			if (imsi != null && !imsi.equals("")) {

			} else {
				map.put("Status", "1005");
				map.put("message", "手机串码不能为空");
				map.put("Phone", phone);
				map.put("POPMessage", "");
				return map;
			}
			StringBuffer buffer = new StringBuffer();
			buffer.append(phone);
			buffer.append(password);
			buffer.append(mobileType);
			buffer.append(version);
			buffer.append(time);
			boolean isture = utils.equalsMD5(buffer.toString(), imsi, token);
			System.out.println(isture);
			if (!isture) {
				map.put("Status", "1002");
				map.put("message", "校验码错误");
				map.put("Phone", phone);
				map.put("POPMessage", "");
				return map;
			}
			try {
				map = getLoginService().login(phone, password, mobileType, longTime + "", imsi, token, request,
						map.get("LastVersion"));
			} catch (Exception e) {
				e.printStackTrace();
				map.put("Status", "1013");
				map.put("message", "登陆错误，请联系管理员");
				map.put("Phone", phone);
				map.put("POPMessage", "");
				StringBuffer returnBuffer = new StringBuffer();
				returnBuffer.append(map.get("Status") + map.get("Phone") + map.get("LastVersion") + map.get("Time")
						+ map.get("POPMessage"));
				System.out.println(returnBuffer.toString() + imsi);
				String returnMd5 = utils.toMD5(returnBuffer.toString() + imsi + "Dz3n9oXpcCRXBKnlA3DBZzaL4fZvYjS2");
				System.out.println(returnBuffer.toString() + imsi + "Dz3n9oXpcCRXBKnlA3DBZzaL4fZvYjS2");
				map.put("Token", returnMd5);
				return map;

			}
			StringBuffer returnBuffer = new StringBuffer();
			returnBuffer.append(map.get("Status") + map.get("Phone") + map.get("LastVersion") + map.get("Time")
					+ map.get("POPMessage"));
			System.out.println(returnBuffer.toString() + imsi);
			String returnMd5 = utils.toMD5(returnBuffer.toString() + imsi + "Dz3n9oXpcCRXBKnlA3DBZzaL4fZvYjS2");
			System.out.println(returnBuffer.toString() + imsi + "Dz3n9oXpcCRXBKnlA3DBZzaL4fZvYjS2");
			map.put("Token", returnMd5);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(注册页面跳转) 提交参数：手机号，手机imsi,token
	 */
	@RequestMapping("/reg")
	public String registration(String imsi, String mobileType, ModelMap modelMap, HttpServletRequest request) {
		log.info("手机串码" + imsi + "手机类型" + mobileType + "注册");
		if (imsi == null || imsi.equals("")) {
			modelMap.put("messageid", "1501");
			modelMap.put("message", "效验错误");
			return "404";
		}
		if (!mobileType.equals("ios") && !mobileType.equals("android")) {
			modelMap.put("messageid", "1502");
			modelMap.put("message", "手机类型错误");
			return "404";
		}
		modelMap.put("mobileType", mobileType);
		modelMap.put("imsi", imsi);
		return "reg";
	}

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(忘记密码页面跳转) 提交参数：手机号，手机imsi,token
	 */
	@RequestMapping("/updatePassword")
	public String forgotPassword(String imsi, String mobileType, ModelMap modelMap, HttpServletRequest request) {
		log.info("手机串码" + imsi + "手机类型" + mobileType + "重置密码");
		if (imsi == null || imsi.equals("")) {
			modelMap.put("messageid", "1601");
			modelMap.put("message", "效验错误");

			return "404";
		}
		modelMap.addAttribute("imsi", imsi);
		modelMap.addAttribute("mobileType", mobileType);
		return "forgotPass";
	}

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(提交参数手机号，公告消息ID，时间戳，手机标示码，MD5效验)
	 */
	@RequestMapping(value="POPMessage",method = RequestMethod.POST)
	private String POPMessage(String phone, String popMessageID, String time, String imsi, String token,
			HttpServletRequest request) {
		log.info("手机号" + phone + "消息id" + popMessageID + "时间" + time + "手机串码" + imsi + "手机校验码" + token);
		if (token == null || token.equals("")) {
			request.setAttribute("messageid", "1401");
			request.setAttribute("message", "效验错误");

			return "404";
		}
		if (popMessageID == null || popMessageID.equals("")) {
			request.setAttribute("messageid", "1402");
			request.setAttribute("message", "消息获取错误");

			return "404";
		}
		if (phone == null || phone.equals("")) {
			request.setAttribute("messageid", "1403");
			request.setAttribute("message", "手机号错误");

			return "404";
		}
		if (time == null || time.equals("")) {
			request.setAttribute("messageid", "1404");
			request.setAttribute("message", "时间错误");
			return "404";
		} else {
			Date date = new Date();
			Long min = ((Long.parseLong(date.getTime() + "") - Long.parseLong(time))) / (1000 * 60);
			if (-30 >= min || min >= 30) {
				request.setAttribute("messageid", "1405");
				request.setAttribute("message", "请于北京时间校准");

				return "404";
			}
		}
		if (imsi == null || imsi.equals("")) {
			request.setAttribute("messageid", "1406");
			request.setAttribute("message", "手机串码错误");

			return "404";
		}
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		if (!imsi.equals(userInfo.getCinemanickname())) {
			request.setAttribute("messageid", "1407");
			request.setAttribute("message", "手机串码错误");

			return "404";
		}
		if (!phone.equals(userInfo.getCinamausername())) {
			request.setAttribute("messageid", "1408");
			request.setAttribute("message", "手机号错误");

			return "404";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(phone);
		buffer.append(popMessageID);
		buffer.append(time);

		boolean isture = utils.equalsMD5(buffer.toString(), imsi, token);
		if (!isture) {
			request.setAttribute("messageid", "1409");
			request.setAttribute("message", "效验码效验错误");

			return "404";
		}
		Notices notices = getNoticesService().findNotice(popMessageID, request);
		if (notices != null && !notices.equals("")) {
			request.setAttribute("notice", notices);
			return "message";
		}

		request.setAttribute("messageid", "1410");
		request.setAttribute("message", "没有该公告");

		return "404";
	}

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(传手机号过来，验证数据库中是否有当前手机号)
	 */
	@RequestMapping("selectUserByPhone")
	@ResponseBody
	public int selectUserByPhone(String phone, String imsi) {
		log.info("这是获取验证码的手机号" + phone + "串码" + imsi);
		int i = getLoginService().selectUserByPhone(phone, imsi);
		System.out.println(phone);
		return i;
	}

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(传手机号过来,获取验证码)
	 */
	@RequestMapping("gettingCode")
	@ResponseBody
	public String gettingCode(String phone, HttpServletRequest request) {
		if (phone == null || "".equals(phone)) {
			return "6";
		}
		log.info("手机号：" + phone + "获取验证码");
		Date date = new Date();
		Long unixTime = System.currentTimeMillis();
		Long sessionTime = (Long) request.getSession().getAttribute("updatePassTime");
		Long min;
		if (sessionTime != null && !sessionTime.equals("")) {
			min = ((Long.parseLong(date.getTime() + "") - sessionTime)) / (1000);
			if (min < 99) {
				return "5";
			}
		}
		Random random = new java.util.Random();

		StringBuffer buffer = new StringBuffer();
		int result;
		for (int i = 0; i < 6; i++) {

			if (i == 0) {
				result = random.nextInt(9);
				buffer.append(result + 1);
				continue;
			}
			result = random.nextInt(10);
			buffer.append(result);

		}
		String url;

		url = "http://sms.airlead.net:28080/dxin10/SendMessage_utf8";
		String rollback = utils.gettingCode(url, phone, buffer.toString());
		log.info(rollback);
		if (rollback == null || rollback.equals("")) {
			return "10";
		}
		if (rollback.startsWith("00,")) {
			request.getSession().setAttribute("updatePassTime", unixTime);
			request.getSession().setAttribute("regPhone", phone);
			int code = (int) ((Math.random() * 9 + 1) * 100000);
			request.setAttribute("phonecode", code);
			request.getSession().setAttribute("phonecode", buffer.toString());
			return "00";
		} else {
			return "10";
		}

	}

	/*
	 * @author (张卫恒)
	 * 
	 * @忘记密码
	 * 
	 * @注释：(传手机号过来,获取验证码)
	 */
	@RequestMapping("forgotPassCode")
	@ResponseBody
	public String forgotPassCode(String phone, HttpServletRequest request) {
		if (phone == null || "".equals(phone)) {
			return "6";
		}
		Date date = new Date();
		Long unixTime = System.currentTimeMillis();
		Long sessionTime = (Long) request.getSession().getAttribute("forgotPassTime");
		Long min;
		if (sessionTime != null && !sessionTime.equals("")) {
			min = ((Long.parseLong(date.getTime() + "") - sessionTime)) / (1000);
			if (min < 99) {
				return "5";
			}
		}
		Random random = new java.util.Random();

		StringBuffer buffer = new StringBuffer();
		int result;
		for (int i = 0; i < 6; i++) {

			if (i == 0) {
				result = random.nextInt(9);
				buffer.append(result + 1);
				continue;
			}
			result = random.nextInt(10);
			buffer.append(result);

		}
		String url;

		url = "http://sms.airlead.net:28080/dxin10/SendMessage_utf8";
		String rollback = utils.gettingCode(url, phone, buffer.toString());
		log.info("验证码"+rollback);
		if (rollback == null || rollback.equals("")) {
			return "10";
		}
		if (rollback.startsWith("00,")) {
			request.getSession().setAttribute("forgotPassTime", unixTime);
			request.getSession().setAttribute("forgotPhone", phone);
			int code = (int) ((Math.random() * 9 + 1) * 100000);
			request.setAttribute("phonecode", code);
			request.getSession().setAttribute("forgotcode", buffer.toString());
			return "00";
		} else {
			return "10";
		}
	}

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(传注册信息过来，验证合法性)
	 */
	@RequestMapping(value = "registerUser", method = RequestMethod.POST)
	@ResponseBody
	public String registerUser(String phone, String code, String regPassword, String twoPassword, String imsi,
			String mobileType, HttpServletRequest request) {
		log.info("注册账号：----------" + "手机号" + phone + "手机验证码" + code + "注册第一次密码" + regPassword + "注册第二次密码" + twoPassword
				+ "手机串码" + imsi + "手机类型" + mobileType);
		Date date = new Date();
		Long unixTime = System.currentTimeMillis();
		Long sessionTime = (Long) request.getSession().getAttribute("regTime");
		Long min;
		if (sessionTime != null && !sessionTime.equals("")) {
			min = ((Long.parseLong(date.getTime() + "") - sessionTime)) / (1000);
			if (min < 99) {
				return "1105";
			}
		}
		if (imsi == null & imsi.equals("")) {
			return "1107";
		}
		if (!mobileType.equals("ios") && !mobileType.equals("android")) {
			return "1108";
		}
		request.getSession().setAttribute("regTime", unixTime);

		if (!"".equals(phone) && null != phone) {

		} else {
			return "1101";// 手机号不能为空
		}
		Pattern p = Pattern.compile("^1[34578]\\d{9}$");
		if (!p.matcher(phone).find()) {
			return "1106";// 手机号不符合规则
		}
		if (!regPassword.equals(twoPassword)) {
			return "1102";// 俩次密码不一样
		} else {
			regPassword = utils.addMD5(regPassword);
		}
		System.out.println((String) request.getSession().getAttribute("regPhone"));
		if (!phone.equals((String) request.getSession().getAttribute("regPhone"))) {
			return "1103";// 判断手机号是否是当前手机
		}
		if (!code.equalsIgnoreCase((String) request.getSession().getAttribute("phonecode"))) {
			return "1104";// 手机验证码是否正确
		}
		String guid = UUID.randomUUID().toString().toUpperCase();
		UserInfo userInfo = new UserInfo();
		userInfo.setId(guid);
		userInfo.setCinamausername(phone);
		userInfo.setPasswordmd5(regPassword);
		userInfo.setStatus(false);
		userInfo.setAdddate(date);
		userInfo.setCinemanickname(imsi);
		userInfo.setWorkstartdays(code);
		userInfo.setMobileType(mobileType);
		getLoginService().reg(userInfo);
		return "0";
	}

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(传)
	 */
	@RequestMapping("forgotPass")
	@ResponseBody
	public String forgotPass(String phone, String code, String regPassword, String twoPassword, String imsi,
			HttpServletRequest request) {

		log.info("忘记密码：----手机号:" + phone + "手机验证码:" + code + "第一次密码:" + regPassword + "第二次密码：" + twoPassword + "手机串码"
				+ imsi);
		Date date = new Date();
		Long unixTime = System.currentTimeMillis();
		Long sessionTime = (Long) request.getSession().getAttribute("forgotTime");
		Long min;
		if (sessionTime != null && !sessionTime.equals("")) {
			min = ((Long.parseLong(date.getTime() + "") - sessionTime)) / (1000);
			if (min < 99) {
				return "5";
			}
		}

		request.getSession().setAttribute("forgotTime", unixTime);
		if (!"".equals(phone) && null != phone) {

		} else {
			return "1201";// 手机号不能为空
		}
		Pattern p = Pattern.compile("^1[34578]\\d{9}$");
		if (!p.matcher(phone).find()) {
			return "1209";// 手机号不符合规则
		}
		if (!regPassword.equals(twoPassword)) {
			return "1202";// 俩次密码不一样
		} else {
			if (regPassword.length() < 6) {
				return "1210";
			}
			regPassword = utils.addMD5(regPassword);
		}
		if (!phone.equals((String) request.getSession().getAttribute("forgotPhone"))) {
			return "1203";// 判断手机号是否是当前手机
		}
		if (!code.equalsIgnoreCase((String) request.getSession().getAttribute("forgotcode"))) {
			return "1204";// 手机验证码是否正确
		}

		UserInfo userInfo = new UserInfo();

		userInfo.setCinamausername(phone);
		userInfo.setPasswordmd5(regPassword);
		userInfo.setCinemanickname(imsi);
		int i = getLoginService().forgot(userInfo);
		return i + "";
	}

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(传用户旧密码，用户新密码，用户二次输入密码)
	 */
	@RequestMapping("chengePassword")
	@ResponseBody
	private int chengePassword(String oldpass, String newpass, String twopass, HttpServletRequest request) {
		log.info("用户在首页修改密码：" + "旧密码" + oldpass + "第一次密码" + newpass + "第二次密码" + twopass);
		if (oldpass == null || oldpass.equals("")) {
			return 1301;
		}
		if (newpass == null || newpass.equals("")) {
			return 1305;
		}
		if (twopass == null || twopass.equals("")) {
			return 1304;
		}
		if (!newpass.equals(twopass)) {
			return 1302;
		}
		if (oldpass.equals(newpass)) {
			return 1303;
		}
		return getLoginService().chengePassword(oldpass, newpass, twopass, request);
	}

}
