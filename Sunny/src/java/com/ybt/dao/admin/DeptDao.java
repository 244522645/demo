package com.ybt.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.admin.Dept;

@Component
public interface DeptDao extends BaseDao<Dept, String> {
	/**
	 * 获取所有部门
	 * @param 
	 * @return List<Dept>
	 */
	@Query("select a from Dept a where a.deleted = 0")
	 List<Dept> findAllDept();
	
	/**
	 * 根据parentId获取部门
	 * @param parentId
	 * @return List<Dept>
	 */
	@Query("select a from Dept a where a.parentId = ? and a.deleted = 0")
	 List<Dept> findByParentId(String parentId);
	
	/**
	 * 根据deptID查询Dept
	 * @param deptId  
	 * @return Dept
	 */
	 @Query("select a from Dept a where a.deptId = ? and a.deleted = 0")
	 public Dept getDeptByDeptId(String deptId);
	 
	 
	 /**
	 * 根据deptID查询父部门
	 * @param deptId  
	 * @return List<Dept>
	 */
	 @Query("select a from Dept a where a.deptId = (select c.parentId from Dept c where c.deptId = ? ) and a.deleted = 0")
	 public List<Dept> getParentDeptByDeptId(String deptId);
}
