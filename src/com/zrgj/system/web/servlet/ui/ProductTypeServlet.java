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
 *	��Ʒ����Servlet 
*/
@WebServlet("/sys/servlet/ui/productTypeServlet")
public class ProductTypeServlet extends BaseServlet {

	private static final long serialVersionUID = 6264530462254688259L;

	// ��ת����Ʒ�������ҳ��
	@Permission(model="productTypeUI",url="addUI")
	public String addUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parentId = req.getParameter("parentId");
		if(!StringUtils.isEmpty(parentId)){
			ProductType productType = productTypeService.queryProductTypeById(Integer.parseInt(parentId));
			req.setAttribute("productType", productType);
		}
		
		return "f:/WEB-INF/pages/producttype/addUI.jsp";
	}
	
	// ��Ʒ�ķ����б�
	@Permission(model="productTypeUI",url="list")
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1������ת���������ҳ��֮ǰҪ��װ����,����Ҫ��һ����������
		List<ProductType> productTypes = productTypeService.queryFirstLevel();
		req.setAttribute("productTypes", productTypes);
		return "f:/WEB-INF/pages/producttype/list.jsp";
	}
	
	// ����һ�������ѯ������ӷ���
	public String querySubProductTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1���������id
		String parentId = req.getParameter("parentId");
	
		// 2�����ݸ������id��ѯ������ӷ���
		List<ProductType> productTypes = productTypeService.querySubProductTypes(Integer.parseInt(parentId));
		req.setAttribute("productTypes", productTypes);
		return "f:/WEB-INF/pages/producttype/list.jsp";
	}
}
