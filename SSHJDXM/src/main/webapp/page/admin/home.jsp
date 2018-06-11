<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>后台管理——首页</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<link href="${pageContext.request.contextPath}/css/smartMenu.css" rel="stylesheet" />   

	<!-- 动态菜单JS -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-smartMenu.js"></script>
	<script type="text/javascript">
	    $(function() {
		    $("#div_adderr").hide();//初始 消失
		    $("#div_dianhuachaxun").hide();//初始 消失
		    function hide(){
				$("#div_adderr").animate({opacity: "hide"}, "slideUp");
				$("#div_dianhuachaxun").animate({opacity: "hide"}, "slideUp");
			}
		    //网页第一次加载时这个数组存储所有房间类型的名字，通过$("#"+arr[i])可以获取这个对象的input元素，在通过attr("name")获取这个状态的房间的数量
		    var arr=new Array("","noMakeRooms","nullRooms","dirtyRooms","propleRooms","peopleDirtyRooms","yudingRooms","repairRooms");
			var arrnum=new Array("",$("#"+arr[1]).attr("name"),$("#"+arr[2]).attr("name"),$("#"+arr[3]).attr("name"),
			$("#"+arr[4]).attr("name"),$("#"+arr[5]).attr("name"),$("#"+arr[6]).attr("name"),$("#"+arr[7]).attr("name"));		       
			//阻止浏览器默认右键点击事件
	        document.oncontextmenu = function() {
	            return false;
	        }
	        //某元素组织右键点击事件
	        $(".roomssss").on("contextmenu", function(){
	            return false;
	        })
	        $(".roomssss").on("mousedown",(function(e) {	         
	      	//存储这个div对象,这个对象触发事件以后，修改他的颜色
	      	var obj=$(this);
	      	//div.attr("name") 获取存储房间id
	      	var roomsid=$(this).attr("name");
	      	//这个div里有一个隐藏的input  通过$("#status"+roomsid)拿到这个元素,同时.val() 获取他的状态id <input type="hidden" value="${mko.roomstatus.id}" id="status${mko.id}" />	
	        var objStau=$("#status"+roomsid);
	        var status=$("#status"+roomsid).val();
	        //数量需要减少的input对象  通过$("#"+arr[status])可以拿到存储被修改房间状态的的input对象，主页最底下那一栏
	        var objjian=$("#"+arr[status]);
	        /* 1.不可用2.空3.脏4.入住5.入住脏6.预定7.维修 */
	        //数量需要减少的input对象
// 		    alert("当前状态代码id："+status); 
	        //获取维修数量那个input
	        var repairRooms	=$("#repairRooms");
	        //获取空闲那个input
	        var nullRooms=$("#nullRooms");
	          //获取不可用那个input
	        var noMakeRooms=$("#noMakeRooms");
	          //获取主人脏那个input
	        var peopleDirtyRooms=$("#peopleDirtyRooms");

            //右键为3
	        if (3 == e.which) {
				var opertionn = {  
	                name: "",  
	                offsetX: 50,  
	                offsetY: -20,  
	                textLimit: 10,  
	                beforeShow: $.noop,  
	                afterShow: $.noop  
	            };  
				  var imageMenuData = [
				  	[{  
				        text: "开房",  
				        func: function () {  
				        	if(status==2){
								window.location.href="${pageContext.request.contextPath}/admin/qiantai/getRoomById?roomId="+roomsid;
				        	}else{
				        		$("#div_adderr").show();
								setTimeout(hide,2000);//1.5秒后消失
				 				return false;
				        	}
				        }  
				    }],  
				    [{  
				        text: "退房",  
				        func: function () {  
							window.location.href="${pageContext.request.contextPath}/admin/qiantai/userOut?roomId="+roomsid;
				        }  
				    }],   
				    [{  
				        text: "修改为空闲",  
				        func: function () { 
				        var url="${pageContext.request.contextPath}/admin/qiantai/alterRoomStatus"; 
				           $.post(url,{"room.id":roomsid,"room.roomstatus.id":2},function(res){
				           		//修改为空闲成功
				           		if(res==0){
					           		//改颜色
					           		obj.css("background-color","C0C0C0")
					           		//修改存储空闲房间数量的数组值+1
					           		arrnum[2]=arrnum[2]*1+1;
					           		//修改存储空闲房间数量的input的value值
					           		nullRooms.val("空闲房间:"+arrnum[2]);
					           		//数量需要减少的input对象,拿到他的value字符串
					           		var chuan=objjian.val();			           		
					           		chuan=chuan.substring(0, chuan.indexOf(":")+1);
					           		arrnum[status]=arrnum[status]-1;
					           		objjian.val(chuan+(arrnum[status]));
					           		objStau.val(2);
					           		$.smartMenu.remove();
				           		}
				           },'json')
				        }  
				    }], 
				    [{  
				        text: "修改为维修",  
				        func: function () { 
					        var description=prompt("输入描述信息");
					        if(description!=null && description!=""){
						        var path="${pageContext.request.contextPath}/admin/qiantai/alterRoomStatus";  
								 $.ajax({ 
									url: path, 
									data:{"room.id":roomsid,"room.roomstatus.id":7,"repair.description":description},
							        type: "post", 				
							       	cache:false,					
							        async:false, 
							        dataType: "json", 
							        success: function(res){ 
										//修改为维修成功
						           		if(res==0){
							           		//改颜色
							           		obj.css("background-color","CACA00")
							           		//改数量
							           		arrnum[7]=arrnum[7]*1+1;
							           		repairRooms.val("维修房间:"+arrnum[7]);
							           		var chuan=objjian.val();
							           		chuan=chuan.substring(0, chuan.indexOf(":")+1);
							           		arrnum[status]=arrnum[status]-1;
							           		objjian.val(chuan+(arrnum[status]));
							           		objStau.val(7);
							           		$.smartMenu.remove();
						           		}
							   		} 
								});
					    	 }else{
					    	 	return;
					    	 }
				         }
				    }],
				    [{  
				        text: "改为不可用",  
				        func: function () {  
							  var path="${pageContext.request.contextPath}/admin/qiantai/alterRoomStatus";  
							 $.ajax({ 
								url: path, 
								data:{"room.id":roomsid,"room.roomstatus.id":1},
						        type: "post", 				
						       	cache:false,					
						        async:false, 
						        dataType: "json", 
						        success: function(res){ 
									//修改为维修成功
					           		if(res==0){
						           		//改颜色
						           		obj.css("background-color","970000")
						           		//改数量
						           		arrnum[1]=arrnum[1]*1+1;
						           		noMakeRooms.val("不可用房:"+arrnum[1]);
						           		var chuan=objjian.val();
						           		chuan=chuan.substring(0, chuan.indexOf(":")+1);
						           		arrnum[status]=arrnum[status]-1;
						           		objjian.val(chuan+(arrnum[status]));
						           		objStau.val(1);
						           		$.smartMenu.remove();
					           		}
						   		} 
							});
				        }  
				    }],  
				  	[{  
				        text: "改为住人脏",  
				        func: function () {  
 						var url="${pageContext.request.contextPath}/admin/qiantai/alterRoomStatus"; 
				           $.post(url,{"room.id":roomsid,"room.roomstatus.id":5},function(res){
				           		//修改为空闲成功
				           		if(res==0){
					           		//改颜色
					           		obj.css("background-color","80BEBF")
					           		//修改存储空闲房间数量的数组值+1
					           		arrnum[5]=arrnum[5]*1+1;
					           		//修改存储空闲房间数量的input的value值
					           		peopleDirtyRooms.val("住人脏房:"+arrnum[5]);
					           		//数量需要减少的input对象,拿到他的value字符串
					           		var chuan=objjian.val();			           		
					           		chuan=chuan.substring(0, chuan.indexOf(":")+1);
					           		arrnum[status]=arrnum[status]-1;
					           		objjian.val(chuan+(arrnum[status]));
					           		objStau.val(5);
					           		$.smartMenu.remove();
				           		}
				           },'json')
				          }				     
				    }] 
			];   
// 	      		$(".xiangqing").attr("data-target","");  
// 	            oTable.$('td.td_selected').removeClass('td_selected'); 
	            if ($(e.target).hasClass('td_selected')) {  
	                $(e.target).removeClass('td_selected');  
	            } else {  
	                $(e.target).addClass('td_selected');  
	            }   
	            $(this).smartMenu(imageMenuData, opertionn);  
          	 } /*  else if(1 == e.which){   
                    $(".xiangqing").attr("data-target","#myModal");
          
              }    */
              $.smartMenu.remove();
 			}));  
 			
			$("#biaozhundanren").click(function(){
				window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByTypes.action?room.roomtypes.id=1";
			});
			$("#biaozhunshuangren").click(function(){
				window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByTypes.action?room.roomtypes.id=2";
			});
			$("#dachuangfang").click(function(){
				window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByTypes.action?room.roomtypes.id=3";
			});
			$("#haohuataofang").click(function(){
				window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByTypes.action?room.roomtypes.id=4";
			});
			$("#zhongdianfang").click(function(){
				window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByTypes.action?room.roomtypes.id=5";
			});
 			$("#yudingchaxun").bind("click",function(){
			  	var yudingphone=$("#yudingphone").val().trim();
			  	if(yudingphone==""){
			  		$("#div_dianhuachaxun").show();
					setTimeout(hide,2000);//1.5秒后消失
	 				return false;
			  	}else{
			  		
				  	window.location.href="${pageContext.request.contextPath}/admin/qiantai/findReverseByPhone?phone="+yudingphone;
			  	}
			});
	 })
    </script>
</head>

<body>
	<div class="container">
		<div class="cont-top">
			<!-- <div class="companyname" style="background-color: red;">老相好</div> -->
			<div class="cont-top-middle">			
				<c:if test="${empty admin}">
					<a href="${pageContext.request.contextPath}/admin/goToAdminLogin.action">登录</a>
				</c:if>
				<c:if test="${not empty admin}">
					<h3 style="color: 337AB7;">欢迎[ ${admin.loginname} ]进入 老相好酒店管理系统</h3>
					<a href="${pageContext.request.contextPath}/admin/adminLoginOut.action">安全退出</a>		
				</c:if>				
			</div>
		</div> 
		<div class="cont-top-right" >
			<button type="button" id="biaozhundanren" class="btn btn-primary">标准单人</button>
			<button type="button" id="biaozhunshuangren" class="btn btn-primary">标准双人</button>
			<button type="button" id="dachuangfang" class="btn btn-primary">大床单人</button>
			<button type="button" id="haohuataofang" class="btn btn-primary">豪华套房</button>
			<button type="button" id="zhongdianfang" class="btn btn-primary">钟点房</button>&nbsp;&nbsp;&nbsp;
			<input type="text" id="yudingphone" >
			<button type="button" id="yudingchaxun" class="btn btn-primary">预定电话查询</button>
			<span id="phoneerror">${phoneerror}</span>
			
		</div>
		<div class="left-menu" style="height:949px;">
			<div class="menu-list">
				<ul>
					<li class="menu-list-01"><a href="javascript:void(0)">
							<p class="fumenu">后台管理</p> 
					</a></li>

					<li class="menu-list-02">
						<p class="fumenu">前台营业</p>
						 <img class="xiala" src="img/adminManagement/xiala.png" />
						<div class="list-p">
							<a href="${pageContext.request.contextPath}/admin/listGoodSrevlet">
								<p class="zcd" id="zcd5">散客开户</p>
							</a>					
						</div>
					</li>

					<li class="menu-list-03">
						<p class="fumenu">会员管理</p> 
						<img class="xiala" src="img/adminManagement/xiala.png" />
						<div class="list-p">
							<a  href="${pageContext.request.contextPath}/admin/huiyuan/userlist"><p class="zcd" id="zcd15">查看全部会员</p></a>
							<a  href="${pageContext.request.contextPath}/admin/huiyuan/useredit"><p class="zcd" id="zcd15">添加会员</p></a>
						</div>
					</li>
					
					<li class="menu-list-04">
						<p class="fumenu">员工管理</p>
						<img class="xiala" src="img/adminManagement/xiala.png" />
						<div class="list-p">
							<a href="${pageContext.request.contextPath}/admin/staffs/staffslist"><p class="zcd" id="zcd8">查看</p></a>
							<a href="${pageContext.request.contextPath}/admin/staffs/staffsedit"><p class="zcd" id="zcd9">增加</p></a>
						</div>
					</li>
					
					<li class="menu-list-05">
						<p class="fumenu">系统管理</p>
						 <img class="xiala"	src="img/adminManagement/xiala.png" />
						<div class="list-p">
						<a href="${pageContext.request.contextPath}/admin/admin/adminlist"><p class="zcd" id="zcd17">管理员管理</p></a>
						<a href="${pageContext.request.contextPath}/admin/adminrole/adminroleslist"><p class="zcd" id="zcd17">角色管理</p></a>
						
						</div>
					</li>
					
					<li class="menu-list-06">
						<p class="fumenu">财务管理</p>
						<img class="xiala" src="img/adminManagement/xiala.png" />
						<div class="list-p">
							<a href="${pageContext.request.contextPath}/admin/listAdminSrevlet"><p class="zcd" id="zcd8">查看</p></a>
							<a href="${pageContext.request.contextPath}/admin/goToAddAdminViewServlet"><p class="zcd" id="zcd9">增加</p></a>
						</div>
					</li>

					<li class="menu-list-07">
						<p class="fumenu">仓库管理</p> <img class="xiala" src="img/adminManagement/xiala.png" />
						<div class="list-p">
						<a href="${pageContext.request.contextPath}/admin/entrepot/entrepotList.action">
						<p class="zcd" id="zcd22">仓库维护</p></a>	
						<a href="${pageContext.request.contextPath}/admin/provider/providerList.action">
						<p class="zcd" id="zcd22">供应商维护</p></a>
						<a href="${pageContext.request.contextPath}/admin/kucun/dittylist.action">
						<p class="zcd" id="zcd22">非卖品维护</p></a>
						<a href="${pageContext.request.contextPath}/admin/kucun/goodslist.action">
						<p class="zcd" id="zcd22">商品维护</p></a>
						<a href="${pageContext.request.contextPath}/admin/caigou/dittyedit.action">
						<p class="zcd" id="zcd22">采购货物</p></a>
						<a href="${pageContext.request.contextPath}/admin/repair/repairlist.action">
						<p class="zcd" id="zcd22">维修查询</p></a>					
						</div>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="right-menussss">
			<div id="right_menu_floorssss">
				<table cellSpacing="0" width="80px" height="500px" border="1px">
					<tr class="floortdssss"><td>一<br />楼</td></tr>
					<tr class="floortdssss"><td>二<br />楼</td></tr>
					<tr class="floortdssss"><td>三<br />楼</td></tr>
					<tr class="floortdssss"><td>四<br />楼</td></tr>
					<tr class="floortdssss"><td>五<br />楼</td></tr>
					<tr class="floortdssss"><td>六<br />楼</td></tr>
				</table>
			</div>
			
			<div id="right_menu_roomssss" >
				<c:forEach items="${mapListRooms}" var="mk" >
					<c:if test="${ empty mk.value }">
					<div class="roomssss"></div>
					</c:if>
<!-- 					children().eq(0).children().eq().attr("id"); -->
					<c:forEach items="${mk.value}" var="mko" varStatus="z"><!-- data-toggle="modal" data-target="#myModal" -->
						<a href="javascript:void(0)" class="xiangqing" data-toggle="modal" data-target="#myModal" >
							<div class="roomssss" name="${mko.id}" id="${mko.no}"  
								<c:if test="${mko.roomstatus.id eq 2}">style="background-color:C0C0C0;" </c:if> 
								<c:if test="${mko.roomstatus.id eq 3}">style="background-color:FF8040;" </c:if>
								<c:if test="${mko.roomstatus.id eq 4}">style="background-color:008040;" </c:if>
								<c:if test="${mko.roomstatus.id eq 5}">style="background-color:80BEBF;" </c:if>
								<c:if test="${mko.roomstatus.id eq 6}">style="background-color:00B714;" </c:if>
								<c:if test="${mko.roomstatus.id eq 7}">style="background-color:CACA00;" </c:if>
								<c:if test="${mko.roomstatus.id eq 1}">style="background-color:970000;" </c:if>	
								>
								<span name="${mko.no}">${mko.no}</span><br />
								<span name="${mko.roomtypes.roomtype}"></span>${mko.roomtypes.roomtype}<br />
							    <span name="￥：${mko.roomtypes.price}"></span>￥：${mko.roomtypes.price}<br />
								<input type="hidden" value="${mko.roomstatus.id}" id="status${mko.id}" />
							</div>
						</a>
					</c:forEach>
					<div style="clear:both"></div>
				 </c:forEach>
	    	</div>
		</div>
		<script type="text/javascript">
			$(function(){
				$("#allRooms").click(function(){
					window.location.href="${pageContext.request.contextPath}/admin/home.action";
				});
				$("#nullRooms").click(function(){
					window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByStatues.action?room.roomstatus.id=2";
				});
				$("#dirtyRooms").click(function(){
					window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByStatues.action?room.roomstatus.id=3";
				});
				$("#propleRooms").click(function(){
					window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByStatues.action?room.roomstatus.id=4";
				});
				$("#peopleDirtyRooms").click(function(){
					window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByStatues.action?room.roomstatus.id=5";
				});
				$("#yudingRooms").click(function(){
					window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByStatues.action?room.roomstatus.id=6";
				});
				$("#repairRooms").click(function(){
					window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByStatues.action?room.roomstatus.id=7";
				});
				$("#noMakeRooms").click(function(){
					window.location.href="${pageContext.request.contextPath}/admin/qiantai/findAllByStatues.action?room.roomstatus.id=1";
				});
				$("#yuding").click(function(){
					var roomId=$("#roomsid").val();	
					var status=$("#status"+roomId).val();
					if(status==2){
						window.location.href="${pageContext.request.contextPath }/admin/qiantai/getRoomById?roomId="+roomId;
					}else{
						$("#div_adderr").show();
						setTimeout(hide,1500);//1.5秒后消失
		 				return false;
					}
				})
				$("#tuifang").click(function(){
					var roomId=$("#roomsid").val();				
					window.location.href="${pageContext.request.contextPath }/admin/qiantai/userOut?roomId="+roomId;
				})
				function hide(){
					$("#div_adderr").animate({opacity: "hide"}, "slideUp");
				}
			});
			</script>
		<div id="footssss">
			<input style="background-color:C0C0C0;" type="button" id="allRooms" value="全部房间:${countRooms.allRooms}">   
			<input style="background-color:C0C0C0;" type="button" id="nullRooms" name="${countRooms.nullRooms}" value="空闲房间:${countRooms.nullRooms}">   
			<input style="background-color:FF8040;" type="button" id="dirtyRooms" name="${countRooms.dirtyRooms}" value="脏 房间:${countRooms.dirtyRooms}">   
			<input style="background-color:008040;" type="button" id="propleRooms" name="${countRooms.propleRooms}" value="住人房间:${countRooms.propleRooms}">   
			<input style="background-color:80BEBF;" type="button" id="peopleDirtyRooms" name="${countRooms.peopleDirtyRooms}" value="住人脏房:${countRooms.peopleDirtyRooms}">     
			<input style="background-color:00B714;" type="button" id="yudingRooms" name="${countRooms.yudingRooms}" value="预定房间:${countRooms.yudingRooms}">   
			<input style="background-color:CACA00;" type="button" id="repairRooms" name="${countRooms.repairRooms}" value="维修房间:${countRooms.repairRooms}">    
			<input style="background-color:970000;" type="button" id="noMakeRooms" name="${countRooms.noMakeRooms}" value="不可用房:${countRooms.noMakeRooms}">    
		</div>
		
		<script type="text/javascript">
			 Date.prototype.format = function(fmt) {
		        var o = {
		            "M+" : this.getMonth() + 1, //月份 
		            "d+" : this.getDate(), //日 
		            "h+" : this.getHours(), //小时 
		            "m+" : this.getMinutes(), //分 
		            "s+" : this.getSeconds(), //秒 
		            "q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
		            "S" : this.getMilliseconds() //毫秒 
		        };
		        if (/(y+)/.test(fmt))
		            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		        for ( var k in o)
		            if (new RegExp("(" + k + ")").test(fmt))
		                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]): (("00" + o[k]).substr(("" + o[k]).length)));
		        return fmt;
		    } 
		  
			$(function(){
				 
				
				$(".xiangqing").click(function(){
					var roomsid=$(this).children().eq(0).attr("name");
					$("#roomsid").val(roomsid);
					var roomsno=$(this).children().eq(0).children().eq(0).attr("name");
					$("#roomsno").html(roomsno);
					var roomstype=$(this).children().eq(0).children().eq(2).attr("name");
					$("#roomstype").html(roomstype);
					var roomsprice=$(this).children().eq(0).children().eq(4).attr("name");
					$("#roomsprice").html(roomsprice);
					var yudingxinxi=$("#yudingxinxi");
					var url="<%=path%>/admin/qiantai/roomUserMsg.action";
					$.post(url,{"room.id":roomsid},function(res){
					
					//获取入住信息
						if(!$.isEmptyObject(res.roomUserMsg)){
							
							$("#roomspeople").html(res.roomUserMsg.roomspeoples[0]);
							if(!$.isEmptyObject(res.roomUserMsg.roomspeoples[1])){
								$("#roomspeople").html(res.roomUserMsg.roomspeoples[0]+","+res.roomUserMsg.roomspeoples[1]);	
							}
							$("#roomsyouhuiprice").html(res.roomUserMsg.roomsyouhuiprice);
							$("#roomszhudate").html(new Date(res.roomUserMsg.roomszhudate.time).format("yyyy-MM-dd"));
							$("#roomslidate").html(new Date(res.roomUserMsg.roomslidate.time).format("yyyy-MM-dd"));
							$("#roomsdays").html(res.roomUserMsg.roomsdays);
							$("#roomsgoodsprice").html(res.roomUserMsg.roomsgoodsprice);
							$("#roomssum").html(res.roomUserMsg.roomsyajin);
						}else{
							$("#roomspeople").html("");
							$("#roomsyouhuiprice").html("");
							$("#roomszhudate").html("");
							$("#roomslidate").html("");
							$("#roomsdays").html("");
							$("#roomsgoodsprice").html("");
							$("#roomssum").html("");
						}
					//获取房间预定信息
						if(!$.isEmptyObject(res.roomUserYudingList)){
							var html="<h3>房间预定信息</h3><br>";
							for(var i=0;i<res.roomUserYudingList.length;i++){
								var roomUserYuding=  res.roomUserYudingList[i];
								html+="<br>预定人姓名："+roomUserYuding.roomUserYudingName;
								html+="<br/>预定人联系方式："+roomUserYuding.roomUserYudingPhone;
								html+="<br/>预定日期："+new Date(roomUserYuding.roomUserYudingcreateDate.time).format("yyyy-MM-dd");
								html+="<br/>预退日期："+new Date(roomUserYuding.roomUserYudingendDate.time).format("yyyy-MM-dd");
								html+="<br/><button type=\"button\" name="+roomUserYuding.roomUserYudingPhone+" id=\"yudingruzhu\" class=\"btn btn-primary\">入住</button>";
								html+="<br/><hr>";
							}
							
							yudingxinxi.html(html);
							 $("#yudingruzhu").bind("click",function(){
							  	var phone=$(this).attr("name");
							  	window.location.href="${pageContext.request.contextPath }/admin/qiantai/findReverseByPhone?phone="+phone;
							  });
						}else{
							yudingxinxi.html("<h3>房间预定信息</h3><br>预定人姓名：<br/>预定人联系方式：<br/>预定日期：<br/>预退日期：<br/><hr>");	
						}
					},'json');
				});
			});
		</script>
		<!-- 模态框 -->				
		<div class="modal fade"  aria-hidden="true" data-backdrop="static" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 1000px;">
				<div class="modal-content" style="width: 1000px;">
					<div class="modal-header" style="width: 1000px; position: relative; float: left;" >
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h2 class="modal-title" id="myModalLabel" style="text-align: center;">房间详细信息</h2>
					</div>
					<div class="modal-body" style="width: 1000px; position: relative; float: left; ">
						<div style="width: 460px; margin-left: 40px; ">
							<h3>房间基本信息</h3><br>
							<input type="hidden" id="yudingId" value=""/>
							房间号：<span id="roomsno"></span><br/>
							类型：<span id="roomstype"></span><br/>
							市价：<span id="roomsprice"></span><br/><br>
							住店人：<span id="roomspeople"></span><br/>
							优惠价：<span id="roomsyouhuiprice"></span><br/>
							住店日期：<span id="roomszhudate"></span><br/>	
							离店日期：<span id="roomslidate"></span><br/>	
							住店天数：<span id="roomsdays"></span><br/>
							消费价格：<span id="roomsgoodsprice"></span><br/>
							押金：<span id="roomssum"></span><br/><br>
							<input id="roomsid" type="hidden" />
						</div>
						<div id="yudingxinxi" style="width: 460px; position: relative; float:left; margin-left: 510px; margin-top: -320px;">
							<h3>房间预定信息</h3><br>
							预定人姓名：<br/>
							预定人联系方式：<br/>
							预定日期：<br/>
							预退日期：<br/><hr>
						</div>
					</div>
					<div class="modal-footer" align="center" >
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" id="yuding" class="btn btn-primary">开房</button>
						<button type="button" id="tuifang" class="btn btn-primary">退房</button>
					</div>
				</div>
			</div>
		</div>
		<div id="div_adderr" style="display: none">
				该房间已经住人
	   </div>
		<div id="div_dianhuachaxun" style="display: none">
				电话信息错误
	   </div>
</body>
