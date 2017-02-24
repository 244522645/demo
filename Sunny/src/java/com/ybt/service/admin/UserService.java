package com.ybt.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ybt.common.util.PropertyFilter;
import com.ybt.model.admin.Role;
import com.ybt.model.admin.User;


@Component
public interface UserService {
	
	/**
	 * 根据userid获取用户
	 * @param userId
	 * @return User
	 */
	public User getUserByUserId(String userId);
	
	/**
	 * 根据用户属性值获取用户
	 * @return User
	 */
	public Page<User> findUserByProperty(List<PropertyFilter> filters,Pageable pageable);
	
	
	/**
	 * 新增一个用户
	 * @param user  新增的用户
	 * @return boolean
	 */
	public boolean addUser(User user);
	
	/**
	 * 批量新增用户
	 * @param user[]  新增的用户
	 * @return boolean
	 */
	public boolean batchAddUser(List<User> user);
	
	/**
	 * 修改一个用户
	 * @param user  修改的用户
	 * @return User 修改之后的用户
	 */
	public User modifyUser(User user);
	/**
	 * 通过账户密码
	 * @param user  用户名 密码
	 * @return User 用户
	 */
	public User getUserByUserIdAndPassword(String userId,String password);
	
	
	/**
	 * 删除一个用户
	 * @param user  删除的用户
	 * @return boolean
	 */
	public boolean delUser(User user);
	
	
	/**
	 * 根据userId删除一个用户
	 * @param user  删除的用户
	 * @return boolean
	 */
	public boolean delUserByUserId(String  userId);
	
	/**
	 * 根据userId批量删除用户
	 * @param userId[]  userId集合
	 * @return boolean
	 */
	public boolean batchDelUser(List<String>  userId);
	
	
	/**
	 * 根据userId获取用户角色
	 * @param userId[]  userId集合
	 * @return boolean
	 */
	public List<Role> getRoleByUserId(String userId);
	
	
}
