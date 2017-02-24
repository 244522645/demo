<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

<meta charset="utf-8" />

<title>kangou | 购卡</title>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="0">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<meta content="" name="description" />

<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="<%=path%>/media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media/css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />

<link href="<%=path%>/media/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media/css/style-metro.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/media/css/default.css" rel="stylesheet"
	type="text/css" id="style_color" />

<link href="<%=path%>/media/css/uniform.default.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/media/css/sweetalert.css" rel="stylesheet"
	type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link href="<%=path%>/media/css/scanCode.css" rel="stylesheet"
	type="text/css" />

<!-- END PAGE LEVEL STYLES -->

<link rel="shortcut icon" href="<%=path%>/media/image/favicon.ico" />
<link href="<%=path%>/media/css/sweetalert.css" rel="stylesheet"
	type="text/css" />
</head>


<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">
	<!--  
	<div class="logo" style="text-align: center;">
		<a class="more" href="javascript: window.history.go(-1)"
			style="float: left;"> <i class="m-icon-big-swapleft m-icon-white"></i>

		</a>
		<h4 class="form-title" style="color: #fff;margin-bottom:0px;"
			onclick="javascript:window.open(index.html);">看购商户版</h4>

	</div>
	
	-->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<form action="<%=path%>/buyCard/backCard.do" method="post"
			id="cardFrom">
			<ul class="ver-inline-menu tabbable margin-bottom-25">
				<li><p align="center"
						style="margin-bottom: 0px; line-height: 28px; text-align: center; margin-top: 3px;">
						<span>流水号:</span> <input class="m-wrap placeholder-no-fix"
							name="swiftNumber" style="width: 190px; margin-bottom: 0px;"
							type="number" maxlength="11" id="swiftNumber"
							placeholder="请输入票据流水号" />
					</p></li>
			</ul>
			<ul class="button" style="margin: 30px;">
				<li style="height: 30px;"><button
						style="width: 100%; background-color: #f9625b;" id="submitBtn"
						class="btn" onclick="issubmit()" type="button">
						退票<i class="m-icon-swapright m-icon-white"></i>
					</button></li>

			</ul>
		</form>
	</div>


	<!-- END LOGIN -->



	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="<%=path%>/media/js/sweetalert.min.js"></script>
	<script type="text/javascript">
		function issubmit() {
			var swiftNumber = $("#swiftNumber").val();
			if (!swiftNumber) {
				swal("流水号不能为空");
				return;
			}
			var param = {
				swiftNumber : swiftNumber
			}
			$.post("./buyCard/backCard.do", param, function(data) {
				swal(data.messageId+data.message);
			});
		}
	</script>
</body>

<!-- END BODY -->

</html>