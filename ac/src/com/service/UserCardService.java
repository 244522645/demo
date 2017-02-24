package com.service;

import java.util.List;
import java.util.Map;

import com.model.KUserRanges;
import com.util.KUserRanges_util;

public interface UserCardService {

	//��ҳ��ѯ�û���
	public List<Map<String,String>> findByPage(int page,int rows,String userid,String cardnum);
	//ͳ���û���
	public int countCard(String userid,String cardnum);
	//����û���
	public void addCard(KUserRanges kUserRanges);
	//�޸��û���
	public void updateCard(KUserRanges_util kUserRanges);
	// ɾ���û���
	public void delCard(List<Integer> dellist);
	//��ѯ��ǰ�û�����Ŀ�
	public List<Map<String, String>> findByUser(String id);
	/*
	 * ��ѯ��ǰ�û�����ĺŶ�
	 */
	public List<KUserRanges> findRangsByUser( String id);
	
}
