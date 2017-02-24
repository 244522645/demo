package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.UserInfo;

public interface UserInfoMapper {
	int deleteByPrimaryKey(String id);

	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(UserInfo record);
	
	int updateByPrimaryKey(UserInfo record);

	List<UserInfo> selectUserByPhone(@Param("phone")String phone,@Param("imsi")String imsi);

	void addUser(UserInfo userInfo);
	int updateVersion(UserInfo userInfo);
	List<Map<String,String>> findBycinema(String userid);
}