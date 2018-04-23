package com.zrgj.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zrgj.bean.Role;
import com.zrgj.dao.RoleDao;
import com.zrgj.exception.DBException;
import com.zrgj.utils.CollectionUtils;
import com.zrgj.utils.DataSouceUtils;

/**
 * 角色RoleDao
 */
public class RoleDaoImpl implements RoleDao {

	private QueryRunner qr = new QueryRunner(DataSouceUtils.getDataSource());

	// 增加角色
	public void insert(Role role) {
		try {
			String sql = "insert into t_role(rname,rdesc) values(?,?)";
			Object[] params = new Object[]{role.getRname(),role.getRdesc()};
			qr.update(sql, params);
		} catch (Exception e) {
			throw new DBException(e);
		}
	}

	// 查询所有的角色
	@SuppressWarnings("unchecked")
	public List<Role> getRoles() {
		List<Role> roles = null;
		try {
			String sql = "select id,rname,rdesc from t_role";
			roles = qr.query(sql, new BeanListHandler<Role>(Role.class));
		} catch (Exception e) {
			throw new DBException(e);
		}
		return (List<Role>) CollectionUtils.returnCollection(roles);
	}

	// 根据角色id查询具体的角色
	public Role getRoleById(int roleId) {
		
		Role role = null;
		try {
			String sql = "select id,rname,rdesc from t_role where id = ?";
			Object[] params = new Object[]{roleId};
			
			role = qr.query(sql, new BeanHandler<Role>(Role.class), params);
		} catch (Exception e) {
			throw new DBException(e);
		}
		return role;
	}

	// 根据用户的id查询此用户下面所有的角色
	@SuppressWarnings("unchecked")
	public List<Role> getRolesByUserId(int userId) {
		
		List<Role> roles = null;
		try {
			String sql = "select r.id,r.rname,r.rdesc from t_role r, t_employee_role ur where ur.user_id = ? and ur.role_id = r.id";
			Object[] params = new Object[]{userId};
			
			roles = qr.query(sql, new BeanListHandler<Role>(Role.class),params);
			
		} catch (Exception e) {
			throw new DBException(e);
		}
		return (List<Role>) CollectionUtils.returnCollection(roles);
	}


	// 根据用户id删除用户下面所有的角色
	public void deleteRolesByUserId(int userId) {
		try {
			String sql = "delete from t_employee_role where user_id = ?";
			Object[] params = new Object[]{userId};
			
			qr.update(sql, params);
		} catch (Exception e) {
			throw new DBException(e);
		}
	}

	// 根据指定的用户id为用户添加角色
	public void insertRoleByUserId(int userId, List<Role> roles) {
		if(roles != null && roles.size() > 0){
			try {
				String sql = "insert into t_employee_role(user_id,role_id) values(?,?)";
				Object[][] params = new Object[roles.size()][];
				for(int i = 0 ; i < roles.size() ; i++){
					Role role = roles.get(i);
					params[i] = new Object[]{userId,role.getId()};
				}
				qr.batch(sql, params);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
