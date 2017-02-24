package com.ybt.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.admin.Menu;

@Component
public interface MenuDao  extends BaseDao<Menu, String> {
	/**
	 * 根据parentId获取菜单
	 * @param parentId
	 * @return List<Menu>
	 */
	@Query("select a from Menu a where a.parentId = ? and a.isdisplay = 0 order by sortnum")
	 List<Menu> findByParentId(String parentId);
	
	
	/**
	 * 根据parentId获取菜单
	 * @param parentId
	 * @return List<Menu>
	 */
	@Query("select a from Menu a where a.menuId = ? and a.isdisplay = 0")
	 Menu findByMenuId(String menuId);
	
	
	/**
	 * 获取所有菜单
	 * @param 
	 * @return List<Menu>
	 */
	@Query("select a from Menu a where a.isdisplay = 0 order by sortnum")
	 List<Menu> findAll();
	
	 
	 
	 /**
	 * 获取指定角色下的指定级别的菜单
	 * @param roleId,parentId
	 * @return List<Menu>
	 */
	 @Query("select a from Menu a where a.parentId = ? and a.isdisplay=0 and a.menuId in (select r.menuId from MenuRole r where r.roleId= ? ) order by sortnum")
	public List<Menu> findMenuByRoleIdAndParentId(String parentId,String roleId);
	 
	 /**
	 * 获取指定角色下的菜单
	 * @param roleId
	 * @return List<Menu>
	 */
	 @Query("select a from Menu a where  a.menuId in (select r.menuId from MenuRole r where r.roleId= ? ) order by sortnum")
	public List<Menu> findMenuByRoleId(String roleId);
	 
	 /**
	 * 获取指定用户有权限查看的菜单
	 * @param roleId,parentId 菜单级别
	 * @return List<Menu>
	 */
	 @Query(" select a from Menu a where a.parentId = ?  and a.isdisplay=0 and a.menuId in (select r.menuId from MenuRole r where r.roleId in (select ur.roleId from UserRole ur where ur.userId = ?)) order by sortnum")
	public List<Menu> findMenuOnUser(String parentId,String userId);
}
