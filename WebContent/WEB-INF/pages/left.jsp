<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/privilege" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导航栏</title>
</head>
<body>
	
	<a href="${pageContext.request.contextPath }/sys/servlet/ui/productTypeServlet?method=addUI"
		target="right">分类管理</a><br/>
	
	<p:privilege model="productTypeUI" url="list">
		&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/sys/servlet/ui/productTypeServlet?method=list"
			target="right">分类列表</a>
	</p:privilege>
		 <br/><br/>
	
	<a href="${pageContext.request.contextPath }/servlet/ui/roleListUIServlet"
		target="right">商品管理</a> <br/>
		
	&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/sys/servlet/ui/productServlet?method=addUI"
		target="right">添加商品</a>
		 <br/>
	
	&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/sys/servlet/ui/productServlet?method=list"
		target="right">商品列表</a>
		 <br/><br/>
	
	<a href="${pageContext.request.contextPath }/servlet/ui/privilegeListUIServlet"
		target="right"
	>订单管理</a>	 <br/>
	&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/sys/servlet/ui/orderServlet?method=list"
		target="right">订单列表</a>
		<br/><br/>
	
	<a href="${pageContext.request.contextPath }/sys/servlet/ui/employeeUIServlet"
		target="right"
	>员工管理</a>	<br/>
	&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/sys/servlet/ui/employeeUIServlet"
		target="right">员工列表</a>
		<br/><br/>
		
	<a href="${pageContext.request.contextPath }/sys/servlet/ui/roleListUIServlet"
		target="right"
	>角色管理</a>	<br/>
	
	&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/sys/servlet/ui/roleListUIServlet"
		target="right">角色列表</a>
		<br/><br/>
	
	<a href="${pageContext.request.contextPath }/sys/servlet/ui/privilegeListUIServlet"
		target="right"
	>权限管理</a>	<br/>
	&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/sys/servlet/ui/privilegeListUIServlet"
		target="right">权限列表</a>
		<br/><br/>
	
	<a href="${pageContext.request.contextPath }/servlet/ui/privilegeListUIServlet"
		target="right"
	>日志管理</a>	<br/><br/>
	
</body>
</html>