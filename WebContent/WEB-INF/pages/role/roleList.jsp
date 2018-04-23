<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表页面</title>
</head>
<body>

	<table border="3" width="900px;" height="150px;" align="center" style="margin-top: 100px;">
		
		<tr>
			<td>角色id</td>
			<td>角色名称</td>
			<td>角色说明</td>
			<td>操作</td>
		</tr>

		<c:forEach items="${roles }" var="role">
			<tr>
				<td>${role.id }</td>
				<td>${role.rname }</td>
				<td>${role.rdesc }</td>
				<td>
					<a href="${pageContext.request.contextPath }/sys/servlet/ui/roleUIServlet?method=dispatchPrivilegeForRole&roleId=${role.id}">
						为角色分配权限
					</a> 
					<font color="red">|</font>
					<a href="#">删除角色</a> <font color="red">|</font>
					<a href="#">修改角色</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<table border="1" align="center" style="margin-top: 30px;">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath }/sys/servlet/ui/roleUIServlet?method=addRoleUIServlet">添加角色</a>
			</td>
		</tr>
	</table>
	
</body>
</html>