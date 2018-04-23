package com.zrgj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zrgj.bean.Employee;
import com.zrgj.bean.Privilege;
import com.zrgj.bean.Role;
import com.zrgj.dao.EmployeeDao;
import com.zrgj.dao.PrivilegeDao;
import com.zrgj.dao.RoleDao;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.EmployeeService;

/**
 * 	�û�����
*/
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao userDao = BeanFactory.getBean(EmployeeDao.class);
	
	private RoleDao roleDao = BeanFactory.getBean(RoleDao.class);
	
	private PrivilegeDao privilegeDao = BeanFactory.getBean(PrivilegeDao.class);
	
	// ����û�
	public void addUser(Employee user) {
		userDao.insert(user);
	}

	// ��ѯ�����û�
	public List<Employee> queryUsers() {
		return userDao.getUsers();
	}

	// �����û�id��ѯ�û�
	public Employee queryUserById(int userId) {
		
		// 1�������û�id��ѯ�û�
		Employee user =  userDao.getUserById(userId);
		
		// 2�������û�id��ѯ���û��������еĽ�ɫ
		if(user != null){
			List<Role> roles = roleDao.getRolesByUserId(userId);
			
			user.setRoles(roles);
		}
		return user;
	}

	// Ϊ�û������ɫ
	public void dispatchRoleForUser(Employee user, String[] roleIds) {
		
		// 1���Ȱ��û���ǰ�Ľ�ɫ�����
		roleDao.deleteRolesByUserId(user.getId());
		
		// 2��Ϊ�û�������ɫ
		if(roleIds != null && roleIds.length > 0){
			List<Role> roles = new ArrayList<Role>();
			for(String roleId : roleIds){
				roles.add(new Role(Integer.parseInt(roleId)));
			}
			roleDao.insertRoleByUserId(user.getId(),roles);
		}
	}

	// �û����������ѯ�û�
	public Employee queryUserByUsernameAndPassword(String username, String password) {
		return userDao.getUserByUsernameAndPassword(username, password);
	}

	// �����û�id��ѯ���е�Ȩ��
	public List<Privilege> queryPrivilegesByUserId(int userId) {
		
		// 1����ȡ��ɫ
		Employee user = this.queryUserById(userId);
		
		List<Privilege> allPrivileges = null;
		
		// 2��ѭ�������˽�ɫ
		if(user != null){
			List<Role> roles = user.getRoles();
			if(roles!= null && roles.size() >0){
				allPrivileges = new ArrayList<Privilege>();
				for(Role role : roles){
					List<Privilege> privileges = privilegeDao.getPrivilegesByRoleId(role.getId());
					allPrivileges.addAll(privileges);
				}
			}
		}
		return allPrivileges;
	}

	@Override
	public Employee login(String username, String password) {
		
		// 1�������û����������ѯԱ��
		Employee employee = userDao.getUserByUsernameAndPassword(username, password);
		
		if(employee != null){
			// 2������Ա��id��ѯԱ�����е�Ȩ��
			List<Role> roles = roleDao.getRolesByUserId(employee.getId());
			employee.setRoles(roles);
		}
		return employee;
	}
}
