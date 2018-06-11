<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	
  </head>
  
  <body>
  <h1>管理员通过电话查询出的用户预定页面</h1>
	  在这个页面办理入住:预定信息
  <table>
  	<thead><tr>
  	
  	<td> 预定电话</td>
  			<td> 预定房间</td>
  			<td> 支付价格</td>
  			<td> 支付方式</td>
  			<td>入住日期</td>
  			<td>预期 离店日期</td>
  	
  	</tr>
  	
  	</thead>
  	<tbody> 
  	<c:forEach items="${roomSelectList }" var="roomSelect" >
  	<div>
  		
  		<form  action="${pageContext.request.contextPath }/admin/qiantai/" method="post">
  			<tr>
  			<td> ${roomSelect.users.phone }</td>
  			<td> ${roomSelect.rooms.no }</td>
  			<td> ${roomSelect.price }</td>
  			<td> ${roomSelect.chargemethod }</td>
  			<td> ${roomSelect.createdate }</td>
  			<td> ${roomSelect.enddate }</td>
  	
  		</tr>
  			<tr>
  		<td>身份证：</td>
  			<td><input name="user1.idcard" placeholder="user1身份证"></td>
  			<td><input name="user1.name" placeholder="姓名"></td>
  			<td>男：<input type="radio" name="user1.sex.id" value="1">&nbsp;女：<input type="radio" name="user1.sex.id" value="2"></td>
  	
  		</tr>
  		
  		<tr>
  			<td>身份证：</td>
  			<td><input name="user2.idcard" placeholder="user2身份证"></td>
  			<td><input name="user2.name" placeholder="姓名"></td>
	<td>男：<input  type="radio" name="user2.sex.id" value="1">&nbsp;女：<input  type="radio" name="user2.sex.id" value="2"></td>
  	
  		</tr>
  		<tr>
  			<td>押金：</td>
  			<td><input placeholder="押金" ></td>
  			<td><input type="hidden" placeholder="会员id" value="${roomSelect.users.id }" name="userId" ></td>
  			<td><input type="submit" value="办理入住" ></td>
  	
  		</tr>
  	
  		
  		
  		</form>
  	</div><br/>
  		<hr/>
  		
  	</c:forEach>
  	
  	
  		
  	</tbody>
  
  
  </table>
  
  
  </body>
</html>
