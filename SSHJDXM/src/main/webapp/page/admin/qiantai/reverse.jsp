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
	  ${userReverse.loginname }的预定<br/>
 
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
 
  	<c:forEach items="${userReverse.roomselects }" var="roomSelect" >
  		<form  action="${pageContext.request.contextPath }/admin/qiantai/transRoomIn" method="post">
  		<tbody> 
  			<tr>
  			<td> ${userReverse.phone}</td>
  			<td> ${roomSelect.rooms.no}</td>
  			<td> ${roomSelect.price}</td>
  			<td> ${roomSelect.chargemethod}</td>
  			<td> ${roomSelect.createdate}</td>
  			<td>  <input value="${roomSelect.enddate}" name="outDate">   </td>
  	
  			</tr>
  			<tr>
  			<td>身份证：</td>
  			<td><input name="userFir.idcard" placeholder="user1身份证" ></td>
  			<td><input name="userFir.name" placeholder="姓名"></td>
  			<td>男：<input type="radio" name="userFir.sex.id" value="1">&nbsp;女：<input type="radio" name="userFir.sex.id" value="2"></td>
  	
  			</tr>
  		
  			<tr>
  			<td>身份证：</td>
  			<td><input name="userTwo.idcard" placeholder="user2身份证"></td>
  			<td><input name="userTwo.name" placeholder="姓名"></td>
			<td>男：<input  type="radio" name="userTwo.sex.id" value="1">&nbsp;女：<input  type="radio" name="userTwo.sex.id" value="2"></td>
  	
  			</tr>
  			<tr>
  			<td>押金：</td>
  			<td><input placeholder="押金"  name="deposit"></td>
  			<td><input type="hidden" placeholder="会员id" value="${userReverse.id}" name="useraId" ></td>
  			<td><input type="hidden" placeholder="房间id" value="${roomSelect.rooms.id}" name="roomId" ></td>
  			<td><input type="hidden" placeholder="预定表id" value="${roomSelect.id }" name="roomSelect.id" ></td>
  			<td><input type="submit" value="办理入住" ></td>
  			</tr>
  			<tr><td colspan="10"><hr style="width: 1200px"/></td></tr>
  			</tbody>
  		</form>
  	</c:forEach>
  	 </table>	
  </body>
</html>
