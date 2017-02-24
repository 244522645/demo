package com.ybt.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.admin.Role;

public interface RoleDao extends BaseDao<Role, String> {

	/**
	 * 根据parentId获取角色
	 * @param parentId  
	 * @return List<Role>
	 */
	 @Query("select a from Role a where a.parentId = ? ")
	 public List<Role> getRoleByParentId(String parentId);
	 
	 /**
	 * 根据deptID查询父部门
	 * @param roleId  
	 * @return List<Role>
	 */
	 @Query("select a from Role a where a.roleId = (select c.parentId from Role c where c.roleId = ? ) ")
	 public List<Role> getParentRoleByRoleId(String roleId);
	 
	 
}
