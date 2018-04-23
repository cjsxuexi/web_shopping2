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
 * 	购物车Servlet
*/
@WebServlet("/front/handler/cartServlet")
public class CartServlet extends BaseServlet {

	private static final long serialVersionUID = 2430968693825703309L;

	// 查看购物车列表
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/front/listcart.jsp";
	}
	
	/**
	 * 	清空购物车
	*/
	public String clearAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1、要获取购物车
		Cart cart = WebUtils.getCart(req);
		
		// 2、清空购物车
		cart.clearAll();
		
		// 3、跳转到购物车列表
		return "r:"+WebUtils.getContextPath(req)+"/front/handler/cartServlet?method=list";
	}
	
	/**
	 *	根据商品id去从购物车中删除商品 
	*/
	public String deleteByProductId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1、获取商品的id
		String productId = req.getParameter("productId");
		
		// 2、去拿到购物车
		Cart cart = WebUtils.getCart(req);
		
		// 3、去删除
		// cart.deleteProduct(Integer.parseInt(productId));
		Product product = new Product();
		product.setId(Integer.parseInt(productId));
		cart.deleteProduct(product);
		
		// 4、跳转到列表页面
		return "r:"+WebUtils.getContextPath(req)+"/front/handler/cartServlet?method=list";
	}
	
}
