<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%><%	String path = request.getContextPath();				String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()					+ path + "/";%><!DOCTYPE html><!--[if IE 8]> <html lang="en" class="ie8"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9"> <![endif]--><!--[if !IE]><!--><html lang="en"><!--<![endif]--><!-- BEGIN HEAD --><head><meta charset="utf-8" /><title>kangou | 主页</title><meta http-equiv="Page-Enter" content="RevealTrans(duration=5,Transitionv=23)"><meta content="width=device-width, initial-scale=1.0" name="viewport" /><meta content="" name="description" /><meta content="" name="author" /><!-- BEGIN GLOBAL MANDATORY STYLES --><link href="<%=path%>/media/css/bootstrap.min.css" rel="stylesheet"	type="text/css" /><link href="<%=path%>/media/css/font-awesome.min.css" rel="stylesheet"	type="text/css" /><link href="<%=path%>/media/css/style.css" rel="stylesheet"	type="text/css" /><!-- END GLOBAL MANDATORY STYLES --><!-- BEGIN PAGE LEVEL STYLES --><link href="<%=path%>/media/css/index2.css" rel="stylesheet"	type="text/css" /><!-- END PAGE LEVEL STYLES --></head><!-- END HEAD --><!-- BEGIN BODY --><body class="login">	<!-- 	<div class="logo" style="text-align: center;">		<h4 class="form-title" style="color: #fff; margin-bottom: 0px;"			onclick="javascript:window.open(index.html);">看购商户版</h4>	</div>		-->	<!-- BEGIN LOGIN -->		<div class="content">		<ul class="ver-inline-menu tabbable margin-bottom-25">			<li style="background: #FFFFFF;"><div>					<a href="<%=path%>/return.do?url=prompt" style="border: 0px;"><i						class="icon-qrcode"></i>扫描二维码 <i						style="float: right; color: #EF4141;" class="icon-chevron-right"></i></a>				</div></li>			<li style="background: #FFFFFF;"><div>					<a href="<%=path%>/return.do?url=codeByPassword"						style="border: 0px;"><i class="icon-shopping-cart"></i>密码购票 <i						style="float: right; color: #EF4141;" class="icon-chevron-right"></i></a>				</div></li>			<li style="background: #FFFFFF;"><div>					<a href="<%=path%>/return.do?url=operations" style="border: 0px;">						<i class="icon-leaf"></i>财务结算 <i						style="float: right; color: #EF4141;" class="icon-chevron-right"></i>					</a>				</div></li>			<!-- 暂时隐藏，后续开发 			<li style="background: #FFFFFF;"><div>					<a href="backCard.html" style="border: 0px;"><i						class="icon-tasks"></i>退票操作<span						style="float: right; font-size: 30px; color: #EF4141; margin-top: 4px;"						class="icon-chevron-right"></span></a>				</div></li>			 -->			<li style="background: #FFFFFF;"><div>					<a href="<%=path%>/return.do?url=again" style="border: 0px;"><i						class="icon-print"></i>重新打印 <i						style="float: right; color: #EF4141;" class="icon-chevron-right"></i></a>				</div></li>			<li style="background: #FFFFFF;"><div>					<a href="<%=path%>/return.do?url=updatePassword"						style="border: 0px;"><i class="icon-plus"></i>修改密码 <i						style="float: right; color: #EF4141;" class="icon-chevron-right"></i></a>				</div></li>			<li style="background: #FFFFFF;"><div>					<a href="<%=path%>/return.do?url=notice" style="border: 0px;"><i						class="icon-envelope"></i>消息查看 <i						style="float: right; color: #EF4141;" class="icon-chevron-right"></i></a>				</div></li>			<li style="background: #FFFFFF;"><div>					<a href="<%=path%>/return.do?url=instruction" style="border: 0px;"><i						class="icon-tasks"></i>使用说明 <i						style="float: right; color: #EF4141;" class="icon-chevron-right"></i></a>				</div></li>			<li style="background: #FFFFFF;"><div>					<a href="<%=path%>/test.jsp" style="border: 0px;"><i						class="icon-tasks"></i>测试接口<i						style="float: right; color: #EF4141;" class="icon-chevron-right"></i></a>				</div></li>		</ul>	</div>		</body><!-- END BODY --></html>