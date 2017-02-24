package com.ybt.web.console.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.model.admin.Dept;
import com.ybt.model.admin.User;
import com.ybt.service.admin.DeptService;
import com.ybt.service.admin.UserService;


/**
 * 用户管理处理类，用户的增 删 改 查
 * 
 */
@Controller
@RequestMapping(value = "/console/admin/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	
	@RequestMapping
	public String defaultView(Model model,@RequestParam(value = "page", defaultValue = "1") int pageNumber,HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, PropertyFilter.FILTER_PREFIX));
		com.ybt.common.util.Page<User> pageUtil = new com.ybt.common.util.Page<User>();
		pageUtil.setPageNo(pageNumber);
		Pageable pager =new PageRequest(pageUtil.getPageNo()-1, pageUtil.getPageSize()); 
		Page<User> page = userService.findUserByProperty(filters, pager);
		pageUtil.setTotalCount(page.getTotalElements());
		pageUtil.setResult(page.getContent());
		model.addAttribute("page", pageUtil);
		return "/console/admin/user/userList";
	}
	
	//跳转到用户修改和查看页面
	@RequestMapping(value = "/edit/{userId:.*}")
	public String editVile(@PathVariable("userId") String userId,Model model,HttpServletRequest request){
		User user = userService.getUserByUserId(userId);
		Dept dept = deptService.getDeptByDeptId(user.getDeptId());
		if(dept!=null)
			user.setDeptName(dept.getName());
		model.addAttribute("user",user);
		return "/console/admin/user/userEdit";
	}
	
	//修改用户
	@RequestMapping(method = RequestMethod.POST,value = "/editUser")
	public String editUser(User user,Model model,HttpServletRequest request){
		User user1 = userService.getUserByUserId(user.getUserId());
		if(user1.getPassword().equals(user.getPassword())){
			user = userService.modifyUser(user);
		}else{
		    userService.addUser(user);
		}
		
		if(user!=null)
			model.addAttribute("msg",true);
		model.addAttribute("user",user);
		return "/console/admin/user/userEdit";
	}
	

	//批量删除用户
	@RequestMapping(method = RequestMethod.POST,value = "/batchDel")
	public String batchDel(Model model,HttpServletRequest request){
		String[] userIds = request.getParameterValues("userIds");
		
		if(userIds!=null&&userIds.length!=0)
			for(int i=0;i<userIds.length;i++){
				userService.delUserByUserId(userIds[i]);
			}
		return "redirect:/admin/user";
	}
	
	//删除用户
	@RequestMapping(produces="text/plain;charset=UTF-8",value = "/del/{userId:.*}")
	public String del(Model model,HttpServletRequest request,@PathVariable("userId") String userId){
	   userService.delUserByUserId(userId);
		return "redirect:/console/admin/user";
	}
	
	//跳转到用户新增页面
	@RequestMapping(value = "/add")
	public String addVile(Model model,HttpServletRequest request){
		
		return "/console/admin/user/userAdd";
	}
	
	//新增保存用户
	@RequestMapping(value = "/saveUser")
	public String saveUser(User user,Model model,HttpServletRequest request){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		user.setCreatetime(dateformat.format(new Date()));
		boolean msg = userService.addUser(user);
		model.addAttribute("msg",msg);
		model.addAttribute("user",user);
		return "/console/admin/user/userAdd";
	}
	
}
