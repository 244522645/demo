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
  </head>
  <body>
    <body>
		<div class="page-group">
			<div class="page" id='searchResult'>
				<!-- 标题栏 -->
				<header class="bar bar-nav">
					<a class="icon sun-icon-left pull-left"  href="${ctx}/wechat/index"></a>
					
					<!-- 搜索框 -->
						<div class="searchbar sun-sreach">
							<a class="searchbar-cancel " href="${ctx}/wechat/index/search?search=${search }">取消</a>
							
							<div class="search-input back">
							<a  href="${ctx}/wechat/index/search?search=${search }">
								<label class="icon icon-search" for="search"></label>
								<input type="search" class="back"  name="search" id='search' readonly="readonly" placeholder='${search }' />
							</a>
							</div>
							
						</div>
					
					
				</header>
				
				<!-- 搜索内容选项 -->
				<div class="content">
					<div class="tabs">
						 <div id="tab1" class="tab active infinite-scroll">
		                    <div class="search-list-container" style="height: 100%;">
		                    	
		                    </div>
		                    <div class="sun-no-data" >
								    <div class="content-padded">
								       <img alt="" class="sun-no-data-img" src="${ctx }/static/images/sun/sun-no-data.png">
									  <p>抱歉，没有找到相关信息</p>
									</div>
							</div>
		                    <!-- 加载提示符 -->
					        <div class="search-infinite-scroll-preloader">
					            <div class="preloader"></div>
					        </div>
					        <!-- 没有了 -->
					        <div class="search-infinite-scroll-nodata" style="display:none;">
					            <p>没有了</p>
					        </div>
		                </div>
		                <%-- <div id="tab2" class="tab infinite-scroll">
		                    <div class="list-container">
		                    	<div class="sun-no-data" >
								    <div class="content-padded">
								       <img alt="" src="${ctx }/static/images/sun/sun-no-data.png">
									  <p>抱歉，没有找到相关信息</p>
									</div>
								</div>
		                    </div>
				            <!-- 加载提示符 -->
					        <div class="infinite-scroll-preloader">
					            <div class="preloader"></div>
					        </div>
				        </div> --%>
	              </div>
				</div>
				
			</div>
		</div>
  </body>
  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
     <%@include file="/WEB-INF/views/work/common/pageInit.jsp" %>
</html>
