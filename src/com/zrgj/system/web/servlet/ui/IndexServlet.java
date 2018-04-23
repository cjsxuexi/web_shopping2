
package com.zrgj.system.web.servlet.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.system.web.servlet.BaseServlet;

/**
 * ��ҳServlet
 */
@WebServlet("/sys/servlet/ui/indexServlet")
public class IndexServlet extends BaseServlet {

	private static final long serialVersionUID = -8394290768651760947L;

	// ��̨�����
	public String index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/index.jsp";
	}

	// ����
	public String top(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/pages/top.jsp";
	}

	// ����
	public String left(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/pages/left.jsp";
	}

	// ����
	public String right(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/pages/right.jsp";
	}
}
