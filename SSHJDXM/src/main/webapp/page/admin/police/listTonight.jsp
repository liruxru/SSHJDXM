<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	
  </head>
  
  <body>
  <table>
  	<thead>
  		<tr>
  			<td>房间号码</td>
  			<td>顾客姓名</td>
  			<td>顾客身份证</td>
  			<td>住店日期</td>
  			<td>预离日期</td>
  		</tr>
  	
  	</thead>
    <c:forEach items="${roomList }" var="room">
  	<tr>
  	<td>${room.no }</td>
  			 <c:forEach items="${room.roomuserses }" var="roomuser" begin="0" end="0" >
  			<td>${roomuser.usersputong.name }</td>
  			<td>${roomuser.usersputong.idcard }</td>
  			
  	 		</c:forEach>
  	 		
  	 		 <c:forEach items="${room.roomuserses }" var="roomuser" begin="0" end="0">
  			<td><fmt:formatDate value="${roomuser.createdate }"  pattern="yy-MM-dd HH:mm:ss"/></td>
  			<td><fmt:formatDate value="${roomuser.moditfydate }"  pattern="yy-MM-dd HH:mm:ss"/></td>
  			
  	 		</c:forEach>
  	 		
  	 		<c:forEach items="${room.roomuserses }" var="roomuser" begin="1" end="1">
 				<tr>
 				<td></td>
 				<td>${roomuser.usersputong.name }</td>
  				<td>${roomuser.usersputong.idcard }</td>
 				<td></td>			
 				</tr>
 			</c:forEach>
  		
  
  			
  		
  </c:forEach>
  
  </table>

  
  
  </body>
</html>
