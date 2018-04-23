package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.OrderItem;

/**
 *	订单明细 
*/
public interface OrderItemDao {
	
	// 批量保存订单明细
	public void insertBatch(List<OrderItem> orderItems);

	// 根据订单的id去查询订单明细
	public List<OrderItem> getOrderItemByOrderId(Integer id);
}
