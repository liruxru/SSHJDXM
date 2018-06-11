 $(function() {
		    $("#div_adderr").hide();//初始 消失
		    $("#div_dianhuachaxun").hide();//初始 消失
		    function hide(){
				$("#div_adderr").animate({opacity: "hide"}, "slideUp");
				$("#div_dianhuachaxun").animate({opacity: "hide"}, "slideUp");
			}
		    //网页第一次加载时这个数组存储所有房间类型的名字，通过$("#"+arr[i])可以获取这个对象的input元素，在通过attr("name")获取这个状态的房间的数量
		    var arr=new Array("","noMakeRooms","nullRooms","dirtyRooms","propleRooms","peopleDirtyRooms","yudingRooms","repairRooms");
			var arrnum=new Array("",$("#"+arr[1]).attr("name"),$("#"+arr[2]).attr("name"),$("#"+arr[3]).attr("name"),
			$("#"+arr[4]).attr("name"),$("#"+arr[5]).attr("name"),$("#"+arr[6]).attr("name"),$("#"+arr[7]).attr("name"));		       
			//阻止浏览器默认右键点击事件
	        document.oncontextmenu = function() {
	            return false;
	        }
	        //某元素组织右键点击事件
	        $(".roomssss").on("contextmenu", function(){
	            return false;
	        })
	        $(".roomssss").on("mousedown",(function(e) {	         
	      	//存储这个div对象,这个对象触发事件以后，修改他的颜色
	      	var obj=$(this);
	      	//div.attr("name") 获取存储房间id
	      	var roomsid=$(this).attr("name");
	      	//这个div里有一个隐藏的input  通过$("#status"+roomsid)拿到这个元素,同时.val() 获取他的状态id <input type="hidden" value="${mko.roomstatus.id}" id="status${mko.id}" />	
	        var objStau=$("#status"+roomsid);
	        var status=$("#status"+roomsid).val();
	        //数量需要减少的input对象  通过$("#"+arr[status])可以拿到存储被修改房间状态的的input对象，主页最底下那一栏
	        var objjian=$("#"+arr[status]);
	        /* 1.不可用2.空3.脏4.入住5.入住脏6.预定7.维修 */
	        //数量需要减少的input对象
// 		    alert("当前状态代码id："+status); 
	        //获取维修数量那个input
	        var repairRooms	=$("#repairRooms");
	        //获取空闲那个input
	        var nullRooms=$("#nullRooms");
	          //获取不可用那个input
	        var noMakeRooms=$("#noMakeRooms");
	          //获取主人脏那个input
	        var peopleDirtyRooms=$("#peopleDirtyRooms");

            //右键为3
	        if (3 == e.which) {
				var opertionn = {  
	                name: "",  
	                offsetX: 50,  
	                offsetY: -20,  
	                textLimit: 10,  
	                beforeShow: $.noop,  
	                afterShow: $.noop  
	            };  
				  var imageMenuData = [
				  	[{  
				        text: "开房",  
				        func: function () {  
				        	if(status==2){
								window.location.href="/SSHJDXM/admin/qiantai/getRoomById?roomId="+roomsid;
				        	}else{
				        		$("#div_adderr").show();
								setTimeout(hide,2000);//1.5秒后消失
				 				return false;
				        	}
				        }  
				    }],  
				    [{  
				        text: "退房",  
				        func: function () {  
							window.location.href="/SSHJDXM/admin/qiantai/userOut?roomId="+roomsid;
				        }  
				    }],   
				    [{  
				        text: "修改为空闲",  
				        func: function () { 
				        var url="/SSHJDXM/admin/qiantai/alterRoomStatus"; 
				           $.post(url,{"room.id":roomsid,"room.roomstatus.id":2},function(res){
				           		//修改为空闲成功
				           		if(res==0){
					           		//改颜色
					           		obj.css("background-color","C0C0C0")
					           		//修改存储空闲房间数量的数组值+1
					           		arrnum[2]=arrnum[2]*1+1;
					           		//修改存储空闲房间数量的input的value值
					           		nullRooms.val("空闲房间:"+arrnum[2]);
					           		//数量需要减少的input对象,拿到他的value字符串
					           		var chuan=objjian.val();			           		
					           		chuan=chuan.substring(0, chuan.indexOf(":")+1);
					           		arrnum[status]=arrnum[status]-1;
					           		objjian.val(chuan+(arrnum[status]));
					           		objStau.val(2);
					           		$.smartMenu.remove();
				           		}
				           },'json')
				        }  
				    }], 
				    [{  
				        text: "修改为维修",  
				        func: function () { 
					        var description=prompt("输入描述信息");
					        if(description!=null && description!=""){
						        var path="/SSHJDXM/admin/qiantai/alterRoomStatus";  
								 $.ajax({ 
									url: path, 
									data:{"room.id":roomsid,"room.roomstatus.id":7,"repair.description":description},
							        type: "post", 				
							       	cache:false,					
							        async:false, 
							        dataType: "json", 
							        success: function(res){ 
										//修改为维修成功
						           		if(res==0){
							           		//改颜色
							           		obj.css("background-color","CACA00")
							           		//改数量
							           		arrnum[7]=arrnum[7]*1+1;
							           		repairRooms.val("维修房间:"+arrnum[7]);
							           		var chuan=objjian.val();
							           		chuan=chuan.substring(0, chuan.indexOf(":")+1);
							           		arrnum[status]=arrnum[status]-1;
							           		objjian.val(chuan+(arrnum[status]));
							           		objStau.val(7);
							           		$.smartMenu.remove();
						           		}
							   		} 
								});
					    	 }else{
					    	 	return;
					    	 }
				         }
				    }],
				    [{  
				        text: "改为不可用",  
				        func: function () {  
							  var path="/SSHJDXM/admin/qiantai/alterRoomStatus";  
							 $.ajax({ 
								url: path, 
								data:{"room.id":roomsid,"room.roomstatus.id":1},
						        type: "post", 				
						       	cache:false,					
						        async:false, 
						        dataType: "json", 
						        success: function(res){ 
									//修改为维修成功
					           		if(res==0){
						           		//改颜色
						           		obj.css("background-color","970000")
						           		//改数量
						           		arrnum[1]=arrnum[1]*1+1;
						           		noMakeRooms.val("不可用房:"+arrnum[1]);
						           		var chuan=objjian.val();
						           		chuan=chuan.substring(0, chuan.indexOf(":")+1);
						           		arrnum[status]=arrnum[status]-1;
						           		objjian.val(chuan+(arrnum[status]));
						           		objStau.val(1);
						           		$.smartMenu.remove();
					           		}
						   		} 
							});
				        }  
				    }],  
				  	[{  
				        text: "改为住人脏",  
				        func: function () {  
 						var url="/SSHJDXM/admin/qiantai/alterRoomStatus"; 
				           $.post(url,{"room.id":roomsid,"room.roomstatus.id":5},function(res){
				           		//修改为空闲成功
				           		if(res==0){
					           		//改颜色
					           		obj.css("background-color","80BEBF")
					           		//修改存储空闲房间数量的数组值+1
					           		arrnum[5]=arrnum[5]*1+1;
					           		//修改存储空闲房间数量的input的value值
					           		peopleDirtyRooms.val("住人脏房:"+arrnum[5]);
					           		//数量需要减少的input对象,拿到他的value字符串
					           		var chuan=objjian.val();			           		
					           		chuan=chuan.substring(0, chuan.indexOf(":")+1);
					           		arrnum[status]=arrnum[status]-1;
					           		objjian.val(chuan+(arrnum[status]));
					           		objStau.val(5);
					           		$.smartMenu.remove();
				           		}
				           },'json')
				          }				     
				    }] 
			];   
// 	      		$(".xiangqing").attr("data-target","");  
// 	            oTable.$('td.td_selected').removeClass('td_selected'); 
	            if ($(e.target).hasClass('td_selected')) {  
	                $(e.target).removeClass('td_selected');  
	            } else {  
	                $(e.target).addClass('td_selected');  
	            }   
	            $(this).smartMenu(imageMenuData, opertionn);  
          	 } /*  else if(1 == e.which){   
                    $(".xiangqing").attr("data-target","#myModal");
          
              }    */
              $.smartMenu.remove();
 			}));  
 			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			$("#biaozhundanren").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByTypes.action?room.roomtypes.id=1";
			});
			$("#biaozhunshuangren").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByTypes.action?room.roomtypes.id=2";
			});
			$("#dachuangfang").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByTypes.action?room.roomtypes.id=3";
			});
			$("#haohuataofang").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByTypes.action?room.roomtypes.id=4";
			});
			$("#zhongdianfang").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByTypes.action?room.roomtypes.id=5";
			});
 			$("#yudingchaxun").bind("click",function(){
			  	var yudingphone=$("#yudingphone").val().trim();
			  	if(yudingphone==""){
			  		$("#div_dianhuachaxun").show();
					setTimeout(hide,2000);//1.5秒后消失
	 				return false;
			  	}else{
			  		
				  	window.location.href="/SSHJDXM/admin/qiantai/findReverseByPhone?phone="+yudingphone;
			  	}
			});
 			$("#allRooms").click(function(){
				window.location.href="/SSHJDXM/admin/home.action";
			});
			$("#nullRooms").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByStatues.action?room.roomstatus.id=2";
			});
			$("#dirtyRooms").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByStatues.action?room.roomstatus.id=3";
			});
			$("#propleRooms").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByStatues.action?room.roomstatus.id=4";
			});
			$("#peopleDirtyRooms").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByStatues.action?room.roomstatus.id=5";
			});
			$("#yudingRooms").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByStatues.action?room.roomstatus.id=6";
			});
			$("#repairRooms").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByStatues.action?room.roomstatus.id=7";
			});
			$("#noMakeRooms").click(function(){
				window.location.href="/SSHJDXM/admin/qiantai/findAllByStatues.action?room.roomstatus.id=1";
			});
			$("#yuding").click(function(){
				var roomId=$("#roomsid").val();	
				var status=$("#status"+roomId).val();
				if(status==2){
					window.location.href="/SSHJDXM/admin/qiantai/getRoomById?roomId="+roomId;
				}else{
					$("#div_adderr").show();
					setTimeout(hide,1500);//1.5秒后消失
	 				return false;
				}
			})
			$("#tuifang").click(function(){
				var roomId=$("#roomsid").val();				
				window.location.href="/SSHJDXM/admin/qiantai/userOut?roomId="+roomId;
			})
			function hide(){
				$("#div_adderr").animate({opacity: "hide"}, "slideUp");
			}
			///////////////////////////////////////////////////////////////////////////////////////////
			
			
			$(".xiangqing").click(function(){
				var roomsid=$(this).children().eq(0).attr("name");
				$("#roomsid").val(roomsid);
				var roomsno=$(this).children().eq(0).children().eq(0).attr("name");
				$("#roomsno").html(roomsno);
				var roomstype=$(this).children().eq(0).children().eq(2).attr("name");
				$("#roomstype").html(roomstype);
				var roomsprice=$(this).children().eq(0).children().eq(4).attr("name");
				$("#roomsprice").html(roomsprice);
				var yudingxinxi=$("#yudingxinxi");
				var url="<%=path%>/admin/qiantai/roomUserMsg.action";
				$.post(url,{"room.id":roomsid},function(res){
				
			/* 	{"roomUserMsg":{"roomslidate":{"nanos":0,"time":1514440800000,"minutes":0,"seconds":0,"hours":14,"month":11,"year":117,"timezoneOffset":-480,"day":4,"date":28},
				"roomsdays":1,"roomszhudate":{"nanos":0,"time":1514336941000,"minutes":9,"seconds":1,"hours":9,"month":11,"year":117,"timezoneOffset":-480,"day":3,"date":27},
				"roomspeoples":["liuyang","alise"],"roomsgoodsprice":0,"roomsyouhuiprice":200,"roomsyajin":300},
				"roomUserYudingList":[{"roomUserYudingName":"liuyang","roomUserYudingcreateDate":{"nanos":0,"time":1514265812000,"minutes":23,"seconds":32,"hours":13,"month":11,"timezoneOffset":-480,"year":117,"day":2,"date":26},
				"roomUserYudingendDate":{"nanos":0,"time":1514438616000,"minutes":23,"seconds":36,"hours":13,"month":11,"timezoneOffset":-480,"year":117,"day":4,"date":28},
				"roomUserYudingPhone":"18341893958"}]} */
				/* !$.isEmptyObject(res.user1) *//* !(res.roomUserMsg).isEmptyObject */
				//获取入住信息
					if(!$.isEmptyObject(res.roomUserMsg)){
						
						$("#roomspeople").html(res.roomUserMsg.roomspeoples[0]);
						if(!$.isEmptyObject(res.roomUserMsg.roomspeoples[1])){
							$("#roomspeople").html(res.roomUserMsg.roomspeoples[0]+","+res.roomUserMsg.roomspeoples[1]);	
						}
						$("#roomsyouhuiprice").html(res.roomUserMsg.roomsyouhuiprice);
						$("#roomszhudate").html(new Date(res.roomUserMsg.roomszhudate.time).format("yyyy-MM-dd"));
						$("#roomslidate").html(new Date(res.roomUserMsg.roomslidate.time).format("yyyy-MM-dd"));
						$("#roomsdays").html(res.roomUserMsg.roomsdays);
						$("#roomsgoodsprice").html(res.roomUserMsg.roomsgoodsprice);
						$("#roomssum").html(res.roomUserMsg.roomsyajin);
					}else{
						$("#roomspeople").html("");
						$("#roomsyouhuiprice").html("");
						$("#roomszhudate").html("");
						$("#roomslidate").html("");
						$("#roomsdays").html("");
						$("#roomsgoodsprice").html("");
						$("#roomssum").html("");
					}
				//获取房间预定信息
					if(!$.isEmptyObject(res.roomUserYudingList)){
						var html="<h3>房间预定信息</h3><br>";
						for(var i=0;i<res.roomUserYudingList.length;i++){
							var roomUserYuding=  res.roomUserYudingList[i];
							html+="<br>预定人姓名："+roomUserYuding.roomUserYudingName;
							html+="<br/>预定人联系方式："+roomUserYuding.roomUserYudingPhone;
							html+="<br/>预定日期："+new Date(roomUserYuding.roomUserYudingcreateDate.time).format("yyyy-MM-dd");
							html+="<br/>预退日期："+new Date(roomUserYuding.roomUserYudingendDate.time).format("yyyy-MM-dd");
							html+="<br/><button type=\"button\" name="+roomUserYuding.roomUserYudingPhone+" id=\"yudingruzhu\" class=\"btn btn-primary\">入住</button>";
							html+="<br/><hr>";
						}
						
						yudingxinxi.html(html);
						 $("#yudingruzhu").bind("click",function(){
						  	var phone=$(this).attr("name");
						  	window.location.href="/SSHJDXM/admin/qiantai/findReverseByPhone?phone="+phone;
						  });
					}else{
						yudingxinxi.html("<h3>房间预定信息</h3><br>预定人姓名：<br/>预定人联系方式：<br/>预定日期：<br/>预退日期：<br/><hr>");	
					}
				},'json');
			});
			
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			Date.prototype.format = function(fmt) {
		        var o = {
		            "M+" : this.getMonth() + 1, //月份 
		            "d+" : this.getDate(), //日 
		            "h+" : this.getHours(), //小时 
		            "m+" : this.getMinutes(), //分 
		            "s+" : this.getSeconds(), //秒 
		            "q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
		            "S" : this.getMilliseconds() //毫秒 
		        };
		        if (/(y+)/.test(fmt))
		            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		        for ( var k in o)
		            if (new RegExp("(" + k + ")").test(fmt))
		                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]): (("00" + o[k]).substr(("" + o[k]).length)));
		        return fmt;
		    } 
			
			
			
			
			
			
			
			
			
	 })