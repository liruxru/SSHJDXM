<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

	<HEAD id=Head1>
		<STYLE type=text/css>
			BODY {
				FONT-SIZE: 12px;
				COLOR: #ffffff;
				FONT-FAMILY: 宋体
			}
			
			TD {
				FONT-SIZE: 12px;
				COLOR: #ffffff;
				FONT-FAMILY: 宋体
			}
		</STYLE>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
				$(function(){
					$("#adminName").blur(function(){
						var adminName=$(this).val().trim(); 
						if(adminName==""){
							$("#RequiredFieldValidator3").html("<font color='red'>登录名不能为空</font>");
						}else{
							$("#RequiredFieldValidator3").html(" ");
						}
					});
					
					$("#adminPwd").blur(function(){
						var adminName=$(this).val().trim(); 
						if(adminName==""){
							$("#RequiredFieldValidator4").html("<font color='red'>登录密码不能为空</font>");
						}else{
							$("#RequiredFieldValidator4").html(" ");
						}
					});
					
					 $("#sub").click(function(){
						var adminName=$("#adminName").val().trim(); 
						var adminPwd=$("#adminPwd").val().trim();
						var url="${pageContext.request.contextPath}/adminLogin.action";
						if(adminName!="" || adminPwd!=""){
							$.post(url,{"admins.loginname":adminName,"admins.password":adminPwd},function(res){
								 if(res==0){
									window.location.href="${pageContext.request.contextPath}/admin/home"	
								}else{
									$("#RequiredFieldValidator4").html("<font color='red'>用户名或密码错误</font>");
								} 
							},'json');
						}
					});
				});
		</script>
	</HEAD>
<BODY>
<!-- 	<FORM id=form1 name=form1 action="<%=path%>/adminLogin" method=post> -->

		<DIV id=UpdatePanel1>
			<!-- 进入页面 左右滑动 -->
			<DIV id="div1" style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
			<DIV id="div2" style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
			<SCRIPT language=JavaScript>
				var speed = 20;
				var temp = new Array();
				var clipright = document.body.clientWidth / 2, clipleft = 0
				for (i = 1; i <= 2; i++) {
					temp[i] = eval("document.all.div" + i + ".style");
					temp[i].width = document.body.clientWidth / 2;
					temp[i].height = document.body.clientHeight;
					temp[i].left = (i - 1) * parseInt(temp[i].width);
				}
				function openit() {
					clipright -= speed;
					temp[1].clip = "rect(0 " + clipright + " auto 0)";
					clipleft += speed;
					temp[2].clip = "rect(0 auto auto " + clipleft + ")";
					if (clipright <= 0)
						clearInterval(tim);
				}
				tim = setInterval("openit()", 100);
			</SCRIPT>

			<DIV>&nbsp;&nbsp; </DIV>
			<DIV>
				<TABLE cellSpacing="0" cellPadding="0" width="900" align="center" border="0">
					<TBODY>
						<TR>
							<TD style="HEIGHT: 105px">
								<IMG src="<%=path%>/img/page/login_1.gif" border="0">
							</TD>
						</TR>
						<TR>
							<TD background="<%=path%>/img/page/login_2.jpg" height="300">
								<TABLE height="300" cellPadding="0" width="900" border="0">
									<TBODY>
										<TR>
											<TD colSpan="2" height="35"></TD>
										</TR>
										<TR>
											<TD width="360" ></TD>
											<TD>
												<TABLE cellSpacing="0" cellPadding="2" border="0" >
													<TBODY>
														<TR>
															<TD style="HEIGHT: 28px" width="80">登 录 名：</TD>
															<TD style="HEIGHT: 28px" width="150">
																<INPUT id="adminName" style="WIDTH: 130px" placeholder="请输入登录名" value="${admin.loginname}" name="txtName">
															</TD>
															<TD style="HEIGHT: 28px" width="370">
																<SPAN id="RequiredFieldValidator3" ></SPAN>
															</TD>
														</TR>
														<TR>
															<TD style="HEIGHT: 28px">登录密码：</TD>
															<TD style="HEIGHT: 28px">
																<INPUT id="adminPwd" style="WIDTH: 130px" placeholder="请输入登录密码" type="password" name="txtPwd">
															</TD>
															<TD style="HEIGHT: 28px">
																<SPAN id="RequiredFieldValidator4" ></SPAN>
															</TD>
														</TR>
														<TR>
															<TD style="HEIGHT: 18px"></TD>
															<TD style="HEIGHT: 18px"></TD>
															<TD style="HEIGHT: 18px"></TD>
														</TR>
														<TR>
															<TD></TD>
															<TD>
																<INPUT id="sub" type="image" src="<%=path%>/img/page/login_button.gif" name="btn" >
															</TD>
														</TR>
													</TBODY>
												</TABLE>
											</TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>
						<TR>
							<TD><IMG src="<%=path%>/img/page/login_3.jpg"  border=0></TD>
						</TR>
					</TBODY>
				</TABLE>
			</DIV>
		</DIV>
<!-- 	</FORM> -->
</BODY>
</HTML>
