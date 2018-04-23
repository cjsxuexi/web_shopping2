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
		
		// 1��ǿ������ת��
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		// 2��ȥ�ж�session�Ƿ��û� 
		User user = WebUtils.getUser(request);
		if(user == null){
			// ��ʾ�û�û�е�¼
			request.setAttribute("message", "���漰�����û����еĲ���,���¼...");
			request.getRequestDispatcher("/WEB-INF/front/message.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
