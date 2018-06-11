<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '<%=path%>/page/user/web/lxhmain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	    <title>老相好官网-全国领先的经济型酒店品牌</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="">
    <meta name="description" content="">

	<link rel="stylesheet" href="<%=path%>/page/user/web/lxhmain/daterangepicker.min.css" />
	<script type="text/javascript" src="<%=path%>/page/user/web/lxhmain/moment.min.js" ></script>
	<script type="text/javascript" src="<%=path%>/page/user/web/lxhmain/jquery.min.js" ></script>
	<script type="text/javascript" src="<%=path%>/page/user/web/lxhmain/jquery.daterangepicker.min.js" ></script>
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
        $(document).ready(function () {
			date=new Date();
			$("#date-range200").val(date.getFullYear()+"-"+add((date.getMonth()+1)) +"-"+add((date.getDate()+0)));
			$("#date-range201").val(date.getFullYear()+"-"+add((date.getMonth()+1)) +"-"+add((date.getDate()+1)));
			
        });
        
        function add(str){
        	return str;
        }
    </script> 
	
	
    <link href="<%=path%>/page/user/web/lxhmain/css.css" rel="stylesheet" type="text/css">
            <link href="<%=path%>/page/user/web/lxhmain/style.css" rel="stylesheet" type="text/css" media="all">

    
    <link href="<%=path%>/page/user/web/lxhmain/layout_101.css" rel="stylesheet">
    
	
	
	
     <!-- Piwik -->
    <!--<script type="text/javascript" async="" defer="defer" src="<%=path%>/page/user/web/lxhmain/piwik.js"></script>-->
<!--    <script type="text/javascript">
    var _paq = _paq || [];
    /* tracker methods like "setCustomDimension" should be called before "trackPageView" */
    _paq.push(['trackPageView']);
    _paq.push(['enableLinkTracking']);
    (function() {
        var u="//piwik.998.com/";
        _paq.push(['setTrackerUrl', u+'piwik.php']);
        _paq.push(['setSiteId', '2']);
        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
        g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);
    })();
    </script>-->
    <!-- End Piwik Code -->






<!--    <script src="<%=path%>/page/user/web/lxhmain/jquery.js"></script>
    <script src="<%=path%>/page/user/web/lxhmain/jquery-1.js"></script>
    <script src="<%=path%>/page/user/web/lxhmain/jquery_003.js"></script>
    <script src="<%=path%>/page/user/web/lxhmain/jquery_002.js"></script>
-->

    <!--[if lt IE 9]>
        <script src="/Scripts/Amendment/Index/html5.js"></script>
        <![endif]-->
    <!--start slider -->
    <link rel="stylesheet" href="<%=path%>/page/user/web/lxhmain/fwslider.css" media="all">
    <script src="<%=path%>/page/user/web/lxhmain/jquery-ui.js"></script>
    <script src="<%=path%>/page/user/web/lxhmain/css3-mediaqueries.js"></script>
    <script src="<%=path%>/page/user/web/lxhmain/fwslider.js"></script>
    <script src="<%=path%>/page/user/web/lxhmain/bootstrap.js"></script>
    <!--end slider -->

    <!--end 通用 -->
    <script src="<%=path%>/page/user/web/lxhmain/common.js"></script>
    <!--end 通用 -->
    <!--end 日期 -->
    <script src="<%=path%>/page/user/web/lxhmain/WdatePicker.js"></script><link href="<%=path%>/page/user/web/lxhmain/WdatePicker.css" rel="stylesheet" type="text/css">
    <!--end 日期 -->
    <link type="text/css" rel="stylesheet" href="<%=path%>/page/user/web/lxhmain/JFFormStyle-1.css">




    <style type="text/css">
        .container_test {
            padding-right: 15px;
            padding-left: 15px;
            margin-right: auto;
            margin-left: auto;
            box-sizing: border-box;
        }



        .btnJoin {
            height: 40%;
            text-align: center;
            border: 1px solid #2A4F67;
            width: 10%;
            color: #2A4F67;
            margin: 0 auto;
            margin-top: 10px;
            min-height: 50px;
            line-height: 50px;
            font-weight: bold;
            border-radius: 0px;
            cursor: pointer;
            min-width:130px;
        }

        .btnJoin:hover {
            background-color: #2A4F67;
            color: #FFF;
        }

        .mid-content a {
            cursor: pointer;
        }

        #ui-datepicker-div {
            background: none;
        }

        .tdChildNav {
            font-size: 0.8em;
            text-align: left;
            padding-top: 5px;
            cursor: pointer;
            padding-left:3px;
        }
        .tdChildNav a{
          text-decoration:none;
          color:#FFF;
        }
            .tdChildNav a:visited {
                text-decoration: none;
            }

            .tdChildNav::outside {
                font-weight: normal;
            }

        .tdTitleNav {
            font-size: 0.95em;
            text-align: left;
            cursor: pointer;
            font-weight: bold;
        }


        .policeInfo {
            clear: both; height: 100%; width: 80%; margin: 0 auto; font-size: 0.8em; color: #FFF; padding-top: 20px; text-align: center;
        }
            .policeInfo a {
                color:#FFF;
                text-decoration:none;
            }
            .policeInfo a:visited {
                color: #FFF;
                text-decoration: none;
            }
        .brand_hotel3 {
            /*transition: all 0.5s linear;
            -webkit-transition: all 0.5s linear;
            -ms-transition: all 0.5s linear;
            -moz-transition: all 0.5s linear;
            -o-transition: all 0.5s linear;*/
        }

        .langRight {
            width: 30px;
            height: 22px;
            line-height: 22px;
            /*float: right;*/
            z-index: 999999;
            /*top: 37px;*/
            /*position: absolute;*/
            right: 0px;
            font-size: 15px;
            color: #FFF;
            cursor: pointer;
        }

        .langLeft {
            width: 30px;
            height: 22px;
            line-height: 30px;
            /*float: right;*/
            z-index: 999999;
            /*top: 37px;
            position: absolute;
            right: 32px;*/
            font-size: 15px;
            color: #FFF;
            cursor: pointer;
            display: none;
        }

        .langCenter {
            width: 30px;
            height: 1px;
            /*top: 37px;
            right: 30px;
            position: absolute;*/
            z-index: 999999;
        }

        .mid-content{
            display:none;
        }
        .unslider-arrow{
            width:40px;
            height:60px;
            position:absolute;
            z-index:99999;
            cursor:pointer;
        }
        #suggest_city_txtCity{
            z-index:999999;
        }
        .MR0{
            margin-right:0px;
        }


        
    </style>
    

    <!-- Set here the key for your domain in order to hide the watermark on the web server -->
    <script type="text/javascript">

    </script>	

	








    <!--nav-->
    <script>
        $(function () {
            var pull = $('#pull');
            menu = $('nav ul');
            menuHeight = menu.height();

            $(pull).on('click', function (e) {
                e.preventDefault();
                menu.slideToggle();
            });

            $(window).resize(function () {
                var w = $(window).width();
                if (w > 320 && menu.is(':hidden')) {
                    menu.removeAttr('style');
                }
            });



            $('#CompanyIntro').carousel({
                interval: 3000,
                pause: "hover"
            });

            $('#myCarousel').carousel({
                interval: 5000
            });



        });
    </script>


	
  </head>
  
  <body>
    <!-- start header -->

<!--    <script src="<%=path%>/page/user/web/lxhmain/al.html" async="" type="text/javascript"></script><script src="<%=path%>/page/user/web/lxhmain/sl.js" type="text/javascript" async=""></script><script type="text/javascript">_slot_id = "5e700c94-bf9d-4968-871a-096009ade7ef";</script><div class="container-fluid" style="max-width:1920px;margin-left:auto;margin-right:auto;">
-->
        <!----start-images-slider---->
        <div class="images-slider">

            <div class="navHead">
               <div class="content">
                   <div class="rightLogo" onclick="ChangeBrand('99');">
                       <img src="<%=path%>/page/user/web/lxhmain/lxh-white.png">
                   </div>

                   <div class="h_right">
                       <ul class="menu">
                           <li class="active"><a href="javascript:void(0)">首页</a></li>
                           <li class="mainlevel">
                               <a href="<%=path%>/page/user/web/lxhlist.jsp">酒店预订</a>
                               <div class="divDroplist">
                                   <div class="divTriangle"></div>
                                   <ul>
                                       <li><a href="http://www.998.com/HotelList/Index">国内酒店</a></li>
                                       <li><a href="javascript:void(0)" onclick="ChangeBrand('90');">海外酒店</a></li>
                                       <li><a href="https://www.greentreeinn.com/cn">美国官网</a></li>
                                   </ul>
                               </div>
                           </li>
                          
                           
                           <li class="mainlevel">
                               <a href="/page/user/signup.jsp">缤纷会员</a>
                               <div class="divDroplist">
                                   <div class="divTriangle"></div>
                                   <ul>
                                       <li><a href="/page/user/signup.jsp">成为会员</a></li>
                                       <li><a href="http://www.998.com/Help/list?typeId=ff8db4d3-4a3f-41f0-9574-c6ed036554af">会员手册</a></li>
                                   </ul>
                               </div>
                           </li>
                           <li class="mainlevel">
                                <a href="javascript:void(0)">新闻论坛</a>
                               <div class="divDroplist">
                                   <div class="divTriangle"></div>
                                   <ul>
                                       <li><a href="http://www.998.com/Forum/Index">论坛社区</a></li>
                                       <li><a href="http://www.998.com/NewsCenter/Index">新闻中心</a></li>
                                   </ul>
                                </div>
                           </li>
                           <li><a href="javascript:void(0)">加盟老相好</a></li>
                           <li><a href="javascript:void(0)">人才招聘</a></li>
                       </ul>
                   </div>

                   <div class="rightLogin">
                       <ul class="menu1" id="loginStatus"><li class="liLogin"><div class="langAll"></div></li><li class="liLogin MR0"><a href="<%=path%>/page/user/web/login.jsp">登录</a>&nbsp;&nbsp;<a href="<%=path%>/page/user/web/reg.jsp">注册</a></li></ul>
						
                   </div>
               </div>

            </div>


            <!-- start slider -->
            <div id="fwslider" style="overflow: hidden; height: 932px;">

                <div class="slider_container">
                                <div class="slide" style="z-index: 0; opacity: 1; display: none;">
                                    
                                    <a href="/page/user/login.jsp" target="_blank"><img src="<%=path%>/page/user/web/lxhmain/1920px-20171214100K5404004.jpg" data-src="" alt=""></a>
                                </div>
                                <div class="slide" style="z-index: 0; opacity: 1; display: none;">
                                    
                                    <a href="/page/user/login.jsp" target="_blank"><img src="<%=path%>/page/user/web/lxhmain/banner1920X936px201712147327881.jpg" data-src="" alt=""></a>
                                </div>
                </div>
                <div class="timers" style="display: none; width: 270px;"> <div class="timer"><div class="progress" style="width: 46.02%; overflow: hidden;"></div></div><div class="timer"><div class="progress" style="width: 0%;"></div></div><div class="timer"><div class="progress" style="width: 0%;"></div></div><div class="timer"><div class="progress" style="width: 0%;"></div></div><div class="timer"><div class="progress" style="width: 0%;"></div></div></div>
                <div class="slidePrev" style="left: 0px; top: 443px;"><span> </span></div>
                <div class="slideNext" style="right: 0px; top: 443px;"><span> </span></div>
            </div>
            <!--/slider -->
            <!--/slide -->





            <script type="text/javascript">
                function show(obj) {
                    var XS = document.getElementById(obj);
                    XS.style.display = "block";
                }
                function hide(obj) {
                    var YC = document.getElementById(obj);
                    YC.style.display = "none";
                }
            </script>










        </div>





<form action="<%=path%>/user/yuding/finRoomsByPositionFromMain" method="post">
        <!--start main -->
        <div class="main_bg">
            <div class="wrap">
                <div class="online_reservation">
                    <div class="b_room">
                        <div class="booking_room">
                            <h4>开始预订</h4>
                            <p>
                         老相好-市安心住-优质服务-快乐享受。房型随心享，快乐无忧的旅程。
      </p>
                        </div>
                        <div class="reservation">
                            <ul>
                                <li class="span1_of_1" style="width:12%;">
                                    <h5>入住信息</h5>
                                    <!----------start section_room----------->
<!--                                    <div class="book_city">
                                        <input class="date" id="txtCity" autocomplete="off" _cqnotice="中文/拼音" name="CityName" data-value="226" value="" data-name="" type="text">
                                    </div>-->
                                </li>
                                
                                <li class="span1_of_1 left">
                                    <h5>入住日期:</h5>
                                    <div class="book_date">
                                        <form>
                                            <input class="date sicon" id="date-range200" _cqnotice="yyyy-mm-dd" name="startDate" autocomplete="off" value="" type="text">
                                        </form>

                                    </div>
                                </li>
                                <li class="span1_of_1 left">
                                    <h5>离店日期:</h5>
                                    <div class="book_date">
                                         <input class="date sicon" id="date-range201" _cqnotice="yyyy-mm-dd" name="endDate" autocomplete="off" value="" type="text">
                                    </div>
                                </li>
                                <li class="span1_of_2 left">
                                    <h5>备注:</h5>
                                    <div class="book_date">
                                        <input class="date" id="txtKeyword" autocomplete="off" _cqnotice="(选填)酒店名/地标/商圈/地址" name="keywordNew" maxlength="50" data-value="" placeholder="(选填)备注" value="" type="text">
                                    </div>
                                </li>
                                <li class="span1_of_3">
                                    <div class="date_btn">
                                        <input value="搜索" id="btnSearch" type="submit">
                                    </div>
                                </li>
                                
                                <div class="clear"></div>
                            </ul>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
</form>

        <div class="grids_of_3 threeHotel" id="divRecommendHotel">
        	<div class="grid1_of_3">
        		<div class="grid1_of_3_img" style="cursor:pointer;">
        			<a target="_blank" data-id="122528">
        				<div class="brand_hotel3" style="background-position:center;width: 100%; height:300px;background-size:100%; background-repeat: no-repeat;border-radius:5px;background-image: url(<%=path%>/page/user/web/lxhmain/tuijian-tu1.png);"></div>
        				<span class="next"> </span>
        			</a>
        		</div>
        		<h4><a href="#">￥108起<span></span></a></h4>
        		<p>北京邮电学校老相好快捷酒店</p>
        		<p>地址：杏石口路xxxx号5号院</p>
        		<p>电话：888-8888-8888</p>  
        		<p class="hotelScore" style="cursor:pointer;" data-id="122528">
        			<font color="#2A4F67">4.9</font> /5 分 来自 378 条住客点评
        		</p>
        	</div>
    		<div class="grid1_of_3">
    			<div class="grid1_of_3_img" style="cursor:pointer;">
    				<a target="_blank" data-id="171899"> 
    					<div class="brand_hotel3" style="background-position: center center; width: 100%; height: 300px; background-size: 100.287% auto; background-repeat: no-repeat; border-radius: 5px; background-image: url(<%=path%>/page/user/web/lxhmain/tuijian-tu2.png);">
    						
    					</div>
    					<span class="next"> </span>
    				</a>
    			</div>
    			<h4><a href="#">￥220起<span></span></a></h4>
        		<p>北京邮电学校老相好快捷酒店</p>
        		<p>地址：杏石口路xxxx号5号院</p>
        		<p>电话：888-8888-8888</p> 
				<p class="hotelScore" style="cursor:pointer;" data-id="171899">
					<font color="#2A4F67">4.9</font> /5 分 来自 30 条住客点评
				</p>
    		</div>
			<div class="grid1_of_3">
				<div class="grid1_of_3_img" style="cursor:pointer;">
					<a target="_blank" data-id="002718"> 
						<div class="brand_hotel3" style="background-position:center;width: 100%; height:300px;background-size:100%; background-repeat: no-repeat;border-radius:5px;background-image: url(<%=path%>/page/user/web/lxhmain/tuijian-tu3.png);">
							
						</div>
						<span class="next"> </span>
					</a>
				</div>
				<h4><a href="#">￥99.8起<span></span></a></h4>
        		<p>北京邮电学校老相好快捷酒店</p>
        		<p>地址：杏石口路xxxx号5号院</p>
        		<p>电话：888-8888-8888</p> 
				<p class="hotelScore" style="cursor:pointer;" data-id="002718">
					<font color="#2A4F67">5</font> /5 分 来自 1 条住客点评
				</p>
			</div>
			<div class="clear">
							
			</div>
        </div>











        <div style="max-width:1476px;margin-left:auto;margin-right:auto;">

            <div class="divBrand">
                <div style="height:50px;"></div>
                <div style="height:100px;">
                    <div class="childBrand greenWest brandLeft" style="margin-left:-10px;" onclick="ChangeBrand('40');">
                        <em class="emBrand" style="background-color: rgb(235, 97, 0); left: 0px; display: none; position: relative; top: 0px;"><span class="spanBrand" style="border-color: #EB6100 transparent transparent;"></span>行在云端，功成东方</em>
                    </div>
                    <div class="childBrand greenTreeinn brandLeft" style="margin-left:-10px;" onclick="ChangeBrand('00');">
                        <em class="emBrand" style="background-color: rgb(133, 192, 40); left: 0px; display: none; position: relative; top: 0px;"><span class="spanBrand" style="border-color: #85c028 transparent transparent;"></span>经典商务，品质之选</em>
                    </div>
                    <div class="childBrand greenUnion brandLeft" style="margin-left:-10px;" onclick="ChangeBrand('30');">
                        <em class="emBrand" style="background-color: rgb(253, 208, 0); left: 0px; display: none; position: relative; top: 0px;"><span class="spanBrand" style="border-color: #fdd000 transparent transparent;"></span>个性选择，共享梦想</em>
                    </div>
                    <div class="childBrand vatica brandLeft" style="margin-left:-10px;" onclick="ChangeBrand('50');">
                        <em class="emBrand" style="background-color: rgb(109, 177, 49); left: 0px; display: none; position: relative; top: 0px;"><span class="spanBrand" style="border-color: #6DB131 transparent transparent;"></span>城市中的绿洲</em>
                    </div>
                    <div class="childBrand shellHotel" onclick="ChangeBrand('70');">
                        <em class="emBrand" style="background-color: rgb(230, 0, 18); left: 0px; display: none; position: relative; top: 0px;"><span class="spanBrand" style="border-color: #E60012 transparent transparent;"></span>懂时尚，更懂年轻</em>
                    </div>
                    <div class="childBrand overSea" onclick="ChangeBrand('90');">
                        <em class="emBrand" style="background-color: rgb(161, 123, 79); left: 0px; display: none; position: relative; top: 0px;"><span class="spanBrand" style="border-color: #A17B4F transparent transparent;"></span>一个国家，一段故事</em>
                    </div>
                </div>
            </div>

            <div style="width:85%;height:30px;border-top:1px dashed #DEDEDE;margin-left:auto;margin-right:auto; margin-bottom:-30px;margin-top:20px;"></div>

            <div class="grids_of_3" style="background-color:#FFF;min-height:150px;">
                <div class="carousel slide" id="CompanyIntro">
                    <!-- Carousel items -->
                    <div class="carousel-inner" style="min-height:200px;">
                        <div class="item active">
                            <div style="height: 15%; color: #484848;text-align:center;font-size:1.7em;">老相好简介</div>
                            <div style="text-indent:3%; line-height:30px; text-align: left; font-size: 1em; color: #1C1C1C; padding-top: 10px; width: 80%; margin: 0 auto; ">
                                
老相好酒店管理集团是一家大型专业化酒店管理集团，旗下拥有众多优质优质品牌，
酒店的房型有多种选择，提供了套房、每个客房都配有花园景, 露台, 卫星频道, 平面电视, 空调, 客厅角, 电风扇, 沙发, 木质/镶木地板, 衣柜/衣橱, 晾衣架, 淋浴, 浴缸, 卫生间, 浴室, 户外家具。
                            </div>
                            <div style="text-indent: 3%; line-height: 30px; text-align: left; font-size: 1em; color: #1C1C1C; padding-top: 10px; width: 80%; margin: 0 auto; ">
                                
秉持“天天低价”原则，我们坚持提供“超健康、超舒适、超价值、超期望”的酒店产品和服务，并以辅助加盟合作伙伴从
生意人发展成为大企业家，培养员工获得能力上的提升和职业上的发展为企业使命。
                            </div>
                        </div>
                        <div class="item">
                            <div style="height: 15%; color: #484848;text-align:center;font-size:1.7em;">加入老相好，超越特权</div>
                            <div style="text-indent: 3%; line-height: 30px; text-align: left; font-size: 1em; color: #1C1C1C; padding-top: 10px; width: 80%; margin: 0 auto; ">
                               老相好会员是老相好集团最尊贵的客人，会员权益覆盖丰富的旅行产品。折扣、积分、特权、商城完美购物，只为特别的你~~~~~~~~。
                            </div>
                        </div>
                    </div>
                    <!-- Carousel nav -->
                    
                </div>

                <div class="btnJoin" onclick="window.location = '/UserRegister/Signin'">马上加入</div>
            </div>

            


                <div id="myCarousel" class="carousel slide">
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class=""></li>
                        <li data-target="#myCarousel" data-slide-to="1" class=""></li>
                        <li data-target="#myCarousel" data-slide-to="2" class=""></li>
                        <li data-target="#myCarousel" data-slide-to="3" class=""></li>
                        <li data-target="#myCarousel" data-slide-to="4" class=""></li>
                        <li data-target="#myCarousel" data-slide-to="5" class=""></li>
                        <li data-target="#myCarousel" data-slide-to="6" class="active"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="item" style="height:350px;">
                            <a target="_blank" href="javascript:viod(0)"><img src="<%=path%>/page/user/web/lxhmain/banner1340-201712144899882.jpg" style="width:100%" data-src=""></a>
                        </div>
                        <div class="item" style="height:350px;">
                            <a target="_blank" href="javascript:viod(0)"><img src="<%=path%>/page/user/web/lxhmain/66685318.jpg" style="width:100%" data-src=""></a>
                        </div>
                     
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="carousel-control left" href="#myCarousel" data-slide="prev">‹</a>
                    <a class="carousel-control right" href="#myCarousel" data-slide="next">›</a>

                </div>


            <div style="width: 100%; background-color: #FFF; clear:both;margin-top:30px;">
                <div style="min-height: 100px; width: 29%; float: left; ">
                    <div style="height:100%;width:40%;float:left;text-align:right;">
                        <img src="<%=path%>/page/user/web/lxhmain/Hotel_Quality.png">
                    </div>
                    <div style="width: 10%; float: left; min-height: 100px;"></div>
                    <div style="min-height: 100px; width: 50%; float: left">
                        <table style="width: 100%; min-height: 100px">
                            <tbody><tr><td style="font-size: 1.2em; min-height: 20px; color:#313131;">酒店品质保证</td></tr>
                            <tr><td style="font-size: 0.9em; min-height: 80px; color: #707070;">上线酒店均经严格质检，<br></td></tr>
                        </tbody></table>
                    </div>
                </div>

                <div style="min-height: 100px; width: 30%; float: left; margin-left: 1%; ">
                    <div style="height:100%;width:40%;float:left;text-align:right;">
                        <img src="<%=path%>/page/user/web/lxhmain/Low_Price.png">
                    </div>
                    <div style="width: 10%; float: left; min-height: 100px;"></div>
                    <div style="min-height: 100px; width: 50%; float: left">
                        <table style="width: 100%; min-height: 100px">
                            <tbody><tr><td style="font-size: 1.2em; min-height: 20px; color:#313131;">低价保证</td></tr>
                            <tr><td style="font-size: 0.9em; min-height: 80px; color: #707070;">会员专享折扣与优惠，<br>在线预订低价保证！</td></tr>
                        </tbody></table>
                    </div>
                </div>



                <div style="min-height: 100px; width: 32%; float: left; margin-left: 2%; ">
                    <div style="height:100%;width:40%;float:left;text-align:right;">
                        <img src="<%=path%>/page/user/web/lxhmain/998_Sale.png">
                    </div>
                    <div style="width: 10%; float: left; min-height: 100px;"></div>
                    <div style="min-height: 100px; width: 50%; float: left">
                        <table style="width: 100%; min-height: 100px">
                            <tbody><tr><td style="font-size: 1.2em; min-height: 20px; color:#313131;">实时特惠</td></tr>
                            <tr><td style="font-size: 0.9em; min-height: 80px; color: #707070;">99.8元特惠房，<br>会员在线预订独享！</td></tr>
                        </tbody></table>
                    </div>
                </div>

            </div>

            <div style="width: 100%; background-color: #FFF; clear:both;padding-top:30px;">
                <div style="min-height: 100px; width: 29%; float: left; ">
                    <div style="height:100%;width:40%;float:left;text-align:right;">
                        <img src="<%=path%>/page/user/web/lxhmain/Green_Bee.png">
                    </div>
                    <div style="width: 10%; float: left; min-height: 100px;"></div>
                    <div style="min-height: 100px; width: 50%; float: left">
                        <table style="width: 100%; min-height: 100px">
                            <tbody><tr><td style="font-size: 1.2em; min-height: 20px; color:#313131;">积分多多</td></tr>
                            <tr><td style="font-size: 0.9em; min-height: 80px; color: #707070;">在线订最高积12倍积分，<br>超值订购</td></tr>
                        </tbody></table>
                    </div>
                </div>
                <div style="min-height: 100px; width: 30%; float: left; margin-left: 1%; ">
                    <div style="height:100%;width:40%;float:left;text-align:right;">
                        <img src="<%=path%>/page/user/web/lxhmain/Download.png">
                    </div>
                    <div style="width: 10%; float: left; min-height: 100px;"></div>
                    <div style="min-height: 100px; width: 50%; float: left">
                        <table style="width: 100%; min-height: 100px">
                            <tbody><tr><td style="font-size: 1.2em; min-height: 20px; color:#313131;">下载APP二维码</td></tr>
                            <tr><td style="font-size: 0.9em; min-height: 80px; color: #707070;">APP首次登录送￥100代金券</td></tr>
                        </tbody></table>
                    </div>
                </div>

                <div style="min-height: 100px; width: 32%; float: left; margin-left: 2%; ">
                    <div style="height:100%;width:40%;float:left;text-align:right;">
                        <img src="<%=path%>/page/user/web/lxhmain/WeChat.png">
                    </div>
                    <div style="width: 10%; float: left; min-height: 100px;"></div>
                    <div style="min-height: 100px; width: 50%; float: left">
                        <table style="width: 100%; min-height: 100px">
                            <tbody><tr><td style="font-size: 1.2em; min-height: 20px; color:#313131;">关注老相好公众号</td></tr>
                            <tr><td style="font-size: 0.9em; min-height: 80px; color: #707070;">绑定微信，得¥100代金券</td></tr>
                        </tbody></table>
                    </div>
                </div>

            </div>

        </div>

        <!--start main -->
        
<link rel="stylesheet" href="<%=path%>/page/user/web/lxhmain/_foot.css" media="all">
<!--[if lt IE 9]>
    <style type="text/css">

        .footer_bg{
             width:1000px;
            margin-left:auto;
            margin-right:auto;
        }

    </style>
<![endif]-->



    <!--start main -->
<div class="footer_bg" style="clear: both; min-height: 120px; background-color: #2A4F67; color: #FFF; filter: alpha(opacity=100); -moz-opacity: 1; -khtml-opacity: 1; opacity: 1;">
    <div style="min-height: 100px; width: 80%; margin-left:auto;margin-right:auto; padding-top:25px;">
        <div class="footerLeftF">
            <div style="height: 50px; width: 100%; line-height: 70px; font-size: 15.2px;font-weight:bold">老 相 好     只 需 感 受</div>
            <div style="height: 50px; width: 100%; line-height: 30px; font-size: 13.6px;">客服热线：8888-88-888</div>
        </div>
        <div style="width: 5%; float: left; min-height: 100px; border-right: 1px solid #FFF;"></div>
        <div style="width: 5%; float: left; min-height: 100px;"></div>
        <div class="footerRightF">
            <div style="width:25%;float:left;height:100%;">
                <table>
                    <tbody><tr><td class="tdTitleNavF">酒店预订</td></tr>
                    <tr><td class="tdChildNavF"><a href="http://www.998.com/HotelList" target="_blank">列表预订</a></td></tr>
                    <tr><td class="tdChildNavF"><a href="http://www.998.com/HotelMap" target="_blank">地图预订</a></td></tr>
                    <tr><td class="tdChildNavF"><a href="https://www.greentreeinn.com/en" target="_blank">老相好</a></td></tr>
                </tbody></table>
            </div>
            <div style="width:25%;float:left;height:100%;">
                <table>
                    <tbody><tr><td class="tdTitleNavF">合作加盟</td></tr>
                    <tr><td class="tdChildNavF"><a href="http://www.998.com/Join/JoiningGreen" target="_blank">加盟老相好</a></td></tr>
                    <tr><td class="tdChildNavF"><a href="http://www.998.com/Information/cooperate" target="_blank">商务合作</a></td></tr>
                    <tr><td class="tdChildNavF"><a href="http://www.998.com/NewsCenter/Detail?id=35a24f2e-8b21-48ea-89d1-66381698c80c" target="_blank">供应商名录</a></td></tr>
                </tbody></table>
            </div>
            <div style="width:25%;float:left;height:100%;">
                <table>
                    <tbody><tr><td class="tdTitleNavF">关于老相好</td></tr>
                    <tr><td class="tdChildNavF"><a href="http://www.998.com/Information/TouchUs" target="_blank">联系我们</a></td></tr>
                    <tr><td class="tdChildNavF"><a href="http://www.998.com/Information/defaultu" target="_blank">关于老相好</a></td></tr>
                    <tr><td class="tdChildNavF"><a href="http://www.998.com/Information/Links" target="_blank">友情链接</a></td></tr>
                </tbody></table>
            </div>
            <div style="width:25%;float:left;height:100%;">
                <table>
                    <tbody><tr><td class="tdTitleNavF">老相好微博</td></tr>
                    <tr><td class="tdChildNavF"><a href="http://www.998.com/Information/sina" target="_blank">老相好官微</a></td></tr>
                    <tr><td class="tdChildNavF"><a href="http://www.998.com/Information/shuguang" target="_blank">曙光微博</a></td></tr>
                    <tr><td class="tdChildNavF"><a href="http://998.com/Help/detaile?typeId=a5e11fd4-deea-4cd0-8cbe-aa64aaa06511&amp;infoId=1706d4ae-2da6-4224-b826-362795d89d1e" target="_blank">投诉建议</a></td></tr>
                </tbody></table>
            </div>
        </div>



    </div>
    <div class="policeInfoF">
        <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=31010702001346">沪公网安备 31010702001346号</a>&nbsp;&nbsp;老相好管理集团版权所有 沪ICP备14035076号
    </div>
    <div style="width:80%;min-height:10px;">

    </div>
</div>








</div>




    
















<script id="KeyWordTemplate" type="text/html">
    <div id="popKeyWord_${id}" class="c_address_box" style="display: 
none">
        <a class="close" href="javascript:;">×</a>
        {{if zoneList}}
        <dl class="key_word_list">
            <dt>热门商圈</dt>
            <dd>
                {{each zoneList}}<a data-category="zoneId" 
id="p_keyword_${$value.ID}" data="${$value.Name}" title="${$value.Name}"
 href="javascript:void(0);">${$value.Name}</a>{{/each}}
            </dd>
        </dl>
        {{/if}}

        {{if slList}}
        <dl class="key_word_list">
            <dt>机场车站</dt>
            <dd>
                {{each slList}}<a data-category="slId" 
id="p_keyword_${$value.ID}" data="${$value.Name}" title="${$value.Name}"
 href="javascript:void(0);">${$value.Name}</a>{{/each}}
            </dd>
        </dl>
        {{/if}}

        {{if metroList}}
        <dl class="key_word_list">
            <dt>地铁</dt>
            <dd>
                {{each metroList}}<a data-category="metroId" 
id="p_keyword_${$value.ID}" data="${$value.Name}" title="${$value.Name}"
 href="javascript:void(0);">${$value.Name}</a>{{/each}}
            </dd>
        </dl>
        {{/if}}

        {{if locationList}}
        <dl class="key_word_list">
            <dt>行政区</dt>
            <dd>
                {{each locationList}}<a data-category="locationId" 
id="p_keyword_${$value.ID}" data="${$value.Name}" title="${$value.Name}"
 href="javascript:void(0);">${$value.Name}</a>{{/each}}
            </dd>
        </dl>
        {{/if}}
        {{if merchantList}}
        <dl class="key_word_list">
            <dt>酒店信息</dt>
            <dd id="Merchant_Hotel">
                {{each merchantList}}<a data-category="locationId" 
id="p_keyword_${$value.ID}" data="${$value.Name}" title="${$value.Name}"
 href="javascript:void(0);">${$value.Name}</a><br />{{/each}}
            </dd>
        </dl>
        {{/if}}
    </div>
</script>

<script id="KeyWordSuggestTemplate" type="text/html">
    <div id="suggestKeyWord_${id}" class="c_address_boxd">
        <div class="c_address_title">
            <p>若需缩小范围，需填更多信息</p>
            <a class="close" href="javascript:;">×</a>
        </div>

        {{if mdList}}
        <div class="sisa">
            <span>门店</span>
            {{each mdList}}<a data-category="zdId" 
id="s_keyword_${$value.ID}" data="${$value.Name}" title="${$value.Name}"
 href="javascript:void(0);">${$value.Name}</a>{{/each}}
        </div>
        {{/if}}

        {{if jcList}}
        <div class="sisa">
            <span>机场车站</span>
            {{each jcList}}<a data-category="zdId" 
id="s_keyword_${$value.ID}" data="${$value.Name}" title="${$value.Name}"
 href="javascript:void(0);">${$value.Name}</a>{{/each}}
        </div>
        {{/if}}


        {{if merchantList}}
        <div class="sisa">
            <span>酒店信息</span>
            {{each merchantList}}
            <a data-category="otherId" id="s_keyword_${$value.ID}" 
data="${$value.Name}" title="${$value.Name}" 
href="javascript:void(0);">${$value.Name}</a>{{/each}}
        </div>
        {{/if}}




        {{if otherList}}
        <div class="sisa">
            <span>其它</span>
            {{each otherList}}<a data-category="otherId" 
id="s_keyword_${$value.ID}" data="${$value.Name}" 
data-value="${$value.Pointxy}" title="${$value.Name}" 
href="javascript:void(0);">${$value.Name}</a>{{/each}}
        </div>
        {{/if}}

        {{if searchList}}
        <div class="sisa">
            <span>搜索</span>
            {{each searchList}}<a data-category="otherId" 
id="s_keyword_seach_${$value.ID}" data="${$value.Name}" 
data-value="${$value.Pointxy}" title="${$value.Name}" 
href="javascript:void(0);">${$value.Name}</a>{{/each}}
        </div>
        {{/if}}
    </div>
</script>

<script src="<%=path%>/page/user/web/lxhmain/a.html" type="text/javascript" charset="utf-8" async=""></script>
    
  </body>
</html>
