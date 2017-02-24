package com.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.KCardDeadline;
import com.util.EasyUiDataGridJson;

public interface KCardDeadlineService {
	//查询当前用户下面的卡
	public List<Map<String, String>> findByCardOrderByNum(String startCardNums,String endCardNum,String cardKingsId);
	//添加延期记录
	public void addAfterCard(KCardDeadline cardDeadline);
	/*
	 * 用户延期记录查询
	 * 
	 */
	public EasyUiDataGridJson<Map<String, String>> findDeadlineByPage(HttpServletRequest request,String startTime,String endTime,int page, int rows);
	/*
	 * 用户延期记录接口
	 */
	public Map<String,String> addAfterCardInterFace(HttpServletRequest request);
	
}
