package com.zrgj.system.web.servlet.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Employee;
import com.zrgj.constant.MessageConstant;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.EmployeeService;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.RequestUtils;

/**
 * 	�û�����Servlet
*/
@WebServlet("/sys/servlet/handler/employeeHandlerServlet")
public class EmployeeHandlerServlet extends BaseServlet{

	private static final long serialVersionUID = 6061263420377398401L;

	private EmployeeService userService = BeanFactory.getBean(EmployeeService.class);
	
	// ����û�����
	public void addUser(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Employee user = RequestUtils.map2bean(request, Employee.class);
			userService.addUser(user);
			request.setAttribute("message", MessageConstant.OPERATOR_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageConstant.SYSTEM_ERROR);
		}
		request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, resp);
	}
	
	// Ϊ�û������ɫ
	public void dispatchRoleForUser(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1����ȡ�û�
			String userId = request.getParameter("userId");
			Employee user = userService.queryUserById(Integer.parseInt(userId));
			
			// 2����ȡ�� �û�����Ľ�ɫid����
			String[] roleIds = request.getParameterValues("roleIds");
			
			// 3��Ϊ�û������ɫ
			userService.dispatchRoleForUser(user, roleIds);
			
			request.setAttribute("message", MessageConstant.OPERATOR_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageConstant.SYSTEM_ERROR);
		}
		request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, resp);
	}

	// �����û���¼
	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Employee user = userService.queryUserByUsernameAndPassword(username, password);
		if(user != null){
			req.getSession().setAttribute("employee", user);
			req.getRequestDispatcher("/WEB-INF/pages/employee/systemPrivileges.jsp").forward(req, resp);
			return;
		}
		req.setAttribute("message", "�û������������");
		req.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(req, resp);
	}
}
