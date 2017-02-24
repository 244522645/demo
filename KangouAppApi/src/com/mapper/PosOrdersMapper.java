package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PosOrdersMapper {
	List<Map<String, String>> fingByTime(@Param("starttime") String starttime, @Param("endtime") String endtime,
			@Param("page") String page, @Param("pagesize") String pagesize, @Param("parentID") String parentID);

	public List<Map<String, String>> countBytime(@Param("starttime") String starttime, @Param("endtime") String endtime,
			@Param("parentID") String parentID);

	public List<Map<String,String>> findByserialNum(String serialNum,String id,String posid);
	public List<Map<String, String>> findByTimeAndPhone(String serialNum,String id,String posid,String time,String phone);
}