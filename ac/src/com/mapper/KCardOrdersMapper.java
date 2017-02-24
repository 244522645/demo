package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.KCardOrders;

public interface KCardOrdersMapper extends BaseMapper {
	// �������
	public void addCardOrders(KCardOrders cardOrders);
	/*
	 * ��ѯ�û�����Ŀ�����¼
	 */
	public List<KCardOrders> findByUserCardOrders(String cardKingsId,String startCardNum,String endCardNum);
	/*
	 * ��ѯ�úŶ���û���˿���¼
	 */
	public List<KCardOrders> findByQuitCardorders(String cardKingsId,String startCardNum,String endCardNum,int cardOrdersType);
	/*
	 * ����˿���
	 */
	public void addQuitCardOrders(KCardOrders cardOrderss);
	/*
	 * ���ݿ�ʼ�ͽ����Ŷβ�ѯ�û��Ŀ�����¼������û�м�¼
	 */
	public List<KCardOrders> findOrdersByCardNum(String startCardNum,String endCardNum,int cardOrdersType);
	/*
	 * ��ѯ�û����Ѽ�¼
	 */
	public List<Map<String, String>> findOrdersByUser(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("page")int page,@Param("rows") int rows,@Param("userType")int userType);
	/*
	 * ͳ���û����Ѽ�¼��
	 * 
	 */
	public Integer countOrdersByUser(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("userType")int userType);
}
