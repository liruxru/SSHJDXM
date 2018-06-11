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

<title>查看用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/userList.css">
	<link rel="stylesheet" href="<%=path%>/page/user/web/lxhlist/daterangepicker.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<!-- 	<script type="text/javascript" src="<%=path%>/page/user/web/lxhlist/jquery.min.js" ></script> -->
	<script type="text/javascript" src="<%=path%>/page/user/web/lxhlist/moment.min.js" ></script>
	<script type="text/javascript" src="<%=path%>/page/user/web/lxhlist/jquery.daterangepicker.min.js" ></script>
<script type="text/javascript">
	$(function(){

			$("#date-range103").dateRangePicker({
				showShortcuts: true,
				shortcuts :
				{
					'prev-days': [3,5,7],
					'prev': ['week','month','year'],
					'next-days':null,
					'next':null
				}
			}	)

		//全选的方法
		$("#allChk").click(function(){
			var chArr=$(".ch");
			for(var i=0;i<chArr.length;i++){
				chArr.eq(i).prop("checked",$("#allChk").prop("checked"));
			}
		})
		$(".ch").click(function(){
			var chArr=$(".ch");
			for(var i=0;i<chArr.length;i++){
				if(chArr.eq(i).prop("checked")==false){
						$("#allChk").prop("checked",false)
						return;
				}else{
					$("#allChk").prop("checked",true)
				}
			}
		})
		
		
	     //单个删除的方法
	     $(".del").click(function(){
	     	  var id  = $(this).attr("name");
	          var obj = $(this).parent().parent();
	          url="${pageContext.request.contextPath}/admin/huiyuan/userdelete";
	          if(confirm("是否删除")){
	           $.post(url,
	                 {"user.id":id},
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
	     
	     //跳转页面的方法
	     $("#sub").click(function(){
	     		var page=$("#tiaozhuang").val();
	     		if(page>${page.totalPage}||page<1){
	     				alert("输入有误")
	     				return;
	     		}
	     		goPage(page);
	     })
	     
	     //批量删除的方法
	     $("#but").click(function(){
	   			if(confirm("确定要删除这些数据吗？")){
	   				document.delForm.submit();
	   			}else{
	   				return;
	   			}
	     })
	   }) 
	
	var url="";
	function getUrl(){
		var cxloginname=document.getElementById("cxloginname").value;
		var cxusername=document.getElementById("cxusername").value;
		var vipId=document.getElementById("vipId").value;
		var cxtime=document.getElementById("date-range103").value;
		var strcxminJifen=document.getElementById("minJifen").value;
		var strcxmaxJifen=document.getElementById("maxJifen").value;
		url="${pageContext.request.contextPath}"+"/admin/huiyuan/userlist?search.cxloginname="+cxloginname+"&search.cxusername="+cxusername+"&search.vipId="+vipId+"&search.cxtime="+cxtime+"&strcxminJifen="+strcxminJifen+"&strcxmaxJifen="+strcxmaxJifen;
	}

	//分页的方法
	function goPage(currentPage){
		getUrl();
	    window.location.href= url+"&currentPage="+currentPage;	
	}
	
	function shengXuClick(){
		getUrl();
		window.location.href= url+"&search.cxshengxu='shengxu'";
	}
	
	function jiangXuClick(){
		getUrl();
		window.location.href= url+"&search.cxjiangxu='jiangxu'";
	}
	
	
		
	
</script>
</head>

<body>

	<div class="table_div">
		
		<div id="bottom_right_top">
				<form action="${pageContext.request.contextPath}/admin/huiyuan/userlist" method="post" >
					<div id="bottom_right_top_left">
						登录名称：<input id="cxloginname" name="search.cxloginname" value="${cx.cxloginname}" >
						真实名称：<input id="cxusername" name="search.cxusername" value="${cx.cxusername}" >
						vip等级：<select id="vipId" name="search.vipId" >
									<option value="0">请选择vip</option>
									<c:forEach items="${vip}" var="v">
										<option value="${v.id}" <c:if test="${v.id eq cx.vipId}">selected</c:if>   >${v.vipname}</option>
									</c:forEach>
								</select>
						注册时间：<input id="date-range103" name="search.cxtime" size="60" style="width: 170px;" value="${cx.cxtime}"/>			
						积分区间：<input id="minJifen" name="strcxminJifen"  class="xiaokuang" value="${strcxminJifen}" >
								<input id="maxJifen" name="strcxmaxJifen"  class="xiaokuang" value="${strcxmaxJifen}" >
						<input type="button" value="升序" onclick="shengXuClick()"  id="shengXu" name="search.cxshengxu"/>
						<input type="button" value="降序" onclick="jiangXuClick()"  id="jiangXu" name="search.cxjiangxu" />
						
					</div>
					<div id="bottom_right_top_right">
						<input id="cxsub" type="submit" value="查询">  
					</div>
				</form>
			</div>
	
	
	
		<div class="div_clear">
		  <a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
			<div class="center_top">
				<div style="float:right;padding-right:6px">
					<img width='16' height='16' src="img/table/20160414160909739.gif" style="vertical-align:middle" />
					<a href="${pageContext.request.contextPath}/admin/huiyuan/useredit">新增</a>&nbsp; 					
					<img width='16' height='16' src="img/table/20160414160859494.gif"	style="vertical-align:middle" />
					<input type="button" value="批量删除" id="but" />
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
								<th>用户名</th>
								<th>登录名称</th>							
								<th>年龄</th>
								<th>电话</th>
								<th>邮箱</th>
								<th>地址</th>
								<th>Vip</th>
								<th>积分</th>
								<th>住宿次数</th>
								<th>注册时间</th>
								<th>修改时间</th>
								<th style="border-right:none">操作</th>
							</tr>
						</thead>
						<tbody>
						<form action="${pageContext.request.contextPath}/admin/huiyuan/userdelete" method="post" name="delForm">
							<c:forEach items="${userList}" var="l" varStatus="v">
							<tr>
								<td><input type="checkbox"  class="ch" name="ch" value="${l.id}" /></td>
								<td>${v.count}</td>
								<td>${l.username}</td>
								<td>${l.loginname}</td>
								<td>${l.age}</td>
								<td>${l.phone}</td>
								<td>${l.email}</td>
								<td>${l.address}</td>
								<td>${l.usersvip.vipname}</td>
								<td>${l.jifen}</td>
								<td>${l.num}</td>
								<td>${l.createdate}</td>
								<td>${l.moditfydate}</td>
								<td style="border-right:none">
									<img width='16' height='16'	src="img/table/20160414160935161.gif" style="vertical-align:middle" />
									<a href="${pageContext.request.contextPath}/admin/huiyuan/useredit?user.id=${l.id}&currentPage=${page.currentPage}">修改</a>&nbsp;
									<img width='16' height='16' src="img/table/20160414160926864.gif" style="vertical-align:middle" />
									<a  href="javascript:void(0)" name="${l.id}" class="del">注销</a>
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
