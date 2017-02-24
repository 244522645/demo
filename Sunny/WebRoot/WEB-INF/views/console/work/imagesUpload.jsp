<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/console/common/static.jsp"%>
<!DOCTYPE html>
<html>
<head>
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
</style>
<body>
	<div class="bodyMain">
		<div class="top_subnav">照片管理  ＞ 上传照片</div>
		<div class="title_h2">上传照片</div>
		<p class="mylinep" style="margin-top: 0;"></p>
		<div>
			<form id="formup" action="#" method="post">
				<div class="pnitem">
					<div class="pnlabel">
						<span class="inputmust">*</span>标题
					</div>
					<input name="title" class="inputtxt" type="text" autocomplete="off"
						value="${works.title}"> <input type="hidden" name="id"
						value="${works.id}">
				</div>
				<div class="pnitem">
					<div class="pnlabel">
						<span class="inputmust">*</span>添加图片
					</div>
					<div id="browse" class="uploadifive-button"
						style="height: 40px; line-height: 40px; overflow: hidden; position: relative; text-align: center; width: 120px;">选择图片</div>
					<div class="upprogress" id="progress"
						style="position: relative; text-align: center; width: 390px; margin-top: 10px; margin-left: 90px; float: none;">
						<div class="progress-bar" id="progress-bar"></div>
					</div>
					<div
						style="margin-top: 5px; margin-left: 90px; color: #555; overflow: hidden;">建议一次不要超过5张作品，格式jpg,jpeg
						&nbsp;&nbsp;文件大小：20MB以内</div>
					
				</div>
				<div id="imgList">
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
		function addImgHtml(obj) {
			var html = "";
			html += "<div class=\"upload_img active\" style='cursor: pointer;'>"
					
					+ "<img src='"
					+ obj.imgWebPath+'.'+obj.suffix
					+ "' width=\"100%\" height=\"100%\"> "
					+ "</div>";
			$("#imgList").append(html);
		}

		//删除
		function delImg(el) {
			$(el).parent().remove();
			setFmShow($(".showFm:first").attr("id"));
		}

		//参考 http://www.cnblogs.com/2050/p/3913184.html
		$(function() {
			//实例化一个plupload上传对象
			var thumbnails = "{thumbnail1:{i:6,w:262,h:265},thumbnail2:{i:4,w:78,h:90},thumbnail3:{i:4,w:884,h:1000},thumbnail4:{i:6,w:584,h:300},thumbnail5:{i:4,w:284,h:300}}";
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
					thumbnails : thumbnails
				}

			});

			//在实例对象上调用init()方法进行初始化
			uploader.init();

			//绑定各种事件，并在事件监听函数中做你想做的事
			uploader.bind('FilesAdded', function(uploader, files) {
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
			uploader
					.bind(
							'ChunkUploaded',
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
					alert(src.message + '，最小尺寸： 600x500/px');
					$("#progress-bar").css("width", 0 + "%");
				}
				uploader.disableBrowse(false);//上传按钮打开
			});
		});
	</script>
</body>
<script type="text/javascript">
	function DropDown(el) {
		this.dd = el;
		this.placeholder = this.dd.children('span');
		this.opts = this.dd.find('ul.dropdown > li');
		this.val = '';
		this.index = -1;
		this.initEvents();
	}
	DropDown.prototype = {
		initEvents : function() {
			var obj = this;

			obj.dd.on('click', function(event) {
				$(this).toggleClass('active');
				return false;
			});

			obj.opts.on('click', function() {
				var opt = $(this);
				obj.val = opt.text();
				obj.index = opt.index();
				obj.placeholder.text(obj.val);
			});
		},
		getValue : function() {
			return this.val;
		},
		getIndex : function() {
			return this.index;
		}
	}
</script>
</html>
