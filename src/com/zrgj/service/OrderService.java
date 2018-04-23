package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.Cart;
import com.zrgj.bean.Order;
import com.zrgj.bean.User;
import com.zrgj.vo.OrderQueryVo;

/**
 * 	订单服务
*/
public interface OrderService {
	
	// 提交订单
	public void submitOrder(Order order,Cart cart,User user);

	// 根据用户查询订单
	public List<Order> getOrderByOrderQueryVo(OrderQueryVo orderQueryVo);

	// 根据订单id查询订单
	public Order queryOrderById(int orderId);

	// 根据订单id去更新订单
	public void updateOrderStatus(int orderId,String flag);
}
