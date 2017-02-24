<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
  <%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
	<script type="text/javascript" src="${ctx}/static/js/citySelect/area.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/citySelect/location.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/citySelect/select2.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/citySelect/select2_locale_zh-CN.js"></script>
	<link rel="stylesheet" href="${ctx}/static/js/citySelect/select2.css" />
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <!-- tag input-->
	<link rel="stylesheet" href="${ctx}/static/css/jquery.tagsinput.css" />
    <script src="${ctx}/static/js/jquery.tagsinput.js"></script>
   	  <script>
   	  	$().ready(function(){
				$.ajaxSetup({ cache: false }); //解决ajax缓存结果问题 
				$("#myform").validate({
					onkeyup: false
				});
				var msg = '${msg}';
				if(msg=='true'){
					showMsg(true,'恭喜！修改成功');
				}else if(msg=='false'){
					showMsg(false,'抱歉！修改失败');
				}
		});
   	  </script>
 <script src="${ctx}/static/js/plupload/plupload.full.min.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			showWeek : true,
			changeMonth : true,
			changeYear : true,
			showOtherMonths : true,
			selectOtherMonths : true,
			firstDay : 1
		});
	});
</script>
<style type="text/css">
.uploadifive-button {
    background: #9f9f9f none repeat scroll 0 0;
    color: #fff;
    height: 40px;
    line-height: 40px;
    text-align: center;
    width: 120px;
    cursor:pointer;
}
.uploadifive-button:hover{
	background: #6bc30d;
}
/*进度条*/
.upprogress{
	background: rgba(0, 0, 0, 0.25) none repeat scroll 0 0;
    /*border-radius: 6px;*/
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.25) inset, 0 1px rgba(255, 255, 255, 0.08);
    float: left;
    margin-top: 68px;
    padding: 2px;
    width: 172px;
    display: none;
}
.progress-bar::before, .progress-bar::after {
    content: "";
    left: 0;
    position: absolute;
    right: 0;
    top: 0;
}
.progress-bar::after {
    background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0.3), rgba(255, 255, 255, 0.05));
    border-radius: 4px;
    bottom: 45%;
    z-index: 2;
}
.progress-bar::before, .progress-bar::after {
    content: "";
    left: 0;
    position: absolute;
    right: 0;
    top: 0;
}
.progress-bar {
    background-color: #86e01e;
    width: 1%;
}
.progress-bar {
    /*border-radius: 4px;*/
    box-shadow: 0 0 1px 1px rgba(0, 0, 0, 0.25), 0 1px rgba(255, 255, 255, 0.1) inset;
    height: 3px;
    position: relative;
    transition-delay: 0s;
    transition-duration: 0.4s;
    transition-property: width, background-color;
    transition-timing-function: linear;
}

/**/
 .upload_add .upload_img{
	border: 2px dashed #dedede;
    height:280px;
    width: 280px;
    float: left;
    text-align: center;
    margin-right: 10px;
    margin-bottom: 10px;
    position: relative;
}
 .upload_add .active{
	border: 1px solid #dedede;
	 height:280px;
	 padding: 5px;
	 width: 280px;
	 position: relative;
}
.upload_add .active:hover .deleted{
	 display: block;
}
.upload_add .active:hover .deleted:hover{
	 box-shadow: 0 0 0 2px rgba(255, 255,255, 0.5);
}

.upload_add  .upload_img .deleted{
	background: rgba(0, 0, 0, 0.3) none repeat scroll 0 0;
    border-radius: 2px;
    box-shadow: 0 0 0 2px rgba(255, 255, 255, 0.2);
    color: white;
    cursor: pointer;
    font-size: 14px;
    height: 30px;
    line-height: 30px;
    padding: 0 12px;
    position: absolute;
    z-index: 100;
    right: 10px;
    top: 10px;
    display: none;
}
</style>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav">照片管理 ＞ 照片编辑 </div>
		<div class="title_h2">
			照片编辑
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
        <div >
        	<form action="${ctx}/console/zy/photoCover/put" method="post" id="myform">
        		<input type="hidden" name="page" value="${page}">
				<table class="fromtable">
					<tr>
						<th class="top_th"><a href="#" class="tongji"><em>查看/照片编辑</em><span></span></a></th>
					</tr>
					<tr >
						<td class="fromtd">
							<table class="contertable">
									<Tr>
										<td class="label">
											标题<font color="red" size="4">*</font>：
										</td>
										<td>
											<input name="title" value="${photo.title}" type="text"
												class=" text" size="50">
										</td>
										<td class="label">
											时间<font color="red" size="4">*</font>:
										</td>
										<td>
											<input onClick="WdatePicker()" name="shootingTime"
												value="<fmt:formatDate value="${photo.shootingTime}"/>" type="text" class="text Wdate required"
												size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											投稿者：
										</td>
										<td>
											<input name="cameristName" value="${photo.cameristName}" type="text"
												class=" text" size="17" placeholder="姓名">
										</td>
											<td class="label">
											地点<font color="red" size="4">*</font>：
										</td>
										<td>
											<input name="address" value="${photo.address}" type="text"
												class=" text required" size="50">
										</td>
									<Tr>
										<td class="label">
											故事介绍：
										</td>
										<td colspan="3">
											<textarea  name="story" cols="60" rows="3" style="width:94%;height:50px;">${photo.story}</textarea>
										</td>
									</Tr>
									
										<Tr>
										<td class="label">
											操作员：
										</td>
										<td>
											<input name="workerName" value="${workerName}" type="text"
												class=" text"  disabled="disabled" size="50">
										</td>
										<td class="label">
											上传时间：
										</td>
										<td>
										   <input name="createTime" value="<fmt:formatDate value="${photo.createTime}" pattern="yyyy-MM-dd HH:mm"/>" 
										   disabled="disabled"
										   type="text" class="text" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											上传照片<font color="red" size="4">*</font>：
										</td>
										<td  colspan="3">
											<div class="upload_add pnitem_img" id="imgList">
												<c:if test="${not empty photo.imgId}">
												<div class="upload_img active" style="cursor: pointer;">
													<img src="${config.imgDomainName}/${photo.imgId.folder }/${photo.imgId.name }_284x290.jpg" width="100%" height="100%">
													<input type="hidden" id="imgId" name="imgId" value="${photo.imgId.id}">
												</div>
												
												</c:if>
											</div>
																						
										</td>
									</Tr>
									<Tr>
										<td class="label">
											审核状态<font color="red" size="4">*</font>：
										</td>
										<td  colspan="3">
											<select  name="released" style="width:95%;  height: 30px;">
												<option value="0" <c:if test="${photo.released eq 0}">selected</c:if>>待审</option>
												<option value="1" <c:if test="${photo.released eq 1}">selected</c:if>>审核通过</option>
												<option value="2" <c:if test="${photo.released eq 2}">selected</c:if>>审核失败</option>
											</select>
											<input type="hidden" name="id" value="${photo.id }">	
										</td>
									</Tr>
								</table>
							<div class="buttondiv"><button type="button" class="btn btn-primary" onclick="goToUrl('${ctx}/console/zy/photo')">返回</button>&nbsp;&nbsp;<button id="submit-btn" class="btn btn-primary" type="button">提交</button> </div>
						</td>
					</tr>
				</table>
			</form>
        </div>
	</div>
 </body>
 <script type="text/javascript">
 $(function(){
	 
	 $("#submit-btn").click(function (){
			$("#myform").submit();
		});
 });
 </script>
</html>
