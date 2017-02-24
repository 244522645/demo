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
	 * 张卫恒
	 * 根据卡号查找当前卡信息
	 */
	List<CardsTong> selectBycardNum(String cardNum);
	/*
	 * 张卫恒
	 * 根据卡号查询当前卡的单价
	 * 
	 */
	List<Map<String, String>> selectByCardprice(String parentid);
}