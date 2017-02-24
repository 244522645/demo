package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.KUser;

public interface KUserMapper  extends BaseMapper{
		public KUser findByName(@Param("username")String username);
		public void updatepassword(@Param("id")String id,@Param("password")String password);
		/*
		 * 为用户下拉框赋值
		 */
		public List<Map<String,String>> selectUser();
		/*
		 * 查询当前用户的信息
		 */
		public KUser findByUser(String string);
		/*
		 * 修改用户余额
		 */
		public void updateUser(KUser user);
 }
