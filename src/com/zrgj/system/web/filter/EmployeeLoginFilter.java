package com.zrgj.system.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Employee;
import com.zrgj.utils.WebUtils;

// 解决POST乱码问题
public class EmployeeLoginFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		if(employee == null){
			response.sendRedirect(WebUtils.getContextPath(request)+"/sys/servlet/handler/employeeHandlerServlet?method=loginUI");
			return;
		}
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		
	}
}


