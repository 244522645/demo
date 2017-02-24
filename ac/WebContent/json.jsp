<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="easyui/jquery-3.1.0.js"></script>
<script type="text/javascript">
	function clicks1() {

		var startCardNumStr = "95010000002";
		var endCardNumStr = "95010000003";
		var cardKindsName = "看片会电影卡";
		var invoiceId = "0";
		var dianShu = "10";
		var danJia = "20";
		var closingDate = "2016-10-11 00:00:00";
	/* 	var jsonStr = {
			"orderJson" : [ {
				"startCardNumStr" : startCardNumStr,
				"endCardNumStr" : endCardNumStr,
				"cardKindsName" : cardKindsName,
				"invoiceId" : invoiceId,
				"dianShu" : dianShu,
				"danJia" : danJia,
				"closingDate" : closingDate
			}, {
				"startCardNumStr" : startCardNumStr,
				"endCardNumStr" : endCardNumStr,
				"cardKindsName" : cardKindsName,
				"invoiceId" : invoiceId,
				"dianShu" : dianShu,
				"danJia" : danJia,
				"closingDate" : closingDate
			} ]
		}; */
		$.ajax({
			type : "post",
			url : "KCardOrders/openCardInterface.do",
			data :  {
				"startCardNum" : startCardNumStr,
				"endCardNum" : endCardNumStr,
				"cardKindsName" : cardKindsName,
				"invoiceId" : invoiceId,
				"dianShu" : dianShu,
				"danJia" : danJia,
				"closingDate" : closingDate,
				"cardType":1
			},
			dataType : "JSON",
			success : function(data) {

			}
		});
	};
	function clicks2() {

		var startCardNumStr = "95010000002";
		var endCardNumStr = "95010000003";
		var cardKindsName = "看片会电影卡";
		var invoiceId = "0";
		var dianShu = "10";
		var danJia = "20";
		var closingDate = "2016-10-11 00:00:00";
	/* 	var jsonStr = {
			"orderJson" : [ {
				"startCardNumStr" : startCardNumStr,
				"endCardNumStr" : endCardNumStr,
				"cardKindsName" : cardKindsName,
				"invoiceId" : invoiceId,
				"dianShu" : dianShu,
				"danJia" : danJia,
				"closingDate" : closingDate
			}, {
				"startCardNumStr" : startCardNumStr,
				"endCardNumStr" : endCardNumStr,
				"cardKindsName" : cardKindsName,
				"invoiceId" : invoiceId,
				"dianShu" : dianShu,
				"danJia" : danJia,
				"closingDate" : closingDate
			} ]
		}; */
		$.ajax({
			type : "post",
			url : "KCardOrders/openCardInterface.do",
			data :  {
				"startCardNum" : startCardNumStr,
				"endCardNum" : endCardNumStr,
				"cardKindsName" : cardKindsName,
				"invoiceId" : invoiceId,
				"dianShu" : dianShu,
				"danJia" : danJia,
				"closingDate" : closingDate,
				"cardType":2
			},
			dataType : "JSON",
			success : function(data) {

			}
		});
	};
	function clicks3() {

		var startCardNumStr = "95010000002";
		var endCardNumStr = "95010000003";
		var afterDate="2017-06-08 10:00:00";
		$.ajax({
			type : "post",
			url : "KCardOrders/openCardInterface.do",
			data :  {
				"startCardNum" : startCardNumStr,
				"endCardNum" : endCardNumStr,
				"afterDate" : afterDate,
				"cardType":3
			},
			dataType : "JSON",
			success : function(data) {

			}
		});
	};
	function clicks4() {

		var startCardNumStr = "95010000002";
		var endCardNumStr = "95010000003";
		var cardKindsName = "看片会电影卡";
		var invoiceId = "0";
		var dianShu = "10";
		var danJia = "20";
		var closingDate = "2016-10-11 00:00:00";
	
		$.ajax({
			type : "post",
			url : "KCardOrders/openCardInterface.do",
			data :  {
				"startCardNum" : startCardNumStr,
				"endCardNum" : endCardNumStr,
				"cardKindsName" : cardKindsName,
				"invoiceId" : invoiceId,
				"dianShu" : dianShu,
				"danJia" : danJia,
				"closingDate" : closingDate,
				"cardType":4
			},
			dataType : "JSON",
			success : function(data) {

			}
		});
	};
</script>
<body>
	<input type="button" value="点击开卡" onclick="clicks1()" />
	<input type="button" value="点击充值" onclick="clicks2()" />
	<input type="button" value="点击延期" onclick="clicks3()" />
	<input type="button" value="点击退卡" onclick="clicks4()" />
</body>
</html>