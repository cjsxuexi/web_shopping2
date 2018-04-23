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
 *  ��������
*/
@WebServlet("/sys/serlvet/handler/orderHandlerServlet")
public class OrderHandlerServlet extends BaseServlet{

	private static final long serialVersionUID = -6176789402867853712L;

	/**
	 * 	���ݶ���id��ѯ����
	*/
	public String queryOrderDetailByOrderId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1����ȡ�û����ݹ����Ķ���id
		String orderId = req.getParameter("orderId");
		
		// 2�����ݶ���idȥ��ѯ����
		Order order = orderService.queryOrderById(Integer.parseInt(orderId));
		
		req.setAttribute("order", order);
		
		return "f:/WEB-INF/pages/order/orderDetail.jsp";
	}
	
	/**
	 * 	���ݶ���id���¶���״̬
	*/
	public String updateOrderStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1����ȡ�û����ݹ����Ķ���id
		String orderId = req.getParameter("orderId");
		String operator = req.getParameter("flag");
		
		// 2�����ݶ���idȥ���¶���
		orderService.updateOrderStatus(Integer.parseInt(orderId),operator);
		
		return "r:"+WebUtils.getContextPath(req)+"/sys/servlet/ui/orderServlet?method=list";
	}
	
}
