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
		if (document.getElementById("sn").value=="") {
			alert("仓库编号为空，无法提交！");
		}else if(document.getElementById("phone").value.length<4){
			alert("联系电话为空，无法提交！");
		}else if(document.getElementById("address").value==""){
			alert("请输入仓库的地址")
		}else if(document.getElementById("staff").value==0){
			alert("请选择仓库负责人")
		}else{
			 document.getElementById("but").setAttribute("type", "submit");
		}				
	}
	</script>
  </head>
  
  <body>
    	<div id="all">
		<a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
	    <c:if test="${entrepots.id ne null}">
	      <div id="bt">修改仓库页</div>
	    </c:if>
	    <c:if test="${entrepots.id eq null}">
	       <div id="bt">添加仓库页</div>
	    </c:if>	

		<form action="${pageContext.request.contextPath}/admin/entrepot/entrepotAdd.action" method="post">
			<div id="tables">
				<div class="input-box">
					<label>仓库编号:</label> 
					<input type="text"  name="entrepot.sn" id="sn" value="${entrepots.sn}" placeholder="请输入仓库的编号"/>
				</div>
				<div class="input-box">
					<label>联系电话：</label> 
					<input type="text"  name="entrepot.phone" id="phone" value="${entrepots.phone}" placeholder="请输入联系电话"/>
				</div>
				<div class="input-box">
					<label>仓库地址：</label>
					 <input type="text"  name="entrepot.address" id="address" value="${entrepots.address}" placeholder="请输入仓库的地址"/>
				</div>
		
				<div class="input-box">
					<div class="form-group">
						<label for="name">责任人:</label>
						 <select class="form-control" id="staff" name="entrepot.staffs.id">
							<option value="0">请选择责任人</option>
							<c:forEach items="${listStaff}" var="c">
								<option value="${c.id}"<c:if test="${entrepots.staffs.id eq c.id}">selected</c:if>>
								${c.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<input type="hidden" name="entrepot.id" value="${entrepots.id}">
				<input type="button" value="提交" id="but" onclick="doSave()" />
			</div>
        </form>
        </div>
  </body>
</html>
