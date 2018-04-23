<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品添加</title>
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
	
	<form action="${pageContext.request.contextPath }/sys/servlet/handler/productHandlerServlet" 
		  method="post"
		  enctype="multipart/form-data">
		<table border="3" align="center" width="600px;">
			<tr>
				<td>商品名称:<input type="text" name="pname"> <br/></td>
			</tr>
			<tr>
				<td>商品分类:
					<select name="productTypeId" onchange="selectProductTypes(this)">
						<option value="no">---请选择一级分类---</option>
						<c:forEach items="${productTypes }" var="pt">
							<option value="${pt.id }">${pt.ptname }</option>
						</c:forEach>
					</select>
					<select name="subProductTypeId" id="subProductTypeId">
						<option value="no">---请选择二级分类---</option>
					</select>
					<br/>
				</td>
			</tr>
			<tr>
				<td>商品作者:<input type="text" name="pauthor"> <br/></td>
			</tr>
			<tr>
				<td>商品价格:<input type="text" name="pprice"> <br/></td>
			</tr>
			<tr>
				<td>商品出版社:<input type="text" name="paddress"> <br/></td>
			</tr>
			<tr>
				<td>商品图片:<input type="file" name="pimageUrl"> <br/></td>
			</tr>
			<tr>
				<td>商品说明:<textarea rows="10" cols="30" name="pdesc"></textarea> <br/></td>
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
