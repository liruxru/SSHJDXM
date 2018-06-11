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
	    var reg =/^\d{6,11}$/;
	    var reg2 =/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/; 
		if (document.getElementById("no").value.trim().length<=0) {
			alert("员工编号为空，无法提交！");
		}else if(document.getElementById("name").value.length<2){
			alert("员工姓名太短或为空，无法提交！");
		}else if(document.getElementById("age").value>55||document.getElementById("age").value<18){
			alert("请选择正确的员工年龄!")
		}else if(!reg.test(document.getElementById("phone").value)||document.getElementById("phone").value.length<6){
			alert("请选择正确的联系电话!");
		}else if(document.getElementById("address").value==""){
			alert("地址为空，无法提交！");
		}else if(document.getElementById("workdescription").value==""){
			alert("请输入工作职位!")
		}else if(!reg2.test(document.getElementById("salary").value)||document.getElementById("salary").value==""){
			alert("请输入员工工资!");
		}else if(document.getElementById("sex").value==0){
			alert("请选择员工的性别!")
		}else{
			 document.getElementById("but").setAttribute("type", "submit");
		}				
	}
 
	</script>
  </head>
  
  <body>
    	<div id="all">
		<a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
	    <c:if test="${staff.id eq null}">
	       <div id="bt">添加員工页</div>
	    </c:if>
		<form action="${pageContext.request.contextPath}/admin/staffs/staffsadd.action" method="post">
			<div id="tables">
				<div class="input-box">
					<label>员工编号:</label> 
					<input name="staffs.no" id="no" value="${staff.no}" placeholder="请输入员工的编号"/>
				</div>
				<div class="input-box">
					<label>员工姓名：</label> 
					<input name="staffs.name" id="name" value="${staff.name}" placeholder="请输入员工姓名"/>
				</div>
				<div class="input-box">
					<label>员工年龄：</label>
					 <input name="staffs.age" id="age" value="${staff.age}" placeholder="请输入员工年龄"/>
				</div>
				<div class="input-box">
					<label>联系电话：</label>
					 <input type="text"  name="staffs.phone" id="phone" value="${staff.phone}" placeholder="请输入联系电话"/>
				</div>
				<div class="input-box">
					<label>家庭住址：</label>
					 <input name="staffs.address" id="address" value="${staff.address}" placeholder="请输入家庭住址"/>
				</div>
				<div class="input-box">
					<label>工作岗位：</label>
					 <input name="staffs.workdescription" id="workdescription" value="${staff.workdescription}" placeholder="请输入工作岗位"/>
				</div>
				<div class="input-box">
					<label>工作薪水：</label>
					 <input name="staffs.salary" id="salary" value="${staff.salary}" placeholder="请输入工作薪水"/>
				</div>
				<div class="input-box">
				     <label for="name">员工性别:</label>
					 <select class="form-control" name="staffs.sex" id="sex">
						<option value="0">请选择性别</option>		
						<option value="1" <c:if test="${staff.sex eq 1}">selected</c:if>>男</option>
						<option value="2" <c:if test="${staff.sex eq 2}">selected</c:if>>女</option>
					</select>					
				</div>
				<input type="hidden" name="staffs.createdate" value="${staff.createdate}">
				<input type="hidden" name="staffs.id" value="${staff.id}">
				<!-- <input type="submit" value="提交" id="but" /> -->
				<input id="but" onclick="doSave()" type="button"  value="提交"/>
			</div>
        </form>
        </div>
  </body>
</html>
