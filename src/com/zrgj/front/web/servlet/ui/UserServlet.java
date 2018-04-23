package com.zrgj.front.web.servlet.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.system.web.servlet.BaseServlet;

/**
 *	�û�ǰ��Servlet 
*/
@WebServlet("/front/ui/userServlet")
public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = -7951300321173041747L;
	
	// ��ת��ע��ҳ��
	public String registerUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/front/register.jsp";
	}
	
	// ��ת����¼ҳ��
	public String loginUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/front/header.jsp";
	}
}
