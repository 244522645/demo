package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.KCardDeadline;

public interface KCardDeadlineMapper extends BaseMapper {
	// ��ѯ��ǰ�û�����Ŀ�
	public List<Map<String, String>> findByCardOrderByNum(String startCardNums, String endCardNum, String cardKingsId);

	// ������ڼ�¼
	public void addAfterCard(KCardDeadline cardDeadline);
	/*
	 * ��ѯ�û����ڼ�¼
	 */
	public List<Map<String, String>> findDeadlineByPage(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("page")int page,@Param("rows") int rows,@Param("userType")int userType);
	/*
	 * ͳ���û����ڼ�¼��
	 * 
	 */
	public Integer countDeadlineByPage(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("userType")int userType);
}
