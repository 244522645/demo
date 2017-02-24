package com.ybt.dao.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.admin.Role;
import com.ybt.model.admin.User;

public interface UserDao extends BaseDao<User, String> {
	
	/**
	 * 根据userID查询user
	 * @param userId  
	 * @return Page<User>
	 */
	 @Query("select a from User a where a.userId = ? and a.deleted = 0 and a.disable = 0 ")
	 public User getUserByUserId(String userId);
	 /**
	 * 根据userID password查询user
	 * @param userId  password
	 * @return User
	 */
	 @Query("select a from User a where a.userId = ? and a.password=? and a.deleted = 0 and a.disable = 0 ")
	 public User getUserByUserIdAndPassword(String userId,String password);
	 
	 /**
	 * 根据deptId查询user
	 * @param deptId  
	 * @return List<User>
	 */
	 @Query("select a from User a where a.deptId = ? and a.deleted = 0 and a.disable = 0 ")
	 public List<User> getUserByDeptId(String deptId);
	 
	 /** 根据用户属性值获取用户
	 * @return User
	 */
	 public Page<User> findAll(Specification<User> spec, Pageable pageable);
	 
	 /**
	 * 在指定部门中添加用户
	 * @param deptId  
	 * @param userId  
	 * @return int
	 */
	 @Modifying 
	 @Query("update User a set a.deptId = ? where a.userId =? ")
	 public int addUserToDept(String deptId, String userId);
	 
	 /**
	 * 将指定人员从部门中删除
	 * @param userId  
	 * @return Page<User>
	 */
	 @Modifying 
	 @Query("update User a set a.deptId = '' where a.userId =? ")
	 public int delUserToDept(String userId);
	 
	 
	 
	 /**
	 * 获取指定人员的角色
	 * @param userId  
	 * @return Page<User>
	 */
	 @Query("select a from Role a where a.roleId in (select u.roleId from  UserRole u where  u.userId=? )")
	 public List<Role> getRoleByUserId(String userId);
	 
}
