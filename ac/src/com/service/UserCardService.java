package com.service;

import java.util.List;
import java.util.Map;

import com.model.KUserRanges;
import com.util.KUserRanges_util;

public interface UserCardService {

	//分页查询用户卡
	public List<Map<String,String>> findByPage(int page,int rows,String userid,String cardnum);
	//统计用户卡
	public int countCard(String userid,String cardnum);
	//添加用户卡
	public void addCard(KUserRanges kUserRanges);
	//修改用户卡
	public void updateCard(KUserRanges_util kUserRanges);
	// 删除用户卡
	public void delCard(List<Integer> dellist);
	//查询当前用户下面的卡
	public List<Map<String, String>> findByUser(String id);
	/*
	 * 查询当前用户下面的号段
	 */
	public List<KUserRanges> findRangsByUser( String id);
	
}
