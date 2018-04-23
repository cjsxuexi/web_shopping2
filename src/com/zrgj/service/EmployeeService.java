package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.Employee;
import com.zrgj.bean.Privilege;

/**
 * 	用户服务
*/
public interface EmployeeService {

	// 添加用户
	public void addUser(Employee user);
	
	// 查询所有用户
	public List<Employee> queryUsers();
	
	// 根据用户id查询用户
	public Employee queryUserById(int id);
	
	// 为用户分配角色
	public void dispatchRoleForUser(Employee user , String[] roleIds);
	
	// 根据用户名和密码查询用户
	public Employee queryUserByUsernameAndPassword(String username,String password);
	
	// 根据用户的id查询用户下面所有的权限
	public List<Privilege> queryPrivilegesByUserId(int userId);

	// 员工登录
	public Employee login(String username, String password);
	
}
