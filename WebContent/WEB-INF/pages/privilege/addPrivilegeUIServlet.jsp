<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户添加权限</title>
</head>
<body>
	
	<form action="${pageContext.request.contextPath }/sys/servlet/handler/privilegeHandlerServlet" method="post">
		<table border="3" align="center" width="600px;" style="margin-top: 100px;">
			
			<tr>
				<td>
					<input type="hidden" name="method" value="addPrivilege"/>
				</td>
			</tr>	
			<tr>
				<td>权限名称:<input type="text" name="pname"> <br/></td>
			</tr>
			<tr>
				<td>权限说明:<textarea rows="10" cols="30" name="pdesc"></textarea> <br/></td>
			</tr>
			<tr>
				<td>
					是否公共权限:<input type="radio" name="piscommon" value="0"/> 否
							 <input type="radio" name="piscommon" value="1"/> 是
				</td>
			</tr>
			<tr>
				<td>
					权限uri:<input type="text" name="puri"/>
				</td>
			</tr>
			<tr>
				<td>
					权限所属模块:<input type="text" name="pmodel" />
				</td>
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
