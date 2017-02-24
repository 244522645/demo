
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
<title>闻鸡起伍</title>
<link rel="stylesheet" href="<%=path%>/static/v3/css/sm,min.css" />
<link rel="stylesheet" href="<%=path%>/static/v3/css/index.css" />
<script type="text/javascript">
//朋友圈 参数
var com_share_title="闻鸡起伍",
com_share_content="一年之计在于春，一日之计在于晨！欢迎参加“闻鸡起伍”挑战！",
com_share_link="${share}",
domainName="闻鸡起伍",
com_share_imgUrl="${userInfo.wechatHeadUrl}";
</script>
</head>
<body>
<div class="page-group">
	<!-- 单个page ,第一个.page默认被展示-->
	<div class="page" id="main">
		<nav class="bar bar-tab" id="main_tab">
			<a class="tab-item external active"
				href="${ctx }/wechat/v3/crow/pkone"> <span class="icon icon_1"></span>
				<span class="tab-label">对战</span>
			</a> <a class="tab-item external" href="javascript:void(0);" id="douji"> <span
				class="icon icon_2"></span> <span class="tab-label" >斗鸡</span>
			</a> <a class="tab-item external"
				href="${ctx }/wechat/v3/crow/punch"> <span class="icon icon_3"></span>
				<span class="tab-label">进度</span>
			</a> <a class="tab-item external"
				href="${ctx }/wechat/v3/myAccount"> <span class="icon icon_4"></span>
				<span class="tab-label">账户</span>
			</a>
		</nav>
		<!-- 这里是页面内容区 -->
		<div class="content main" id="egg">
			<img class="main_bg" src="<%=path%>/static/v3/img/logo.png" alt="">
			<div class="egg">
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
						<img src="${requestScope.crowUserInfo.user.wechatHeadUrl}" alt="">
					</figure>
					<p class="name">${requestScope.crowUserInfo.user.wechatNickname }</p>
					<div class="code">
						
						<div class="code-img">
							<img src="data:image/png;base64,${requestScope.crowUserInfo.sunQrcode.img}" alt="">
						 </div>
					</div>
				</div>
				<div class="btn_wrap">
					<a class="btn_start external" href="<%=path%>/wechat/v3/pkChongZhi"></a>
					<c:if test="${requestScope.crowUserInfo.iscard==1 }">
						<div class="red" id="downCard"
							style="color: #f7172d; height: 3rem; padding-top: 1rem; font-size: 1rem;; margin: 0 auto; line-height: 2rem; text-align: center;">
							邀请朋友</div>

					</c:if>
					<br>
				</div>
			</div>

			<div class="main_content">
				<p class="list" id="egglist">
					坚持天数： <em>${requestScope.crowUserInfo.alldays }</em> <br>
					影响人数： <em>${requestScope.crowUserInfo.impel }</em> <br> 
					挑战名额： <em>${requestScope.count }</em>
				</p>


			</div>

		</div>
	  </div>
	</div>

	<%-- <div class="popup popup-yaoqing_l">
		<div class="content-block heikuang_l"></div>
		<div class="fenxiang_l close-popup"
			style="position: absolute; top: 0; width: 100%; height: 100%;">
			<img src="<%=path%>/static/v3/img/xiantiao.png"
				style="width: 11.1rem; height: 12.4rem; float: right;">
			<p
				style="color: #FFFFFF; float: left; width: 100%; text-align: center; margin-top: 2rem;">
				分享给朋友</p>
		</div>
	</div> --%>

			<div class="popup popup-yaoqing_l">
			    <div class="content-block heikuang_l" id="qvbian"></div>
			    <div class="fenxiang_l close-popup" style="position: absolute; top: 0; width: 100%; height: 100%;">
			    	<img src="<%=path%>/static/v3/img/xiantiao.png" style="width: 11.1rem; height: 12.4rem;float: right;    margin-top: 1rem;margin-right: 1rem;">
			    	<div class="pengy_l">
			    		<div class="tanzi_l fen">邀</div>
			    		<div class="tanzi_l xiang">请</div>
			    		<div class="tanzi_l gei">朋</div>
			    		<div class="tanzi_l peng">友</div>
			    	</div>
			    	<div class="pengy_l">
			    		<div class="tanzi_l can">参</div>
			    		<div class="tanzi_l gei">加</div>
			    		<div class="tanzi_l peng">挑</div>
			    		<div class="tanzi_l you">战</div>
			    	</div>
			    	
			    </div>
			</div>




	<!-- Left Panel with Reveal effect -->
 	<script type='text/javascript' src='${ctx}/static/public/sm.min.js' charset='utf-8'></script>
 <script type='text/javascript' src='${ctx}/static/public/sm-extend.min.js' charset='utf-8'></script>
 <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
 <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>

	<script> 
		$(function() {
			var shareindex='${shareindex}';
			$(document).on('click','#douji', function () {
				$.toast('即将开放');
			 })
			$(document).on('click','.fenxiang_l', function () {
				$.closeModal('.popup-yaoqing_l');
			 })
			 
			 function showShare(){
				$.popup('.popup-yaoqing_l');
				 wx.ready(function(){
		   	    		//朋友分享 参数
		   	    	      com_shareAppMessage_title='闻鸡起伍',
		   	    	      com_shareAppMessage_content='一年之计在于春，一日之计在于晨！欢迎参加“闻鸡起伍”挑战！',
		   	    	      com_shareAppMessage_link='${share}',
		   	    	      com_shareAppMessage_imgUrl='${requestScope.crowUserInfo.user.wechatHeadUrl}';

		   	    	      wxShare.init({
		   	    				title:com_shareAppMessage_title,
		   	    				imgUrl:com_shareAppMessage_imgUrl,
		   	    				link:com_shareAppMessage_link,
		   	    				content:com_shareAppMessage_content
		   	    			});
		   	    		  	var json = {
		   	    		  			"title":com_shareAppMessage_title,
		   	    		  			"content":com_shareAppMessage_content,
		   	    		  			"link":com_shareAppMessage_link,
		   	    		  			"imgUrl":com_shareAppMessage_imgUrl,
		   	    		  			};
		   	    			wxShare.shareAppFunction(json,OrderShareSuccess.success,OrderShareSuccess.cancel);
		   	    	 	});
			}
			if(shareindex!=''){
				showShare();
			}
			
			$("#downCard").on("click", function(event) {
				showShare();
				/*
				$.showPreloader();
				 $.get("<%=path%>/wechat/v3/crow/shareQRcode",function(data) {
					  setTimeout(function () {
					        $.hidePreloader();
					    }, 500);
								if(data==0){
									   $.alert('对战发起成功', '邀请卡已通过公众号发动给你','请前往保存分享');
								}			
								if(data==1){
									 $.alert('对战发送失败','下载邀请卡失败，请稍后再次尝试');
								}
								
					}); */
			   });
			});
						
	</script>
</body>
</html>