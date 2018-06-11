$(function(){
	/* 	$("#demo").dateRangePicker({
			autoClose: true,
			singleDate : true,
			showShortcuts: false,
			singleMonth: true
		});
 */		
 		$("#div_message").hide();//初始 消失
 		$("#div_addsuccess").hide();
 		
 		$("#but").click(function(){
 			var id1=$("#u1IdCard").val().trim();
			var lidiandate=$("#demo1").val().trim();	
			var yajin=$("#yajin").val();
 			if(id1=="" || lidiandate=="" || yajin==""){
 				$("#div_message").show();
				setTimeout(hide,1500);//1.5秒后消失
 				return false;
 			}else{
 				$(this).attr('type','submit'); 
 				$("#div_addsuccess").show();
				setTimeout(hide,1500);//1.5秒后消失
 			}
 		});
 		function hide(){
			$("#div_message").animate({opacity: "hide"}, "slideUp");
			$("#div_addsuccess").animate({opacity: "hide"}, "slideUp");
		}
 		$("#demo1").focus(function(){
 			$("#day").val("-");
			$("#dollar").val("-");
			$("#finalDollar").val("-");
 		})
		$("#demo1").blur(function(){
			var date= new Date(Date.parse($("#demo1").val().replace(/-/g,"/")));
			var d=date.getTime();
			var d1=new Date().getTime();
			var day=Math.floor(((d-d1)/24/3600/1000))+1;
			if(day==0){
				day=day+1;
			}
			var price=$("#price").val();
			$("#day").val(day);
			$("#dollar").val(Math.floor(day*price));
			$("#finalDollar").val(Math.floor(day*price));
		})
		/*身份证失焦检测用户是否为会员*/
		$(".searchBtn1").blur(function(){
			var min=1;
			var nan1=$("#nan1");
			var nv1=$("#nv1");
			var xxx1=$("#xxx1");
			var dollar=$("#dollar").val()*1;
			var id1 = $("#u1IdCard").val();
			var userFirName=$("#userFirName");
			var userphone1=$("#userphone1");
			var vipType1=$("#vipType1");
			var vipDiscount1=$("#vipDiscount1");
			var vipDiscount2=$("#vipDiscount2").val();
			var url="/SSHJDXM/admin/qiantai/searchUser1";
			$.post(url,{"userFir.idcard":id1},function(res){
				if(!$.isEmptyObject(res)){
					userFirName.val(res.usersputong.name);
					vipType1.val(res.usersvip.vipname);
					vipDiscount1.val(res.usersvip.roomdiscount);
					userphone1.val(res.phone);
					if(res.usersputong.sex.id==1){
						nan1.prop("checked",true);
					}else{
						nv1.prop("checked",true);
					}
					xxx1.val(res.id);
					min=res.usersvip.roomdiscount;
					if(min<=vipDiscount2){
						$("#finalDollar").val(Math.floor(min*dollar));
					}else{
						$("#finalDollar").val(Math.floor(vipDiscount2*dollar));
					}
				}else{
					userFirName.val("");
					vipType1.val("");
					vipDiscount1.val(1);
					userphone1.val("");
					$("#finalDollar").val(Math.floor(min*dollar));
				}
				},'json');
			})
			
		$(".searchBtn2").blur(function(){
			var min=1;
			var nan2=$("#nan2");
			var nv2=$("#nv2");
			var xxx2=$("#xxx2");
			var dollar=$("#dollar").val()*1;
			var id2 = $("#u2IdCard").val();
			var userTwoName=$("#userTwoName");
			var userphone2=$("#userphone2");
			var vipType2=$("#vipType2");
			var vipDiscount2=$("#vipDiscount2");
			var vipDiscount1=$("#vipDiscount1").val();
			var url="${pageContext.request.contextPath}/admin/qiantai/searchUser2";
			$.post(url,{"userTwo.idcard":id2},function(res){
				if(!$.isEmptyObject(res)){
					userTwoName.val(res.usersputong.name);
					vipType2.val(res.usersvip.vipname);
					vipDiscount2.val(res.usersvip.roomdiscount);
					userphone2.val(res.phone);
						
					if(res.usersputong.sex.id==1){
							nan2.prop("checked",true);
						}else{
							nv2.prop("checked",true);
						}
						xxx2.val(res.id);
						min=res.usersvip.roomdiscount;
						if(min<=vipDiscount1){
							$("#finalDollar").val(Math.floor(min*dollar));
						}else{
							$("#finalDollar").val(Math.floor(vipDiscount1*dollar));
						}
					}else{
						userTwoName.val("");
						vipType2.val("");
						vipDiscount2.val(1);
						userphone2.val("");
						$("#finalDollar").val(Math.floor(min*dollar));
					}
			},'json')
		})
		
		/* $(".searchPhoneBtn").click(function(){
			var phone=$("#searchPhone").val();
			var obj1= $("#u1IdCard").parent().parent().next().children();
			var dollar=$("#dollar").text()*1;
			var url="/SSHJDXM/admin/qiantai/searchUserByPhone";
			var u1=	$("#u1IdCard");
			var u1name=$("#userFirName");
			var nan=$("#nan");
			var nv=$("#nv");
			$.post(url,{"phone":phone},function(res){
				if(!$.isEmptyObject(res)){
					obj1.html("");
					obj1.append("会员登录名："+res.loginname+"会员类型："+res.usersvip.vipname+"会员折扣："+res.usersvip.roomdiscount);
					obj1.append("<input type='hidden' value='"+res.id+"' name='useraId'/>");
					alert(res.usersputong.idcard)
					u1.val(res.usersputong.idcard);
					u1name.val(res.usersputong.name);
					if(res.usersputong.sex.id==1){
						nan.prop("checked",true);
					}else{
						nv.prop("checked",true);
					}
					var price=res.usersvip.roomdiscount*dollar;
					$("#finalDollar").val(price);
				}else{
					obj1.html("");
				}
			},'json')
		}) */
	})
	
	
	
	