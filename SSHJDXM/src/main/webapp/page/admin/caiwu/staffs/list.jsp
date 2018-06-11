<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
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

<title>后台管理——首页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/index.css" />
<script src="js/jquery.min.js"></script>
<!-- 动态菜单JS -->
<script type="text/javascript" src="js/menu.js"></script>
</head>

<body>
	<div class="container">
		<%-- <div class="cont-top">
			<div class="companyname">老相好</div>
			<div class="cont-top-middle">			
			<c:if test="${empty adminsInfo}">
				<a
					href="${pageContext.request.contextPath}/admin/goToAdminLoginServlet">登录</a>
			</c:if>
			<c:if test="${not empty adminsInfo}">
				<a href="">欢迎"${adminsInfo.getAdminLoginname()}</a>
				<a href="${pageContext.request.contextPath}/admin/adminLoginOutServlet">安全退出</a>		
			</c:if>				
			</div>
		</div> --%>
		<div class="left-menu" style="height:949px;">
			<div class="menu-list">
				<ul>
					<li class="menu-list-01"><a href="javascript:void(0)">
							<p class="fumenu">前台营业</p> 
					</a></li>

					<li class="menu-list-02">
						<p class="fumenu">商品管理</p>
						 <img class="xiala" src="img/adminManagement/xiala.png" />
						<div class="list-p">
							<a href="${pageContext.request.contextPath}/admin/listGoodSrevlet">
								<p class="zcd" id="zcd5">查看全部商品</p>
							</a>
						    <a	href="${pageContext.request.contextPath}/admin/goToAddGoodViewServlet"><p
									class="zcd" id="zcd6">添加商品</p>
							</a>
						</div>
					</li>

					<li class="menu-list-01">
						<p class="fumenu">管理员管理</p>
						<img class="xiala" src="img/adminManagement/xiala.png" />
						<div class="list-p">
							<a href="${pageContext.request.contextPath}/admin/listAdminSrevlet"><p class="zcd" id="zcd8">查看全部管理员</p></a>
							<a href="${pageContext.request.contextPath}/admin/goToAddAdminViewServlet"><p class="zcd" id="zcd9">增加管理员</p></a>
						</div>
					</li>

					<li class="menu-list-02">
						<p class="fumenu">用户管理</p> 
						<img class="xiala" src="img/adminManagement/xiala.png" />
						<div class="list-p">
							<a  href="${pageContext.request.contextPath}/admin/listUserServlet"><p class="zcd" id="zcd15">查看全部用户</p></a>
						</div>
					</li>

					<li class="menu-list-01">
						<p class="fumenu">店铺管理</p> <img class="xiala"
						src="img/adminManagement/xiala.png" />
						<div class="list-p">
						<a href="${pageContext.request.contextPath}/admin/listStoreServlet"><p class="zcd" id="zcd17">查看店铺信息</p></a>
						</div>
					</li>

					<li class="menu-list-02">
						<p class="fumenu">订单管理</p> <img class="xiala"
						src="img/adminManagement/xiala.png" />

						<div class="list-p">
						<a href="${pageContext.request.contextPath}/admin/listOrderServlet"><p class="zcd" id="zcd22">查看全部订单</p></a>	
						</div>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="right-menu">
			<div class="main-hd">
				<div class="page-teb" style="height:887px;">
					<div class="l-tab-links">
						<ul style="left:0;">
							<li class="l-select"><a href="#" style="padding:0 25px;">首页</a>
							</li>
						</ul>
					</div>
					<div class="l-tab-content" style="height:851px;">
						<div class="tab-content-item">
							<div class="home">
								<!--成交金额-->
								<div class="space-style">
									<div class="col-xs">
										<a href="#" class="title-button bg-deep">
											<div class="carousel">
												<div class="left-img">
													<i><img src="img/adminManagement/left-img1.png"></i>
													<p>成交金额</p>
												</div>
												<div class="right-info">￥<f:formatNumber pattern="#,###.00">${adminNeedMsg.countMoney}</f:formatNumber>
												</div>
											</div>
										</a>
									</div>

									<div class="col-xs">
										<a href="#" class="title-button bg-red">
											<div class="carousel">
												<div class="left-img bg-color-red">
													<i><img src="img/adminManagement/left-img2.png"></i>
													<p>订单</p>
												</div>
												<div class="right-info">${adminNeedMsg.countNum}笔</div>
											</div>
										</a>
									</div>
									
									<div class="col-xs">
										<a href="#" class="title-button bg-orange">
											<div class="carousel">
												<div class="left-img bg-color-orange">
													<i><img src="img/adminManagement/left-img4.png"></i>
													<p>待处理</p>
												</div>
												<div class="right-info">${adminNeedMsg.handleMsg}条</div>
											</div>
										</a>
									</div>

									<div class="col-xs">
										<a href="#" class="title-button bg-yellow">
											<div class="carousel">
												<div class="left-img bg-color-yellow">
													<i><img src="img/adminManagement/left-img6.png"></i>
													<p>紧急通知</p>
												</div>
												<div class="right-info">${num}条</div>
											</div>
										</a>
									</div>
								</div>

								<div class="col-xs-6 ranking-style">
										<div class="frame">
											<div class="title-name">
												<i></i> 商品销售排行 <a href="#">+更多</a>
											</div>
											<table class="table table-list">
												<thead>
													<tr>
														<th width="50">排名</th>
														<th>商品名称</th>
														<th>商品类型</th>
														<th width="80">销售数量</th>
													</tr>
												</thead>
												<tbody>
												<c:forEach items="${listSale}" var="s" varStatus="v">
												    <tr>
														<td><em>${v.count}</em></td>
														<td>${s.goods.goodFullname}</td>
														<td><a href="#">${s.types.typeName}</a></td>
														<td>${s.saleNum}</td>
													</tr>											
												</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
       
</body>
</html>
