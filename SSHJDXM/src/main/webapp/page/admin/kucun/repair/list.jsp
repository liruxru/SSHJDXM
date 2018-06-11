<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

<title>查看商品</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/goodList.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
	     //单个删除的方法
	     $(".del").click(function(){
	         var goodsId = $(this).attr("name");
	         var trObj=$(this).parent().parent();     
	         if(confirm("是否删除")){
		         var url="${pageContext.request.contextPath}/admin/kucun/goodsdelete.action";
		         $.get(url,
		               {"goods.id":goodsId},
		               function(res){
		                 if (res == 0) {
							window.location.reload();
						 }else{
						    alert("删除失败")
						 }
		               },
		               'json');	       
		         }
	     	
	     });
	})

</script>
</head>

<body>

	<div class="table_div">
		<div class="div_clear">
		  <a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
		</div>
		<div class="div_clear">
			<div class="center_center">
				<div class="table_content">
					<table cellspacing="0px" cellpadding="0px">
						<thead>
							<tr>								
								<th>房间号</th>
								<th>维修问题</th>							
								<th>报修日期</th>
								<th>房间状态</th>		
								<th style="border-right:none">操作</th>
							</tr>
						</thead>
						<tbody>							
							<c:forEach items="${repairList}" var="g">
							<tr>								
								<td>${g.rooms.no}</td>
								<td>${g.description}</td>
								<td><fmt:formatDate value="${g.createdate}" pattern="yyyy年MM年dd日  HH:mm:ss"/></td>
								<td><c:if test="${g.rooms.roomstatus.id eq 7}">待维修</c:if><c:if test="${g.rooms.roomstatus.id ne 7}">已维修</c:if></td>
								<td style="border-right:none">
									<img width='16' height='16'	src="img/table/20160414160935161.gif" style="vertical-align:middle" />
									<a href="${pageContext.request.contextPath}/admin/kucun/goodsedit.action?goods.id=${g.id}&currentPage=${page.currentPage}">修改</a>								</td>
							    </td>
							</tr>
						    </c:forEach>						
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
