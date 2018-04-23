<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单的信息确认页面</title>
  </head>
  
  <body style="text-align: center;">
  		<br/>
  		<h2>订单购买列表</h2>
  		
  		<c:if test="${not empty sessionScope.cart.cartItems }">
	    	<table width="80%" frame="border" border="1" align="center">
	    		<tr>
	    			<td>商品id</td>
	    			<td>商品名称</td>
	    			<td>作者</td>
	    			<td>单价</td>
	    			<td>数量</td>
	    			<td>小计</td>
	    		</tr>
	    		
	    		<c:forEach var="item" items="${sessionScope.cart.cartItems}">
	    			<tr>
	    				<td>${item.product.id }</td>
		    			<td>${item.product.pname }</td>
		    			<td>${item.product.pauthor }</td>
		    			<td>${item.product.pprice }</td>
		    			<td>${item.quantity }</td>
		    			<td>${item.subPrice }</td>
	    			</tr>
	    		</c:forEach>
	    		
	    		<tr>
	    			<td colspan="7">总价:<font color="red">￥${cart.totalPrice }元</font></td>
	    		</tr>
	    		
				<tr>
					<td>
						<form action="${pageContext.request.contextPath }/front/handler/orderHandlerServlet?method=submitOrder" method="POST">
							 留言:<textarea name="message" rows="3" cols="15"></textarea><br/>
							 付款方式:<input type="radio" name="paymentType" value="0"/>货到付款
							 		<input type="radio" checked="checked" name="paymentType" value="1"/>在线支付 <br/><br/>
							 <input type="submit" value="提交订单"/>
						</form>
					</td>
				</tr>	    		
	    	</table>
    	</c:if>
</html>
