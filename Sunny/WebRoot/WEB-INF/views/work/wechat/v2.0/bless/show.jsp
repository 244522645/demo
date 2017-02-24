<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/common/static.jsp"%>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>预订成功-${config.siteName}</title>
    	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <style type="text/css">
    </style>
</head>
<body>
	 <div class="page-group">
        <div class="page birthdaymain yuding-share">
            <div class="content">
				<div class="daojishi">
					<span class="songming">距离张三生日还有</span><br />
					<span class="tianshu">
						<div class="heidi">
						</div>
					</span>
				</div>
				<div class="fenxiangtou">
					<img src="${ctx}/static/v2.0/img/touxiang4.png">
				</div>
				<div class="liwu">
					<span>小雨为您准备了一份神秘礼物，<br />
					请生日当天来这里领取！</span>
					<img src="${ctx}/static/v2.0/img/dalibao.png">
				</div>	
				<div class="shenmi">神秘礼物，正在赶制中，敬请期待！</div>
				<div class="saoma">
					<div class="saoma1">
						<span>扫描关注二维码，<br />
						偷窥一下“神秘礼物”</span>
						<img src="${ctx}/static/v2.0/img/erweima.png">
					</div>
				</div>
				<div class="zhuanfa">
					<img src="${ctx}/static/v2.0/img/zhuanfa.png" />
				</div>
        	</div> 
   		</div>
    <!-- popup, panel 等放在这里 -->
    	<div class="popup popup-chenggong">
		<div class="content-block heikuai">
		  	<img src="${ctx}/static/v2.0/img/xiantiao.png" />
		</div>
		<div class="diandian">点</div>
		<div class="jiji">击</div>
		<div class="fenfen">分</div>
		<div class="xiangxiang">享</div>
	</div>
</body>
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<script type="text/javascript">
$(function(){
	$.init();
});

</script>
 <script>
    
    share={
    	that:this,
    		init : function(){
    			that=this;
    			var t = 1
				var t = share.shuzibuling(t,3);	
    			var arr = (t+'').split('');
    			var shijian='<div class="shubai">'+arr[0]+'</div>'+
							'<div class="shushi">'+arr[1]+'</div>'+
							'<div class="shuge">'+arr[2]+'</div>';
							$(".heidi").append(shijian);
				$(".zhuanfa").on("click",function(){
			  $.popup('.popup-chenggong');
		})
    		},
    	shuzibuling:function(num,n){
    		return (Array(n).join(0) + num).slice(-n);
    	},
    }
    share.init();
    
    </script>
<script type="text/javascript">
//页面跳转
$(document).on("pageInit", function(e, pageId, $page) {
});
</script>
</html>