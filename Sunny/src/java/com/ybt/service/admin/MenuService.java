package com.ybt.service.admin;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.util.Page;
import com.ybt.model.admin.Menu;

@Component
public interface MenuService {
	

	/**
	 * 根据菜单id获取菜单
	 * @param id
	 * @return Menu
	 */
	public Menu findOne(String menuId);
	/**
	 * 根据菜单id获取菜单,返回显示的菜单
	 * @param id
	 * @return Menu
	 */
	public Menu getMenuByMenuId(String menuId);
	
	/**
	 * 获取所有菜单
	 * @return List<Menu>
	 */
	public List<Menu> findAll();
	
	/**
	 * 根据父菜单id获取菜单
	 * @param parentId
	 * @return List<Menu>
	 */
	public List<Menu> findMenuByParentId(String parentId);
	
	
	/**
	 * 获取指定角色下的指定级别的菜单
	 * @param roleId,parentId
	 * @return List<Menu>
	 */
	public List<Menu> findMenuByRoleIdAndParentId(String roleId,String parentId);
	
	
	 /**
	 * 获取指定角色下的菜单
	 * @param roleId
	 * @return List<Menu>
	 */
	public Page<Menu>  findMenuByRoleId(String roleId,Page<Menu> page);
	 
	/**
	 * 获取指定用户有权限查看的菜单
	 * @param roleId,parentId 菜单级别
	 * @return List<Menu>
	 */
	public List<Menu> findMenuOnUser(String userId,String parentId);
	
	/**
	 * 保存菜单
	 * @param menu
	 * @return boolean
	 */
	public boolean saveMenu(Menu menu);
	
	/**
	 * 根据MenuId删除菜单
	 * @param menuId
	 * @return boolean
	 */
	public boolean delMenu(String menuId);
}
