<%@page import="com.model.KUser"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="images/favicon.jpg" type="image/x-icon" />
<title>首页</title>


<link rel="stylesheet" type="text/css"
	href="<%=path%>/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css" />

<script type="text/javascript" src="<%=path%>/easyui/jquery-3.1.0.js"></script>
<script type="text/javascript"
	src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"
	charset="utf-8"></script>
</head>


<body class="easyui-layout" style="overflow-y: hidden" scroll="no" oncontextmenu="return false" onselectstart="return false">
	<!-- 顶部 -->
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 110px; width: 100%; line-height: 20px; color: rgb(255, 255, 255); font-family: 宋体, 黑体; background: #1DA5CD; background-size: 100%;">
		<span
			style="font-size: 300%; color: rgb(255, 255, 255); margin-left: 5%; line-height: 218%;">
			欢迎使用看购AC管理系统 </span> <span
			style="float: right; font-size: 140%; padding-right: 20px;display: inline-block;margin-top: 0.6%;"
			class="head">欢迎 ${sessionScope.username.username} 登录 <a
			class="easyui-linkbutton" onclick="updatePassword()" id="editpass">修改密码 </a>
			<a onclick="out()" class="easyui-linkbutton" id="loginOut">安全退出</a>
		</span> <span
			style=" font-size: 140%; color: rgb(255, 255, 255);margin-left: 5%; line-height: 218%;display: inline-block;"
			id="clock"></span>
	</div>
	<!-- 顶部 -->

	<!-- 底部 -->
	<div data-options="region:'south',split:true"
		style="height: 30px; background: #D2E0F2;">
		<div class="footer" align="center">北京看购科技有限公司 @ 版权所有 2007-2016
			服务热线：400-677-6501</div>
	</div>
	<!-- 底部 -->
	<!-- 内容页面 -->
	<div data-options="region:'center'" style="width: 80%;">
		<iframe id="mainFrame"
			style="width: 99%; height: 99%;" frameborder="no" border="0"
			marginwidth="0" marginheight="0" scrolling="no"></iframe>

	</div>
	<!-- 内容页面 -->
	<!-- 菜单内容 -->
	<div region="west" split="true" title="导航菜单" style="width: 200px;"
		id="west">
		<div class="easyui-accordion" fit="true" border="false"
			style="overflow: auto;">
			<!--  导航内容 -->
			<ul class="mainmenu">
			</ul>
		</div>

	</div>
	<!-- 
	<div region="west" split="true" title="首页"  align="center"
		style="width: 15%; height: 100%; font-size: 20px;" id="west">
		<div id="menus" class="easyui-accordion" style="height: 100%;" align="center">

		</div>

	</div>
	 -->
	<!-- 菜单内容 -->

	<!--修改密码窗口-->
	<div id="updatepassword" class="easyui-window" title="修改密码"
		collapsible="false" minimizable="false" maximizable="false"
		icon="icon-save"
		style="width: 300px; height: 200px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					onclick="updateword()"> 确定</a> <a class="easyui-linkbutton"
					icon="icon-cancel" onclick="$('#updatepassword').dialog('close')">取消</a>
			</div>
		</div>
	</div>
	<!-- 修改密码窗口 -->
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				var $submenu = $('.submenu');
				var $mainmenu = $('.mainmenu');
				$submenu.hide();
				$submenu.first().delay(400).slideDown(700);
				$submenu.on('click', 'li', function() {
					$submenu.siblings().find('li').removeClass('chosen');
					$(this).addClass('chosen');
				});
				$mainmenu.on('click', 'li', function() {
					$(this).next('.submenu').slideToggle().siblings('.submenu')
							.slideUp();
				});
				$mainmenu.children('li:last-child').on('click', function() {
					$mainmenu.fadeOut().delay(500).fadeIn();
				});
			});
</script>
</html>