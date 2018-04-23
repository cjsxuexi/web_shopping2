package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.Privilege;

/**
 * 	Ȩ��DAO
*/
public interface PrivilegeDao {

	// ��ѯϵͳ�����е�Ȩ��
	public List<Privilege> getPrivileges();
	
	// ���ݽ�ɫ��ѯ�˽�ɫ�������е�Ȩ��
	public List<Privilege> getPrivilegesByRoleId(int roleId);
	
	// ����Ȩ��
	public void insert(Privilege privilege);
	
	// ���ݽ�ɫ��idȥɾ�������ɫ��������е�Ȩ��
	public void deletePrivilegesByRoleId(int roleId);
	
	// ���ݽ�ɫ��id���Ȩ��
	public void insertPrivilegesByRoleId(int roleId,List<Privilege> privileges);
	
	// ����Ȩ�޵�uri��ȥ��ѯȨ��
	public Privilege getPrivilegeByURI(String model,String uri);
}
