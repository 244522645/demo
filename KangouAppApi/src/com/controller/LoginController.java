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
	 * @author ������
	 * 
	 * @ע�ͣ���¼
	 * 
	 * @return
	 */
	@RequestMapping(value = "login.do",method = RequestMethod.POST) // post�ύ
	@ResponseBody
	public Map<String, String> login(String phone, String password, String mobileType, String time, String imsi,
			String version, String token, HttpServletRequest request) {
		log.info("��½sessionID" + request.getSession().getId());
		log.info("��¼�ֻ���:" + phone + "��¼����:" + password + "�ֻ�����:" + mobileType + "��¼ʱ�䣺" + time + "�ֻ����룺" + imsi
				+ "md5У���룺" + token);
		Long longTime = System.currentTimeMillis();
		Map<String, String> map = new HashMap<String, String>();
		map.put("Time", longTime + "");
		try {
			if (!mobileType.equals("ios") && !mobileType.equals("android")) {
				map.put("message", "�ֻ����ʹ���");
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
					map.put("message", "�ֻ��汾����");
					map.put("Status", "1012");
					map.put("Phone", phone);
					map.put("POPMessage", "");

					map.put("LastVersion", "");
					return map;
				}
			} else {
				map.put("message", "�ֻ��汾����");
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
				map.put("message", "�������ֻ���");
				map.put("Phone", "");
				map.put("POPMessage", "");
				return map;
			}

			if (token == null || token.equals("")) {

				map.put("message", "У�������");
				map.put("Status", "1001");
				map.put("Phone", phone);
				map.put("POPMessage", "");
				return map;
			}

			if (time == null || time.equals("")) {
				map.put("Status", "1006");
				map.put("message", "ʱ�����");
				map.put("Phone", phone);
				map.put("POPMessage", "");
				return map;
			} else {
				Date date = new Date();
				Long min = ((Long.parseLong(date.getTime() + "") - Long.parseLong(time))) / (1000 * 60);
				if (-30 >= min || min >= 30) {
					request.setAttribute("message", "���ڱ���ʱ��У׼");
					map.put("Status", "1006");
					map.put("message", "ʱ�����");
					map.put("Phone", phone);
					map.put("POPMessage", "");
					return map;
				}
			}

			if (imsi != null && !imsi.equals("")) {

			} else {
				map.put("Status", "1005");
				map.put("message", "�ֻ����벻��Ϊ��");
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
				map.put("message", "У�������");
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
				map.put("message", "��½��������ϵ����Ա");
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
	 * @author (������)
	 * 
	 * @ע�ͣ�(ע��ҳ����ת) �ύ�������ֻ��ţ��ֻ�imsi,token
	 */
	@RequestMapping("/reg")
	public String registration(String imsi, String mobileType, ModelMap modelMap, HttpServletRequest request) {
		log.info("�ֻ�����" + imsi + "�ֻ�����" + mobileType + "ע��");
		if (imsi == null || imsi.equals("")) {
			modelMap.put("messageid", "1501");
			modelMap.put("message", "Ч�����");
			return "404";
		}
		if (!mobileType.equals("ios") && !mobileType.equals("android")) {
			modelMap.put("messageid", "1502");
			modelMap.put("message", "�ֻ����ʹ���");
			return "404";
		}
		modelMap.put("mobileType", mobileType);
		modelMap.put("imsi", imsi);
		return "reg";
	}

	/*
	 * @author (������)
	 * 
	 * @ע�ͣ�(��������ҳ����ת) �ύ�������ֻ��ţ��ֻ�imsi,token
	 */
	@RequestMapping("/updatePassword")
	public String forgotPassword(String imsi, String mobileType, ModelMap modelMap, HttpServletRequest request) {
		log.info("�ֻ�����" + imsi + "�ֻ�����" + mobileType + "��������");
		if (imsi == null || imsi.equals("")) {
			modelMap.put("messageid", "1601");
			modelMap.put("message", "Ч�����");

			return "404";
		}
		modelMap.addAttribute("imsi", imsi);
		modelMap.addAttribute("mobileType", mobileType);
		return "forgotPass";
	}

	/*
	 * @author (������)
	 * 
	 * @ע�ͣ�(�ύ�����ֻ��ţ�������ϢID��ʱ������ֻ���ʾ�룬MD5Ч��)
	 */
	@RequestMapping(value="POPMessage",method = RequestMethod.POST)
	private String POPMessage(String phone, String popMessageID, String time, String imsi, String token,
			HttpServletRequest request) {
		log.info("�ֻ���" + phone + "��Ϣid" + popMessageID + "ʱ��" + time + "�ֻ�����" + imsi + "�ֻ�У����" + token);
		if (token == null || token.equals("")) {
			request.setAttribute("messageid", "1401");
			request.setAttribute("message", "Ч�����");

			return "404";
		}
		if (popMessageID == null || popMessageID.equals("")) {
			request.setAttribute("messageid", "1402");
			request.setAttribute("message", "��Ϣ��ȡ����");

			return "404";
		}
		if (phone == null || phone.equals("")) {
			request.setAttribute("messageid", "1403");
			request.setAttribute("message", "�ֻ��Ŵ���");

			return "404";
		}
		if (time == null || time.equals("")) {
			request.setAttribute("messageid", "1404");
			request.setAttribute("message", "ʱ�����");
			return "404";
		} else {
			Date date = new Date();
			Long min = ((Long.parseLong(date.getTime() + "") - Long.parseLong(time))) / (1000 * 60);
			if (-30 >= min || min >= 30) {
				request.setAttribute("messageid", "1405");
				request.setAttribute("message", "���ڱ���ʱ��У׼");

				return "404";
			}
		}
		if (imsi == null || imsi.equals("")) {
			request.setAttribute("messageid", "1406");
			request.setAttribute("message", "�ֻ��������");

			return "404";
		}
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		if (!imsi.equals(userInfo.getCinemanickname())) {
			request.setAttribute("messageid", "1407");
			request.setAttribute("message", "�ֻ��������");

			return "404";
		}
		if (!phone.equals(userInfo.getCinamausername())) {
			request.setAttribute("messageid", "1408");
			request.setAttribute("message", "�ֻ��Ŵ���");

			return "404";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(phone);
		buffer.append(popMessageID);
		buffer.append(time);

		boolean isture = utils.equalsMD5(buffer.toString(), imsi, token);
		if (!isture) {
			request.setAttribute("messageid", "1409");
			request.setAttribute("message", "Ч����Ч�����");

			return "404";
		}
		Notices notices = getNoticesService().findNotice(popMessageID, request);
		if (notices != null && !notices.equals("")) {
			request.setAttribute("notice", notices);
			return "message";
		}

		request.setAttribute("messageid", "1410");
		request.setAttribute("message", "û�иù���");

		return "404";
	}

	/*
	 * @author (������)
	 * 
	 * @ע�ͣ�(���ֻ��Ź�������֤���ݿ����Ƿ��е�ǰ�ֻ���)
	 */
	@RequestMapping("selectUserByPhone")
	@ResponseBody
	public int selectUserByPhone(String phone, String imsi) {
		log.info("���ǻ�ȡ��֤����ֻ���" + phone + "����" + imsi);
		int i = getLoginService().selectUserByPhone(phone, imsi);
		System.out.println(phone);
		return i;
	}

	/*
	 * @author (������)
	 * 
	 * @ע�ͣ�(���ֻ��Ź���,��ȡ��֤��)
	 */
	@RequestMapping("gettingCode")
	@ResponseBody
	public String gettingCode(String phone, HttpServletRequest request) {
		if (phone == null || "".equals(phone)) {
			return "6";
		}
		log.info("�ֻ��ţ�" + phone + "��ȡ��֤��");
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
	 * @author (������)
	 * 
	 * @��������
	 * 
	 * @ע�ͣ�(���ֻ��Ź���,��ȡ��֤��)
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
		log.info("��֤��"+rollback);
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
	 * @author (������)
	 * 
	 * @ע�ͣ�(��ע����Ϣ��������֤�Ϸ���)
	 */
	@RequestMapping(value = "registerUser", method = RequestMethod.POST)
	@ResponseBody
	public String registerUser(String phone, String code, String regPassword, String twoPassword, String imsi,
			String mobileType, HttpServletRequest request) {
		log.info("ע���˺ţ�----------" + "�ֻ���" + phone + "�ֻ���֤��" + code + "ע���һ������" + regPassword + "ע��ڶ�������" + twoPassword
				+ "�ֻ�����" + imsi + "�ֻ�����" + mobileType);
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
			return "1101";// �ֻ��Ų���Ϊ��
		}
		Pattern p = Pattern.compile("^1[34578]\\d{9}$");
		if (!p.matcher(phone).find()) {
			return "1106";// �ֻ��Ų����Ϲ���
		}
		if (!regPassword.equals(twoPassword)) {
			return "1102";// �������벻һ��
		} else {
			regPassword = utils.addMD5(regPassword);
		}
		System.out.println((String) request.getSession().getAttribute("regPhone"));
		if (!phone.equals((String) request.getSession().getAttribute("regPhone"))) {
			return "1103";// �ж��ֻ����Ƿ��ǵ�ǰ�ֻ�
		}
		if (!code.equalsIgnoreCase((String) request.getSession().getAttribute("phonecode"))) {
			return "1104";// �ֻ���֤���Ƿ���ȷ
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
	 * @author (������)
	 * 
	 * @ע�ͣ�(��)
	 */
	@RequestMapping("forgotPass")
	@ResponseBody
	public String forgotPass(String phone, String code, String regPassword, String twoPassword, String imsi,
			HttpServletRequest request) {

		log.info("�������룺----�ֻ���:" + phone + "�ֻ���֤��:" + code + "��һ������:" + regPassword + "�ڶ������룺" + twoPassword + "�ֻ�����"
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
			return "1201";// �ֻ��Ų���Ϊ��
		}
		Pattern p = Pattern.compile("^1[34578]\\d{9}$");
		if (!p.matcher(phone).find()) {
			return "1209";// �ֻ��Ų����Ϲ���
		}
		if (!regPassword.equals(twoPassword)) {
			return "1202";// �������벻һ��
		} else {
			if (regPassword.length() < 6) {
				return "1210";
			}
			regPassword = utils.addMD5(regPassword);
		}
		if (!phone.equals((String) request.getSession().getAttribute("forgotPhone"))) {
			return "1203";// �ж��ֻ����Ƿ��ǵ�ǰ�ֻ�
		}
		if (!code.equalsIgnoreCase((String) request.getSession().getAttribute("forgotcode"))) {
			return "1204";// �ֻ���֤���Ƿ���ȷ
		}

		UserInfo userInfo = new UserInfo();

		userInfo.setCinamausername(phone);
		userInfo.setPasswordmd5(regPassword);
		userInfo.setCinemanickname(imsi);
		int i = getLoginService().forgot(userInfo);
		return i + "";
	}

	/*
	 * @author (������)
	 * 
	 * @ע�ͣ�(���û������룬�û������룬�û�������������)
	 */
	@RequestMapping("chengePassword")
	@ResponseBody
	private int chengePassword(String oldpass, String newpass, String twopass, HttpServletRequest request) {
		log.info("�û�����ҳ�޸����룺" + "������" + oldpass + "��һ������" + newpass + "�ڶ�������" + twopass);
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
