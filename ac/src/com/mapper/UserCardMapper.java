package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.KUserRanges;
import com.util.KUserRanges_util;

public interface UserCardMapper extends BaseMapper {
	// ��ҳ��ѯ�û���
	public List<Map<String, String>> findByPage(@Param("page") int page, @Param("rows") int rows,
			@Param("userid") String userid, @Param("cardnum") String cardnum);

	// ͳ���û���
	public int countCard(@Param("userid") String userid, @Param("cardnum") String cardnum);

	// ����û���
	public void addCard(KUserRanges kUserRanges);

	// �޸��û���
	public void updateCard(KUserRanges_util kUserRanges);

	// ɾ���û���
	public void delCard(@Param("dellist") List<Integer> dellist);

	// ��ѯ��ǰ�û�����Ŀ�
	public List<Map<String, String>> findByUser(String id);
	/*
	 * ��ѯ��ǰ�û�����ĺŶ�
	 */
	public List<KUserRanges> findRangsByUser(String id);
	/*
	 * ��ѯ�����������û�������Ϣ
	 */
	public List<KUserRanges> findByUserAndCard(String id,String startCard,String endCard);
	/*
	 * ��ѯ�úŶε�������
	 */
	public List<KUserRanges> findByCard(String startCard,String endCard);
}
