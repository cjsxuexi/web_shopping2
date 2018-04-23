<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1 align="center">-------------------订单详情--------------</h1>

	<table border="1" align="center">
		<tr>
			<td>下单时间</td>
			<td>订单状态</td>
			<td>订单号</td>
			<td>订单价格</td>
			<td>订单付款时间</td>
			<td>留言</td>
			<td>付款方式</td>
			<td>明细</td>
		</tr>
		<tr>
			<td><fmt:formatDate value="${order.submitTime }"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td><c:choose>
					<c:when test="${order.orderState == 0 }">待审核</c:when>
					<c:when test="${order.orderState == 1 }">取消</c:when>
					<c:when test="${order.orderState == 2 }">关闭</c:when>
					<c:when test="${order.orderState == 3 }">待付款</c:when>
					<c:when test="${order.orderState == 4 }">待发货</c:when>
					<c:when test="${order.orderState == 5 }">待收货</c:when>
					<c:when test="${order.orderState == 6 }">已收货</c:when>
					<c:otherwise>
								订单异常
							</c:otherwise>
				</c:choose></td>
			<td>${order.orderNo }</td>
			<td>${order.orderPrice }</td>
			<td><fmt:formatDate value="${order.paymentTime }"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td>${order.message }</td>
			<td><c:choose>
					<c:when test="${order.paymentType == 0 }">
								货到付款
							</c:when>
					<c:otherwise>
								在线支付
							</c:otherwise>
				</c:choose></td>
			<td>
				<table border="1" bgcolor="gray" align="right">
					<tr>
						<td>商品名称</td>
						<td>商品价格</td>
						<td>商品数量</td>
						<td>小计</td>
					</tr>
					<c:forEach items="${order.orderItems }" var="orderItem">
						<tr>
							<td>${orderItem.productName }</td>
							<td>${orderItem.productPrice }</td>
							<td>${orderItem.qunantity }</td>
							<td>${orderItem.orderItemPrice }</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
	
	</br></br>
	
	<h2 align="center">********订单的操作***********</h2>
	
	<table border="1" align="center">
		
		<c:if test="${order.orderState == 0  }">
			<tr>
				<td><a href="${pageContext.request.contextPath }/sys/serlvet/handler/orderHandlerServlet?method=updateOrderStatus&flag=checkedPass&orderId=${order.id}">审核通过</a></td>
				<td><a href="${pageContext.request.contextPath }/sys/serlvet/handler/orderHandlerServlet?method=updateOrderStatus&flag=checkedFail&orderId=${order.id}">审核失败</a></td>
			</tr>
		</c:if>
		
		<c:if test="${order.orderState == 4}">
			<tr>
				<td><a href="${pageContext.request.contextPath }/sys/serlvet/handler/orderHandlerServlet?method=updateOrderStatus&flag=beginDeliver&orderId=${order.id}">发货</a></td>
			</tr>
		</c:if>
		
		<c:if test="${order.orderState == 3 or order.orderState == 4 or order.orderState == 5}">
			<tr>
				<td><a href="${pageContext.request.contextPath }/sys/serlvet/handler/orderHandlerServlet?method=updateOrderStatus&flag=cancle&orderId=${order.id}">取消</a></td>
			</tr>
		</c:if>
		
	</table>
</body>
</html>