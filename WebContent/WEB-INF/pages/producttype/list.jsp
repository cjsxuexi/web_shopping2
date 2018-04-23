<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/privilege" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类列表</title>
</head>
<body>
	<table border="3" width="900px;" height="150px;" align="center" style="margin-top: 150px;">
		
		<tr>
			<td>分类id</td>
			<td>分类名称</td>
			<td>分类说明</td>
			<td>操作</td>
		</tr>

		<c:forEach items="${productTypes }" var="pt">
			<tr>
				<td>${pt.id }</td>
				<td>${pt.ptname }</td>
				<td>${pt.ptdesc }</td>
				<td>
					<p:privilege model="productTypeUI" url="addUI">
						<a href="${pageContext.request.contextPath }/sys/servlet/ui/productTypeServlet?method=addUI&parentId=${pt.id}">增加分类</a> <font color="red">|</font>
					</p:privilege>
					<p:privilege model="productTypeSys" url="delete">
						<a href="${pageContext.request.contextPath }/sys/servlet/handler/productTypeHandlerServlet?method=delete&id=${pt.id}">删除分类</a> <font color="red">|</font>
					</p:privilege>
					<p:privilege model="productTypeUI" url="updateUI">
						<a href="#">修改分类</a> <font color="red">|</font>
					</p:privilege>
					<a href="${pageContext.request.contextPath }/sys/servlet/ui/productTypeServlet?method=querySubProductTypes&parentId=${pt.id}"">查看分类</a> 
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<table border="1" align="center" style="margin-top: 30px;">
		<tr>
			<td>
				<p:privilege model="productTypeUI" url="addUI">
					<a href="${pageContext.request.contextPath }/sys/servlet/ui/productTypeServlet?method=addUI">添加一级分类</a>
				</p:privilege>
			</td>
		</tr>
	</table>
	
</body>
</html>