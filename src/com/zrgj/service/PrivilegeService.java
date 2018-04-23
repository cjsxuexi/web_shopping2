package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.Privilege;

/**
 * 权限服务层
 */
public interface PrivilegeService {

	// 查询系统中所有的权限
	public List<Privilege> queryPrivileges();

	// 根据角色查询此角色下面所有的权限
	public List<Privilege> queryPrivilegesByRoleId(int roleId);

	// 增加权限
	public void addPrivilege(Privilege privilege);
	
	// 根据uri查询权限
	public Privilege queryPrivilegeByURI(String model,String uri);
}
