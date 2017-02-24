<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<script type="text/javascript" src="<%=path%>/js/users/UserCard.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.datagrid-cell, .datagrid-cell-group, .datagrid-header-rownumber,
	.datagrid-cell-rownumber {
	font-size: 16px;
}
</style>
<title></title>
</head>
<body class="easyui-layout" style="font: 14px Microsoft YaHei" oncontextmenu="return false" onselectstart="return false">
	<!-- 查询条件 -->
	<div title="检索条件"
		data-options="region:'north',border:false,collapsible:false,"
		style="height: 60px;" id="TopTitle">
		<div style="margin-top: 5px; margin-left: 10px">
			<span>用户名：</span> <input id="sel_user" name="userId"> <span>卡号：</span>
			<input id="sel_cardnum" class="easyui-validatebox textbox"
				data-options="iconCls:'icon-search'" type="text" size="20px" /> <a
				onclick="GetUserInfo()" style="height: 23px"
				class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	<!-- 查询条件 -->
	<!-- 表格 -->
	<div data-options="region:'center',border:false"
		style="padding: 0px; background: #eee;">
		<div id="tb" class="easyui-layout" data-options="fit:true">
			<div id="tba" align="left" style="padding: 5px; height: 25px">
				<form id="uploadForam" method="post">
					<a class="easyui-linkbutton"
						data-options="iconCls:'icon-add',plain:true"
						onclick="addUserInfo()" id="1"><span
						style="font-size: 17px; font-weight: bold">添加</span></a> <a
						class="easyui-linkbutton"
						data-options="iconCls:'icon-edit',plain:true"
						onclick="updateCard()" id="3"><span
						style="font-size: 17px; font-weight: bold">修改</span></a> <a
						class="easyui-linkbutton"
						data-options="iconCls:'icon-remove',plain:true"
						onclick="delCard()" id="2"><span
						style="font-size: 17px; font-weight: bold">删除</span></a>
				</form>
			</div>
			<div data-options="region:'center',border:false"
				style="background: #eee;">
				<table id="cardInfo" style="background: #eee; margin-top: -5px"></table>
			</div>
		</div>
	</div>
	<!-- 表格 -->
	<!-- 新增用户卡管理 -->
	<div id="dlgSave" title="添加用户卡"
		data-options="iconCls:'icon-edit',draggable:false"
		style="padding: 5px; width: 380px; height: 400px;">
		<div style="margin-left: 20px; margin-top: 15px">
			<form id="addForm" method="post">
				<table align="center">
					<tr style="height: 32px;">
						<td class="td_left">用户名：</td>
						<td><input id="add_user" name="userId" style="width: 150px;"></td>
					</tr>
					<tr style="height: 32px;">
						<td class="td_left">卡号前缀：</td>
						<td><input id="add_userRangeName" name="userRangeName"
							type="text" class="easyui-numberbox"
							data-options="min:0,precision:0"></td>
					</tr>
					<tr>
						<td class="td_left">类型：</td>
						<td><input id="add_cardKindID" class="easyui-combobox"
							name="cardKindID" /></td>
					</tr>
					<tr style="height: 32px;">
						<td class="td_left">是否激活：</td>
						<td><select id="add_userRangeStatus" class="easyui-combobox"
							name="userRangeStatus" style="width: 150px;"
							data-options="required:true,multiple:false,editable : false,">
								<option value="false" selected="selected">未激活</option>
								<option value="true">已激活</option>
						</select></td>
					</tr>

					<tr style="height: 32px;">
						<td class="td_left">开始号段：</td>
						<td><input type="text" id="add_userRangeBegin"
							name="userRangeBegin" maxlength="12" minlength="11"
							onkeyup="this.value=this.value.replace(/\D/g,'')" /> <!-- 
						<input type="text" class="easyui-numberbox" id="add_userRangeBegin" name="userRangeBegin" data-options="min:0,precision:0"></input>  
 --></td>
					</tr>
					<tr style="height: 32px;">
						<td class="td_left">结束号段：</td>
						<td><input type="text" id="add_userRangeEnd"
							name="userRangeEnd" maxlength="12" minlength="11"
							onkeyup="this.value=this.value.replace(/\D/g,'')" /> <!-- 
						<input type="text" class="easyui-numberbox"
							id="add_userRangeEnd" name="userRangeEnd"
							data-options="min:0,precision:0"></input>
							
							
							 --></td>
					</tr>
					<tr style="height: 32px;">
						<td class="td_left">扣款比例:</td>
						<td><input type="text" id="add_cardKindsPrice"
							name="cardKindsPrice" maxlength="12"
							/></td>
					</tr>
					<tr style="height: 32px;">
						<td class="td_left">成本比例:</td>
						<td><input type="text" id="add_cardKindsCost"
							name="cardKindsCost" maxlength="12"
							/></td>
					</tr>
					<tr align="center">
						<td colspan="2"><span id="addalert"
							style="color: rgb(236, 24, 24);"></span></td>

					</tr>
					<tr style="height: 32px;">
						<td colspan="4" align="center" style="padding-top: 10px;"><a
							id="btn" onclick="addInfo()"
							class="table_add_btn easyui-linkbutton"
							data-options="iconCls:'icon-save'">添加</a> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-remove'"
							onclick="$('#dlgSave').dialog('close')">取消</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 修改用户卡管理 -->
	<div id="dlgupdate" title="修改用户卡"
		data-options="iconCls:'icon-edit',draggable:false"
		style="padding: 5px; width: 380px; height: 380px;">
		<div style="margin-left: 20px; margin-top: 15px">
			<form id="updateForm" method="post">
				<table align="center">
					<input type="hidden" id="up_userRangeId" name="userRangeId" />
					<tr style="height: 32px;">
						<td class="td_left">用户名</td>
						<td><input id="update_user" name="userId"
							style="width: 150px;" data-options="required:true"></td>
					</tr>
					<tr style="height: 32px;">
						<td class="td_left">卡号前缀：</td>
						<td><input id="update_userRangeName" name="userRangeName"
							type="text" class="easyui-numberbox"
							data-options="min:0,precision:0"></td>
					</tr>
					<tr>
						<td class="td_left">类型：</td>
						<td><input id="update_cardKindID" class="easyui-combobox"
							name="cardKindID" /></td>
					</tr>

					<tr style="height: 32px;">
						<td class="td_left">是否激活：</td>
						<td><select id="update_userRangeStatus"
							class="easyui-combobox" name="userRangeStatus"
							style="width: 150px;" data-options="required:true,multiple:false">
								<option value="false">未激活</option>
								<option value="true">已激活</option>
						</select></td>
					</tr>

					<tr style="height: 32px;">
						<td class="td_left">开始号段:</td>
						<td><input type="text" id="update_userRangeBegin"
							name="userRangeBegin" maxlength="12"
							onkeyup="this.value=this.value.replace(/\D/g,'')" /></td>
					</tr>
					<tr style="height: 32px;">
						<td class="td_left">结束号段:</td>
						<td><input type="text" id="update_userRangeEnd"
							name="userRangeEnd" maxlength="12"
							onkeyup="this.value=this.value.replace(/\D/g,'')" /></td>
					</tr>
					<tr style="height: 32px;">
						<td class="td_left">扣款比例:</td>
						<td><input type="text" id="update_cardKindsPrice"
							name="cardKindsPrice" maxlength="12"
							/></td>
					</tr>
					<tr style="height: 32px;">
						<td class="td_left">成本比例:</td>
						<td><input type="text" id="update_cardKindsCost"
							name="cardKindsCost" maxlength="12"
							/></td>
					</tr>
					<tr align="center">
						<td colspan="2"><span id="updatealert"
							style="color: rgb(236, 24, 24);"></span></td>

					</tr>
					<tr style="height: 32px;">
						<td colspan="4" align="center" style="padding-top: 10px;"><a
							id="btn" onclick="updateInfo()"
							class="table_up_btn easyui-linkbutton"
							data-options="iconCls:'icon-save'">修改</a> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-remove'"
							onclick="$('#dlgupdate').dialog('close')">取消</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>