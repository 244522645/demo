<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%	String path = request.getContextPath();			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()					+ path + "/";%><!DOCTYPE html><!--[if IE 8]> <html lang="en" class="ie8"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9"> <![endif]--><!--[if !IE]><!--><html lang="en"><!--<![endif]--><!-- BEGIN HEAD --><head><meta charset="utf-8" /><title>kangou | 消息</title><meta content="width=device-width, initial-scale=1.0" name="viewport" /><meta content="" name="description" /><meta content="" name="author" /><!-- BEGIN GLOBAL MANDATORY STYLES --><link href="<%=path%>/media/css/bootstrap.min.css" rel="stylesheet"	type="text/css" /><link href="<%=path%>/media/css/style.css" rel="stylesheet"	type="text/css" /><!-- END GLOBAL MANDATORY STYLES --><link rel="shortcut icon" href="<%=path%>/media/image/favicon.ico" /><link href="<%=path%>/media/css/operations.css" rel="stylesheet"	type="text/css" /></head><!-- END HEAD --><!-- BEGIN BODY --><body class="login">	<!-- 	<div class="logo" style="text-align: center;">		<a class="more" href="javascript: window.history.go(-1)"			style="float: left;"> <i class="m-icon-big-swapleft m-icon-white"></i>		</a>		<h4 class="form-title" style="color: #fff; margin-bottom: 0px;"			onclick="javascript:window.open(index.html);">看购商户版</h4>	</div> -->	<!-- BEGIN LOGIN -->	<div class="row-fluid margin-bottom-20">		<div class="span12"			style="word-break: break-all; word-wrap: break-word;">			<h3 style="text-align: center;">${requestScope.notice.ntitle}</h3>			<br />			<p style="text-align: center;">				<fmt:formatDate value="${requestScope.notice.releasetime}"					pattern="yyyy-MM-dd HH-mm-ss" />			</p>			<p				style="text-indent: 2em; padding: 0px; margin: 0px; font-size: 16px;">${requestScope.notice.ncontent }</p>		</div>	</div>	<!-- END LOGIN -->	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->	<!-- BEGIN CORE PLUGINS -->	<script src="<%=path%>/media/js/jquery-1.10.1.min.js"		type="text/javascript"></script>	<script src="<%=path%>/media/js/bootstrap.min.js"		type="text/javascript"></script>	<!--[if lt IE 9]>
	<script src="<%=path%>/media/js/excanvas.min.js"></script>
	<script src="<%=path%>/media/js/respond.min.js"></script>  
	<![endif]--></body><!-- END BODY --></html>