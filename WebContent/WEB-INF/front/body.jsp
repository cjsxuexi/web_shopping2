<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'body.jsp' starting page</title>
    
  </head>
  
  <body style="text-align: center;" onload="init();">
    
    <div id="content" style="width: 840px">
    	
    	<div id="category" style="float: left;width: 200px;border: 1px solid red;text-align: left;height: 300px;margin-left: 150px">
    		分类列表：
    		<ul>
    		<c:forEach var="pt" items="${productTypes}">
    			<li>
    				${pt.ptname }<br/><br/>
    				<c:forEach items="${pt.subTypes }" var="subPt">
    					&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/front/ui/frontIndexServlet?method=body&productTypeId=${subPt.id }">${subPt.ptname }</a><br/><br/>
    				</c:forEach>
    			</li>
    		</c:forEach>
    		</ul>
    	</div>
    	
    	<div id="bookandpage" style="float: left;margin-left: 50px">
    	
    		<div id="books">
    			<c:forEach var="product" items="${page.datas}">
    				<div id="book">
    					<div id="image" style="float: left">
    						<img src="${pageContext.request.contextPath }/upload/${product.pimageUrl }" width="50px" height="30px;">
    					</div>
    					
    					<div id="bookinfo" style="float:left;text-align: left;">
    						<ul>
    							<li>${product.pname }</li>
    							<li>${product.pauthor }</li>
    							<li>${product.pprice }</li>
    							<li>
    								<a href="${pageContext.request.contextPath }/front/handler/buyProductServlet?method=buy&productId=${product.id}">购买</a>
    							</li>
    						</ul>
    					</div>
    					<div style="clear: both"></div>
    					<br/>
    				</div>
    			</c:forEach>
    		</div>
    		<!-- 作div浮动后，为了不影响后续页面的显示，一定要记住使用下面语句清除浮动效果 -->
    		<div style="clear: both"></div>
    		<div id="page">
    			当前第[${page.currentPage }]页 &nbsp; &nbsp;
    
			    <c:forEach var="pagenum" begin="${page.startPageNo}" end="${page.endPageNo}">
			    	[<a href="${pageContext.request.contextPath }/front/ui/frontIndexServlet?pageNum=${pagenum }&productTypeId=${param.productTypeId }&method=body">${pagenum }</a>]
			    </c:forEach>
			    &nbsp; &nbsp;
			    总共[${page.totalPage }]页，总[${page.totalRecored }]记录
    		</div>
    	
    	</div>
    </div>
    
  </body>
</html>

