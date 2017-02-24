<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/wechat/v3/common/static.jsp"%>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>闻鸡起伍</title>
   	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
</head>
<body>
	 <div class="page-group">
	<!-- 单个page ,第一个.page默认被展示-->
	 <div class="page" id="share">


		<!-- 这里是页面内容区 -->
		<div class="content main" id="egg">
			<img class="main_bg" src="<%=path%>/static/v3/img/logo.png" alt="">
			<div class="egg" id="chicken">
				<c:if test="${requestScope.crowUserInfo.sublevel<0 }">
					<img src="<%=path%>/static/v3/img/err.png" alt="">
				</c:if>
				<c:if
					test="${0<=requestScope.crowUserInfo.sublevel&&requestScope.crowUserInfo.sublevel<3}">
					<img src="<%=path%>/static/v3/img/egg.png" alt="">
				</c:if>
				<c:if
					test="${3<=requestScope.crowUserInfo.sublevel&&requestScope.crowUserInfo.sublevel<6}">
					<img src="<%=path%>/static/v3/img/egg_1.png" alt="">
				</c:if>
				<c:if
					test="${6<=requestScope.crowUserInfo.sublevel&&requestScope.crowUserInfo.sublevel<9}">
					<img src="<%=path%>/static/v3/img/egg_2.png" alt="">
				</c:if>
				<c:if
					test="${9<=requestScope.crowUserInfo.sublevel&&requestScope.crowUserInfo.sublevel<12}">
					<img src="<%=path%>/static/v3/img/egg_3.png" alt="">
				</c:if>
				<c:if
					test="${12<=requestScope.crowUserInfo.sublevel&&requestScope.crowUserInfo.sublevel<15}">
					<img src="<%=path%>/static/v3/img/egg_4.png" alt="">
				</c:if>
				<c:if
					test="${15<=requestScope.crowUserInfo.sublevel&&requestScope.crowUserInfo.sublevel<18}">
					<img src="<%=path%>/static/v3/img/egg_5.png" alt="">
				</c:if>
				<c:if
					test="${18<=requestScope.crowUserInfo.sublevel&&requestScope.crowUserInfo.sublevel<21}">
					<img src="<%=path%>/static/v3/img/egg_6.png" alt="">
				</c:if>
				<c:if test="${requestScope.crowUserInfo.sublevel==21}">
					<img src="<%=path%>/static/v3/img/chicken.png" alt="">
				</c:if>
				<div class="egg_content">
					<figure class="header">
						<img src="${requestScope.crowUserInfo.user.wechatHeadUrl }" alt="">
					</figure>
					<p class="name">${requestScope.crowUserInfo.user.wechatNickname }</p>
					<div class="code">
						
						<div class="code-img">
							<img src="data:image/png;base64,${requestScope.crowUserInfo.sunQrcode.img}" alt="">
						</div>
						
					</div>

				</div>
				<h3 class="title_after_G">长按二维码接受挑战</h3>
				<div class="egg_text"> 每天晨跑2.5Km,9点之前将运动截图发送至平台，审核通过，视为打卡成功！</div>
			</div>

			<div class="main_content">
				<p class="list" id="egglist">
					坚持天数： <em>${requestScope.crowUserInfo.alldays }</em> <br>
					影响人数： <em>${requestScope.crowUserInfo.impel }</em><br> 
					挑战名额： <em>${requestScope.count }</em> 
					
				</p>

			</div>

		</div>
	</div>

</div>
	<!-- Left Panel with Reveal effect -->
	<script type="text/javascript" src="<%=path%>/static/v3/js/sm.min.js"></script>
	<script type="text/javascript" src="<%=path%>/static/v3/js/jquery.js"></script>
	
</body>
</html>