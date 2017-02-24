<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>送祝福-${config.siteName}</title>
    <meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
 <style type="text/css">
    .content-block {
    margin: 0; 
}

.content-block {
    margin: 0;
    padding:0;
    color: #6d6d72;
}
</style>
  </head>
  <body>
    <div class="page-group">
        <div class="page">
            <!-- 标题栏 -->
                 <button class="button button-link button-nav pull-right" style="top:1rem;right:1rem;z-index:11111;">
                    <a  class="icon icon-home" onclick="window.location.href='${config.domainName}/wechat/index';" target="_blank"></a>
                </button>
               
            <!-- 内容 -->
            <div class="content">
                <div class="content-block ">
                  <!-- 拍立得卡片效果 -->
               		  <div class="card-mobile wx-img-brow">
                       <%--  <img class='cover-img wx-img-brow' src="${config.imgDomainName}/${order.cardImage.folder }/${order.cardImage.name}_y1080.jpg" alt=""> --%>
                    </div>
                   <script type="text/javascript">
                   		$(function(){
	               		  
	                       $(document).on('click','#shareit', function () {
	                    	  $("#shareit").hide(); 
	                    	});
	                       $(document).on('click','.wx-img-brow',function () {
		                  		imgBrow();
		                   	  }); 
	               	   });
	                   	function shareApp(){
	                    	 $("#shareit").show();
	                    }
	                    //spa 注册
	                    if (wxjs) {
	                    	 share();
		               	} 
	                    //ajax 注册
	                	fshare = function(){ 
	                		share();
	                	} 
	                    
	                	//图片浏览器 -照片
	                	imgBrowArray = new Array();
	                	imgBrowArray[0] = '${config.imgDomainName}/${order.cardImage.folder }/${order.cardImage.name}_y2000.jpg';
	                	
                </script>
                </div>
                
 <style type="text/css"> 
.card-mobile { 
background: url('${config.imgDomainName}/${order.cardImage.folder }/${order.cardImage.name}_y1080.jpg'); 
background-size:
 100%;
-webkit-animation:cloud 20s linear 1s infinite alternate;
-o-animation:cloud 20s linear 1s infinite alternate;
} 
@-webkit-keyframes cloud { 
from{background-position:0% 0%} 
to{background-position:100% 100%} 
} 
@media all and (orientation : landscape) { 

.card-mobile { 

background-size:100% auto;
} 

} 

@media all and (orientation : portrait){ 

.card-mobile { 
background-size:auto 100%;
} 

} 
.card-mobile:ACTIVE {
	animation-play-state:paused;
}
/* .bar-menu:HOVER {
	animation-play-state:running;
} */
</style> 
            </div>
        </div>
    </div>
  </body>
    <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
</html>