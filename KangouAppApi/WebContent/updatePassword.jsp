<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%><%	String path = request.getContextPath();				String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()					+ path + "/";%><!DOCTYPE html><!--[if IE 8]> <html lang="en" class="ie8"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9"> <![endif]--><!--[if !IE]><!--><html lang="en"><!--<![endif]--><!-- BEGIN HEAD --><head><meta charset="utf-8" /><title>Metronic | Login Page</title><meta content="width=device-width, initial-scale=1.0" name="viewport" /><meta content="" name="description" /><meta content="" name="author" /><!-- BEGIN GLOBAL MANDATORY STYLES --><link href="<%=path%>/media/css/bootstrap.min.css" rel="stylesheet"	type="text/css" /><link href="<%=path%>/media/css/style-metro.css" rel="stylesheet"	type="text/css" /><link href="<%=path%>/media/css/style.css" rel="stylesheet"	type="text/css" /><link href="<%=path%>/media/css/default.css" rel="stylesheet"	type="text/css" id="style_color" /><!-- END GLOBAL MANDATORY STYLES --><!-- BEGIN PAGE LEVEL STYLES --><link href="<%=path%>/media/css/scanCode.css" rel="stylesheet"	type="text/css" /><!-- END PAGE LEVEL STYLES --></head><!-- END HEAD --><!-- BEGIN BODY --><body class="login">	<!-- 	<div class="logo" style="text-align: center;">		<a class="more" href="javascript: window.history.go(-1)"			style="float: left;"> <i class="m-icon-big-swapleft m-icon-white"></i>		</a>		<h4 class="form-title" style="color: #fff; margin-bottom: 0px;"			onclick="javascript:window.open(index.html);">看购商户版</h4>	</div>	 -->	<!-- BEGIN LOGIN -->	<div class="content">		<ul class="ver-inline-menu tabbable margin-bottom-25">			<li><p align="center">					<span>原密码:</span> <input class="m-wrap placeholder-no-fix" style="margin-bottom: 0px;"						type="password" placeholder="原密码" name="password" id="oldpass" />				</p></li>			<li><p align="center">					<span>新密码:</span> <input class="m-wrap placeholder-no-fix" style="margin-bottom: 0px;"						type="password" placeholder="新密码" name="password" id="newpass" />				</p></li>			<li><p align="center">					<span>新密码:</span> <input class="m-wrap placeholder-no-fix" style="margin-bottom: 0px;"						type="password" placeholder="确认密码" name="password" id="twopass" />				</p></li>		</ul>		<ul class="button" style="margin-left: 0px;margin: 30px;">			<li style="height: 30px;"><button type="button" class="btn red"					style="width: 100%;" onclick="updatepass">提交</button></li>			<!-- 			<li><button type="submit" class="btn red "					onclick="javascript: window.history.go(-1)">返回</button></li>			 -->		</ul>	</div>	<!-- END LOGIN -->	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->	<!-- BEGIN CORE PLUGINS -->	<script src="<%=path%>/media/js/sweetalert.min.js"		type="text/javascript"></script>	<script src="<%=path%>/media/js/jquery-1.10.1.min.js"		type="text/javascript"></script>	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->	<script src="<%=path%>/media/js/bootstrap.min.js"		type="text/javascript"></script>	<!--[if lt IE 9]>
	<script src="<%=path%>/media/js/excanvas.min.js"></script>
	<script src="<%=path%>/media/js/respond.min.js"></script>  
	<![endif]-->	<!-- END CORE PLUGINS -->	<!-- BEGIN PAGE LEVEL SCRIPTS -->	<script src="<%=path%>/media/js/updatePassword.js?_=<%=System.currentTimeMillis()%>"		type="text/javascript"></script>	</body><!-- END BODY --></html>