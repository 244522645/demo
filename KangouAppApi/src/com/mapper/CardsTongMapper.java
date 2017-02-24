package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.CardsTong;

public interface CardsTongMapper {
	int deleteByPrimaryKey(String cardtnumber);

	int insert(CardsTong record);

	int insertSelective(CardsTong record);

	CardsTong selectByPrimaryKey(String cardtnumber);

	int updateByPrimaryKeySelective(CardsTong record);

	int updateByPrimaryKey(CardsTong record);
	/*
	 * ������
	 * ���ݿ��Ų��ҵ�ǰ����Ϣ
	 */
	List<CardsTong> selectBycardNum(String cardNum);
	/*
	 * ������
	 * ���ݿ��Ų�ѯ��ǰ���ĵ���
	 * 
	 */
	List<Map<String, String>> selectByCardprice(String parentid);
}