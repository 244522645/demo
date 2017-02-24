<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
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
  </head>
  <body>
    <div class="page-group">
        <div class="page"  id='show'>
            <!-- 标题栏 -->
            <%-- <header class="bar bar-nav bar-fixed">
              <a class="icon icon-left pull-left" href="javascript:void(0);"></a>
              <a class="icon icon-gift pull-right createCard" style="color:#4cd964;" href="${shareUrl }"></a>
              <h1 class="title">${photo.title }</h1>
            </header> --%>
            <!-- 内容 -->
            <div class="content content-clear-top">
                    <div class="card-mobile">
                        <div class="tip-modal" id="tip-modal">
                            <h3>
                            ${photo.title }
                             <a class="menu-item pull-right createCard"  style="color:#4cd964;font-size: 0.8rem;"  href="${shareUrl }">
                                <span class="icon icon-gift">&nbsp;送出祝福</span>
                            </a>
                             </h3>
                            <p><fmt:formatDate value="${photo.shootingTime}" pattern="yyyy年MM月dd日"/>${photo.title }的第一缕阳光</p>
                            
                        </div>
                        <div class="bar-menu" onclick="showMenu();">
                            <span class="menu-item pull-left">
                                <span class="icon icon-app"  style="color:#fff;" ></span>
                            </span>
                        </div>
                        <%-- <img  class='cover-img touch-null' src="${config.imgDomainName}/${photo.imgId.folder}/${photo.imgId.name}_y1080.jpg" alt=""> --%>
                    </div>
                    
                    <script type="text/javascript">

 function showMenu() {
	if( $("#tip-modal").hasClass("hide")){
		 $("#tip-modal").removeClass("hide");
		$(".createCard").removeClass("hide");
	}else{
		$(".tip-modal").addClass("hide");
		$(".createCard").addClass("hide");
	}
 };
</script>
<style type="text/css">
    
.modal-overlay.modal-overlay-visible, .popup-overlay.modal-overlay-visible, .preloader-indicator-overlay.modal-overlay-visible {
  visibility: visible;
  opacity: 1;
}
.modal-overlay, .popup-overlay, .preloader-indicator-overlay {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,.4);
  z-index: 99999999;
  visibility: hidden;
  opacity: 0;
  -webkit-transition-duration: .4s;
  transition-duration: .4s;
}

.modal.modal-in {
  opacity: 1;
  -webkit-transition-duration: .4s;
  transition-duration: .4s;
  -webkit-transform: translate3d(0,0,0) scale(1);
  transform: translate3d(0,0,0) scale(1);
}
.modal {
  width: 13.5rem;
  position: absolute;
  z-index: 9999999;
  left: 50%;
  margin-left: -6.75rem;
  margin-top: 0;
  top: 50%;
  text-align: center;
  border-radius: .35rem;
  opacity: 0;
  -webkit-transform: translate3d(0,0,0) scale(1.185);
  transform: translate3d(0,0,0) scale(1.185);
  -webkit-transition-property: -webkit-transform,opacity;
  transition-property: transform,opacity;
  color: #3d4145;
  display: none;
}

.card-mobile > .tip-modal{
    bottom: 3em;
    position: fixed;
}
.card-mobile > .tip-modal:before{
left: 22%;
}
.hide{display:none!important}
</style>
<style type="text/css"> 
.card-mobile { 
background: url('${config.imgDomainName}/${photo.imgId.folder}/${photo.imgId.name}_y1080.jpg'); 
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
                    <script type="text/javascript">
	                    com_share_title="${photo.title }",
	                    com_share_content="${photo.story }",
	                    com_share_link=location.href.split('#')[0],
	                    com_share_imgUrl="${config.imgDomainName}/${photo.imgId.folder}/${photo.imgId.name}_100x100.jpg";
	                    
	                    com_shareAppMessage_title=com_share_title,
	                    com_shareAppMessage_content=com_share_content,
	                    com_shareAppMessage_link=com_share_link,
	                    com_shareAppMessage_imgUrl=com_shareAppMessage_imgUrl;
	                    
	                    if (wxjs) {
	                    	 share();
		               	} 
	                	fshare = function(){ 
                    		share();
                    	} 
	                	 $(function(){
	                		 $(document).on('click','#createCard', function () {
	                			 $.showPreloader('加载中');
	                		  });
	             	    });
                    </script>
                  
            </div>
        </div>
    </div>
  </body>
 <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
     <%@include file="/WEB-INF/views/work/common/pageInit.jsp" %>
</html>
