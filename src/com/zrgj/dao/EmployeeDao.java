package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.Employee;

/**
 *	�û�Dao 
*/
public interface EmployeeDao {

	// ����û�
	public void insert(Employee employee);
	
	// ��ѯ�����û�
	public List<Employee> getUsers();
	
	// �����û�id��ѯ�û�
	public Employee getUserById(int userId);
	
	//-------------------------------------------------
	// �û������û����������¼
	public Employee getUserByUsernameAndPassword(String username,String password);
	
}
