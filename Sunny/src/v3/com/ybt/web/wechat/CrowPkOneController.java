package com.ybt.web.wechat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ybt.model.work.CrowPkOne;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.aop.Authorization;
import com.ybt.service.work.CrowPkOneService;

@Controller
@RequestMapping("/wechat/v3/crow/pkone")
public class CrowPkOneController {
	@Autowired(required = false)
	private CrowPkOneService crowPkOneService;

	public CrowPkOneService getCrowPkOneService() {
		return crowPkOneService;
	}

	public void setCrowPkOneService(CrowPkOneService crowPkOneService) {
		this.crowPkOneService = crowPkOneService;
	}

	private String baseView(String v) {
		return "/work/wechat/v3/tiaozhan/" + v;
	}

	@RequestMapping("CrowUserOne")
	public String CrowUserOne() {
		return this.baseView("tiaozhan");
	}

	/*
	 * zwh 查询当前用户的挑战
	 */
	@RequestMapping(method = RequestMethod.GET)
	@Authorization
	private String findCrowUser(HttpServletRequest request) {
		
		SunWechatUser user=(SunWechatUser)  request.getSession().getAttribute("userInfo");
		 
		List<CrowPkOne> list = getCrowPkOneService().findByuserid(user.getId());
		List<CrowPkOne> winList = getCrowPkOneService().findCrowUserWin(user.getId());
		List<CrowPkOne> loseList = getCrowPkOneService().findCrowUserLose(user.getId());

		System.out.println(list.size());
		request.setAttribute("CrowPkOne", list);
		request.setAttribute("winList", winList);
		request.setAttribute("loseList", loseList);
		return baseView("jilu");
	}

	@RequestMapping("show")
	private String findByID(String id,HttpServletRequest request) {
		CrowPkOne crowPkOne =getCrowPkOneService().findOne(id);
		System.out.println(crowPkOne.getPkStatus());
		request.setAttribute("pkUsers", crowPkOne);
		return baseView("tiaozhan");
	}
}
