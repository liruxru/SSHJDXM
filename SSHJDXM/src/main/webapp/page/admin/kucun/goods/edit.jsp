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
    
    <title>商品修改页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/goods/edit.css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
	function doSave(){	
        /* var reg = new RegExp("^\+?[1-9][0-9]*$");         
        var salePriceReg = new RegExp("^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$ ");  			 */
		if (document.getElementById("goodName").value=="") {
			alert("商品名称为空，无法提交！");
		}else if(document.getElementById("fullName").value.length<4){
			alert("全称太短或为空，无法提交！");
		}
		/* else if(reg.test(document.getElementById("num").value)){
			 alert("请输入正确的库存数量")
		}
		else if(salePriceReg.test(document.getElementById("price").value)){
			alert("请输入正确的成本价格")
		}
		else if(salePriceReg.test(document.getElementById("salePrice").value)){
			alert("请输入正确的售价")
		} */
		else if(document.getElementById("entrepot").value==0){
			alert("请选择存放的仓库")
		}
		else if(document.getElementById("type").value==0){
			alert("请选择商品类型")
		}
		else if(document.getElementById("provider").value==0){
			alert("请选择供货商")
		}
		else if(document.getElementById("statues").value==-1){
			alert("请选择是否上架")
		}
		/* else if(document.getElementsByName("imgcover").value==null){		
			alert("请选择商品封面") 
		} */
		else{
			 document.getElementById("but").setAttribute("type", "submit");
		}				
	}
	
	function PreviewImage(fileObj, imgPreviewId, divPreviewId) {       
            var allowExtention = ".jpg,.bmp,.gif,.png"; //允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;
            var extention = fileObj.value.substring(fileObj.value.lastIndexOf(".") + 1).toLowerCase();
            var browserVersion = window.navigator.userAgent.toUpperCase();
            if (allowExtention.indexOf(extention) > -1) {
                if (fileObj.files) {//HTML5实现预览，兼容chrome、火狐7+等
                    if (window.FileReader) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            document.getElementById(imgPreviewId).setAttribute("src", e.target.result);
                        }
                        reader.readAsDataURL(fileObj.files[0]);
                    } else if (browserVersion.indexOf("SAFARI") > -1) {
                        alert("不支持Safari6.0以下浏览器的图片预览!");
                    }
                } else if (browserVersion.indexOf("MSIE") > -1) {  //IE浏览器
                    if (browserVersion.indexOf("MSIE 6") > -1) {//ie6
                        document.getElementById(imgPreviewId).setAttribute("src", fileObj.value);
                    } else {//ie[7-9]
                        fileObj.select();
                        if (browserVersion.indexOf("MSIE 9") > -1)
                            fileObj.blur(); //不加上document.selection.createRange().text在ie9会拒绝访问
                        var newPreview = document.getElementById(divPreviewId + "New");
                        if (newPreview == null) {
                            newPreview = document.createElement("div");
                            newPreview.setAttribute("id", divPreviewId + "New");
                            newPreview.style.width = document.getElementById(imgPreviewId).width + "px";
                            newPreview.style.height = document.getElementById(imgPreviewId).height + "px";
                            newPreview.style.border = "solid 1px #d2e2e2";
                        }
                        newPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";
                        var tempDivPreview = document.getElementById(divPreviewId);
                        tempDivPreview.parentNode.insertBefore(newPreview, tempDivPreview);
                        tempDivPreview.style.display = "none";
                    }
                } else if (browserVersion.indexOf("FIREFOX") > -1) {//firefox
                    var firefoxVersion = parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);
                    if (firefoxVersion < 7) {//firefox7以下版本
                        document.getElementById(imgPreviewId).setAttribute("src", fileObj.files[0].getAsDataURL());
                    } else {//firefox7.0+                    
                        document.getElementById(imgPreviewId).setAttribute("src", window.URL.createObjectURL(fileObj.files[0]));
                    }
                } else {
                    document.getElementById(imgPreviewId).setAttribute("src", fileObj.value);
                }
            } else {
                alert("仅支持" + allowExtention + "为后缀名的文件!");
                fileObj.value = ""; //清空选中文件
                if (browserVersion.indexOf("MSIE") > -1) {
                    fileObj.select();
                    document.selection.clear();
                }
                fileObj.outerHTML = fileObj.outerHTML;
            }
            return fileObj.value;    //返回路径
        }	
	</script>

  </head>
  
  <body>
   <div id="all">
		<a href="${pageContext.request.contextPath}/admin/home">返回主页</a>	   
	    <div id="bt">修改商品页</div>
	    <span style="color:red">${fileError}</span>
		<form action="${pageContext.request.contextPath}/admin/kucun/goodsadd.action" method="post" enctype="multipart/form-data">
			<div id="tables">
				<div class="input-box">
					<label>商品名称:</label> 
					<input type="text" id="goodName" name="goods.name" value="${good.name}" placeholder="请输入商品的名称"/>
				</div>
				<div class="input-box">
					<label>商品全称：</label> 
					<input type="text"  id="fullName" name="goods.fullname" value="${good.fullname}" placeholder="请输入商品的全称"/>
				</div>
				<div class="input-box">
					<label>库存数量：</label>
					 <input type="text"  name="goods.num" id="num" value="${good.num}" placeholder="请输入库存数量"/>
				</div>
				<div class="input-box">
					<label>成本价：</label>
					 <input type="text"  name="goods.price" id="price" value="${good.price}" placeholder="请输入商品的成本价格"/>
				</div>
				<div class="input-box">
					<label>售价：</label>
					 <input type="text"  name="goods.salePrice" id="salePrice" value="${good.salePrice}" placeholder="请输入商品的销售价格"/>
				</div>
				<div class="input-box">
					<div class="form-group">
						<label for="name">存放仓库:</label>
						 <select class="form-control" id="entrepot" name="goods.entrepot.id">
							<option value="0">请选择类型</option>
							<c:forEach items="${entrepotList}" var="c">
								<option value="${c.id}"<c:if test="${good.entrepot.id eq c.id}">selected</c:if>>
								${c.sn}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="input-box">
					<div class="form-group">
						<label for="name">商品类型:</label>
						 <select class="form-control" id="type" name="goods.goodstypes.id">
							<option value="0">请选择类型</option>
							<c:forEach items="${typeList}" var="c">
								<option value="${c.id}"<c:if test="${good.goodstypes.id eq c.id}">selected</c:if>>
								${c.typename}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="input-box">
					<div class="form-group">
						<label for="name">供货商:</label>
						 <select class="form-control" id="provider" name="goods.provider.id">
							<option value="0">请选择类型</option>
							<c:forEach items="${providerList}" var="c">
								<option value="${c.id}"<c:if test="${good.provider.id eq c.id}">selected</c:if>>
								${c.providename}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="input-box">
					<div class="form-group">
						<label for="name">是否上架:</label>
						 <select class="form-control" id="statues" name="goods.statues">
							<option value="-1">请选择是否上架</option>
							<option value="0" <c:if test="${good.statues eq 0}">selected</c:if>>
								上架</option>
							<option value="1" <c:if test="${good.statues eq 1}">selected</c:if>>
								下架</option>
						</select>
					</div>
				</div>
				<div id="imgcover">
					<div class="form-group">
						<label for="name">商品封面:</label>
						 <input type="file"	name="imgcover" onchange="PreviewImage(this,'myimg','showImage')"/>
						<img id="myimg" src="${pageContext.request.contextPath}/upload/goods/${good.imgcover}" width="50px" height="40px"/>
					</div>
				</div>
							
				<div class="input-box" id="iii">
					<div class="form-group" id="imgs">
						<label for="name">商品附图:</label>
						
						<c:if test="${empty good.goodsimgs }">
						  <input type="file" name="imgs"  onchange="PreviewImage(this,'img1','showImage')" style="width:60px; height: 30px" />
						   <img id="img1"  width="30px" height="30px"/>
						  <input type="file" name="imgs"  onchange="PreviewImage(this,'img2','showImage')"style="width:60px; height: 30px" />
						   <img id="img2"  width="30px" height="30px"/>
						  <input type="file" name="imgs"  onchange="PreviewImage(this,'img3','showImage')"style="width:60px; height: 30px" />
						   <img id="img3"  width="30px" height="30px"/>
						</c:if>
						<c:if test="${not empty good.goodsimgs}">						
						  <c:forEach items="${good.goodsimgs}" var="c" varStatus="v">
						    <input type="file" name="imgs" value="${c.id}" onchange="PreviewImage(this,'img${v.count}','showImage')"  style="width:70px; height: 30px"/>
						    <img id="img${v.count}" src="${pageContext.request.contextPath}/upload/goods/${c.goodsimg}" width="30px" height="30px"/>
						    <input type="hidden" name="createdate" value="${c.createdate}" />
						  </c:forEach>
						</c:if>
					</div>
				</div>
				<input type="hidden" name="goods.id" value="${good.id}">
				<input type="button"  onclick="doSave()" value="提交" id="but" />
			</div>
        </form>
        </div>
  </body>
  </body>
</html>
