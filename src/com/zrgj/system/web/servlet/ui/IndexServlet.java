
package com.zrgj.system.web.servlet.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.system.web.servlet.BaseServlet;

/**
 * 首页Servlet
 */
@WebServlet("/sys/servlet/ui/indexServlet")
public class IndexServlet extends BaseServlet {

	private static final long serialVersionUID = -8394290768651760947L;

	// 后台的入口
	public String index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/index.jsp";
	}

	// 顶部
	public String top(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/pages/top.jsp";
	}

	// 顶部
	public String left(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/pages/left.jsp";
	}

	// 顶部
	public String right(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "f:/WEB-INF/pages/right.jsp";
	}
}
