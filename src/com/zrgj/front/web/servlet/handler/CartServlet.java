package com.zrgj.front.web.servlet.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Cart;
import com.zrgj.bean.Product;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.WebUtils;

/**
 * 	���ﳵServlet
*/
@WebServlet("/front/handler/cartServlet")
public class CartServlet extends BaseServlet {

	private static final long serialVersionUID = 2430968693825703309L;

	// �鿴���ﳵ�б�
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/front/listcart.jsp";
	}
	
	/**
	 * 	��չ��ﳵ
	*/
	public String clearAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1��Ҫ��ȡ���ﳵ
		Cart cart = WebUtils.getCart(req);
		
		// 2����չ��ﳵ
		cart.clearAll();
		
		// 3����ת�����ﳵ�б�
		return "r:"+WebUtils.getContextPath(req)+"/front/handler/cartServlet?method=list";
	}
	
	/**
	 *	������Ʒidȥ�ӹ��ﳵ��ɾ����Ʒ 
	*/
	public String deleteByProductId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1����ȡ��Ʒ��id
		String productId = req.getParameter("productId");
		
		// 2��ȥ�õ����ﳵ
		Cart cart = WebUtils.getCart(req);
		
		// 3��ȥɾ��
		// cart.deleteProduct(Integer.parseInt(productId));
		Product product = new Product();
		product.setId(Integer.parseInt(productId));
		cart.deleteProduct(product);
		
		// 4����ת���б�ҳ��
		return "r:"+WebUtils.getContextPath(req)+"/front/handler/cartServlet?method=list";
	}
	
}
