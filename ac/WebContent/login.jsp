<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>欢迎登录</title>
<link rel="shortcut icon" href="images/favicon.jpg" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<link href="css/login.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<h1>
			看购AC管理系统 <sup>V2016</sup>
		</h1>

		<div class="login" style="margin-top: 50px;">



			<div class="web_qr_login" id="web_qr_login"
				style="display: block; height: 235px;">

				<!--登录-->
				<div class="web_login" id="web_login">


					<div class="login-box">


						<div class="login_form">
							<form class="loginForm" method="post" id="loginform">
								<label id="did" style="color: red;"></label>
								<div class="uinArea" id="uinArea">
									<label class="input-tips" for="u">帐号：</label>
									<div class="inputOuter" id="uArea">

										<input type="text" id="u" name="username" class="inputstyle" />
									</div>
								</div>
								<div class="pwdArea" id="pwdArea">
									<label class="input-tips" for="p">密码：</label>
									<div class="inputOuter" id="pArea">
										<input type="password" id="p" name="password"
											class="inputstyle" />
									</div>
								</div>
								<div style="padding-left: 50px; margin-top: 20px;">
									<input type="button" id="loginbutton" value="登 录"
										style="width: 150px;" class="button_blue" />
								</div>
							</form>
						</div>

					</div>

				</div>
				<!--登录end-->
			</div>


		</div>
		<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
		<div class="jianyi">北京看购科技有限公司 @ 版权所有 2007-2016
			服务热线：400-677-6501</div>


	</body>
</html>