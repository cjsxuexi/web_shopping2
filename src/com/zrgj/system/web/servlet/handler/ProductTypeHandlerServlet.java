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
 * 	���ദ��Servlet
*/
@WebServlet("/sys/servlet/handler/productTypeHandlerServlet")
public class ProductTypeHandlerServlet extends BaseServlet {

	private static final long serialVersionUID = 6631756538618904957L;

	/*
	 * 	��ӷ���
	*/
	@Permission(model="productTypeSys",url="add")
	public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ProductType productType = RequestUtils.map2bean(req, ProductType.class);
			
			String productTypeParentId = req.getParameter("productTypeParentId");
			if(!StringUtils.isEmpty(productTypeParentId)){
				
				// ��ʾ���ǲ�ѯ��ǰҪ��ӵĶ��������һ��������˭
				ProductType parent = new ProductType();
				parent.setId(Integer.parseInt(productTypeParentId));
				productType.setParent(parent);
			}
			productTypeService.addProductType(productType);
			req.setAttribute("message", "��ӷ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "���ʧ��");
		}
		return "f:/WEB-INF/pages/message.jsp";
	}
	
	
	
	/**
	 * 	���ݷ���id��ѯ�ӷ���
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
	 * ���ݷ����idɾ������
	*/
	@Permission(model="productTypeSys",url="delete")
	public String delete(HttpServletRequest request,HttpServletResponse resp){
		
		try {
			// 1������Ҫ��ȡ�������id
			String id = request.getParameter("id");
			
			// 2�������������ȥ��ѯ�˷��������Ƿ��з���,����з���,�Ͳ���ɾ��
			productTypeService.deleteSubTypeByParentId(Integer.parseInt(id));
			request.setAttribute("message", "ɾ������ɹ�");
		} catch (ProductTypeContainsSubTypeException e) {
			request.setAttribute("message", "�˷�����������ӷ���,����ɾ��");
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "ϵͳ��С����,���Ժ�...");
		}
		return "f:/WEB-INF/pages/message.jsp";
	}
	
	
}
