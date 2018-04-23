package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.Role;

/**
 * ��ɫ����
 */
public interface RoleService {

	// ��ӽ�ɫ
	public void addRole(Role role);

	// ��ȡ���еĽ�ɫ
	public List<Role> queryRoles();

	// ���ݽ�ɫid��ѯ��ɫ
	public Role queryRoleById(int roleId);

	// �����û���id��ѯ���û�����Щ��ɫ
	public List<Role> queryRolesByUserId(int userId);
	
	// Ϊ��ɫ����Ȩ��,role��ʾ����Ϊ�ĸ����Ƿ���Ȩ�� , privileges��ʾ���Ƿ����Ȩ��
	public void dispatchPrivilegeForRole(Role role,String[] privilegeIds);

}
