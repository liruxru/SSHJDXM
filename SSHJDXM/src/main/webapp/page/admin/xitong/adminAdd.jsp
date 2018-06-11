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
    
    <title>My JSP 'adminAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/roleAdd.css" />
	<link rel="stylesheet" href="layui/css/layui.css" media="all">
  	<script type="text/javascript" src="layui/layui.js" ></script>
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script>
		if(${admin.id eq null}){
			var userNameCode=0;
			var passwordCode=0;
			var rePasswordCode=0;
		}else{
			var userNameCode=1;
			var passwordCode=1;
			var rePasswordCode=1;
		}
		var checkroleCode=0;
		var staffsCode=0;
		//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
		layui.use(['form','layer','element','jquery'], function(){
		  var form = layui.form
		  ,layer=layui.layer
		  ,element=layui.element
		  ,$=layui.$
		  $(function(){
		  		//添加时不操作验证
		  		$("#loginName").blur(function(){
		  			var loginName=$(this).val().trim();
		  			if(loginName==""){
		  				userNameCode=0;
		  				$("#checkloginname").html("<font color='red'>名称不能为空</font>");
		  			}
		  		})
		  		
		  		$("#loginName").change(function(){
		  			var loginName=$(this).val().trim();
		  			var url="${pageContext.request.contextPath}/admin/admin/checkadmin"
		  			if(loginName==""){
		  				userNameCode=0;
		  				$("#checkloginname").html("<font color='red'>名称不能为空</font>");
		  			}else{
		  				$.post(url,{"loginName":loginName},function(res){
		  					if(res==0){
		  						userNameCode=1;
		  						$("#checkloginname").html("<font color='green'>√</font>");
		  					}else{
		  						userNameCode=0;
		  						$("#checkloginname").html("<font color='red'>该名称已存在</font>");
		  					}
		  				},
		  				'json');
		  			}
		  		})
		  		
		  	 //验证密码
			 $("#pwd").blur(function(){
			 		var strPwd=$(this).val().trim();
			 		if(strPwd==""){
			 			$("#checkPwd").html("<font color='red'>密码不能为空</font>");
			 			passwordCode=0;
			 		}else if(strPwd.length<8){
			 				$("#checkPwd").html("<font color='red'>密码不能少于八位</font>");
			 				passwordCode=0;
			 		}else{
			 			passwordCode=1;	
			 			$("#checkPwd").html("<font color='green'>√</font>");
			 		}
			 		
			 })
			 
			 //确认密码
			 $("#repwd").blur(function(){
			 		var strPwd=$(this).val().trim();
			 		var stryPwd=$("#pwd").val().trim();
				 	if(strPwd.length==0){
						 rePasswordCode=0;
						$("#checkrePwd").html("<font color='red'>确认密码不能为空</font>");
					}else if(strPwd !=stryPwd){
						 rePasswordCode=0;
						$("#checkrePwd").html("<font color='red'>二次密码不一致</font>");
					}else{
						rePasswordCode=1;	
			 			$("#checkrePwd").html("<font color='green'>√</font>");
					}
			 })
		
		 })
		});
		function sub(){
	  			//责任人选择验证
				var staffs=document.getElementById("staffs").value;
				if(staffs==0){
				 	staffsCode=0;
				}else{
					staffsCode=1;
				}
				//获取复选框被选中的个数
			    var obj = document.getElementsByName("roleIds");
			    var check_val = [];
			    for(k in obj){
			        if(obj[k].checked)
			            check_val.push(obj[k].value);
			    }
			   if(check_val.length==0){
			   		checkroleCode=0;
			   }else{
			   		checkroleCode=1;
			   }
			   
			   if(userNameCode==0||passwordCode==0||rePasswordCode==0||staffsCode==0||checkroleCode==0){
			   			return false;
			   }else{
			   			return true;
			   }
			   
		}
	</script>

  </head>
  
  <body>
  		
    	 <div id="all">
		<a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
	    <c:if test="${admin.id ne null}">
	      <div id="bt">修改管理员</div>
	    </c:if>
	    <c:if test="${admin.id eq null}">
	       <div id="bt">添加管理员</div>
	    </c:if>

		<form action="${pageContext.request.contextPath}/admin/admin/adminadd" onsubmit="return sub()" method="post">
			<div id="tables">
				<div class="input-box">
					<label>登录名称：</label> 
					<input type="text"  name="admin.loginname"  id="loginName" value="${adminDo.loginname}" placeholder="请输入名称"/>
					<span id="checkloginname" ></span>
				</div>
				
				<div class="input-box">
					<label>登录密码：</label>
					 <input type="password"  name="admin.password" id="pwd" value="${adminDo.password}" placeholder="请输入密码"/>
					<span id="checkPwd"></span>
				</div>
				<div class="input-box">
					<label>确认密码：</label>
					 <input type="password"  value="${adminDo.password}" id="repwd"  placeholder="请确认密码"/>
					<span id="checkrePwd"></span>
				</div>
				
				<div class="input-box">
					<label>责任人:</label>
					<select style="margin-top: 15px" name="admin.staffs.id" id="staffs">
						<option value="0">请选择员工</option>
						<c:forEach items="${staffsAll}" var="s">
								<option value="${s.id}"   <c:if test="${adminDo.staffs.id eq s.id}">selected</c:if> >
								${s.name}</option>
						</c:forEach>
					</select> 
				</div>
				<div class="admin-addrole">
					<div style="height: 120px; width:520px; padding-top: 20px;margin-left: 70px">
					<c:forEach items="${adminrole}" var="l">
						<input type="checkbox" value="${l.id}" name="roleIds"  <c:forEach items="${setRole}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>     >${l.rolename}&nbsp;&nbsp;&nbsp;
					</c:forEach>
					</div>
				</div>
				
				<input type="hidden" name="admin.id" value="${adminDo.id}">
				<input type="submit" value="提交" id="but" />
			</div>
        </form>
        </div>
    	 
  </body>
</html>
