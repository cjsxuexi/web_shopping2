package com.zrgj.vo;

import com.zrgj.bean.Order;
import com.zrgj.bean.User;

/**
 * 	������ѯʵ��
*/
public class OrderQueryVo {
	
	// �û�
	private User user;
	
	// ����
	private Order order;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
