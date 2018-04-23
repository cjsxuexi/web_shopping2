package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.Privilege;

/**
 * 	权限DAO
*/
public interface PrivilegeDao {

	// 查询系统中所有的权限
	public List<Privilege> getPrivileges();
	
	// 根据角色查询此角色下面所有的权限
	public List<Privilege> getPrivilegesByRoleId(int roleId);
	
	// 增加权限
	public void insert(Privilege privilege);
	
	// 根据角色的id去删除这个角色下面的所有的权限
	public void deletePrivilegesByRoleId(int roleId);
	
	// 根据角色的id添加权限
	public void insertPrivilegesByRoleId(int roleId,List<Privilege> privileges);
	
	// 根据权限的uri来去查询权限
	public Privilege getPrivilegeByURI(String model,String uri);
}
