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
 *	用户处理的Servlet 
*/
@WebServlet("/front/handler/userHandlerServlet")
public class UserHandlerServlet extends BaseServlet {

	private static final long serialVersionUID = -3693111168676843348L;

	// 表示用户注册
	public String register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1、接收用户的数据
			User user = RequestUtils.map2bean(req, User.class);
			
			// 2、注册用户
			userService.register(user);
			
			// 3、跳转到登录页面
			return "r:"+WebUtils.getContextPath(req)+"/front/ui/frontIndexServlet?method=body";
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "注册失败,服务器忙...");
			return "r:"+WebUtils.getContextPath(req)+"/front/ui/userServlet?method=registerUI";
		}
	}
	
	// 处理用户登录
	public String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1、接收用户的数据
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			// 2、注册用户
			User user = userService.login(username,password);
			if(user != null){
				// 用户登录成功,放入session
				WebUtils.setUser(req, user);
			}
			// 3、跳转到登录页面
			return "r:"+WebUtils.getContextPath(req)+"/front/ui/frontIndexServlet?method=header";
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "注册失败,服务器忙...");
			return "r:"+WebUtils.getContextPath(req)+"/front/ui/userServlet?method=registerUI";
		}
	}
	
	// 用户注销
	public String logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 注销
		WebUtils.sessionValidate(req);
		
		return "r:"+WebUtils.getContextPath(req)+"/front/ui/frontIndexServlet?method=header";
	}
}
