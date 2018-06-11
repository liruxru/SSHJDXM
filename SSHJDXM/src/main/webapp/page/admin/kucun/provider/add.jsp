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
			alert("供应商为空，无法提交！");
		}else if(document.getElementById("phone").value.length<6){
			alert("联系电话为空，无法提交！");
		}else if(document.getElementById("address").value==""){
			alert("请输入供应商的地址")
		}else{
			document.getElementById("but").setAttribute("type", "submit");
		}				
	}
	</script>
  </head>
  
  <body>
    	<div id="all">
		<a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
	    <c:if test="${providers.id ne null}">
	      <div id="bt">修改供应商页</div>
	    </c:if>
	    <c:if test="${providers.id eq null}">
	       <div id="bt">添加供应商页</div>
	    </c:if>	

		<form action="${pageContext.request.contextPath}/admin/provider/providerAdd.action" method="post">
			<div id="tables">
				<div class="input-box">
					<label>供应商姓名:</label> 
					<input type="text" id="name"  name="provider.providename" value="${providers.providename}" placeholder="请输入供应商名称"/>
				</div>
				<div class="input-box">
					<label>联系电话：</label> 
					<input type="text"  id="phone" name="provider.providephone" value="${providers.providephone}" placeholder="请输入联系电话"/>
				</div>
				<div class="input-box">
					<label>地址：</label>
					 <input type="text" id="address"  name="provider.provideaddress" value="${providers.provideaddress}" placeholder="请输入地址"/>
				</div>
				<input type="hidden" name="provider.createdate" value="${providers.createdate}">
				<input type="hidden" name="provider.id" value="${providers.id}">
				<input type="button" onclick="doSave()" value="提交" id="but" />
			</div>
        </form>
        </div>
  </body>
</html>