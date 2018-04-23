package com.zrgj.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.zrgj.bean.Employee;
import com.zrgj.bean.Privilege;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.EmployeeService;
import com.zrgj.service.PrivilegeService;

public class PermissionTag extends TagSupport {

	private static final long serialVersionUID = -4957739017305812083L;
	
	private PrivilegeService privilegeService = BeanFactory.getBean(PrivilegeService.class);
	
	protected EmployeeService employeeService = BeanFactory.getBean(EmployeeService.class);

	private String model;
	
	private String url;
	
	public int doStartTag() throws JspException {
		
		Privilege privilege = privilegeService.queryPrivilegeByURI(model,url);
		
		if(privilege == null || privilege.isPiscommon()){
			// 表示包含
			return TagSupport.EVAL_BODY_INCLUDE;
		}
		
		Employee employee = (Employee)this.pageContext.getSession().getAttribute("employee");
		
		// 3.1 表示用户没有登录
		if(employee == null){
			return TagSupport.SKIP_BODY;
		}
		
		List<Privilege> privileges = employeeService.queryPrivilegesByUserId(employee.getId());
		if(privileges.contains(new Privilege(url, model))){
			return TagSupport.EVAL_BODY_INCLUDE;
		}
		return TagSupport.SKIP_BODY;
	}

	//-----------------------------------------
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
