var card;
var cardType;
var balance;
var jmBiLi;
var isZero;
var fapiao;
var available;
$(function() {
	$("#fapiao").combobox({
		onSelect : function(data) {
			fapiao = data.value;
		}
	});
	init();
	systemTime();
	RemainCount();
	selectUser();
	fapiao = $("#fapiao").combobox('getValue');

});

// 查询当期用户下面的钱数
function selectUser() {
	$.post("./KUser/findByUser.do", function(data) {
		balance = data.balance;
		jmBiLi = data.jmBiLi;
		isZero = data.isZero;
		$("#balance").html(balance);
		$("#jmBiLi").html(jmBiLi);
	});

}

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
// 计算到期时间
function countDate(data) {
	if (cardType == 1 || cardType == 2) {
		if (fapiao == 0) {

			$("#add_chengben").val(
					floatMul(card.cardKindsCost, $("#add_dianShu").val()));

		} else {
			var num = floatMul(card.cardKindsCost, $("#add_dianShu").val());
			var sum = floatAdd(num, floatMul(num, 0.03));

			$("#add_chengben").val(sum)

		}

	} else {
		if (fapiao == 0) {
			var cardKinds = floatMul(card.cardKindsDefault, $("#add_dianShu")
					.val());
			$("#add_chengben").val(floatMul(cardKinds, card.cardKindsCost));

		} else {
			var num = floatMul(card.cardKindsDefault, $("#add_dianShu").val());
			var sum = floatAdd(num, floatMul(num, 0.04));

			$("#add_chengben").val(sum);

		}

	}

	countMoney();
	if (cardType) {
		if (cardType == 1 || cardType == 2) {
			var dianshu = $("#add_dianShu").val();
			if (dianshu <= 100 && dianshu > 0) {
				var param = {
					"num" : 3
				}
				$.post("countTime.do", param, function(data) {
					$("#add_closingDate").textbox('setValue', data);
				});
			} else if (dianshu >= 100 && dianshu <= 200) {
				var param = {
					"num" : 6
				}
				$.post("countTime.do", param, function(data) {
					$("#add_closingDate").textbox('setValue', data);
				});
			} else if (dianshu > 200) {
				var param = {
					"num" : 12
				}
				$.post("countTime.do", param, function(data) {
					$("#add_closingDate").textbox('setValue', data);
				});
			}
		} else {
			var dianshu = $("#add_dianShu").val();
			if (dianshu <= 1 && dianshu > 0) {
				var param = {
					"num" : 3
				}
				$.post("countTime.do", param, function(data) {
					$("#add_closingDate").textbox('setValue', data);
				});
			} else if (dianshu >= 2 && dianshu <= 5) {
				var param = {
					"num" : 2
				}
				$.post("countTime.do", param, function(data) {
					$("#add_closingDate").textbox('setValue', data);
				});
			} else if (dianshu >= 5 && dianshu <= 12) {
				var param = {
					"num" : 6
				}
				$.post("countTime.do", param, function(data) {
					$("#add_closingDate").textbox('setValue', data);
				});
			} else if (dianshu > 12) {
				var param = {
					"num" : 12
				}
				$.post("countTime.do", param, function(data) {
					$("#add_closingDate").textbox('setValue', data);
				});
			}
		}
	}

}
// 结束号段验证并计算
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

	
	$.post("./UserCard/findRangsByUser.do", function(data) {
		for (var i = 0; i < data.length; i++) {
			if (data[i].cardKindID == cardKingsId) {
				if (startCardNum < data[i].userRangeBegin||data[i].userRangeEnd < endCardNum) {
					$.messager.alert('提示', '号段超出你的号段范围，请联系管理员', 'info');
					return;
				}
			}
		}
	});
	var param = {
		"startCardNums" : startCardNum,
		"endCardNum" : endCardNum
	}
	$.post("./KCardOrders/findByCardnum.do", param, function(data) {
		if (data != 1) {
			$.messager.alert('提示', '号段没有被使用，请到开卡页开卡', 'info');
			return;
		}
	});
	
	$("#add_shuLiang").val(endCardNum - startCardNum + 1);
	
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

// 统计金额
function countMoney() {
	var add_danJia = $("#add_danJia").val();
	var add_shuLiang = $("#add_shuLiang").val();
	var add_chengben = $("#add_chengben").val();

	$("#add_money").val(floatMul(add_danJia, add_shuLiang));
	$("#add_lirun").val(
			floatSub(floatMul(add_danJia, add_shuLiang), floatMul(add_chengben,
					add_shuLiang)));
}
// 添加开卡单
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
	// 点数验证
	var add_dianShu = $("#add_dianShu").val();
	if (!add_dianShu) {
		$.messager.alert('提示', '请输入点数或次数', 'info');
		return;
	}
	// 售价验证
	var add_danJia = $("#add_danJia").val();
	if (!add_danJia) {
		$.messager.alert('提示', '请输入售价', 'info');
		return;
	}
	// 利润验证
	var add_lirun = $("#add_lirun").val();
	if (add_lirun < 0) {
		$.messager.alert('提示', '请核对开卡单，利润不能为负数', 'info');
		return;
	}
	var add_danJia = $("#add_danJia").val();
	var add_shuLiang = $("#add_shuLiang").val();
	var add_chengben = $("#add_chengben").val();

	if (userType == 1) {
		if (available < floatMul(add_danJia, add_shuLiang)) {
			$.messager.alert('提示', '您的账户余额不足，请充值', 'info');
			return;
		}
	} else {
		var sum = floatMul(add_danJia, add_shuLiang);
		var num = floatMul(floatSub(card.cardKindsCost, 1), sum);
		if (available < num) {
			$.messager.alert('提示', '您的账户余额不足，请充值', 'info');
			return;
		}
	}
	if ($("#openCard").form("validate")) {
		$("#openCard .table_add_btn").linkbutton('disable');// 保存按钮失效防止重复点击
	}
	$("#openCard").form('submit', {
		url : './KCardOrders/addPayCardOrders.do',
		success : function(data) {
			if (data == 0) {
				$.messager.alert('提示', '添加成功！');
				$("#openCard").form('clear');
				$("#openCard .table_add_btn").linkbutton('enable');
				selectUser();
				RemainCount();
			} else if (data == 1) {
				$.messager.alert('提示', '添加失败，数量错误');
			} else if (data == 2) {
				$.messager.alert('提示', '添加失败，总金额错误');
			} else if (data == 3) {
				$.messager.alert('提示', '添加失败，找不到用户下面的卡');
			}else{
				$.messager.alert('提示', '添加失败，请联系管理员');					
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
// 获取用户折扣
function RemainCount() {
	$
			.post(
					"./KCardOrders/getProportion.do",
					function(data) {
						var xmlStrDoc = null;
						if (window.DOMParser) {// Mozilla Explorer
							parser = new DOMParser();
							xmlStrDoc = parser
									.parseFromString(data, "text/xml");
						} else {// Internet Explorer
							xmlStrDoc = new ActiveXObject("Microsoft.XMLDOM");
							xmlStrDoc.async = "false";
							xmlStrDoc.loadXML(data);
						}
						var bili = xmlStrDoc.childNodes[0].childNodes[1].childNodes[0].childNodes[0].childNodes[0].textContent;
						$("#noplay").html(bili);
						available = balance - (bili * jmBiLi);
						if (available >= 0) {
							$("#available").html(available.toFixed(2));
						} else {
							$("#zhu")
									.html(
											'<span style="color: rgb(245, 29, 29); font-size: 25px;">注:你余额不足，请到充值页面充值</span>');
						}

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
