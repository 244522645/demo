
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

<title>kangou | 注册</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<meta content="" name="description" />

<meta content="" name="author" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="content-type" content="no-cache, must-revalidate" />
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="<%=path%>/media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media/css/style-metro.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media/css/style.css" rel="stylesheet"
	type="text/css" />


<link href="<%=path%>/media/css/sweetalert.css" rel="stylesheet"
	type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link href="<%=path%>/media/css/login.css" rel="stylesheet"
	type="text/css" />

<!-- END PAGE LEVEL STYLES -->

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">

	<!-- BEGIN LOGO -->
	<!-- 
	<div class="logo" style="color: #D84A38;">
		<h3 class="form-title">看购商户版</h3>
	</div>
 -->
	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->

	<div class="content">
		<!-- BEGIN REGISTRATION FORM -->

		<div class="control-group " style="margin-top: 20px;">


			<div class="form-actions"
				style="margin-top: 0px; padding-bottom: 0px;">
				<div class="input-icon left ">

					<i class="icon-rss"></i> <input class="m-wrap placeholder-no-fix "
						type="text" placeholder="手机号" onkeyup="isphone(this)"
						maxlength="11" id="phone" />

				</div>


			</div>
		</div>


		<div class="control-group">

			<div class="form-actions"
				style="margin-top: 0px; padding-bottom: 0px;">
				<button id="phone_btn" type="button" class="btn red"
					style="width: 100%; background-color: #f9625b !important;"
					data-loading-text="获取验证码">

					<i class="swapleft"></i> 获取验证码

				</button>
			</div>
		</div>
		<div class="control-group">

			<div class="controls">
				<div class="form-actions"
					style="margin-top: 0px; padding-bottom: 0px;">
					<div class="input-icon left">

						<i class="icon-envelope"></i> <input
							class="m-wrap placeholder-no-fix" type="text" placeholder="验证码"
							maxlength="6" id="code" />

					</div>
				</div>
			</div>

		</div>
		<div class="control-group">
			<div class="form-actions"
				style="margin-top: 0px; padding-bottom: 0px;">
				<label class="control-label visible-ie8 visible-ie9">密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i> <input class="m-wrap placeholder-no-fix"
							type="password" id="register_password" placeholder="密码"
							onblur="regPassword()" name="password" />

					</div>
				</div>
			</div>

		</div>

		<div class="control-group">
			<div class="form-actions"
				style="margin-top: 0px; padding-bottom: 0px;">
				<label class="control-label visible-ie8 visible-ie9">确认密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-ok"></i> <input class="m-wrap placeholder-no-fix"
							id="twoPassword" onblur="istwoPassword()" type="password"
							placeholder="确认密码" name="rpassword" />

					</div>
				</div>
			</div>

		</div>



		<!-- 
			<div class="control-group">

				<div class="controls">

					<label class="checkbox"> <input type="checkbox" name="tnc" />
						我同意公司的 <a href="#">服务条件</a> 和 <a href="#">隐私政策协议</a>

					</label>

					<div id="register_tnc_error"></div>

				</div>

			</div>
			 -->
		<div class="control-group">
	<div class="form-actions"
				style="margin-top: 0px; padding-bottom: 0px;">
			<div class="controls">
				<div  style="color: red;white-space: nowrap;">
					<i class="checkbox"></i> <input type="checkbox" name="tnc"
						checked="checked" />注册完成后请联系资源部审核开通
				</div>
			</div>
</div>
		</div>

		<div class="form-actions" style="padding-bottom: 0px;margin-bottom: 0px;border-bottom-width: 0px;">
			<button type="button" id="register-submit-btn" class="btn red"
				onclick="formBtn()"
				style="width: 100%; background-color: #f9625b !important;">

				注册 <i class="m-icon-swapright m-icon-white"></i>

			</button>

		</div>

		<div class="form-actions">
			<button type="button" id="return-btn" class="btn red"
				onclick="returnLogin()"
				style="width: 100%; background-color: #f9625b !important;">

				登录 <i class="m-icon-swapright m-icon-white"></i>

			</button>

		</div>



		<!-- END REGISTRATION FORM -->



	</div>

	<!-- END LOGIN -->



	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="<%=path%>/media/js/jquery-1.10.1.min.js"
		type="text/javascript"></script>


	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->


	<script src="<%=path%>/media/js/bootstrap.min.js"
		type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="<%=path%>/media/js/excanvas.min.js"></script>

	<script src="<%=path%>/media/js/respond.min.js"></script>  

	<![endif]-->


	<!-- END CORE PLUGINS -->

	

	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="<%=path%>/media/js/reg.js?_=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
	<script src="<%=path%>/media/js/sweetalert.min.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->

	<script>
		jQuery(document).ready(function() {
			imsi = '${requestScope.imsi}';
			mobileType = '${requestScope.mobileType}';
		

			Login.init();

		});
		function returnLogin() {
			if (mobileType == 'ios') {
				kangoulogin();
			}
			if (mobileType == 'android') {
				kangou.login();
			}

		}
	</script>

</body>

<!-- END BODY -->

</html>
