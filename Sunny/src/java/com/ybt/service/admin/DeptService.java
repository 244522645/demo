package com.ybt.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ybt.model.admin.Dept;
import com.ybt.model.admin.User;

@Component
public interface DeptService {
	/**
	 * 根据deptid获取部门
	 * @param deptId
	 * @return Dept
	 */
	public Dept getDeptByDeptId(String deptId);
	
	/**
	 * 获取所有部门
	 * @return List<Dept>
	 */
	public List<Dept> findAllDept();
	
	/**
	 * 根据父部门id获取部门
	 * @param eeptId
	 * @return List<Dept>
	 */
	public List<Dept> findDeptByParentId(String parentId);
	
	/**
	 * 根据deptId删除一个部门
	 * @param deptId 
	 * @return boolean
	 */
	public boolean delDeptByDeptId(String  deptId);
	
	/**
	 * 保存部门
	 * @param Dept 
	 * @return boolean
	 */
	public boolean saveDept(Dept dept);
	
	/**
	 * 根据deptId获取父部门
	 * @param deptId 
	 * @return List<Dept>
	 */
	public List<Dept> getParentDeptByDeptId(String deptId);
	
	
	/**
	 * 获取部门和用户的树结构数据
	 * @param  
	 * @return List<Map<String,String>>
	 */
	public List<Map<String,String>> getTreeDateToDeptUser();
	
	/**
	 * 在指定部门下添加用户
	 * @param deptId 
	 * @param userId[] 
	 * @return boolean
	 */
	public boolean addUserToDept(String deptId,String[] userId);
	
	/**
	 * 查询指定部门下的用户
	 * @param deptId 
	 * @param userId[] 
	 * @return boolean
	 */
	public List<User> findUserByDeptId(String deptId);
	
	/**
	 * 删除指定部门下的用户
	 * @param deptId 
	 * @param userId[] 
	 * @return boolean
	 */
	public boolean delUserToDept(String deptId,String[] userId);
}
