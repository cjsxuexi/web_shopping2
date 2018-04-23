package com.zrgj.front.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.User;
import com.zrgj.utils.WebUtils;

public class ValidateUserLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		// 1、强制类型转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		// 2、去判断session是否用户 
		User user = WebUtils.getUser(request);
		if(user == null){
			// 表示用户没有登录
			request.setAttribute("message", "您涉及到了用户敏感的操作,请登录...");
			request.getRequestDispatcher("/WEB-INF/front/message.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
