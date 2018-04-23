package com.zrgj.front.web.servlet.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.User;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.RequestUtils;
import com.zrgj.utils.WebUtils;

/**
 *	�û������Servlet 
*/
@WebServlet("/front/handler/userHandlerServlet")
public class UserHandlerServlet extends BaseServlet {

	private static final long serialVersionUID = -3693111168676843348L;

	// ��ʾ�û�ע��
	public String register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1�������û�������
			User user = RequestUtils.map2bean(req, User.class);
			
			// 2��ע���û�
			userService.register(user);
			
			// 3����ת����¼ҳ��
			return "r:"+WebUtils.getContextPath(req)+"/front/ui/frontIndexServlet?method=body";
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "ע��ʧ��,������æ...");
			return "r:"+WebUtils.getContextPath(req)+"/front/ui/userServlet?method=registerUI";
		}
	}
	
	// �����û���¼
	public String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1�������û�������
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			// 2��ע���û�
			User user = userService.login(username,password);
			if(user != null){
				// �û���¼�ɹ�,����session
				WebUtils.setUser(req, user);
			}
			// 3����ת����¼ҳ��
			return "r:"+WebUtils.getContextPath(req)+"/front/ui/frontIndexServlet?method=header";
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "ע��ʧ��,������æ...");
			return "r:"+WebUtils.getContextPath(req)+"/front/ui/userServlet?method=registerUI";
		}
	}
	
	// �û�ע��
	public String logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// ע��
		WebUtils.sessionValidate(req);
		
		return "r:"+WebUtils.getContextPath(req)+"/front/ui/frontIndexServlet?method=header";
	}
}
