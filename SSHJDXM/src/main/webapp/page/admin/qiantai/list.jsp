<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	
  </head>
  
  <body>
  <form action="">
   <select>
 	 <option>选择房间类型</option> 
  
  		<c:forEach items="${roomtypeList }" var="roomtype">
  		<option>${roomtype.roomtype }</option>
  		</c:forEach>
  
  </select>
  
  
  
  </form>
  通过电话号码查询预定
  ${phoneerror } ${errorUserIn }
    <form action="${pageContext.request.contextPath }/admin/qiantai/findReverseByPhone"  method="post">
   输入电话号码:<input name="phone" />
   <br/><input type="submit" > 
  
  </form>
 
  <table>
  <thead>
  
  	<tr>
  		<td>编号</td>
  		<td>房间号</td>
  		<td>房间类型</td>
  		<td>房间价格</td>
  		<td>房间状态</td>
  		<td>房间预定情况</td>
  	
  	</tr>
  </thead>
  
  <tbody>
    <c:forEach items="${roomList }" var="room" varStatus="v">
  	<tr>
  		<td>${v.count }</td>
  		<td>${room.no }</td>
  		<td>${room.roomtypes.roomtype }</td>
  		<td>${room.roomtypes.price }</td>
  		<td>${room.roomstatus.status }</td>
  		
  		<td>
  		
  		<c:forEach items="${room.roomselects }" var="roomsel">
  		
  		<fmt:formatDate value="${roomsel.createdate }" pattern="MM月dd日" />
  		-<fmt:formatDate value="${roomsel.enddate }" pattern="MM月dd日" />
  		</c:forEach>
  		</td>
  		<td><a href="${pageContext.request.contextPath }/admin/qiantai/getRoomById?roomId=${room.id}">给用户开房</a></td>
  		<td><a href="${pageContext.request.contextPath }/admin/qiantai/userOut?roomId=${room.id}">用户退房</a></td>
  		<td><a href="${pageContext.request.contextPath }/admin/qiantai/goOnInRoom?roomId=${room.id}">用户续住</a></td>
  		
  		</tr>
  
 		 </c:forEach> 
  	
  
  
  </tbody>
  
  
  
  </table>
  
  </body>
</html>
