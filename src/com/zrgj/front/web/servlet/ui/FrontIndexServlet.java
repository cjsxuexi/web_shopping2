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
 * ǰ�˵���ҳServlet
 */
@WebServlet("/front/ui/frontIndexServlet")
public class FrontIndexServlet extends BaseServlet {

	private static final long serialVersionUID = 9107812886739634338L;

	/*
	 * �����������ҳ
	 */
	public String index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/front/index.jsp";
	}

	/*
	 * �����ͷ
	 */
	public String header(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/front/header.jsp";
	}

	/**
	 * ������м�����
	 */
	public String body(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1����ѯ���еķ���
		List<ProductType> productTypes = productTypeService.queryProductsByDeep();
		req.setAttribute("productTypes", productTypes);

		// 0�����յ��û�ѡ�����Ʒ����id
		String productTypeId = req.getParameter("productTypeId");

		String whereSQL = null;
		List<Object> whereParams = null;
		if (!StringUtils.isEmpty(productTypeId)) {
			whereSQL = " producttype_id=? ";
			whereParams = new ArrayList<Object>();
			whereParams.add(Integer.parseInt(productTypeId));
		}

		// 2����ҳ��ʾ��ǰ��Ʒ�б�[���Ը��ݲ�ѯ������ҳ��ѯ��](��ǰ��Ʒ�б�����ݻ�ȡ�������ڵ�ǰҳ)
		String currentPageStr = req.getParameter("pageNum");
		Integer currentPage = StringUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		PageView<Product> page = productService.queryPageDataWithWhere(currentPage, whereSQL, whereParams);

		req.setAttribute("page", page);
		return "f:/WEB-INF/front/body.jsp";
	}

}
