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
 * 	用户服务
*/
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao userDao = BeanFactory.getBean(EmployeeDao.class);
	
	private RoleDao roleDao = BeanFactory.getBean(RoleDao.class);
	
	private PrivilegeDao privilegeDao = BeanFactory.getBean(PrivilegeDao.class);
	
	// 添加用户
	public void addUser(Employee user) {
		userDao.insert(user);
	}

	// 查询所有用户
	public List<Employee> queryUsers() {
		return userDao.getUsers();
	}

	// 根据用户id查询用户
	public Employee queryUserById(int userId) {
		
		// 1、根据用户id查询用户
		Employee user =  userDao.getUserById(userId);
		
		// 2、根据用户id查询此用户下面所有的角色
		if(user != null){
			List<Role> roles = roleDao.getRolesByUserId(userId);
			
			user.setRoles(roles);
		}
		return user;
	}

	// 为用户分配角色
	public void dispatchRoleForUser(Employee user, String[] roleIds) {
		
		// 1、先把用户当前的角色给清空
		roleDao.deleteRolesByUserId(user.getId());
		
		// 2、为用户新增角色
		if(roleIds != null && roleIds.length > 0){
			List<Role> roles = new ArrayList<Role>();
			for(String roleId : roleIds){
				roles.add(new Role(Integer.parseInt(roleId)));
			}
			roleDao.insertRoleByUserId(user.getId(),roles);
		}
	}

	// 用户名和密码查询用户
	public Employee queryUserByUsernameAndPassword(String username, String password) {
		return userDao.getUserByUsernameAndPassword(username, password);
	}

	// 根据用户id查询所有的权限
	public List<Privilege> queryPrivilegesByUserId(int userId) {
		
		// 1、获取角色
		Employee user = this.queryUserById(userId);
		
		List<Privilege> allPrivileges = null;
		
		// 2、循环遍历此角色
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
		
		// 1、根据用户名和密码查询员工
		Employee employee = userDao.getUserByUsernameAndPassword(username, password);
		
		if(employee != null){
			// 2、根据员工id查询员工所有的权限
			List<Role> roles = roleDao.getRolesByUserId(employee.getId());
			employee.setRoles(roles);
		}
		return employee;
	}
}
