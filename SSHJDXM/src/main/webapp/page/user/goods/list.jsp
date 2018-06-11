<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'goodAndCart.jsp' starting page</title>
    		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/goods/base.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/quick_links.js"></script>
	<script type="text/javascript">
		$(function(){
		
		
		
		 
		//鼠标的移入移出
		$(".my_qlinks").mouseover(function (){
			
		
			$(".ibar_login_box").show();
		})
		$(".ibar_login_box").mouseover(function (){
			
		
			$(".ibar_login_box").show();
		}).mouseout(function (){
			$(".ibar_login_box").hide();
		});
		
		$(".history_list").click(function (){
		
		//查询押金，订单总额
			var roomsid=$("#roomsid").val();
			var msgObj=$(this).next();
			var url="${pageContext.request.contextPath}/user/roomUserMsg";
					$.post(url,{"room.id":roomsid},function(res){
					if(!$.isEmptyObject(res.roomUserMsg)){
							
							var yajin=res.roomUserMsg.roomsyajin;
							var xiaofei=res.roomUserMsg.roomsgoodsprice;
							var yue= yajin-xiaofei;
							msgObj.html("房间押金:"+yajin+"<br/>房间消费:"+xiaofei+"<br/>房间余额:"+yue);
						}
					
					},'json');
		
		
			$(".mp_tooltip").show();
		}).mouseout(function (){
			$(".mp_tooltip").hide();
		});
		
		
		 //点击空白处或者自身隐藏弹出层，下面分别为滑动和淡出效果。  
		     
            $(".mp_tooltip").hide();
			$("#message").hide();
			var message=$("#message");
			var url="/SSHJDXM/room/cart/roomCart";
					
			function getCart(url,message){
					$.post(url,{},function(res){
						var html="<div id='xiaoche' ><a href='#'>我的购物车</a>";
						var sum=0;
						var num=0;
						for(var i=0;i<res.length;i++){				
						html=html+"<div class='cartItem'>	<div class='goodsImg'><img src=${pageContext.request.contextPath }/upload/goods/"+res[i].goods.imgcover+" /></div>";
						html=html+"	<div class='cartDesc'  name='"+res[i].id+"'><p>商品:"+res[i].goodsname+"</p><p>数量:<input type='button' class='sub' value='-'/><input class='cartGoodsNum' value='"+res[i].goodsnum+"'/><input type='button' class='add' value='+'/></p><p>单价:￥"+res[i].goods.price+"</p><input type='hidden' value='"+res[i].goods.price+"'></div></div>";
						html=html+"	<div class='clear'></div>";
						sum+=res[i].totalsum;
						num+=res[i].goodsnum;
						}
						html=html+"<div id='cartNum'><span>共"+num+"商品</span>总价:<span>"+sum+"</span></div>";
						html+="<input  class='abtn' type='button' value='去购物车结算' /></div>";
						message.html(html);
						$("#cart_num").text(num);
						
						//购物车结算
						$(".abtn").click(function(){
							location="${pageContext.request.contextPath}/room/order/creatOrderByRoom";
						
						
						
						})
						//购物车数量加减
						$(".sub,.add").click(function(){
							var jiesunMsg=$("#cartNum");
							var cartNumObj=$("#cart_num");
							var cartNum=$("#cart_num").text();
							var obj=$(this).parent().parent().parent();
							var cartId=$(this).parent().parent().attr("name");
							var cartSum=$(this).parent().parent().children().eq(3).val();
							var tag=$(this).attr("class");
							var objNum=$(this).parent().parent().children().eq(1).children().eq(1);
							var num=objNum.val();
							if(tag=="sub"&&num==1&&!confirm("移除购物车？")){
								return;
							}
							var url="${pageContext.request.contextPath}/room/cart/alertCart"
							$.post(url,{"cart.id":cartId,"tag":tag},function(res){
								if(res==0){
								if(tag=="add"){
									cartNum=cartNum*1+1;
									objNum.val(num*1+1);
									cartNumObj.text(cartNum);
									sum=sum*1+cartSum*1;
									
								}else{
								
									objNum.val(num*1-1);
									cartNum=cartNum*1-1;
									sum=sum*1-cartSum*1;
									cartNumObj.text(cartNum);
									if((num-1)==0){
									obj.remove();
									}
								}
								jiesunMsg.html("<span>共"+cartNum+"商品</span><span>总价"+sum+"</span>");
								
									
								}else{
								alert("error");
								}
							
							
							},'json');
							
							
						
						
						});
					
					},'json');
	
			
			}
			//刷新页面时获取信息
			 getCart(url,message);
			 
			 
		
			
			$(".addCart").click(function(){
				var goodId=$(this).attr("name");
				var url="${pageContext.request.contextPath }/room/cart/addToCart";
				$.post(url,{"goods.id":goodId},function(res){
				
					if(res==1){
						alert("加入购物车失败");
					}
				
				},'json');
			
			
			});
			
			/* 单击弹出购物车 */
			var showXiaoChe=0;
			$("#shopCart").click(function(event){
			  //取消事件冒泡  
                event.stopPropagation();  
				if(showXiaoChe==0){
						
						getCart(url,message);
					
						$("#message").show();
						showXiaoChe=1;
						 //点击空白处或者自身隐藏弹出层，
				/* $(document).click(function (event) { $('#message').hide();showXiaoChe=0; }); */  
						
				 $(document).click(function(event) {  
               
                var elem = event.target || event.srcElement;  
                while (elem) { //循环判断至跟节点，防止点击的是div子元素   
                    if (elem.id && elem.id == 'message') {  
                        return;  
                    }  
                    elem = elem.parentNode;  
                }  
              		 $("#message").hide();showXiaoChe=0; //点击的不是div或其子元素   
            	});  
		           
				}else{
					$("#message").hide();
					showXiaoChe=0;
				}		
			});
			
		
		
	/* 	 $('#btnShow').click(function (event) {  
               
                 //设置弹出层的位置  
                 var offset = $(event.target).offset();  
                $('#divTop').css({ top: offset.top + $(event.target).height() + "px", left: offset.left });  
                 //按钮的toggle,如果div是可见的,点击按钮切换为隐藏的;如果是隐藏的,切换为可见的。  
                  $('#divTop').toggle('slow');  
             });  
             //点击空白处或者自身隐藏弹出层，下面分别为滑动和淡出效果。  
             $(document).click(function (event) { $('#divTop').slideUp('slow') });  
             $('#divTop').click(function (event) { $(this).fadeOut(1000) });  
	
			
			 */
		});
		
		
		
 
		
		
	
		
		
		
		

	
	</script>

  </head>
  
 <body id="home" style="height:1000px;">
		
		
		<div class="search">
			
				<div id="logo">
					
				</div>
				<div id="searchDiv">
					<input id="txt" type="text" placeholder="请输入关键字" />
				</div>
				<div id="searchInput">
					<input id="btn" type="button" value="搜索" />
				</div>
			
		</div>
		<hr/>

   	
	<!--商品主界面-->		
	<div class="main">		
	
	<c:forEach items="${goodsList }" var="goods">
   		 		 
   		 	
   		 	 <div class="goods"   >
        
                <div class="lh_wrap">
                    <div class="p-img"><a href="#"><img alt="" src="${pageContext.request.contextPath}/upload/goods/${goods.imgcover}" width="220" height="200"></a></div>
                    <div class="p-name"><a href="#" title="${goods.name }">${goods.fullname }</a></div>
                    <div class="p-price"><strong>${goods.price }</strong><span id="p200"></span></div>
                  
                    <div class="addCart" name="${goods.id}">
                        <a href="javascript:;"  class="btnCart"  >加入购物车</a>
                    </div>
                </div>
         	  <div id="flyItem" class="fly_item"><img src="${pageContext.request.contextPath}/upload/goods/item-pic.jpg" width="40" height="40"></div>
    		</div>
  
	</c:forEach>
   	
   	
   		
   	</div>	
		
		
	
<!-- 	//隐藏的购物车 -->
	<div id="message">
		
	
	</div>	
	
		
		
		
	<!--右侧贴边导航quick_links.js控制-->
<div class="mui-mbar-tabs">
	<div class="quick_link_mian">
		<div class="quick_links_panel">
			<div id="quick_links" class="quick_links">
			<ul>
				<li>
					<a href="#" class="my_qlinks"><i class="setting"></i></a>
					<div class="ibar_login_box status_login">
						<div class="avatar_box">
							<p class="avatar_imgbox"><img src="images/no-img_mid_.jpg" /></p>
							<ul class="user_info">
								<li>房间号:${room.no }</li>
								<li><input type="hidden" id="roomsid" value="${room.id }"/></li>
							</ul>
						</div>
						<div class="login_btnbox">
							<a href="${pageContext.request.contextPath }/room/order/listGoodsOrder" class="login_order">房间订单</a>
						</div>
						<i class="icon_arrow_white"></i>
					</div>
				</li>
				<li id="shopCart">
					<a href="#" class="message_list" ><i class="message"></i><div class="span">购物车</div><span id="cart_num" class="cart_num"></span></a>
				</li>
				<li>
					<a href="#" class="history_list"><i class="view"></i></a>
					<div class="mp_tooltip">
						
					</div>
				</li>
				</ul>
			<!-- 	<li>
					<a href="#" class="mpbtn_histroy"><i class="zuji"></i></a>
					<div class="mp_tooltip">我的足迹<i class="icon_arrow_right_black"></i></div>
				</li> -->
				
				
			</div>
			<div class="quick_toggle">
				<ul>
				<li>
					<a href="#"><i class="kfzx"></i></a>
				</li>
				
				<li><a href="#top" class="return_top"><i class="top"></i></a></li>
				</ul>
			</div>
		</div>
		<div id="quick_links_pop" class="quick_links_pop hide"></div>
	</div>
</div>

<%-- <script src="${pageContext.request.contextPath}/js/ieBetter.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/parabola.js"></script>
<script type="text/javascript">
	
// 元素以及其他一些变量
var eleFlyElement = document.querySelector("#flyItem"), eleShopCart = document.querySelector("#shopCart");



// 抛物线运动
var myParabola = funParabola(eleFlyElement, eleShopCart, {
	speed: 400, //抛物线速度
	curvature: 0.0008, //控制抛物线弧度
	complete: function() {
	var numberItem = $("#cart_num").text()*1;
		eleFlyElement.style.visibility = "hidden";
		eleShopCart.querySelector("span").innerHTML = ++numberItem;
	}
});
// 绑定点击事件
if (eleFlyElement && eleShopCart) {
	[].slice.call(document.getElementsByClassName("btnCart")).forEach(function(button) {
		button.addEventListener("click", function(event) {
			// 滚动大小
			var scrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft || 0,
			    scrollTop = document.documentElement.scrollTop || document.body.scrollTop || 0;
			eleFlyElement.style.left = event.clientX + scrollLeft + "px";
			eleFlyElement.style.top = event.clientY + scrollTop + "px";
			eleFlyElement.style.visibility = "visible";
			// 需要重定位
			myParabola.position().move();			
		});
	});
}






</script>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';clear:both;">

</div>
  </body>
</html>
