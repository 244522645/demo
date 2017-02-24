package com.ybt.web.console.account;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ybt.model.admin.User;


/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，

 * 真正登录的POST请求由Filter完成,
 * 
 */
@Controller
@RequestMapping(value = "/console")
public class LoginController {
	public String baseView() {
		return "/console/";
	}
	@RequestMapping(method = RequestMethod.GET,value = "/login")
	public String loginView(String id,Model model) {
		
		return baseView()+"account/login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)  
    public String login(@Valid User user,RedirectAttributes redirectAttributes){  
        try {  
            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！  
            SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getName(), user.getPassword(),user.isRememberMe()));  
            return "redirect:/console/main";  
        } catch (AuthenticationException e) {  
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");  
            return "redirect:/login";  
        }  
    } 
	
	@RequestMapping(method = RequestMethod.GET,value = "/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}
}
