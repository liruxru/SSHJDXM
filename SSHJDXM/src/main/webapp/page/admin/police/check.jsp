<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
      <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function(){
	
		$("#checkIdCard").click(function(){
		
			var value=$("#idcard").val();
			location="${pageContext.request.contextPath }/admin/qiantai/findUserByIdCard?idCard="+value;
		
		})
		
		
		$("#checkAll").click(function(){
		
			
			location="${pageContext.request.contextPath }/admin/qiantai/findAllRoomUser"
		
		})
	
	
	
	})




</script>	
  </head>
  
  <body>
  <h1>通过身份证查询当天入住信息</h1>
  <form>
  	<input  id="idcard"     />
  </form>
   <input  type="button" id="checkIdCard"  value="通过身份证查询入住信息"   />
 
   
  <input   type="button" id="checkAll" value="浏览今晚全部客人信息"   />
   
   	
  </body>
</html>
