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
        <!-- 单个page ,第一个.page默认被展示-->
        <div class="page" id="me_index">
            <!-- 标题栏 -->

            <!-- 工具栏 -->


            <!-- 这里是页面内容区 -->
            <div class="content birthdaymain">
				
				<div class="zhiti">
					<div class="content-block">
	                	<div class="touxiangname">
	                		<div class="touxiang">
	                			<img src="${userInfo.wechatHeadUrl }">
	                		</div>
	                		<div class="mingzi">${userInfo.wechatNickname }</div>
	                	</div>
	                	<a href="${ctx}/wechat/v2/me/myGiftList" class="external">
		                	<div class="jinian">
		                		<div class="jinian1">
		                			<img src="${ctx}/static/v2.0/img/jinnian1.png">
		                		</div>
		                		
		                		<span class="ka">我的礼物</span>
		                		<span class="icon icon-right kaoyou"></span>
		                	</div>
	                	</a>
	                	<a href="${ctx}/wechat/v2/relation/relationList" class="external">
		                	<div class="guanli">
		                		<div class="jinian1">
		                			<img src="${ctx}/static/v2.0/img/guanli1.png">
		                		</div>
		                		
		                		<span class="ka">生日管理</span>
		                		<span class="icon icon-right kaoyou"></span>
		                		
		                	</div>
	                	</a>
	                	<a href="${ctx}/wechat/v2/sunnyCard/cardList" class="external">
		                	<div class="guangka">
		                		<div class="jinian1">
		                			<img src="${ctx}/static/v2.0/img/guangka.png">
		                		</div>
		                		
		                		<span class="ka">我的阳光卡</span>
		                		<span class="icon icon-right kaoyou"></span>
		                	</div>
	                	</a>

                	</div>
            </div>
        </div>
    </div>
</body>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
</html>
