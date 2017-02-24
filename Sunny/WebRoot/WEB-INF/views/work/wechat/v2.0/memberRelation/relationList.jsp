<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
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
     <style type="text/css">
     .page-relation{
      display:block;
     }
     </style>
  </head>
<body  class="v2">
  
  	 <div class="page-group">
        <!-- 单个page ,第一个.page默认被展示-->
        <div class="page page-relation" id="relationList">
            <!-- 标题栏 -->

            <!-- 工具栏 -->
           <nav class="bar bar-tab bir">
			<a class="tab-item external active" href="${ctx}/wechat/v2/relation/addRelation">
		      <span class="tab-label">新增朋友</span>
		    </a>
		   </nav>

            <!-- 这里是页面内容区 -->
            <div class="content">
				<div class="TopWriting">
					选择一位朋友为TA定制一缕阳光
				</div>
				<div class="kongbai" style="display: none">
					你还没有任何朋友生日信息哦<br />点击下面的“新增朋友“添加吧
				</div>
				<div id="content">
					<!-- 关系列表内容 
					<input type="button" onclick="loadData();">xxxxx</input>  -->
               </div> 
            </div>
        </div> 
   </div>
    <!-- popup, panel 等放在这里 -->
    <div class="panel-overlay"></div>
  </body>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/v2.0/memberRelation/js_memberRelation.jsp"%>
</html>
