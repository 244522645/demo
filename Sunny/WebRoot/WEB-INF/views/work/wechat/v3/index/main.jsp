
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/wechat/v3/common/static.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>个人主页</title>
</head>
<body>
	<!-- 单个page ,第一个.page默认被展示-->
	<div class="page main" id="main">
		<nav class="bar bar-tab" id="main_tab">
			<a class="tab-item external active"
				href="${ctx }/wechat/v3/crow/pkone"> <span class="icon icon_1"></span>
				<span class="tab-label">对战</span>
			</a> <a class="tab-item external"  href="javascript:void(0);" id="douji"> <span
				class="icon icon_2"></span> <span class="tab-label">斗鸡</span>
			</a> <a class="tab-item external"
				href="${ctx }/wechat/v3/crow/punch"> <span class="icon icon_3"></span>
				<span class="tab-label">进度</span>
			</a> <a class="tab-item external"
				href="${ctx }/wechat/v3/myAccount"> <span class="icon icon_4"></span>
				<span class="tab-label">账户</span>
			</a>
		</nav>
		
		<!-- 这里是页面内容区 -->	
		<div class="content content_bg" id="egg">
			<img class="main_bg" src="<%=path%>/static/v3/img/logo.png" alt="">
			<div class="main_content">
			   <div style="width: 100%;height: 30%;padding-top: 0.3rem;">
				   <div class="list" id="egglist">
						<p>坚持天数： <em>${requestScope.crowUserInfo.alldays }&nbsp;</em> </p>
						<p>影响人数： <em>${requestScope.crowUserInfo.impel }&nbsp;</em></p>
					</div>
			   </div>
				
				<figure class="header">
					<img src="${requestScope.crowUserInfo.user.wechatHeadUrl}" alt="">
				</figure>
				<p class="name">${requestScope.crowUserInfo.user.wechatNickname }</p>
				<h3>挑战规则</h3>
				<p class="rule">
					每人拿出5元作为挑战金，连续5天坚持晨跑则<br> 挑战成功，失败者扣除挑战金，<br>
					成功者赢得失败者的挑战金！<br>

				</p>
				<a href="${ctx }/wechat/v3/crow/punch" class="btn_start external"></a>

			</div>
			<img class="main_bg_2" src="<%=path%>/static/v3/img/main_img.png" alt="">
		</div>
	</div>


	<script type='text/javascript' src='${ctx}/static/public/sm.min.js' charset='utf-8'></script>
 <script type='text/javascript' src='${ctx}/static/public/sm-extend.min.js' charset='utf-8'></script>
 <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
 <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
	<script>
		$(function() {
			$(document).on('click','#douji', function () {
				$.toast('即将开放');
			 })
		});
		
		
		$(document).on('click','.red', function () {
			$.popup('.popup-yaoqing_l');
		 })
		 $(document).on('click','.fenxiang_l', function () {
			$.closeModal('.popup-yaoqing_l');
		 })
	</script>
</body>
</html>