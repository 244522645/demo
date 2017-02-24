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
	   	var imgId;
		var imgUrl;
		var imgName;
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
   	//jquery ui
		$(function() {
	     	$( "#dialog-form" ).dialog({
	 			autoOpen: false,
	 			height: 400,
	 			title:'图库',
	 			width: 750,
	 			modal: true,
	 			buttons: {
	 				"使用": function() {
	 			 		selectAddImgHtml(imgId,imgUrl,imgName)
	 			 		$( this ).dialog( "close" );
	 				},
	 				"查看大图": function() {
	 					//$( this ).dialog( "close" );
	 				}
	 			}
	 		});
		});

     	function showImgLib(){
     		$( "#dialog-form" ).dialog( "open" );
     		//图库
     	 }
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

/* thumbnails */
ul.thumbnails.image_picker_selector{overflow:auto;list-style-image:none;list-style-position:outside;list-style-type:none;padding:0px;margin:0px;}
ul.thumbnails.image_picker_selector li{margin:0px 12px 12px 0px; float:left;}
ul.thumbnails.image_picker_selector li .thumbnail{padding:6px;border:1px solid #dddddd;}
ul.thumbnails.image_picker_selector li .thumbnail.selected{background:#0088cc;}
</style>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav">照片管理 ＞ 上传编辑 </div>
		<div class="title_h2">
			上传编辑
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
        <div >
        	<form action="${ctx}/console/zy/photo/put" method="post" id="myform">
        		<input type="hidden" name="page" value="${page}">
				<table class="fromtable">
					<tr>
						<th class="top_th"><a href="#" class="tongji"><em>查看/修改照片</em><span></span></a></th>
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
											省市<font color="red" size="4">*</font>：
										</td>
										<td>
										    <select id="loc_province" name="province" style="width:47%;">
										    </select>
										    <select id="loc_city" name="city" style="width:47%; margin-left: 2%">
										    </select>
											<%-- 
											<input type="text" size="6" value="${photo.province}" class="text valid" id="province" name="province" placeholder="省份">-
											<input type="text" size="6" value="${photo.city}" class="text valid" id="city" name="city" placeholder="城市">
											 --%>
											<%-- <div class="col-sm-2">
												<input id="pr" type="text" class="form-control" name ="province" placeholder="省份"
												value="${photo.province}" rule="must" <c:if test="${!empty photo.province}">disabled="disabled"</c:if>/>
												</div>
											<div class="col-sm-3">
									            <input id="ci" type="text" class="form-control" name ="city" placeholder="城市" 
									            value="${photo.city}" rule="must" <c:if test="${!empty photo.city}">disabled="disabled"</c:if>/>
									            </div> --%>
											<%-- <div class="col-sm-3">
									            <input id="co" type="text" class="form-control" name ="area" placeholder="县级" 
									            value="${photo.area}" rule="must" <c:if test="${!empty photo.area}">disabled="disabled"</c:if>/>
								            </div> --%>
										</td>
										<td class="label">
											地点<font color="red" size="4">*</font>：
										</td>
										<td>
											<input name="address" value="${photo.address}" type="text"
												class=" text required" size="50">
										</td>
									</Tr>
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
											分类题材：
										</td>
										<td>
											<%-- <input name="subject"
												value="${photo.subject}" type="text" class="text"
												size="50"> --%>
											<select  name="subject" style="width:95%;  height: 30px;">
												<option value="日出" <c:if test="${photo.subject eq '日出'}">selected</c:if>>日出</option>
												<option value="升旗" <c:if test="${photo.subject eq '升旗'}">selected</c:if>>升旗</option>
												<option value="美景" <c:if test="${photo.subject eq '美景'}">selected</c:if>>美景</option>
												<option value="日落" <c:if test="${photo.subject eq '日落'}">selected</c:if>>日落</option>
												<option value="美景" <c:if test="${photo.subject eq '其他'}">selected</c:if>>其他</option>
											</select>
										</td>
										<td class="label">
											标签：
										</td>
										<td>
										<div>
			        						<input id="tags" name="tags" type="text" class="tags yahei"  style="display: none;" >
			        					</div>
			        					
											<%-- <input name="tags"
												value="${photo.tags}" type="text" class="text"
												size="50">
											如： --%>
										</td>
									</Tr>
									
									<Tr>
										<td class="label">
											摄影师：
										</td>
										<td>
											<select name="camerist"  class="text">
												<c:if test="${empty photo.grapher }">
													<option>
														选择摄影师
													</option>
												</c:if>
												<c:if test="${not empty photo.grapher }">
													<option value="${photo.grapher.id}">${photo.grapher.name }</option>
												</c:if>
																								
												<c:forEach items="${cameristList}" var="camerist" varStatus="status">
													<option value="${camerist.id}">&nbsp;&nbsp;&nbsp;${camerist.name }&nbsp;&nbsp;</option>
												</c:forEach>
											</select>
											<input name="cameristName" value="${photo.cameristName}" type="text"
												class=" text" size="17" placeholder="姓名">
										    &nbsp; - &nbsp; 
										    <input name="cameristId" value="${photo.cameristId}" type="text"
												class=" text number" size="20" placeholder="联系方式">
										</td>
										<td class="label">
											价格：
										</td>
										<td>
										   <input name="price" value="${photo.price}" type="text"
												class=" text number" size="50">元
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
											<div id="browse" class="uploadifive-button" style="height: 40px; line-height: 40px; overflow: hidden; position: relative; text-align: center; width: 120px;float:left">本地上传</div>
											<div id="browseImgLib" class="uploadifive-button" style="height: 40px; line-height: 40px; overflow: hidden; position: relative; text-align: center; width: 120px;left: 10px;">选择图库</div>
											
											
											<div id="fileName" >
												
											</div>
											
											
											<div class="upprogress" id="progress"
												style="position: relative; text-align: center; width: 390px; margin-top: 10px; margin-left: 90px; float: none;">
												<div class="progress-bar" id="progress-bar"></div>
											</div>
											
											<hr style="margin-top:5px;">
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
											证明图片：
										</td>
										<td  colspan="3">
											<div id="browse2" class="uploadifive-button" style="height: 40px; line-height: 40px; overflow: hidden; position: relative; text-align: center; width: 120px;">选择图片</div>
											<div class="upprogress" id="progress2"
												style="position: relative; text-align: center; width: 390px; margin-top: 10px; margin-left: 90px; float: none;">
												<div class="progress-bar" id="progress2-bar"></div>
											</div>
											<div class="upload_add pnitem_img" id="explainList" style="display: block;">
												
												<c:forEach items="${explainList}" var="img" varStatus="status">
													<div class="upload_img active" style="cursor: pointer;">
														<div class="deleted" onclick="delImg(this)">删除</div>
														<img src="${config.imgDomainName}/${img.folder }/${img.name }_284x290.jpg" width="100%" height="100%">
														<input type="hidden" name="explainIds" value="${img.id }">
													</div>
												</c:forEach>
											</div>
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
	 <div id="dialog-form"  title="图库" class="fromtable">
			<div class="imageLib">
				<ul class="thumbnails image_picker_selector">
				</ul>
				<div class="more" style="text-align: center;">
					<br>
					<a href="javascript:void();" onclick="getImgLiblist()" style="font-size: 20px;margin:10px">查看更多</a>
				</div>
			</div>
		
		</div>
 </body>
 <script type="text/javascript">
		 function AddTag(tag) {
			 if ($('#tags').tagExist(tag)==false) {  
				$('#tags').addTag(tag);
			 }
		}
		
		<c:forEach var="taag" items="${tags}" varStatus="status">
		 	AddTag('${taag}');
		</c:forEach>
		
		//照片显示
		function addImgHtml(obj) {
			var html = "";
			html += "<div class=\"upload_img active\" style='cursor: pointer;'>"
				+ "<input type='hidden' name='imgId' id='imgId' value='"+obj.id+"'>"
					+ "<img src='${config.imgDomainName}/"
					+ obj.folder+'/'+obj.name
					+ "_284x290.jpg'  width='280'> "
					+ "</div>";
			$("#imgList").html(html);
		}
		//证明图片显示
		function addExplainIdsHtml(obj) {
			var html = "";
			html += "<div class=\"upload_img active\" style='cursor: pointer;'>"
				+ "<div class=\"deleted\" onclick=\"delImg(this)\">删除</div>"
				+ "<input type='hidden' name='explainIds'  value='"+obj.id+"'>"
				+ "<img src='${config.imgDomainName}/"
				+ obj.folder+'/'+obj.name
				+ "_284x290.jpg' width=\"100%\" height=\"100%\"> "
					+ "</div>";
			$("#explainList").append(html);
		}

		//删除图片
		function delImg(el) {
			$(el).parent().remove();
		}

		//参考 http://www.cnblogs.com/2050/p/3913184.html
		$(function() {
			
			//实例化一个plupload上传对象
			var fileName='';
			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'browse', //触发文件选择对话框的按钮，为那个元素id
				url : '${ctx}/console/upload/upImages', //服务器端的上传页面地址
				flash_swf_url : '${ctx}/static/js/plupload/Moxie.swf', //swf文件，当需要使用swf方式进行上传时需要配置该参数
				silverlight_xap_url : '${ctx}/static/js/plupload/Moxie.xap', //silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
				file_data_name : 'myFile',
				chunk_size : '100kb',
				multipart_params : {
					folder : 'works',
					id : '',
					fileName : fileName,
				}

			});

			//在实例对象上调用init()方法进行初始化
			uploader.init();

			//绑定各种事件，并在事件监听函数中做你想做的事
			uploader.bind('FilesAdded', function(uploader, files) {
				for (var i = 0; i < files.length; i++) {
					$("#fileName").html("<input type=\"hidden\" name=\"localName\" value=\""+files[i].name+"\">  /"+files[i].name);
					fileName=files[i].name;
					
				}
				uploader.settings.multipart_params = {
						folder : 'works',
						id : '',
						fileName : fileName
				};
				uploader.start();
				uploader.disableBrowse(true);//上传按钮关闭
				$("#myFace").hide();
				$("#progress").show();
			});

			//进度条
			uploader.bind('UploadProgress', function(uploader, file) {
				var w = file.percent < 10 ? file.percent : file.percent - 10;//为了进度条的好看
				$("#progress-bar").css("width", w + "%");
			});

			//片段文件上传完成后
			uploader.bind('ChunkUploaded',
							function(uploader, file, responseObject) {
								uploader.settings.multipart_params.id = responseObject.response;

							});

			//上传完成后
			uploader.bind('FileUploaded', function(uploader, file,
					responseObject) {
				uploader.settings.multipart_params.id = '';
				$("#progress").hide();
				var src = eval('(' + responseObject.response + ')');
				if (src.state == 1) {
					addImgHtml(src.t);
					$("#progress-bar").css("width", 0 + "%");
				} else {
					alert(src.message + '');
					$("#progress-bar").css("width", 0 + "%");
				}
				uploader.disableBrowse(false);//上传按钮打开
			});

			//实例化一个plupload上传对象
			var uploader2 = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'browse2', //触发文件选择对话框的按钮，为那个元素id
				url : '${ctx}/console/upload/upImages', //服务器端的上传页面地址
				flash_swf_url : '${ctx}/static/js/plupload/Moxie.swf', //swf文件，当需要使用swf方式进行上传时需要配置该参数
				silverlight_xap_url : '${ctx}/static/js/plupload/Moxie.xap', //silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
				file_data_name : 'myFile',
				chunk_size : '100kb',
				multipart_params : {
					folder : 'works',
					id : '',
					fileName : fileName,
				}

			});

			//在实例对象上调用init()方法进行初始化
			uploader2.init();

			//绑定各种事件，并在事件监听函数中做你想做的事
			uploader2.bind('FilesAdded', function(uploader, files) {
				uploader.start();
				uploader.disableBrowse(true);//上传按钮关闭
				for (var i = 0; i < files.length; i++) {
					fileName=files[i].name;
				}
				uploader.settings.multipart_params = {
						folder : 'works',
						id : '',
						fileName : fileName
				};
				$("#myFace").hide();
				$("#progress2").show();
			});

			//进度条
			uploader2.bind('UploadProgress', function(uploader, file) {
				var w = file.percent < 10 ? file.percent : file.percent - 10;//为了进度条的好看
				fileName=file.name;
				$("#progress2-bar").css("width", w + "%");
			});

			//片段文件上传完成后
			uploader2.bind(
							'ChunkUploaded',
							function(uploader, file, responseObject) {
								uploader2.settings.multipart_params.id = responseObject.response;

							});

			//上传完成后
			uploader2.bind('FileUploaded', function(uploader, file,
					responseObject) {
				uploader2.settings.multipart_params.id = '';
				$("#progress2").hide();
				var src = eval('(' + responseObject.response + ')');
				if (src.state == 1) {
					addExplainIdsHtml(src.t);
					$("#progress2-bar").css("width", 0 + "%");
				} else {
					alert(src.message + '');
					$("#progress2-bar").css("width", 0 + "%");
				}
				uploader2.disableBrowse(false);//上传按钮打开
			});
			
			$("#submit-btn").click(function (){
				if (typeof($("#imgId").val()) == "undefined") { 
					   alert("请上传照片"); 
					   return;
					}  
				$("#myform").submit();
			});
			
			//标签设置
			$('#tags').tagsInput({width:'250px',height:'30px;',onAddTag:function(){$("#tagserror").hide();}});	
		
			
			$("#browseImgLib").click(function (){
				$( "#dialog-form" ).dialog("open");
				$(".image_picker_selector").html("");
				page=1;
				getImgLiblist(1);
			});
		});
	   var	page=1;
		function getImgLiblist(){
			
			$.ajax({  
		        type:'GET',  
		        url:"${ctx}/console/upload/data?page="+page,  
		        success:function(result){
		        	var pot = result.result;
		        	var html='';
		        	if(page>10){
		        		$(".more").html("");
		        	}
		        	 for (var i = 0; i < pot.length; i++) {
		        		 html += "<li><div id=\"imgid"+pot[i].id+"\" class=\"thumbnail\">"
		        		 +"<img onclick=\"selectImg('"+pot[i].id+"','"+pot[i].folder+'/'+pot[i].name+"','"+pot[i].localName+"')\" class=\"image_picker_image\" src=\"${config.imgDomainName}/"+ pot[i].folder+'/'+pot[i].name+ "_y100.jpg\">"
		        		 +"<div style=\"font-size:9px;\">"+pot[i].localName+"</div>"
		        		 +"</div></li>";
		        	 }
		        	 $(".image_picker_selector").append(html);
		        	 page=page+1;
		        }
			 });
		}
		function selectImg(id,url,name){
			imgId = id;
			imgUrl=url;
			imgName=name;
			$(".selected").removeClass("selected");	
			
			$("#imgid"+imgId).addClass("selected").siblings().removeClass("selected");	
			
		}
		//选择照片显示
		function selectAddImgHtml(id,url,name) {
			var html = "";
			html += "<div class=\"upload_img active\" style='cursor: pointer;'>"
				+ "<input type='hidden' name='imgId' id='imgId' value='"+id+"'>"
					+ "<img src='${config.imgDomainName}/"
					+ url
					+ "_284x290.jpg'  width='280'> "
					+ "</div>";
			$("#imgList").html(html);
			$("#fileName").html("<input type=\"hidden\" name=\"localName\" value=\""+name+"\">  /"+name);
			
		}
		//初始化城市选择器 （基于select2插件）
		showLocation('${photo.province}','${photo.city}','');
		$('#loc_province').select2("val","${photo.province}");
		$('#loc_city').select2("val","${photo.city}");
	</script>
</html>
