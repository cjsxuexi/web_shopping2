<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表页面</title>
</head>
<body>

	<table border="3" width="900px;" height="100px;" align="center" style="margin-top: 100px;">
		
		<tr>
			<td>用户id</td>
			<td>用户名</td>
			<td>用户密码</td>
			<td>操作</td>
		</tr>

		<c:forEach items="${users }" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.username }</td>
				<td>${user.password }</td>
				<td>
					<a href="${pageContext.request.contextPath }/sys/servlet/ui/employeeUIServlet?method=dispatchRoleForUser&userId=${user.id}">
						为用户分配角色
					</a> 
					<font color="red">|</font>
					<a href="#">删除用户</a> <font color="red">|</font>
					<a href="#">修改用户</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<table border="1" align="center" style="margin-top: 30px;">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath }/sys/servlet/ui/employeeUIServlet?method=addUserUIServlet">添加用户</a>
			</td>
		</tr>
	</table>
	
</body>
</html>