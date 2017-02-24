var card;
var cardType;
var balance;
var jmBiLi;
var isZero;
var fapiao;
var available;
$(function() {
	init();
	systemTime();

});

// 开始号段验证
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

// 结束号段验证并计算
function sum(data) {
	var kcaedOrders = [];
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
	var param = {
		"cardKingsId" : cardKingsId,
		"startCardNum" : startCardNum,
		"endCardNum" : endCardNum
	}
	var shuliang = floatSub(endCardNum, startCardNum);
	var addshuliang = floatAdd(shuliang, 1);
	$.post("./KCardOrders/findByUserCardOrders.do", param, function(data) {
		
		if (!data) {
			$.messager.alert('提示', '该号段没有开卡记录，请重新核对', 'info');
			return;
		}

		if (data[0].cardOrdersType == 1) {
			kcaedOrders = data;
			$("#add_dianShu").val(data[0].dianShu);
			$("#add_danJia").val(data[0].danJia);
			$("#add_shuLiang").val(addshuliang);
			$("#add_money").val(floatMul(addshuliang, data[0].danJia));
			$("#add_closingDate").val(data[0].closingDate);
			if (data[0].invoiceId == 1) {
				$("#fapiao1").val("是");
				$("#fapiao2").val("1");
			} else {
				$("#fapiao1").val("否");
				$("#fapiao2").val("0");
			}
			$("#memo").val(data[0].memo);

		}
		if (data[0].cardOrdersType == 3) {
			$.messager.alert('提示', '该号段中有退卡记录，请重新核对', 'info');
			return;
		}
	});

}

// 初始化下拉框
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
		
			if (data.id == 1 || data.id == 2) {

				$("#add_dianShuName").html("点数：");
				$("#add_dianShuUnit").html("(点/张)");

			} else {
				$("#add_dianShuName").html("次数：");
				$("#add_dianShuUnit").html("(次/张)");

			}

		}
	});

}

// 添加退卡单
function addInfo() {

	if ($("#openCard").form("validate")) {
		$("#openCard .table_add_btn").linkbutton('disable');// 保存按钮失效防止重复点击
	}
	$("#openCard").form('submit', {
		url : './KCardOrders/addQuitCardOrders.do',
		success : function(data) {
			if (data == 0) {
				$.messager.alert('提示', '退卡成功', 'info');
				$("#openCard").form('clear');
				$("#openCard .table_add_btn").linkbutton('enable');

			}
			if (data == 1) {
				$.messager.alert('提示', ' 该号段有开卡记录', 'info');
				$("#openCard .table_add_btn").linkbutton('enable');
				return;
			}
			if (data == 2) {
				$.messager.alert('提示', ' 没有查到该号段的开卡记录', 'info');
				$("#openCard .table_add_btn").linkbutton('enable');
				return;
			}
			if (data == 3) {
				$.messager.alert('提示', ' 添加退卡记录异常,请联系管理员', 'info');
				$("#openCard .table_add_btn").linkbutton('enable');
				return;
			}
			if (data == 4) {
				$.messager.alert('提示', ' 添加交易记录异常，请联系管理员', 'info');
				$("#openCard .table_add_btn").linkbutton('enable');
				return;
			}
			if (data == 5) {
				$.messager.alert('提示', ' 修改用户余额异常，请联系管理员', 'info');
				$("#openCard .table_add_btn").linkbutton('enable');
				return;
			}

		}
	});
}

// 获取服务器时间
function systemTime() {
	$.post("systemTime.do", function(data) {
		$("#systemtime").html(data);
	});
}

loadXML = function(xmlString) {
	var xmlDoc = null;
	// 判断浏览器的类型
	// 支持IE浏览器
	if (!window.DOMParser && window.ActiveXObject) { // window.DOMParser
		// 判断是否是非ie浏览器
		var xmlDomVersions = [ 'MSXML.2.DOMDocument.6.0',
				'MSXML.2.DOMDocument.3.0', 'Microsoft.XMLDOM' ];
		for (var i = 0; i < xmlDomVersions.length; i++) {
			try {
				xmlDoc = new ActiveXObject(xmlDomVersions[i]);
				xmlDoc.async = false;
				xmlDoc.loadXML(xmlString); // loadXML方法载入xml字符串
				break;
			} catch (e) {
			}
		}
	}
	// 支持Mozilla浏览器
	else if (window.DOMParser && document.implementation
			&& document.implementation.createDocument) {
		try {
			/*
			 * DOMParser 对象解析 XML 文本并返回一个 XML Document 对象。 要使用
			 * DOMParser，使用不带参数的构造函数来实例化它，然后调用其 parseFromString() 方法
			 * parseFromString(text, contentType) 参数text:要解析的 XML 标记
			 * 参数contentType文本的内容类型 可能是 "text/xml" 、"application/xml" 或
			 * "application/xhtml+xml" 中的一个。注意，不支持 "text/html"。
			 */
			domParser = new DOMParser();
			xmlDoc = domParser.parseFromString(xmlString, 'text/xml');
		} catch (e) {
		}
	} else {
		return null;
	}
	return xmlDoc;
}

// 减
function floatSub(arg1, arg2) {
	var r1, r2, m, n;
	try {
		r1 = arg1.toString().split(".")[1].length
	} catch (e) {
		r1 = 0
	}
	try {
		r2 = arg2.toString().split(".")[1].length
	} catch (e) {
		r2 = 0
	}
	m = Math.pow(10, Math.max(r1, r2));
	// 动态控制精度长度
	n = (r1 >= r2) ? r1 : r2;
	return ((arg1 * m - arg2 * m) / m).toFixed(n);
}
// 加
function floatAdd(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split(".")[1].length
	} catch (e) {
		r1 = 0
	}
	try {
		r2 = arg2.toString().split(".")[1].length
	} catch (e) {
		r2 = 0
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (arg1 * m + arg2 * m) / m;
}

// 乘
function floatMul(arg1, arg2) {
	var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	try {
		m += s1.split(".")[1].length
	} catch (e) {
	}
	try {
		m += s2.split(".")[1].length
	} catch (e) {
	}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
			/ Math.pow(10, m);
}
