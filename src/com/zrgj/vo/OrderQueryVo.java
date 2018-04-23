package com.zrgj.vo;

import com.zrgj.bean.Order;
import com.zrgj.bean.User;

/**
 * 	订单查询实体
*/
public class OrderQueryVo {
	
	// 用户
	private User user;
	
	// 订单
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
