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
 * 	����
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

	// ��������
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String methodName = req.getParameter("method");
		
		if(StringUtils.isEmpty(methodName)){
			this.execute(req,resp);
		}else{
			try {
				Method method = this.getClass().getMethod(methodName, new Class[]{HttpServletRequest.class,
															 HttpServletResponse.class});
				
				// 1�������жϷ������Ƿ��д�ע��,���û�д�ע��,���ʾ���Է���
				Permission permission = method.getAnnotation(Permission.class);
				if(permission == null){
					// ��ʾ���Է���
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
				
				// 2�������ע��,��Ҫ����Ȩ���ж�
				String model = permission.model();
				String uri = permission.url();
				
				// 3���жϴ�Ȩ���Ƿ���һ��������Ȩ��
				Privilege privilege = privilegeService.queryPrivilegeByURI(model,uri);
				if(privilege.isPiscommon()){
					// ��ʾ������,�����
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
				
				// 3�����Ǳ�ʾ�û���Ҫ��¼��
				Employee employee = (Employee) req.getSession().getAttribute("employee");
				
				// 3.1 ��ʾ�û�û�е�¼
				if(employee == null){
					req.setAttribute("message", "����û�е�¼");
					req.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(req, resp);
					return;
				}
				
				// 3.2 ��ʾ�û���¼��
				Privilege p = new Privilege(uri,model);
				List<Privilege> privileges = employeeService.queryPrivilegesByUserId(employee.getId());
				if(privileges.contains(p)){
					// ��ʾ������,�����
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
					req.setAttribute("message", "�ܱ�Ǹ,��û�д�Ȩ��");
					req.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(req, resp);
					return;
				}
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	// ����û�û�д��ݷ�������,Ĭ��ִ��execute,�������û���κ�ʵ��,��ҪĿ����Ϊ��������д
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
