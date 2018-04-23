package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.Role;

/**
 * 角色服务
 */
public interface RoleService {

	// 添加角色
	public void addRole(Role role);

	// 获取所有的角色
	public List<Role> queryRoles();

	// 根据角色id查询角色
	public Role queryRoleById(int roleId);

	// 根据用户的id查询此用户有哪些角色
	public List<Role> queryRolesByUserId(int userId);
	
	// 为角色分配权限,role表示的是为哪个就是分配权限 , privileges表示的是分配的权限
	public void dispatchPrivilegeForRole(Role role,String[] privilegeIds);

}
