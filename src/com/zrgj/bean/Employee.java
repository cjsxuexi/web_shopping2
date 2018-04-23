package com.zrgj.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *	用户实体
*/
public class Employee {

	// 对象主键
	private Integer id;
	
	// 用户名
	private String username;
	
	//  密码
	private String password;
	
	// 一个用户有多个角色
	private List<Role> roles = new ArrayList<Role>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
