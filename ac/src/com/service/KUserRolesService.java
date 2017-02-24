package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.KUserRightModel;
import com.model.KUserRoles;

@Service
public interface KUserRolesService {
	public List<KUserRightModel> findbyUser(String  roleID);
	public List<KUserRoles> findRoleBy(int pageSize,int page);
	public int countRole();
	public void addRole(String roleKind,String roleName,String  roleDescription,String dateString);
	public void updateRole(String roleId,String roleKind, String roleName, String roleDescription);
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
	public void deleterights(String roleid,String rightsid);
	/*
	 * ���ݽ�ɫid��ӽ�ɫ�����Ȩ��
	 * 
	 */
	public void addrights(String roleid,String rightsid);
	
	/*
	 * ��ѯ��ɫ������û��Ҫ��ת��·����Ȩ��
	 * 
	 */
	public Integer returnurl(String userid,String url);
}
