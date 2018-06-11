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
    
    <title>查看非卖品</title>
    
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
	          var id  = $(this).attr("name");
	          var obj = $(this).parent().parent();
	          url="${pageContext.request.contextPath}/admin/kucun/dittydelete.action";
	          if(confirm("是否删除")){
	           $.post(url,
	                 {"ditty.id":id},
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
	    
	    $("#but").click(function(){
		    if(confirm("是否确定删除这些数据?")){
		        document.delForm.submit();
		    }else{
		        return;
		    }
	    })	
	    
	    
	    
	    
	    //全选的方法。
	    $("#allChk").click(function(){
	         var obj = $(".ch");
	         for(var i=0;i<obj.length;i++){
	            obj.prop("checked",$("#allChk").prop("checked"));
	         }
	         changBut();   
	    })
	    
	    //单击单选框时，也要按钮状态
	    $(".ch").click(function(){
	         changBut();   
	    })
	    
	        	    
	 
	 }) 
	 
	 //写一个改变全选按钮状态的事件
	 function changBut(){
	 	var j =0;
	 	var obj = document.getElementsByName("ch");
	 	var but = document.getElementById("but");
	 	for(var i =0;i<obj.length;i++){
	 	  if(obj[i].checked){	 	  
	 	     j++;
	 	  }	
	 	}
	 	if(j>1){
	 	  but.disabled="";
	 	}else{
	 	  but.disabled="disabled";
	 	}	 
	 }
	</script>
  </head>  
	
<body>
 <a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
		<div class="div_clear">		   
			<div class="center_top">
				<div style="float:right;padding-right:6px">
					<img width='16' height='16' src="img/table/20160414160909739.gif" style="vertical-align:middle" />
					<a href="${pageContext.request.contextPath}/admin/kucun/dittyedit.action">新增</a>&nbsp; 					
					<img width='16' height='16' src="img/table/20160414160859494.gif"	style="vertical-align:middle" />
					<input type="button" value="批量删除" id="but" disabled="disabled" />
				</div>
			</div>
		</div>
		<div class="div_clear">
			<div class="center_center">
				<div class="table_content">
					<table cellspacing="0px" cellpadding="0px">
						<thead>
							<tr>
								<th>全选 <input type="checkbox" id="allChk"/></th>
								<th>名称</th>
								<th>全称</th>
								<th>库存数量</th>
								<th>价格（元）</th>	
								<th>供应商</th>
								<th>仓库</th>						
								<th style="border-right:none">操作</th>
							</tr>
						</thead>
						<tbody>	
						<form action="${pageContext.request.contextPath}/admin/kucun/dittydelete.action" method="post" name="delForm">						
							<c:forEach items="${dittyList}" var="g">
								<td><input type="checkbox" class="ch" name="ch" value="${g.id}"/></td>
								<td>${g.name}</td>
								<td>${g.fullname}</td>
								<td>${g.num}</td>
								<td>${g.price}</td>
								<td>${g.provider.providename}</td>
								<td>${g.entrepot.sn}</td>					
								<td style="border-right:none">
									<img width='16' height='16'	src="img/table/20160414160935161.gif" style="vertical-align:middle" />
									<a href="${pageContext.request.contextPath}/admin/kucun/dittyedit.action?ditty.id=${g.id}">修改</a>&nbsp;
									<img width='16' height='16' src="img/table/20160414160926864.gif" style="vertical-align:middle" />
									<a  href="javascript:void(0)" name="${g.id}" class="del">删除</a>
								</td>
							</tr>
						    </c:forEach>
						    </form>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
