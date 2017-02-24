package com.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.KCardOrders;
import com.util.EasyUiDataGridJson;

public interface KCardOrdersService {
	// 开卡添加
	public int addCardOrders(KCardOrders cardOrders, HttpServletRequest request, Integer cardOrdersType);

	/*
	 * 查询用户下面的开卡记录
	 */
	public List<KCardOrders> findByUserCardOrders(String cardKingsId, String startCardNum, String endCardNum);

	/*
	 * 查询该号段有没有退卡记录
	 */
	public List<KCardOrders> findByQuitCardorders(String cardKingsId, String startCardNum, String endCardNum);

	/*
	 * 添加退卡单
	 */
	public int addQuitCardOrders(KCardOrders cardOrders, HttpServletRequest request);

	/*
	 * 已经开卡的退卡单
	 */
	public int addOpenCardOrders(KCardOrders cardOrders, HttpServletRequest request);
	/*
	 * 查询用户消费记录
	 */
	public EasyUiDataGridJson<Map<String, String>> findOrdersByUser(HttpServletRequest request,String startTime,String endTime,int page, int rows,int userType);
	/*
	 * 用户开卡和消费接口
	 */
	public Map<String, String> openCardInterface(HttpServletRequest request,String cardType);
	/*
	 * 用户退卡接口
	 * 
	 */
	public Map<String, String> quitCardInterface(HttpServletRequest request);
}
