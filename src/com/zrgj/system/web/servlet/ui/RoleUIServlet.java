package com.zrgj.system.web.servlet.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Privilege;
import com.zrgj.bean.Role;
import com.zrgj.constant.MessageConstant;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.PrivilegeService;
import com.zrgj.service.RoleService;
import com.zrgj.system.web.servlet.BaseServlet;

/**
 * 角色UIServlet 主要作用就是：负责url地址的跳转
 */
@WebServlet("/sys/servlet/ui/roleUIServlet")
public class RoleUIServlet extends BaseServlet {

	private static final long serialVersionUID = -3644567394059095085L;
	
	// 角色服务
	private RoleService roleService = BeanFactory.getBean(RoleService.class);
	
	// 权限服务
	private PrivilegeService privilegeService = BeanFactory.getBean(PrivilegeService.class);

	// 添加角色ui方法
	public void addRoleUIServlet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/pages/role/addRoleUIServlet.jsp").forward(req, resp);
	}

	// 为角色分配权限
	public void dispatchPrivilegeForRole(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			// 1、要查询出来当前为那个角色分配权限---Role
			String roleId = request.getParameter("roleId");
			Role role = roleService.queryRoleById(Integer.parseInt(roleId));
			// 2、查询当前角色已有的权限
			// List<Privilege> privileges = privilegeService.queryPrivilegesByRoleId(role.getId());
			// 3、查询当前系统中所有的权限
			List<Privilege> privileges = privilegeService.queryPrivileges();
			
			request.setAttribute("role", role);
			request.setAttribute("allPrivileges", privileges);
			
			request.getRequestDispatcher("/WEB-INF/pages/role/dispatchPrivilegeForRole.jsp").forward(request, resp);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageConstant.SYSTEM_ERROR);
			request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, resp);
		}
	}
}
