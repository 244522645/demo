var card;
var cardType;

// 获取服务器时间
function systemTime() {
	$.post("systemTime.do", function(data) {
		$("#systemtime").html(data);
	});
}


//初始化下拉框
function init() {
	$('#add_cardKindsId').combobox({
		url : './UserCard/findByUser.do',
		valueField : 'id',
		textField : 'CardKindsName',
		editable : false,
		onLoadSuccess : function(data) {
			$('#add_cardKindsId').combobox('setValue', "");
		},
		onSelect : function(data) {
			cardType = data.id;
			card = data;
			
		}
	});

}

$(function(){
	systemTime();
	init();
	
});


//开始号段验证
function chengben(data) {
	var cardKingsId = $("#add_cardKindsId").combobox('getValue');
	if (!cardKingsId) {
		$.messager.alert('提示', '请先选择卡类型', 'info');
		return;
	}
	if (data.value.length < 11) {
		$.messager.alert('提示', '号段输入错误，请重新输入', 'info');
		return;
	}

}

//结束号段验证并计算
function sum(data) {
	var cardKingsId = $("#add_cardKindsId").combobox('getValue');
	if (!cardKingsId) {
		$.messager.alert('提示', '请先选择卡类型', 'info');
		return;
	}
	if (data.value.length < 11) {
		$.messager.alert('提示', '号段输入错误，请重新输入', 'info');
		return;
	}
	var startCardNum = $("#add_startCardNum").val();
	var endCardNum = $("#add_endCardNum").val();
	if (endCardNum <= startCardNum) {
		$.messager.alert('提示', '结束号段不能小于开始号段', 'info');
		return;
	}

	var startcard = startCardNum.substring(0, 4);
	var endcard = endCardNum.substring(0, 4);
	if (startcard != endcard) {
		$.messager.alert('提示', '开始号段前缀和结束号段前缀不一致', 'info');
		return;
	}

	var i = 0;
	$.post("./UserCard/findRangsByUser.do", function(data) {
		for (var i = 0; i < data.length; i++) {
			if (data[i].cardKindID == cardKingsId) {
				
				if (startCardNum < data[i].userRangeBegin) {
					$.messager.alert('提示', '开始号不能小于你的起始号段', 'info');
					return;
				} else {
					if (data[i].userRangeEnd < endCardNum) {
						$.messager.alert('提示', '结束号不能大于于你的结束号段', 'info');
						return;
					} else {
						i = 1;
					}
				}
			}
		}
	});
	var param = {
		"startCardNums" : startCardNum,
		"endCardNum" : endCardNum
	}
	$.post("./KCardOrders/findByCardnum.do", param, function(data) {
		if (data == 1) {
			$.messager.alert('提示', '号段已被使用，请联系管理员更换号段', 'info');
			return;
		}
	});
	if (i = 1) {
		$("#add_shuLiang").val(endCardNum - startCardNum + 1);
	} else {
		$.messager.alert('提示', '输入的号段没有开通，请联系管理员开通', 'info');
	}
}


//结束号段验证并计算
function sum(data) {
	var cardKingsId = $("#add_cardKindsId").combobox('getValue');
	if (!cardKingsId) {
		$.messager.alert('提示', '请先选择卡类型', 'info');
		return;
	}
	if (data.value.length < 11) {
		$.messager.alert('提示', '号段输入错误，请重新输入', 'info');
		return;
	}
	var startCardNum = $("#add_startCardNum").val();
	var endCardNum = $("#add_endCardNum").val();
	if (endCardNum < startCardNum) {
		$.messager.alert('提示', '结束号段不能小于开始号段', 'info');
		return;
	}

	var startcard = startCardNum.substring(0, 4);
	var endcard = endCardNum.substring(0, 4);
	if (startcard != endcard) {
		$.messager.alert('提示', '开始号段前缀和结束号段前缀不一致', 'info');
		return;
	}

	var i=0;
	$.post("./UserCard/findRangsByUser.do", function(data) {
		for (var i = 0; i < data.length; i++) {
			if (data[i].cardKindID == cardKingsId) {
				
				
				if (startCardNum < data[i].userRangeBegin) {
					
					$.messager.alert('提示', '开始号段超出你的号段范围，请联系管理员', 'info');
					i=1;
					return;
				}
				if (data[i].userRangeEnd <endCardNum) {
					i=1;
					
					$.messager.alert('提示', '结束号段超出你的号段范围，请联系管理员', 'info');
					return;
				}
			}
		}
	});
	if(i==1){
		return;
	}
	var param = {
		"startCardNums" : startCardNum,
		"endCardNum" : endCardNum
	}
	$.post("./KCardOrders/findByCardnum.do", param, function(data) {
		if (data == 1) {
			$.messager.alert('提示', '号段没有被使用，请到开卡页开卡', 'info');
			return;
		}
	});
	
	var param2={
			"startCardNums" : startCardNum,
			"endCardNum" : endCardNum,
			"cardKingsId":cardKingsId
	};
	
	$("#add_shuLiang").val(endCardNum - startCardNum + 1);
	$.post("./KCardOrders/findByCardOrderByNum.do",param2,function(data){
		if(data.length==1){
			$("#add_closingDate").datetimebox('setValue',data[0].ClosingDate);
			
		}else{
			$.messager.alert('提示', '输入的号段查询到多条记录，请核对卡号重新输入', 'info');
			return;
		}
	});
	
}


//查询用户开卡记录
function addInfo() {
	// 卡类型和开始号段验证
	var cardKingsId = $("#add_cardKindsId").combobox('getValue');
	if (!cardKingsId) {
		$.messager.alert('提示', '请先选择卡类型', 'info');
		return;
	}

	// 结束号段验证
	var startCardNum = $("#add_startCardNum").val();
	var endCardNum = $("#add_endCardNum").val();
	if (startCardNum.length < 11) {
		$.messager.alert('提示', '号段输入错误，请重新输入', 'info');
		return;
	}
	if (endCardNum <= startCardNum) {
		$.messager.alert('提示', '结束号段不能小于开始号段', 'info');
		return;
	}

	var startcard = startCardNum.substring(0, 4);
	var endcard = endCardNum.substring(0, 4);
	if (startcard != endcard) {
		$.messager.alert('提示', '开始号段前缀和结束号段前缀不一致', 'info');
		return;
	}

	var j = 0;
	$.post("./UserCard/findRangsByUser.do", function(data) {
		
		for (var i = 0; i < data.length; i++) {
			if (data[i].cardKindID == cardKingsId) {

				if (startCardNum >= data[i].userRangeBegin
						&& data[i].userRangeEnd >= endCardNum) {

					j = 1;
				}
			}
		}
		if (j == 0) {
			$.messager.alert('提示', '你没有该号段的使用权,请重新输入或联系管理员更换号段', 'info');
			return;
		}
	});
	// 时间验证
	var before=$("#add_closingDate").datetimebox('getValue');
	var after=$("#add_afterDate").datetimebox('getValue');
	var a = new Date(before.replace(/-/g,"/"));
	var b = new Date(after.replace(/-/g,"/"));
	
	if(a>=b){
		$.messager.alert('提示', '修改后的时间大于修改前的时间，请重新输入', 'info');
		return;
	}

	if ($("#openCard").form("validate")) {
		$("#openCard .table_add_btn").linkbutton('disable');// 保存按钮失效防止重复点击
	}
	$("#openCard").form('submit', {
		url : './KCardOrders/addAfterCard.do',
		success : function(data) {
			if (data == 0) {
				$.messager.alert('提示', '添加成功！');
				$("#openCard").form('clear');
				$("#openCard .table_add_btn").linkbutton('enable');
				selectUser();
				RemainCount();
			} else{
				$.messager.alert('提示', '添加失败，请联系管理员');					
			}
		}
	});
}
