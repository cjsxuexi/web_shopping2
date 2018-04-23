<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品分类添加</title>
</head>
<body>
	
	<form action="${pageContext.request.contextPath }/sys/servlet/handler/productTypeHandlerServlet" method="post">
		<table border="3" align="center" width="600px;" style="margin-top: 100px;">
			
			<tr>
				<td>
					<input type="hidden" name="method" value="add"/>
				</td>
			</tr>
			<c:if test="${productType != null }">
				<tr>
					<td>一级分类:<input readonly="readonly" type="text" value=${productType.ptname }> <br/></td>
				</tr>
			</c:if>
			<tr>
				<td>分类名称:<input type="text" name="ptname"> <br/></td>
			</tr>
			<tr>
				<td>分类说明:<textarea rows="10" cols="30" name="ptdesc"></textarea> <br/></td>
			</tr>
			<input type="hidden" value="${productType.id }" name="productTypeParentId">
			<tr>
				<td align="center">
					<input type="submit" value="提交"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
