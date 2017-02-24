package com.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.KUser;

public interface KUserService {
	//登录
	public KUser findByName(String  username);
	//修改密码
	public void updatepassword(String id,String password);
	/*
	 * 为用户下拉框赋值
	 * 
	 */
	public List<Map<String,String>> selectUser();
	/*
	 * 查询当前用户的信息
	 */
	public KUser findByUser(HttpServletRequest request);
}
