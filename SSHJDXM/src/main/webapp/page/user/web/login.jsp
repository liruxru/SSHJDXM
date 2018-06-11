<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="css/userLogin.css">
   <style type="text/css">
         b {
				display: inline-block;
				*display: inline;
				*zoom: 1;
				width: 18px;
				height: 14px;
				margin: 0 5px;
				background: url(${pageContext.request.contextPath}/img/q-icon.png) no-repeat;
				overflow: hidden;
				vertical-align: middle;
			}
			
   </style>
  </head>
  
  <body>
   
		<div class="w">
			<!-- 头部，logo部分 -->
			<div class="logo">
				<img alt="lxh" src="<%=path%>/page/user/web/lxhmain/lxh.png" width="140px" height="70px"></img>
				<font size="5px" class="welcome-login">欢迎登录</font>
			</div>
			<a class="questions" onmouseover="color:red;" target="_blank" href="#"><b></b>登录页面，调查问卷</a>
		</div>
		<!-- body -->
		<div id="content">
			<div class=login-wrap>
				<div class="w">
					<!-- 登录框 -->
					<div class="login-form">
						<div class="login-tab login-tab-l">
							<a class="ss" href="javascript:void(0)" style="color: #666">扫码登录</a>
						</div>
						<div class="login-tab login-tab-r">
							<a class="ss" href="javascript:void(0)" style="color:#f00">账户登录</a>
						</div>
						<!-- 主体 -->
						<div class="login-box">
							<div class="mt tab-h"></div>
							<div class="msg-wrap">
								<div class="msg-warn">
									<label>公共场所不建议自动登录，以防账号丢失</label>
								</div>
							</div>

							<!-- 输入框 -->
							<div class="mc">
								<div class="form">
									<form id="formlogin" action="<%=path%>/user/web/login.action" method="post">
										<div class="item item-fore1">											
											<label for="loginanme" class="login-label name-label ">账号</label>
											<input class="itxt" type="text" name="loginName" value="${userLoginname}" placeholder="邮箱/用户名/已验证手机">
										</div>
										<div class="item item-fore2">
											<label for="loginanme" class="login-label name-label">密码</label>
											<input type="password" class="itxt" name="passWord" placeholder="请输入密码">
										</div>
										<span style="color: red">${msg}</span>

										<div class="item item-fore4">
											<div class="safe">
												<input class="jdcheckbox" type="checkbox" name="remember">自动登录
												<a href="<%=path%>/page/user/web/reg.jsp" class="forgetPassword" style="text-align: right;">立即注册</a>
											</div>
										</div>

										<!-- 登录按钮 -->
										<div class="item item-fore5">
											<div class="login-btn">
												<input id="but" class="but" name="" type="submit" value="登    录" />
											</div>
										</div>

									</form>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="login-banner">
					<!-- style="background-color:#041422" -->
					<div class="w">
						<img class="main-img" height="347px" width="645px" src="<%=path%>/page/user/web/lxhmain/login_banner.jpg" alt="lxh">
						<!-- <div id="banner-bg"  class="i-inner"   
                        style="background: url(//img11.360buyimg.com/da/jfs/t3154/258/5179306513/128208/9d5b12bd/5864cf6eN542ab244.jpg) "></div> -->
					</div>
				</div>
			</div>

		</div>

		<div class="w">
			<div id="footer">
				<div class="links">
					<a rel="nofollow" target="_blank" href="//www.jd.com/intro/about.aspx">
						关于我们
					</a>
					|
					<a rel="nofollow" target="_blank" href="//www.jd.com/contact/">
						联系我们
					</a>
					|
					<a rel="nofollow" target="_blank" href="//zhaopin.jd.com/">
						人才招聘
					</a>
					|
					<a rel="nofollow" target="_blank" href="//www.jd.com/contact/joinin.aspx">
						商家入驻
					</a>
					|
					<a rel="nofollow" target="_blank" href="//www.jd.com/intro/service.aspx">
						广告服务
					</a>
					|
					<a rel="nofollow" target="_blank" href="//app.jd.com/">
						手机LXH
					</a>
					|
					<a target="_blank" href="/links.vm/club.jd.com/links.aspx">
						友情链接
					</a>
					|
					<a target="_blank" href="//media.jd.com/">
						销售联盟
					</a>
					|
					<a href="//club.jd.com/" target="_blank">
						LXH社区
					</a>
				</div>

			</div>
		</div>
	</body>
</html>
