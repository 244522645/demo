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
  </head>
  <body>
   <div class="page-group">
      <div class="page">
        <div class="content">
          <div class="header-banner">
            <div class="banner " style="height:auto;">
               <img style="width:100%;" class="wx-img-brow" src="${config.imgDomainName}/${order.cardImage.folder }/${order.cardImage.name}_500.jpg" alt="">
              <div class="banner-content">
                <img class='banner-img' src="${wximg}" alt="">
                <div class="banner-text">
                  <span class="name">${order.title}</span>
                  <!-- <span class="tip">送</span>
                  <span class="name">某某</span> -->
                </div>
              </div>
            </div>
            <div class="body">
              <img src="${ctx}/static/images/sun/qrcode_for_gh.jpg" alt="二维码">
              ↗↗关注“给点儿阳光”公众号↖↖
              <br>
               <br>
                <br>
                 <br>
                  <br>
            </div>
          </div>
        </div>
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
  </body>
    <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
</html>