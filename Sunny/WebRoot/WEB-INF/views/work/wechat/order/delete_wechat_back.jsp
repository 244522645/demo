<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>请关注-${config.siteName}</title>
    <meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
<style>
.t_section{margin: 10% auto;width:95%;text-align: center;}
.t_section img{width:60%}
.page {background-color: #FEFFEE}
</style>
<body>
 <div class="page-group">
    <div class="page">
        <!-- 标题栏 -->
        <header class="bar bar-nav">
            <button class="button button-link button-nav pull-left">
                <a href="item.html" class="icon icon-left"></a>
            </button>
            <h1 class="title">关注给点儿阳光</h1>
        </header>
        <!-- 内容 -->
        <div class="content">
		<!--提示内容-->
			<section class="t_section t_center">
				<div>
					<img src="${ctx}/static/images/sun/qrcode_for_gh.jpg"/>
				</div>
				<p>长按上方二维码关注【给点儿阳光】↑↑↑</p>
				<p>${content}</p>
			</section>
        </div>
    </div>
</div>
<script>
document.body.addEventListener("touchstart",function(){});
</script>

</body>
  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
</html>