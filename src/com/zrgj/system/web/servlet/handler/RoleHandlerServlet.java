
package com.zrgj.system.web.servlet.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Role;
import com.zrgj.constant.MessageConstant;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.RoleService;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.RequestUtils;

/**
 * 	角色处理Servlet
*/
@WebServlet("/sys/servlet/handler/roleHandlerServlet")
public class RoleHandlerServlet extends BaseServlet {

	private static final long serialVersionUID = -4354647418574389005L;

	// 角色service
	private RoleService roleService = BeanFactory.getBean(RoleService.class);
	
	/**
	 * 	角色添加servlet
	*/
	public void addRole(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1、将请求参数封装成bean对象
			Role role = RequestUtils.map2bean(request, Role.class);
			// 2、调用service,添加角色
			roleService.addRole(role);
			
			// 3、给用户反馈
			request.setAttribute("message", MessageConstant.OPERATOR_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageConstant.SYSTEM_ERROR);
		}
		request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, resp);
	}
	
	/**
	 * 	为角色分配权限
	*/
	public void dispatchPrivilegeForHandler(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1、接收角色的id
			String roleId = request.getParameter("roleId");
			// 2、通过角色id查询角色
			Role role = roleService.queryRoleById(Integer.parseInt(roleId));
			
			// 3、接收用户要分配的权限的值
			String[] privilegeIds = request.getParameterValues("privilegeIds");
			
			// 4、为角色分配权限
			roleService.dispatchPrivilegeForRole(role, privilegeIds);
			
			request.setAttribute("message", MessageConstant.OPERATOR_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageConstant.SYSTEM_ERROR);
		}
		request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, resp);
	}
}
