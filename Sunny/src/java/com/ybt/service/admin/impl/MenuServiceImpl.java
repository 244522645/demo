package com.ybt.service.admin.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.util.Page;
import com.ybt.dao.admin.MenuDao;
import com.ybt.model.admin.Menu;
import com.ybt.service.admin.MenuService;

@Component
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	/**
	 * 根据菜单id获取菜单
	 * @param id
	 * @return Menu
	 */
	public Menu getMenuByMenuId(String menuId){
		return menuDao.findByMenuId(menuId);
	}
	
	/**
	 * 获取所有菜单
	 * @return List<Menu>
	 */
	public List<Menu> findAll(){
		return (List<Menu>) menuDao.findAll();
	}
	
	/**
	 * 获取一级菜单
	 * @param parentId
	 * @return List<Menu>
	 */
	public List<Menu> findMenuByParentId(String parentId){
		return  menuDao.findByParentId( parentId);
	}

	public List<Menu> findMenuByRoleIdAndParentId(String roleId, String parentId) {
		return menuDao.findMenuByRoleIdAndParentId(parentId, roleId);
	}

	public List<Menu> findMenuOnUser(String userId, String parentId) {
		return  menuDao.findMenuOnUser(parentId, userId);
	}

	public boolean saveMenu(Menu menu) {
		try {
			menuDao.save(menu);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delMenu(String menuId) {
		try {
			menuDao.delete(menuId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Menu findOne(String menuId) {
		return menuDao.findOne(menuId);
	}

	@SuppressWarnings("unchecked")
	public Page<Menu>  findMenuByRoleId(String roleId,Page<Menu>  page) {
		String sql ="select a from Menu a where  a.menuId in (select r.menuId from MenuRole r where r.roleId=:roleId )";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("roleId",roleId);
		page.setTotalCount(query.getResultList()!=null?query.getResultList().size():0);
		
		query.setFirstResult(page.getFirst()-1).setMaxResults(page.getPageSize());
		page.setResult(query.getResultList());
		em.close();
		return page;
	}

}
