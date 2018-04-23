package com.zrgj.system.web.servlet.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Privilege;
import com.zrgj.constant.MessageConstant;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.PrivilegeService;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.RequestUtils;

/**
 *	权限管理Servlet 
*/
@WebServlet("/sys/servlet/handler/privilegeHandlerServlet")
public class PrivilegeHandlerServlet extends BaseServlet{

	private static final long serialVersionUID = -3567531265194994373L;
	
	// 权限服务
	private PrivilegeService privilegeService =  BeanFactory.getBean(PrivilegeService.class);

	/**
	 * 	添加权限
	*/
	public void addPrivilege(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1、接收用户请求过来的参数并封装成实体bean
		try {
			Privilege privilege = RequestUtils.map2bean(request, Privilege.class);
			privilegeService.addPrivilege(privilege);
			request.setAttribute("message", MessageConstant.OPERATOR_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageConstant.SYSTEM_ERROR);
		}
		request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, resp);
	}
}
