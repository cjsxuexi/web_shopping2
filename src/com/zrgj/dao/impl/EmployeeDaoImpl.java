package com.zrgj.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zrgj.bean.Employee;
import com.zrgj.bean.User;
import com.zrgj.dao.EmployeeDao;
import com.zrgj.exception.DBException;
import com.zrgj.utils.CollectionUtils;
import com.zrgj.utils.DataSouceUtils;

/**
 * 	�û�Dao
*/
public class EmployeeDaoImpl implements EmployeeDao {

	private QueryRunner qr = new QueryRunner(DataSouceUtils.getDataSource());

	// ����û�
	public void insert(Employee user) {
		try {
			String sql = "insert into t_employee(username,password) values(?,?)";
			Object[] params = new Object[]{user.getUsername(),user.getPassword()};
			qr.update(sql, params);
		} catch (Exception e) {
			throw new DBException("����û��쳣", e);
		}
	}

	// ��ѯ�����û�
	@SuppressWarnings("unchecked")
	public List<Employee> getUsers() {
		
		List<User> users = null;
		try {
			String sql = "select id,username,password from t_employee";
			users = qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (Exception e) {
			throw new DBException("����û��쳣", e);
		}
		return (List<Employee>) CollectionUtils.returnCollection(users);
	}

	// ����id��ѯ�û�
	public Employee getUserById(int userId) {
		Employee user = null;
		try {
			String sql = "select id,username,password from t_employee where id = ?";
			Object[] params = new Object[]{userId};
			user = qr.query(sql, new BeanHandler<Employee>(Employee.class), params);
		} catch (Exception e) {
		}
		return user;
	}

	// �����û����������ѯ�û�
	public Employee getUserByUsernameAndPassword(String username, String password) {
		Employee user = null;
		try {
			String sql = "select id,username,password from t_employee where username=? and password=?";
			Object[] params = new Object[]{username,password};
			user = qr.query(sql, new BeanHandler<Employee>(Employee.class), params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return user;
	}
}
