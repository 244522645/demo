package com.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.model.KCardOrders;
import com.util.EasyUiDataGridJson;

public interface KCardOrdersService {
	// �������
	public int addCardOrders(KCardOrders cardOrders, HttpServletRequest request, Integer cardOrdersType);

	/*
	 * ��ѯ�û�����Ŀ�����¼
	 */
	public List<KCardOrders> findByUserCardOrders(String cardKingsId, String startCardNum, String endCardNum);

	/*
	 * ��ѯ�úŶ���û���˿���¼
	 */
	public List<KCardOrders> findByQuitCardorders(String cardKingsId, String startCardNum, String endCardNum);

	/*
	 * ����˿���
	 */
	public int addQuitCardOrders(KCardOrders cardOrders, HttpServletRequest request);

	/*
	 * �Ѿ��������˿���
	 */
	public int addOpenCardOrders(KCardOrders cardOrders, HttpServletRequest request);
	/*
	 * ��ѯ�û����Ѽ�¼
	 */
	public EasyUiDataGridJson<Map<String, String>> findOrdersByUser(HttpServletRequest request,String startTime,String endTime,int page, int rows,int userType);
	/*
	 * �û����������ѽӿ�
	 */
	public Map<String, String> openCardInterface(HttpServletRequest request,String cardType);
	/*
	 * �û��˿��ӿ�
	 * 
	 */
	public Map<String, String> quitCardInterface(HttpServletRequest request);
}
