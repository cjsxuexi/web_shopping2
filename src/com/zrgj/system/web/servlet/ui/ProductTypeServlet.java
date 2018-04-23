package com.zrgj.system.web.servlet.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.ProductType;
import com.zrgj.permission.Permission;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.StringUtils;

/**
 *	商品分类Servlet 
*/
@WebServlet("/sys/servlet/ui/productTypeServlet")
public class ProductTypeServlet extends BaseServlet {

	private static final long serialVersionUID = 6264530462254688259L;

	// 跳转到商品分类添加页面
	@Permission(model="productTypeUI",url="addUI")
	public String addUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parentId = req.getParameter("parentId");
		if(!StringUtils.isEmpty(parentId)){
			ProductType productType = productTypeService.queryProductTypeById(Integer.parseInt(parentId));
			req.setAttribute("productType", productType);
		}
		
		return "f:/WEB-INF/pages/producttype/addUI.jsp";
	}
	
	// 商品的分类列表
	@Permission(model="productTypeUI",url="list")
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1、在跳转到分类添加页面之前要组装数据,首先要把一级分类查出来
		List<ProductType> productTypes = productTypeService.queryFirstLevel();
		req.setAttribute("productTypes", productTypes);
		return "f:/WEB-INF/pages/producttype/list.jsp";
	}
	
	// 根据一级分类查询下面的子分类
	public String querySubProductTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1、父分类的id
		String parentId = req.getParameter("parentId");
	
		// 2、根据父分类的id查询下面的子分类
		List<ProductType> productTypes = productTypeService.querySubProductTypes(Integer.parseInt(parentId));
		req.setAttribute("productTypes", productTypes);
		return "f:/WEB-INF/pages/producttype/list.jsp";
	}
}
