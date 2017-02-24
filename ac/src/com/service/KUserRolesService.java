package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.KUserRightModel;
import com.model.KUserRoles;

@Service
public interface KUserRolesService {
	public List<KUserRightModel> findbyUser(String  roleID);
	public List<KUserRoles> findRoleBy(int pageSize,int page);
	public int countRole();
	public void addRole(String roleKind,String roleName,String  roleDescription,String dateString);
	public void updateRole(String roleId,String roleKind, String roleName, String roleDescription);
	/*
	 * 查询所有的角色权限
	 */
	public List<KUserRightModel> findAllRights();
	/*
	 * 根据角色ID查询下面的权限
	 */
	public List<String>  findUserRights(String roleId);
	/*
	 * 根据角色id删除角色下面的权限
	 * 
	 */
	public void deleterights(String roleid,String rightsid);
	/*
	 * 根据角色id添加角色下面的权限
	 * 
	 */
	public void addrights(String roleid,String rightsid);
	
	/*
	 * 查询角色下面有没有要跳转的路径的权限
	 * 
	 */
	public Integer returnurl(String userid,String url);
}
