<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>前台首页</title>
  </head>
  
  <frameset rows="25%,*">
  	<frame src="${pageContext.request.contextPath }/front/ui/frontIndexServlet?method=header" name="head">
  	<frame src="${pageContext.request.contextPath }/front/ui/frontIndexServlet?method=body" name="body">
  </frameset>

</html>
