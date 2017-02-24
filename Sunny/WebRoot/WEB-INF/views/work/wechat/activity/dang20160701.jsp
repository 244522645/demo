<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${photo.title }</title>
	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    
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
    
    </style>
  </head>
  <body>
    <div class="page-group">
        <div class="page">
            <!-- 内容 -->
            <div class="content">
            	<div class="list-block">
            	
				    <ul>
				      <!-- Text inputs -->
				      <li>
				        <div class="item-content">
				          <div class="item-media"><i class="icon icon-form-name"></i></div>
				          <div class="item-inner">
				            <div class="item-title label">姓名</div>
				            <div class="item-input">
				              <input type="text" placeholder="真实姓名">
				            </div>
				          </div>
				        </div>
				      </li>
				      <li>
				        <div class="item-content">
				          <div class="item-media"><i class="icon icon-form-email"></i></div>
				          <div class="item-inner">
				            <div class="item-title label">手机</div>
				            <div class="item-input">
				              <input type="email" placeholder="手机">
				            </div>
				          </div>
				        </div>
				      </li>
				    </ul>
				  </div>
				  <div class="content-block">
				    <div class="row">
				      <div class="col-50"><a href="#" class="button button-big button-fill button-success">提交</a></div>
				    </div>
				  </div>
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
                		 $(document).on('click','.button-success', function () {
                			 $.showIndicator() 
                		  });
             	    });
                   </script>
                  
            </div>
        </div>
  </body>
 <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
</html>
