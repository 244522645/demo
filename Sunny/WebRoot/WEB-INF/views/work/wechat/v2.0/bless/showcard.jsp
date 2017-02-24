<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/common/static.jsp"%>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${config.siteName}</title>
    	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	 <script type="text/javascript">
	 var com_share_title="不能言说的爱这样表示",
	 com_share_content="一缕阳光，一份祝福",
	 com_share_link="${shareAppMessageUrl}"
	 domainName="${config.domainName}",
	 com_share_imgUrl="${bless.userId.wechatHeadUrl}";

	 </script> 
</head>
<body class="v2">
	 <div class="page-group">
        <div class="page yuding-share" id="show-card">
			<nav class="bar bar-tab" style="display:none">
			  <a class="tab-item external fenxianggeipengy" href="#">
			    <span class="tab-label">分享</span>
			  </a>
			</nav>
            <div class="content">
				<div class="liulankapian">
					
					<div>
						<img class="wx-preview-image" data="${bless.photo.imgUrl}_y1080.jpg" src="${bless.photo.imgUrl}_500.jpg">
						<c:if test="${down eq 'true'}">
							<div class="xiazaigaoqing">
								<img src="${ctx}/static/v2.0/img/xiazai.png">
							</div>
						</c:if>
						
					</div>
					<div class="dizizhufu">
						<div class="dizhishijian">
							<b>${bless.photo.provinceF}·${bless.photo.title}</b><span>${bless.photo.shootingTimeF}</span>
						</div>
						<div class="zhufuyv1">
							${bless.bless}
						</div>
						<div class="zengsongren">
							<span>${bless.sender}</span>
						</div>
					</div>
				</div>
				
       		</div> 
 		</div>
	</div>
	
	 <div class="popup popup-youxiang">
		     <div class="content-block heikuai close-popup"></div>
			  <div class="baikuang1">
				  	<div class="shuruyx">
				  		<span>
				  			邮箱：
				  		</span>
				  		<input type="text" id="youxiang" placeholder="请输入邮箱,接受高清原图"/>
				  	</div>
				  	<div class="yanzheng"></div>
				  	<div class="fasong">我们会将高清照片发送到该邮箱，请注意查收</div>
				  	<div class="queding">确定</div>
			  </div>
	 </div>
	 <div class="popup popup-chenggong">
		  <div class="content-block heikuai close-popup"></div>
			  <div class="baikuang1">
			  	<div class="fasong"><img src="${ctx}/static/v2.0/img/dueigou.png"></div>
			  </div>
  </div>
    <!-- popup, panel 等放在这里 -->
   	<div class="popup popup-chenggong wx-fenxiang">
		<div class="content-block heikuai">
		  	<img src="${ctx}/static/v2.0/img/xiantiao.png" />
		</div>
		
		<div class="diandian">点</div>
		<div class="jiji">击</div>
		<div class="fenfen">这</div>
		<div class="xiangxiang">里</div>
	</div>
</body>
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<script type="text/javascript">
$(function(){
	$.init();
});

</script>
<script type="text/javascript">
   	var ShowCard ={
   			init : function (){
   				//分享
  				 $(".fenxianggeipengy").unbind( "click" );
	   			 $(".fenxianggeipengy").on("click",function(){
	   				$.popup('.wx-fenxiang');
	   			});
	   			$(".wx-fenxiang").unbind( "click" ).on("click",function(){
	   				$.closeModal(".wx-fenxiang");
	   			});
   				//大图预览
   				 $(".wx-preview-image").unbind( "click" );
	   			 $(".wx-preview-image").on("click",function(){
	   				var urls = new Array();
	   				urls.push($(this).attr('data'));
	   					wx.previewImage({
	   					    current: $(this).attr('data'), // 当前显示图片的http链接
	   					    urls: urls // 需要预览的图片http链接列表
	   					});
	   			});
	   			 
	   			 //选择
	   			 $(".xiazaigaoqing").unbind( "click" );
	   			$(".xiazaigaoqing").on("click",function(){
   				 	$.popup('.popup-youxiang');
   				});
	   			
	   			//验证
	   			$(".queding").unbind( "click" ).on("click",function(){
				  	$(".yanzheng").empty();
				  	var passVal = $("#youxiang").val();
				  	var reg= /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/; 
					
					if(passVal == '') {
							$(".yanzheng").html('<span>请填写邮箱<span>')
					} else if(reg.test(passVal)==false){
							$(".yanzheng").html('<span>邮箱格式不正确</span>')
					}else{
						  $.ajax({
								url:"${ctx}/wechat/v2/bless/submitDownRequest",
								type: 'post',//提交的方式
								data:{blessId:'${bless.id}',photoId:'${bless.photo.id}',email:passVal},
								dataType:'json',
									//请求成功
								success:function(result){
									//
									if(result.state==1){ //绑定成功
										 $.popup('.popup-chenggong');
									} else
									{
										 $.toast("提交失败");
										$.closeModal(".popup-mail");
									} 
								}
							}); 
						  
						
					}
			  })
   			}
   	};
</script>
<script type="text/javascript">
$(document).on("pageInit", function(e, pageId, $page) {
	if(pageId == 'show-card'){
		ShowCard.init();
	}
});
</script>
</html>