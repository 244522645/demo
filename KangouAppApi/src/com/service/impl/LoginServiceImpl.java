package com.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.NoticeLogs;
import com.model.Notices;
import com.model.UserError;
import com.model.UserInfo;
import com.model.Version;
import com.service.LoginService;

@Service
public class LoginServiceImpl extends BaseService implements LoginService {
	/*
	 * @author 张卫恒
	 * 
	 * @注释：登录
	 * 
	 * @return
	 */
	public Map<String, String> login(String phone, String password, String mobileType, String time, String imsi,
			String token, HttpServletRequest request, String version) {
		Map<String, String> map = new HashMap<String, String>();
		List<UserInfo> list = getUserInfoMapper().selectUserByPhone(phone, null);
		if (list == null || list.size() <= 0) {
			map.put("message", "账号不存在，请注册");
			map.put("Status", "1010");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// 账号错误
		}
		UserInfo userInfo = list.get(0);
		int counterror = getUserErrorMapper().countByerror(userInfo.getId(), "0", format.format(new Date()));
		// if (errorlist.size() != 0) {
		// String date1 =
		// format.format(errorlist.get(0).getErrortime().toString());
		// String date2 = format.format(new Date());
		// if (!date1.equals(date2)) {
		// getUserErrorMapper().deleteByUsername(phone, 0);
		// }
		// }

		if (counterror >= 3) {
			map.put("message", "错误次数超过三次，请第二天重试或联系资源部解除");
			map.put("Status", "1009");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// 错误次数超过三次，请第二天重试或联系资源部解除
		}

		if (!imsi.equals(userInfo.getCinemanickname())) {
			map.put("message", "手机已更换，请重新注册");
			map.put("Status", "1004");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// 手机串码不匹配
		}
		if (!userInfo.isStatus()) {
			map.put("message", "账户没有激活,请联系资源部开通");
			map.put("Status", "1007");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// 账户没有激活，请联系资源部开通
		}
		if (!utils.addMD5(password).equals(userInfo.getPasswordmd5())) {

			UserError userError = new UserError();
			userError.setErrortime(new Date());
			userError.setErrornum("0");
			userError.setErrortype(0);
			userError.setErroruserid(userInfo.getId());
			getUserErrorMapper().insertSelective(userError);
			map.put("message", "密码错误");
			map.put("Status", "1008");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// 密码错误

		}

		UserError userError = new UserError();
		userError.setErrortime(new Date());
		userError.setErrornum("0");
		userError.setErrortype(4);
		userError.setErroruserid(userInfo.getId());
		getUserErrorMapper().insertSelective(userError);
		log.info("登录手机号:" + phone + "登录密码:" + password + "手机类型:" + mobileType + "登录时间：" + time + "手机串码：" + imsi
				+ "md5校验码：" + token + "登录陈宫");
		request.getSession().setAttribute("currentUser", userInfo);
		List<Map<String, String>> cinemaName = getUserInfoMapper().findBycinema(userInfo.getParentid());
		if (cinemaName.size() > 0) {
			request.getSession().setAttribute("cinemaName", cinemaName.get(0));
		} else {
			map.put("message", "终端号错误");
			map.put("Status", "1014");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// 密码错误
		}
		if (counterror > 0) {
			Date dNow = new Date(); // 当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(dNow);// 把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, 1); // 设置为前一天
			dBefore = calendar.getTime(); // 得到前一天的时间
			getUserErrorMapper().updateUserError(format.format(dNow) + " 00:00:00",
					format.format(dBefore) + " 00:00:00", userInfo.getId());
		}
		userInfo.setVersion(version);
		getUserInfoMapper().updateVersion(userInfo);
		Notices notices = getNoticesMapper().findLast();
		if (notices != null && !"".equals(notices)) {
			List<NoticeLogs> noticeLogs = getLogsMapper().selectByUser(userInfo.getId(), notices.getNid() + "");
			log.info(noticeLogs.size());
			if (noticeLogs.size() > 0) {
				map.put("Status", "0");
				map.put("message", "登录成功");
				map.put("Phone", phone);
				map.put("Time", time + "");
				map.put("POPMessage", "0");
				map.put("LastVersion", version);
				return map;
			} else {
				map.put("Status", "0");
				map.put("message", "登录成功");
				map.put("Phone", phone);
				map.put("Time", time + "");
				map.put("POPMessage", notices.getNid() + "");
				map.put("LastVersion", version);
				return map;
			}
		} else {
			map.put("Status", "0");
			map.put("message", "登录成功");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "0");
			map.put("LastVersion", version);
			return map;
		}

	}

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(传手机号过来，验证数据库中是否有当前手机号)
	 */
	public int selectUserByPhone(String phone, String imsi) {

		if (phone != null) {
			if (phone.length() == 11) {
				return getUserInfoMapper().selectUserByPhone(phone, imsi).size();
			}
		}
		return 0;
	}

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(传userinfo对象过来)
	 */

	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public void reg(UserInfo userInfo) {
		try {
			getUserInfoMapper().addUser(userInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public int forgot(UserInfo userInfo) {
		int i = 0;
		try {
			UserInfo user = getUserInfoMapper()
					.selectUserByPhone(userInfo.getCinamausername(), userInfo.getCinemanickname()).get(0);
			System.out.println(user.getCinemanickname());
			System.out.println(userInfo.getCinemanickname());
			System.out.println(user.getCinamausername());
			System.out.println(userInfo.getCinamausername());

			if (!user.getCinemanickname().equals(userInfo.getCinemanickname())) {
				return 1207;
			}

			if (!user.getCinamausername().equals(userInfo.getCinamausername())) {
				return 1208;
			}
			user.setPasswordmd5(userInfo.getPasswordmd5());
			getUserInfoMapper().updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			i = 6;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public int chengePassword(String oldpass, String newpass, String twopass, HttpServletRequest request) {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		if (userInfo == null && "".equals(userInfo)) {
			return 1306;
		}
		System.out.println(userInfo.getCinamausername());
		int i = 0;
		try {
			UserInfo user = getUserInfoMapper()
					.selectUserByPhone(userInfo.getCinamausername(), userInfo.getCinemanickname()).get(0);
			String MD5password = utils.addMD5(oldpass);
			if (!user.getPasswordmd5().equals(MD5password)) {
				return 1307;
			}
			user.setPasswordmd5(utils.addMD5(newpass));
			getUserInfoMapper().updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			i = 1308;
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public Version checkVersion(String mobileType) {
		return getVersionMapper().selsectByMobileType(mobileType);

	}

}
