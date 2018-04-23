package com.zrgj.system.web.servlet.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Order;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.WebUtils;

/**
 *  订单处理
*/
@WebServlet("/sys/serlvet/handler/orderHandlerServlet")
public class OrderHandlerServlet extends BaseServlet{

	private static final long serialVersionUID = -6176789402867853712L;

	/**
	 * 	根据订单id查询订单
	*/
	public String queryOrderDetailByOrderId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1、获取用户传递过来的订单id
		String orderId = req.getParameter("orderId");
		
		// 2、根据订单id去查询订单
		Order order = orderService.queryOrderById(Integer.parseInt(orderId));
		
		req.setAttribute("order", order);
		
		return "f:/WEB-INF/pages/order/orderDetail.jsp";
	}
	
	/**
	 * 	根据订单id更新订单状态
	*/
	public String updateOrderStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1、获取用户传递过来的订单id
		String orderId = req.getParameter("orderId");
		String operator = req.getParameter("flag");
		
		// 2、根据订单id去更新订单
		orderService.updateOrderStatus(Integer.parseInt(orderId),operator);
		
		return "r:"+WebUtils.getContextPath(req)+"/sys/servlet/ui/orderServlet?method=list";
	}
	
}
