package com.ybt.service.admin.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ybt.dao.admin.DeptDao;
import com.ybt.dao.admin.UserDao;
import com.ybt.model.admin.Dept;
import com.ybt.model.admin.User;
import com.ybt.service.admin.DeptService;


@Component
public class DeptServiceImpl implements DeptService{

	@Autowired
	private DeptDao deptDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	
	public boolean delDeptByDeptId(String deptId) {
		try{
			Dept dept = deptDao.getDeptByDeptId(deptId);
			dept.setDeleted("1");
			deptDao.save(dept);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Dept> findAllDept() {
		
		return deptDao.findAllDept();
	}

	public List<Dept> findDeptByParentId(String parentId) {
		
		return deptDao.findByParentId(parentId);
	}

	public Dept getDeptByDeptId(String deptId) {
		return deptDao.getDeptByDeptId(deptId);
	}

	public boolean saveDept(Dept dept) {
		try{
			dept.setDeleted("0");
			deptDao.save(dept);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Dept> getParentDeptByDeptId(String deptId) {
		return deptDao.getParentDeptByDeptId(deptId);
	}

	public List<Map<String, String>> getTreeDateToDeptUser() {
		EntityManager em = entityManagerFactory.createEntityManager();
		String sql = "select deptid,name,parentid,'dept'  from ybt_web_dept where deleted='0'  UNION ALL select userid,name,deptid,'user'  from ybt_web_user where deleted='0'";
		Query query =  em.createNativeQuery(sql);
		List<?> roleObjects = query.getResultList();
		List<Map<String, String>> roleList = new ArrayList<Map<String, String>>();
		if(roleObjects!=null&&!roleObjects.isEmpty()){
			for(int i=0;i<roleObjects.size();i++) {
				 Object[] obj = (Object[]) roleObjects.get(i);
				 Map<String, String> map = new HashMap<String, String>();
				 map.put("id",obj[0]==null?"":obj[0].toString());
				 map.put("name",obj[1]==null?"":obj[1].toString());
				 map.put("pId",obj[2]==null?"":obj[2].toString());
				 if("-1".equals(obj[2]==null?"":obj[2].toString())){
					map.put("open","true");
				 }
				 if("dept".equals(obj[3])){
					 map.put("nocheck","true");
				 }else  if("user".equals(obj[3])){
					 map.put("nocheck","false");
				 }
				 roleList.add(map);
			}
		}
		em.close();
		return roleList;
	}
	
	@Transactional
	public boolean addUserToDept(String deptId, String[] userId) {
		try{
			for(int i=0;i<userId.length;i++){
				userDao.addUserToDept(deptId, userId[i]);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public boolean delUserToDept(String deptId, String[] userId) {
		try{
			for(int i=0;i<userId.length;i++){
				userDao.delUserToDept(userId[i]);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public List<User> findUserByDeptId(String deptId) {
		return userDao.getUserByDeptId(deptId);
	}

}
