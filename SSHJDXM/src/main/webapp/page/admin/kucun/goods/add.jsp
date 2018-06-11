<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/entrepot/add.css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">	
	function doSave(){	
        /* var reg = new RegExp("^\+?[1-9][0-9]*$");         
        var salePriceReg = new RegExp("^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$");  			 */
		if (document.getElementById("goodName").value=="") {
			alert("商品名称为空，无法提交！");
		}else if(document.getElementById("fullName").value.length<4){
			alert("全称太短或为空，无法提交！");
		}
		/* else if(reg.test(document.getElementById("num").value)){
			 alert("请输入正确的库存数量")
		}
		else if(salePriceReg.test(document.getElementById("salePrice").value)){
			alert("请输入正确的成本价格")
		}
		else if(salePriceReg.test(document.getElementById("price").value)){
			alert("请输入正确的售价")
		} */
		else if(document.getElementById("entrepot").value==0){
			alert("请选择存放的仓库")
		}
		else if(document.getElementById("type").value==0){
			alert("请选择商品类型")
		}else if(document.getElementById("provider").value==0){
			alert("请选择供货商")
		}else if(document.getElementsByName("imgcover").size>0){
			alert("请选择商品封面")
		}else{
			 document.getElementById("but").setAttribute("type", "submit");
		}				
	}

	</script>
  </head>
  
  <body>
    	<div id="all">
		<a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
	    <c:if test="${good.id eq null}">
	       <div id="bt">添加商品页</div>
	    </c:if>
	    <span style="color:red">${fileError}</span>
		<form action="${pageContext.request.contextPath}/admin/kucun/goodsadd.action" method="post" enctype="multipart/form-data">
			<div id="tables">
				<div class="input-box">
					<label>商品名称:</label> 
					<span id="nameSpan"></span>
					<input type="text" name="goods.name" id="goodName" value="${good.name}" onblur="inName()" placeholder="请输入商品的名称"/>
				    <div class="tip">
						<i></i> <span></span>
					</div>
				</div>
				<div class="input-box">
					<label>商品全称：</label> 
					<input type="text"  name="goods.fullname" id="fullName" value="${good.fullname}" placeholder="请输入商品的全称"/>
				</div>
				<div class="input-box">
					<label>库存数量：</label>
					 <input type="text"  name="goods.num" id="num" value="${good.num}" placeholder="请输入库存数量"/>
				</div>
				<div class="input-box">
					<label>成本价：</label>
					 <input type="text"  name="goods.salePrice" id="salePrice" value="${good.salePrice}" placeholder="请输入商品的成本价格"/>
				</div>
				<div class="input-box">
					<label>售价：</label>
					 <input type="text"  name="goods.price" id="price" value="${good.price}" placeholder="请输入商品的销售价格"/>
				</div>
				<div class="input-box">
					<div class="form-group">
						<label for="name">存放仓库:</label>
						 <select class="form-control" name="goods.entrepot.id" id="entrepot">
							<option value="0">请选择仓库</option>
							<c:forEach items="${entrepotList}" var="c">
								<option value="${c.id}"<c:if test="${good.entrepot.id eq c.id}">selected</c:if>>
								${c.sn}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="input-box">
					<div class="form-group">
						<label for="name">商品类型:</label>
						 <select class="form-control" name="goods.goodstypes.id" id="type">
							<option value="0">请选择类型</option>
							<c:forEach items="${typeList}" var="c">
								<option value="${c.id}"<c:if test="${good.goodstypes.id eq c.id}">selected</c:if>>
								${c.typename}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="input-box">
					<div class="form-group">
						<label for="name">供货商:</label>
						 <select class="form-control" name="goods.provider.id" id="provider">
							<option value="0">请选择供货商</option>
							<c:forEach items="${providerList}" var="c">
								<option value="${c.id}"<c:if test="${good.provider.id eq c.id}">selected</c:if>>
								${c.providename}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="input-box">
					<div class="form-group">
						<label for="name">商品封面:</label>
						 <input type="file"	name="imgcover" />
					</div>
				</div>
				<div class="input-box">
					<div class="form-group" id="imgs">
						<label for="name">商品附图:</label> 
						  <input type="file" name="imgs"  style="width:170px; height: 50px" />
						  <input type="file" name="imgs"  style="width:170px; height: 50px" />
						  <input type="file" name="imgs"  style="width:170px; height: 50px" />
					</div>
				</div>
				<input type="hidden" name="goods.id" value="${goods.id}">
				<!-- <input type="submit" value="提交" id="but" /> -->
				<input id="but" onclick="doSave()" type="button"  value="提交"/>
			</div>
        </form>
        </div>
  </body>
</html>
