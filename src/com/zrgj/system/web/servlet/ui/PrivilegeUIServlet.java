package com.zrgj.system.web.servlet.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.system.web.servlet.BaseServlet;

/**
 *	����û�Ȩ��UIServlet
 *		��Ҫ���þ��ǣ�����url��ַ����ת 
*/
@WebServlet("/sys/servlet/ui/addPrivilegeUIServlet")
public class PrivilegeUIServlet extends BaseServlet{

	private static final long serialVersionUID = -2800615905228080454L;
	
	// ����û�Ȩ��ui����
	public void addPrivilegeUIServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/privilege/addPrivilegeUIServlet.jsp").forward(req, resp);
	}
}
