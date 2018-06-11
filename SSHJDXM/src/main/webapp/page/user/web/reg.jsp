<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>用户注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="<%=path%>/css/reg.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/reg.js"></script>

</head>
<body>
	<center>
		<!--头部-->
		<div class="header">
			<a class="logo" href="#"></a>
			<div class="desc">欢迎注册</div>
			<div class="fanhui">
				<a href="<%=path%>/page/user/web/lxhmain.jsp">返回首页</a>
			</div>
			<div class="reg">
				<a href="${pageContext.request.contextPath}/page/account/login.jsp">已有账号?请登陆</a>
			</div>
			<hr />
		</div>
		<div class="container">
		<form action="${pageContext.request.contextPath}/account/userAddServlet" method="post" submit="return false">
			<div class="register">
				<!--用户名-->
				<div class="register-box">
					<div class="box default">
						<label for="userName">登 陆 名 称 </label> <input type="text"
							id="userLoginname" name="userLoginname" placeholder="您的账户名和登录名" />
						<i></i>
					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div>
				<!--设置密码-->
				<div class="register-box">
					<div class="box default">
						<label for="pwd">设 置 密 码 </label> <input type="password"
							id="userPassword" name="userPassword" placeholder="建议至少两种字符组合" />
						<i></i>
					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div>
				<!--确认密码-->
				<div class="register-box">
					<div class="box default">
						<label for="pwd2">确 认 密 码 </label> <input type="password"
							id="pwd2" placeholder="请再次输入密码" /> <i></i>
					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div>
				<!--真实姓名-->
				<div class="register-box">
					<div class="box default">
						<label for="rightName">真 实 姓 名 </label> <input type="text"
							id="userName" name="userName" placeholder="请输入真实姓名" /> <i></i>
					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div>
				<!--性别-->
				<div class="register-box">
					<div class="box default">
						<label for="sex">性 &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;别</label>
						男<input class="sex" type="radio" name="sex" value="0" checked="checked" />
						女<input class="sex" type="radio" name="sex" value="1"/>

					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div>
				<!--手机-->
				<div class="register-box ">
					<div class="box default">
						<label for="mobile">手机号信息</label> <input type="text"
							id="userPhone" name="userPhone" placeholder="输入您的手机号" /> <i></i>
					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div>
				<!--地址-->
				<div class="register-box ">
					<div class="box default">
						<label for="address">地 址 信 息</label>
						 <input type="text" name="userAddress" id="userAddress" placeholder="请输入你的地址" /> <i></i>
					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div>
				<!--邮箱-->
				<div class="register-box">
					<div class="box default">
						<label for="email">邮 箱 信 息 </label> <input type="text"
							id="userMail" name="userMail" placeholder="请输入邮箱" /> <i></i>
					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div>
				<!--验证码-->
				<div class="register-box ">
					<div class="box default">
						<label for="vCode">验证码</label> 
						<input type="text" name="vCode" id="vCode" placeholder="输入验证码" />							
						<img  style="width:100px;height: 30px;text-align: center;" tilte="点击图片换一张"  src="<%=path%>/user/web/CaptServlet.action"  onclick="this.src=this.src+'?'">
					    <i></i>
					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div>
				<!--注册协议-->
				<div class="register-box xieyi">
					<!--表单项-->
					<div class="box default">
						<input type="checkbox" id="ck" /> <span>我已阅读并同意<a href="#">《EGO用户注册协议》</a></span>
					</div>
					<!--提示信息-->
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div>
				<input type="button" id="btn" value="立即注册"/>
			</div>
		</form>
		</div>
	</center>
</body>
</html>
