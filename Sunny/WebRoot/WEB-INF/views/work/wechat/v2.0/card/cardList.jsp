<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>给点儿阳光</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <link rel="stylesheet" href="${ctx}/static/v2.0/css/swiper-3.4.1.min.css" />
</head>
<body  class="v2">
	<div class="page-group"> 
       <div class="page" id="cardList">
          <div class="content infinite-scroll infinite-scroll-bottom" id="cards_content">
				
		 </div>
		 <!-- 工具栏 -->
		 <nav class="bar bar-tab">
		   <a class="tab-item bangding" href="${ctx}/wechat/v2/sunnyCard/cardBinding">
		     <span class="tab-label">绑定阳光卡</span>
		   </a>
		   <a class="tab-item shuom" href="${ctx}/wechat/v2/sunnyCard/cardInstructions">
		     <span class="tab-label">使用说明</span>
		   </a>
		</nav>
       </div> 
    </div>
</body>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/v2.0/card/js_card.jsp" %>
</html>
