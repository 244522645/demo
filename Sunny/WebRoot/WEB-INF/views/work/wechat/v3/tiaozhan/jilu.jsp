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
			<div class="content bcakcolor_l">
				<div class="jilu_biaoti_l">闻鸡起伍</div>
				<div class="buttons-tab jilu_xuanxiangk_l">
					<a href="#tab1" class="tab-link active button jilu_xuanxiangg_l">进行中</a>
					<a href="#tab2" class="tab-link button jilu_xuanxiangg_l heibian">成功</a>
					<a href="#tab3" class="tab-link button jilu_xuanxiangg_l">失败</a>
				</div>
				<div class="content-block">
					<div class="tabs">

						<div id="tab1" class="tab active">
							<div class="content-block  jinxing_l">
								<!--每一个星星代表一个状态更改星星颜色直接换class名，勋章同样的道理-->
								<div class="row xzq_l">
									<c:forEach items="${requestScope.CrowPkOne}" var="rowuser"
										varStatus="rowvs">



										<div class="col-33 xunzhang_l"
											onclick="javascript:self.location.href='<%=path%>/wechat/v3/crow/pkone/show?id=${rowuser.id}'  ">
											<img src="${rowuser.touser.wechatHeadUrl}" /><br />
											<div class="xingxing_l">
												 <c:forEach begin="1" end="${rowuser.otherUserPkCompleteDay}">
													<div class="xhongxing_l"></div>
												</c:forEach> 
												<c:forEach begin="1" end="${5-rowuser.otherUserPkCompleteDay}">
													<div class="xhueixing_l"></div>
												</c:forEach>


											</div>
										</div>
									</c:forEach>
								</div>
							</div>


							<!--没有进行的-->
							<!--<div class="kongji_l">
			         	<img src="img/daji.png"/>
			         </div>
			         <p class="weikaiqi_l">您还没有开启挑战</p>
			         <div class="jilu_kaiqi_l">
			         	开启挑战
			         </div>-->

						</div>
						<div id="tab2" class="tab">
							<div class="content-block">
								<div class="row xzq_l">
									<c:forEach items="${requestScope.winList}" var="rowuser">
										<div class="col-33 xunzhang_l"
											onclick="javascript:self.location.href='<%=path%>/wechat/v3/crow/pkone/show?id=${rowuser.id}'  ">
											<!--给图片加灰色添加 class="qvse_l"代表对手的头像失败自己胜利-->
											<img src="${rowuser.touser.wechatHeadUrl}" class="qvse_l" />
											<div class="xingxing_l">
												<div class="xhongxing_l"></div>
												<div class="xhongxing_l"></div>
												<div class="xhongxing_l"></div>
												<div class="xhongxing_l"></div>
												<div class="xhongxing_l"></div>
											</div>
											<c:if test="${rowuser.otherUserPkStatus=='0'}">
												<div class="sheng_l">
													<img src="<%=path%>/static/v3/img/sheng.png" />
												</div>
											</c:if>
											<c:if test="${rowuser.otherUserPkStatus=='1'}">
												<div class="sheng_l">
													<img src="<%=path%>/static/v3/img/shuangying.png" />
												</div>
											</c:if>

										</div>
									</c:forEach>
								</div>
								<!-- 没有成功的<div class="kongji_l">
				         	<img src="img/daji.png"/>
				        </div>
				        <div class="meiyou_l">您还没有挑战成功，加油哦！</div>-->
							</div>
						</div>
						<div id="tab3" class="tab">
							<div class="content-block">
								<div class="row xzq_l">
									<c:forEach items="${requestScope.loseList}" var="rowuser">
										<div class="col-33 xunzhang_l"
											onclick="javascript:self.location.href='<%=path%>/wechat/v3/crow/pkone/show?id=${rowuser.id}'  ">
											<!--给图片加灰色添加 class="qvse_l"代表对手的头像失败自己胜利-->
											<img src="${rowuser.touser.wechatHeadUrl}"  />

											<c:if test="${rowuser.otherUserPkStatus=='1'}">
												<div class="sheng_l">
													<img src="<%=path%>/static/v3/img/bai.png" />
												</div>
											</c:if>
											<c:if test="${rowuser.otherUserPkStatus=='0'}">
												<div class="sheng_l">
													<img src="<%=path%>/static/v3/img/shuangbai.png" />
												</div>
											</c:if>

										</div>
									</c:forEach>
								</div>
								<!-- 没有失败<div class="kongji_l">
			         	<img src="img/daji.png"/>
			         </div>
			         <div class="meiyou_l">您保持了不败的战绩，干得漂亮！</div>-->
							</div>
						</div>
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
		
	</script>
</body>
</html>