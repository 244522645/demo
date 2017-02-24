
package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.UserCardMapper;
import com.model.KUserRanges;
import com.service.UserCardService;
import com.util.KUserRanges_util;
@Service
public class UserCardServiceImpl implements UserCardService{
	@Autowired(required=false)
	private UserCardMapper cardMapper;
	
	
	//��ҳ��ѯ�û���
	public List<Map<String,String>> findByPage(int page, int rows, String userid, String cardnum) {
		return cardMapper.findByPage(page, rows, userid, cardnum);
		
		 
	}
	
	//ͳ���û���
	public int countCard(String userid, String cardnum) {
		return cardMapper.countCard(userid, cardnum);
	}
	
	//����û���
	public void addCard(KUserRanges kUserRanges) {
		System.out.println(1111);
		cardMapper.addCard(kUserRanges);
	}

	//�޸��û���
	public void updateCard(KUserRanges_util kUserRanges) {
	
		if((kUserRanges.getUserRangeStatus()).equals("true")){
		
			kUserRanges.setUserRangeStatus("1");
		}
		if((kUserRanges.getUserRangeStatus()).equals("false")){
		
			kUserRanges.setUserRangeStatus("0");
		}
		cardMapper.updateCard(kUserRanges);
		System.gc();
	}

	// ɾ���û���
	public void delCard(List<Integer> dellist) {
		cardMapper.delCard(dellist);
	}

	//��ѯ��ǰ�û�����Ŀ�
	public List<Map<String, String>> findByUser(String id) {
		
		return cardMapper.findByUser(id);
	}

	@Override
	public List<KUserRanges> findRangsByUser(String id) {
		return cardMapper.findRangsByUser(id);
		
	}
	
}
