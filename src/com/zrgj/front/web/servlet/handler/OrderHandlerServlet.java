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
 * 	����Servlet
*/
@WebServlet("/front/handler/orderHandlerServlet")
public class OrderHandlerServlet extends BaseServlet{

	private static final long serialVersionUID = -9185421192187334994L;

	private OrderService orderService = BeanFactory.getBean(OrderService.class);
	
	/**
	 *  �¶���(ָ�ľ��Ƕ�����ȷ��ҳ��)
	*/
	public String confirmOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		return "f:/WEB-INF/front/confirmOrderUI.jsp";
	}
	
	
	/**
	 * 	�ύ����
	*/
	public String submitOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1������Ҫ��ȡ���û�
		User user = WebUtils.getUser(req);
		
		// 2����ȡ�����ﳵ
		Cart cart = WebUtils.getCart(req);
		
		// 3����װ��������
		Order order = new Order();
		order.setMessage(req.getParameter("message"));
		order.setPaymentType(Integer.parseInt(req.getParameter("paymentType")));
		
		// 4���ύ����
		orderService.submitOrder(order, cart, user);
		
		// 5���ѹ��ﳵ���
		WebUtils.sessionValidateCart(req);
		
		req.setAttribute("message", "�����ύ�ɹ�,�����������,���Ժ�...");
		
		return "f:/WEB-INF/front/message.jsp";
	}
	
	/**
	 * 	�鿴����
	 * 		�����û���ѯ������
	*/
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// �½������Ĳ�ѯ����
		OrderQueryVo orderQueryVo = new OrderQueryVo();
		
		// 0�������û���ѯ�Ķ���״̬
		String orderStateStr = req.getParameter("orderState");
		String orderNo = req.getParameter("orderNo");
		Order queryOrder = new Order();
		queryOrder.setOrderNo(orderNo);
		queryOrder.setOrderState(orderStateStr!=null ? Integer.parseInt(orderStateStr) : null);
		orderQueryVo.setOrder(queryOrder);
		
		// 1�����Ȼ�ȡ���û�
		User user = WebUtils.getUser(req);
		orderQueryVo.setUser(user);
		// 2����ѯ����
		List<Order> orders = orderService.getOrderByOrderQueryVo(orderQueryVo);
		
		// 3����ת�������б�ҳ��
		req.setAttribute("orders", orders);
		
		return "f:/WEB-INF/front/orderList.jsp";
	}
	
	/**
	 *	���� 
	*/
	public String pay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String orderId = req.getParameter("orderId");
		String flag = req.getParameter("flag");
		
		orderService.updateOrderStatus(Integer.parseInt(orderId), flag);
		
		return "r:"+WebUtils.getContextPath(req)+"/front/handler/orderHandlerServlet?method=list";
	}
	
}
