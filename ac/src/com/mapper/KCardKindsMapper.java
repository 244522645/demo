package com.mapper;

import java.util.List;

import com.model.KCardKinds;

public interface KCardKindsMapper extends BaseMapper {
	//��ѯ���еĿ�
	public List<KCardKinds> findall();
	//���ݿ�id��ѯ���ĵ���
	public KCardKinds findKingsByid(int id);
	/*
	 * ���ݿ������ֲ�ѯ����ID
	 */
	public KCardKinds fingByname(String cardName);
}
