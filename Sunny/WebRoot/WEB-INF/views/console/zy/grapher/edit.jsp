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
 	<style>
		form {
			margin: 0;
		}
		textarea {
			display: block;
		}
	</style>
	<script charset="utf-8"  src="${ctx}/static/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8"  src="${ctx}/static/kindeditor/zh_CN.js"></script>
	<script>
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="introduce"]', {
				//uploadJson : '${ctx}/console/cbirthday/upImg?',
				uploadJson : "${ctx}/console/upload/upImages?folder=head",
				allowFileManager : true
			});
		});
	</script>
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
        <form action="${ctx}/console/zy/photoGrapher/put" method="post" id="myform">
				<table class="fromtable">
					<tr>
						<th class="top_th"><a href="#" class="tongji"><em>新增/编辑摄影师</em><span></span></a></th>
					</tr>
					<tr >
						<td class="fromtd">
							
							<table class="contertable">
									<Tr>
										<td class="label">
											姓名：
										</td>
										<td>
											<input name="name"  value="${photo.name }" type="text" class="text {required:true,maxlength:20 ,minlength:1,messages:{required:'请输入userId'}}" size="50">
										</td>
									</tr>
									<tr>
										<td class="label">
											地点：
										</td>
										<td>
											<input name="tags" value="${photo.tags }" type="text " class="text {required:true,maxlength:20 ,minlength:1,messages:{required:'请输入'}}" size="50" >
										</td>
									</Tr>
									<Tr>
										<td class="label">
											手机：
										</td>
										<td >
											<input name="phone" value="${photo.phone }" type="text" class="text {required:true}" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											邮箱：
										</td>
										<td >
											<input name="email" value="${photo.email }" type="text" class="text" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											头像：
										</td>
										<td >
										<div id="browse" class="uploadifive-button" style="height: 40px; line-height: 40px; overflow: hidden; position: relative; text-align: center; width: 120px;float:left">本地上传</div>
											
											<div id="fileName" >
												
											</div>
											
											
											<div class="upprogress" id="progress"
												style="position: relative; text-align: center; width: 390px; margin-top: 10px; margin-left: 90px; float: none;">
												<div class="progress-bar" id="progress-bar"></div>
											</div>
											
											<hr style="margin-top:5px;">
											<div class="upload_add pnitem_img" id="imgList">
												<c:if test="${not empty photo.head}">
												<div class="upload_img active" style="cursor: pointer;">
													<img src="${config.imgDomainName}/${photo.head.folder }/${photo.head.name }_284x290.jpg" width="100%" height="100%">
													<input type="hidden" id="head" name="head" value="${photo.head.id}">
												</div>
												
												</c:if>
											</div>
										</td>
									</Tr>
									<Tr>
										<td class="label">
											代表作：
										</td>
										<td >
										<div id="browse2" class="uploadifive-button" style="height: 40px; line-height: 40px; overflow: hidden; position: relative; text-align: center; width: 120px;float:left">本地上传</div>
											
											
											<div id="fileName2" >
												
											</div>
											
											
											<div class="upprogress" id="progress2"
												style="position: relative; text-align: center; width: 390px; margin-top: 10px; margin-left: 90px; float: none;">
												<div class="progress-bar" id="progress2-bar"></div>
											</div>
											
											<hr style="margin-top:5px;">
											<div class="upload_add pnitem_img" id="imgList2">
												<c:if test="${not empty photo.work}">
												<div class="upload_img active" style="cursor: pointer;">
													<img src="${config.imgDomainName}/${photo.work.folder }/${photo.work.name }_284x290.jpg" width="100%" height="100%">
													<input type="hidden" id="work" name="work" value="${photo.work.id}">
												</div>
												
												</c:if>
											</div>
										</td>
									</Tr>
									<Tr>
										<td class="label">
											介绍：
										</td>
										<td >
											<textarea name="introduce" style="width:800px;height:400px;visibility:hidden;">${photo.introduce }</textarea>
										<input type="hidden" name="id" value="${photo.id }">	
										</td>
									</Tr>
								</table>
							<div class="buttondiv"><button type="button" class="btn btn-primary" onclick="goToUrl('${ctx}/console/cbirthday')">返回</button>&nbsp;&nbsp;<button class="btn btn-primary" type="submit">提交</button> </div>
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
 <script type="text/javascript">
		//照片显示
		function addImgHtml(obj) {
			var html = "";
			html += "<div class=\"upload_img active\" style='cursor: pointer;'>"
				+ "<input type='hidden' name='head' id='head' value='"+obj.id+"'>"
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
				+ "<input type='hidden' name='work' id='work' value='"+obj.id+"'>"
					+ "<img src='${config.imgDomainName}/"
					+ obj.folder+'/'+obj.name
					+ "_284x290.jpg'  width='280'> "
					+ "</div>";
			$("#imgList2").html(html);
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
					folder : 'heads',
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
						folder : 'heads',
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
			var fileName2='';
			var uploader2 = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'browse2', //触发文件选择对话框的按钮，为那个元素id
				url : '${ctx}/console/upload/upImages', //服务器端的上传页面地址
				flash_swf_url : '${ctx}/static/js/plupload/Moxie.swf', //swf文件，当需要使用swf方式进行上传时需要配置该参数
				silverlight_xap_url : '${ctx}/static/js/plupload/Moxie.xap', //silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
				file_data_name : 'myFile',
				chunk_size : '100kb',
				multipart_params : {
					folder : 'heads',
					id : '',
					fileName : fileName2,
				}

			});

			//在实例对象上调用init()方法进行初始化
			uploader2.init();

			//绑定各种事件，并在事件监听函数中做你想做的事
			uploader2.bind('FilesAdded', function(uploader, files) {
				for (var i = 0; i < files.length; i++) {
					$("#fileName2").html("<input type=\"hidden\" name=\"localName\" value=\""+files[i].name+"\">  /"+files[i].name);
					fileName=files[i].name;
					
				}
				uploader.settings.multipart_params = {
						folder : 'heads',
						id : '',
						fileName : fileName
				};
				uploader.start();
				uploader.disableBrowse(true);//上传按钮关闭
				$("#myFace2").hide();
				$("#progress2").show();
			});

			//进度条
			uploader2.bind('UploadProgress', function(uploader, file) {
				var w = file.percent < 10 ? file.percent : file.percent - 10;//为了进度条的好看
				$("#progress2-bar").css("width", w + "%");
			});

			//片段文件上传完成后
			uploader2.bind('ChunkUploaded',
							function(uploader, file, responseObject) {
								uploader.settings.multipart_params.id = responseObject.response;

							});

			//上传完成后
			uploader2.bind('FileUploaded', function(uploader, file,
					responseObject) {
				uploader.settings.multipart_params.id = '';
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
				if (typeof($("#work").val()) == "undefined") { 
					   alert("请上传照片"); 
					   return;
					}  
				$("#myform").submit();
			});
			
			
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
	</script>
</html>
