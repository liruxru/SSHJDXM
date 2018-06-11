<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	     
	     //全选的方法
	     $("#allChk").click(function(){
	       var obj = $(".ch");
	       for(var i =0;i<obj.length;i++){
	          obj.prop("checked", $("#allChk").prop("checked"));
	       }	
	       changDel();     
	     })
	     
	     $(".ch").click(function(){
	        changDel();   
	     })        
	     
	     //跳转方法
	     $("#sub").click(function(){
	        var page = $("#tiaozhuang").val();
	         var re=/^\d*$/; 
	         if(!re.test( page )||page>${page.totalPage}||page<1){
	             alert("输入有误!");
	             return;
	         }
	        goPage(page);
	     })	     
	     
	})
	
	//改变按钮的状态
	function changDel(){
	   var ButObj = document.getElementById("but");
	   var obj    = document.getElementsByName("ch");
	   var j = 0;
	   for(var i=0; i<obj.length;i++){
	       if(obj[i].checked){
	           j++;
	       }   
	   }
	   if(j>1){
	     ButObj.disabled="";
	   }else{
	     ButObj.disabled="disabled";
	   }
	
	
	}	
	//页面的方法
	function goPage(currentPage){
	    window.location.href= "${pageContext.request.contextPath}"+"/admin/kucun/goodslist?currentPage="+currentPage;	
	}
	
	function but(){
	    if(confirm("是否确定删除这些数据?")){
	        document.delForm.submit();
	        alert(${error});
	    }else{
	        return
	    }
    }	
</script>
</head>

<body>

	<div class="table_div">
		<div class="div_clear">
		  <a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
			<div class="center_top">
				<div style="float:right;padding-right:6px">
					<img width='16' height='16' src="img/table/20160414160909739.gif" style="vertical-align:middle" />
					<a href="${pageContext.request.contextPath}/admin/kucun/goodsedit.action">新增</a>&nbsp; 					
					<img width='16' height='16' src="img/table/20160414160859494.gif"	style="vertical-align:middle" />
					<input type="button" value="批量删除" id="but" onclick="but()" disabled="disabled" />
				</div>
			</div>
		</div>
		<div class="div_clear">
			<div class="center_center">
				<div class="table_content">
					<table cellspacing="0px" cellpadding="0px">
						<thead>
							<tr>
								<th>全选 <input type="checkbox" id="allChk" /></th>
								<th>No</th>
								<th>名称</th>
								<th>全称</th>							
								<th>库存</th>
								<th>成本价</th>
								<th>售价</th>
								<th>存放仓库</th>
								<th>类型</th>
								<th>供货商</th>
								<th>是否上架</th>
								<!-- <th>封面</th> -->
								<th style="border-right:none">操作</th>
							</tr>
						</thead>
						<tbody>
							<form action="${pageContext.request.contextPath}/admin/kucun/goodsdelete.action" method="post" name="delForm">
							<c:forEach items="${goodsList}" var="g" varStatus="v">
							<tr>
								<td><input type="checkbox" onclick="chi()" class="ch" name="ch" value="${g.id}" /></td>
								<td>${v.count}</td>
								<td>${g.name}</td>
								<td>${g.fullname}</td>
								<td>${g.num}</td>
								<td>${g.price}</td>
								<td>${g.salePrice}</td>
								<td>${g.entrepot.sn}</td>
								<td>${g.goodstypes.typename}</td>
								<td>${g.provider.providename}</td>
								<td><c:if test="${g.statues eq 0}">已上架</c:if><c:if test="${g.statues eq 1}"><span style="color: red">已下架</span></c:if></td>							
								<%-- <td><ul><li><div>预览</div><img src="${pageContext.request.contextPath}/upload/goods/${g.imgcover}" style="width: 50px;height: 50px"/></li></ul></td> --%>
								<td style="border-right:none">
									<img width='16' height='16'	src="img/table/20160414160935161.gif" style="vertical-align:middle" />
									<a href="${pageContext.request.contextPath}/admin/kucun/goodsedit.action?goods.id=${g.id}&currentPage=${page.currentPage}">修改</a>&nbsp;
									<c:if test="${g.statues eq 0}">
									<img width='16' height='16' src="img/table/20160414160926864.gif" style="vertical-align:middle" />
									<a  href="javascript:void(0)" name="${g.id}" class="del">删除</a>
									</c:if>
								</td>
							</tr>
						    </c:forEach>
						</form>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="div_clear">
			<div class="left_bottom"></div>
			<div class="center_bottom">
				<span>总页数:${page.totalPage}，当前页第${page.currentPage}/${page.totalPage}页，总记录数:${page.count}</span>
				
				<div style="float:right;padding-right:30px">
				<c:if test="${page.currentPage ne 1}">				
					<a href="javascript:goPage(1)"><input type="button" value="首页" /></a>
				</c:if> <c:if test="${page.currentPage ne 1}">
					<a href="javascript:goPage(${page.currentPage-1})"><input type="button" value="上页"/></a>
				</c:if> <c:if test="${page.currentPage ne page.totalPage}">
					<a href="javascript:goPage(${page.currentPage+1})"><input type="button" value="下页"/></a>
				</c:if> <c:if test="${page.currentPage ne page.totalPage}">
					<a href="javascript:goPage(${page.totalPage})"><input type="button" value="尾页"/></a>
				</c:if>					
				<input type="text" size="1" id="tiaozhuang" /> 
				<a  href="javascript:void(0)" ><input type="button" value="跳转" id="sub"/></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
