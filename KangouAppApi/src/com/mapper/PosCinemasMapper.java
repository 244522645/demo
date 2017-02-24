package com.mapper;

import java.util.List;
import java.util.Map;

public interface PosCinemasMapper {
	/*
	 * 查询当前用户下面的终端号
	 */
	List<Map<String, String>> selectPosid(String id);
	/*
	 * 更新用户下面的流水号
	 */
	int updateSerialnumber(String userid,String serialnumber);
	/*
	 *查询9开头消费记录的id
	 */
	List<Map<String, String>> selectnineResule(String posid,String serialnumber,String cid);
	/*
	 *查询不是9开头消费记录的id
	 */
	List<Map<String, String>> selectnotnineResule(String posid,String serialnumber,String cid);
	/*
	 * 修改9开头的校验码
	 */
	int uodatenineResule(String id,String baoxianResult);
	
	/*
	 * 修改不是9开头的校验码
	 */
	int uodatenotnineResule(String id,String baoxianResult);
}