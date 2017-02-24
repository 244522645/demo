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
<link rel="stylesheet" type="text/css"
	href="<%=path%>/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/easyui/themes/icon.css" />
<script type="text/javascript" src="<%=path%>/easyui/jquery-3.1.0.js"></script>
<script type="text/javascript"
	src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/card/ transactionLog.js"></script>
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.jpg" type="image/x-icon" />
</head>
<body class="easyui-layout" style="font: 14px Microsoft YaHei"  onselectstart="return false">
	<!-- 查询条件 -->
	<div title="检索条件"
		data-options="region:'north',border:false,collapsible:false,"
		style="height: 60px;" id="TopTitle">
		<div style="margin-top: 5px; margin-left: 10px" align="center">
			<span>开始时间：</span> <input class="easyui-datebox" name="birthday"
				id="startTime" data-options="required:true,showSeconds:false,editable:false"
				value="" style="width: 150px"> <span>结束时间：</span>
			<input class="easyui-datebox" name="birthday" id="endTime"
				data-options="required:true,showSeconds:false,editable:false" value=""
				style="width: 150px"> <a onclick="GetUserInfo()"
				style="height: 23px" class="easyui-linkbutton" iconCls="icon-search">交易查询</a>
			<a onclick="delay()" style="height: 23px"
				class="easyui-linkbutton" iconCls="icon-search">延期查询</a>
		</div>
	</div>
	<!-- 查询条件 -->
	<!-- 表格 -->
	<div data-options="region:'center',border:false"
		style="padding: 0px; background: #eee;">
		<div id="tb" class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false"
				style="background: #eee;">
				<table id="cardInfo" style="background: #eee; margin-top: -5px"></table>
			</div>
		</div>
	</div>

</body>
</html>