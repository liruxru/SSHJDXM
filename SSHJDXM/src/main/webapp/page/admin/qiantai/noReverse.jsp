<%-- <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
	
		$(function(){
		$("#outDate").blur(function(){
			var date= new   Date(Date.parse($(this).val().replace(/-/g,   "/")));
			var d1=date.getDate();
			var d=new Date().getDate();
			var day=d1-d;
			if(day<1)day=1;
			var price=$("#price").text();
			$("#day").text(day);
			$("#dollar").text(day*price);			
			$("#finalDollar").val(day*price);
		})
		/*检测用户是否为会员*/
		$(".searchBtn").click(function(){
			var dollar=$("#dollar").text()*1;
			var id1 = $("#u1IdCard").val();
			var id2 = $("#u2IdCard").val();
			var obj1= $("#u1IdCard").parent().parent().next().children();
			var obj2= $("#u2IdCard").parent().parent().next().children();
			var url="${pageContext.request.contextPath }/admin/qiantai/searchUser";
			$.post(url,{"userFir.idcard":id1,"userTwo.idcard":id2},function(res){
				var min=1;
				if(!$.isEmptyObject(res.user1)){
					obj1.append("会员登录名："+res.user1.loginname+"会员类型："+res.user1.usersvip.vipname+"会员折扣："+res.user1.usersvip.roomdiscount);
					obj1.append("<input type='hidden' value='"+res.user1.id+"' name='useraId'/>");
					 min=res.user1.usersvip.roomdiscount;
				}
				if(!$.isEmptyObject(res.user2)){
					obj2.append("会员登录名："+res.user2.loginname+"会员类型："+res.user2.usersvip.vipname+"会员折扣："+res.user2.usersvip.roomdiscount)
					obj2.append("<input type='hidden' value='"+res.user2.id+"' name='userbId'/>");
				min=res.user2.usersvip.roomdiscount;
				}
				var price=min*dollar;
				$("#finalDollar").val(price);
			},'json')
		})
		$(".searchPhoneBtn").click(function(){
			var phone=$("#searchPhone").val();
			var obj1= $("#u1IdCard").parent().parent().next().children();
			var dollar=$("#dollar").text()*1;
			var url="${pageContext.request.contextPath }/admin/qiantai/searchUserByPhone";
			/*******************************************************************/
			var u1=	$("#u1IdCard");
			var u1name=$("#userFirName");
			var nan=$("#nan");
			var nv=$("#nv");
			$.post(url,{"phone":phone},function(res){
				if(!$.isEmptyObject(res)){
					obj1.html("");
					obj1.append("会员登录名："+res.loginname+"会员类型："+res.usersvip.vipname+"会员折扣："+res.usersvip.roomdiscount);
					obj1.append("<input type='hidden' value='"+res.id+"' name='useraId'/>");
					alert(res.usersputong.idcard)
					u1.val(res.usersputong.idcard);
					u1name.val(res.usersputong.name);
					if(res.usersputong.sex.id==1){
						nan.prop("checked",true);
					}else{
						nv.prop("checked",true);
					}
					var price=res.usersvip.roomdiscount*dollar;
					$("#finalDollar").val(price);
				}
			},'json')
		})
	})
	
	</script>
  </head>
  
  <body>
  <h1>user直接来前台直接入住管理员预定页面</h1>
	该房间 已经预定的日期 
	<c:forEach items="${room.roomselects }" var="roomsel">
  		
  		<fmt:formatDate value="${roomsel.createdate }" pattern="MM月dd日" />
  		-<fmt:formatDate value="${roomsel.enddate }" pattern="MM月dd日" /><br/>
  		</c:forEach>
	
	  <br/>
  	该房间 		现在的状态 ${room.roomstatus.status }  <br/>
 <h2>${errorUserIn }</h2><br/>
   </td>
 
<input id="searchPhone"/>	<input class="searchPhoneBtn" type="button" value="通过电话检测会员"/>
 
  	
  		<form  action="${pageContext.request.contextPath }/admin/qiantai/transRoomInNoReverse"   method="post" >
  		<table>
  		<thead><tr><td>预期 离店日期</td><td>  <input id="outDate" name="outDate"> </tr></thead>
		 	<thead><tr>			
		  			
		  			<td> 房间编号</td>
		  			<td> 房间类型</td>
		  			<td> 房间价格</td>
		  			
		  			<td>入住天数</td>
		  			
		  			<td>价格</td>
		  	
		  	</tr>
		  	
		  	</thead> 	
  	
  		
  		<tbody> 
  			<tr>
	  			<td> ${room.no}</td>
	  			<td> ${room.roomtypes.roomtype }</td>
	  			<td id="price"> ${room.roomtypes.price }</td>  			
	  			
	  			<td id="day"> </td>  
	  			<td id="dollar"> </td>  		
  			</tr>
  			<tr>
	  			<td>身份证：</td>
	  			<td><input id="u1IdCard"  name="userFir.idcard" placeholder="user1身份证" ></td>
	  			<td><input id="userFirName" name="userFir.name" placeholder="姓名"></td>
	  			<td>男：<input  id="nan" type="radio" name="userFir.sex.id" value="1">&nbsp;女：<input id="nv" type="radio" name="userFir.sex.id" value="2"></td>
  			</tr>
  			<tr><td colspan="10"></td></tr>
  			<tr><td colspan="10"><hr style="width: 1200px"/></td></tr>  		
  			<tr>
	  			<td>身份证：</td>
	  			<td><input id="u2IdCard" name="userTwo.idcard" placeholder="user2身份证"></td>
	  			<td><input name="userTwo.name" placeholder="姓名"></td>
				<td>男：<input  type="radio" name="userTwo.sex.id" value="1">&nbsp;女：<input  type="radio" name="userTwo.sex.id" value="2"></td>
  			</tr>
  			<tr><td colspan="10"></td></tr>
  			<tr><td colspan="10"><hr style="width: 1200px"/></td></tr>  		
  			<tr>
  			<td>成交房价<input  id="finalDollar" value="${room.roomtypes.price }"  name="price"></td>
  			<td>押金：</td>
  			<td><input placeholder="押金"  name="deposit"></td>
  			
  			<td><input type="hidden" placeholder="房间id" value="${room.id}" name="roomId" ></td>
  			
  			<td><input class="searchBtn" type="button" value="通过身份证检测会员"/></td>  
  			<td><input type="submit" value="办理入住" ></td>
  		
  			</tr> 	
  		
  			</tbody>
  		</form>
  	
  	
  	 
  	 		
  
  	
  	 </table>	
  		
  
  
  

  
  
  </body>
</html>
 --%>