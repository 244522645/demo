package com.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.UserInfo;
import com.model.Version;

public interface LoginService {
	/*
	 * @author 张卫恒
	 * 
	 * @注释：登录
	 * 
	 * @return
	 */
	public Map<String, String> login(String phone, String password, String mobileType, String time, String imsi,
			String token, HttpServletRequest request,String version);

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(传手机号过来，验证数据库中是否有当前手机号)
	 */
	public int selectUserByPhone(String phone,String imsi);

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(传userinfo对象过来)
	 */
	public void reg(UserInfo userInfo);

	/*
	 * @author (张卫恒).
	 * 
	 * @忘记密码
	 * 
	 * @注释：(传userinfo对象过来)
	 */
	public int forgot(UserInfo userInfo);

	/*
	 * @author (张卫恒)
	 * 
	 * @注释：(传用户旧密码，用户新密码，用户二次输入密码)
	 */
	public int chengePassword(String oldpass, String newpass, String twopass, HttpServletRequest request);
	/*
	 * 查询更新数据
	 */
	public Version checkVersion(String mobileType);
}
