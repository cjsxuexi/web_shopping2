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
 * 	用户去购买商品的Servlet
*/
@WebServlet("/front/handler/buyProductServlet")
public class BuyProductServlet extends BaseServlet {

	private static final long serialVersionUID = 5165223163406725127L;

	// 用户购买
	public String buy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1、获取商品的id
		String productId = req.getParameter("productId");
		
		// 2、查询商品
		Product product = productService.queryProductById(Integer.parseInt(productId));
		
		// 3、获取购物车
		Cart cart = WebUtils.getCart(req);
		if(cart == null){
			// 如果cart为空,就表示还没有购物车,去创建购物车
			cart = new Cart();
			
			// 购物车创建好之后,放入session中
			WebUtils.setCart(req, cart);
		}
		
		// 4、将要购物的这本书放置到购物车
		cart.addProduct(product);
		
		// 5、跳转到购物车的列表
		return "f:/WEB-INF/front/listcart.jsp";
	}
	
}
