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
  <tr><td colspan="5">入住历史</td></tr>
  <tr>
  
  			
  			<td>房间顾客1</td>
  			<td>房间顾客1</td>
  			<td>住店日期</td>
  			<td>离店日期</td>
  
  
  </tr>
  			
  
  </thead>
  		  <c:forEach items="${usersputong.userRoomOrder }" var="roomorder">
 			
 			<tr>
 				<c:forEach items="${roomorder.customerorders.userRoomOrder }" var="useroomorder" begin="0" end="0">
 				<td>${useroomorder.userputong.name }</td><td>${useroomorder.userputong.idcard }</td>	
 							
 			
 			</c:forEach>
 			<td><fmt:formatDate value="${roomorder.createdate }"  pattern="yy-MM-dd HH:mm:ss"/>    </td>
			<td><fmt:formatDate value="${roomorder.moditfydate }"  pattern="yy-MM-dd HH:mm:ss"/> </td>
  			</tr>
  				<c:forEach items="${roomorder.customerorders.userRoomOrder }" var="useroomorder" begin="1" end="1">
 				<tr>
 				<td></td>
 				<td>${useroomorder.userputong.name }</td>
 				<td>${useroomorder.userputong.idcard }</td>	
 				<td></td>			
 				</tr>
 				</c:forEach>
  </c:forEach>
  
  		<tr><td colspan="5">今日入住</td></tr>
 		 <tr>
 		 	<td>房间顾客1</td>
  			<td>房间顾客1</td>
  			<td>住店日期</td>
  			<td>预离日期</td>
  		</tr>
  		 <c:forEach items="${usersputong.roomuserses }" var="roomuserses">
 			
 			
 			<c:forEach items="${roomuserses.rooms.roomuserses}" var="roomuser"  begin="0" end="0">
 				<tr>
 				<td>${roomuser.usersputong.name }</td><td>${roomuser.usersputong.idcard }</td>	
 				<td><fmt:formatDate value="${roomuserses.createdate }"  pattern="yy-MM-dd HH:mm:ss"/>    </td>
			<td><fmt:formatDate value="${roomuserses.moditfydate }"  pattern="yy-MM-dd HH:mm:ss"/> </td>
	  			</tr>	
	 		</c:forEach>
 			<tr>
  			<c:forEach items="${roomuserses.rooms.roomuserses}" var="roomuser"  begin="1" end="1">
 				
 				<td>${roomuser.usersputong.name }</td><td>${roomuser.usersputong.idcard }</td>	
	  			
	 		</c:forEach>
  			
			</tr>
	 			
 		</c:forEach>	
 		 
  
  
  
<tbody>

 		
 			
  </tbody>
  </table>
  
  </body>
</html>
