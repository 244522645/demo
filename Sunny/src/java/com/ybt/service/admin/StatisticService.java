package com.ybt.service.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import com.ybt.common.util.Page;
import com.ybt.model.work.SunGlStatisticSql;
import com.ybt.service.base.IBaseService;

@Component
public interface StatisticService extends IBaseService<SunGlStatisticSql, String>{

	/**
	 *@name    获取所有查询统计sql
	 *@description 相关说明
	 *@time    创建时间:2016年4月29日上午11:56:16
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	List<SunGlStatisticSql> getAllStatisticSql();

	/**
	 *@name    根据ID获取查询统计sql的信息
	 *@description 相关说明
	 *@time    创建时间:2016年4月29日上午11:56:40
	 *@param id
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	SunGlStatisticSql getStatisticSqlById(String id);
	
	/**
	 *@name    根据SQL_CODE获取查询统计sql的信息
	 *@description 相关说明
	 *@time    创建时间:2016年5月6日上午11:56:40
	 *@param id
	 *@return
	 *@author   包青友
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	SunGlStatisticSql getSqlBySqlCode(String code);
	
	/**
	 *@name    通过sqlName 查询报表
	 *@description 相关说明
	 *@time    创建时间:2016年5月4日上午11:56:40
	 *@return List<Map<String,Object>>
	 *@author   bao'qingyou包青友
	 * @throws Exception 
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public List<?> findBySqlName4MapResult(String sqlName,  Map<String, ?> map, Page<T> page,Map<String,String> header) throws Exception;
	
	/**
	 *@name    通过sqlName 查询报表
	 *@description 导出数据
	 *@time    创建时间:2016年5月4日上午11:56:40
	 *@return List<Map<String,Object>>
	 *@author   bao'qingyou包青友
	 * @throws Exception 
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public void exportExcel(String sqlName,  Map<String, ?> map, HttpServletResponse response) throws Exception;

}
