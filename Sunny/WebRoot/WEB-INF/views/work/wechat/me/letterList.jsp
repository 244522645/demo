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
	<%@include file="/WEB-INF/views/work/common/static.jsp"%>
	<link rel="stylesheet" href="${ctx }/static/letter/css/me.css?t=new Date()">
</head>
<body>
	<div class="page-group"> 
        <div class="page page-current" id="me_letter">
       		<header class="bar bar-nav">
       			<a class="icon icon-left pull-left back " href="#" data-no-cache="true"></a>
			  <h1 class='title'>我的简信</h1>
			</header>
			<div class="content">
			  <div class="buttons-tab">
			    <a href="#tab11" id="shoudao_jianxin" class="tab-link <c:if test="${status==1}">active</c:if> button">收到的</a>
			    <a href="#tab22" id="songchu_jianxin" class="tab-link <c:if test="${status==2}">active</c:if> button">送出的</a>
			    <a href="#tab33" id="weisongchu_jianxin" class="tab-link <c:if test="${status==3}">active</c:if> button">未送出</a>
			  </div>
			  <div class="gdyg-content-block content-block">
			    <div class="tabs">
			      <div id="tab11" class="tab <c:if test="${status==1}">active</c:if> infinite-scroll">
			        <div class="content-block">
			        	<div class="shoudaoList_jianxin">
			        	</div>
			            <!-- 加载提示符 -->
			            <div class="infinite-scroll-preloader shoudao_jianxin">
			              <div class="preloader"></div>
			            </div>
			        </div>
			      </div>
			      <div id="tab22" class="tab <c:if test="${status==2}">active</c:if> infinite-scroll">
			        <div class="content-block ">
			        	<div class="songchuList_jianxin">
			        	</div>
				        <!-- 加载提示符 -->
			          	<div class="infinite-scroll-preloader songchu_jianxin">
			              <div class="preloader"></div>
			         	</div>
					</div>
			      </div>
			       <div id="tab33" class="tab <c:if test="${status==3}">active</c:if> infinite-scroll">
			       	 <div class="content-block">
			       	 	 <div class="weisongchuList_jianxin">
			       	 	 </div>
			       	 	 <!-- 加载提示符 -->
				         <div class="infinite-scroll-preloader weisongchu_jianxin">
				              <div class="preloader"></div>
				         </div>
					 </div>
			       </div>
			    </div>
			  </div>
			</div>
			<script type="text/javascript">
				status=${status};
			</script>
        </div>
    </div>
    <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
    <%@include file="/WEB-INF/views/work/wechat/me/routeJs.jsp" %>
</body>
</html>
