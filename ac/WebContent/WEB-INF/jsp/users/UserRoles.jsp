
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/easyui/themes/icon.css" />
<script type="text/javascript" src="<%=path%>/easyui/jquery-3.1.0.js"
	charset="utf-8"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>

<link href="<%=path%>/css/users/default.css" rel="stylesheet" type="text/css" />
</head>
<body class="easyui-layout" style="font: 14px Microsoft YaHei" oncontextmenu="return false" onselectstart="return false">
	<div data-options="region:'center',border:false"
		style="padding: 0px; background: #eee;">
		<div id="tb" class="easyui-layout" data-options="fit:true">
			<div id="tba" align="left" style="padding: 5px; height: 25px">
				<a class="easyui-linkbutton"
					data-options="iconCls:'icon-save',plain:true"
					onclick="addUserInfo()" id="1"><span
					style="font-size: 17px; font-weight: bold">添加</span></a> <a
					class="easyui-linkbutton"
					data-options="iconCls:'icon-edit',plain:true"
					onclick="updateInfo()" id="3"><span
					style="font-size: 17px; font-weight: bold">修改</span></a>
			</div>

			<div data-options="region:'center',border:false"
				style="background: #eee;">
				<table id="UserInfo" style="background: #eee; margin-top: -5px"></table>
			</div>

		</div>
	</div>

	<!-- 新增用户信息 -->
	<div id="dlgSave" title="添加用户信息"
		data-options="iconCls:'icon-edit',draggable:false"
		style="padding: 5px; width: 380px; height: 370px;">
		<div style="margin-left: 20px; margin-top: 15px">
			<form id="addForm" method="post">
				<table align="center">
					<tr style="height: 32px;">
						<td class="td_left">角色种类：</td>
						<td><select id="roleKind" class="easyui-combobox"
							name="roleKind" data-options="required:true"
							style="width: 152px;">
								<option value="0">管理员</option>
								<option value="1">代理商</option>
								<option value="2">加盟商</option>
								<option value="3">个人加盟</option>
								<option value="6">财务</option>
						</select></td>
					</tr>
					<tr style="height: 32px;">
						<td class="td_left">角色名：</td>
						<td><input type="text" class="easyui-validatebox"
							data-options="required:true"
							style="border: 1px solid #8DB2E3; width: 150px; height: 20px"
							id="roleName" name="roleName" /></td>
					</tr>
					<!-- 
					<tr style="height: 32px;">
						<td class="td_left">添加时间：</td>
						<td><input class="easyui-datetimebox" name="birthday"
							data-options="required:true,showSeconds:false"
							style="width: 152px"></td>
					</tr>
					 -->
					<tr style="height: 32px;">
						<td class="td_left">描述</td>
						<td><input type="text" class="easyui-textbox"
							style="border: 1px solid #8DB2E3; width: 200px; height: 60px"
							name="roleDescription" id="roleDescription"
							data-options="multiline:true" /></td>
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


	<!-- 修改用户信息 -->
	<div id="dlgUpdate" title="编辑用户信息"
		data-options="iconCls:'icon-edit',closed:true,draggable:false"
		style="width: 30%; height: 270px; padding-left: 0px; display: none;">
		<div>
			<form id="updateForm" method="post">
				<input type="hidden" id="roleId" name='roleId' />
				<table align="center">

					<tr style="height: 32px;">
						<td class="td_left">角色种类：</td>
						<td><select id="roleKind" class="easyui-combobox"
							name="roleKind" data-options="required:true"
							style="width: 152px;">
								<option value="0">管理员</option>
								<option value="1">代理商</option>
								<option value="2">加盟商</option>
								<option value="3">个人加盟</option>
								<option value="6">财务</option>
						</select></td>
					</tr>
					<tr style="height: 32px;">
						<td class="td_left">角色名：</td>
						<td><input type="text" class="easyui-validatebox"
							data-options="required:true"
							style="border: 1px solid #8DB2E3; width: 150px; height: 20px"
							id="roleName" name="roleName" /></td>
					</tr>
					<!-- 
					<tr style="height: 32px;">
						<td class="td_left">添加时间：</td>
						<td><input class="easyui-datetimebox" name="birthday"
							data-options="required:true,showSeconds:false"
							style="width: 152px"></td>
					</tr>
					 -->
					<tr style="height: 32px;">
						<td class="td_left">描述</td>
						<td><input type="text" class="easyui-textbox"
							style="border: 1px solid #8DB2E3; width: 200px; height: 60px"
							name="roleDescription" id="roleDescription"
							data-options="multiline:true" /></td>
					</tr>
					<tr style="height: 32px;">
						<td colspan="4" align="center" style="padding-top: 10px;"><a
							id="btn" onclick="updateUserInfo()"
							class="table_add_btn easyui-linkbutton"
							data-options="iconCls:'icon-save'">修改</a> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-remove'"
							onclick="$('#dlgUpdate').dialog('close')">取消</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<!-- 修改用户组权限 -->
	<div id="groupUpdate" title="编辑用户权限"
		data-options="iconCls:'icon-edit',closed:true,draggable:false"
		style="width: 70%; height: 60%; padding-left: 0px; display: none; border-collapse: collapse;">
		<div>
			<form id="groupForm" method="post">
				<input type="hidden" name="rolerightid" id="rolerightid" />
				<table id="groupTable" style=" width:99%;  border: 1px solid rgb(173, 217, 192); margin-top: 10px; margin-left: 10px; margin-right: 10px;">
				</table>
				<table align="center" >
					<tr style="height: 32px;">
						<td colspan="4" align="center" style="padding-top: 10px;"><a
							id="btn" onclick="updateRights()"
							class="table_add_btn easyui-linkbutton"
							data-options="iconCls:'icon-save'">修改</a> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-remove'"
							onclick="$('#groupUpdate').dialog('close')">取消</a> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-edit'" onclick="selectAll()">全选</a> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-edit'" onclick="noselectall()">不选</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>


</body>
<script type="text/javascript" src="<%=path%>/js/users/UserRoles.js"></script>
</html>