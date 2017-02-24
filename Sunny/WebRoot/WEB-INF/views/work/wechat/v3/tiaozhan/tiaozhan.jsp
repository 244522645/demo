<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

</head>
<body class="v2">
	<div class="page-group">
		<!-- 单个page ,第一个.page默认被展示-->
		<div class="page">
			<!-- 标题栏 -->

			<!-- 工具栏 -->


			<!-- 这里是页面内容区 -->
			<div class="content bcakcolor_l four">
				<div class="tiaozhan_bipin_l">
					<div class="tiaozhan_bipin_one_l">
						<div class="tianzhan_zan_l">
							<img src="<%=path%>/static/v3/img/kaishi.png">
						</div>
						<div class="tiaozhan_data_l"></div>
						<div class="tiaozhan_tian_l"  id="daynum">
							第 <b id="nowday">3</b> 天
						</div>
						<div class="tiaozhan_zhuangtai_l">
							进<br />行<br />中
						</div>
					</div>
					<div class="tiaozhan_bipin_two_l row no-gutter">
						<div class="tiaozhan_touxiang1_l" id="faqiren">
							<div class="touxiang1_l">
								<img src="${requestScope.pkUsers.user.wechatHeadUrl }" />
							</div>
							<span class="jia" id="usernum">+10</span>
							<p class="tiaozhuan_name_l">${requestScope.pkUsers.user.wechatNickname }</p>
							<div class="tiaozhuan_xunzhan_l">
								<img src="<%=path%>/static/v3/img/xunz.png">
							</div>
						</div>
						<div class="tiaozhan_vs_l">VS</div>
						<div class="tiaozhan_touxiang2_l hueitu" id="tiaozhanzhe">
							<div class="touxiang1_l">
								<img src="${requestScope.pkUsers.touser.wechatHeadUrl }" />
							</div>
							<span class="jian" id="crowusernum"></span>
							<p class="tiaozhuan_name_l huei">${requestScope.pkUsers.touser.wechatNickname }</p>
							<div class="tiaozhuan_xunzhan_l" id="beitiaozhan">
								<img src="<%=path%>/static/v3/img/xunz.png">
							</div>
						</div>
					</div>
					<div class="tiaozhan_bipin_three_l">
						<div class="jinchneng_l">
							<span class="tiaozhan_name_l">${requestScope.pkUsers.user.wechatNickname }：</span>
							<c:forEach begin="1" end="5" varStatus="name">

								<c:if
									test="${ requestScope.pkUsers.userPkCompleteDay>=name.index}">
									<div class="hongxing_l"></div>
								</c:if>
								<c:if
									test="${ requestScope.pkUsers.userPkCompleteDay<name.index}">
									<div class="hueixing_l"></div>
								</c:if>


							</c:forEach>
						</div>
						<div class="jinchneng_l">
							<span class="tiaozhan_name_l">${requestScope.pkUsers.touser.wechatNickname }：</span>
							<c:forEach begin="1" end="5" varStatus="name">

								<c:if
									test="${ requestScope.pkUsers.otherUserPkCompleteDay>=name.index}">
									<div class="hongxing_l"></div>
								</c:if>
								<c:if
									test="${ requestScope.pkUsers.otherUserPkCompleteDay<name.index}">
									<div class="hueixing_l"></div>
								</c:if>


							</c:forEach>
						</div>
					</div>
					<div class="tiaozhan_bipin_four_l">
						<p class="tiaozhan_shuoming_l">挑战规则</p>
						<p>闻鸡起伍，连续五天早起晨跑，</p>
						<p>并于早9点之前，把运动记录发送至平台，</p>
						<p>视为挑战成功！挑战成功者返回挑战金，</p>
						<p>并赢取挑战失败者挑战金！</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- popup, panel 等放在这里 -->
	<div class="panel-overlay"></div>
	<!-- Left Panel with Reveal effect -->
	<script type="text/javascript"
		src="<%=path%>/static/v3/js/zepto.min.js"></script>
	<script type="text/javascript" src="<%=path%>/static/v3/js/sm.min.js"></script>
	<script>
		$.init();
	</script>

	<script>
		$(function() {
			function show() {
				var mydate = new Date();
				var str = "" + mydate.getFullYear() + ".";
				str += (mydate.getMonth() + 1) + ".";
				str += mydate.getDate();
				return str;
			}
			$(".tiaozhan_data_l").html(show());
			var pkstauts = '${pkUsers.userPkStatus}';
			if (pkstauts == 3) {
				$("#daynum").text("明日开战");
				$("#nowday").text("0");
				$("#usernum").hide();
				$("#crowusernum").hide();
				$("#tiaozhanzhe").attr({
					class : "tiaozhan_touxiang1_l"
				});
				$(".tiaozhuan_xunzhan_l").hide();
				$(".tiaozhan_zhuangtai_l").html("未<br />开<br />");
			}
			if (pkstauts == 2) {
				$("#nowday").text('${pkUsers.userPkCompleteDay}');
				$("#usernum").hide();
				$("#crowusernum").hide();
				$("#tiaozhanzhe").attr({
					class : "tiaozhan_touxiang1_l"
				});
				$(".tiaozhuan_xunzhan_l").hide();
				$(".tiaozhan_zhuangtai_l").html("进<br />行<br />");
			}
			//挑战者成功
			if (pkstauts == 1) {
				$("#nowday").text('${pkUsers.userPkCompleteDay}');
				$("#daynum").text("挑战结束");
				//被挑战者成功
				if ("${pkUsers.otherUserPkStatus}" == 1) {
					$("#tiaozhanzhe").attr({
						class : "tiaozhan_touxiang1_l"
					});
					$(".tiaozhuan_xunzhan_l").show();
					$("#beitiaozhan").show();
					$("#usernum").text("+5");
					$("#crowusernum").text("+5");

				}else{
					$("#beitiaozhan").hide();
				}
			
				$(".tiaozhan_zhuangtai_l").html("结<br />束<br />");
			}
			//挑战者失败
			if (pkstauts == 0) {
				$("#daynum").text("挑战结束");
				$("#nowday").text('${pkUsers.userPkCompleteDay}');
				$("#faqiren").attr({
					class : "tiaozhan_touxiang1_l hueitu"
				});
				//被挑战者成功
				if ("${pkUsers.otherUserPkStatus}" == 1) {
					$("#tiaozhanzhe").attr({
						class : "tiaozhan_touxiang1_l"
					});
					$("#usernum").text("");
					$("#crowusernum").text("+10");
					$(".tiaozhuan_xunzhan_l").hide();
					$("#beitiaozhan").show();
				}
				if ("${pkUsers.otherUserPkStatus}" == 0) {
					$("#tiaozhanzhe").attr({
						class : "tiaozhan_touxiang1_l hueitu"
					});
					$("#usernum").text("");
					$("#crowusernum").text("");
					$(".tiaozhuan_xunzhan_l").hide();
					$("#beitiaozhan").hide();
				}
				$(".tiaozhan_zhuangtai_l").html("结<br />束<br />");
			}
		});
	</script>
</body>
</html>