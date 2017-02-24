var carddatagrid;
var dlgSave;
var dlgupdate;
var user;
$(function() {
	findAll();
	GetUserInfo();
});
// 获取用户
function findAll() {
	dlgSave = $('#dlgSave').show().dialog({
		modal : true,
		draggable : true,
		closed : true
	});
	dlgupdate = $('#dlgupdate').show().dialog({
		modal : true,
		draggable : true,
		closed : true
	});
	$.post("KUser/selectUser.do", function(data) {
		user = data;
	});
	$('#sel_user').combobox({
		url : 'KUser/selectUser.do',
		valueField : 'id',
		textField : 'username',
		editable : false
	});
	$('#add_user').combobox({
		url : 'KUser/selectUser.do',
		valueField : 'id',
		textField : 'username',
		editable : false
	});
	$('#update_user').combobox({
		url : 'KUser/selectUser.do',
		valueField : 'id',
		textField : 'username',
		editable : false
	});
}
// 打开添加框
function addUserInfo() {
	
	dlgSave.dialog('open');
	$('#addForm').form().form('clear');
	$('#add_cardKindID').combobox({
		url : './KCardKinds/findall.do',
		valueField : 'id',
		textField : 'cardKindsName',
		editable : false,
		onLoadSuccess : function(data) {
			
			$('#add_cardKindID').combobox('setValue', data[0].id);
		}
	});
	$('#add_userRangeStatus').combobox('select', 'false');
	// $("#addForm .table_add_btn").linkbutton('enable');// 初始化保存点击事件
	// $("#add_userRangeEnd").focusout(function(){
	//	
	// });

	// $("#add_userRangeName").numberbox({
	// "onChange" : function(row) {
	// if (row.length > 11) {
	// $.messager.show({
	// title : '提示消息',
	// msg : '卡号过长，请重新输入',
	// timeout : 5000,
	// showType : 'slide'
	// });
	// $("#add_userRangeName").numberbox('clear');
	// }
	// }
	// });
	// $("#add_userRangeBegin").numberbox({
	// "onChange" : function(row) {
	// if (row.length > 11) {
	// $.messager.show({
	// title : '提示消息',
	// msg : '开始号段过长，请重新输入',
	// timeout : 5000,
	// showType : 'slide'
	// });
	// $("#add_userRangeBegin").numberbox('clear');
	// }
	// }
	// });
	// $("#add_userRangeEnd").numberbox({
	// "onChange" : function(row) {
	// if (row.length > 11) {
	// $.messager.show({
	// title : '提示消息',
	// msg : '结束号段过长，请重新输入',
	// timeout : 5000,
	// showType : 'slide'
	// });
	// $("#add_userRangeEnd").numberbox('clear');
	// } else if (row < $("#add_userRangeBegin").numberbox('getValue')) {
	// $.messager.show({
	// title : '提示消息',
	// msg : '结束不能小于开始号段，请重新输入',
	// timeout : 5000,
	// showType : 'slide'
	// });
	// $("#add_userRangeEnd").numberbox('clear');
	// }
	// }
	// });
}

// 提交添加
function addInfo() {
	
	var adduser = $("#add_user").combobox('getValue');

	if (!adduser) {
		$("#addalert").text("请选择用户");
		return;
	} else {
		$("#addalert").text("");
	}
	var add_userRangeName = $("#add_userRangeName").numberbox('getValue');
	if (!add_userRangeName) {
		$("#addalert").text("请输入卡号前缀");
		return;
	} else {
		$("#addalert").text("");
	}
	if (!$("#add_userRangeBegin").val()) {
		$("#addalert").text("请填写开始字段");
		return;
	} else {
		$("#addalert").text("");
	}
	if (!$("#add_userRangeEnd").val()) {
		$("#addalert").text("请填写结束字段");
		return;
	} else {
		$("#addalert").text("");
	}
	if(add_userRangeName!=(($("#add_userRangeEnd").val()).substring(0,4))&&add_userRangeName!=(($("#add_userRangeBegin").val()).substring(0,4))){
		$("#addalert").text("开始号段或结束号段的前缀和卡号前缀不同");
		return;
	}else{
		$("#addalert").text("");
	}
	if (($("#add_userRangeEnd").val()).substring(0,4)!=($("#add_userRangeBegin").val()).substring(0,4)) {
		$("#addalert").text("开始号段和结束号段的前缀不同");
		return;
	} else {
		$("#addalert").text("");
	}
	if (parseInt($("#add_userRangeEnd").val()) < parseInt($("#add_userRangeBegin").val())) {
		$("#addalert").text("结束号段不能小于开始号段");
		return;
	} else {
		$("#addalert").text("");
	}
	if(!$("#add_cardKindsPrice").val()&&!$("add_cardKindsCost").val()){
		$("#addalert").text("价格或成本不能为空");
		return;
	}else{
		$("#addalert").text("");
	}
	
	 if ($("#addForm").form("validate")) {
	 $("#addForm .table_add_btn").linkbutton('disable');// 保存按钮失效防止重复点击
	 }
	$("#addForm").form('submit', {
		url : 'UserCard/addCard.do',
		success : function(data) {
			if (data == 0) {
				$.messager.alert('提示', '添加成功！');
				carddatagrid.datagrid('load');
				carddatagrid.datagrid('clearSelections');
				carddatagrid.datagrid('clearChecked');
				dlgSave.dialog('close');
			} else {
				$.messager.alert('提示', '程序异常,请联系服务人员！');
			}
		}
	});

}

// 打开修改框
function updateCard() {

	$("#updateForm .table_up_btn").linkbutton('enable');// 修改按钮初始化
	var rows = carddatagrid.datagrid("getChecked");
	
	if (rows.length > 0 && rows.length < 2) {

		dlgupdate.dialog('open');
		$('#update_cardKindID').combobox({
			url : './KCardKinds/findall.do',
			valueField : 'id',
			textField : 'cardKindsName',
			editable : false,
			onLoadSuccess : function(data) {
				for(var i=0;i<data.length;i++){
					if(data[i].cardKindsName==rows[0].cardKindsName){
						$('#update_cardKindID').combobox('select', data[i].id);
					}
				}
			
				
			}
		});
		for (var i = 0; i < user.length; i++) {
			if (rows[0].username == user[i].username) {
				$("#update_user").combobox('select', user[i].id);
			}
		}
		
		$("#up_userRangeId").val(rows[0].UserRangeID);

		$("#update_userRangeName").numberbox('setValue', rows[0].UserRangName);
		if (rows[0].UserRangeStatus == false) {
			$("#update_userRangeStatus").combobox('select', 'false');
		} else {
			$("#update_userRangeStatus").combobox('select', 'true');
		}
		
		$("#update_userRangeBegin").val(rows[0].UserRangeBegin);
		$("#update_userRangeEnd").val(rows[0].UserRangEnd);
		$("#update_cardKindsPrice").val(rows[0].cardKindsPrice);
		$("#update_cardKindsCost").val(rows[0].cardKindsCost);
	} else if (rows.length < 1) {
		$.messager.alert('提示', '请选择要修改的数据!', 'error');
	} else {
		$.messager.alert('提示', '请选择一行数据!', 'error');
	}
}

// 提交修改框
function updateInfo() {
	var adduser = $("#update_user").combobox('getValue');

	if (!adduser) {
		$("#updatealert").text("请选择用户");
		return;
	} else {
		$("#updatealert").text("");
	}
	var add_userRangeName = $("#update_userRangeName").numberbox('getValue');
	if (!add_userRangeName) {
		$("#updatealert").text("请输入卡号前缀");
		return;
	} else {
		$("#updatealert").text("");
	}
	if (!$("#update_userRangeBegin").val()) {
		$("#updatealert").text("请填写开始字段");
		return;
	} else {
		$("#updatealert").text("");
	}
	if (!$("#update_userRangeEnd").val()) {
		$("#updatealert").text("请填写结束字段");
		return;
	} else {
		$("#updatealert").text("");
	}
	if(add_userRangeName!=(($("#update_userRangeEnd").val()).substring(0,4))&&add_userRangeName!=(($("#update_userRangeBegin").val()).substring(0,4))){
		$("#updatealert").text("开始号段或结束号段的前缀和卡号前缀不同");
		return;
	}else{
		$("#updatealert").text("");
	}
	if (($("#update_userRangeEnd").val()).substring(0,4)!=($("#update_userRangeBegin").val()).substring(0,4)) {
		$("#updatealert").text("开始号段和结束号段的前缀不同");
		return;
	} else {
		$("#updatealert").text("");
	}
	if ( parseInt($("#update_userRangeEnd").val()) <= parseInt($("#update_userRangeBegin").val())) {
		$("#updatealert").text("结束号段不能小于等于开始号段");
		return;
	} else {
		$("#updatealert").text("");
	}
	if(!$("#update_cardKindsPrice").val()&&!$("update_cardKindsCost").val()){
		$("#updatealert").text("价格或成本不能为空");
		return;
	}else{
		$("#updatealert").text("");
	}
	if ($("#updateForm").form("validate")) {
		$("#updateForm .table_up_btn").linkbutton('disable');// 修改按钮失效防止重复点击
	}
	$("#updateForm").form({
		url : 'UserCard/updateCard.do',
		success : function(data) {

			if (data) {
				$.messager.alert('提示', '修改成功！');
				carddatagrid.datagrid('load');
				dlgupdate.dialog('close');
			}
		}
	});
	$('#updateForm').submit();
}

// 多条数据的删除
function delCard() {
	var rows = carddatagrid.datagrid("getChecked");
	var array = [];
	for (var i = 0; i < rows.length; i++) {
		array.push(rows[i].UserRangeID);

	}
	var param = {
		array : array
	};
	
	$.post("UserCard/delCard.do", param, function(data) {
		if (data == 0) {
			$.messager.alert('提示', '删除成功！');
			carddatagrid.datagrid('load');
		} else {
			$.messager.alert('提示', '删除失败，请联系管理员!');
		}
	});
}

// 获取用户卡片的情况
function GetUserInfo() {
	carddatagrid = $('#cardInfo').datagrid({
		url : "UserCard/findByPage.do",
		fit : true,
		rownumbers : true,
		striped:true,
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
		title : "用户卡管理",
		queryParams : {
			userid : $('#sel_user').combobox('getValue'),
			cardnum : $('#sel_cardnum').val()
		},
		columns : [ [ {
			field : 'xunze',

			checkbox : true,
		}, {
			field : 'username',
			title : '用户',
			width : 100,

		}, {
			field : 'UserRangName',
			title : '卡号前缀',
			width : 100,
		}, {
			field : 'UserRangeStatus',
			title : '是否激活',
			width : 100,
			formatter : function(value, row, index) {
				
				if (row.UserRangeStatus == false) {
					return "未激活";
				} else {
					return "已激活";
				}
			}
		},
		{
			field : 'cardKindsName',
			title : '卡的种类',
			width : 100
		},
		{
			field : 'UserRangeBegin',
			title : '开始号段',
			width : 100
		}, {
			field : 'UserRangEnd',
			title : '结束号段',
			width : 100
		}, {
			field : 'cardKindsPrice',
			title : '扣款比例',
			width : 100
		}, {
			field : 'cardKindsCost',
			title : '成本比例',
			width : 100
		} ] ]

	});
}
