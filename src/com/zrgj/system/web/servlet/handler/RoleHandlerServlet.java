
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
 * 	��ɫ����Servlet
*/
@WebServlet("/sys/servlet/handler/roleHandlerServlet")
public class RoleHandlerServlet extends BaseServlet {

	private static final long serialVersionUID = -4354647418574389005L;

	// ��ɫservice
	private RoleService roleService = BeanFactory.getBean(RoleService.class);
	
	/**
	 * 	��ɫ���servlet
	*/
	public void addRole(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1�������������װ��bean����
			Role role = RequestUtils.map2bean(request, Role.class);
			// 2������service,��ӽ�ɫ
			roleService.addRole(role);
			
			// 3�����û�����
			request.setAttribute("message", MessageConstant.OPERATOR_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageConstant.SYSTEM_ERROR);
		}
		request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, resp);
	}
	
	/**
	 * 	Ϊ��ɫ����Ȩ��
	*/
	public void dispatchPrivilegeForHandler(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1�����ս�ɫ��id
			String roleId = request.getParameter("roleId");
			// 2��ͨ����ɫid��ѯ��ɫ
			Role role = roleService.queryRoleById(Integer.parseInt(roleId));
			
			// 3�������û�Ҫ�����Ȩ�޵�ֵ
			String[] privilegeIds = request.getParameterValues("privilegeIds");
			
			// 4��Ϊ��ɫ����Ȩ��
			roleService.dispatchPrivilegeForRole(role, privilegeIds);
			
			request.setAttribute("message", MessageConstant.OPERATOR_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageConstant.SYSTEM_ERROR);
		}
		request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, resp);
	}
}
