package com.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.KUser;
import com.service.KUserRolesService;

@Controller

public class ReturnController extends BaseController {

	@Autowired(required = false)
	private KUserRolesService kuserRolesService;

	// 传request对象和url进来
	public ModelAndView isnull(HttpServletRequest request, String url) {
		KUser user = (KUser) request.getSession().getAttribute("username");
		if (user == null || user.equals("")) {
			return new ModelAndView("404");
		} else {
			KUser kUser = (KUser) request.getSession().getAttribute("username");
			Integer count = kuserRolesService.returnurl(kUser.getId(), url);
			System.out.println(url);
			if (count != 0) {
				return new ModelAndView(url);
			} else {
				return new ModelAndView("redirect:login.do");
			}

		}
	}

	// 跳转至登录页面
	@RequestMapping("/login")
	public ModelAndView login() {

		return new ModelAndView("login");
	}

	// 跳转至首页
	@RequestMapping("/turnindex")
	public ModelAndView turnindex(HttpServletRequest request, String url) {
		if (!url.equalsIgnoreCase("index")) {
			return this.isnull(request, url);
		} else {
			KUser user = (KUser) request.getSession().getAttribute("username");
			if (user == null || user.equals("")) {
				System.out.println("这是跳转的页面");
				return new ModelAndView("redirect:login.do");
			} else {
				return new ModelAndView("index");
			}

		}

	}

	// 开卡获取服务器时间的方法
	@RequestMapping("systemTime")
	@ResponseBody
	public String systemTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	// 开卡计算时间的方法
	@RequestMapping("countTime")
	@ResponseBody
	public String countTime(int num) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (num != 12) {
			calendar.add(Calendar.MONTH, num);

			Date date = calendar.getTime();
			String dateString = formatter.format(date);
			System.out.println(dateString);
			return dateString;
		} else {
			calendar.add(Calendar.YEAR, 1);

			Date date = calendar.getTime();
			String dateString = formatter.format(date);
			System.out.println(dateString);
			return dateString;
		}

	}
}
