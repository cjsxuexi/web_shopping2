package com.zrgj.front.web.servlet.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Cart;
import com.zrgj.bean.Order;
import com.zrgj.bean.User;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.OrderService;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.WebUtils;
import com.zrgj.vo.OrderQueryVo;

/**
 * 	订单Servlet
*/
@WebServlet("/front/handler/orderHandlerServlet")
public class OrderHandlerServlet extends BaseServlet{

	private static final long serialVersionUID = -9185421192187334994L;

	private OrderService orderService = BeanFactory.getBean(OrderService.class);
	
	/**
	 *  下订单(指的就是订单的确认页面)
	*/
	public String confirmOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		return "f:/WEB-INF/front/confirmOrderUI.jsp";
	}
	
	
	/**
	 * 	提交订单
	*/
	public String submitOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1、首先要获取到用户
		User user = WebUtils.getUser(req);
		
		// 2、获取到购物车
		Cart cart = WebUtils.getCart(req);
		
		// 3、封装订单数据
		Order order = new Order();
		order.setMessage(req.getParameter("message"));
		order.setPaymentType(Integer.parseInt(req.getParameter("paymentType")));
		
		// 4、提交订单
		orderService.submitOrder(order, cart, user);
		
		// 5、把购物车清空
		WebUtils.sessionValidateCart(req);
		
		req.setAttribute("message", "订单提交成功,订单正在审核,请稍后...");
		
		return "f:/WEB-INF/front/message.jsp";
	}
	
	/**
	 * 	查看订单
	 * 		根据用户查询看订单
	*/
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 新建订单的查询对象
		OrderQueryVo orderQueryVo = new OrderQueryVo();
		
		// 0、接收用户查询的订单状态
		String orderStateStr = req.getParameter("orderState");
		String orderNo = req.getParameter("orderNo");
		Order queryOrder = new Order();
		queryOrder.setOrderNo(orderNo);
		queryOrder.setOrderState(orderStateStr!=null ? Integer.parseInt(orderStateStr) : null);
		orderQueryVo.setOrder(queryOrder);
		
		// 1、首先获取到用户
		User user = WebUtils.getUser(req);
		orderQueryVo.setUser(user);
		// 2、查询订单
		List<Order> orders = orderService.getOrderByOrderQueryVo(orderQueryVo);
		
		// 3、跳转到订单列表页面
		req.setAttribute("orders", orders);
		
		return "f:/WEB-INF/front/orderList.jsp";
	}
	
	/**
	 *	付款 
	*/
	public String pay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String orderId = req.getParameter("orderId");
		String flag = req.getParameter("flag");
		
		orderService.updateOrderStatus(Integer.parseInt(orderId), flag);
		
		return "r:"+WebUtils.getContextPath(req)+"/front/handler/orderHandlerServlet?method=list";
	}
	
}
