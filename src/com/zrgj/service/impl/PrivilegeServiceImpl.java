package com.zrgj.service.impl;

import java.util.List;

import com.zrgj.bean.Privilege;
import com.zrgj.dao.PrivilegeDao;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.PrivilegeService;

/**
 * 	Ȩ�޷����ʵ��
*/
public class PrivilegeServiceImpl implements PrivilegeService {

	private PrivilegeDao privilegeDao = BeanFactory.getBean(PrivilegeDao.class);
	
	// ��ѯ���е�Ȩ��
	public List<Privilege> queryPrivileges() {
		return privilegeDao.getPrivileges();
	}

	// ���ݽ�ɫ��id��ѯ�˽�ɫ�������е�Ȩ��
	public List<Privilege> queryPrivilegesByRoleId(int roleId) {
		return privilegeDao.getPrivilegesByRoleId(roleId);
	}

	// ����Ȩ��
	public void addPrivilege(Privilege privilege) {
		privilegeDao.insert(privilege);
	}

	// ����uri��ѯȨ��
	public Privilege queryPrivilegeByURI(String model,String uri) {
		return privilegeDao.getPrivilegeByURI(model,uri);
	}
}
