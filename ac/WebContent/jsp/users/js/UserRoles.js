var dlgUpdate;
var dlgSave;
var areadatagrid;
var groupUpdate;
var rightsModel;
var userroles;

var cboData = new Array();
$(function() {
	$.post("../../KUserRoles/findAllRights.do", function(data) {
		rightsModel = data;
	});
	dlgUpdate = $('#dlgUpdate').show().dialog({
		modal : true,
		
		draggable : true,
		closed : true
	});
	dlgSave = $('#dlgSave').show().dialog({
		modal : true,
		draggable : true,
		closed : true
	});
	groupUpdate = $('#groupUpdate').show().dialog({
		modal : true,
		draggable : true,
		closed : true
	});
	GetUserInfo();
	// initCbo(cboData, "#role_id1");
});
// 打开添加框
function addUserInfo() {
	dlgSave.dialog('open');
	$('#addForm').form().form('clear');
	$("#addForm .table_add_btn").linkbutton('enable');// 初始化保存点击事件
}

// 打开修改框
function updateInfo() {
	$("#updateForm .table_up_btn").linkbutton('enable');// 修改按钮初始化
	var rows = areadatagrid.datagrid("getChecked");
	if (rows.length > 0 && rows.length < 2) {

		dlgUpdate.dialog('open');
		$('#roleKind').combobox('select', rows[0].roleKind);
		$("#roleName").val(rows[0].roleName);
		$("#roleDescription").textbox('setText', rows[0].roleDescription);
		$("#roleId").val(rows[0].roleId);

	} else if (rows.length < 1) {
		$.messager.alert('提示', '请选择要修改的数据!', 'error');
	} else {
		$.messager.alert('提示', '请选择一行数据!', 'error');
	}
}

// 提交修改
function updateUserInfo() {
	if ($("#updateForm").form("validate")) {
		$("#updateForm .table_up_btn").linkbutton('disable');// 修改按钮失效防止重复点击
	}
	$("#updateForm").form({
		url : '../../KUserRoles/updateRole.do',
		success : function(data) {

			if (data==0) {
				$.messager.alert('提示', '修改成功！');
				areadatagrid.datagrid('load');
				dlgUpdate.dialog('close');
			}else{
				$.messager.alert('提示', '修改失败！');
				areadatagrid.datagrid('load');
				dlgUpdate.dialog('close');
			}
		}
	});
	$('#updateForm').submit();
}

// 提交添加
function addInfo() {
	if ($("#addForm").form("validate")) {
		$("#addForm .table_add_btn").linkbutton('disable');// 保存按钮失效防止重复点击
	}
	$("#addForm").form({
		url : '../../KUserRoles/addRole.do',
		success : function(data) {
			if (data == 0) {
				$.messager.alert('提示', '添加成功！');
				areadatagrid.datagrid('load');
				areadatagrid.datagrid('clearSelections');
				areadatagrid.datagrid('clearChecked');
				dlgSave.dialog('close');
			} else {
				$.messager.alert('提示', '程序异常,请联系服务人员！');
			}
		}
	});
	$('#addForm').submit();
}

function GetUserInfo() {
	areadatagrid = $('#UserInfo')
			.datagrid(
					{
						url : "../../KUserRoles/findRoleBy.do",
						fit : true,
						rownumbers : false,
						singleSelect : false,
						ctrlSelect : true,
						autoRowHeight : false,
						pagination : true,
						checkOnSelect : true,
						nowrap : true,
						method : 'post',
						fitColumns : true,
						pageSize : 15,
						pageList : [ 15, 25, 35, 45 ],

						sortOrder : "desc",
						toolbar : '#tba',
						remoteSort : false,
						title : "角色管理",

						columns : [ [
								{
									field : 'roleKind',
									title : '用户类别',
									width : 100,
									checkbox : true,
									formatter : function(value, row, index) {
										if (row.roleKind == 0) {
											return '管理员';
										} else if (row.roleKind == 1) {
											return '代理商';
										} else if (row.roleKind == 2) {
											return '加盟商';
										} else if (row.roleKind == 3) {
											return '个人加盟';
										} else if (row.roleKind == 6) {
											return '财务';
										}
									}
								},
								{
									field : 'roleName',
									title : '角色名',
									width : 100,
								},
								{
									field : 'roleDescription',
									title : '角色描述',
									width : 100,

								},
								{
									field : 'roleAddDate',
									title : '添加时间',
									width : 100,
									formatter : function(value, row, index) {
									
										return getLocalTime(row.roleAddDate);
									}
								},
								{
									field : '权限分配',
									title : '操作',
									width : 100,
									align : 'center',
									formatter : function(value, row, index) {
										return '</span><a  style="list-style-type:none;margin-bottom: 10px;color:rgb(38, 81, 231)" href="javascript:groups(\''
												+ row.roleId + '\');">权限分配</a>';
									}
								} ] ]

					});
}
// 权限分配窗口打开
function groups(id) {
	var param = {
		roleId : id
	}

	$("#groupForm .table_add_btn").linkbutton('enable');// 修改按钮初始化
	$("#groupTable").empty();
	groupUpdate.dialog('open');

	$('#groupForm').form().form('clear');

	$("#rolerightid").val(id);
	$
			.each(
					rightsModel,
					function(i, v) {
						var tr = $("<tr ></tr>");
						var td1;
						var td2 = $("<td></td>");
						if (!v.rightascription) {
							td1 = "<td style='width:10%;border:solid 1px #add9c0;background: rgb(197, 213, 213) none repeat scroll 0% 0%;'><input id="
									+ v.rightId
									+ " name='rightid' onclick='zhucaidan(this.checked,this.value)' type='checkbox' value='"
									+ v.rightId + "'/>" + v.rightName + "</td>";
									var table=$("<table></table>");
									var tr1=$("<tr></tr>");
							$
									.each(
											rightsModel,
											function(p, o) {
												if (v.rightId == o.rightascription) {
													tr1
															.append("<td style='width:10%;border:solid 1px #add9c0;background: rgb(177, 219, 242) none repeat scroll 0% 0%;'><input id="
																	+ o.rightId
																	+ " name='rightid' type='checkbox' onclick='panduan(\'this.checked/',/'"+v.rightId+"/')' value='"
																	+ o.rightId
																	+ "'/>"
																	+ o.rightName+"</td>");
												}
											});
							table.append(tr1);
							td2.append(table);
							tr.append(td1);
							tr.append(td2);
							$("#groupTable").append(tr);
						}
					});
	userroles = "";
	$.post("../../KUserRoles/findRoleRights.do", param, function(data) {
		var checkbox = $("#groupTable :checkbox");
		$.each(checkbox, function(i, v) {
			$.each(data, function(j, n) {
				if (v.value == n) {
					$("#" + v.value).prop("checked", true);
				}
			});
		});
	});
}

// 主菜单不选
function zhucaidan(checked, value) {

	if (checked == false) {
		$
		.each(
				rightsModel,
				function(i, v) {
					if(v.rightascription==value){
						$("#"+v.rightId).prop("checked", false);
					}
				});
	}
}
//点击子菜单
function panduan(value,checked){
	
	if($("#"+checked).prop("checked")==false){
		alert("主菜单没有选中");
	}
}

// 权限窗口提交
function updateRights() {
	if ($("#groupForm").form("validate")) {
		$("#groupForm .table_add_btn").linkbutton('disable');// 保存按钮失效防止重复点击
	}

	$("#groupForm").form({
		url : '../../KUserRoles/updateRoleRight.do',
		success : function(data) {
			if (data == 0) {
				$.messager.alert('提示', '修改成功！');
				areadatagrid.datagrid('load');
				areadatagrid.datagrid('clearSelections');
				areadatagrid.datagrid('clearChecked');
				groupUpdate.dialog('close');
			} else {
				$.messager.alert('提示', '程序异常,请联系服务人员！');
			}
		}
	});
	$('#groupForm').submit();
}
// 全选
function selectAll() {
	$("#groupForm input").prop("checked", true);
}

// 取消全部
function noselectall() {
	$("#groupForm input").prop("checked", false);
}
// 格式化日期时间戳
function getLocalTime(obj) {
	return (obj.year + 1900) + '年' + (obj.month + 1) + '月' + obj.date + '日   '
			+ obj.hours + '时' + obj.minutes + '分' + obj.seconds + '秒';

}