package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.KUserRanges;
import com.util.KUserRanges_util;

public interface UserCardMapper extends BaseMapper {
	// 分页查询用户卡
	public List<Map<String, String>> findByPage(@Param("page") int page, @Param("rows") int rows,
			@Param("userid") String userid, @Param("cardnum") String cardnum);

	// 统计用户卡
	public int countCard(@Param("userid") String userid, @Param("cardnum") String cardnum);

	// 添加用户卡
	public void addCard(KUserRanges kUserRanges);

	// 修改用户卡
	public void updateCard(KUserRanges_util kUserRanges);

	// 删除用户卡
	public void delCard(@Param("dellist") List<Integer> dellist);

	// 查询当前用户下面的卡
	public List<Map<String, String>> findByUser(String id);
	/*
	 * 查询当前用户下面的号段
	 */
	public List<KUserRanges> findRangsByUser(String id);
	/*
	 * 查询符合条件的用户卡的信息
	 */
	public List<KUserRanges> findByUserAndCard(String id,String startCard,String endCard);
	/*
	 * 查询该号段的所属人
	 */
	public List<KUserRanges> findByCard(String startCard,String endCard);
}
