package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.Privilege;

/**
 * Ȩ�޷����
 */
public interface PrivilegeService {

	// ��ѯϵͳ�����е�Ȩ��
	public List<Privilege> queryPrivileges();

	// ���ݽ�ɫ��ѯ�˽�ɫ�������е�Ȩ��
	public List<Privilege> queryPrivilegesByRoleId(int roleId);

	// ����Ȩ��
	public void addPrivilege(Privilege privilege);
	
	// ����uri��ѯȨ��
	public Privilege queryPrivilegeByURI(String model,String uri);
}
