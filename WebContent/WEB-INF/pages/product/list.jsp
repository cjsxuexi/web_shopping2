<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类列表</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	
	// 改变分类的时候去查询子分类
	function selectProductTypes(obj){
		var value = obj.value;
		if(value == 'no'){
			alert("请选择一级分类");
			return;
		}
		
		$.get(
			"${pageContext.request.contextPath}/sys/servlet/handler/productTypeHandlerServlet",
			"productTypeId="+value+"&method=querySubProductTypeById",
			function(returnData){
				// 1、首先获取到二级分类的select
				var _select = $("#subProductTypeId");
				
				// 2、获取到之后,二话不说,直接清空
				_select.empty();
				
				// 3、清空之后,再次附加请选择二级分类
				var noOption = "<option value='no'>---请选择二级分类---</option>";
				$(noOption).appendTo(_select);
				
				// 4、真正的去根据返回的数据去创建option
				if(returnData){
					for(var i = 0 ; i < returnData.length ; i++){
						var obj = returnData[i];
						var option = "<option value='"+obj.id+"'>"+obj.ptname+"</option>";						
						$(option).appendTo(_select);
					}
				}
			},
			"json"
		);
	}
</script>
</head>
<body>
	
	<table border="1" align="center">
		<tr>
			<td>
				<select onchange="selectProductTypes(this);">
					<option value="no">---选择一级分类---</option>
					<c:forEach items="${productTypes}" var="pt">
						<option value="${pt.id }">${pt.ptname }</option>
					</c:forEach>
				</select>
				<select name="subProductTypeId" id="subProductTypeId">
						<option value="no">---请选择二级分类---</option>
				</select>
			</td>
		</tr>
	</table>
	
	<br/><h3 align="center">----------------------------------------------</h3><br/><br/><br/>


	<table border="3" width="900px;" height="150px;" align="center" >
	
		<tr>
			<td>商品id</td>
			<td>商品名称</td>
			<td>商品说明</td>
			<td>商品价格</td>
			<td>商品作者</td>
			<td>商品出版社</td>
			<td>商品图片</td>
			<td>商品状态</td>
			<td>操作</td>
		</tr>

		<c:forEach items="${products }" var="p">
			<tr>
				<td>${p.id }</td>
				<td>${p.pname }</td>
				<td>${p.pdesc }</td>
				<td>${p.pprice }</td>
				<td>${p.pauthor }</td>                            
				<td>${p.paddress }</td>
				<td><img width="50px" height="30px;" src="${pageContext.request.contextPath }/upload/${p.pimageUrl}" /></td>
				<td>
					<c:if test="${p.pdeleted }">
						上架
					</c:if>
					<c:if test="${!p.pdeleted}">
						下架
					</c:if>
				</td>
				<td>
					<a href="${pageContext.request.contextPath }/sys/servlet/handler/productHandlerServlet?method=delete&productId=${p.id}">删除商品</a> <font color="red">|</font>
					<a href="${pageContext.request.contextPath }/sys/servlet/ui/productServlet?method=editUI&productId=${p.id}">修改商品</a> 
				</td>
			</tr> 
		</c:forEach>
	</table>
	
	<table border="1" align="center" style="margin-top: 30px;">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath }/sys/servlet/ui/productTypeServlet?method=addUI">添加商品</a>
			</td>
		</tr>
	</table>
</body>
</html>