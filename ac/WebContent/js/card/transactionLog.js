$(function() {
	GetUserInfo();

});

// 获取用户交易记录
function GetUserInfo() {
	$('#cardInfo').datagrid({
		url : "KCardOrders/findOrdersByUser.do",
		fit : true,
		rownumbers : true,
		striped : true,
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
		remoteSort : false,
		title : "用户卡管理",
		queryParams : {
			startTime : $('#startTime').datebox('getText'),
			endTime : $('#endTime').datebox('getText')
		},
		columns : [ [ {
			field : 'StartCardNum',
			title : '起始号段',
			width : 100,

		}, {
			field : 'EndCardNum',
			title : '结束号段',
			width : 100,
		}, {
			field : 'OrdersDate',
			title : '开卡时间',
			width : 140,
		}, {
			field : 'ClosingDate',
			title : '到期时间',
			width : 140,
		}, {
			field : 'CardKindsName',
			title : '卡的类型 ',
			width : 100,
		}, {
			field : 'CardOrdersType',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
				if (row.CardOrdersType == 1) {
					return "开卡";
				}
				if (row.CardOrdersType == 2) {
					return "充值";
				}
				if (row.CardOrdersType == 3) {
					return "退卡";
				}
			}

		}, {
			field : 'InvoiceID',
			title : '发票',
			width : 100,
			formatter : function(value, row, index) {
				if (row.InvoiceID == 0) {
					return "开";
				}
				if (row.InvoiceID == 1) {
					return "不开";
				}

			}
		}, {
			field : 'DanJia',
			title : '单价',
			width : 100
		}, {
			field : 'DianShu',
			title : '点数（次数）',
			width : 100
		}, {
			field : 'ShuLiang',
			title : '数量',
			width : 100
		}, {
			field : 'Money',
			title : '金额',
			width : 100
		}, {
			field : 'UserName',
			title : '开卡人',
			width : 100
		} ] ]

	});
}

// 延期记录查询
function delay() {
	$('#cardInfo').datagrid({
		url : "KCardOrders/findDeadlineByPage.do",
		fit : true,
		rownumbers : true,
		striped : true,
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
		remoteSort : false,
		title : "用户卡管理",
		queryParams : {
			startTime : $('#startTime').datebox('getText'),
			endTime : $('#endTime').datebox('getText')
		},
		columns : [ [ {
			field : 'StartCardNum',
			title : '起始号段',
			width : 100,

		}, {
			field : 'EndCardNum',
			title : '结束号段',
			width : 100,
		}, {
			field : 'SubmitDate',
			title : '修改时间',
			width : 140,
		}, {
			field : 'BeforeDate',
			title : '修改前时间',
			width : 140,
		}, {
			field : 'AfterDate',
			title : '修改后时间 ',
			width : 100,
		},{
			field : 'UserName',
			title : '开卡人',
			width : 100
		} ] ]

	});
}
