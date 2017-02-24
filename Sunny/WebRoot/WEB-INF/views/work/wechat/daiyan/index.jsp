<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
    <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/common/static.jsp"%>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>阳光代言</title>
   	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="我为阳光代言，传递正能量！">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="${ctx}/static/daiyan/css/style.css?v=2.0" />
        
     <script type="text/javascript">
	//朋友圈 参数
	 var com_share_title="我是${daiyan.userId.wechatNickname},我为阳光代言,将今日阳光打包送给你",
	 com_share_content="",
	 com_share_link="${config.domainName}/wechat/activity/daiyan?dyid=${daiyan.id}",
 	 com_share_imgUrl="${daiyan.userId.wechatHeadUrl}",
	 domainName="${config.domainName}";
	 
	 </script>   
  </head>
  <body>
  <div class="zhezhao" style="display:none;">
  	<img src="${ctx}/static/daiyan/img/yindao2.png">
  	<br><br>
  	<h2>恭喜成功代言！</h2>
  </div>
    <div class="page-group">
        <div class="page page-current gdyg-page">
    		<header class="bar bar-nav" style="display:none">
    		  <a class="icon icon-head pull-left"><img src="${daiyan.userId.wechatHeadUrl}"></a>
			  <h1 class="title open-panel" data-panel='#panel-left-gdyg'>今日第一缕阳光</h1>
			</header>
			<div class="content" style="display:none">
				<c:forEach items="${photos}" var="entity" varStatus="status">
				<div class="card demo-card-header-pic dyphoto">
				    <div valign="bottom" class="card-header color-white no-border no-padding">
				      <img class='card-cover' dt="${config.imgDomainName}/${entity.imgId.folder}/${entity.imgId.name}_y1080.jpg" src="${config.imgDomainName}/${entity.imgId.folder}/${entity.imgId.name}_348x232.jpg" alt="">
				      <div class="card_font">
				      	<%-- <p><tags:todayDateForamt dateTime="${entity.shootingTime }" /> </p> --%>
				      	<p><fmt:formatDate value="${entity.shootingTime }" pattern="MM/dd"/></p>
				      	<p>${entity.provinceF} • ${entity.title}</p>
				      </div>
				    </div>
				  </div>
				</c:forEach>
				 <div class="card demo-card-header-pic">
				    <div valign="bottom" class="card-header color-white no-border no-padding">
				      <img class='card-cover' src="${ctx}/static/daiyan/img/erweima_tt.jpg" alt="">
				      <div class="ewm"> 
				      <c:if test="${daiyan.share eq 0 }">
					  <img class="ewmimg-no"  src="${ctx}/static/daiyan/img/erweima_b.jpg" />
					   <img class="ewmimg" style="display:none;" src="data:image/gif;base64,${daiyan.qrcode.img }" /> 
					  </c:if>
					  <c:if test="${daiyan.share != 0 }">
					  <img class="ewmimg" src="data:image/gif;base64,${daiyan.qrcode.img }" /> 
					  </c:if>
				      </div>
				    </div>
				  </div>
			</div>
			<div class="top_content theme">
				<div id="wrapper">
					<div id="bg"></div>
					<!--<div id="overlay"></div>-->
					<div id="main"> 
							<header id="header">
								<img class="head" src="${daiyan.userId.wechatHeadUrl}" />
								<div class="name">我是${daiyan.userId.wechatNickname}</div>
								<h1>我为阳光代言</h1>
								<p>我在这里与全国各地第一缕阳光邂逅</p>
								<p class="erweima">
								  <c:if test="${daiyan.share eq 0 }">
								  <img class="ewmimg-no" src="${ctx}/static/daiyan/img/erweima_b.jpg" />
					   			  <img class="ewmimg" style="display:none;" src="data:image/gif;base64,${daiyan.qrcode.img }" /> 
								  </c:if>
								  <c:if test="${daiyan.share != 0 }">
								  <img src="data:image/gif;base64,${daiyan.qrcode.img }" /> 
								  </c:if>
								  
								</p>
							</header>
							<footer id="footer">
								<i class="up"></i>
								<span class="copyright">查看今日阳光</span>
							</footer>
					</div>
				</div> 
			</div>
        </div>
    </div>
    <script>
    	$(".icon-head").tap(function(){
    	  	$(".top_content").removeClass("modal-out").addClass("modal-in");
    	});
    	$(".title ").on("swipeDown",function(event){
    	  	$(".top_content").removeClass("modal-out").addClass("modal-in");
    	});
    	$(".top_content ").on("touchstart",function(event){
    		  $(".native-scroll").css("overflow","hidden");
    	});
    	
    	$(".top_content ").on("tap",function(event){
    		$(".bar").show();
    		$(".content").show();
  		  	$(".top_content").removeClass("modal-in").addClass("modal-out");
  		  	setTimeout(function(){
					    $(".native-scroll").css("overflow","auto");
			    },500);
    	});
    	/* $(".top_content ").on("swipeUp",function(event){
    		event.preventDefault();
    		$(".bar").show();
    		$(".content").show();
  		  	$(".top_content").removeClass("modal-in").addClass("modal-out");
  		  	setTimeout(function(){
					    $(".native-scroll").css("overflow","auto");
			    },500);
    	}); */
    	$(".top_content").bind('touchstart', touches);
		$(".top_content").bind('touchend', touches);
		$(".top_content").bind('touchmove', touches);
		
	  var startY;
	  var isOpen = true;
		function touches(e){
				e.preventDefault();
				switch(e.type) {
						case 'touchstart': 
								startY = e.touches[0].clientY;
								break;
						case 'touchend':
								break;
						case 'touchmove':
								 var moveY = e.touches[0].clientY;
							   if(((moveY-startY)<-100)&&isOpen){
										$(".top_content").removeClass("modal-in").addClass("modal-out");
										setTimeout(function(){
										    $(".native-scroll").css("overflow","auto");
										    isOpen = true;
								    },500);
										  $(".bar").show();
								    		$(".content").show();
										isOpen = false;
								 }
								break;
				}
		}
    	
    	SetTheme  = {
    		  theme : ['theme1','theme2','theme3','theme4'],
    			layout : function(theme){
    				  for(i=0;i<this.theme.length;i++){
    				  	if(theme==this.theme[i]){
    				  			$(".top_content").removeClass('theme2');
    				  			$(".top_content").removeClass(theme).addClass(theme);
    				  			return;
    				  	}
    				  }
    			},
    			init:function(theme){
    				this.layout(theme)
    			}
    	}
    	
    	
    	Zepto(function($) {    
			 SetTheme.init("${daiyan.theme }");
			 $(".dyphoto .card-cover").click(function(){
				 	var imgs=$(this).attr('dt');
					wx.ready(function(){
						 wx.previewImage({
					  	    current: imgs, // 当前显示图片的http链接
					  	    urls: new Array(imgs), // 需要预览的图片http链接列表
					  	});
					});
			 });
		}); 
    </script>
  </body>
<script type="text/javascript">
//第一次打开  提示并 功能引导
$(function(){
	var firstdy='${firstdy}';
	if(firstdy!=''){
		$(".zhezhao").show();
		$(".zhezhao ").on("tap",function(event){
			$(".zhezhao").hide();
    	});
	}
	setTimeout(function() { 
		$(".zhezhao").remove();
		},10000) ;
	
	
  });
 //回调覆盖
 var daiyan_me='${daiyan_me}';
 wxShare.success = function(){
  $.ajax({  
       type:'POST',  
       url:ctx+"/wechat/activity/daiyanShare?dyid=${daiyan.id}",  
       success:function(result){
    	   if(result.state==1){
    		   if(result.t!=''){
    			   if(daiyan_me!='')
    			   $.alert('您已代言成功，获得一张“阳光卡”，前往“个人中心”查看');
    		   }else{
    			  // if(daiyan_me!='')
    			  // $.alert('您已代言成功');
    		   }
    		   $(".ewmimg-no").hide();
		  	   $(".ewmimg").show();
    	   }
       	
       }	
   });
}
</script>
 <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<%@include file="/WEB-INF/views/work/common/pageInit.jsp" %>
</html>
