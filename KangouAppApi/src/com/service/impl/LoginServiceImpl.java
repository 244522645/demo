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
	 * @author ������
	 * 
	 * @ע�ͣ���¼
	 * 
	 * @return
	 */
	public Map<String, String> login(String phone, String password, String mobileType, String time, String imsi,
			String token, HttpServletRequest request, String version) {
		Map<String, String> map = new HashMap<String, String>();
		List<UserInfo> list = getUserInfoMapper().selectUserByPhone(phone, null);
		if (list == null || list.size() <= 0) {
			map.put("message", "�˺Ų����ڣ���ע��");
			map.put("Status", "1010");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// �˺Ŵ���
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
			map.put("message", "��������������Σ���ڶ������Ի���ϵ��Դ�����");
			map.put("Status", "1009");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// ��������������Σ���ڶ������Ի���ϵ��Դ�����
		}

		if (!imsi.equals(userInfo.getCinemanickname())) {
			map.put("message", "�ֻ��Ѹ�����������ע��");
			map.put("Status", "1004");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// �ֻ����벻ƥ��
		}
		if (!userInfo.isStatus()) {
			map.put("message", "�˻�û�м���,����ϵ��Դ����ͨ");
			map.put("Status", "1007");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// �˻�û�м������ϵ��Դ����ͨ
		}
		if (!utils.addMD5(password).equals(userInfo.getPasswordmd5())) {

			UserError userError = new UserError();
			userError.setErrortime(new Date());
			userError.setErrornum("0");
			userError.setErrortype(0);
			userError.setErroruserid(userInfo.getId());
			getUserErrorMapper().insertSelective(userError);
			map.put("message", "�������");
			map.put("Status", "1008");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// �������

		}

		UserError userError = new UserError();
		userError.setErrortime(new Date());
		userError.setErrornum("0");
		userError.setErrortype(4);
		userError.setErroruserid(userInfo.getId());
		getUserErrorMapper().insertSelective(userError);
		log.info("��¼�ֻ���:" + phone + "��¼����:" + password + "�ֻ�����:" + mobileType + "��¼ʱ�䣺" + time + "�ֻ����룺" + imsi
				+ "md5У���룺" + token + "��¼�¹�");
		request.getSession().setAttribute("currentUser", userInfo);
		List<Map<String, String>> cinemaName = getUserInfoMapper().findBycinema(userInfo.getParentid());
		if (cinemaName.size() > 0) {
			request.getSession().setAttribute("cinemaName", cinemaName.get(0));
		} else {
			map.put("message", "�ն˺Ŵ���");
			map.put("Status", "1014");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "");
			map.put("LastVersion", version);
			return map;// �������
		}
		if (counterror > 0) {
			Date dNow = new Date(); // ��ǰʱ��
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); // �õ�����
			calendar.setTime(dNow);// �ѵ�ǰʱ�丳������
			calendar.add(Calendar.DAY_OF_MONTH, 1); // ����Ϊǰһ��
			dBefore = calendar.getTime(); // �õ�ǰһ���ʱ��
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
				map.put("message", "��¼�ɹ�");
				map.put("Phone", phone);
				map.put("Time", time + "");
				map.put("POPMessage", "0");
				map.put("LastVersion", version);
				return map;
			} else {
				map.put("Status", "0");
				map.put("message", "��¼�ɹ�");
				map.put("Phone", phone);
				map.put("Time", time + "");
				map.put("POPMessage", notices.getNid() + "");
				map.put("LastVersion", version);
				return map;
			}
		} else {
			map.put("Status", "0");
			map.put("message", "��¼�ɹ�");
			map.put("Phone", phone);
			map.put("Time", time + "");
			map.put("POPMessage", "0");
			map.put("LastVersion", version);
			return map;
		}

	}

	/*
	 * @author (������)
	 * 
	 * @ע�ͣ�(���ֻ��Ź�������֤���ݿ����Ƿ��е�ǰ�ֻ���)
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
	 * @author (������)
	 * 
	 * @ע�ͣ�(��userinfo�������)
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
