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
	<link rel="stylesheet" href="${ctx }/static/letter/css/me.css?v=2.0">
	<script> 
	    $(function(){
	    	//这里判断是否是安卓和ios  修改一个 样式的bug
		    var ua = navigator.userAgent.toLowerCase();	
			if (/iphone|ipad|ipod/.test(ua)) {
				//$(".gdyg-list-block").addClass("az-gdyg-list-block");
			} else if (/android/.test(ua)) {
				$(".gdyg-list-block").addClass("az-gdyg-list-block");
			}
	    });
	</script>
</head>
<body>
	<div class="page-group"> 
        <div class="page page-current" id="me_index">
			<div class="content">
			  <div class="list-block contacts-block gdyg-list-block ">
			  	<header class="myCenter">
					<img src="${userInfo.wechatHeadUrl }"> 
					<div>${userInfo.wechatNickname }</div>
				</header>
			    <div class="list-group">
			      <ul>
			        <li class="list-group-title"> <i class="icon icon-zf-ico"></i>日出明信片</li>
			        <li class="item-content item-link">
			        	<a href="${ctx}/wechat/me/blessList?status=1" data-no-cache="true">
					        <div class="item-inner">
					          <div class="item-title">收到</div>
					          <div class="item-after">${brcount}张</div>
					        </div> 
				        </a>
				    </li>
			        <li class="item-content item-link">
			     	    <a href="${ctx}/wechat/me/blessList?status=2" data-no-cache="true">
					        <div class="item-inner">
					          <div class="item-title">送出</div>
					          <div class="item-after">${bscount}张</div>
					        </div>
				        </a>
				    </li>
				    <li class="item-content item-link">
				     	<a href="${ctx}/wechat/me/blessList?status=3" data-no-cache="true" >
					        <div class="item-inner">
					          <div class="item-title">未发送</div>
					          <div class="item-after">${bnscount}张</div>
					        </div>
				     	</a>
				    </li>
			      </ul>
			    </div>
			    <div class="list-group" style="display: none">
			      <ul>
			        <li class="list-group-title"><i class="icon icon-xf-ico"></i>简信</li>
			        <li class="item-content item-link">
			        	<a href="${ctx}/wechat/me/letterList?status=1" data-no-cache="true">
					        <div class="item-inner">
					          <div class="item-title">收到</div>
					          <div class="item-after">${lrcount}封</div>
					        </div>
				        </a>
				    </li>
			        <li class="item-content item-link">
			       	   <a href="${ctx}/wechat/me/letterList?status=2" data-no-cache="true">
				        <div class="item-inner">
				          <div class="item-title">送出</div>
				          <div class="item-after">${lscount}封</div>
				        </div>
				       </a>
				    </li>
				    <li class="item-content item-link">
				       <a href="${ctx}/wechat/me/letterList?status=3" data-no-cache="true">
				        <div class="item-inner">
				          <div class="item-title">未发送</div>
				          <div class="item-after">${lnscount}封</div>
				        </div>
				       </a>
				    </li>
			      </ul>
			    </div>
			    
			    
			    <div class="list-group">
			      <a href="${ctx}/wechat/relation/relationList" class="external">
			       	 生日管理
			      </a> 
			    </div>
			    
			    
			    
			    <div class="list-group">
			      <ul>
			        <li class="list-group-title"><i class="icon icon-card-ico"></i>阳光卡</li>
			        <li class="item-content item-link">
			        	<a href="${ctx}/wechat/me/cardList" data-no-cache="true">
					        <div class="item-inner">
					          <div class="item-title">我的阳光卡</div>
					          <div class="item-after">${ccount}张</div>
					        </div> 
				        </a>
				    </li>
			      </ul>
			    </div>
			 </div>
		   </div>
        </div>
    </div>
    <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
    <%@include file="/WEB-INF/views/work/wechat/me/routeJs.jsp" %>
    <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
</body>
</html>
