<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/wechat/v3/common/static.jsp"%>
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
  <body class="v2">
  	 <div class="page-group">
  	 	<div class="page">
            <div class="content bcakcolor_l">
				<div class="banner_l">
					<div class="portrait_l">
						<img src="${userInfo.wechatHeadUrl }">
					</div>
					<div class="xinxi_l">
						<span class="xinxi_name_l">${userInfo.wechatNickname }</span>
						<br />
						<span class="xinxi_yver_l">余额：${balance }</span>
						<span class="xinxi_yver_l">元</span>
					</div>
					<div class="tixian_l">
						<a href="${ctx}/wechat/v3/myAccount/withdrawCashPage" class="external">提现</a>
					</div>
				</div>	
				<div class="zhanghuxiangqing_l">
					<span class="zhanghuxiangqing_l_xiangq">账户详情</span>
				</div>
                <div class="mingxiliebiao_l">
                	
                </div>  
			</div>
            </div>
  	 </div>
    <div class="panel-overlay"></div>
  </body>
 
  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/v3/myAccount/js_myAccount.jsp"%>
<script>

</script> 
</html>
