package com.zrgj.system.web.servlet.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Privilege;
import com.zrgj.constant.MessageConstant;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.PrivilegeService;
import com.zrgj.system.web.servlet.BaseServlet;

/**
 *	权限列表UIServlet 
*/
@WebServlet("/sys/servlet/ui/privilegeListUIServlet")
public class PrivilegeListUIServlet extends BaseServlet{

	private static final long serialVersionUID = -4498055422486209998L;

	private PrivilegeService privilegeService = BeanFactory.getBean(PrivilegeService.class);
	
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			List<Privilege> privileges = privilegeService.queryPrivileges();
			req.setAttribute("privileges", privileges);
			req.getRequestDispatcher("/WEB-INF/pages/privilege/privilegeList.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", MessageConstant.SYSTEM_ERROR);
			req.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(req, resp);
		}
	}
	
}
