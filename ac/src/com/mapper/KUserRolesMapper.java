package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.KUserRightModel;
import com.model.KUserRoles;

public interface KUserRolesMapper extends BaseMapper{

	/*
	 * 查询该用户的菜单
	 */
	public List<KUserRightModel> findbyUser(@Param("roleID")String roleID);
	/*
	 * 查询所有角色
	 * 
	 */
	public List<KUserRoles> findRoleBy(@Param("pageSize")int pageSize,@Param("page")int page);
	/*
	 * 统计总数有多少
	 */
	public Integer countRole();
	/*
	 * 添加角色
	 */
	public void addRole(@Param("roleKind")String roleKind,@Param("roleName")String roleName,@Param("roleDescription")String  roleDescription,@Param("date")String dateString);
	/*
	 * 
	 * 修改角色
	 */
	public void updateRole(@Param("roleId")String roleId,@Param("roleKind")String roleKind, @Param("roleName")String roleName,@Param("roleDescription")String roleDescription);

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
	public void deleterights(@Param("roleid")String roleid,@Param("rightsid")String rightsid);
	/*
	 * 根据角色id添加角色下面的权限
	 * 
	 */
	public void addrights(@Param("roleid")String roleid,@Param("rightsid")String rightsid);

	/*
	 * 查询角色下面有没有要跳转的路径的权限
	 * 
	 */
	public Integer returnurl(@Param("userid")String userid,@Param("url")String url);
}
