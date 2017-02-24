package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.KUser;

public interface KUserMapper  extends BaseMapper{
		public KUser findByName(@Param("username")String username);
		public void updatepassword(@Param("id")String id,@Param("password")String password);
		/*
		 * Ϊ�û�������ֵ
		 */
		public List<Map<String,String>> selectUser();
		/*
		 * ��ѯ��ǰ�û�����Ϣ
		 */
		public KUser findByUser(String string);
		/*
		 * �޸��û����
		 */
		public void updateUser(KUser user);
 }
