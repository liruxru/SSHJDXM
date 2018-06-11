<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$(".shouhuoBtn").click(function(){
    		
    			var orderId=$(this).attr("name");
    			var obj=$(this);
    			var objstatu=obj.parent().prev();
    			var url="${pageContext.request.contextPath}/room/order/alertOrder"
							$.post(url,{"orderId":orderId},function(res){
							
								if(res==0){
									obj.remove();
									objstatu.text("已经收货");
								}
							
							},'json')
    		
    		
    		})
    	
    	
    	
    	})
    
    
    </script>
	
  </head>
  
  <body>
  全部订单如下:
  
  
  	<table>
   		<thead>
   		 <tr>
   		 
   		 	<td>订单编号</td>
   		 	<td>订单日期</td>
   		 	<td>订单总价</td>
   		 	<td>订单状态</td>
   		 	
   		 	
   		 	
   		 
   		 </tr>
   		
   		</thead>
   	<tbody>   	
   		<c:forEach items="${orderList }" var="order">
   			 <tr>
   		
   		 	<td>${order.sn }</td>
   		 	<td>${order.createdate }</td>
   		 	<td>${order.sum }</td>
   		 	<td>${order.orderstatus.orderstatus }</td>
   		 	
   		 	<td><c:if test="${order.orderstatus.id eq 5 }">
   		 	<input type="button" value="确认收货"  name="${order.id }" class="shouhuoBtn"/>
   		 	</c:if></td>
   		 	 </tr>
   		 	 
   		 	<c:forEach items="${order.customerorderitemses }" var="orderItem" >
   		 	<tr>
   		 	<td>${orderItem.goods.name }</td>
   		 	<td>${orderItem.price }</td>
   		 	<td>${orderItem.goodsnum }</td>   		 	
   		 	<td>${orderItem.subtotal }</td>
   		 	<td></td>
   		 	 </tr>
   		 	</c:forEach>
   		 	
   		 	 <tr> 
   		 	 <td colspan="10"><hr/></td>
   		 	 </tr>
   	
   		</c:forEach>
   	
   	</tbody>
   	
   </table>
  

   
  </body>
</html>
