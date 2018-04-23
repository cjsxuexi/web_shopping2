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
 * 	�û�ȥ������Ʒ��Servlet
*/
@WebServlet("/front/handler/buyProductServlet")
public class BuyProductServlet extends BaseServlet {

	private static final long serialVersionUID = 5165223163406725127L;

	// �û�����
	public String buy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1����ȡ��Ʒ��id
		String productId = req.getParameter("productId");
		
		// 2����ѯ��Ʒ
		Product product = productService.queryProductById(Integer.parseInt(productId));
		
		// 3����ȡ���ﳵ
		Cart cart = WebUtils.getCart(req);
		if(cart == null){
			// ���cartΪ��,�ͱ�ʾ��û�й��ﳵ,ȥ�������ﳵ
			cart = new Cart();
			
			// ���ﳵ������֮��,����session��
			WebUtils.setCart(req, cart);
		}
		
		// 4����Ҫ������Ȿ����õ����ﳵ
		cart.addProduct(product);
		
		// 5����ת�����ﳵ���б�
		return "f:/WEB-INF/front/listcart.jsp";
	}
	
}
