package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.KUserRightModel;
import com.model.KUserRoles;

public interface KUserRolesMapper extends BaseMapper{

	/*
	 * ��ѯ���û��Ĳ˵�
	 */
	public List<KUserRightModel> findbyUser(@Param("roleID")String roleID);
	/*
	 * ��ѯ���н�ɫ
	 * 
	 */
	public List<KUserRoles> findRoleBy(@Param("pageSize")int pageSize,@Param("page")int page);
	/*
	 * ͳ�������ж���
	 */
	public Integer countRole();
	/*
	 * ��ӽ�ɫ
	 */
	public void addRole(@Param("roleKind")String roleKind,@Param("roleName")String roleName,@Param("roleDescription")String  roleDescription,@Param("date")String dateString);
	/*
	 * 
	 * �޸Ľ�ɫ
	 */
	public void updateRole(@Param("roleId")String roleId,@Param("roleKind")String roleKind, @Param("roleName")String roleName,@Param("roleDescription")String roleDescription);

	/*
	 * ��ѯ���еĽ�ɫȨ��
	 */
	public List<KUserRightModel> findAllRights();
	/*
	 * ���ݽ�ɫID��ѯ�����Ȩ��
	 */
	public List<String>  findUserRights(String roleId);
	/*
	 * ���ݽ�ɫidɾ����ɫ�����Ȩ��
	 * 
	 */
	public void deleterights(@Param("roleid")String roleid,@Param("rightsid")String rightsid);
	/*
	 * ���ݽ�ɫid��ӽ�ɫ�����Ȩ��
	 * 
	 */
	public void addrights(@Param("roleid")String roleid,@Param("rightsid")String rightsid);

	/*
	 * ��ѯ��ɫ������û��Ҫ��ת��·����Ȩ��
	 * 
	 */
	public Integer returnurl(@Param("userid")String userid,@Param("url")String url);
}
