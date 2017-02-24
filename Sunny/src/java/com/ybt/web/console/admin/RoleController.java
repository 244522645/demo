package com.ybt.web.console.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.util.AjaxPageUtil;
import com.ybt.common.util.Page;
import com.ybt.model.admin.Menu;
import com.ybt.model.admin.Role;
import com.ybt.model.admin.User;
import com.ybt.service.admin.MenuService;
import com.ybt.service.admin.RoleService;

/**
 * 角色管理处理类，包括角色中添加 用户  部门  菜单，
 * 
 */
@Controller
@RequestMapping(value = "/console/admin/role")
public class RoleController {
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
	
	
	@RequestMapping
	public String defaultView(Model model,HttpServletRequest request){
		return "/console/admin/role/roleList";
	}
	
	//获取角色json
	@RequestMapping(value = "/jsonRole")
	@ResponseBody
	public List<Map<String,String>> jsonDept(Model model,HttpServletRequest request){
		List<Role> list = roleService.findAll();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		
		for(Role role:list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("id",role.getRoleId());
			map.put("pId",role.getParentId());
			map.put("name",role.getName()+"("+role.getCode()+")");
			if("-1".equals(role.getParentId())){
				map.put("name",role.getName());
				map.put("open","true");
			}
			mapList.add(map);
		}
		return mapList;
	}
	
	//保存角色
	@RequestMapping(value = "/jsonSaveRole",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String jsonSaveDept(Role role,HttpServletRequest request){
		Boolean msg = roleService.saveRole(role);
		if(msg)
			return "操作成功";
		return "操作失败";
	}
	
	//获取一个角色
	@RequestMapping(value = "/jsonGetRole")
	@ResponseBody
	public Role jsonGetDept(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		Role role = roleService.getRoleByRoleId(roleId);
		if(role!=null){
			List<Role> roles = roleService.getParentRoleByRoleId(role.getRoleId());
			if(roles!=null&&!roles.isEmpty()){
				role.setParentName(roles.get(0).getName());
			}
		}
		return role;
	}
	
	//删除角色
	@RequestMapping(value = "/jsonDelRole",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String jsonDelRole(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		Page<User> pageuser = new Page<User>();
		pageuser = roleService.findUserByRoleId(roleId, pageuser);
		if(pageuser!=null&&pageuser.getResult().size()>0){
			return "该角色有用户，请先删除用户再删除该角色！";
		}
		
		Page<Menu> page = new Page<Menu>();
		page = menuService.findMenuByRoleId(roleId, page);
		if(page!=null&&page.getResult().size()>0){
			return "该角色有菜单，请先删除菜单再删除该角色！";
		}
		
		Boolean flag = roleService.delRole(roleId);
		if(flag==true){
			return "true";
		}else
			return "false";
	}
	
	//添加用户
	@RequestMapping(value = "/jsonAddUser")
	@ResponseBody
	public boolean jsonAddUser(HttpServletRequest request){
		String userIds = request.getParameter("userIds");
		String roleId = request.getParameter("roleId");
		String[] userId = {};
		if(userIds!=null){
			userId = userIds.split(",");
		}
		return roleService.addUserToRole(roleId, userId);
	}
	
	//获取用户列表
	@RequestMapping(value = "/jsonUser")
	@ResponseBody
	public com.ybt.common.util.Page<User> jsonUser(HttpServletRequest request){
		Page<User> page = new Page<User>(AjaxPageUtil.PAGE_SIZE);
		AjaxPageUtil.init(page, request);
		String roleId = request.getParameter("roleId");
		return  roleService.findUserByRoleId(roleId,page);
	}
	
	
	//删除用户
	@RequestMapping(value = "/jsonDelUser")
	@ResponseBody
	public Boolean jsonDelUser(HttpServletRequest request){
		String userId = request.getParameter("userId");
		String roleId = request.getParameter("roleId");
		Boolean flag =  roleService.relieveUserByUserId(roleId, userId);
		if(flag)
			return true;
		else
			return false;
	}
	
	//获取菜单列表
	@RequestMapping(value = "/jsonMenu")
	@ResponseBody
	public com.ybt.common.util.Page<Menu> jsonMenu(HttpServletRequest request){
		Page<Menu> page = new Page<Menu>(AjaxPageUtil.PAGE_SIZE);
		AjaxPageUtil.init(page, request);
		String roleId = request.getParameter("roleId");
		return  menuService.findMenuByRoleId(roleId, page);
	}
	
	//添加菜单
	@RequestMapping(value = "/jsonAddMenu")
	@ResponseBody
	public boolean jsonAddMenu(HttpServletRequest request){
		String menuIds = request.getParameter("menuIds");
		String roleId = request.getParameter("roleId");
		String[] menuId = {};
		if(menuIds!=null){
			menuId = menuIds.split(",");
		}
		return roleService.addMenuToRole(roleId, menuId);
	}
	
	//删除菜单
	@RequestMapping(value = "/jsonDelMenu")
	@ResponseBody
	public Boolean jsonDelMenu(HttpServletRequest request){
		String menuId = request.getParameter("menuId");
		String roleId = request.getParameter("roleId");
		Boolean flag =  roleService.relieveDeptByMenuId(roleId, menuId);
		if(flag)
			return true;
		else
			return false;
	}
}
