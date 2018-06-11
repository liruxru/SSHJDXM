<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>userin</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/qiantai/WdatePicker.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/daterangepicker.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/qiantai/userin.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/lang/zh-cn.js"></script>
	<script type="text/javascript">
	$(function(){	
 		$("#div_message").hide();//初始 消失
 		$("#div_addsuccess").hide();
 		
 		$("#but").click(function(){
 			var id1=$("#u1IdCard").val().trim();
			var lidiandate=$("#demo1").val().trim();	
			var yajin=$("#yajin").val();
 			if(id1=="" || lidiandate=="" || yajin==""){
 				$("#div_message").show();
				setTimeout(hide,1500);//1.5秒后消失
 				return false;
 			}else{
 				$(this).attr('type','submit'); 
 				$("#div_addsuccess").show();
				setTimeout(hide,1500);//1.5秒后消失
 			}
 		});
 		function hide(){
			$("#div_message").animate({opacity: "hide"}, "slideUp");
			$("#div_addsuccess").animate({opacity: "hide"}, "slideUp");
		}
 		$("#demo1").focus(function(){
 			$("#day").val("-");
			$("#dollar").val("-");
			$("#finalDollar").val("-");
 		})
		$("#demo1").blur(function(){
			var date= new Date(Date.parse($("#demo1").val().replace(/-/g,"/")));
			var d=date.getTime();
			var d1=new Date().getTime();
			var day=Math.floor(((d-d1)/24/3600/1000))+1;
			if(day==0){
				day=day+1;
			}
			var price=$("#price").val();
			$("#day").val(day);
			$("#dollar").val(Math.floor(day*price));
			$("#finalDollar").val(Math.floor(day*price));
		})
		/*身份证失焦检测用户是否为会员*/
		$(".searchBtn1").blur(function(){
			var min=1;
			var nan1=$("#nan1");
			var nv1=$("#nv1");
			var xxx1=$("#xxx1");
			var dollar=$("#dollar").val()*1;
			var id1 = $("#u1IdCard").val();
			var userFirName=$("#userFirName");
			var userphone1=$("#userphone1");
			var vipType1=$("#vipType1");
			var vipDiscount1=$("#vipDiscount1");
			var vipDiscount2=$("#vipDiscount2").val();
			var url="/SSHJDXM/admin/qiantai/searchUser1";
			$.post(url,{"userFir.idcard":id1},function(res){
				if(!$.isEmptyObject(res)){
					userFirName.val(res.usersputong.name);
					vipType1.val(res.usersvip.vipname);
					vipDiscount1.val(res.usersvip.roomdiscount);
					userphone1.val(res.phone);
					if(res.usersputong.sex.id==1){
						nan1.prop("checked",true);
					}else{
						nv1.prop("checked",true);
					}
					xxx1.val(res.id);
					min=res.usersvip.roomdiscount;
					if(min<=vipDiscount2){
						$("#finalDollar").val(Math.floor(min*dollar));
					}else{
						$("#finalDollar").val(Math.floor(vipDiscount2*dollar));
					}
				}else{
					userFirName.val("");
					vipType1.val("");
					vipDiscount1.val(1);
					userphone1.val("");
					$("#finalDollar").val(Math.floor(min*dollar));
				}
				},'json');
			})
			
		$(".searchBtn2").blur(function(){
			var min=1;
			var nan2=$("#nan2");
			var nv2=$("#nv2");
			var xxx2=$("#xxx2");
			var dollar=$("#dollar").val()*1;
			var id2 = $("#u2IdCard").val();
			var userTwoName=$("#userTwoName");
			var userphone2=$("#userphone2");
			var vipType2=$("#vipType2");
			var vipDiscount2=$("#vipDiscount2");
			var vipDiscount1=$("#vipDiscount1").val();
			var url="${pageContext.request.contextPath}/admin/qiantai/searchUser2";
			$.post(url,{"userTwo.idcard":id2},function(res){
				if(!$.isEmptyObject(res)){
					userTwoName.val(res.usersputong.name);
					vipType2.val(res.usersvip.vipname);
					vipDiscount2.val(res.usersvip.roomdiscount);
					userphone2.val(res.phone);
						
					if(res.usersputong.sex.id==1){
							nan2.prop("checked",true);
						}else{
							nv2.prop("checked",true);
						}
						xxx2.val(res.id);
						min=res.usersvip.roomdiscount;
						if(min<=vipDiscount1){
							$("#finalDollar").val(Math.floor(min*dollar));
						}else{
							$("#finalDollar").val(Math.floor(vipDiscount1*dollar));
						}
					}else{
						userTwoName.val("");
						vipType2.val("");
						vipDiscount2.val(1);
						userphone2.val("");
						$("#finalDollar").val(Math.floor(min*dollar));
					}
			},'json')
		})
		
		/* $(".searchPhoneBtn").click(function(){
			var phone=$("#searchPhone").val();
			var obj1= $("#u1IdCard").parent().parent().next().children();
			var dollar=$("#dollar").text()*1;
			var url="/SSHJDXM/admin/qiantai/searchUserByPhone";
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
				}else{
					obj1.html("");
				}
			},'json')
		}) */
	})
	</script>
  </head>
  
  <body>
    	<div id="all">
			<a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
	        <div id="bt">用户前台办理入住</div>

			<form action="${pageContext.request.contextPath}/admin/qiantai/transRoomInNoReverse" method="post" submit="return false">
			<div id="tables_left">
				<div class="input-box">
					<label>房 间 号:</label> 
					<input type="text" readonly="readonly"  value="${room.no}" />
					<input type="hidden" value="${room.id}" name="roomId" />
				</div>
				<div class="input-box">
					<label>房间类型:</label> 
					<input type="text" readonly="readonly"  value="${room.roomtypes.roomtype}"/>
				</div>
				<div class="input-box">
					<label>房间价格:</label>
					<input type="text" readonly="readonly" id="price"  value="${room.roomtypes.price}"/>
				</div>
				<div class="input-box">
					<label>预离日期:</label>
					<input class="laydate-icon" id="demo1" onClick="WdatePicker()"  name="outDate" value="" placeholder="请选择退房日期" />
				</div>
				<div class="input-box">
					<label>入住天数:</label>
					 <input type="text" readonly="readonly" id="day"  />
				</div>
				<div class="input-box">
					<label>房间总价:</label>
					 <input type="text" readonly="readonly" id="dollar"  />
				</div>
				<div class="input-box">
					<label>优惠价格:</label>
					 <input type="text" readonly="readonly" id="finalDollar" value="${room.roomtypes.price }"  name="price" />
				</div>
			</div>
			<div id="tables_center">
			<!-- 用户1的会员id -->
			<input type="hidden" id="xxx1" value="" name="useraId"/>
				<div class="input-box">
					<label>会员ID:</label>
					 <input type="text" id="u1IdCard"  class="searchBtn1"  name="userFir.idcard" placeholder="user1身份证" />
				</div>
				<div class="input-box">
					<label>会员名称:</label> 
					<input type="text"  id="userFirName" name="userFir.name"  placeholder="请输入会员姓名"/>
				</div>
				<div class="input-box">
					<label>会员性别:</label>
					男：<input  id="nan1" style="width :20px; margin-top: 18px; " type="radio" name="userFir.sex.id" value="1">
					女：<input id="nv1" style="width :20px; margin-top: 18px; " type="radio" name="userFir.sex.id" value="2">
				</div>
				<div class="input-box">
					<label>会员电话:</label> 
					<input type="text" id="userphone1" name="" value="" placeholder="请输入会员电话"/>
				</div>
				
				<div class="input-box">
					<label>会员类型:</label>
					 <input type="text" id="vipType1" readonly="readonly"   value="" placeholder="会员类型"/>
				</div>
				<div class="input-box">
					<label>会员折扣:</label>
					 <input type="text"  id="vipDiscount1" readonly="readonly" name="" value="1" placeholder="会员折扣"/>
				</div>
				<div class="input-box">
					<label>押金金额:</label>
					 <input type="text" name="deposit" id="yajin"  placeholder="请输入押金金额"/>
				</div>
			</div>
			<div id="tables_right">
			<div class="input-box">
			<!-- 用户2的会员id -->
			<input type="hidden" id="xxx2" value="" name="userbId"/>
					<label>会员ID:</label>
					 <input type="text" id="u2IdCard"  class="searchBtn2" name="userTwo.idcard" placeholder="user2身份证"/>
				</div>
				<div class="input-box">
					<label>会员名称:</label> 
					<input type="text" name="userTwo.name" id="userTwoName"  placeholder="请输会员姓名"/>
				</div>
				<div class="input-box">
					<label>会员性别:</label>
					男：<input  id ="nan2" style="width :20px; margin-top: 18px; " type="radio" name="userTwo.sex.id" value="1">
					女：<input id ="nv2" style="width :20px; margin-top: 18px; " type="radio" name="userTwo.sex.id" value="2">
				</div>
				<div class="input-box">
					<label>会员电话:</label> 
					<input type="text" id="userphone2" name="" value="" placeholder="请输入会员电话"/>
				</div>
				
				<div class="input-box">
					<label>会员类型:</label>
					 <input type="text" id="vipType2" readonly="readonly" name="" value="" placeholder="会员类型"/>
				</div>
				<div class="input-box">
					<label>会员折扣:</label>
					 <input type="text"  id="vipDiscount2" readonly="readonly" name="" value="1" placeholder="会员折扣"/>
				</div>
				<input type="button" value="办理入住" id="but" />
			</div>
        	</form>
        </div>
       <div id="div_message" style="display: none">
			 信息选择不完整
	   </div>
       <div id="div_addsuccess" style="display: none">
			 添加成功
	   </div>
  </body>
</html>