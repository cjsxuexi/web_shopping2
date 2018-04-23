package com.zrgj.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zrgj.bean.Cart;
import com.zrgj.bean.CartItem;
import com.zrgj.bean.Order;
import com.zrgj.bean.OrderItem;
import com.zrgj.bean.OrderState;
import com.zrgj.bean.User;
import com.zrgj.dao.OrderDao;
import com.zrgj.dao.OrderItemDao;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.OrderService;
import com.zrgj.vo.OrderQueryVo;

/**
 * ��������
 */
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao = BeanFactory.getBean(OrderDao.class);

	private OrderItemDao orderItemDao = BeanFactory.getBean(OrderItemDao.class);

	// ���涩��
	public void submitOrder(Order order, Cart cart, User user) {

		// 1�����涩������
		order.setOrderPrice(cart.getTotalPrice()); // �����ļ۸�
		order.setOrderNo(generateOrderNo()); // ������
		order.setOrderState(OrderState.WAIT_CHECKED); // �����
		order.setSubmitTime(new Date()); // �µ�ʱ��
		order.setUser(user); // ���ö������������û�
		orderDao.insert(order);

		// 2�����涩������ϸ(Ҳ����˵���ﳵ�����ÿ����Ŀ��ʵ���Ƕ�����ϸ����Ŀ)
		List<CartItem> cartItems = cart.getCartItems();
		for (CartItem cartItem : cartItems) {

			OrderItem orderItem = new OrderItem();
			orderItem.setProductName(cartItem.getProduct().getPname()); // ��Ʒ����
			orderItem.setProductPrice(cartItem.getProduct().getPprice()); // ��Ʒ�ļ۸�
			orderItem.setOrderItemPrice(cartItem.getSubPrice()); // ����С��
			orderItem.setQunantity(cartItem.getQuantity()); // ���õ�������

			// ���ö����Ͷ�����ϸ��ά����ϵ
			orderItem.setOrder(order); // ���ö�����ϸ�����Ķ���
			order.getOrderItems().add(orderItem);
		}

		// 3��ȥ����OrderItem��Daoȥ�������涩����ϸ
		orderItemDao.insertBatch(order.getOrderItems());
	}


	// -----------------------------------------------------

	// ���ɶ�����
	private String generateOrderNo() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderPriex = sdf.format(new Date());

		return orderPriex + System.nanoTime();
	}


	// �����û���ѯ����
	public List<Order> getOrderByOrderQueryVo(OrderQueryVo orderQueryVo) {
		
		// 1����ȡ������(�����û�id��ȡ����)
		List<Order> orders = orderDao.getOrderByOrderQueryVo(orderQueryVo);
		if(orders != null && !orders.isEmpty()){
			// 2����ȡÿ������������������ϸ
			for(Order order : orders){
				// ���ݶ�����idȥ��ѯ������ϸ
				List<OrderItem> orderItems = orderItemDao.getOrderItemByOrderId(order.getId());
				if(orderItems != null && !orderItems.isEmpty()){
					// ��ϵ��ά��
					order.setOrderItems(orderItems);
				}
			}
		}
		return orders;
	}


	// ���ݶ���idȥ��ѯ����
	public Order queryOrderById(int orderId) {
		
		// 1����ȥ��ѯ��������
		Order order = orderDao.getOrderById(orderId);
		
		if(order != null){
			// 2����ȥ��ѯ������ϸ�ӱ�
			List<OrderItem> orderItems = orderItemDao.getOrderItemByOrderId(orderId);
			order.setOrderItems(orderItems);
		}
		return order;
	}

	// ���ݶ���idȥ���¶���
	public void updateOrderStatus(int orderId,String flag) {
		
		// 1�����ݶ���id��ѯ����
		Order order = orderDao.getOrderById(orderId);
		
		if("checkedPass".equals(flag)){
			// ���ͨ��
			if(order.getOrderState() == OrderState.WAIT_CHECKED){
				// ��������˲��Ҷ�����֧����ʽ�ǻ�������
				if(order.getPaymentType() == 0){
					// ���ʱ����ܽ�����״̬��Ϊ
					order.setOrderState(OrderState.WAIT_DELIVER);
				}
				if(order.getPaymentType() == 1){
					// ��ʾ��������˲��Ҷ�����֧����ʽ������֧��
					order.setOrderState(OrderState.WAIT_PAYMENT);
				}
			}
		}else if("checkedFail".equals(flag)){
			// ���ʧ��
			if(order.getOrderState() == OrderState.WAIT_CHECKED){
				// ���������,����ȡ��
				order.setOrderState(OrderState.CANCLE);
			}
		}else if("cancle".equals(flag)){
			// ��ʾ�����û��������ȡ��
			Integer orderStatus = order.getOrderState();
			if(orderStatus == OrderState.WAIT_PAYMENT || orderStatus == OrderState.WAIT_DELIVER ||
			   orderStatus == OrderState.WAIT_RECEIVE){
				//  ����ȡ��
				order.setOrderState(OrderState.CANCLE);
			}
		}else if("beginDeliver".equals(flag)){
			// ��ʾ�����û�Ҫ������
			if(order.getOrderState() == OrderState.WAIT_DELIVER){
				// ��ʾ�����û�ֻ���ڶ����Ǵ�������ʱ����ܷ���
				order.setOrderState(OrderState.WAIT_RECEIVE);
			}
		}else if("pay".equals(flag)){
			// ��ʾ�����û�Ҫ������...
			if(order.getOrderState() == OrderState.WAIT_CHECKED && order.getPaymentType() == 1){
				// ��ʾ�û������֮��,����״̬�Ǵ�����
				order.setOrderState(OrderState.WAIT_DELIVER);
			}
		}
		// 3�����¶���״̬
		orderDao.update(order);
	}
}
