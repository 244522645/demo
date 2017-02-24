<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>日出明星片-${config.siteName}</title>
    <meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/common/static.jsp"%>
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
  margin-top: 15em;
  font-size: 1.17em;
  color:#FFFFFF;
  width:98%;
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
</style>
<style type="text/css">
.content-block {
    margin: 0; 
}

.content-block {
    margin: 0;
    padding:0;
    color: #6d6d72;
} 
.card-mobile {
    position: initial;
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
.bar-menus-button {
    position: fixed;
    left: 0;
    right: 0;
    bottom: 1em;
    z-index: 990;
    padding: 0 20%;
    text-align: center;
}
    </style>
    <style type="text/css"> 
.card-mobile { 
background: url('${order.cardImage.localPath}_y1080.jpg'); 
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
            <!-- <header class="bar bar-nav">
                <button class="button button-link button-nav pull-left">
                    <a href="javascript:void(0);" class="icon icon-left"></a>
                </button>
                <h1 class="title">制作成功！</h1>
                
            </header> -->
            <!-- 内容 -->
            <div class="content content-clear-top">
                <div class="content-block" style="height: 100%;">
                    <!-- 拍立得卡片效果 -->
               		  <div class="card-mobile wx-img-brow">
                        <%-- <div class="tip-modal">
                            <h3>${photo.title }</h3>
                            <p>${photo.story }</p>
                        </div> --%>
                        
                       <%--  <img class='cover-img wx-img-brow' src="${config.imgDomainName}/${image.folder }/${image.name }_y1080.jpg" alt=""> --%>
                    </div>
               			<div class="bar-menus-button">
                           <div class="col-50">
					      <a   href="javascript:shareApp();" id="onMenuShareAppMessage"  class="button button-big button-fill button-success">发给朋友</a>
					      </div>
					      <div class="col-50" style="margin-top:1rem;">
					      <a href="${ctx }/wechat/letter?orderId=${order.id}#content" id="onMenuShareAppMessage" class="button button-big button-fill button-success">塞进简信发送</a>
					      </div>
                        </div> 
               	
                </div>
               
               <script type="text/javascript">
	             //朋友分享 参数
	               com_shareAppMessage_title='${order.title}',
	               com_shareAppMessage_content='${message}',
	               com_shareAppMessage_link='${shareAppMessageUrl}',
	               com_shareAppMessage_imgUrl='${config.imgDomainName}/${order.cardImage.folder }/${order.cardImage.name}_100x100.jpg';
		              
               	   Zepto(function($){
               		  
                       $(document).on('click','#shareit', function () {
                    	  $("#shareit").hide(); 
                    	});
                    
                       $.ajax({  
                           type:'POST',  
                           url:ctx+"/wechat/my/card/getToken?token="+token,  
                           success:function(result){
                        	   if(result.state!=1){
                        		   token ='';
                        	   }
                           }	
                       });
               	   });
                  
                  //图片浏览器 -照片
                	imgBrowArray = new Array();
                	imgBrowArray[0] = '${config.imgDomainName}/${image.folder }/${image.name }_y2000.jpg';
                	
                    //spa 注册
                  /*   if (wxjs) {
                    	 share();
                    	
	               	}  */
                    var token ='${cardToken}';
                    //ajax 注册
                	/* fshare = function(){ 
                		share();
                	}  */
                    
                
                </script>
                
                   <script type="text/javascript">
                    		var WXPayUrl = "${WXPayUrl}";
                    		//提交订单
                    		var metering = 0;//计次，用于生产订单号
                    		var orderId="${order.id}";
                    		 OrderShareSuccess.orderId='${order.id}';
                    		function createQrcode(p){
                    			//判断是不是PC端
                    			if(IsPC()){
                    				metering++;
//                    		 			$("#qrcode").click(); 
                    				window.open("${ctx}/work/chxt/account/payAlipayPCOrder?orderId="+orderId+"&metering="+metering);
//                    		 		window.location.href="${ctx}/work/chxt/account/payAlipayPCOrder?orderId="+orderId+"&metering="+metering;
//                    	 	 		createQrcodeWX();//微信支付
                    			}else{
                    				if(p){
                    					window.location.href=WXPayUrl;
                    				}else{
                    					window.location.href=aliPayUrl;
                    				}
                    			}
                    			
                    			
                    		}
                      </script>
            </div>
        </div>
    </div>
  </body>
    <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
  <script type="text/javascript">
	function shareApp(){
	   	 $("#shareit").show();
	     wx.ready(function(){
	    		//朋友分享 参数
	    	      com_shareAppMessage_title='${order.title}',
	    	      com_shareAppMessage_content='${message}',
	    	      com_shareAppMessage_link='${shareAppMessageUrl}',
	    	      com_shareAppMessage_imgUrl='${config.imgDomainName}/${order.cardImage.folder }/${order.cardImage.name}_100x100.jpg';
	              
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
	$(function(){
     	 $(document).on('click','.wx-img-brow',function () {
     		imgBrow();
      	  }); 
     });
  
  </script>
</html>
