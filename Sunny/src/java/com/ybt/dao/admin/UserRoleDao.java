package com.ybt.dao.admin;

import java.util.List;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.admin.UserRole;

public interface UserRoleDao extends BaseDao<UserRole, String> { 
	/**
	 * 根据roleId查询UserRole
	 * @param userId  
	 * @return List<UserRole>
	 */
	 public List<UserRole> findByRoleId(String roleId);
	 
	 /**
	 * 根据roleId查询UserRole
	 * @param userId  
	 * @return UserRole
	 */
	 public List<UserRole> findByUserIdAndRoleId(String userId,String roleId);
	 
}
