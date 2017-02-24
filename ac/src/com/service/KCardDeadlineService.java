package com.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.KCardDeadline;
import com.util.EasyUiDataGridJson;

public interface KCardDeadlineService {
	//��ѯ��ǰ�û�����Ŀ�
	public List<Map<String, String>> findByCardOrderByNum(String startCardNums,String endCardNum,String cardKingsId);
	//������ڼ�¼
	public void addAfterCard(KCardDeadline cardDeadline);
	/*
	 * �û����ڼ�¼��ѯ
	 * 
	 */
	public EasyUiDataGridJson<Map<String, String>> findDeadlineByPage(HttpServletRequest request,String startTime,String endTime,int page, int rows);
	/*
	 * �û����ڼ�¼�ӿ�
	 */
	public Map<String,String> addAfterCardInterFace(HttpServletRequest request);
	
}
