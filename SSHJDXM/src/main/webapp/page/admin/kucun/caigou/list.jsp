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
         $("#entrepot").click(function(){
               var id  =  $("#entrepot").val();
               var obj =  $("#tbody");
               $(".div_clear").css({"display": "block"});
               url="${pageContext.request.contextPath}/admin/caigou/dittyupdate.action";
               $.post(url,{"entrepotId":id},
                     function(map){
                    	 var html="";
                    	 var ls=map[0].dittyList;
                    	 //alert(ls.length)
                    	 for(var i=0;i<ls.length;i++){
                    	  	html+="<tr><td>"+(i+1)+"</td><td>"+ls[i].name+"</td>";
                    	  	html+="<td>"+ls[i].fullname+"</td>";
                    	  	html+="<td>"+ls[i].num+"</td>";
							html+="<td>"+ls[i].price+"</td>";	
							html+="<td>"+ls[i].provider.providename+"</td></tr>";	
                    	 }
                    	 var gs=map[0].goodsList;
                    	 //alert(ls.length)
                    	 for(var i=0;i<gs.length;i++){
                    	  	html+="<tr><td>"+(i+1)+"</td><td>"+gs[i].name+"</td>";
                    	  	html+="<td>"+gs[i].fullname+"</td>";
                    	  	html+="<td>"+gs[i].num+"</td>";
							html+="<td>"+gs[i].price+"</td>";	
							html+="<td>"+gs[i].provider.providename+"</td></tr>";	
                    	 }                    
                    	 obj.html(html);		
                     },'json');         
         
         })         
      
      })      
    </script>

  </head>
  
  <body>
   <label for="name">存放仓库:</label>
	 <select class="form-control" id="entrepot">
		<option value="0">请选择仓库</option>
		<c:forEach items="${entrepotList}" var="c">
			<option value="${c.id}">${c.sn}</option>
		</c:forEach>
	</select>
    <a href="${pageContext.request.contextPath}/admin/caigou/dittyadd"><button type="button" class="btn btn-primary">申请采购</button></a>
	<div>
        <h4>库存不足商品:</h4>
    </div>
	<div class="div_clear" style="display:block;height: 260px">
			<div class="center_center" style=" height: 240px">
				<div class="table_content" style=" height: 240px">
					<table cellspacing="0px" cellpadding="0px">
						<thead>
							<tr>
								<!-- <th>No</th> -->
								<th>名称</th>
								<th>全称</th>							
								<th>库存</th>
								<th>成本价</th>								
								<th>供货商</th>
								<th>存放仓库</th>						
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${goodsList}" var="g" varStatus="v">
							<tr>
								<%-- <td>${v.count}</td> --%>
								<td>${g.name}</td>
								<td>${g.fullname}</td>
								<td>${g.num}</td>
								<td>${g.price}</td>
								<td>${g.provider.providename}</td>
								<td>${g.entrepot.sn}</td>
							</tr>
						    </c:forEach>
						    <c:forEach items="${dittyList}" var="d" varStatus="p">
							<tr>
								<%-- <td>${p.count}</td> --%>
								<td>${d.name}</td>
								<td>${d.fullname}</td>
								<td>${d.num}</td>
								<td>${d.price}</td>
								<td>${d.provider.providename}</td>
								<td>${d.entrepot.sn}</td>
							</tr>
						    </c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	
	<div class="div_clear" style="display: none;z-index: 999">
			<div class="center_center">
				<div class="table_content">
					<table cellspacing="0px" cellpadding="0px">
						<thead>
							<tr>
								<th>No</th>
								<th>名称</th>
								<th>全称</th>							
								<th>库存</th>
								<th>成本价</th>								
								<th>供货商</th>						
							</tr>
						</thead>
						<tbody id="tbody">
						</tbody>
					</table>
				</div>
			</div>
		</div>
  </body>
</html>
