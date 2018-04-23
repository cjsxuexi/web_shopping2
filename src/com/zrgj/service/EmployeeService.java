package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.Employee;
import com.zrgj.bean.Privilege;

/**
 * 	�û�����
*/
public interface EmployeeService {

	// ����û�
	public void addUser(Employee user);
	
	// ��ѯ�����û�
	public List<Employee> queryUsers();
	
	// �����û�id��ѯ�û�
	public Employee queryUserById(int id);
	
	// Ϊ�û������ɫ
	public void dispatchRoleForUser(Employee user , String[] roleIds);
	
	// �����û����������ѯ�û�
	public Employee queryUserByUsernameAndPassword(String username,String password);
	
	// �����û���id��ѯ�û��������е�Ȩ��
	public List<Privilege> queryPrivilegesByUserId(int userId);

	// Ա����¼
	public Employee login(String username, String password);
	
}
