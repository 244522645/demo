package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunGlStatisticSql;

public interface StatisticSqlDao extends BaseDao<SunGlStatisticSql, String> {
	
	 @Query("select a from SunGlStatisticSql a  where a.deleted != 1 order by a.title")
	List<SunGlStatisticSql> getAllStatisticSql();
	 
	 /**
	 *@description 通过sqlCode  查询 sql
	 *@time    创建时间:2016年5月4日上午11:56:03
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Query("select a from SunGlStatisticSql a  where a.deleted != 1 and a.sqlCode=?")
	 public SunGlStatisticSql getSqlBySqlCode(String code);
	
}
