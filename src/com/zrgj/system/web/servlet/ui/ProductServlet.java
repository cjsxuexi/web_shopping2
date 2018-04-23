package com.zrgj.system.web.servlet.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Product;
import com.zrgj.bean.ProductType;
import com.zrgj.bean.result.ProductResult;
import com.zrgj.system.web.servlet.BaseServlet;

@WebServlet("/sys/servlet/ui/productServlet")
public class ProductServlet extends BaseServlet {

	private static final long serialVersionUID = -8433296807122089937L;

	// 跳转到商品增加页面
	public String addUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1、添加商品的时候要把系统中的商品分类查出来
		List<ProductType> productTypes = productTypeService.queryFirstLevel();
		req.setAttribute("productTypes", productTypes);
		return "f:/WEB-INF/pages/product/addUI.jsp";
	}

	// 跳转到商品列表
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1、查询出来商品的一级分类
		List<ProductType> productTypes = productTypeService.queryFirstLevel();
		req.setAttribute("productTypes", productTypes);
		
		// 2、列出来所有的图书
		List<Product> products = productService.queryProducts();
		req.setAttribute("products", products);

		return "f:/WEB-INF/pages/product/list.jsp";
	}
	
	//-------------------  修改商品 ----------------
	
	// 1、跳转到商品修改页面
	public String editUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1、接收参数从数据库中查询商品
		String productId = req.getParameter("productId");
		ProductResult product = productService.queryProductAllInfoById(Integer.parseInt(productId));
		
		// 2、获取一级分类
		List<ProductType> productTypes = productTypeService.queryFirstLevel();
		
		req.setAttribute("product", product);
		req.setAttribute("productTypes", productTypes);
		return "f:/WEB-INF/pages/product/editUI.jsp";
	}
	
	// 2、修改商品
	//...........
	
	
	
	
}
