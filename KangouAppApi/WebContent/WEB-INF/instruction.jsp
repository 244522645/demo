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
<link href="<%=path%>/media/css/style.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media/css/style-metro.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media/css/sweetalert.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/media/css/index2.css" rel="stylesheet"
	type="text/css" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">
	<!-- BEGIN LOGIN -->
	<div class="content" style="background-color: #fdf7f2;">


		<table style="width: 100%;">
			<tr style="margin: 0px;">
				<td style="width: 30%;"><h5
						style="text-align: right; margin: 0px;">商户名：</h5></td>
				<td style="margin-left: 50%;"><h5
						style="text-align: left; margin: 0px;">${sessionScope.cinemaName.name}</h5></td>
			</tr>
			<tr style="margin: 0px;">
				<td style="width: 30%;"><h5
						style="text-align: right; margin: 0px;">终端号：</h5></td>
				<td style="margin-left: 50%;"><h5
						style="text-align: left; margin: 0px;">${sessionScope.cinemaName.posid}</h5></td>

			</tr>
			<tr style="margin: 0px;">
				<td style="width: 30%;"><h5
						style="text-align: right; margin: 0px;">用户名：</h5></td>
				<td style="margin-left: 50%;"><h5
						style="text-align: left; margin: 0px;">${sessionScope.currentUser.cinamausername}</h5></td>

			</tr>
		</table>
		<ul class="ver-inline-menu tabbable" id="notice">
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<a style='border: 0px; border-left: 0px;' onclick="ul_onclick(1)"><i
						class='icon-file-text'></i> 1.权限说明</a>
				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<a style='border: 0px; border-left: 0px;' onclick="ul_onclick(2)"><i
						class='icon-file-text'></i> 2.如何连接打印机</a>
				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<a style='border: 0px; border-left: 0px;' onclick="ul_onclick(3)"><i
						class='icon-file-text'></i> 3.密码购票说明</a>
				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<a style='border: 0px; border-left: 0px;' onclick="ul_onclick(4)"><i
						class='icon-file-text'></i> 4.财务结算说明</a>
				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<a style='border: 0px; border-left: 0px;' onclick="ul_onclick(5)"><i
						class='icon-file-text'></i> 5.重新打印说明</a>
				</div></li>

		</ul>

	</div>
	<div class="content content1">
		<div class="form-actions"
			style="background-color: #fff; border-top: 0px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px;">
			<button type="button" id="more-rollback" class="btn red"
				style="width: 100%; background-color: #f9625b !important;">
				返回上级 <i class="m-icon-swapright m-icon-white"></i>
			</button>

		</div>
		<ul class="ver-inline-menu tabbable margin-bottom-25" id="notice">
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3; text-align: center; font-size: 20px;">安卓需要以下权限：</p>
					<ul class="ver-inline-menu tabbable margin-bottom-25" id="notice">
						<li>1.相机权限</li>
						<li>2.蓝牙权限</li>
						<li>3.地理位置权限</li>
						<li>4.获取手机信息权限</li>
						<li>5.存储权限</li>
					</ul>
				</div></li>

			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3; text-align: center; font-size: 20px;">苹果需要以下权限：</p>
					<ul class="ver-inline-menu tabbable margin-bottom-25" id="notice">
						<li>1.相机权限（设置->隐私->相机）</li>
						<li>2.蓝牙权限（打开蓝牙）</li>
					</ul>
				</div></li>
		</ul>

	</div>
	<div class="content content3">
		<div class="form-actions"
			style="background-color: #fff; border-top: 0px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px;">
			<button type="button" id="more-rollback" class="btn red"
				style="width: 100%; background-color: #f9625b !important;">
				返回上级 <i class="m-icon-swapright m-icon-white"></i>
			</button>

		</div>
		<ul class="ver-inline-menu tabbable margin-bottom-25" id="notice">
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3;">1.输入卡号和密码以后，点击提交出现异常</p>
					<p>请联系管理解决问题</p>
				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3;">2.有效期变成红色字体，无法购票</p>
					<p>说明当前看购卡已过期，不能购票</p>
				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3;">3.购票页面错误</p>
					<p>购票页面默认张数是1,如果想购买多张请选择其他。然后输入购买的张数。单价跟购票操作是一样的。选择完后提交即可</p>
				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3;">4.打印小票</p>
					<p>请先核对打印页面的内容，然后点击打印小票。</p>
					<p>如果您是苹果手机用户，点击打印小票以后会提示您连接蓝牙，连接上蓝牙后点击右上方的打印就可以打印了</p>
					<p>如果您是安卓手机用户，您是第一次打印小票的话会跳转到连接页面。点击扫描设备，看到扫描到设备名称。点击连接即可。连接完成后。在页面点击打印小票，即可打印。</p>
					<P>安卓手机如果您已经连接上打印机，直接打印即可。</P>
				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3;">5.无法打印</p>
					<p>如果您是安卓手机用户，请确认是否打开手机摄像头权限、蓝牙权限、地理位置权限、扫描权限.如果还是不能打印请联系管理员。</p>
					<p>如果您是苹果手机用户，请先查看自己的应用是否为最新版本，如果是最新版本还是不能打印请直接联系管理员解决问题</p>
				</div></li>
		</ul>

	</div>
	<div class="content content4">
		<div class="form-actions"
			style="background-color: #fff; border-top: 0px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px;">
			<button type="button" id="more-rollback" class="btn red"
				style="width: 100%; background-color: #f9625b !important;">
				返回上级 <i class="m-icon-swapright m-icon-white"></i>
			</button>

		</div>
		<ul class="ver-inline-menu tabbable margin-bottom-25" id="notice">
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3;">1.如何选择时间</p>
					<p>点击上方的时间按钮选择时间。如果上面的时间不符合您的要求。可以点击时间框自己选择想要的时间，注意开始时间不能大于于结束时间</p>

				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3;">2.如何查询</p>
					<p>选择完时间后，点击查询按钮</p>
				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3;">3.打印小票</p>
					<p>请先核对打印页面的内容，然后点击打印小票。</p>
					<p>如果您是苹果手机用户，点击打印小票以后会提示您连接蓝牙，连接上蓝牙后点击右上方的打印就可以打印了</p>
					<p>如果您是安卓手机用户，您是第一次打印小票的话会跳转到连接页面。点击扫描设备，看到扫描到设备名称。点击连接即可。连接完成后。在页面点击打印小票，即可打印。</p>
					<P>安卓手机如果您已经连接上打印机，直接打印即可。</P>
				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3;">4.查询明细</p>
					<p>查询明细会根据您在页面选择的时间查询出当前时间段内的所有业务明细，点击加载更多会加载剩余的项</p>
				</div></li>
		</ul>

	</div>
	<div class="content content5">
		<div class="form-actions"
			style="background-color: #fff; border-top: 0px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px;">
			<button type="button" id="more-rollback" class="btn red"
				style="width: 100%; background-color: #f9625b !important;">
				返回上级 <i class="m-icon-swapright m-icon-white"></i>
			</button>

		</div>
		<ul class="ver-inline-menu tabbable margin-bottom-25" id="notice">
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3;">1.打印上一笔</p>
					<p>直接打印出在您登陆的时间内，产生消费的最后一笔。</p>

				</div></li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'><div>
					<p style="color: #e715f3;">2.指定流水号打印</p>
					<p>输入要打印的流水号，点击打印指定。</p>
					<p>如果您是苹果手机用户，点击打印指定以后会提示您连接蓝牙，连接上蓝牙后点击右上方的打印就可以打印了</p>
					<p>如果您是安卓手机用户，您是第一次打印小票的话会跳转到连接页面。点击扫描设备，看到扫描到设备名称。点击连接即可。连接完成后。在页面点击打印小票，即可打印。</p>
					<P>安卓手机如果您已经连接上打印机，直接打印即可。</P>
				</div></li>

		</ul>

	</div>
	<div class="content content2">
		<div class="form-actions"
			style="background-color: #fff; border-top: 0px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px;">
			<button type="button" id="more-rollback" class="btn red"
				style="width: 100%; background-color: #f9625b !important;">
				返回上级 <i class="m-icon-swapright m-icon-white"></i>
			</button>

		</div>
		<ul class="ver-inline-menu tabbable margin-bottom-25" id="notice">
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'>
				<div align="center">
					<p
						style="color: #e715f3; font-size: 20px; margin-top: 20px; width: 95%;"
						align="left">&nbsp;&nbsp;请点击右上角【更多】→【连接打印机】</p>

					<img style="width: 80%; height: 60%;"
						src="<%=path%>/media/image/106.png">

				</div>
			</li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'>
				<div align="center">
					<p
						style="color: #e715f3; font-size: 20px; margin-top: 20px; width: 95%;"
						align="left">&nbsp;&nbsp;点击完成后会出现下面界面，点击扫描设备</p>

					<img style="width: 80%; height: 60%;"
						src="<%=path%>/media/image/107.png">

				</div>
			</li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'>
				<div align="center">
					<p
						style="color: #e715f3; font-size: 20px; margin-top: 20px; width: 95%;"
						align="left">&nbsp;&nbsp;扫描完成后会出现下面界面，点击设备名称</p>
					<p
						style="color: red; font-size: 20px; margin-top: 20px; width: 95%;"
						align="left">特别注意：点击第一个蓝牙名字出现连接失败，请点击第二个蓝牙名称。</p>

					<img style="width: 80%; height: 60%;"
						src="<%=path%>/media/image/108.png">

				</div>
			</li>
			<li
				style='background: #FFFFFF; margin-left: 30px; margin-right: 30px;'>
				<div align="center">
					<p
						style="color: #e715f3; font-size: 20px; margin-top: 20px; width: 95%;"
						align="left">&nbsp;&nbsp;点击设备名称后会出现下面界面，输入连接PIN码。默认是1234或0000</p>


					<img style="width: 80%; height: 60%;"
						src="<%=path%>/media/image/109.png">

				</div>
			</li>
		</ul>

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

	<script src="<%=path%>/media/js/instruction.js?_=<%=System.currentTimeMillis()%>" type="text/javascript"></script>

	
</body>

<!-- END BODY -->

</html>