<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限列表页面</title>
</head>
<body>

	<table border="3" width="900px;" height="150px;" align="center" style="margin-top: 150px;">
		
		<tr>
			<td>权限id</td>
			<td>权限名称</td>
			<td>权限说明</td>
			<td>是否公共权限</td>
			<td>权限uri</td>
			<td>权限所属模块</td>
			<td>操作</td>
		</tr>

		<c:forEach items="${privileges }" var="privilege">
			<tr>
				<td>${privilege.id }</td>
				<td>${privilege.pname }</td>
				<td>${privilege.pdesc }</td>
				<td>
					<c:if test="${privilege.piscommon }">
						<font color="blue">是</font>
					</c:if>
					<c:if test="${!privilege.piscommon }">
						<font color="red">否</font>
					</c:if>
				</td>
				<td>${privilege.puri }</td>
				<td>${privilege.pmodel }</td>
				<td>
					<a href="#">删除权限</a> <font color="red">|</font>
					<a href="#">修改权限</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<table border="1" align="center" style="margin-top: 30px;">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath }/sys/servlet/ui/addPrivilegeUIServlet?method=addPrivilegeUIServlet">添加权限</a>
			</td>
		</tr>
	</table>
	
</body>
</html>