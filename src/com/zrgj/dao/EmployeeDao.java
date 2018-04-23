package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.Employee;

/**
 *	用户Dao 
*/
public interface EmployeeDao {

	// 添加用户
	public void insert(Employee employee);
	
	// 查询所有用户
	public List<Employee> getUsers();
	
	// 根据用户id查询用户
	public Employee getUserById(int userId);
	
	//-------------------------------------------------
	// 用户根据用户名和密码登录
	public Employee getUserByUsernameAndPassword(String username,String password);
	
}
