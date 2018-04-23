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
 * Role�����
 */
public class RoleServiceImpl implements RoleService {

	// ��ɫdao
	private RoleDao roleDao = BeanFactory.getBean(RoleDao.class);

	// Ȩ��dao
	private PrivilegeDao privilegeDao = BeanFactory.getBean(PrivilegeDao.class);

	// ���ӽ�ɫ
	public void addRole(Role role) {
		roleDao.insert(role);
	}

	//  ��ѯ���еĽ�ɫ
	public List<Role> queryRoles() {

		// 1����ѯ���еĽ�ɫ
		List<Role> roles = roleDao.getRoles();

		return roles;
	}

	// ����ָ����roleId��ѯRole����
	public Role queryRoleById(int roleId) {

		// 1����ѯ����Ľ�ɫ
		Role role = roleDao.getRoleById(roleId);

		// 2����ѯ�����ɫ��������е�Ȩ��
		if (role != null) {
			List<Privilege> privileges = privilegeDao.getPrivilegesByRoleId(role.getId());
			role.setPrivileges(privileges);
		}

		return role;
	}

	/**
	 * �����û���id��ѯ�������������еĽ�ɫ,�ڲ�ѯ�ý�ɫ��ͬʱҲ�Ͱ����е�ÿ����ɫ�����Ȩ��һ����ѯ����
	 */
	public List<Role> queryRolesByUserId(int userId) {

		// 1����ѯ���еĽ�ɫ
		List<Role> roles = roleDao.getRolesByUserId(userId);

		// 2��ѭ��������ɫ�����е�ÿ����ɫ,Ŀ���ǲ�ѯÿ����ɫ��������е�Ȩ��
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				List<Privilege> privileges = privilegeDao.getPrivilegesByRoleId(role.getId());
				role.setPrivileges(privileges);
			}
		}
		return roles;
	}

	// Ϊ��ɫ����Ȩ��
	public void dispatchPrivilegeForRole(Role role,String[] privilegeIds) {
		
		// 1�����Ȱѵ�ǰ��ɫ��������е�Ȩ�����
		privilegeDao.deletePrivilegesByRoleId(role.getId());
		
		// 2���ٸ��������ɫȫ�µ�Ȩ��
		if(privilegeIds != null && privilegeIds.length > 0){
			List<Privilege> privileges = new ArrayList<Privilege>();
			for(String pId : privilegeIds){
				privileges.add(new Privilege(Integer.parseInt(pId)));
			}
			privilegeDao.insertPrivilegesByRoleId(role.getId(),privileges);
		}
	}
}
