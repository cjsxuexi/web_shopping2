package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.Order;
import com.zrgj.vo.OrderQueryVo;

/**
 * 	订单dao
*/
public interface OrderDao {

	// 保存订单
	public void insert(Order order);

	// 根据用户的id查询订单
	public List<Order> getOrderByOrderQueryVo(OrderQueryVo orderQueryVo);

	// 根据订单id查询订单
	public Order getOrderById(int orderId);

	// 更新订单
	public void update(Order order);
	
}
