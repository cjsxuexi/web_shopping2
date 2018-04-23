<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>为用户分配角色页面</title>
</head>
<body>
	<table border="1" align="center" width="300px;">
		<tr>
			<td>
				当前用户名 : ${user.username }
			</td>
		</tr>
	</table>
	
	<table border="1" align="center" width="600px;" style="margin-top: 30px;">
		<tr>
			<td>
				当前用户已有的角色 : 
			</td>
			
			<td>
				<table>
					<c:if test="${not empty user.roles }">
						<c:forEach items="${user.roles }" var="role">
							<tr>
								<td>${role.rname }</td>
							</tr>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty user.roles }">
							<tr><td><font color="blue">当前用户没有任何角色</font></td></tr>
					</c:if>
				</table>
			</td>
		</tr>
	</table>
	
	<table border="1" align="center" width="600px;" style="margin-top: 30px;">
		<tr>
			<td>
				当前系统中所有的角色 :
			</td>
			<td>
				<form action="${pageContext.request.contextPath }/sys/servlet/handler/employeeHandlerServlet" method="post">
					<input type="hidden" name="method" value="dispatchRoleForUser">
					<input type="hidden" name="userId" value="${user.id }">
					<table>
						<c:if test="${not empty allRoles }">
							
								<c:forEach items="${allRoles }" var="role">
									<tr>
										<td>
											<input type="checkbox" name="roleIds" value="${role.id}"/>${role.rname }
										</td>
											
									</tr>
								</c:forEach>
						</c:if>
						
						<c:if test="${empty allRoles }">
							<tr>
								<td><font color="blue">当前系统中没有一个角色</font> </td>
							</tr>
						</c:if>
					</table>
					<input type="submit" value="提交">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>