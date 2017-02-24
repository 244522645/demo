package com.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.Notices;

public interface NoticesService {
	/*
	 * 获取用户的公告
	 */
	public List<Map<String, String>> getNotices(String userid, int page, int pagesize);

	/*
	 * 根据id获取公告信息
	 */
	public Notices findNotice(String nid,HttpServletRequest request);
	/*
	 * 查询最新一条信息
	 */
	public Notices findLast();
}
