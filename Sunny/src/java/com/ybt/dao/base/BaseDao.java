package com.ybt.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ybt.common.exception.SqlIsNotFindException;

/**
 *
 * @project 云备胎
 * @package com.ybt.dao.base
 * @file BaseDao.java 创建时间:2016年5月9日下午3:42:28
 * @title  spring data jpa 基础扩展 dao
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @module 模块:  spring data jpa 基础扩展 dao
 * @author   AndyBao
 * @param <T>
 * @param <ID>
 * @version 2.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID> {

	/**
	 * 分页查询实体
	 * */
	public Page<T> findAll(Specification<T> spec,Pageable pageable);
	
	
	/**
	 * 物理删除方法
	 * @param id  主键 ,默认主键属性为 id 。否则报错
	 * @see 物理删除 默认字段为 deleted ，1 为删除  0为 失败。如果没有此字段调用该方法会报错
	 * */
	public void deletion(ID id);
	
	/**
	 * 获取全部实体
	 * */
	public List<T> findAll();
	
	 
//	-------------------------------------------------------------
	/**
	 *@description、通过名字查找sql
	 *@param hqlName
	 *@return
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:38:59
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public String loadSql(String hqlName) throws Exception;
	
	/**
	 *@description、 通过名字查找sql  isSql=fasle 时 Hql语句 
	 *@param hqlName
	 *@return
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:38:43
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public String loadHql(String hqlName) throws Exception;


	/**
	 *@description、hql语句转换成sql语句
	 *@param hql
	 *@return
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:38:20
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public String hql2sql(String hql) throws Exception ;

	/**
	 *@description、分页查询.
	 *@param page
	 *@param sqlName
	 *@param map
	 *@return
	 *@throws SqlIsNotFindException
	 *@time    创建时间:2016年5月9日下午3:38:05
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public com.ybt.common.util.Page<T> findByHqlName(com.ybt.common.util.Page<T>  page, String sqlName, Map<String, ?> map) throws Exception ;

	/**
	 *@description、分页查询.
	 *@param page
	 *@param sqlName
	 *@param map
	 *@return
	 *@throws SqlIsNotFindException
	 *@time    创建时间:2016年5月9日下午3:37:43
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public com.ybt.common.util.Page<T> findBySqlName(com.ybt.common.util.Page<T>  page, String sqlName, Map<String, ?> map) throws Exception ;

	/**
	 *@description、执行查询 sql文件中的sql语句，返回结果集
	 *@param sqlName
	 *@param map
	 *@return
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:37:34
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public List<?> findBySql4MapResult(String sqlName, Map<String, ?> map) throws Exception ;
	
	/**
	 *@description、执行查询 sql文件中的sql语句，返回结果集
	 *@param sqlName
	 *@param map
	 *@return
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:37:24
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public List<?> findBySqlName4MapResult(String sqlName, Map<String, ?> map) throws Exception ;
	
	/**
	 *@description、返回sql语句结果集的长度
	 *@param sqls
	 *@return
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:37:12
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public int getTotalBySql(String sqls) throws Exception ;

	/**
	 *@description、查询sql语句返回的数字
	 *@param sql
	 *@param map
	 *@return
	 *@time    创建时间:2016年5月9日下午3:37:02
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public Integer findIntegerBySql(String sql,Map<String,?> map);
	
	/**
	 *@description、查询sql语句返回的数字
	 *@param sqlName
	 *@param map
	 *@return
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:36:36
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public Integer findIntegerBySqlName(String sqlName,Map<String,?> map) throws Exception;

	/**
	 *@description、查询sql语句返回的数字
	 *@param sql
	 *@return 数字
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:36:24
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public Integer findIntegerBySql(String sql) throws Exception ;
	
	/**
	 *@description、执行查询 sql文件中的sql语句，返回结果集
	 *@param sqlName
	 *@param map
	 *@return 结果集
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:36:08
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public List<?> findBySqlName(String sqlName, Map<String, ?> map) throws Exception ;

	/**
	 *@description、执行查询 hql文件中的hql语句
	 *@param hqlName hql名称
	 *@param map   参数
	 *@return
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:35:44
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public List<?> findByHqlName(String hqlName, Map<String, ?> map) throws Exception ;

	
	/**
	 *@description、执行sql
	 *@param sql
	 *@return
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:35:27
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public int executeSql(StringBuffer sql) throws Exception ;

	/**
	 *@description、執行sql返回結果集
	 *@param sql
	 *@return
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:35:02
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public List<?> executeSql(String sql) throws Exception ;
	

	/**
	 *@description、
	 *@param sqlName
	 *@param param
	 *@return
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:34:44
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public T findEntityBySqlName(String sqlName, Map<String, Object> param) throws Exception ;

	/**
	 *@description、按SQL查询实体列表.
	 *@param sqlName
	 *@param param
	 *@return 实体列表
	 *@throws Exception
	 *@time    创建时间:2016年5月9日下午3:34:08
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public List<T> findEntityListBySqlName(String sqlName, Map<String, Object> param) throws Exception;

}
