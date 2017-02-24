package com.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.Notices;

public interface NoticesService {
	/*
	 * ��ȡ�û��Ĺ���
	 */
	public List<Map<String, String>> getNotices(String userid, int page, int pagesize);

	/*
	 * ����id��ȡ������Ϣ
	 */
	public Notices findNotice(String nid,HttpServletRequest request);
	/*
	 * ��ѯ����һ����Ϣ
	 */
	public Notices findLast();
}
