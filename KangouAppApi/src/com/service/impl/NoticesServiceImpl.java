package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.model.NoticeLogs;
import com.model.Notices;
import com.model.UserInfo;
import com.service.NoticesService;

@Service
public class NoticesServiceImpl extends BaseService implements NoticesService {

	@Override
	public List<Map<String, String>> getNotices(String userid, int page, int pagesize) {
		return getNoticesMapper().selectNotReadByNotice(userid, page, pagesize);
	}

	@Override
	public Notices findNotice(String nid, HttpServletRequest request) {
		int id = 0;
		if (nid != null && !nid.equals("")) {
			id = Integer.parseInt(nid);
		}
		NoticeLogs logs = new NoticeLogs();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		logs.setUserid(userInfo.getId());
		logs.setNoticeid(id);
		logs.setReadtime(new Date());
		getLogsMapper().insert(logs);
		return getNoticesMapper().selectByPrimaryKey(id);
	}

	@Override
	public Notices findLast() {
		return getNoticesMapper().findLast();
	}

}
