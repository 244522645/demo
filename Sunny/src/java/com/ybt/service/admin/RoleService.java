package com.ybt.service.admin;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.util.Page;
import com.ybt.model.admin.Dept;
import com.ybt.model.admin.Menu;
import com.ybt.model.admin.Role;
import com.ybt.model.admin.User;

@Component
public interface RoleService {
	
	/**
	 * 在指定角色下添加用户
	 * @param roleId 
	 * @param userId[] 
	 * @return boolean
	 */
	public boolean addUserToRole(String roleId,String[] userId);
	
	/**
	 * 在指定角色下添加菜单
	 * @param roleId 
	 * @param menuId[] 
	 * @return boolean
	 */
	public boolean addMenuToRole(String roleId,String[] menuId);
	
	/**
	 * 保存角色
	 * @param role
	 * @return int 
	 */
	public boolean saveRole(Role role);
	
	/**
	 * 根据角色id获取角色
	 * @param roleId
	 * @return Role
	 */
	public Role getRoleByRoleId(String roleId);
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return 
	 */
	public boolean delRole(String roleId);
	
	/**
	 * 根据parentId获取角色
	 * @param parentId
	 * @return List<Role>
	 */
	public List<Role> getRoleByParentId(String parentId);
	
	/**
	 * 根据roleId获取父部门
	 * @param roleId 
	 * @return List<Role>
	 */
	public List<Role> getParentRoleByRoleId(String roleId);
	
	
	/**
	 * 获取所有角色
	 * @param 
	 * @return List<Role>
	 */
	public List<Role> findAll();
	
	/**
	 * 在指定角色中添加用户
	 * @param roleId 角色id, users  要添加的用户
	 * @return boolean
	 */
	public boolean addUsers(String roleId,List<User> users);
	
	/**
	 * 在指定角色中添加部门
	 * @param roleId 角色id, depts  要添加的部门
	 * @return boolean
	 */
	public boolean addDepts(String roleId,List<Dept> depts);
	
	/**
	 * 在指定角色下添加菜单
	 * @param roleId 角色ID,Menus  要添加的菜单
	 * @return boolean
	 */
	public boolean addMenus(String roleId,List<Menu> Menus);
	
	/**
	 * 获取指定角色下的用户
	 * @param roleId 角色ID
	 * @return List<User> 
	 */
	public List<User> findUserByRoleId(String roleId);
	
	
	/**
	 * 获取指定角色下的部门
	 * @param roleId 角色ID
	 * @return List<Dept>
	 */
	public List<Dept> findDeptByRoleId(String roleId);
	
	
	/**
	 * 获取指定角色下的菜单
	 * @param roleId 角色ID
	 * @return List<Menu>
	 */
	public List<Menu> findMenuByRoleId(String roleId);
	
	/**
	 * 删除指定角色下的用户
	 * @param roleId 角色ID,userId  要删除的用户id
	 * @return boolean
	 */
	public boolean relieveUserByUserId(String roleId,String userId);
	
	/**
	 * 删除角色下的部门
	 * @param roleId 角色ID,deptId  要删除的部门id
	 * @return boolean
	 */
	public boolean relieveDeptByDeptId(String roleId,String deptId);
	
	/**
	 * 删除角色下的菜单
	 * @param roleId 角色ID,menuId  要删除的菜单id
	 * @return boolean
	 */
	public boolean relieveDeptByMenuId(String roleId,String menuId);
	
	
	/**
	 * 获取指定角色下的用户
	 * @param roleId,pager
	 * @return Page<User>
	 */
	public Page<User> findUserByRoleId(String roleId,Page<User> page);
	
	
}
