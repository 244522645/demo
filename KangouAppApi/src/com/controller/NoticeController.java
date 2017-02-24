package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.UserInfo;

@Controller
@RequestMapping("notice")
public class NoticeController extends BaseController {

	@RequestMapping("getNotices")
	@ResponseBody
	public List<Map<String, String>> getNotices(HttpServletRequest request, int page, int pagesize) {
		System.out.println(page);
		System.out.println(pagesize);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		return getNoticesService().getNotices(userInfo.getId(), page, pagesize);
	}

	@RequestMapping("findNotice")
	public String findNotice(String nid, HttpServletRequest request) {
		request.setAttribute("notice", getNoticesService().findNotice(nid, request));
		return "message";
	}

}
