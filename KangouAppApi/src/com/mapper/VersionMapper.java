package com.mapper;

import com.model.Version;

public interface VersionMapper {
	int deleteByPrimaryKey(Integer vid);

	int insert(Version record);

	int insertSelective(Version record);

	Version selectByPrimaryKey(Integer vid);

	int updateByPrimaryKeySelective(Version record);

	int updateByPrimaryKey(Version record);

	Version selsectByMobileType(String mobiletype);
}