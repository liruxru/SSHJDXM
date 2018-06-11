<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/userAdd.css" />
	<script  type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		if(${userDo.id eq null}){
			var loginNameCode=0;
			var passwordCode=0;
			var rePasswordCode=0;
			var shqCode=0;
			var userNameCode=0;
			var ageCode=0;
			var phoneCode=0;
			var emainCode=0;
		}else{
			var loginNameCode=1;
			var passwordCode=1;
			var rePasswordCode=1;
			var shqCode=1;
			var userNameCode=1;
			var ageCode=1;
			var phoneCode=1;
			var emailCode=1;
		}
		var ageReg=/^\d{1,3}$/;
		var phoneReg=/^(1([3|5|8][0-9]{9}))|(1(7[0|6|7|8][0-9]{8}))$/;
		var emailReg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		
		$(function(){
			
		  //验证密码
			 $("#pwd").blur(function(){
			 		var strPwd=$(this).val().trim();
			 		var restrPwd=$("#repwd").val().trim();
			 		if(strPwd.length==0){
			 			$("#checkPwd").html("<font color='red'>密码不能为空</font>");
			 			passwordCode=0;
			 		}else if(strPwd.length<8){
			 				$("#checkPwd").html("<font color='red'>密码不能少于八位</font>");
			 				passwordCode=0;
			 		}else if(strPwd !=restrPwd){
			 			rePasswordCode=0;
						$("#checkrePwd").html("<font color='red'>二次密码不一致</font>");
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
						passwordCode=1;	
			 			$("#checkPwd").html("<font color='green'>√</font>");
						rePasswordCode=1;	
			 			$("#checkrePwd").html("<font color='green'>√</font>");
					}
			 })
		 
		 
		 //省市区三级联动
	     $("#sheng").change(function(){
				var strCode=$(this).val();
				/* alert(strCode) */
				var strArr=strCode.split("|");
				/* var name=strArr[0]; */
				var shengcode=strArr[1];
				/* alert(shengcode) */
				/* alert(strCode) */
				var shiObj=$("#shi");
				var quObj=$("#qu"); 
				if(shengcode!=0){
					var url="${pageContext.request.contextPath}/getListShiBysheng";
					$.post(url,
					{"shengCode":shengcode},
					function(data){
						shiObj.html("<option value='0'>请选择市</option>");
						quObj.html("<option value='0'>请选择区</option>");
						var html="";
						for(var i=0;i<data.length;i++){
							html+="<option value='"+data[i].name+'|'+data[i].code+"'>"+data[i].name+"</option>";
						}
						shiObj.append(html);
					},
					'json');
				}else{
					shiObj.html("<option value='0'>请选择市</option>");
					quObj.html("<option value='0'>请选择区</option>");
					
				}
			});
		$("#shi").change(function(){
				var strCode=$(this).val();
				var strArr=strCode.split("|");
				var shicode=strArr[1];
				var quObj=$("#qu");
				if(shicode!=0){
					var url="${pageContext.request.contextPath}/getQuByShi";
					$.post(url,{"shiCode":shicode},
						function(data){
							quObj.html("<option value='0'>请选择区</option>");
							var html="";
							for(var i=0;i<data.length;i++){
								html+="<option value='"+data[i].name+'|'+data[i].code+"'>"+data[i].name+"</option>";
							}
							quObj.append(html);
					},
					'json');
				}else{
					quObj.html("<option value='0'>请选择区</option>");
				}
			}) 
			
			//添加时不操作验证
	  		$("#loginName").blur(function(){
	  			var loginName=$(this).val().trim();
	  			if(loginName==""){
	  				userNameCode=0;
	  				$("#checkloginname").html("<font color='red'>名称不能为空</font>");
	  			}
	  		})
			
			//验证登陆名
			$("#loginName").change(function(){
		  			var loginName=$(this).val().trim();
		  			var url="${pageContext.request.contextPath}/admin/huiyuan/checkuser"
		  			if(loginName==""){
		  				$("#checkloginname").html("<font color='red'>登录名不能为空</font>");
		  				loginNameCode=0;
		  			}else{
		  				$.post(url,{"loginName":loginName},function(res){
		  					if(res==0){
		  						$("#checkloginname").html("<font color='green'>√</font>");
		  						loginNameCode=1;
		  					}else{
		  						$("#checkloginname").html("<font color='red'>该名称已存在</font>");
		  						loginNameCode=0;
		  					}
		  				},
		  				'json');
		  			}
		  	})
		  	//验证真实名称
		  	$("#username").blur(function(){
		  		var userName=$(this).val().trim();
		  		if(userName==""){
		  			$("#checkUsername").html("<font color='red'>真实名称不能为空</font>");
		  			userNameCode=0;
		  		}else{
		  			$("#checkUsername").html("<font color='green'>√</font>");
		  			userNameCode=1;
		  		}
		  	})
		  	//验证年龄
		  	$("#age").blur(function(){
		  		var age=$(this).val().trim();
		  		if(age==""){
		  			$("#checkAge").html("<font color='red'>年龄不能为空</font>");
		  			ageCode=0;
		  		}else if(!ageReg.test(age)){
		  			$("#checkAge").html("<font color='red'>只能输入1-3位数字</font>");
		  			ageCode=0;
		  		}else{
		  			$("#checkAge").html("<font color='green'>√</font>");
		  			ageCode=1;
		  		}
		  	})
		  	//验证手机号
		  	$("#phone").blur(function(){
		  		var phone=$(this).val().trim();
		  		if(phone==""){
		  			$("#checkPhone").html("<font color='red'>电话不能为空</font>");
		  			phoneCode=0;
		  		}else if(!phoneReg.test(phone)){
		  			$("#checkPhone").html("<font color='red'>请输入正确格式</font>");
		  			phoneCode=0;
		  		}else{
		  			$("#checkPhone").html("<font color='green'>√</font>");
		  			phoneCode=1;
		  		}
		  	})
		  	//验证邮箱
		  	$("#email").blur(function(){
		  		var email=$(this).val().trim();
		  		if(email==""){
		  			$("#checkEmail").html("<font color='red'>电话不能为空</font>");
		  			emailCode=0;
		  		}else if(!emailReg.test(email)){
		  			$("#checkEmail").html("<font color='red'>请输入正确格式</font>");
		  			emailCode=0;
		  		}else{
		  			$("#checkEmail").html("<font color='green'>√</font>");
		  			emailCode=1;
		  		}
		  	})
		  	
		  	
		  	
		})
		
		function sub(){
			var shengVal=document.getElementById("sheng").value;
			var shiVal=document.getElementById("shi").value;
			var quVal=document.getElementById("qu").value;
			
			//添加时省市区选项均不能为空   修改时原地址栏和修改地址选项不能同时为空
			if(${userDo.id eq null}){
				if(shengVal==0||shiVal==0||quVal==0){
					shqCode=0;
				}else{
					shqCode=1;
				}
			}
			if(loginNameCode==0||passwordCode==0||rePasswordCode==0||shqCode==0||userNameCode==0||ageCode==0||phoneCode==0||emailCode==0){
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
	     <c:if test="${userDo.id ne null}">
	      <div id="bt">修改会员</div>
	    </c:if> 
	    <c:if test="${userDo.id eq null}">
	       <div id="bt">添加会员</div>
	    </c:if>
	   <!--  onsubmit="return sub()" -->
	    <form action="${pageContext.request.contextPath}/admin/huiyuan/useradd"  method="post" onsubmit="return sub()">
			<div id="tables">
				<div class="input-box">
					<label>登录名称：</label> 
					<input type="text"  name="user.loginname"  id="loginName" value="${userDo.loginname}" placeholder="请输入名称"/>
					<span id="checkloginname" ></span>
				</div>
				<div class="input-box">
					<label>登录密码：</label>
					 <input type="password"  id="pwd"  name="user.password" value="${userDo.password}" placeholder="请输入密码"/>
					<span id="checkPwd"></span>
				</div>
				<div class="input-box">
					<label>确认密码：</label>
					 <input type="password" id="repwd"  value="${userDo.password}"   placeholder="请确认密码"/>
					<span id="checkrePwd"></span>
				</div>
				<div class="input-box">
					<label>真实名称：</label>
					 <input type="text" id="username"   name="user.username"  value="${userDo.username}"   placeholder="请输入真实名称"/>
					<span id="checkUsername"></span>
				</div>
				
				<c:if test="${userDo.id ne null}">
	      			<div class="input-box">
					<label>地址：</label>
					 <input type="text"  name="user.address" id="yuanAdd" disabled="disabled"  value="${userDo.address}" />
				</div>
	    		</c:if>
				<div class="input-box">
					<c:if test="${userDo.id ne null}">
						<label>修改地址：</label>
					</c:if>
					<c:if test="${userDo.id eq null}">
						<label>地址:</label>
					</c:if>
					<select id="sheng" style="margin-top: 15px" name="address.sheng" >
				 			<option value="0">请选择省</option>
				 			<c:forEach items="${listProvince}" var="she">
				 				<option  value="${she.name}|${she.code}">${she.name}</option>
				 			</c:forEach>
				 	</select>
				 	&nbsp;&nbsp;
				   	<select id="shi" style="margin-top: 15px" name="address.shi">
				   		<option>请选择市</option>
					</select>
					&nbsp;&nbsp;
				   	<select id="qu" style="margin-top: 15px" name="address.qu">
				   		<option>请选择区</option>
				   	</select>
				   	<c:if test="${userDo.id ne null}">
						<span><font color="green" style="margin-left: 70px">若不修改请忽略</font></span>
					</c:if>
				</div>
				
				<div class="input-box">
					<label>年龄：</label>
					 <input type="text" id="age"  name="user.age"  value="${userDo.age}"   placeholder="请输入真实名称"/>
					<span id="checkAge"></span>
				</div>
				<div class="input-box">
					<label>电话：</label>
					 <input type="text" id="phone"  name="user.phone"  value="${userDo.phone}"   placeholder="请输入真实名称"/>
					<span id="checkPhone"></span>
				</div>
				<div class="input-box">
					<label>邮箱：</label>
					 <input type="text"	id="email"  name="user.email"  value="${userDo.email}"   placeholder="请输入真实名称"/>
					<span id="checkEmail"></span>
				</div>
				<input type="hidden" name="user.id" value="${userDo.id}">
				<input type="submit" value="提交" id="but"  />
			</div>
        </form>
        </div>
	  </div>
  </body>
</html>
