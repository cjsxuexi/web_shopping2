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

	// ��ת����Ʒ����ҳ��
	public String addUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1�������Ʒ��ʱ��Ҫ��ϵͳ�е���Ʒ��������
		List<ProductType> productTypes = productTypeService.queryFirstLevel();
		req.setAttribute("productTypes", productTypes);
		return "f:/WEB-INF/pages/product/addUI.jsp";
	}

	// ��ת����Ʒ�б�
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1����ѯ������Ʒ��һ������
		List<ProductType> productTypes = productTypeService.queryFirstLevel();
		req.setAttribute("productTypes", productTypes);
		
		// 2���г������е�ͼ��
		List<Product> products = productService.queryProducts();
		req.setAttribute("products", products);

		return "f:/WEB-INF/pages/product/list.jsp";
	}
	
	//-------------------  �޸���Ʒ ----------------
	
	// 1����ת����Ʒ�޸�ҳ��
	public String editUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1�����ղ��������ݿ��в�ѯ��Ʒ
		String productId = req.getParameter("productId");
		ProductResult product = productService.queryProductAllInfoById(Integer.parseInt(productId));
		
		// 2����ȡһ������
		List<ProductType> productTypes = productTypeService.queryFirstLevel();
		
		req.setAttribute("product", product);
		req.setAttribute("productTypes", productTypes);
		return "f:/WEB-INF/pages/product/editUI.jsp";
	}
	
	// 2���޸���Ʒ
	//...........
	
	
	
	
}
