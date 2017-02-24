package com.mapper;

import java.util.List;

import com.model.KCardKinds;

public interface KCardKindsMapper extends BaseMapper {
	//查询所有的卡
	public List<KCardKinds> findall();
	//根据卡id查询卡的单价
	public KCardKinds findKingsByid(int id);
	/*
	 * 根据卡的名字查询卡的ID
	 */
	public KCardKinds fingByname(String cardName);
}
