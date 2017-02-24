<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>Metronic | Extra - 404 Page</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<meta content="" name="description" />

<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="<%=path%>/media//css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />


<link href="<%=path%>/media//css/style.css" rel="stylesheet"
	type="text/css" />



<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link href="<%=path%>/media//css/error.css" rel="stylesheet"
	type="text/css" />

<!-- END PAGE LEVEL STYLES -->


</head>

<!-- END HEAD -->


<!-- BEGIN BODY -->

<body class="page-404-full-page">

	<div class="row-fluid" align="center">
		<div class="span12 page-404" align="center">


			<div class="number">

				<i
					style="width: 71px; height: 69px; background: url(/KangouAppApi/media/image/404_bg.jpg); display: inline-block;">
				</i> 出错了
			</div>
			<br />
			<div class="details">

				<h3 style="color: #d21010; margin-left: 15px; margin-right: 15px;">编号:${requestScope.messageid }您访问的页面由于${requestScope.message },请重新再试或联系管理员</h3>

			</div>


		</div>



	</div>


	<!-- END BODY -->
</html>