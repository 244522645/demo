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
	<style>
	    	
	    	.jieshu{
	    		line-height: 5rem;
	    		text-align: center;
	    		font-size: 2rem;
	    		color: #FFFFFF;
	    		margin-top: 105%;
	    	}
	    	.songzhuf{
	    		line-height: 2rem;
	    		text-align: center;
	    		font-size: 1rem;
	    		color: #FFFFFF;
	    	}
	    	.sonzhufanniu{
	    		margin-right: 35%;
	    		margin-top:5% ;
	    		margin-left: 35%;
	    		color:#c42329;
	    		font-size: 1rem;
	    		line-height: 2rem;
	    		background: #FFFFFF;
	    		text-align: center;
	    		border-radius:0.2rem ;
	    	}
    </style>
  </head>
	<body class="v2">
		 <div class="page-group">
	        <!-- 单个page ,第一个.page默认被展示-->
	        <div class="page birthdaymain yuding-share">
	            <!-- 标题栏 -->
	
	            <!-- 工具栏 -->
	
	
	            <!-- 这里是页面内容区 -->
	            <div class="content beijingtupin">
					
				<div class="jieshu">
					活动已结束
				</div>
				<div class="songzhuf">您可以为朋友送去一份生日祝福</div>
				
					<div class="sonzhufanniu">
						<a id="alink" href="${ctx}/wechat/index" style="color:#c42329;">
							送祝福
						</a>
					
					</div>
					
	        	</div> 
	   </div>
	    <div class="panel-overlay"></div>
	</body>
   <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
   <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
   <script type="text/javascript" src="${ctx }/static/v2.0/js/swiper-3.4.1.jquery.min.js"></script>
  <script>
  
</script>
</html>
