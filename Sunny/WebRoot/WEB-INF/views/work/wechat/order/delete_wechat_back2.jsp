<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>制作福卡-${config.siteName}</title>
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
            <header class="bar bar-nav">
                 <button class="button button-link button-nav pull-right">
                    <a href="${ctx}/wechat/index" class="icon icon-home"></a>
                </button>
                <h1 class="title">${order.title}</h1>
                
               
            </header>
            <!-- 内容 -->
            <div class="content">
                <div class="content-block">
                  <!-- 拍立得卡片效果 -->
               		  <div class="card-mobile">
                        <img class='cover-img wx-img-brow' src="${config.imgDomainName}/${order.cardImage.folder }/${order.cardImage.name}_y1080.jpg" alt="">
                   
                   		<div class="qr-code">
                          <img class="img-responsive" src="${ctx}/static/images/sun/qrcode_for_gh.jpg" alt="二维码">
                          <small>长按识别关注</small>
                        </div>
                    </div>
                   <script type="text/javascript">
                   		$(function(){
	               		  
	                       $(document).on('click','#shareit', function () {
	                    	  $("#shareit").hide(); 
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
            </div>
        </div>
    </div>
  </body>
    <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
</html>