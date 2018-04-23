package com.zrgj.system.web.servlet.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zrgj.bean.ProductType;
import com.zrgj.exception.service.ProductTypeContainsSubTypeException;
import com.zrgj.permission.Permission;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.RequestUtils;
import com.zrgj.utils.StringUtils;

/**
 * 	分类处理Servlet
*/
@WebServlet("/sys/servlet/handler/productTypeHandlerServlet")
public class ProductTypeHandlerServlet extends BaseServlet {

	private static final long serialVersionUID = 6631756538618904957L;

	/*
	 * 	添加分类
	*/
	@Permission(model="productTypeSys",url="add")
	public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ProductType productType = RequestUtils.map2bean(req, ProductType.class);
			
			String productTypeParentId = req.getParameter("productTypeParentId");
			if(!StringUtils.isEmpty(productTypeParentId)){
				
				// 表示的是查询当前要添加的二级分类的一级分类是谁
				ProductType parent = new ProductType();
				parent.setId(Integer.parseInt(productTypeParentId));
				productType.setParent(parent);
			}
			productTypeService.addProductType(productType);
			req.setAttribute("message", "添加分类成功");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "添加失败");
		}
		return "f:/WEB-INF/pages/message.jsp";
	}
	
	
	
	/**
	 * 	根据分类id查询子分类
	*/
	public void querySubProductTypeById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		resp.setContentType("application/json;charset=utf-8");
		
		String parentTypeId = req.getParameter("productTypeId");
		List<ProductType> subProductTypes = productTypeService.querySubProductTypes(Integer.parseInt(parentTypeId));
		
		Gson gson = new Gson();
		String result = gson.toJson(subProductTypes);
		
		resp.getWriter().write(result);
	}
	
	/**
	 * 根据分类的id删除分类
	*/
	@Permission(model="productTypeSys",url="delete")
	public String delete(HttpServletRequest request,HttpServletResponse resp){
		
		try {
			// 1、首先要获取到分类的id
			String id = request.getParameter("id");
			
			// 2、拿着这个分类去查询此分类下面是否还有分类,如果有分类,就不能删除
			productTypeService.deleteSubTypeByParentId(Integer.parseInt(id));
			request.setAttribute("message", "删除分类成功");
		} catch (ProductTypeContainsSubTypeException e) {
			request.setAttribute("message", "此分类下面包含子分类,不能删除");
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "系统开小差了,请稍后...");
		}
		return "f:/WEB-INF/pages/message.jsp";
	}
	
	
}
