<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<%=path%>/media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<script src="<%=path%>/media/js/bootstrap.min.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>

	<div class="content">

		<div align="center">
			<p style="color: #e715f3; font-size: 20px; margin-top: 20px;width: 95%;" align="left">
				&nbsp;&nbsp;请点击右上角【更多】→【扫一扫】</p>
			
			<img style="width: 90%; height: 60%; "
				src="<%=path%>/media/image/103.png">

		</div>



		<div style="margin-top: 10px;">
			<ul class="ver-inline-menu tabbable"
				style="text-align: left; list-style-type: none;">
				<li
					style='background: #FFFFFF;  list-style-type: none;'><div>
						<p style="color: #e715f3; font-size: 20px;">安卓需要以下权限：</p>
						<ul class="ver-inline-menu tabbable"
							style="list-style-type: none;">
							<li>1.相机权限</li>
							<li>2.蓝牙权限</li>
							<li>3.地理位置权限</li>
							<li>4.获取手机信息权限</li>
							<li>5.存储权限</li>
						</ul>
					</div></li>

				<li
					style='background: #FFFFFF;margin-top: 10px;'><div>
						<p style="color: #e715f3; font-size: 20px;">苹果需要以下权限：</p>
						<ul class="ver-inline-menu tabbable "
							style="list-style-type: none;">
							<li>1.相机权限（设置->隐私->相机）</li>
							<li>2.蓝牙权限（打开蓝牙）</li>
						</ul>
					</div></li>
			</ul>
		</div>
	</div>
</body>
</html>