package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.UserInfo;

@Controller
@RequestMapping("PosOrders")

public class PosOrdersController extends BaseController {
	@RequestMapping("fingByTime")
	@ResponseBody
	private List<Map<String, String>> fingByTime(String starttime, String endtime, String page, String pagesize,
			HttpServletRequest request) {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		List<Map<String, String>> map = getPosOrdersService().fingByTime(starttime, endtime, page, pagesize,
				userInfo.getParentid());
		return map;
	}

	@RequestMapping("countBytime")
	@ResponseBody
	private List<List<Map<String,String>>> countBytime(String starttime, String endtime, HttpServletRequest request) {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		System.out.println(userInfo.getParentid());
		System.out.println(userInfo.getId());
		return getPosOrdersService().countBytime(starttime, endtime, userInfo.getParentid(),userInfo.getId());
	}

	@RequestMapping("findByserialnum")
	@ResponseBody
	private List<Map<String, String>> findByserialnum(String serialnum,HttpServletRequest request) {
		if (serialnum == null && "".equals(serialnum)) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<String,String> cinemas= (Map<String, String>) request.getSession().getAttribute("cinemaName");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		String id=cinemas.get("id");
		String posid=cinemas.get("posid");
		List<Map<String, String>> map=getPosOrdersService().findByserialnum(serialnum,id,posid,userInfo);
		log.info("查询指定交易 id"+id+"posid"+posid);
		if(1>map.size()){
			return null;
		}
		return map;
	}
}
