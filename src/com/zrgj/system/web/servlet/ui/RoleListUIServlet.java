package com.zrgj.system.web.servlet.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Role;
import com.zrgj.constant.MessageConstant;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.RoleService;
import com.zrgj.system.web.servlet.BaseServlet;

/**
 *	角色列表UIServlet 
*/
@WebServlet("/sys/servlet/ui/roleListUIServlet")
public class RoleListUIServlet extends BaseServlet{

	private static final long serialVersionUID = -4498055422486209998L;

	private RoleService roleService = BeanFactory.getBean(RoleService.class);
	
	// 列出所有的角色
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			List<Role> roles = roleService.queryRoles();
			req.setAttribute("roles", roles);
			req.getRequestDispatcher("/WEB-INF/pages/role/roleList.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", MessageConstant.SYSTEM_ERROR);
			req.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(req, resp);
		}
	}
	
}
