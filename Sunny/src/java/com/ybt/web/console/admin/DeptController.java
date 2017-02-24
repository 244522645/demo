package com.ybt.web.console.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.util.AjaxPageUtil;
import com.ybt.common.util.Page;
import com.ybt.common.util.PropertyFilter;
import com.ybt.model.admin.Dept;
import com.ybt.model.admin.User;
import com.ybt.service.admin.DeptService;
import com.ybt.service.admin.UserService;


/**
 * 部门管理处理类，部门的增 删 改 查
 * 
 */
@Controller
@RequestMapping(value = "/console/admin/dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	@Autowired
	private UserService userService;
	
	@RequestMapping
	public String defaultView(Model model,HttpServletRequest request){
		return "/console/admin/dept/deptList";
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
	
	
	@RequestMapping(value = "/jsonUser")
	@ResponseBody
	public com.ybt.common.util.Page<User> jsonUser(HttpServletRequest request){
		Page<User> page = new Page<User>(AjaxPageUtil.PAGE_SIZE);
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
	
	//保存部门
	@RequestMapping(value = "/jsonSaveDept",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String jsonSaveDept(Dept dept,HttpServletRequest request){
		Boolean msg = deptService.saveDept(dept);
		if(msg)
			return "操作成功";
		return "操作失败";
	}
	
	//获取一个部门
	@RequestMapping(value = "/jsonGetDept")
	@ResponseBody
	public Dept jsonGetDept(HttpServletRequest request){
		String deptId = request.getParameter("deptId");
		Dept dept = deptService.getDeptByDeptId(deptId);
		if(dept!=null){
			List<Dept> tdepts = deptService.getParentDeptByDeptId(dept.getDeptId());
			if(tdepts!=null&&!tdepts.isEmpty()){
				dept.setParentName(tdepts.get(0).getName());
			}
		}
		return dept;
	}
	
	//删除部门
	@RequestMapping(value = "/jsonDelDept",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String jsonDelDept(HttpServletRequest request){
		String deptId = request.getParameter("deptId");
		List<User> list = deptService.findUserByDeptId(deptId);
		if(list!=null&&list.size()>0){
			return "该部门下有用户，不可以删除";
		}
		
		Boolean flag = deptService.delDeptByDeptId(deptId);
		if(flag==true){
			return "true";
		}else{
			return "false";
		}
	}
	
	//添加用户
	@RequestMapping(value = "/jsonAddUser")
	@ResponseBody
	public boolean jsonAddUser(HttpServletRequest request){
		String userIds = request.getParameter("userIds");
		String deptId = request.getParameter("deptId");
		String[] userId = {};
		if(userIds!=null){
			userId = userIds.split(",");
		}
		
		return deptService.addUserToDept(deptId, userId);
	}
	
	//删除用户
	@RequestMapping(value = "/jsonDelUser")
	@ResponseBody
	public boolean jsonDelUser(HttpServletRequest request){
		String userIds = request.getParameter("userIds");
		String deptId = request.getParameter("deptId");
		String[] userId = {};
		if(userIds!=null){
			userId = userIds.split(",");
		}
		return deptService.delUserToDept(deptId, userId);
	}
	
}
