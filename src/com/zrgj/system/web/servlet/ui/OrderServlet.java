package com.zrgj.system.web.servlet.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Order;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.vo.OrderQueryVo;

/**
 * 	订单列表
*/
@WebServlet("/sys/servlet/ui/orderServlet")
public class OrderServlet extends BaseServlet{

	private static final long serialVersionUID = 556159903916073776L;
	
	/**
	 * 订单列表
	 */
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 新建订单的查询对象
		OrderQueryVo orderQueryVo = new OrderQueryVo();

		// 0、接收用户查询的订单状态
		String orderStateStr = req.getParameter("orderState");
		String orderNo = req.getParameter("orderNo");
		Order queryOrder = new Order();
		queryOrder.setOrderNo(orderNo);
		queryOrder.setOrderState(orderStateStr != null ? Integer.parseInt(orderStateStr) : null);
		orderQueryVo.setOrder(queryOrder);

		// 2、查询订单
		List<Order> orders = orderService.getOrderByOrderQueryVo(orderQueryVo);

		// 3、跳转到订单列表页面
		req.setAttribute("orders", orders);
		
		return "f:/WEB-INF/pages/order/orderList.jsp";
	}
}
