<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物系统</title>
</head>

<frameset border="5" rows="20%,*">
	<frame frameborder="5" name="top" src="${pageContext.request.contextPath }/sys/servlet/ui/indexServlet?method=top">
	<frameset border="5" cols="20%,*">
		<frame frameborder="5" name="left" src="${pageContext.request.contextPath }/sys/servlet/ui/indexServlet?method=left">
		<frame frameborder="5" name="right" src="${pageContext.request.contextPath }/sys/servlet/ui/indexServlet?method=right">
	</frameset>
</frameset>

</html>