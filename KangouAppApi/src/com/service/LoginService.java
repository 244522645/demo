package com.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.UserInfo;
import com.model.Version;

public interface LoginService {
	/*
	 * @author ������
	 * 
	 * @ע�ͣ���¼
	 * 
	 * @return
	 */
	public Map<String, String> login(String phone, String password, String mobileType, String time, String imsi,
			String token, HttpServletRequest request,String version);

	/*
	 * @author (������)
	 * 
	 * @ע�ͣ�(���ֻ��Ź�������֤���ݿ����Ƿ��е�ǰ�ֻ���)
	 */
	public int selectUserByPhone(String phone,String imsi);

	/*
	 * @author (������)
	 * 
	 * @ע�ͣ�(��userinfo�������)
	 */
	public void reg(UserInfo userInfo);

	/*
	 * @author (������).
	 * 
	 * @��������
	 * 
	 * @ע�ͣ�(��userinfo�������)
	 */
	public int forgot(UserInfo userInfo);

	/*
	 * @author (������)
	 * 
	 * @ע�ͣ�(���û������룬�û������룬�û�������������)
	 */
	public int chengePassword(String oldpass, String newpass, String twopass, HttpServletRequest request);
	/*
	 * ��ѯ��������
	 */
	public Version checkVersion(String mobileType);
}
