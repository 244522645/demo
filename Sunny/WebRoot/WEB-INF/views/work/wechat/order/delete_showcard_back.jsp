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
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
<style>
 #shareit {
  -webkit-user-select: none;
  display: none;
  position: absolute;
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,0.85);
  text-align: center;
  top: 0;
  left: 0;
    z-index: 9999;
}
#shareit img {
  max-width: 100%;
}
.arrow {
  position: absolute;
  right: 10%;
  top: 5%;
}
#share-text {
  margin-top: 400px;
  font-size: 1.17em;
  color:#FFFFFF;
}
a:link{
text-decoration:none;
}
a:visited{
text-decoration:none;
}
a:hover{
text-decoration:none;
}
a:active{
text-decoration:none;
}
.bar .zhizuo {
    
    top: 0rem;

}

.content-block {
    margin: 0; 
}

.content-block {
    margin: 0;
    padding:0;
    color: #6d6d72;
} 
.card-mobile > .tip-modal{
    bottom: 0em;
    width: 100%;
        position: fixed;
}
.photo-browser .bar-nav{
	display: none;
}
.photo-browser .bar-tab{
	display: none;
}
.photo-browser .bar-tab~.content{
	top:0;
}
.photo-browser .bar-nav~.content{
	bottom:0;
}
.card-mobile {
    position: initial;
 }
</style>
  </head>
  <body>
   <div id="shareit"> 
	  <img class="arrow" src="${ctx}/static/images/share-it.png"> 
	  <a href="#" id="follow"> 
	    <!-- <img id="share-text" src="http://dev.vxtong.com/cases/nuannan/imgs/share-text.png"> -->
	   <div  id="share-text">
	    	  请点击右上角，选择- “发送给朋友” 找到要送的人。
	   </div>
	    
	  </a>
  </div>
    <div class="page-group">
        <div class="page">
            <!-- 标题栏 -->
            <header class="bar bar-nav">
                <h1 class="title">贺卡</h1>
                 <button class="button button-link button-nav pull-left">
                    <a href="${ctx }/wechat/letter?orderId=${order.id}#content" class="button button-fill zhizuo button-success">制作简信</a>
                </button>
                 <button class="button button-link button-nav pull-right">
                    <a href="javascript:shareApp();" id="onMenuShareAppMessage" class="button button-fill zhizuo button-success">发给Ta</a>
                </button>
            </header>
            <div class="content">
                <div class="content-block"  style="height: 100%;">
                    <div class="card-mobile wx-img-brow">
                    
                    	 <div class="tip-modal">
                            <h3>${order.photo.title}</h3>
                            <p>${order.photo.story}</p>
                             <p style="float: right; font-size: 0.6rem;padding-right: 14px;"> ${order.photo.shootingTimeF}</p>
                        </div>
                        <%-- <img class='cover-img wx-img-brow' src="${config.imgDomainName}/${order.cardImage.folder }/${order.cardImage.name}_y1080.jpg" > --%>
                    </div>
                 </div>
                 <%-- <div style="padding-left: 14px;padding-right: 14px;">
                	 <h4>${order.photo.title} ——<tags:todayDateForamt dateTime="${order.photo.shootingTime}" /> </h4>
                	 <p style="font-size: 0.6rem">${order.photo.story}</p>
               </div> --%>
                 <script type="text/javascript">
	             //朋友分享 参数
	               var orderId="${order.id}";
	               com_shareAppMessage_title='${order.title}',
	               com_shareAppMessage_content='${orderMessage}',
	               
	               com_shareAppMessage_link='${shareAppMessageUrl}',
	               com_shareAppMessage_imgUrl='${config.imgDomainName}/${order.cardImage.folder }/${order.cardImage.name}_100x100.jpg';
	              
               	   Zepto(function($){
               		  
                       $(document).on('click','#shareit', function () {
                    	  $("#shareit").hide(); 
                    	});
                    
               	   });
                   /* 	function shareApp(){
                    	 $("#shareit").show();
                    } */
                    //spa 注册
                    /* if (wxjs) {
                    	 share();
	               	} 
                    //ajax 注册
                	fshare = function(){ 
                		share();
                	}  */
                    
                	//图片浏览器 -照片
                	//imgBrowArray = new Array();
                	///imgBrowArray[0] = '${config.imgDomainName}/${image.folder }/${image.name }_y2000.jpg';
                	
                	//图片浏览器 -照片
                	imgBrowArray = new Array();
                	imgBrowArray[0] = '${config.imgDomainName}/${order.cardImage.folder }/${order.cardImage.name}_y2000.jpg';
                	
                </script>
                <script type="text/javascript">
					function shareApp(){
					   	 $("#shareit").show();
					     wx.ready(function(){
					    		//朋友分享 参数
						              
					    	      wxShare.init({
					    				title:this.title,
					    				imgUrl:this.imgUrl,
					    				link:this.link,
					    				content:this.content
					    			});
					    		  	var json = {
					    		  			"title":com_shareAppMessage_title,
					    		  			"content":com_shareAppMessage_content,
					    		  			"link":com_shareAppMessage_link,
					    		  			"imgUrl":com_shareAppMessage_imgUrl,
					    		  			};
					    			wxShare.shareAppFunction(json,OrderShareSuccess.success,OrderShareSuccess.cancel);
					    	 	});
				   }
				</script>
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
