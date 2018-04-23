package com.zrgj.system.web.servlet.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Employee;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.WebUtils;

// 用户登录
@WebServlet("/sys/login")
public class EmployeeLoginServlet extends BaseServlet{

	private static final long serialVersionUID = 2835333550296594078L;

	// 跳转到用户登录页面
	public String loginUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/pages/employee/userLogin.jsp";
	}

	// 用户正在去登录
	public String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		Employee employee = employeeService.login(username, password);

		if (employee != null) {
			req.getSession().setAttribute("employee", employee);
			return "r:" + WebUtils.getContextPath(req) + "/sys/servlet/ui/indexServlet?method=index";
		}
		return "r:" + WebUtils.getContextPath(req) + "/sys/login?method=loginUI";
	}

}
