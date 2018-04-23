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
 * 订单服务
 */
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao = BeanFactory.getBean(OrderDao.class);

	private OrderItemDao orderItemDao = BeanFactory.getBean(OrderItemDao.class);

	// 保存订单
	public void submitOrder(Order order, Cart cart, User user) {

		// 1、保存订单主体
		order.setOrderPrice(cart.getTotalPrice()); // 订单的价格
		order.setOrderNo(generateOrderNo()); // 订单号
		order.setOrderState(OrderState.WAIT_CHECKED); // 待审核
		order.setSubmitTime(new Date()); // 下单时间
		order.setUser(user); // 设置订单所关联的用户
		orderDao.insert(order);

		// 2、保存订单的明细(也就是说购物车里面的每项条目其实就是订单明细的条目)
		List<CartItem> cartItems = cart.getCartItems();
		for (CartItem cartItem : cartItems) {

			OrderItem orderItem = new OrderItem();
			orderItem.setProductName(cartItem.getProduct().getPname()); // 商品名称
			orderItem.setProductPrice(cartItem.getProduct().getPprice()); // 商品的价格
			orderItem.setOrderItemPrice(cartItem.getSubPrice()); // 设置小计
			orderItem.setQunantity(cartItem.getQuantity()); // 设置的是数量

			// 设置订单和订单明细的维护关系
			orderItem.setOrder(order); // 设置订单明细所属的订单
			order.getOrderItems().add(orderItem);
		}

		// 3、去调用OrderItem的Dao去批量保存订单明细
		orderItemDao.insertBatch(order.getOrderItems());
	}


	// -----------------------------------------------------

	// 生成订单号
	private String generateOrderNo() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderPriex = sdf.format(new Date());

		return orderPriex + System.nanoTime();
	}


	// 根据用户查询订单
	public List<Order> getOrderByOrderQueryVo(OrderQueryVo orderQueryVo) {
		
		// 1、获取主订单(根据用户id获取订单)
		List<Order> orders = orderDao.getOrderByOrderQueryVo(orderQueryVo);
		if(orders != null && !orders.isEmpty()){
			// 2、获取每个订单所关联订单明细
			for(Order order : orders){
				// 根据订单的id去查询订单明细
				List<OrderItem> orderItems = orderItemDao.getOrderItemByOrderId(order.getId());
				if(orderItems != null && !orderItems.isEmpty()){
					// 关系的维护
					order.setOrderItems(orderItems);
				}
			}
		}
		return orders;
	}


	// 根据订单id去查询订单
	public Order queryOrderById(int orderId) {
		
		// 1、先去查询订单主表
		Order order = orderDao.getOrderById(orderId);
		
		if(order != null){
			// 2、再去查询订单明细子表
			List<OrderItem> orderItems = orderItemDao.getOrderItemByOrderId(orderId);
			order.setOrderItems(orderItems);
		}
		return order;
	}

	// 根据订单id去更新订单
	public void updateOrderStatus(int orderId,String flag) {
		
		// 1、根据订单id查询订单
		Order order = orderDao.getOrderById(orderId);
		
		if("checkedPass".equals(flag)){
			// 审核通过
			if(order.getOrderState() == OrderState.WAIT_CHECKED){
				// 订单待审核并且订单的支付方式是货到付款
				if(order.getPaymentType() == 0){
					// 这个时候才能将订单状态改为
					order.setOrderState(OrderState.WAIT_DELIVER);
				}
				if(order.getPaymentType() == 1){
					// 表示订单待审核并且订单的支付方式是在线支付
					order.setOrderState(OrderState.WAIT_PAYMENT);
				}
			}
		}else if("checkedFail".equals(flag)){
			// 审核失败
			if(order.getOrderState() == OrderState.WAIT_CHECKED){
				// 订单待审核,订单取消
				order.setOrderState(OrderState.CANCLE);
			}
		}else if("cancle".equals(flag)){
			// 表示的是用户点击的是取消
			Integer orderStatus = order.getOrderState();
			if(orderStatus == OrderState.WAIT_PAYMENT || orderStatus == OrderState.WAIT_DELIVER ||
			   orderStatus == OrderState.WAIT_RECEIVE){
				//  订单取消
				order.setOrderState(OrderState.CANCLE);
			}
		}else if("beginDeliver".equals(flag)){
			// 表示的是用户要发货啦
			if(order.getOrderState() == OrderState.WAIT_DELIVER){
				// 表示的是用户只有在订单是待发货的时候才能发货
				order.setOrderState(OrderState.WAIT_RECEIVE);
			}
		}else if("pay".equals(flag)){
			// 表示的是用户要付款啦...
			if(order.getOrderState() == OrderState.WAIT_CHECKED && order.getPaymentType() == 1){
				// 表示用户付完款之后,订单状态是待发货
				order.setOrderState(OrderState.WAIT_DELIVER);
			}
		}
		// 3、更新订单状态
		orderDao.update(order);
	}
}
