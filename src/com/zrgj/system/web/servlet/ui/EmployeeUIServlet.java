package com.zrgj.system.web.servlet.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Employee;
import com.zrgj.bean.Role;
import com.zrgj.constant.MessageConstant;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.EmployeeService;
import com.zrgj.service.RoleService;
import com.zrgj.system.web.servlet.BaseServlet;

/**
 * 用户UIServlet 主要作用就是：负责url地址的跳转
 */
@WebServlet("/sys/servlet/ui/employeeUIServlet")
public class EmployeeUIServlet extends BaseServlet {

	private static final long serialVersionUID = -1831819300145928359L;

	private EmployeeService userService = BeanFactory.getBean(EmployeeService.class);
	
	private RoleService roleService = BeanFactory.getBean(RoleService.class);

	// 查询所有的用户
	public void execute(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		try {
			List<Employee> users = userService.queryUsers();
			request.setAttribute("users", users);
			request.getRequestDispatcher("/WEB-INF/pages/employee/userList.jsp").forward(request, resp);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageConstant.SYSTEM_ERROR);
			request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, resp);
		}
	}

	// 添加用户UI
	public void addUserUIServlet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/employee/addUserUIServlet.jsp").forward(request, resp);
	}
	
	// 为用户分配角色ui
	public void dispatchRoleForUser(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 1、获取用户id
			String userId = request.getParameter("userId");
			
			// 2、根据用户id查询用户
			Employee user = userService.queryUserById(Integer.parseInt(userId));
			
			// 3、根据用户id查询此用户当前所有的角色
			
			// 4、查询系统中所有的角色
			List<Role> roles = roleService.queryRoles();
			
			// 5、跳转到页面
			request.setAttribute("user", user);
			request.setAttribute("allRoles", roles);
			
			request.getRequestDispatcher("/WEB-INF/pages/employee/dispatchRoleForUser.jsp").forward(request, resp);
			
		} catch (Exception e) {
			request.setAttribute("message", MessageConstant.SYSTEM_ERROR);
			request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, resp);
		}
	}
	
	//------------------------------------------
}
