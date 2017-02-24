package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.UserError;

public interface UserErrorMapper {
	int deleteByPrimaryKey(Integer errorid);

	int insert(UserError record);

	int insertSelective(UserError record);

	UserError selectByPrimaryKey(Integer errorid);

	int updateByPrimaryKeySelective(UserError record);

	int updateByPrimaryKey(UserError record);

	/*
	 * �����û���id�ʹ������Ͳ�ѯ��ǰ�û�����ĵ�¼����
	 */
	List<UserError> findByID(String userID, String errorType, String time);

	/*
	 * �����û����ֻ��ţ�ɾ��ָ�����͵Ĵ���
	 */
	int deleteByUsername(String userID, Integer errorType);

	/*
	 * ��ѯ�û���ǰ�������
	 */
	int countByerror(String erroruserid, String errortype, String errortime);

	/*
	 * ��ѯ��ǰ�û���ǰ��������������
	 */
	int countByCard(@Param("userID") String userID, @Param("errorType") String errorType, @Param("time") String time,
			@Param("errornum") String errornum);

	/*
	 * �û���¼�ɹ�����޸Ĵ���ĵ�½��¼��Ϊ����
	 */
	int updateUserError(String startTime, String endTime, String userID);

}