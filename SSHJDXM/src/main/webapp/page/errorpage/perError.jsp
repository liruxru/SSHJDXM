<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>这个.. 页面没有找到权限！！!</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


	<style type="text/css">
		body{ margin:0; padding:0; background:#efefef; font-family:Georgia, Times, Verdana, Geneva, Arial, Helvetica, sans-serif; }
		div#mother{ margin:0 auto; width:943px; height:572px; position:relative; }
		div#errorBox{ background: url(img/pererror.jpg) no-repeat top left; width:943px; height:572px; margin:auto; }
		div#home{ margin:20px 0 0 444px;}
		div#contact{ margin:20px 0 0 25px;}
		h1{ font-size:40px; margin-bottom:35px; }
	</style>
  </head>
  
  <body>
    <div id="mother">
			<div id="errorBox">
				<a href="" ></a>
			</div>
		</div>
  </body>
</html>