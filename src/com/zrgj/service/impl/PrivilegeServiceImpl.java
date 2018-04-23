package com.zrgj.service.impl;

import java.util.List;

import com.zrgj.bean.Privilege;
import com.zrgj.dao.PrivilegeDao;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.PrivilegeService;

/**
 * 	权限服务层实现
*/
public class PrivilegeServiceImpl implements PrivilegeService {

	private PrivilegeDao privilegeDao = BeanFactory.getBean(PrivilegeDao.class);
	
	// 查询所有的权限
	public List<Privilege> queryPrivileges() {
		return privilegeDao.getPrivileges();
	}

	// 根据角色的id查询此角色下面所有的权限
	public List<Privilege> queryPrivilegesByRoleId(int roleId) {
		return privilegeDao.getPrivilegesByRoleId(roleId);
	}

	// 增加权限
	public void addPrivilege(Privilege privilege) {
		privilegeDao.insert(privilege);
	}

	// 根据uri查询权限
	public Privilege queryPrivilegeByURI(String model,String uri) {
		return privilegeDao.getPrivilegeByURI(model,uri);
	}
}
