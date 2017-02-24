$(function() {

});

function printLast() {
	printTicket();
}

function printAppoint() {

	var serialnum = $("#swiftNumber").val();
	var param = {
		serialnum : serialnum
	}
	if (serialnum.replace(/(^\s*)|(\s*$)/g, "")) {
		
		$("#appointPrint").attr("disabled","disabled");
		$.post("./PosOrders/findByserialnum.do", param,
				function(data) {
					if (data == null || data == "") {
						$("#appointPrint").removeAttr("disabled");
						$("#appointPrint").blur();
						swal("没有找到指定交易记录");
						return;
					}
					if (mobileType == 'ios') {
						//kangouprint(title, printText, twoiosprint);
						kangouprint("1B/33/10/1D/21/11",data[0].name);
						kangouprint("1B/33/25/1D/21/00","---------------------"+'\n'+'终端号:' + data[0].posid+'\n'+'批次号:' + data[0].batch+			'\n流水号:'
								+ data[0].serialnumber+'\n操作员:'
								+ subString(data[0].posuserid)+'\n消费卡号:'
								+ data[0].cardtnumber +'\n交易类型:'
								+ returntype(data[0].operationtype)+'\n消费张数:'
								+ data[0].posordercount +"\n消费点数:"
								+ data[0].posorderpurchasecount+'\n结算类型:'
								+ data[0].cinemaprice+'\n校 验 码:'
								+ data[0].baoxianresult+ '\n'+data[0].posordertime+"\n---------------------"+ "\n商户对账联"+"\n看购电影感谢您的支持"+"\n看购网 www.kanggou.cn\n\n");
						kangouprint("1B/33/10/1D/21/11",data[0].name);
						kangouprint("1B/33/25/1D/21/00", "---------------------"+'\n'+'终端号:' + data[0].posid+'\n'+'批次号:' + data[0].batch+			'\n流水号:'
								+ data[0].serialnumber+'\n操作员:'
								+ subString(data[0].posuserid)+'\n消费卡号:'
								+ data[0].cardtnumber +'\n交易类型:'
								+ returntype(data[0].operationtype)+'\n消费张数:'
								+ data[0].posordercount +"\n消费点数:"
								+ data[0].posorderpurchasecount+'\n结算类型:'
								+ data[0].cinemaprice+'\n校 验 码:'
								+ data[0].baoxianresult+ '\n'+data[0].posordertime+"\n---------------------"+ "\n客户凭证联"+"\n看购电影感谢您的支持"+"\n看购网 www.kanggou.cn\n\n");
									
					} else {
						console.log(data[0]);
						kangou.checkPrinter();
						kangou.print("29/33/17/27/51/15", data[0].name + '\n');
						kangou.print("27/97/0/27/51/15",
								"---------------------\n");
						kangou.print("27/97/0/27/51/15", '终端号:' + data[0].posid
								+ '\n');
						kangou.print("27/97/0/27/51/15", '批次号:' + data[0].batch
								+ '\n');
						kangou.print("27/97/0/27/51/15", '流水号:'
								+ data[0].serialnumber + '\n');
						kangou.print("27/97/0/27/51/15", '操作员:'
								+ subString(data[0].posuserid) + '\n');
						kangou.print("27/97/0/27/51/15", '消费卡号:'
								+ data[0].cardtnumber + '\n');
						kangou.print("27/97/0/27/51/15", '交易类型:'
								+ returntype(data[0].operationtype) + '\n');

						kangou.print("27/97/0/27/51/15", '消费张数:'
								+ data[0].posordercount + '\n');
						kangou.print("27/97/0/27/51/15", "消费点数:"
								+ data[0].posorderpurchasecount + " \n");
						kangou.print("27/97/0/27/51/15", "剩余点数: "
								+ data[0].posorderticketremaincount + " \n");
						kangou.print("27/97/0/27/51/15", '结算类型:'
								+ data[0].cinemaprice + '\n');
						kangou.print("27/97/0/27/51/15", '校 验 码:'
								+ data[0].baoxianresult + '\n');
						kangou.print("27/97/0/27/51/15", data[0].posordertime
								+ '\n');
						kangou.print("27/97/0/27/51/15",
								"---------------------\n");
						kangou.print("27/97/0/27/51/15", "商户对账联\n");
						kangou.print("27/97/0/27/51/15", "看购电影感谢您的支持\n");
						kangou
								.print("27/97/0/27/51/15",
										"看购网 www.kanggou.cn\n");

						kangou.print("27/97/0/27/51/15", '\n');
						kangou.print("27/97/0/27/51/15", '\n');
						kangou.print("27/97/0/27/51/15", '\n');
						kangou.print("27/97/0/27/51/15", '\n');
						kangou.print("29/33/17/27/51/15", data[0].name + '\n');
						kangou.print("27/97/0/27/51/15",
								"---------------------\n");
						kangou.print("27/97/0/27/51/15", '终端号:' + data[0].posid
								+ '\n');
						kangou.print("27/97/0/27/51/15", '批次号:' + data[0].batch
								+ '\n');
						kangou.print("27/97/0/27/51/15", '流水号:'
								+ data[0].serialnumber + '\n');
						kangou.print("27/97/0/27/51/15", '操作员:'
								+ subString(data[0].posuserid) + '\n');
						kangou.print("27/97/0/27/51/15", '消费卡号:'
								+ data[0].cardtnumber + '\n');
						kangou.print("27/97/0/27/51/15", '交易类型:'
								+ returntype(data[0].operationtype) + '\n');

						kangou.print("27/97/0/27/51/15", '消费张数:'
								+ data[0].posordercount + '\n');
						kangou.print("27/97/0/27/51/15", "消费点数:"
								+ data[0].posorderpurchasecount + " \n");
						kangou.print("27/97/0/27/51/15", "剩余点数: "
								+ data[0].posorderticketremaincount + " \n");
						kangou.print("27/97/0/27/51/15", '结算类型:'
								+ data[0].cinemaprice + '\n');
						kangou.print("27/97/0/27/51/15", '校 验 码:'
								+ data[0].baoxianresult + '\n');
						kangou.print("27/97/0/27/51/15", data[0].posordertime
								+ '\n');
						kangou.print("27/97/0/27/51/15",
								"---------------------\n");
						kangou.print("27/97/0/27/51/15", "客户凭证联\n");
						kangou.print("27/97/0/27/51/15", "看购电影感谢您的支持\n");
						kangou
								.print("27/97/0/27/51/15",
										"看购网 www.kanggou.cn\n");
						kangou.print("27/97/0/27/51/15", '\n');
						kangou.print("27/97/0/27/51/15", '\n');
					}
				});
		$("#appointPrint").removeAttr("disabled");
		$("#appointPrint").blur();
	} else {
		swal("请输入流水号");
	}
}

function subString(c) {
	return c.substring(0, 3) + "****" + c.substring(7);
}

function returntype(type) {
	if (type.indexOf("密码") > 0) {
		return "密码消费";
	}
	return "刷卡消费";
}