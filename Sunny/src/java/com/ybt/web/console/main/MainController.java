package com.ybt.web.console.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.util.AjaxPageUtil;
import com.ybt.common.util.PropertyFilter;
import com.ybt.model.admin.Dept;
import com.ybt.model.admin.User;
import com.ybt.service.admin.DeptService;
import com.ybt.service.admin.UserService;

@Controller
@RequestMapping(value = "/console/main")
public class MainController {
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private DeptService deptService;
	@Autowired
	private UserService userService;
	
	@RequestMapping
	public String indexView(Model model,HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		@SuppressWarnings("unused")
		User user = (User) subject.getPrincipal();
		
		return "/console/index";
	}
	

	//跳转到帐号管理页面
	@RequestMapping(value = "/MyInfoEdit")
	public String editMyVile( Model model,HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		Dept dept = deptService.getDeptByDeptId(user.getDeptId());
		if(dept!=null)
			user.setDeptName(dept.getName());
		model.addAttribute("user",user);
		return "/console/admin/user/myuserEdit";
	}
	//修改我的用户密码
	@RequestMapping(method = RequestMethod.POST,value = "/editMyUser")
	public String editMyUser(Model model,HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		User users = (User) subject.getPrincipal();
		String password =request.getParameter("password");
		String truePassword =request.getParameter("truePassword");
		String newPassword =request.getParameter("newPassword");
		User user=null;
		if(password!=null&newPassword!=null&truePassword!=null){
		 user=userService.getUserByUserIdAndPassword(users.getUserId(),password);
		}
		/*user = userService.modifyUser(user);*/
		if(user!=null){
			model.addAttribute("msg",true);
			user.setPassword(request.getParameter("truePassword"));
			user = userService.modifyUser(user);
		}else{
			model.addAttribute("msg",false);
		}
		
		model.addAttribute("user",user);
		return "/console/admin/user/myuserEdit";
	}

	//获取部门json
		@RequestMapping(value = "/jsonDept")
		@ResponseBody
		public List<Map<String,String>> jsonDept(Model model,HttpServletRequest request){
			List<Dept> list = deptService.findAllDept();
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			
			for(Dept dept:list){
				Map<String,String> map = new HashMap<String,String>();
				map.put("id",dept.getDeptId());
				map.put("pId",dept.getParentId());
				map.put("name",dept.getName());
				if("-1".equals(dept.getParentId())){
					map.put("open","true");
				}
				mapList.add(map);
			}
			return mapList;
		}

		@RequestMapping(value = "/jsonUser")
		@ResponseBody
		public com.ybt.common.util.Page<User> jsonUser(HttpServletRequest request){
			com.ybt.common.util.Page<User> page = new com.ybt.common.util.Page<User>(AjaxPageUtil.PAGE_SIZE);
			AjaxPageUtil.init(page, request);
			
			Pageable pager =new PageRequest(page.getPageNo()-1, page.getPageSize());
			String deptId = request.getParameter("deptId");
			PropertyFilter pf = new PropertyFilter("EQS_deptId",deptId);
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
			filters.add(pf);
			org.springframework.data.domain.Page<User> myPage = userService.findUserByProperty(filters, pager);
			page.setTotalCount(myPage.getTotalElements());
			page.setResult(myPage.getContent());
			return page;
		}
		
	//获取部门+用户json
	@RequestMapping(value = "/tree/all")
	@ResponseBody
	public List<Map<String,String>> jsonDeptUser(Model model,HttpServletRequest request){
		return deptService.getTreeDateToDeptUser();
	}
	
	//获取部门tree 带复选框
	@RequestMapping(value = "/tree/dept")
	@ResponseBody
	public List<Map<String,String>> jsonDeptTree(Model model,HttpServletRequest request){
		List<Dept> list = deptService.findAllDept();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		
		for(Dept dept:list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("id",dept.getDeptId());
			map.put("pId",dept.getParentId());
			map.put("name",dept.getName());
			if("-1".equals(dept.getParentId())){
				map.put("open","true");
				map.put("nocheck","true");
			}
			mapList.add(map);
		}
		return mapList;
	}
}
