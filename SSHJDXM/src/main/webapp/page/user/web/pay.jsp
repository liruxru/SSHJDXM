<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title></title>
		<style>
			* {
				margin: 0px auto;
				padding: 0px;
			}
			
			html,
			body {
				width: 1200px;
				height: 900px;
			}
			
			#container {
				position: absolute;
				width: 1024px;
				height: 768px;
				left: 88px;
			}
			
			#head {
				width: 100%;
				height: 60px;
			}
			
			#msg {
				width: 100%;
				height: 100px;
				background: #FFFFFF;
				font-size: 13px;
			}
			
			#pay {
				position: relative;
				width: 100%;
				height: 550px;
			}
			
			#pay div {}
			
			#pleasePay,
			#yourDollartoMydollar {
				float: left;
			}
			
			.user {
				float: right;
			}
			
			#self {
				margin-right: 50px;
			}
			
			#orderPrice {
				font-size: 18px;
				color: #e31613;
				margin: 0 3px;
			}
			
			.orderPrice {
				float: right;
				margin-top: 25px;
			}
			
			#detail {
				text-align: right;
			}
		
			
			#detail:hover {
				cursor: pointer;
				color: red;
			}
			
			#pleasePay {
				margin-top: 30px;
				margin-left: 10px;
			}
			
			
			
			#orderItem div {
				margin-left: 10px;
				font-size: 12px;
			}
			
			
			
			
			.divContent{
				margin-top: 20px;
			}
			.divBank {
				border: 5px solid #efeae5;
				width: 100%;
				height: 500px;
				margin-top: 20px;
				
			}
			.divBank div{
				margin-left: 20px;
			}
			
			.divText {
				font-size: 15px;
				font-weight: 900;
				margin: 20px;
			}
			
			.spanPrice {
				font-weight: 900;
				font-family: 黑体;
				margin-left: 30px;
			}
			
			.spanOid {
				font-size: 13px;
				color: #878787;
				font-family: Arial;
				padding-top: 10px;
				margin-left: 30px;
			}
			
			.price_t {
				color: #c30;
				font-weight: bold;
				padding-right: 10px;
				font-family: Arial;
				font-size: 15pt;
			}
			
			img {
				margin-right: 25px;
			}
			
			.linkNext {
				background: url(/goods/images/icon2.png) no-repeat;
				display: inline-block;
				background-position: 0 -35px;
				height: 40px;
				width: 190px;
				margin: 0;
				text-align: center;
				text-decoration: none;
				font-size: 24px;
				font-weight: 900;
				color: #ffffff;
				line-height: 40px;
				font-family: 黑体;
			}
			
			.linkNext:HOVER {
				background: url(/goods/images/icon2.png) no-repeat;
				display: inline-block;
				background-position: 0 -75px;
				height: 40px;
				width: 190px;
			}
			
			#payBtn{margin-left: 50px;margin-top: 50px;}
			#payBtn,#payBtn a{
				text-decoration: none;
				font-size: 20px;
				width: 100px;
				height: 50px;
				background: red;
				text-align: center;	
				line-height: 50px;	
				
				
			}
			#orderItem{
				display: none;
			}
			
		</style>
		<script type="text/javascript" src="${root }/js/jquery-1.8.3.min.js"></script>
		<script>
			$(function() {
				$("#detail").click(function() {
					if($("#orderItem").css("display") == "none") {
						$("#orderItem").show();
						$("#detail").children().eq(0).text("收起详情");
					} else {
						$("#orderItem ").hide();
						$("#detail").children().eq(0).text("订单详情");
					}
				})
				
				$("#payBtn").click(function(){
				
				
				var value=$("#orderId").val();
				location="${root }/account/changeOrderStatus?code=2&orderId="+value;
				
				})

			})
		</script>

	</head>
	

	<body>
		<div id="container">
			<div id="head">
				<div id="yourDollartoMydollar">
					易购收银台
				</div>
				<div class="user userOrder">
					 <a href="${root }/account/listOrder">我的订单</a>
				</div>
				<div class="user" id="self">
					${user.userName } <a href="${root }/account/userLogOutServlet">[安全退出]</a>
				</div>

			</div>
			<div id="msg">
				<div id="pleasePay">
					<span>订单提交成功，请尽快付款！订单号：${uuidsn}</span><br/>
					<span> 请您在24小时内完成支付，否则订单会被自动取消(库存紧俏订单请参见详情页时限) </span>
				</div>
				<div class="orderPrice">
					<span>应付金额<em id="orderPrice">${roomprice}</em>元</span><br/>
					<div id="detail"><span>订单详情</span></div>

				</div>

			</div>
			<div id="orderItem">
				<div>
					<div id="userInfo">
						送货地址：<c:if test="${ not empty userChooseAddr }">${userChooseAddr }</c:if>  <c:if test="${empty userChooseAddr }">${user.userAddress }</c:if>    ${user.userName } --> ${user.userPhone }
					</div>
					&nbsp;&nbsp;&nbsp;订单描述：<div id="goodName">
						<c:forEach items="${retMessage.orders.orderItemList }"  var="orderItem">
						${orderItem.goods.goodFullname } --> ${orderItem.goods.goodDescription } -->${orderItem.orderitemNum }<br/>
						
						</c:forEach>
					

					</div>
				</div>

			</div>
			<div id="pay">

				<div class="divContent">
					<span class="spanPrice">支付金额：${roomprice}</span><span class="price_t">&yen;${retMessage.orders.orderSum }</span>
					<span class="spanOid">编号：${retMessage.orders.orderSn }</span>
				</div>
				<form  action="" method="post" >
					
					<div class="divBank">
						<div class="divText">选择网上银行</div>
						<div style="margin-left: 20px;">
							<div style="margin-bottom: 20px;">
								<input id="ICBC-NET-B2C" type="radio" name="bankName"  value="ICBC-NET-B2C" checked="checked" />
								<img name="ICBC-NET-B2C" src="<%=path%>//img/bank/icbc.bmp" />

								<input id="CMBCHINA-NET-B2C" type="radio" name="bankName"  value="CMBCHINA-NET-B2C" />
								<img name="CMBCHINA-NET-B2C" src="<%=path%>//img/bank/cmb.bmp" />

								<input id="ABC-NET-B2C" type="radio" name="bankName"  value="ABC-NET-B2C" />
								<img name="ABC-NET-B2C" src="<%=path%>//img/bank/abc.bmp" />

								<input id="CCB-NET-B2C" type="radio" name="bankName"  value="CCB-NET-B2C" />
								<img name="CCB-NET-B2C" src="<%=path%>//img/bank/ccb.bmp" />
							</div>
							<div style="margin-bottom: 20px;">
								<input id="BCCB-NET-B2C" type="radio" name="bankName"  value="BCCB-NET-B2C" />
								<img name="BCCB-NET-B2C" src="<%=path%>//img/bank/bj.bmp" />

								<input id="BOCO-NET-B2C" type="radio" name="bankName"  value="BOCO-NET-B2C" />
								<img name="BOCO-NET-B2C" src="<%=path%>//img/bank/bcc.bmp" />

								<input id="CIB-NET-B2C" type="radio" name="bankName"  value="CIB-NET-B2C" />
								<img name="CIB-NET-B2C" src="<%=path%>//img/bank/cib.bmp" />

								<input id="NJCB-NET-B2C" type="radio" name="bankName"  value="NJCB-NET-B2C" />
								<img name="NJCB-NET-B2C" src="<%=path%>//img/bank/nanjing.bmp" />
							</div>
							<div style="margin-bottom: 20px;">
								<input id="CMBC-NET-B2C" type="radio" name="bankName"  value="CMBC-NET-B2C" />
								<img name="CMBC-NET-B2C" src="<%=path%>//img/bank/cmbc.bmp" />

								<input id="CEB-NET-B2C" type="radio" name="bankName"  value="CEB-NET-B2C" />
								<img name="CEB-NET-B2C" src="<%=path%>//img/bank/guangda.bmp" />

								<input id="BOC-NET-B2C" type="radio" name="bankName"  value="BOC-NET-B2C" />
								<img name="BOC-NET-B2C" src="<%=path%>//img/bank/bc.bmp" />

								<input id="PINGANBANK-NET" type="radio" name="bankName"  value="PINGANBANK-NET" />
								<img name="PINGANBANK-NET" src="<%=path%>//img/bank/pingan.bmp" />
							</div>
							<div style="margin-bottom: 20px;">
								<input id="CBHB-NET-B2C" type="radio" name="bankName"  value="CBHB-NET-B2C" />
								<img name="CBHB-NET-B2C" src="<%=path%>//img/bank/bh.bmp" />

								<input id="HKBEA-NET-B2C" type="radio" name="bankName"  value="HKBEA-NET-B2C" />
								<img name="HKBEA-NET-B2C" src="<%=path%>//img/bank/dy.bmp" />

								<input id="NBCB-NET-B2C" type="radio" name="bankName"  value="NBCB-NET-B2C" />
								<img name="NBCB-NET-B2C" src="<%=path%>//img/bank/ningbo.bmp" />

								<input id="ECITIC-NET-B2C" type="radio" name="bankName"  value="ECITIC-NET-B2C" />
								<img name="ECITIC-NET-B2C" src="<%=path%>//img/bank/zx.bmp" />
							</div>
							<div style="margin-bottom: 20px;">
								<input id="SDB-NET-B2C" type="radio" name="bankName"  value="SDB-NET-B2C" />
								<img name="SDB-NET-B2C" src="<%=path%>//img/bank/sfz.bmp" />

								<input id="GDB-NET-B2C" type="radio" name="bankName"  value="GDB-NET-B2C" />
								<img name="GDB-NET-B2C" src="<%=path%>//img/bank/gf.bmp" />

								<input id="SHB-NET-B2C" type="radio" name="bankName"  value="SHB-NET-B2C" />
								<img name="SHB-NET-B2C" src="<%=path%>//img/bank/sh.bmp" />

								<input id="SPDB-NET-B2C" type="radio" name="bankName"  value="SPDB-NET-B2C" />
								<img name="SPDB-NET-B2C" src="<%=path%>//img/bank/shpd.bmp" />
							</div>
							<div style="margin-bottom: 20px;">
								<input id="POST-NET-B2C" type="radio" name="bankName"  value="POST-NET-B2C" />
								<img name="POST-NET-B2C" src="<%=path%>//img/bank/post.bmp" />

								<input id="BJRCB-NET-B2C" type="radio" name="bankName"  value="BJRCB-NET-B2C" />
								<img name="BJRCB-NET-B2C" src="<%=path%>//img/bank/beijingnongshang.bmp" />

								<input id="HXB-NET-B2C" type="radio" name="bankName"  value="HXB-NET-B2C" />
								<img name="HXB-NET-B2C" src="<%=path%>//img/bank/hx.bmp" />

								<input id="CZ-NET-B2C" type="radio" name="bankName"  value="CZ-NET-B2C" />
								<img name="CZ-NET-B2C" src="<%=path%>//img/bank/zheshang.bmp" />
							</div>
						</div>
						<div id="payBtn"> <a href="<%=path%>/user/yuding/selectRoom.action" >去支付</a></div>
						<input type="hidden" id="orderId" name="orderId"  value="${retMessage.orders.orderId }">
					</div>

			</div>

			
	</body>

</html>