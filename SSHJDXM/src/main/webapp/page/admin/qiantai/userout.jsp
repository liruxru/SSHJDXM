<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>userout</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/qiantai/userout.css" />
	<script type="text/javascript">
		$(function(){
			$("#but").click(function(){
 				$(this).attr('type','submit'); 
 				$("#div_outsuccess").show();
				setTimeout(hide,1500);//1.5秒后消失
			});
			function hide(){
				$("#div_outsuccess").animate({opacity: "hide"}, "slideUp");
			}
			//住店日期
			var date= new Date(Date.parse($("#creatDate").val().replace(/-/g, "/")));
			var d1=date.getTime();
			var d=new Date().getTime();
			var day=Math.floor(((d-d1)/23/3600/1000))+1;
			$("#day").val(day);
			var lidianriqi=new Date(Date.parse($("#lidianriqi").val().replace(/-/g, "/")));
			var discount1=$("#discount1").val();
			var discount2=$("#discount2").val();
			var min=discount1;
			if(discount2<discount1){
				min=discount2;
			} 
			var roomPrice=$("#roomroomprice").val();
			//应付
			var xiaofeifangjia= Math.floor(roomPrice*day*min);
			$("#xiaofeifangjia").text(xiaofeifangjia);
			//生成订单了
			var yifufangjia=$("#yifufangjia").val();
			var yingtuifangjia=yifufangjia-xiaofeifangjia;
			$("#yingtuifangjia").text(yingtuifangjia);
			var zhaoling=$("#zhaoling").val();
			var zongji=(yingtuifangjia*1)+(zhaoling*1);
			$("#zongji").text(zongji);
			
			 $("#zongji").append("<input type='hidden' name='zongji' value='"+zongji+"'/>"); 
		})
	</script>
  </head>
  
  <body>
    	<div id="all">
			<a href="${pageContext.request.contextPath}/admin/home.action">返回主页</a>
	        <div id="bt">用户前台办理退房</div>
			<form action="${pageContext.request.contextPath}/admin/qiantai/transRoomOut"  method="post">
			<div id="tables_left">
				<div class="input-box">
					<label>房 间 号:</label> 
					<input type="text" readonly="readonly"  value="${room.no}" />
				</div>
				<div class="input-box">
					<label>住店日期:</label>
					<c:forEach items="${room.customerorderses}" var ="orders" begin="0" end="0"> <c:set var="creatdate" value="${orders.createdate}"></c:set>  </c:forEach>	
					
					 <input type="text" readonly="readonly" id="creatDate"  value=<fmt:formatDate value="${creatdate}" pattern="yyyy-MM-dd" />  />
				</div>
				<div class="input-box">
					<label>离店日期:</label>
					<jsp:useBean id="now" class="java.util.Date" scope="page"/>				
					 <input type="text" readonly="readonly" value=<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /> />
				</div>
				<div class="input-box">
					<label>入住天数:</label>					
					 <input type="text" readonly="readonly"  id="day"  value="" />
				</div>
			</div>
			<div id="tables_center">
			<!-- 获取住店人信息 -->
			<input type="hidden" id="roomroomprice" value="${room.roomtypes.price}"/>
				<c:forEach items="${room.roomuserses}"  var="roomuser" varStatus="v">
			  		<c:if test="${v.count eq 1}">
			  		<c:set var="lidianriqi" value="${roomuser.moditfydate}"></c:set>
			  		<c:forEach items="${roomuser.usersputong.userses}"  var="user" begin="0" end="0">			  		
			  			<c:set value="${user.usersvip.roomdiscount}" var="discount1"></c:set>
			  			<input type="hidden" id="discount1" value="${discount1}"/>
			  		</c:forEach>
			  		
			  		<input type="hidden" value=<fmt:formatDate value="${lidianriqi}" pattern="yyyy-MM-dd" />  id="lidianriqi">
			  			<jsp:useBean id="u1" class="com.oracle.entity.Usersputong" >
		   					<jsp:setProperty name="u1" property="idcard"   value="${roomuser.usersputong.idcard}"/>
		  			 		<jsp:setProperty name="u1" property="name" value="${roomuser.usersputong.name}"/>
						</jsp:useBean>
					 	<jsp:useBean id="sex1" class="com.oracle.entity.Sexs" />
	  			 			<jsp:setProperty name="sex1" property="id"  value="${roomuser.usersputong.sex.id}"/>
	  			 			<c:forEach items="${roomuser.usersputong.userses}" var="upphone"  begin="0" end="0">
	  			 				<c:set var="u1phone" value="${upphone.phone}"></c:set>
	  			 			</c:forEach>
			  		</c:if>
			  		<c:if test="${v.count eq 2}">
			  		<c:forEach items="${roomuser.usersputong.userses}"  var="user" begin="0" end="0">	
			  			<c:set value="${user.usersvip.roomdiscount}" var="discount2"></c:set>
			  			<input type="hidden" id="discount2" value="${discount2}"/>
			  		</c:forEach>
		  			<jsp:useBean id="u2" class="com.oracle.entity.Usersputong" >
	  					<jsp:setProperty name="u2" property="idcard"  value="${roomuser.usersputong.idcard}"/>
	 			 		<jsp:setProperty name="u2" property="name" value="${roomuser.usersputong.name}"/>
					</jsp:useBean>
				 	<jsp:useBean id="sex2" class="com.oracle.entity.Sexs" />
	 			 		<jsp:setProperty name="sex2" property="id"  value="${roomuser.usersputong.sex.id}"/>
							<c:forEach items="${roomuser.usersputong.userses}" var="uphone" begin="0" end="0" >
	 			 			<c:set var="u2phone" value="${uphone.phone}"></c:set>
	 			 		</c:forEach>
			  		</c:if>
	  			</c:forEach>
				<div class="input-box">
					<label>会员ID:</label>
					 <input type="text" value="${u1.idcard}" readonly="readonly" id="u1IdCard"  class="searchBtn"  name="userFir.idcard" placeholder="user1身份证" />
				</div>
				<div class="input-box">
					<label>会员名称:</label> 
					<input type="text"  value="${u1.name}"  readonly="readonly" id="userFirName" name="userFir.name"  placeholder="请输入会员电姓名"/>
				</div>
				<div class="input-box">
					<label>会员性别:</label>
					男：<input <c:if test="${sex1.id eq 1}">checked="checked"</c:if> id="nan1" style="width :20px; margin-top: 18px; " type="radio" name="userFir.sex.id" value="1">
					女：<input  <c:if test="${sex1.id eq 2}">checked="checked"</c:if> id="nv1" style="width :20px; margin-top: 18px; " type="radio" name="userFir.sex.id" value="2">
				</div>
				<div class="input-box">
					<label>会员电话:</label> 
					<input type="text"  value="${u1phone}" readonly="readonly" id="userphone1"  placeholder="请输入非卖品名称"/>
				</div>
			</div>
			<div id="tables_right">
				<div class="input-box">
					<label>会员ID:</label>
					 <input type="text"  value="${u2.idcard}" readonly="readonly" id="u2IdCard"  class="searchBtn" name="userTwo.idcard" placeholder="user2身份证"/>
				</div>
				<div class="input-box">
					<label>会员名称:</label> 
					<input type="text"  value="${u2.name}" readonly="readonly" name="userTwo.name" id="userTwoName"  placeholder="请输姓名"/>
				</div>
				<div class="input-box">
					<label>会员性别:</label>
					男：<input  <c:if test="${sex2.id eq 1}">checked="checked"</c:if>   id ="nan2" style="width :20px; margin-top: 18px; " type="radio" name="userTwo.sex.id" value="1">
					女：<input <c:if test="${sex2.id eq 2}">checked="checked"</c:if> id ="nv2" style="width :20px; margin-top: 18px; " type="radio" name="userTwo.sex.id" value="2">
				</div>
				<div class="input-box">
					<label>会员电话:</label> 
					<input type="text"  value="${u2phone}" readonly="readonly" id="userphone2"  placeholder="请输入非卖品名称"/>
				</div>
			</div>
			<div id="table_foot">
			<div id="table_foot_biao">    
				<table cellspacing="0" cellpadding="0" border="1px" bordercolor="#ddd" align="center"  >
					<tbody> 
					<c:forEach items="${room.customerorderses}" var="order">
						<c:if test="${not empty order.usersputong}" >
							<tr>
								<td style="text-align: left;"><h3>入住房间的订单</h3></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
					   		 	<th>房间订单编号</th>
					   		 	<th>订单日期</th>
					   		 	<th>已付房价</th>
					   		 	<th>订单状态</th> 		 
			   		 		</tr>	
				   			<tr>
					   		 	<td>${order.sn}</td>
					   		 	<td>${order.createdate}</td>
					   		 	<td>${order.sum}</td>
					   		 	<c:set var="ordersum" value="${order.sum}"></c:set>
					   		 	<input type="hidden" value="${order.sum}" id="yifufangjia"/>
					   		 	<td>${order.orderstatus.orderstatus}</td>
				   		 	</tr>
				   		 	<tr>
				   		 	 	<td></td>
				   		 	 	<td></td>
				   		 	 	<td></td>
				   		 	 	<td></td>
				   		 	 </tr>
			   		 	 </c:if>
		  				<c:if test="${empty order.usersputong}" >
		  					<tr>
		  						<td style="text-align: left;"><h3>购买的商品订单</h3></td>
		  						<td></td>
				   		 	 	<td></td>
				   		 	 	<td></td>
		  					</tr>	
				   			 <tr>
					   		 	<td>${order.sn}</td>
					   		 	<td>${order.createdate}</td>
					   		 	<td>${order.sum}</td><c:set var="orderSum" value="${order.sum+orderSum}"/>
					   		 	<td>${order.orderstatus.orderstatus}</td>
				   		 	 </tr>
				   		 	 <tr>
				   		 	 	<td></td>
				   		 	 	<td></td>
				   		 	 	<td></td>
				   		 	 	<td></td>
				   		 	 </tr>
				  			 <tr>
				  			 	<td style="text-align: left;"><h3>商品订单详情</h3></td>
				  			 	<td></td>
				   		 	 	<td></td>
				   		 	 	<td></td>
				  			 </tr>
				   		 	<tr>
					   		 	<th>商品名</th>
					   		 	<th>商品价格</th>
					   		 	<th>商品数量</th>
					   		 	<th>小计</th> 
				   		 	</tr>
				   		 	<c:forEach items="${order.customerorderitemses}" var="orderItem" >
				   		 	<tr>
					   		 	<td>${orderItem.goods.name}</td>
					   		 	<td>${orderItem.price}</td>
					   		 	<td>${orderItem.goodsnum}</td>   		 	
					   		 	<td>${orderItem.subtotal}</td>
				   		 	 </tr>
				   		 	</c:forEach>
				   		 	 <tr> 
				   		 		<td></td>
				   		 	 	<td></td>
				   		 	 	<td></td>	
				   		 	 	<td></td>	
				   		 	 </tr>
				   		</c:if>
				   		</c:forEach>
			   		</tbody>
			   		<tfoot>
			  			<tr>
			  				<td style="text-align: left;"><h3>物品损坏信息</h3></td> 		
			   		 	 	<td></td>
			   		 	 	<td></td>
			   		 	 	<td></td>	
			  			</tr>
			  			<tr>
			  				<th>损坏物品</th> 	
			   		 	 	<th>物品描述</th>
			   		 	 	<th>物品价格</th>
			   		 	 	<th><input type="hidden" placeholder="房间id" value="${room.id}" name="roomId"></th>	
			  			</tr>
			  			<tr>
				  			<td>押金：${room.deposit}</td>
				  			<td>商品总价:${orderSum}</td>
				  			<td>找零:${room.deposit-orderSum}</td>
				  			<input type="hidden" value="${room.deposit-orderSum}" id="zhaoling" />
				  			<td></td>
			  			<tr>
			  			<tr>
				  			<td>应付房价：<span  id="xiaofeifangjia"></span></td>
				  			<td>已付房价:${ordersum}</td>
				  			<td>应退房价:<span id="yingtuifangjia"></span></td>
				  			<td>总计:<span id="zongji"></span></td>
			  			<tr>
		  			</tfoot>
				</table>
			</div>
			</div>
				<input type="submit" value="办        理         退         房" id="but" />
        	</form>
        </div>
        <div id="div_outsuccess" style="display: none">
			 退房成功
	   </div>
  </body>
</html>