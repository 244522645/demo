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
       <div class="page" id="cardInstructions">
          <div class="content">
				<div class="buttons-tab xuanxiangk">
			      <a href="#tab1" class="tab-link button active">使用说明</a>
			      <a href="#tab2" class="tab-link button">使用明细</a>
			    </div>
			    <div class="tabs">
			      <div id="tab1" class="tab active">
			        <div class="content-block">
			        		<div class="shuoming">
			        			1. "阳光卡”是“给点儿阳光”发行的通用   卡，可用于在平台上购买“阳光祝福”。<br />
								2. “阳光卡”包括线上发售的电子卡和线下    发售的实体卡；电子卡可直接用于支付，实体卡需要绑定微信账号后才能使用。<br />
								3. 实体卡绑定流程：关注“给点儿阳光”公众号，打开”会员中心“--->”我的阳光卡"--->”绑定阳光卡“，输入阳光卡账号、密码进行绑定。每张实体卡，只能绑定一个微信账号。<br />
								4.使用方式：为朋友预定阳光祝福，填写完必要信息，在最后的支付环节选择”阳光卡支付“即可。
			        		</div>
			        </div>
			      </div>
			      <div id="tab2" class="tab">
			        <div class="content-block">
			          	<div class="mingxi">
			          	</div>
			        </div>
			      </div>
			    </div>
        	</div>
       </div> 
    </div>
</body>
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/v2.0/card/js_card.jsp" %>
</html>
