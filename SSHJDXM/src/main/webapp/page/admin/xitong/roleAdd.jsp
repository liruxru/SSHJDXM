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
    
    <title>My JSP 'roleAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/roleAdd.css" />
	<link rel="stylesheet" href="layui/css/layui.css" media="all">
  	<script type="text/javascript" src="layui/layui.js" ></script>
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script>
		if(${adminrole.id eq null}){
			var roleNameCode=0;
			var descriptionCode=0;
		}else{
			var roleNameCode=1;
			var descriptionCode=1;
		}
		var checkPerCode=0;
		//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
		layui.use(['form','layer','element','jquery'], function(){
		  var form = layui.form
		  ,layer=layui.layer
		  ,element=layui.element
		  ,$=layui.$
		  
		  $(function(){
		  		$("#selall1").click(function(){
		  			var selArr=$(".sel1");
		  			for(var i=0;i<selArr.length;i++){
		  				selArr.eq(i).prop("checked",$("#selall1").prop("checked"))
		  			}
		  		})
		  		$(".sel1").click(function(){
					var selArr=$(".sel1");
					for(var i=0;i<selArr.length;i++){
						if(selArr.eq(i).prop("checked")==false){
							$("#selall1").prop("checked",false);
							return;
						}else{
							$("#selall1").prop("checked",true);
							}
						}
				});
				
		  		$("#selall2").click(function(){
		  			var selArr=$(".sel2");
		  			for(var i=0;i<selArr.length;i++){
		  				selArr.eq(i).prop("checked",$("#selall2").prop("checked"))
		  			}
		  		})
		  		$(".sel2").click(function(){
					var selArr=$(".sel2");
					for(var i=0;i<selArr.length;i++){
						if(selArr.eq(i).prop("checked")==false){
							$("#selall2").prop("checked",false);
							return;
						}else{
							$("#selall2").prop("checked",true);
							}
						}
				});
		  		
		  		$("#selall3").click(function(){
		  			var selArr=$(".sel3");
		  			for(var i=0;i<selArr.length;i++){
		  				selArr.eq(i).prop("checked",$("#selall3").prop("checked"))
		  			}
		  		})
		  		$(".sel3").click(function(){
					var selArr=$(".sel3");
					for(var i=0;i<selArr.length;i++){
						if(selArr.eq(i).prop("checked")==false){
							$("#selall3").prop("checked",false);
							return;
						}else{
							$("#selall3").prop("checked",true);
							}
						}
				});
		  		
		  		$("#selall4").click(function(){
		  			var selArr=$(".sel4");
		  			for(var i=0;i<selArr.length;i++){
		  				selArr.eq(i).prop("checked",$("#selall4").prop("checked"))
		  			}
		  		})
		  		$(".sel4").click(function(){
					var selArr=$(".sel4");
					for(var i=0;i<selArr.length;i++){
						if(selArr.eq(i).prop("checked")==false){
							$("#selall4").prop("checked",false);
							return;
						}else{
							$("#selall4").prop("checked",true);
							}
						}
				});
		  		
		  		$("#selall5").click(function(){
		  			var selArr=$(".sel5");
		  			for(var i=0;i<selArr.length;i++){
		  				selArr.eq(i).prop("checked",$("#selall5").prop("checked"))
		  			}
		  		})
		  		$(".sel5").click(function(){
					var selArr=$(".sel5");
					for(var i=0;i<selArr.length;i++){
						if(selArr.eq(i).prop("checked")==false){
							$("#selall5").prop("checked",false);
							return;
						}else{
							$("#selall5").prop("checked",true);
							}
						}
				});
				$("#selall6").click(function(){
		  			var selArr=$(".sel6");
		  			for(var i=0;i<selArr.length;i++){
		  				selArr.eq(i).prop("checked",$("#selall6").prop("checked"))
		  			}
		  		})
		  		$(".sel6").click(function(){
					var selArr=$(".sel6");
					for(var i=0;i<selArr.length;i++){
						if(selArr.eq(i).prop("checked")==false){
							$("#selall6").prop("checked",false);
							return;
						}else{
							$("#selall6").prop("checked",true);
							}
						}
				});
				
				$("#roleName").blur(function(){
		  			var roleName=$(this).val().trim();
		  			if(roleName==""){
		  				roleNameCode=0;
		  				$("#checkRolename").html("<font color='red'>角色名不能为空</font>");
		  			}
		  		})
				
		  		$("#roleName").change(function(){
		  			var roleName=$(this).val().trim();
		  			var url="${pageContext.request.contextPath}/admin/adminrole/checkrolename"
		  			if(roleName==""){
		  				roleNameCode=0;
		  				$("#checkRolename").html("<font color='red'>角色名不能为空</font>");
		  			}else{
		  				$.post(url,{"roleName":roleName},function(res){
		  					alert(res)
		  					if(res==0){
		  						
		  						roleNameCode=1;
		  						$("#checkRolename").html("<font color='green'>√</font>");
		  					}else{
		  						roleNameCode=0;
		  						$("#checkRolename").html("<font color='red'>角色名已存在</font>");
		  					}
		  				},
		  				'json');
		  			}
		  		})
		  		$("#scription").blur(function(){
		  			var scription=$(this).val().trim();
		  			if(scription==""){
		  				descriptionCode=0;
		  				$("#checkScription").html("<font color='red'>角色描述不能为空</font>");
		  			}else{
		  				descriptionCode=1;
		  				$("#checkScription").html("<font color='green'>√</font>");
		  			}
		  		})
		  		
		  });
		});
		function sub(){
				//获取复选框被选中的个数
			    var obj = document.getElementsByName("perIds");
			    var check_val = [];
			    for(k in obj){
			        if(obj[k].checked)
			            check_val.push(obj[k].value);
			    }
			   if(check_val.length==0){
			   		checkPerCode=0;
			   }else{
			   		checkPerCode=1;
			   }
			   if(roleNameCode==0||descriptionCode==0||checkPerCode==0){
			   		return false;
			   }else{
			   		return true;
			   }
		}
	</script>
  </head>
  
  <body>
    	<div id="all">
		<a href="${pageContext.request.contextPath}/admin/home">返回主页</a>
	    <c:if test="${adminrole.id ne null}">
	      <div id="bt">修改角色</div>
	    </c:if>
	    <c:if test="${adminrole.id eq null}">
	       <div id="bt">添加角色</div>
	    </c:if>	

		<form action="${pageContext.request.contextPath}/admin/adminrole/adminrolesadd" onsubmit="return sub()"  method="post">
			<div id="tables">
				<div class="input-box">
					<label>角色名称:</label> 
					<input type="text"  name="adminrole.rolename" value="${adminroleDo.rolename}"  id="roleName"  placeholder="请输入角色名称"   />
					 <span id="checkRolename" ></span>
				</div>
				<div class="input-box">
					<label>角色描述：</label> 
					<input type="text"  name="adminrole.roledescription" id="scription"  value="${adminroleDo.roledescription}" placeholder="请输入角色描述"/>
					<span id="checkScription"></span>
				</div>
				<div class="input-div">
					<div class="input-div-div"> 
						<label >添加权限：</label>
					</div>
					 
					<br>
						<div class="layui-tab">
						  <ul class="layui-tab-title">
						    <li class="layui-this">系统权限</li>
						    <li>前台权限</li>
						    <li>会员权限</li>
						    <li>员工权限</li>
						    <li>仓库权限</li>
						    <li>财务权限</li>
						  </ul>
						  <div class="layui-tab-content">
						  	<!--系统权限部分-->
						    <div class="layui-tab-item layui-show">
						    		<div class="perdiv">
								    	<input type="checkbox" id="selall1">全选<br>
								    	<hr>
								    	
								    	<c:forEach items="${adminrolePerAll}" var="l">
								    	<input type="checkbox" value="${l.id}"  class="sel1" name="perIds"  <c:forEach items="${setPer}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>       >${l.pername}&nbsp;&nbsp;&nbsp;
								    	</c:forEach>
								    	<hr>
								    	<c:forEach items="${adminPerAll}" var="l">
								    	<input type="checkbox" value="${l.id}" class="sel1"  name="perIds"  <c:forEach items="${setPer}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>    >${l.pername}&nbsp;&nbsp;
								    	</c:forEach>
						    		</div>
						    </div>
						    <!--前台权限部分-->
						    <div class="layui-tab-item">
						    		<div class="perdiv" style="width:600px">
								    	<input type="checkbox" id="selall2" >全选<br>
								    	<hr>
								    	<c:forEach items="${qiantaiPerAll}" var="l">
								    	<input type="checkbox" value="${l.id}" class="sel2" name="perIds"  <c:forEach items="${setPer}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>   >${l.pername}&nbsp;&nbsp;&nbsp;
								    	</c:forEach>
						    		</div>
						    </div>
						    <!--会员权限部分-->
						    <div class="layui-tab-item">
						    		<div class="perdiv" style="padding-left: 10px;padding-bottom: 10px">
								    	<input type="checkbox" id="selall3" >全选<br>
								    	<hr>
								    	<c:forEach items="${huiyuanPerAll}" var="l">
								    	<input type="checkbox" value="${l.id}" class="sel3"  name="perIds"    <c:forEach items="${setPer}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>  >${l.pername}&nbsp;&nbsp;&nbsp;
								    	</c:forEach>
						    		</div>
						    
						    </div>
						    <!-- 员工权限部分 -->
						    <div class="layui-tab-item">
						    		<div class="perdiv" style="padding-left: 10px;padding-bottom: 10px">
								    	<input type="checkbox" id="selall4" >全选<br>
								    	<hr>
								    	<c:forEach items="${staffsPerAll}" var="l">
								    	<input type="checkbox" value="${l.id}" class="sel4" name="perIds"   <c:forEach items="${setPer}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>   >${l.pername}&nbsp;&nbsp;&nbsp;
								    	</c:forEach>
						    		</div>
						    </div>
						     <!-- 仓库权限部分 -->
						    <div class="layui-tab-item">
						    		<div class="perdiv" style="padding-left: 10px;padding-bottom: 10px">
								    	<input type="checkbox" id="selall5" >全选<br>
								    	<hr>
								    	<c:forEach items="${entrepotPerAll}" var="l">
								    	<input type="checkbox" value="${l.id}" class="sel5" name="perIds"  <c:forEach items="${setPer}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>   >${l.pername}&nbsp;&nbsp;&nbsp;
								    	</c:forEach>
								    	<hr>
								    	<c:forEach items="${providerPerAll}" var="l">
								    	<input type="checkbox" value="${l.id}" class="sel5" name="perIds"  <c:forEach items="${setPer}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>  >${l.pername}&nbsp;&nbsp;&nbsp;
								    	</c:forEach>
								    	<hr>
								    	<c:forEach items="${kucunPerAll}" var="l">
								    	<input type="checkbox" value="${l.id}" class="sel5" name="perIds"  <c:forEach items="${setPer}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>   >${l.pername}&nbsp;&nbsp;&nbsp;
								    	</c:forEach>
								    	<hr>
								    	<c:forEach items="${repairsPerAll}" var="l">
								    	<input type="checkbox" value="${l.id}" class="sel5" name="perIds"  <c:forEach items="${setPer}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>  >${l.pername}&nbsp;&nbsp;&nbsp;
								    	</c:forEach>
								    	<hr>
								    	<c:forEach items="${caigouPerAll}" var="l">
								    	<input type="checkbox" value="${l.id}" class="sel5" name="perIds"   <c:forEach items="${setPer}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>  >${l.pername}&nbsp;&nbsp;&nbsp;
								    	</c:forEach>
								    	
						    		</div>
						    
						    </div>
						    <!--财务权限部分-->
						    <div class="layui-tab-item">
						    		<div class="perdiv" style="width:530px">
								    	<input type="checkbox" id="selall6" >全选<br>
								    	<hr>
								    	<c:forEach items="${assetsPerAll}" var="l">
								    	<input type="checkbox" value="${l.id}" class="sel6" name="perIds" style="padding-bottom: 10px"  <c:forEach items="${setPer}"  var="sp" >  <c:if test="${l.id eq sp.id}"> checked="checked"</c:if></c:forEach>  >${l.pername}&nbsp;&nbsp;&nbsp;
								    	</c:forEach>
						    		</div>
						    </div>
						  </div>
						</div>
					</div>
				<input type="hidden" name="adminrole.id" value="${adminroleDo.id}">
				<input type="hidden" name="adminrole.createdate" value="${adminroleDo.createdate}">
				<input type="hidden" name="adminrole.moditfydate" value="${adminroleDo.moditfydate}">
				<input type="submit" value="提交" id="but" />
			</div>
        </form>
        </div>
    	
    	
  </body>
</html>
