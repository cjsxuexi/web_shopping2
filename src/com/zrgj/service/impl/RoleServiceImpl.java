package com.zrgj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zrgj.bean.Privilege;
import com.zrgj.bean.Role;
import com.zrgj.dao.PrivilegeDao;
import com.zrgj.dao.RoleDao;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.RoleService;

/**
 * Role服务层
 */
public class RoleServiceImpl implements RoleService {

	// 角色dao
	private RoleDao roleDao = BeanFactory.getBean(RoleDao.class);

	// 权限dao
	private PrivilegeDao privilegeDao = BeanFactory.getBean(PrivilegeDao.class);

	// 增加角色
	public void addRole(Role role) {
		roleDao.insert(role);
	}

	//  查询所有的角色
	public List<Role> queryRoles() {

		// 1、查询所有的角色
		List<Role> roles = roleDao.getRoles();

		return roles;
	}

	// 根据指定的roleId查询Role对象
	public Role queryRoleById(int roleId) {

		// 1、查询具体的角色
		Role role = roleDao.getRoleById(roleId);

		// 2、查询具体角色下面的所有的权限
		if (role != null) {
			List<Privilege> privileges = privilegeDao.getPrivilegesByRoleId(role.getId());
			role.setPrivileges(privileges);
		}

		return role;
	}

	/**
	 * 根据用户的id查询此用所属的所有的角色,在查询好角色的同时也就把所有的每个角色下面的权限一并查询好了
	 */
	public List<Role> queryRolesByUserId(int userId) {

		// 1、查询所有的角色
		List<Role> roles = roleDao.getRolesByUserId(userId);

		// 2、循环遍历角色集合中的每个角色,目的是查询每个角色下面的所有的权限
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				List<Privilege> privileges = privilegeDao.getPrivilegesByRoleId(role.getId());
				role.setPrivileges(privileges);
			}
		}
		return roles;
	}

	// 为角色分配权限
	public void dispatchPrivilegeForRole(Role role,String[] privilegeIds) {
		
		// 1、首先把当前角色下面的所有的权限清空
		privilegeDao.deletePrivilegesByRoleId(role.getId());
		
		// 2、再赋予这个角色全新的权限
		if(privilegeIds != null && privilegeIds.length > 0){
			List<Privilege> privileges = new ArrayList<Privilege>();
			for(String pId : privilegeIds){
				privileges.add(new Privilege(Integer.parseInt(pId)));
			}
			privilegeDao.insertPrivilegesByRoleId(role.getId(),privileges);
		}
	}
}
