<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	
  </head>
  
  <body>
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
