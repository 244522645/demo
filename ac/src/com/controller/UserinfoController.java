package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.KUser;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/KUser")
public class UserinfoController extends BaseController {
	
		//登录
		@RequestMapping("/login")
		public String login(String username,String password,HttpServletRequest request,HttpServletResponse response){
			KUser kUser= getKuserService().findByName(username);
			if(kUser!=null&&!kUser.equals("")){
				if(kUser.getPassword().equals(password)){
					request.getSession().setAttribute("username", kUser);
					try {
						response.getWriter().print(1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return  null;
				}else{
					try {
						response.getWriter().print(2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			}else{
				try {
					response.getWriter().print(3);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
			
		}
		
		
	/*
	 * 退出
	 */
	@RequestMapping("/out")

	public void out(HttpServletRequest request,HttpServletResponse response){
		
		request.getSession().removeAttribute("username");
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 修改密码
	 */
	@RequestMapping("updatepassword")
	public void updatepassword(String newpass,HttpServletResponse response,HttpServletRequest request) {
		int i=0;
		try {
			KUser  user=(KUser) request.getSession().getAttribute("username");
			String id=user.getId();
			getKuserService().updatepassword(id, newpass);
			System.out.println(newpass);
		} catch (Exception e) {
			i=1;
			e.printStackTrace();
		}
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 为用户下拉框赋值
	 * 
	 */
	@RequestMapping("/selectUser")
	public void selectUser(HttpServletResponse response) {
		List<Map<String,String>> user=getKuserService().selectUser();
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(JSONArray.fromObject(user));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 查询当前用户的信息
	 */
	@RequestMapping("/findByUser")
	@ResponseBody
	private KUser findByUser(HttpServletRequest request) {
		KUser user=getKuserService().findByUser(request);
		return user;
	}
}
