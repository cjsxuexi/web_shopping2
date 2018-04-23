package com.zrgj.front.web.servlet.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.PageView;
import com.zrgj.bean.Product;
import com.zrgj.bean.ProductType;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.StringUtils;

/**
 * 前端的首页Servlet
 */
@WebServlet("/front/ui/frontIndexServlet")
public class FrontIndexServlet extends BaseServlet {

	private static final long serialVersionUID = 9107812886739634338L;

	/*
	 * 这个是整个首页
	 */
	public String index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/front/index.jsp";
	}

	/*
	 * 这个是头
	 */
	public String header(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/front/header.jsp";
	}

	/**
	 * 这个是中间内容
	 */
	public String body(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1、查询所有的分类
		List<ProductType> productTypes = productTypeService.queryProductsByDeep();
		req.setAttribute("productTypes", productTypes);

		// 0、接收到用户选择的商品分类id
		String productTypeId = req.getParameter("productTypeId");

		String whereSQL = null;
		List<Object> whereParams = null;
		if (!StringUtils.isEmpty(productTypeId)) {
			whereSQL = " producttype_id=? ";
			whereParams = new ArrayList<Object>();
			whereParams.add(Integer.parseInt(productTypeId));
		}

		// 2、分页显示当前商品列表[可以根据查询条件分页查询啦](当前商品列表的数据获取是依赖于当前页)
		String currentPageStr = req.getParameter("pageNum");
		Integer currentPage = StringUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		PageView<Product> page = productService.queryPageDataWithWhere(currentPage, whereSQL, whereParams);

		req.setAttribute("page", page);
		return "f:/WEB-INF/front/body.jsp";
	}

}
