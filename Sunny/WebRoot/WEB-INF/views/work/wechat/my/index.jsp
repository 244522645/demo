<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
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
		<div class="page-group">
			<div class="page page-current"  id='myIndex'>
				<!-- 你的html代码 -->
				<div class="content pad-mar" style="padding:  0rem;" data-type="">
					<div class="my-page-top">
					  <c:if test="${not empty userInfo.wechatHeadUrl}">
    					<div class="tx">
							<img alt="" src="${userInfo.wechatHeadUrl}">
						</div>
						<div class="my-name">${userInfo.wechatNickname}</div>
    				  </c:if> 
    				  <c:if test="${ empty userInfo.wechatHeadUrl}">
    					<div class="tx">
							<img alt="" src="${ctx}/static/images/sun/logo-icon.png">
						</div>
						<div class="my-name">给点儿阳光</div>
    				  </c:if>
					</div>
					<a href="${ctx}/wechat/my/zhufu">
						<div class="my-item">

							<div class="xiaodian color-blue"></div>
							<div class="wenzi">
								明信片
							</div>
							<div class="shuzi" id="zhufu">
							</div>

						</div>
					</a>
					<a href="${ctx}/wechat/my/letters">
						<div class="my-item">
							<div class="xiaodian color-green"></div>
							<div class="wenzi">
								简信
							</div>
							<div class="shuzi" id="letters">
							</div>
						</div>
					</a>
					<a href="${ctx}/wechat/my/cards">
						<div class="my-item">
							<div class="xiaodian color-red"></div>
							<div class="wenzi">
								阳光卡
							</div>
							<div class="shuzi" id="cards">
							</div>
						</div>
					</a>
				</div>

				<!--结束了-->
			</div>
		</div>
	</body>
    <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  	<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
    <%@include file="/WEB-INF/views/work/common/pageInit.jsp" %>
</html>
