package com.zrgj.system.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.bean.Employee;
import com.zrgj.bean.Privilege;
import com.zrgj.factory.BeanFactory;
import com.zrgj.permission.Permission;
import com.zrgj.service.EmployeeService;
import com.zrgj.service.OrderService;
import com.zrgj.service.PrivilegeService;
import com.zrgj.service.ProductService;
import com.zrgj.service.ProductTypeService;
import com.zrgj.service.UserService;
import com.zrgj.utils.StringUtils;

/**
 * 	基类
*/
public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = -3615125649446194951L;

	protected ProductTypeService productTypeService = BeanFactory.getBean(ProductTypeService.class);
	
	protected ProductService productService = BeanFactory.getBean(ProductService.class);
	
	protected UserService userService = BeanFactory.getBean(UserService.class);
	
	protected OrderService orderService = BeanFactory.getBean(OrderService.class);
	
	private PrivilegeService privilegeService = BeanFactory.getBean(PrivilegeService.class);

	protected EmployeeService employeeService = BeanFactory.getBean(EmployeeService.class);
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.process(req,resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.process(req,resp);
	}

	// 处理请求
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String methodName = req.getParameter("method");
		
		if(StringUtils.isEmpty(methodName)){
			this.execute(req,resp);
		}else{
			try {
				Method method = this.getClass().getMethod(methodName, new Class[]{HttpServletRequest.class,
															 HttpServletResponse.class});
				
				// 1、首先判断方法上是否有此注解,如果没有此注解,则表示可以访问
				Permission permission = method.getAnnotation(Permission.class);
				if(permission == null){
					// 表示可以访问
					String url = (String) method.invoke(this, req,resp);
					if(!StringUtils.isEmpty(url)){
						String flag = url.split(":")[0];
						String directUrl = url.split(":")[1];
						if("f".equals(flag)){
							req.getRequestDispatcher(directUrl).forward(req, resp);
						}else if("r".equals(flag)){
							resp.sendRedirect(directUrl);
						}
					}
					return;
				}
				
				// 2、如果有注解,则要进行权限判断
				String model = permission.model();
				String uri = permission.url();
				
				// 3、判断此权限是否是一个公共的权限
				Privilege privilege = privilegeService.queryPrivilegeByURI(model,uri);
				if(privilege.isPiscommon()){
					// 表示公共的,则放行
					String url = (String) method.invoke(this, req,resp);
					if(!StringUtils.isEmpty(url)){
						String flag = url.split(":")[0];
						String directUrl = url.split(":")[1];
						if("f".equals(flag)){
							req.getRequestDispatcher(directUrl).forward(req, resp);
						}else if("r".equals(flag)){
							resp.sendRedirect(directUrl);
						}
					}
					return;
				}
				
				// 3、就是表示用户需要登录了
				Employee employee = (Employee) req.getSession().getAttribute("employee");
				
				// 3.1 表示用户没有登录
				if(employee == null){
					req.setAttribute("message", "您还没有登录");
					req.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(req, resp);
					return;
				}
				
				// 3.2 表示用户登录了
				Privilege p = new Privilege(uri,model);
				List<Privilege> privileges = employeeService.queryPrivilegesByUserId(employee.getId());
				if(privileges.contains(p)){
					// 表示公共的,则放行
					String url = (String) method.invoke(this, req,resp);
					if(!StringUtils.isEmpty(url)){
						String flag = url.split(":")[0];
						String directUrl = url.split(":")[1];
						if("f".equals(flag)){
							req.getRequestDispatcher(directUrl).forward(req, resp);
						}else if("r".equals(flag)){
							resp.sendRedirect(directUrl);
						}
					}
					return;
				}else{
					req.setAttribute("message", "很抱歉,您没有此权限");
					req.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(req, resp);
					return;
				}
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	// 如果用户没有传递方法名称,默认执行execute,这个方法没有任何实现,主要目的是为了子类重写
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
