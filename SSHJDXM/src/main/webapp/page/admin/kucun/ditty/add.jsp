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
    
    <title>add.jsp</title>
    
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
		if (document.getElementById("name").value=="") {
			alert("商品名称为空，无法提交！");
		}else if(document.getElementById("fullName").value.length<4){
			alert("全称太短或为空，无法提交！");
		}else if(document.getElementById("entrepot").value==0){
			alert("请选择存放的仓库")
		}else if(document.getElementById("provider").value==0){
			alert("请选择供货商")
		}else{
			 document.getElementById("but").setAttribute("type", "submit");
		}				
	}

	</script>
  </head>
  
  <body>
    	<div id="all">
		<a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
	    <c:if test="${dittys.id ne null}">
	      <div id="bt">修改非卖品页</div>
	    </c:if>
	    <c:if test="${dittys.id eq null}">
	       <div id="bt">添加非卖品页</div>
	    </c:if>	

		<form action="${pageContext.request.contextPath}/admin/kucun/dittyadd.action" method="post">
			<div id="tables">
				<div class="input-box">
					<label>名称:</label> 
					<input type="text" id="name" name="ditty.name" value="${dittys.name}" placeholder="请输入非卖品名称"/>
				</div>
				<div class="input-box">
					<label>全名:</label> 
					<input type="text" id="fullName" name="ditty.fullname" value="${dittys.fullname}" placeholder="请输入全名"/>
				</div>
				<div class="input-box">
					<label>库存:</label>
					 <input type="text" id="num" name="ditty.num" value="${dittys.num}" placeholder="请输入库存"/>
				</div>
				<div class="input-box">
					<label>价格:</label>
					 <input type="text"  id=""price name="ditty.price" value="${dittys.price}" placeholder="请输入成本价格"/>
				</div>
				<div class="input-box">
					<div class="form-group">
						<label for="name">供货商:</label>
						 <select class="form-control" id="provider" name="ditty.provider.id">
							<option value="0">请选择供货商:</option>
							<c:forEach items="${providerList}" var="c">
								<option value="${c.id}"<c:if test="${dittys.provider.id eq c.id}">selected</c:if>>
								${c.providename}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="input-box">
					<div class="form-group">
						<label for="name">存放仓库:</label>
						 <select class="form-control" id="entrepot" name="ditty.entrepot.id">
							<option value="0">请选择仓库</option>
							<c:forEach items="${entrepotList}" var="c">
								<option value="${c.id}"<c:if test="${dittys.entrepot.id eq c.id}">selected</c:if>>
								${c.sn}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<input type="hidden" name="ditty.id" value="${dittys.id}">
				<input type="button" onclick="doSave()" value="提交" id="but" />
			</div>
        </form>
        </div>
  </body>
</html>