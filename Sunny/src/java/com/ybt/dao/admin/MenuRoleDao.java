package com.ybt.dao.admin;

import java.util.List;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.admin.MenuRole;

public interface MenuRoleDao extends BaseDao<MenuRole, String> {

	 /**
	 * 根据roleId查询MenuRole
	 * @param userId  
	 * @return List<MenuRole>
	 */
	 public List<MenuRole> findByRoleId(String roleId);
	 
	 /**
	 * 根据roleId查询MenuRole
	 * @param menuId  
	 * @return MenuRole
	 */
	 public List<MenuRole> findByMenuIdAndRoleId(String menuId,String roleId);
	 
}
