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
 * ��ɫUIServlet ��Ҫ���þ��ǣ�����url��ַ����ת
 */
@WebServlet("/sys/servlet/ui/roleUIServlet")
public class RoleUIServlet extends BaseServlet {

	private static final long serialVersionUID = -3644567394059095085L;
	
	// ��ɫ����
	private RoleService roleService = BeanFactory.getBean(RoleService.class);
	
	// Ȩ�޷���
	private PrivilegeService privilegeService = BeanFactory.getBean(PrivilegeService.class);

	// ��ӽ�ɫui����
	public void addRoleUIServlet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/pages/role/addRoleUIServlet.jsp").forward(req, resp);
	}

	// Ϊ��ɫ����Ȩ��
	public void dispatchPrivilegeForRole(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			// 1��Ҫ��ѯ������ǰΪ�Ǹ���ɫ����Ȩ��---Role
			String roleId = request.getParameter("roleId");
			Role role = roleService.queryRoleById(Integer.parseInt(roleId));
			// 2����ѯ��ǰ��ɫ���е�Ȩ��
			// List<Privilege> privileges = privilegeService.queryPrivilegesByRoleId(role.getId());
			// 3����ѯ��ǰϵͳ�����е�Ȩ��
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
