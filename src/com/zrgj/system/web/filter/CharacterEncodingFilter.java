package com.zrgj.system.web.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 解决POST乱码问题
public class CharacterEncodingFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpServletRequest proxyRequest = (HttpServletRequest)Proxy.newProxyInstance
								(this.getClass().getClassLoader(),
								new Class[]{HttpServletRequest.class}, 
								new InvocationHandler() {
									@Override
									public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
										
										String methodName = method.getName();
										
										if("getParameter".equalsIgnoreCase(methodName)){
											
											Object value = method.invoke(request, args);
											if(value == null){
												return null;
											}
											
											String requestMethod = request.getMethod();
											if("get".equalsIgnoreCase(requestMethod)){
												
												String inputValue = (String)value;
												
												return new String(inputValue.getBytes("ISO-8859-1"),request.getCharacterEncoding());
											}else{
												return value;
											}
										}else{
											return method.invoke(request, args);
										}
									}
								}
							);
		
		chain.doFilter(proxyRequest
				, response);
	}
	
	@Override
	public void destroy() {
		
	}
}


