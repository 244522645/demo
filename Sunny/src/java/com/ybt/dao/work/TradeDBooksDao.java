package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunZhDaybook;

@Component
public interface TradeDBooksDao extends BaseDao<SunZhDaybook, String> {

	
	/**
	* 根据ping id和交易类型(0转入,1转出) 获取流水记录
	* @return YbtTradeBRecord
	*/
	@Query("select a from SunZhDaybook a where a.pingppId = ? and a.type=? and a.deleted=0")
	SunZhDaybook getBookByPidAndType(String pingPPId, String string);
	
	/**
	 * 查询芸备胎流水
	 */
	@Query("select a from SunZhDaybook a where a.type in ('3','4') and a.deleted=0")
	List<SunZhDaybook> queryDaybook();
	/**
	* 根据交易类型， 获取流水记录总数
	*/
	@Query("select count(*) from SunZhDaybook a where a.type in ('3','4') and a.deleted=0")
	long queryDaybookCount();
	
}
