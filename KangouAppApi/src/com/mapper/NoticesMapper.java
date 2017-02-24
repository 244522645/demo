package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.Notices;

public interface NoticesMapper {
	int deleteByPrimaryKey(Integer nid);

	int insert(Notices record);

	int insertSelective(Notices record);

	Notices selectByPrimaryKey(Integer nid);

	int updateByPrimaryKeySelective(Notices record);

	int updateByPrimaryKey(Notices record);

	List<Map<String, String>> selectNotReadByNotice(String userid, @Param("page") int page,
			@Param("pagesize") int pagesize);

	Notices findLast();
}