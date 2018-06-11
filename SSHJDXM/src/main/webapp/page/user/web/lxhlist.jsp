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
    
    <title>My</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content="老相好官网-全国领先的经济型酒店品牌">
    
	
	<link rel="stylesheet" href="<%=path%>/page/user/web/lxhlist/daterangepicker.min.css" />
	<script type="text/javascript" src="<%=path%>/page/user/web/lxhlist/moment.min.js" ></script>
	<script type="text/javascript" src="<%=path%>/page/user/web/lxhlist/jquery.min.js" ></script>
	<script type="text/javascript" src="<%=path%>/page/user/web/lxhlist/jquery.daterangepicker.min.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/jquerySession.js"></script>
	<!--
		作者：offline
		时间：2017-12-26
		描述：日期控件
	-->
	<script> 
		$(function(){
			$("#date-range200").focus(function(){
				$("#date-range200").dateRangePicker(
					{	
						language: 'cn',
						separator : ' to ',
						getValue: function()
						{
							if ($('#date-range200').val() && $('#date-range201').val() )
								return $('#date-range200').val() + ' to ' + $('#date-range201').val();
							else
								return '';
						},
						setValue: function(s,s1,s2)
						{
							$('#date-range200').val(s1);
							$('#date-range201').val(s2);
						},
						startDate: new Date(),
						selectForward: true,
/*						showDateFilter: function(time, date)
						{
							return '<div style="padding:0 5px;">\
										<span style="font-weight:bold">'+date+'</span>\
										<div style="opacity:0.3;">$'+Math.round(Math.random()*999)+'</div>\
									</div>';
						},*/
						beforeShowDay: function(t)
						{
							var valid = !(t.getDay() == -1 || t.getDay() == -6);  //disable saturday and sunday
							var _class = '';
							var _tooltip = valid ? '' : 'sold out';
							return [valid,_class,_tooltip];
						}
					}
				);
			})
		////////
		$("#date-range201").focus(function(){
				$("#date-range201").dateRangePicker(
					{	
						language: 'cn',
						separator : ' to ',
						getValue: function()
						{
							if ($('#date-range200').val() && $('#date-range201').val() )
								return $('#date-range200').val() + ' to ' + $('#date-range201').val();
							else
								return '';
						},
						setValue: function(s,s1,s2)
						{
							$('#date-range200').val(s1);
							$('#date-range201').val(s2);
						},
						startDate: new Date(),
						selectForward: true,
//						showDateFilter: function(time, date)
//						{
//							return '<div style="padding:0 5px;">\
//										<span style="font-weight:bold">'+date+'</span>\
//										<div style="opacity:0.3;">$'+Math.round(Math.random()*999)+'</div>\
//									</div>';
//						},
						beforeShowDay: function(t)
						{
							var valid = !(t.getDay() == -1 || t.getDay() == -6);  //disable saturday and sunday
							var _class = '';
							var _tooltip = valid ? '' : 'sold out';
							return [valid,_class,_tooltip];
						}
					}
				);
			})
	
		//方法结束的括号
		})
		
		
	</script>
	

	
	
	
    <script type="text/javascript">
    	//完成定房间功能
    	$(function(){
    		$(".btn_buy").click(function(){
				url="<%=path%>/user/yuding/checkuserloginINList.action";
	  			var roomId=$(this).attr("id");
	  			var startDate=$("#date-range200").val();
	  			var endDate=$("#date-range201").val();
	  			
    			$.post(url,
    			{"roomId":roomId,"startDate":startDate,"endDate":endDate},
    			function(res){
    				if(res.msg == "没登录"){
    				    $("#yudingNO").show();
    					setTimeout(function(){$("#yudingNO").hide();},2000);//1.5秒后消失  
		    			
    				}else{
    					userOrderRoomsifLogin(roomId);
 						//window.location.href=url+"?roomId="+roomId+"&startDate="+startDate+"&endDate="+endDate;
    				}			
    			},
    			'json'); 
    		
    		})
    			
    		function userOrderRoomsifLogin(roomId){
    			url="<%=path%>/user/yuding/userOrderRoomsifLogin.action";
	  			var startDate=$("#date-range200").val();
	  			var endDate=$("#date-range201").val();
	  			var position=$("#position").val();
    			window.location.href=url+"?roomId="+roomId+"&startDate="+startDate+"&endDate="+endDate+"&position="+position;
    		
    		}

    	
    	})
    	function orderRoom(){
    		url="<%=path%>/user/yuding/userOrderRooms.action";
    		
    	}	
    </script>

	
	
	    <link href="<%=path%>/page/user/web/lxhlistommon.css" rel="stylesheet">
	    <link href="<%=path%>/page/user/web/lxhlist/OAuth.css" rel="stylesheet">	
	    <link href="<%=path%>/page/user/web/lxhlist/LoginCommon.css" rel="stylesheet">
        <link href="<%=path%>/page/user/web/lxhlist/list.css" rel="stylesheet">
        <link href="<%=path%>/page/user/web/lxhlist/pagination.css" rel="stylesheet">
	    <link href="<%=path%>/page/user/web/lxhlist/bootstrap.css" rel="stylesheet">
	    <link href="<%=path%>/page/user/web/lxhlist/bootstrap-responsive.css" rel="stylesheet">
	    <link href="<%=path%>/page/user/web/lxhlist/_layout.css" rel="stylesheet">
	    <link href="<%=path%>/page/user/web/lxhlist/PopBoxDiv.css" rel="stylesheet">


<link href="<%=path%>/page/user/web/lxhlist/WdatePicker.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/page/user/web/lxhlist/CH_public.css" rel="stylesheet">
    <link href="<%=path%>/page/user/web/lxhlist/brand_0.css" rel="stylesheet">



    
    





    <title>老相好酒店预订</title>


    <style type="text/css">
        #overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: #000;
            opacity: 0.5;
            filter: Alpha(Opacity=50);
            z-index: 9999999999;
            display: none;
        }

        /*
            html #overlay {
                position: absolute;
            }
            */
        #win {
            position: absolute;
            left: 220px;
            display: none;
            z-index: 999999999999999;
        }

        input, textarea {
            color: #000;
        }

        .placeholder {
            color: #666;
        }

        #btnCode {
            text-align: center;
            display: inline-block;
            cursor: pointer;
            color: #2A4F67;
            font-size: 12px;
            font-weight: bold;
        }

        .lg_login_code_img {
            float: none !important; /*样式优先使用*/
            height: 32px;
        }

        .up-box-hd {
            cursor: move;
        }
        .mainlevel ul {
            display: none;
            position: absolute;
            z-index: 9999;
            width: 64px;
            margin:0px;
        }

            .mainlevel ul li {
                list-style: none;
                display: block;
                background-color: #2A4F67 !important;
                margin: 0px !important;
                width: 100%;
                text-align: center !important;
            }

                .mainlevel ul li a {
                    font-size: 14px !important;
                    margin: 0px !important;
                }
    </style>
	
  </head>
  
  <body>
    	<script src="<%=path%>/page/user/web/lxhlist/al.html"  type="text/javascript"></script>
    	<script src="<%=path%>/page/user/web/lxhlist/sl.js" type="text/javascript" ></script>
    	<script type="text/javascript">_slot_id = "5e700c94-bf9d-4968-871a-096009ade7ef";</script>

        <script type="text/javascript">
            function show(obj){
                var XS = document.getElementById(obj);
                XS.style.display ="block";
            }
            function hide(obj){
                var YC = document.getElementById(obj);
                YC.style.display = "none";
            }
        </script>



                  
<!-- 导航 -->
                <div id="navigation">
                    <a class="hidden-desktop" id="btn-nav" style="float:left;margin-left:10px;"><img src="<%=path%>/page/user/web/lxhlist/btn-nav.png">格林豪泰酒店集团</a>
                    <div class="container">
                        <div class="navigation-wrapper">
                            <div class="navigation clearfix-normal">
                                <div style="height: 60px; width: 240px; float: left; line-height:60px;color:#FFF;cursor:pointer;" class="groupLogo" onclick="ChangeBrand('99');">
                                    <div style="height: 40%; width: 100%; font-weight: bold;font-size:15px;line-height:50px;">老&nbsp;&nbsp;&nbsp;
相&nbsp;&nbsp;&nbsp;好&nbsp;&nbsp;&nbsp;管&nbsp;&nbsp;&nbsp;
理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;集&nbsp;&nbsp;&nbsp;团</div>
                                    <div style="height:60%;width:100%;font-size:12px;line-height:36px;">OldIsAlwaysGood Management Group&nbsp;,&nbsp; Inc.</div>
                                </div>

                                <ul class="nav" style="margin-left:20px;">


                                    
                                        <li><a href="<%=path%>/page/user/web/lxhmain.jsp">首页</a></li>

                                    
                                        <li class="active mainlevelbb">
                                            <a href="javascript:void(0)">酒店预订</a>
                                            <div class="divDroplist" style="display: none;">
                                                <div class="divTriangle"></div>
                                                <ul>
                                                    <li><a href="http://www.998.com/HotelList">国内酒店</a></li>
                                                    <li><a href="javascript:void(0)" onclick="ChangeBrand('90')">海外酒店</a></li>
                                                    <li><a href="https://www.greentreeinn.com/cn">美国官网</a></li>
                                                </ul>
                                            </div>
                                        </li>

                                    
                                        <li><a href="http://www.998.com/Selected">优惠精选</a></li>
                                    
                                        
                                    
                                        <li class="mainlevelbb">
                                            <a href="javascript:void(0)">缤纷会员</a>
                                            <div class="divDroplist" style="display: none;">
                                                <div class="divTriangle"></div>
                                                <ul>
                                                    <li><a href="http://www.998.com/ColorfulMember">成为会员</a></li>
                                                    <li><a href="http://www.998.com/Help/list?typeId=ff8db4d3-4a3f-41f0-9574-c6ed036554af">会员手册</a></li>
                                                </ul>
                                            </div>
                                        </li>
                                    
                                        <li class="mainlevelbb">
                                            <a href="javascript:void(0)">新闻论坛</a>
                                            <div class="divDroplist">
                                                <div class="divTriangle"></div>
                                                <ul>
                                                    <li><a href="http://www.998.com/NewsCenter">新闻中心</a></li>
                                                    <li><a href="http://www.998.com/Forum">论坛社区</a></li>
                                                </ul>
                                            </div>
                                        </li>
                                    
                                    
                                        <li><a href="http://www.998.com/Join/JoiningGreen">加盟老相好</a></li>

                                    
                                    <li><a href="http://998.zhiye.com/" target="_blank">人才招聘</a></li>
                                </ul><!-- /.nav -->

							<c:if test="${webuser eq null}">
                                <div class="header-login">
                                    <a class="Hovergreen">老相好</a>&nbsp;
                                    <a href="<%=path%>/page/user/web/login.jsp">登录</a>&nbsp;
                                    <a href="<%=path%>/page/user/web/reg.jsp">注册</a>
                                </div>
							</c:if>
							<c:if test="${webuser ne null}">
                                <div class="header-login">
                                    <a class="Hovergreen">${webuser.username}</a>&nbsp;
                                    
                                </div>
							</c:if>							

                                

                            </div><!-- /.navigation -->
                        </div><!-- /.navigation-wrapper -->
                    </div><!-- /.container -->

                </div><!-- /.navigation -->
                <!-- CONTENT -->
               
				<div id="yudingNO" style="border:2px solid #F47920; hidden:'hidden' ;position:fixed; top:40%;left: 40%;height: 100px;
				width: 250px;font-size: 25px;background-color: white;display: none;z-index: 99999;text-align: center;">
					<div style="padding-top: 30px">
						<span >请先登录</span>
					</div>
				</div>
				
				<div id="yudingError">
				</div>



<form action="<%=path%>/user/yuding/finRoomsByPosition" method="post" id="form-s">
	<div class="row" style="margin-left: 0px; margin-top: 10px; background-color: #F7F6F5; height: 80px">
	   
	    <div class="span2_5" style="margin-left:150px;">
			
	    </div>
	    
	    <div class="span2_5" style="margin-left: 0px;">
	        <div class="date">
		        <input id="date-range200" class="t2"  placeholder="入住" value='<fmt:formatDate value="${startDate}" type="date"/>' type="text" style="height:40px" name="startDate">
		        <img src="<%=path%>/page/user/web/lxhlist/start.png">
	        </div>
	    </div>
	    
	    <div class="span2_5" style="margin-left: 0px;">
	        <div class="date">
		        <input id="date-range201" class="t2"  placeholder="退房" value='<fmt:formatDate value="${endDate}" type="date"/>' type="text" style="height:40px" name="endDate">
		        <img src="<%=path%>/page/user/web/lxhlist/end.png">
	        </div>
	    </div>
	    
	    <div class="span2_5" style="margin-left: 0px;">
			<div class="date">
		       <select name="position" id="position" style="margin-top:14px; width: 100px">
			       	<option value=1  ${position eq 1?"selected":""}>1F</option>
			       	<option value=2  ${position eq 2?"selected":""}>2F</option>
			       	<option value=3  ${position eq 3?"selected":""}>3F</option>
			       	<option value=4  ${position eq 4?"selected":""}>4F</option>
			       	<option value=5  ${position eq 5?"selected":""}>5F</option>
			       	<option value=6  ${position eq 6?"selected":""}>6F</option>
			       	<option value=7  ${position eq 7?"selected":""}>7F</option>
		       </select> 
	        </div>
	    </div>
	    
	    <div class="span2_5" style="margin-left: 0px;height:100%;">
	        <div  class="btnDivSearch" style="background-color: #F47920; width: 280px"><input type="submit" id="submit-btn" value="搜索"/></div>
	    </div>
	</div>
</form>




<div style="padding-top:5px;">
        <div class="container">
            <div style="position: relative; box-sizing: border-box; ">
                <div class="header-bottom_Loc">
                    <div class="bottom-head_Loc t2 jc" >
                        <a>
                            <div class="buy-media_Loc">
                                <h3 ${position eq 1?"style='color: rgb(145, 144, 144);font: red;font-size: 22px'":"style='color: rgb(145, 144, 144);'"}  >1F</h3>
                            </div>
                        </a>
                    </div>
                    <div class="bottom-head_Loc t4   " >
                        <a>
                            <div class="buy-media_Loc">
                                <h3 style="color: rgb(145, 144, 144);">2F</h3>
                            </div>
                        </a>
                    </div>
                    <div class="bottom-head_Loc t5" >
                        <a>
                            <div class="buy-media_Loc">
                                <h3 style="color: rgb(145, 144, 144);">3F</h3>
                            </div>
                        </a>
                    </div>
                    <div class="bottom-head_Loc t3" >
                        <a>
                            <div class="buy-media_Loc">
                                <h3 style="color: rgb(145, 144, 144);">4F</h3>
                            </div>
                        </a>
                    </div>
                    <div class="bottom-head_Loc t7" >
                        <a>
                            <div class="buy-media_Loc">
                              
                                <h3 style="color: rgb(145, 144, 144);">5F</h3>
                            </div>
                        </a>
                    </div>
                    <div class="bottom-head_Loc t1" >
                        <a>
                            <div class="buy-media_Loc">
                                <h3 style="color: rgb(145, 144, 144);">6F</h3>
                            </div>
                        </a>
                    </div>
                    <div class="bottom-head_Loc t6" data-mark="5,6,7" style="border-right:0px;">
                        <a>
                            <div class="buy-media_Loc">
                                <h3 style="color: rgb(145, 144, 144);">7F</h3>
                            </div>
                        </a>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </div>
        </div>

</div>
<br />
<div class="container">
    <div id="cnt" style="width:100%;">
        
        <!--<酒店列表区域>-->
        <div id="hotellist_div" class="grid clx">
            <div class="listContent span9" style="min-height:600px;">
                <div id="hotelSort" class="sort_list clx">
                    <ul id="sortModel">
                        <li class="def"><span id="tuijianTab" class="on" data-type="asc" data-id="1">推荐</span></li>

<!--                        <li class="price">
                            <span data-type="desc" data-id="2" class="">
                                价格
                                <i class="down"></i>
                            </span>
                        </li>-->


                    </ul>
                </div>
                <div style="text-align:center;">
                    <img src="<%=path%>/page/user/web/lxhlist/loadDark.gif" style="width: 80px; height: 80px; display: none;" id="searchLoading">
                </div>
               
                <div id="hotelContent" class="hotelContent clx">
                <c:forEach items="${roomListByPosition}" var="roomP" varStatus="index">
					<div class="hotel-list_a">            
						<div class="hotel-list">             
							<div class="list_cont_right">                      
                                 <div class="listTop">                         
                                     <div class="imgCon span2" style="margin-left:15px;">                                                         
                                     	<a title="${roomP.no}" class="thumbnail"   rel=" nofollow">                             
	                                        <img src="<%=path%>/page/user/web/lxhlist/LOGO_320001_4.jpg" style="width: 100%  !important">                                                                                                               
	                            			<span>查看相册<i></i></span>                    
                                        </a> 
                                     </div>                        
	                                     <div class="infoCon">                             
	                                        	<div class="infoBox span4">                                
		                                        	 <div class="infoTop span4">                                                                       
				                                        	<a  title="中青东方嘉定国际酒店">                                                                                                                 
				                                        		<div class="list_num_box">
				                                        			<span class="num">${index.count}</span>
				                                        		</div>
				                                        		<h2><span class="num">房间号:${roomP.no}</span></h2>                                                         
				                                        	</a>                                       
		                                        	</div>                 
                                      </div>    
                                                   
	                                   <div class="pp_infos span3">     
	                                        <div class="hotelPrice span1" style="">    
		                                      	<a target="_blank" href="javascript:void(0);">
		                                      		<span class="rmb">￥</span>
		                                      		<span class="num">
		                                      			${roomP.roomtypes.price}
		                                      			<b style="font-weight:normal; font-size:12px;"></b>
		                                      		</span>
		                                      	</a>                                                                      
		                                     </div>                               
		                                     	 <ul class="hotelComment span2">                                                                                                                        
		                                      		<li class="lkp">评</li>                                     
		                                      		<li>                                         
		                                      		<a href="http://www.998.com/HotelDetail?hotelCode=320001#dianpin" target="_blank" rel="nofollow" title="查看中青东方嘉定国际酒店的评论">
		                                      		<span class="pingNum">5</span>/5</a> 分                                   
		                                      		</li>                                   
		                                      		<li>来自
		                                      			<a href="http://www.998.com/HotelDetail?hotelCode=320001#dianpin" target="_blank" rel="nofollow">0</a>条住客点评
		                                      		</li> 
		                                      	</ul>                            
	                                     </div>                         
                                     </div>                     
                                   </div>                     
                                     <div id="hotel_map_320001" class="intr_map">                         
                                     	<iframe scrolling="no" width="808" height="300" frameborder="0"></iframe>               
                                     	</div>                 
                                   </div>                                  
                                  <div class="room_list2">                              
                                     	<table class="hotel_datelist" width="100%" cellspacing="0" cellpadding="0" border="0">                   
                                     	<thead>                         
                                     	<tr>                             
	                                     	<th style="background-color: #F6F4F4;width:1%;"></th>                             
	                                     	<th style="width: 30%; background-color: #F6F4F4;text-align:left;padding-left:10px;">房型</th>                                                          
	                                     	<th style="width: 7%; background-color: #F6F4F4; text-align: center">早餐</th>                                                         
                                  			<th style="width: 10%; background-color: #F6F4F4; text-align: center">门市价</th>
                                            <th style="width: 11%; background-color: #F6F4F4; text-align: center">会员专享价</th>                             
                                            <th class="text_right" style="width: 10%; background-color: #F6F4F4;text-align:center">房态</th>                                                                                                                    
                                        <tr class="t"  style="border-bottom:1px dashed #ccc;">                                                         
                                           	<td style="background-color:#ffffff"></td>                                                             
                                           	<td class="hotel_room" style="position: relative;">        
                                           	    <span >                                                                             
                                            			<b></b>${roomP.roomtypes.roomtype}                      
                                  				</span>
                                             </td>                                                    
                                       		<td style="text-align:center;vertical-align:middle;border-bottom:1px dashed #ccc; ">                                 
                                       			<div class="list_pl" style="padding-top:6px">                                                                         
                                       				<u>                                                                                  
                                       					<a>无早 </a>           
                                       				</u>                                
                                       			</div>                            
                                       		</td>                                                                                      
                                       		<td style="text-align:center;vertical-align:middle;border-bottom:1px dashed #ccc; ">                                 
                                       			<u>                                         
                                       				<a href="javascript:;" >${roomP.roomtypes.price}<b></b> </a>                                     
                                       			</u>                                                                                       
                                       		</td>
                                                                        

                                	<td style="vertical-align:middle;text-align:center;border-bottom:1px dashed #ccc; "> 
	                                	<div class="list_pl" style="width:50%;height:25px;float:left;text-align:right;line-height:25px;">                            
	                                		<u>                                       
	                                			<a href="javascript:;" >无</a>                                     
	                                		</u>                                 
	                                	</div> 
	                                	                                 
	                                	<div style="width: 30%; height: 25px; float: left; text-align: left; margin-left: 0px; line-height: 25px; ">                                                                                                              
	                                		 <img src="<%=path%>/page/user/web/lxhlist/arrowTop.png" style="height:15px;">     
	                                	</div>                              
                                	</td>   
                                	                                                                                          
                                	<td class="htl_price_td text_right" style="text-align:center;vertical-align:middle;border-bottom:1px dashed #ccc; ">                                 
                                		<span class="base_price"><dfn>可用</dfn></span>                             
                                	</td>                                                           
                                	<td class="hotel_room_last" style="text-align:center;vertical-align:middle;border-bottom:1px dashed #ccc; ">                                 
                                		<div class="hnsg">
                                			
                                		</div>                            
	                                </td>                             
	                                <td class="hotel_room_last" style="text-align:center;vertical-align:middle;border-bottom:1px dashed #ccc; ">  
	                                </td>                                                              
	                                <td style="text-align:center;vertical-align:middle">                                                                                                   
	                                		<a href="JavaScript:void(0)" class="btn_buy" style="background:#F47920;" id="${roomP.id}">预订</a>    
	                                </td>                                                                               
                               </table>                                     
                            </div>                 
                  					</div>  
                 				 </div>  
                 	
                 </c:forEach>	
                 	
                  			</div> 
                 		  </div> 
                  	  </div> 
                  </div> 
             </div>                			
		                
         <!-- 酒店列表 end -->
                





	<link href="<%=path%>/page/user/web/lxhlist/hotelList.css" rel="stylesheet">

  </body>
  
  
<!--   	<script type="text/javascript">
		//解决预订后房间多一个的问题
		$(function(){
			$("body").ready(function(){
				$("#form-s").submit();
			})
		})
	</script> -->
</html>
