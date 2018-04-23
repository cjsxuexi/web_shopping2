<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>为角色分配权限页面</title>
</head>
<body>
	
	<table border="1" align="center" width="300px;">
		<tr>
			<td>
				当前角色 : ${role.rname }
			</td>
		</tr>
	</table>
	
	<table border="1" align="center" width="600px;" style="margin-top: 30px;">
		<tr>
			<td>
				当前角色已有的权限 : 
			</td>
			
			<td>
				<table>
					<c:if test="${not empty role.privileges }">
						<c:forEach items="${role.privileges }" var="p">
							<tr>
								<td>${p.pname }</td>
							</tr>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty role.privileges }">
							<tr><td><font color="blue">当前角色没有任何权限</font></td></tr>
					</c:if>
				</table>
			</td>
			
		</tr>
	</table>
	
	<table border="1" align="center" width="600px;" style="margin-top: 30px;">
		<tr>
			<td>
				当前系统中所有的权限 :
			</td>
			<td>
				<form action="${pageContext.request.contextPath }/sys/servlet/handler/roleHandlerServlet" method="post">
					<input type="hidden" name="method" value="dispatchPrivilegeForHandler">
					<input type="hidden" name="roleId" value="${role.id }">
					<table>
						<c:if test="${not empty allPrivileges }">
							
								<c:forEach items="${allPrivileges }" var="p">
									<tr>
										<td>
											<input type="checkbox" name="privilegeIds" value="${p.id }"/>${p.pname }
										</td>
											
									</tr>
								</c:forEach>
						</c:if>
						
						<c:if test="${empty allPrivileges }">
							<tr>
								<td><font color="blue">当前系统中没有一个权限</font> </td>
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