<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	
  </head>
  
  <body>
   		<h1>用户准备退房的界面</h1>	
   	
  

 	<c:if test="${not empty room }">
 	 房间号码：${room.no }<br/>  
 		<form  action="${pageContext.request.contextPath }/admin/qiantai/transRoomOut"   method="post" >
  		<table>  	
  		
  		<tbody> 
  		
  		<c:forEach items="${room.roomuserses }"  var="roomuser">
	  			<tr>
	  			<td>身份证：</td>
	  			<td><input   value="${roomuser.usersputong.idcard }"    ></td>
	  			<td><input  value="${roomuser.usersputong.name }"></td>
	  			<td>性别：${roomuser.usersputong.sex.sex}</td>
	  			</tr>
	  			<tr><td colspan="10"></td></tr>
	  			<tr><td colspan="10"><hr style="width: 1200px"/></td></tr>  
  		</c:forEach>
  		
  		
  		
  		
  		
  		
  		
  		<c:forEach items="${room.customerorderses }" var="order">
  			<c:if test="${not empty order.usersputong }" >
  				<tr><td><h2>入住房间订单</h2></td>	</tr>	
	  			 <tr>
		   		 	<td>房间订单编号</td>
		   		 	<td>订单日期</td>
		   		 	<td>订单房价</td>
		   		 	<td>订单状态</td> 		 
	   		 	</tr>	
	   			 <tr>
		   		 	<td>${order.sn }</td>
		   		 	<td>${order.createdate }</td>
		   		 	<td>${order.sum }</td>
		   		 	<td>${order.orderstatus.orderstatus}</td>
	   		 	 </tr>
	   		 	 <tr>
	   		 	 <td colspan="10"><hr/></td>
	   		 	 </tr>
  			</c:if>
	  		<c:if test="${empty order.usersputong }" >
	  			<tr><td><h2>购买的商品订单</h2>	</td></tr>	
	  			 <tr>
		   		 	<td>订单编号</td>
		   		 	<td>订单日期</td>
		   		 	<td>订单总价</td>
		   		 	<td>订单状态</td> 		 
	   		 	</tr>	
	   			 <tr>
		   		 	<td>${order.sn }</td>
		   		 	<td>${order.createdate }</td>
		   		 	<td>${order.sum }   </td><c:set var="orderSum" value="${order.sum+orderSum }"/>
		   		 	<td>${order.orderstatus.orderstatus}</td>
	   		 	 </tr>
	   		 	 <tr>
	   		 	 	<td colspan="10"><hr/></td>
	   		 	 </tr>
	  			 <tr><td>商品订单详情</td></tr>
	   		 	<tr>
		   		 	<td>商品名</td>
		   		 	<td>商品价格</td>
		   		 	<td>商品数量</td>
		   		 	<td>小计</td> 
	   		 	</tr>
	   		 	<c:forEach items="${order.customerorderitemses }" var="orderItem" >
	   		 	<tr>
	   		 	<td>${orderItem.goods.name }</td>
	   		 	<td>${orderItem.price }</td>
	   		 	<td>${orderItem.goodsnum }</td>   		 	
	   		 	<td>${orderItem.subtotal }</td>
	   		 	 </tr>
	   		 	</c:forEach>
	   		 	
	   		 	 <tr> 
	   		 	 <td colspan="10"><hr  width="1024px" size=6 noshade=noshade color=red /></td>
	   		 	 </tr>
	   		</c:if>
	   		</c:forEach>
  			</tbody>
  			
  			<tfoot>
  			<tr>
  				<td colspan="10">物品损坏信息</td> 			
  			</tr>
  			<tr>
  				<td>损坏物品</td> 			
  			</tr>
  			<tr>
	  			<td>押金：${room.deposit }</td>
	  			<td>商品总价:${orderSum }</td>
	  			<td>损坏总价:<input value="0"     ></td>
	  			<td>找零:${room.deposit-orderSum  }</td>
  			</tr> 
  			<tr>
  				<td><input type="hidden" placeholder="房间id" value="${room.id}" name="roomId" ></td>
  				<td><input type="submit" value="办理退房" ></td>
  			</tr>	
  			</tfoot>
  			</table>
  			
  		</form> 
 	
 	</c:if>
   	
   	
  </body>
</html>
