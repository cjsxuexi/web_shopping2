<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加角色</title>
</head>
<body>
	
	<form action="${pageContext.request.contextPath }/sys/servlet/handler/roleHandlerServlet" method="post">
		<table border="3" align="center" width="600px;" style="margin-top: 100px;">
			
			<tr>
				<td>
					<input type="hidden" name="method" value="addRole"/>
				</td>
			</tr>	
			<tr>
				<td>角色名称:<input type="text" name="rname"> <br/></td>
			</tr>
			<tr>
				<td>角色说明:<textarea rows="10" cols="30" name="rdesc"></textarea> <br/></td>
			</tr>
			<tr>
				<td align="center">
					<input type="submit" value="提交"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
