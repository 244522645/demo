package com.ybt.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ybt.common.util.Page;
import com.ybt.dao.admin.MenuDao;
import com.ybt.dao.admin.MenuRoleDao;
import com.ybt.dao.admin.RoleDao;
import com.ybt.dao.admin.UserDao;
import com.ybt.dao.admin.UserRoleDao;
import com.ybt.model.admin.Dept;
import com.ybt.model.admin.Menu;
import com.ybt.model.admin.MenuRole;
import com.ybt.model.admin.Role;
import com.ybt.model.admin.User;
import com.ybt.model.admin.UserRole;
import com.ybt.service.admin.RoleService;

@Component
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private MenuRoleDao menuRoleDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	EntityManagerFactory entityManagerFactory;

	
	public boolean addDepts(String roleId, List<Dept> depts) {
		
		return false;
	}

	public boolean addUsers(String roleId, List<User> users) {
		try{
			List<UserRole> urList = new ArrayList<UserRole>();
			for(User user:users){
				UserRole ur = new UserRole();
				ur.setRoleId(roleId);
				ur.setUserId(user.getUserId());
				urList.add(ur);
			}
			userRoleDao.save(urList);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Role> findAll() {
		return (List<Role>) roleDao.findAll();
	}

	public List<Dept> findDeptByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findUserByRoleId(String roleId) {
		List<UserRole> urs = userRoleDao.findByRoleId(roleId);
		List<String> list = new ArrayList<String>();
		for(UserRole ur:urs){
			list.add(ur.getUserId());
		}
		return (List<User>) userDao.findAll(list);
	}

	public boolean relieveDeptByDeptId(String roleId, String deptId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean relieveUserByUserId(String roleId, String userId) {
		try{
			List<UserRole> ur = userRoleDao.findByUserIdAndRoleId(userId, roleId);
			userRoleDao.delete(ur);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addMenus(String roleId, List<Menu> Menus) {
		try{
			List<MenuRole> mrList = new ArrayList<MenuRole>();
			for(Menu menu:Menus){
				MenuRole mr = new MenuRole();
				mr.setRoleId(roleId);
				mr.setMenuId(menu.getMenuId());
			}
			menuRoleDao.save(mrList);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Menu> findMenuByRoleId(String roleId) {
		List<MenuRole> mrList = menuRoleDao.findByRoleId(roleId);
		List<Menu> menuList = new ArrayList<Menu>();
		for(MenuRole mr : mrList){
			Menu menu = menuDao.findByMenuId(mr.getMenuId());
			menuList.add(menu);
		}
		return menuList;
	}

	public boolean relieveDeptByMenuId(String roleId, String menuId) {
		try{
			List<MenuRole> ur = menuRoleDao.findByMenuIdAndRoleId(menuId, roleId);
			menuRoleDao.delete(ur);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delRole(String roleId) {
		try {
			roleDao.delete(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true; 
	}

	public List<Role> getRoleByParentId(String parentId) {
		return roleDao.getRoleByParentId(parentId);
	}

	public Role getRoleByRoleId(String roleId) {
		return roleDao.findOne(roleId);
	}

	public boolean saveRole(Role role) {
		try {
			roleDao.save(role);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true; 
	}

	
	public List<Role> getParentRoleByRoleId(String roleId) {
		return roleDao.getParentRoleByRoleId(roleId);
	}

	@Transactional
	public boolean addUserToRole(String roleId, String[] userId) {
		try{
			if(userId!=null&&userId.length>0){
				for(int i=0;i<userId.length;i++){
					List<UserRole> tur = userRoleDao.findByUserIdAndRoleId(userId[i], roleId);
					if(tur==null||tur.size()==0){
						UserRole ur = new UserRole();
						ur.setRoleId(roleId);
						ur.setUserId(userId[i]);
						userRoleDao.save(ur);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	public Page<User> findUserByRoleId(String roleId,Page<User> page) {
		EntityManager em = entityManagerFactory.createEntityManager();
		
		String sql = " select a from User a where a.userId in (select ur.userId from UserRole ur where ur.roleId = (select r.roleId from Role r where r.roleId=:roleId))";
		
		Query query = em.createQuery(sql);
		
		
		query.setParameter("roleId",roleId);
		
		page.setTotalCount(query.getResultList()!=null?query.getResultList().size():0);
		
		query.setFirstResult(page.getFirst()-1).setMaxResults(page.getPageSize());
		
		page.setResult(query.getResultList());
		em.close();
		return page;
	}

	
	public boolean addMenuToRole(String roleId, String[] menuId) {
		try{
			if(menuId!=null&&menuId.length>0){
				for(int i=0;i<menuId.length;i++){
					List<MenuRole> tmr = menuRoleDao.findByMenuIdAndRoleId(menuId[i], roleId);
					if(tmr==null||tmr.size()==0){
						MenuRole mr = new MenuRole();
						mr.setRoleId(roleId);
						mr.setMenuId(menuId[i]);
						menuRoleDao.save(mr);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
