<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>阳光卡</title>
    <meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
  </head>
<body>
	 <div class="page-group">
		  <div class="page page-current" id="myCards">
		    
			<!-- 你的html代码 -->
        		<div class="content pull-to-refresh-content infinite-scroll infinite-scroll-bottom"  style="padding:  0.5rem;">
	           		 <!--   默认的下拉刷新层 -->
				    <div class="pull-to-refresh-layer">
				        <div class="preloader"></div>
				        <div class="pull-to-refresh-arrow"></div>
				    </div>
				    <div class="list-block" style="margin: 0;">
		             	<ul class="list-container" style="position: inherit;background: transparent;">
						<!--  <li><div style="text-align: right;font-size:0.5rem;color:#7a7a7a;margin-right:0.5rem">使用规则？</div></li> -->
					   		
					   </ul>
				  </div>
				  <!-- 加载提示符 -->
		         <div class="infinite-scroll-preloader" style="display: none;">
		             <div class="preloader"></div>
		         </div>
			 </div>
			 <!--结束了-->
		 </div>
	</div>
</body>
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<%@include file="/WEB-INF/views/work/common/pageInit.jsp" %>
</html>

