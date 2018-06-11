<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
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
	    $(".del").click(function(){
	          var id  = $(this).attr("name");
	          var obj = $(this).parent().parent();
	          url="${pageContext.request.contextPath}/admin/entrepot/entrepotDelete.action";
	          if(confirm("是否删除")){
	           $.post(url,
	                 {"entrepot.id":id},
	                 function(res){	          
	                    if(res==0){
	                      obj.remove();
	                    }else{
	                      alert("删除失败");
	                    }
	                 },'json')	          
	          }else{
	           return;
	          }       
	    })
	    
	    //全选的方法
	    	    
	 
	 }) 
	</script>
  </head>
  
  <body>
    
	<div class="table_div">
		<div class="div_clear">
		   <a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
			<div class="center_top">
				<div style="float:right;padding-right:6px">
					<img width='16' height='16' src="img/table/20160414160909739.gif" style="vertical-align:middle" />
					<a href="${pageContext.request.contextPath}/admin/entrepot/entrepotEdit.action">新增</a>&nbsp;
				</div>
			</div>
		</div>
		<div class="div_clear">
			<div class="center_center">
				<div class="table_content">
					<table cellspacing="0px" cellpadding="0px">
						<thead>
							<tr>
								<th>编号</th>
								<th>位置</th>
								<th>电话</th>
								<th>责任人</th>					
								<th style="border-right:none">操作</th>
							</tr>
						</thead>
						<tbody>							
							<c:forEach items="${listEntrepot}" var="g" varStatus="v">
							<tr>
								<td>${g.sn}</td>
								<td>${g.address}</td>
								<td>${g.phone}</td>
								<td>${g.staffs.name}</td>					
								<td style="border-right:none">
									<img width='16' height='16'	src="img/table/20160414160935161.gif" style="vertical-align:middle" />
									<a href="${pageContext.request.contextPath}/admin/entrepot/entrepotEdit?entrepot.id=${g.id}">修改</a>&nbsp;
									<img width='16' height='16' src="img/table/20160414160926864.gif" style="vertical-align:middle" />
									<a  href="javascript:void(0)" name="${g.id}" class="del">删除</a>
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
