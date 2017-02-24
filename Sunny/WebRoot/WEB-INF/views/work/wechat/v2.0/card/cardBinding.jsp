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
  	<link rel="stylesheet" href="${ctx}/static/v2.0/css/swiper-3.4.1.min.css" />
</head>
<body  class="v2">
	<div class="page-group"> 
       <div class="page" id="cardBinding">
          <div class="content">
				<div class="zixi">请一定要仔细填写</div>
				<div class="txkh">
					<div class="khwz">卡号：</div>
					<div class="khnr">
						<input id="cardNo" type="number" placeholder="请输入卡号" />
					</div>
				</div>
				<div class="txkh">
					<div class="khwz">密码：</div>
					<div class="khnr">
						<input id="password" type="number" placeholder="请输入密码" />
					</div>
				</div>	
				<div class="tijiaoanniu">确定</div>
        	</div>
       </div>
       
       <div class="popup popup-shibai">
		  <div class="content-block heikuai close-popup"></div>
			  <div class="baikuang2">
			  	<div class="cuowu"><img src="${ctx}/static/v2.0/img/bangdingshibai.png"></div>
			  	
			  </div>
		</div>
		<div class="popup popup-chenggong">
		  <div class="content-block heikuai close-popup"></div>
			  <div class="baikuang2 bangdingchenggong">
			  	<div class="duei"><img src="${ctx}/static/v2.0/img/huangdan.png"></div>
			  </div>
		</div>
    	<div class="panel-overlay"></div>
        
    </div>
    
  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/v2.0/card/js_card.jsp" %>
</body>
</html>
