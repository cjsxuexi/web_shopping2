package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.Role;

/**
 * 	��ɫDao
*/
public interface RoleDao {

	// ��ӽ�ɫ
	public void insert(Role role);
	
	// ��ȡ���еĽ�ɫ
	public List<Role> getRoles();
	
	// ���ݽ�ɫid��ѯ��ɫ
	public Role getRoleById(int roleId);
	
	// �����û���id��ѯ���û�����Щ��ɫ
	public List<Role> getRolesByUserId(int userId);
	
	// �����û���idɾ���û��Ľ�ɫ
	public void deleteRolesByUserId(int userId);
	
	// �����û���idΪ�û���ӽ�ɫ
	public void insertRoleByUserId(int userId, List<Role> roles);
}
