package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.KUserRolesMapper;
import com.model.KUserRightModel;
import com.model.KUserRoles;
import com.service.KUserRolesService;

@Service
public class KUserRolesServiceImpl implements KUserRolesService{
	@Autowired(required=false)
	private KUserRolesMapper kUserRoleMapper;
	@Override
	public List<KUserRightModel> findbyUser(String roleID) {
	
		return kUserRoleMapper.findbyUser(roleID);
	}
	@Override
	public List<KUserRoles> findRoleBy(int pageSize, int page) {
		
		return kUserRoleMapper.findRoleBy(pageSize, page);
	}
	/*
	 * (non-Javadoc)
	 * @see com.service.KUserRolesService#countRole()
	 * 获取用户组表的个数
	 */
	@Override
	public int countRole() {
		
		return kUserRoleMapper.countRole();
	}
	/*
	 * zwh 
	 * 2016.08.26
	 * (non-Javadoc) 添加角色
	 * @see com.service.KUserRolesService#addRole(java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void addRole(String roleKind, String roleName, String roleDescription, String dateString) {
		kUserRoleMapper.addRole(roleKind, roleName, roleDescription,dateString);
	}
	/*
	 * zwh 
	 * 2016.08.26
	 * (non-Javadoc) 修改角色
	 * 
	 */
	public void updateRole(String roleId, String roleKind, String roleName, String roleDescription) {
		kUserRoleMapper.updateRole(roleId, roleKind, roleName, roleDescription);
	}
	/*
	 * 查询所有的角色权限
	 */
	public List<KUserRightModel> findAllRights() {
		return kUserRoleMapper.findAllRights();
	}
	/*
	 * 根据角色ID查询下面的权限
	 */
	public List<String> findUserRights(String roleId) {
		
		return kUserRoleMapper.findUserRights(roleId);
	}
	
	@Override
	public void deleterights(String roleid, String rightsid) {
		kUserRoleMapper.deleterights(roleid, rightsid);
		
	}
	@Override
	public void addrights(String roleid, String rightsid) {
		kUserRoleMapper.addrights(roleid, rightsid);
		
	}
	@Override
	public Integer returnurl(String userid,String url) {
		return kUserRoleMapper.returnurl(userid,url);
	}
	
}
