package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.Cart;
import com.zrgj.bean.Order;
import com.zrgj.bean.User;
import com.zrgj.vo.OrderQueryVo;

/**
 * 	��������
*/
public interface OrderService {
	
	// �ύ����
	public void submitOrder(Order order,Cart cart,User user);

	// �����û���ѯ����
	public List<Order> getOrderByOrderQueryVo(OrderQueryVo orderQueryVo);

	// ���ݶ���id��ѯ����
	public Order queryOrderById(int orderId);

	// ���ݶ���idȥ���¶���
	public void updateOrderStatus(int orderId,String flag);
}
