package com.zrgj.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 	�û�
*/
public class User {
	
	// ����id
	private Integer id;
	
	// �û���
	private String username;
	
	// ����(ǩ��)
	private String password;
	
	// ����
	private String email;
	
	// �ֻ���
	private String phone;
	
	// �û��ĵ�ַ
	private String address;
	
	// �û���״̬(�˺��Ƿ񱻷�)
	private Boolean deleted;
	
	// ����״̬
	private Boolean activation;
	
	// �û���ע��ʱ��
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
