package com.zrgj.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 	用户
*/
public class User {
	
	// 对象id
	private Integer id;
	
	// 用户名
	private String username;
	
	// 密码(签名)
	private String password;
	
	// 邮箱
	private String email;
	
	// 手机号
	private String phone;
	
	// 用户的地址
	private String address;
	
	// 用户的状态(账号是否被封)
	private Boolean deleted;
	
	// 激活状态
	private Boolean activation;
	
	// 用户的注册时间
	private Date registerTime;
	
	// 
	private List<Order> orders = new ArrayList<Order>();

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Boolean getActivation() {
		return activation;
	}

	public void setActivation(Boolean activation) {
		this.activation = activation;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
