package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.KCardDeadline;

public interface KCardDeadlineMapper extends BaseMapper {
	// 查询当前用户下面的卡
	public List<Map<String, String>> findByCardOrderByNum(String startCardNums, String endCardNum, String cardKingsId);

	// 添加延期记录
	public void addAfterCard(KCardDeadline cardDeadline);
	/*
	 * 查询用户延期记录
	 */
	public List<Map<String, String>> findDeadlineByPage(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("page")int page,@Param("rows") int rows,@Param("userType")int userType);
	/*
	 * 统计用户延期记录数
	 * 
	 */
	public Integer countDeadlineByPage(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("userType")int userType);
}
