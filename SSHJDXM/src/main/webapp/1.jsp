<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
      <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function(){
	
		$("#checkIdCard").click(function(){
		
			var value=$("#idcard").val();
			location="${pageContext.request.contextPath }/admin/qiantai/findUserByIdCard?idCard="+value;
		
		})
		
		
		$("#checkAll").click(function(){
		
			
			location="${pageContext.request.contextPath }/admin/qiantai/findAllRoomUser"
		
		})
	
	
	
	})




</script>	
  </head>
  
  <body>
  <h1>通过身份证查询当天入住信息</h1>
  <form>
  	<input  id="idcard"     />
  </form>
   <input  type="button" id="checkIdCard"  value="通过身份证查询入住信息"   />
 
   
  <input   type="button" id="checkAll" value="浏览今晚全部客人信息"   />
   
   <a href="${pageContext.request.contextPath }/admin/room/findAll.action">查看全部房间</a>
   	

  
  <h1>酒店住宿预定</h1> 
  	${error }
  		<form action="${pageContext.request.contextPath }/user/loginReverse" method="post">
  		
  		用户名<input name="user.loginname" />
  		用户密码<input name="user.password" />
  		<input type="submit" >
  		</form>
  
  		<h1>酒店商店买东西</h1> 
 
  		<form action="${pageContext.request.contextPath }/user/goods/loginBuyGoods" method="post">
  		请输入房间号码：<input   name="room.no"  />
  	
  		<input type="submit" >
  		</form>
  		  </body>
</html>
