package com.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.KUser;

public interface KUserService {
	//��¼
	public KUser findByName(String  username);
	//�޸�����
	public void updatepassword(String id,String password);
	/*
	 * Ϊ�û�������ֵ
	 * 
	 */
	public List<Map<String,String>> selectUser();
	/*
	 * ��ѯ��ǰ�û�����Ϣ
	 */
	public KUser findByUser(HttpServletRequest request);
}
