<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>购物车显示列表</title>
  </head>
  
  <body style="text-align: center;">
  		<br/>
  		<h2>购物列表</h2>
  		
  		<c:if test="${empty sessionScope.cart.cartItems }">
  			<h2 align="center"><font color="red">长的帅的人都购物满满......</font></h2>
  		</c:if>
  		
  		<c:if test="${not empty sessionScope.cart.cartItems }">
	    	<table width="80%" frame="border" border="1" align="center">
	    		<tr>
	    			<td>商品id</td>
	    			<td>商品名称</td>
	    			<td>作者</td>
	    			<td>单价</td>
	    			<td>数量</td>
	    			<td>小计</td>
	    			<td>
	    				<a href="#">删除</a>
	    			</td>
	    		</tr>
	    		
	    		<c:forEach var="item" items="${sessionScope.cart.cartItems}">
	    			<tr>
	    				<td>${item.product.id }</td>
		    			<td>${item.product.pname }</td>
		    			<td>${item.product.pauthor }</td>
		    			<td>${item.product.pprice }</td>
		    			<td>${item.quantity }</td>
		    			<td>${item.subPrice }</td>
		    			<td>
		    				<a href="${pageContext.request.contextPath }/front/handler/cartServlet?method=deleteByProductId&productId=${item.product.id}">删除</a>
		    			</td>
	    			</tr>
	    		</c:forEach>
	    		
	    		<tr>
	    			<td colspan="7">总价:<font color="red">￥${cart.totalPrice }元</font></td>
	    		</tr>
	    	</table>
	    	<a href="javascript:void(0)" onclick="confirmClearAll();">清空购物车</a>
	    	<a href="${pageContext.request.contextPath }/front/handler/orderHandlerServlet?method=confirmOrder">下订单</a>
    	</c:if>
  </body>
  <script type="text/javascript">
  	
  		function confirmClearAll(){
  			var result = window.confirm("确认要清空吗?");
  			
  			if(result){
  				window.location.href="${pageContext.request.contextPath }/front/handler/cartServlet?method=clearAll";
  			}
  		}
  
  </script>
</html>
