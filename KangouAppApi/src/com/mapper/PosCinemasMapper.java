package com.mapper;

import java.util.List;
import java.util.Map;

public interface PosCinemasMapper {
	/*
	 * ��ѯ��ǰ�û�������ն˺�
	 */
	List<Map<String, String>> selectPosid(String id);
	/*
	 * �����û��������ˮ��
	 */
	int updateSerialnumber(String userid,String serialnumber);
	/*
	 *��ѯ9��ͷ���Ѽ�¼��id
	 */
	List<Map<String, String>> selectnineResule(String posid,String serialnumber,String cid);
	/*
	 *��ѯ����9��ͷ���Ѽ�¼��id
	 */
	List<Map<String, String>> selectnotnineResule(String posid,String serialnumber,String cid);
	/*
	 * �޸�9��ͷ��У����
	 */
	int uodatenineResule(String id,String baoxianResult);
	
	/*
	 * �޸Ĳ���9��ͷ��У����
	 */
	int uodatenotnineResule(String id,String baoxianResult);
}