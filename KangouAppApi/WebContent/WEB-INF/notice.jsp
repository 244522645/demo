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

<title>kangou | 主页</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<meta content="" name="description" />

<meta content="" name="author" />

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

<link href="<%=path%>/media/css/index2.css" rel="stylesheet"
	type="text/css" />

<!-- END PAGE LEVEL STYLES -->


</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">
	<!-- BEGIN LOGIN -->
	<div class="content">

		<ul class="ver-inline-menu tabbable margin-bottom-25" id="notice">
			

		</ul>

	</div>
	<div class="portlet-body">
		<p style="margin-left: 30px; margin-right: 30px;">
			<button class="btn red btn-block" onclick="clickBtn()">
				加载更多 <i class=" icon-download-alt"></i>
			</button>
		</p>

	</div>

	<!-- END LOGIN -->



	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="<%=path%>/media/js/jquery-1.10.1.min.js"
		type="text/javascript"></script>


	<script src="<%=path%>/media/js/bootstrap.min.js"
		type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="<%=path%>/media/js/excanvas.min.js"></script>

	<script src="<%=path%>/media/js/respond.min.js"></script>  

	<![endif]-->



	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script
		src="<%=path%>/media/js/notice.js?_=<%=System.currentTimeMillis()%>"
		type="text/javascript"></script>
	<script src="<%=path%>/media/js/sweetalert.min.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->

	<script>
		jQuery(document).ready(function() {
			
			notice.init();

		});
	</script>


</body>

<!-- END BODY -->

</html>