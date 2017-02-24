package com.ybt.web.console.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ybt.model.admin.Menu;
import com.ybt.model.admin.User;
import com.ybt.service.admin.MenuService;
import com.ybt.service.admin.UserService;

@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService userService;
	@RequestMapping(method = RequestMethod.GET,value = "/login")
	public String loginView() {
		return "/console/account/login";
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "console/main/main")
	public String mainView() {
		return "/console/main/main";
	}
	
	//左边菜单
	@RequestMapping(method = RequestMethod.GET,value = "/console/leftMenu/{id}")
	public String view(@PathVariable("id") String id,Model model,HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		//一级菜单
		List<Menu> menu = menuService.findMenuOnUser(user.getUserId(), id);
		model.addAttribute("menu", menu);
		model.addAttribute("herf", request.getParameter("herf"));
		model.addAttribute("parentMenu", menuService.getMenuByMenuId(id));
		return "/console/main/left";
	}
	
	//无权限
	@RequestMapping(method = RequestMethod.GET,value = "/console/unauth")
	public String unauth(HttpServletRequest request) {
		return "/console/main/unauth";
	}
	
	//头部
	@RequestMapping(method = RequestMethod.GET,value = "/console/menu/top")
	public String mainBody(Model model,HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		model.addAttribute("name",userService.getUserByUserId(user.getUserId()).getName());
		//一级菜单
		List<Menu> menu = menuService.findMenuOnUser(user.getUserId(), "0");
		model.addAttribute("menu", menu);
		model.addAttribute("menuId", ""+request.getParameter("id"));
		model.addAttribute("herf", request.getParameter("herf"));
		return "/console/main/top";
	}
	
}
