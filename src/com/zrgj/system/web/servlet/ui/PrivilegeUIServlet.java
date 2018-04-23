package com.zrgj.system.web.servlet.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.system.web.servlet.BaseServlet;

/**
 *	添加用户权限UIServlet
 *		主要作用就是：负责url地址的跳转 
*/
@WebServlet("/sys/servlet/ui/addPrivilegeUIServlet")
public class PrivilegeUIServlet extends BaseServlet{

	private static final long serialVersionUID = -2800615905228080454L;
	
	// 添加用户权限ui方法
	public void addPrivilegeUIServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/privilege/addPrivilegeUIServlet.jsp").forward(req, resp);
	}
}
