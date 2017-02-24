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

<title>Metronic | Login Page</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<meta content="" name="description" />

<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="<%=path%>/media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media/css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=path%>/media/css/DT_bootstrap.css" rel="stylesheet"
	type="text/css" />

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
<link href="<%=path%>/media/css/daterangepicker-bs3.css"
	rel="stylesheet" type="text/css" />

<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link href="<%=path%>/media/css/operations.css" rel="stylesheet"
	type="text/css" />

<!-- END PAGE LEVEL STYLES -->

<link rel="shortcut icon" href="<%=path%>/media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">

	<!-- BEGIN LOGIN -->

	<div style="margin-top: 10px;" align="center">


		<table class="table table-hover">
			<thead>

				<tr>

					<th style="text-align: center;">单价（元）</th>

					<th style="text-align: center;">数量（张）</th>

					<th style="text-align: center;">总价（元）</th>

				</tr>

			</thead>
			<tbody id="tabletbody">
			</tbody>


		</table>


		<div class="form-actions"
			style="background-color: #fff; border-top: 0px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px;">
			<button type="button" id="register-submit-btn" class="btn red"
				style="width: 100%; background-color: #f9625b !important;" onclick="clickBtn()">
				加载更多<i class="m-icon-swapright m-icon-white"></i>

			</button>

		</div>


	</div>

	<!-- END LOGIN -->



	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="<%=path%>/media/js/jquery-1.10.1.min.js"
		type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="<%=path%>/media/js/jquery-ui-1.10.1.custom.min.js"
		type="text/javascript"></script>

	<script src="<%=path%>/media/js/bootstrap.min.js"
		type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="<%=path%>/media/js/excanvas.min.js"></script>

	<script src="<%=path%>/media/js/respond.min.js"></script>  

	<![endif]-->

	<script src="<%=path%>/media/js/jquery.slimscroll.min.js"
		type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.blockui.min.js"
		type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.cookie.min.js"
		type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.uniform.min.js"
		type="text/javascript"></script>
	<script src="<%=path%>/media/js/moment.js" type="text/javascript"></script>
	<script src="<%=path%>/media/js/daterangepicker.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script src="<%=path%>/media/js/jquery.validate.min.js"
		type="text/javascript"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="<%=path%>/media/js/app.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/moreoperations.js?_=<%=System.currentTimeMillis()%>" type="text/javascript"></script>

	<!-- END PAGE LEVEL SCRIPTS -->

	<script>
		jQuery(document).ready(function() {
			$('#starttime').daterangepicker({
				singleDatePicker : true
			}, function(start, end, label) {
				console.log(start.toISOString(), end.toISOString(), label);
			});
			$('#endtime').daterangepicker({
				singleDatePicker : true
			}, function(start, end, label) {
				console.log(start.toISOString(), end.toISOString(), label);
			});
			App.init();

			Login.init();

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