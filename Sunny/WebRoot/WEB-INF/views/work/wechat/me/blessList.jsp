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
        <div class="page page-current" id="me_bless">
       		<header class="bar bar-nav">
       			<a class="icon icon-left pull-left back " href="#" data-no-cache="true"></a>
			  <h1 class='title'>我的日出明信片</h1>
			</header>
			<div class="content">
			  <div class="buttons-tab">
			    <a href="#tab1" id="shoudao" class="tab-link <c:if test="${status==1}">active</c:if> button">收到的</a>
			    <a href="#tab2" id="songchu" class="tab-link <c:if test="${status==2}">active</c:if> button">送出的</a>
			    <a href="#tab3" id="weisongchu" class="tab-link <c:if test="${status==3}">active</c:if> button">未送出</a>
			  </div>
			  <div class="gdyg-content-block content-block">
			    <div class="tabs">
			      <div id="tab1" class="tab <c:if test="${status==1}">active</c:if> infinite-scroll">
			        <div class="content-block">
			        	<div class="shoudaoList">
			        	
			        	</div>
			            <!-- 加载提示符 -->
			            <div class="infinite-scroll-preloader shoudao">
			              <div class="preloader"></div>
			            </div>
			        </div>
			      </div>
			      <div id="tab2" class="tab <c:if test="${status==2}">active</c:if> infinite-scroll">
			        <div class="content-block ">
			        	<div class="songchuList">
			        	
			        	</div>
				        <!-- 加载提示符 -->
			          	<div class="infinite-scroll-preloader songchu">
			              <div class="preloader"></div>
			         	</div>
					</div>
			      </div>
			       <div id="tab3" class="tab <c:if test="${status==3}">active</c:if> infinite-scroll">
			       	 <div class="content-block ">
			       	 	 <div class="weisongchuList">
			        	
			        	 </div>
			       	 	 <!-- 加载提示符 -->
				         <div class="infinite-scroll-preloader weisongchu">
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
