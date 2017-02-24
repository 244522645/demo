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

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<meta content="" name="description" />

<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="<%=path%>/media//css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media//css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />

<link href="<%=path%>/media//css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media//css/style-metro.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media//css/style.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media//css/style-responsive.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media//css/default.css" rel="stylesheet"
	type="text/css" id="style_color" />

<link href="<%=path%>/media//css/uniform.default.css" rel="stylesheet"
	type="text/css" />

<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link href="<%=path%>/media//css/error.css" rel="stylesheet"
	type="text/css" />

<!-- END PAGE LEVEL STYLES -->

<link rel="shortcut icon" href="<%=path%>/media//image/favicon.ico" />

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

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="<%=path%>/media//js/jquery-1.8.3.min.js"
		type="text/javascript"></script>

	<script src="<%=path%>/media//js/bootstrap.min.js"
		type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="<%=path%>/media//js/excanvas.js"></script>

	<script src="<%=path%>/media//js/respond.js"></script>  

	<![endif]-->

	<script src="<%=path%>/media//js/breakpoints.js" type="text/javascript"></script>

	<script src="<%=path%>/media//js/jquery.uniform.min.js"
		type="text/javascript"></script>

	<!-- END CORE PLUGINS -->

	<script src="<%=path%>/media//js/app.js"></script>

	<script>
		jQuery(document).ready(function() {

			App.init();

		});
	</script>

	<!-- END JAVASCRIPTS -->

	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-37564768-1' ]);
		_gaq.push([ '_setDomainName', 'keenthemes.com' ]);
		_gaq.push([ '_setAllowLinker', true ]);
		_gaq.push([ '_trackPageview' ]);
		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://'
					: 'http://')
					+ 'stats.g.doubleclick.net/dc.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>
</body>

<!-- END BODY -->

</html>