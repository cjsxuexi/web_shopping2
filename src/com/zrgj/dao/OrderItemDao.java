package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.OrderItem;

/**
 *	������ϸ 
*/
public interface OrderItemDao {
	
	// �������涩����ϸ
	public void insertBatch(List<OrderItem> orderItems);

	// ���ݶ�����idȥ��ѯ������ϸ
	public List<OrderItem> getOrderItemByOrderId(Integer id);
}
