package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.KCardOrders;

public interface KCardOrdersMapper extends BaseMapper {
	// 开卡添加
	public void addCardOrders(KCardOrders cardOrders);
	/*
	 * 查询用户下面的开卡记录
	 */
	public List<KCardOrders> findByUserCardOrders(String cardKingsId,String startCardNum,String endCardNum);
	/*
	 * 查询该号段有没有退卡记录
	 */
	public List<KCardOrders> findByQuitCardorders(String cardKingsId,String startCardNum,String endCardNum,int cardOrdersType);
	/*
	 * 添加退卡单
	 */
	public void addQuitCardOrders(KCardOrders cardOrderss);
	/*
	 * 根据开始和结束号段查询用户的开卡记录里面有没有记录
	 */
	public List<KCardOrders> findOrdersByCardNum(String startCardNum,String endCardNum,int cardOrdersType);
	/*
	 * 查询用户消费记录
	 */
	public List<Map<String, String>> findOrdersByUser(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("page")int page,@Param("rows") int rows,@Param("userType")int userType);
	/*
	 * 统计用户消费记录数
	 * 
	 */
	public Integer countOrdersByUser(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("userType")int userType);
}
