package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.Order;
import com.zrgj.vo.OrderQueryVo;

/**
 * 	����dao
*/
public interface OrderDao {

	// ���涩��
	public void insert(Order order);

	// �����û���id��ѯ����
	public List<Order> getOrderByOrderQueryVo(OrderQueryVo orderQueryVo);

	// ���ݶ���id��ѯ����
	public Order getOrderById(int orderId);

	// ���¶���
	public void update(Order order);
	
}
