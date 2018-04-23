package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.Role;

/**
 * 	角色Dao
*/
public interface RoleDao {

	// 添加角色
	public void insert(Role role);
	
	// 获取所有的角色
	public List<Role> getRoles();
	
	// 根据角色id查询角色
	public Role getRoleById(int roleId);
	
	// 根据用户的id查询此用户有哪些角色
	public List<Role> getRolesByUserId(int userId);
	
	// 根据用户的id删除用户的角色
	public void deleteRolesByUserId(int userId);
	
	// 根据用户的id为用户添加角色
	public void insertRoleByUserId(int userId, List<Role> roles);
}
