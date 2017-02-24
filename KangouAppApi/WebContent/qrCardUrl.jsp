<%@page import="com.util.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="" name="description" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="0">
<meta content="" name="author" />

<link href="<%=path%>/media/css/sweetalert.css" rel="stylesheet"
	type="text/css" />
<script src="<%=path%>/media/js/jquery-1.10.1.min.js"
	type="text/javascript"></script>


<title>Insert title here</title>
</head>
<body>
	<div style="width: 100%; text-align: center;">
		<h4>
			<span style="color: red;">正在处理中，请稍后......</span>
		</h4>
	</div>


</body>
<%
	 long Starttime = System.currentTimeMillis();
	Utils u = new Utils();
	String urlNameString = (String) request.getSession().getAttribute("cardByurl");
	String result = u.sendGet(urlNameString);
	long endtime = System.currentTimeMillis();
	System.out.println("获取响应时间：------->" + (endtime - Starttime));
	System.out.println("返回的结果是：" + result);
	System.out.println(result);
%>

<script src="<%=path%>/media/js/sweetalert.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		var fanhui='0';
		if (fanhui == '0') {
			$.post("./ScanQRCode/ticketQRcode.do", function(data) {
				if (data.messageid == '2100') {
					window.location.href = "./return.do?url=resultCode";
					return;
				}else{
					swal({
						title : "购卡失败",
						text : data.messageid+data.message,
						type : "error",
						closeOnConfirm : false,
						confirmButtonText : "返回",
						confirmButtonColor : "#ec6c62"
					}, function() {
						history.back();
					});
					return;
				}
			});
		}
		if (fanhui == "10") {
			swal({
				title : "购卡失败",
				text : "2110" + "无效卡,卡不存在",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}
		if (fanhui == "11") {
			swal({
				title : "购卡失败",
				text : "2111" + "余额不足",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});

			return;

		}
		if (fanhui == "12") {
			swal({
				title : "购卡失败",
				text : "2112" + "本月额度不足 卡超过月消费最大数",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}
		if (fanhui == "13") {
			swal({
				title : "购卡失败",
				text : "2113" + "日期格式不正确",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}
		if (fanhui == "14") {
			swal({
				title : "购卡失败",
				text : "2114" + "卡过期",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});

			return;
		}
		if (fanhui == "15") {
			swal({
				title : "购卡失败",
				text : "2115" + "日期超出范围",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;

		}
		if (fanhui == "16") {
			swal({
				title : "购卡失败",
				text : "2116" + "商户终端号错误",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}
		if (fanhui == "17") {
			swal({
				title : "购卡失败",
				text : "2117" + "此影院不支持",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;

		}
		if (fanhui == "18") {
			swal({
				title : "购卡失败",
				text : "2118" + "无数据",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}
		if (fanhui == "20") {
			swal({
				title : "购卡失败",
				text : "2120" + "此终端号不存在",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}
		if (fanhui == "21") {
			swal({
				title : "购卡失败",
				text : "2121" + "此终端号未启用",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}
		if (fanhui == "22") {
			swal({
				title : "购卡失败",
				text : "2122" + "此卡未开通",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;

		}
		if (fanhui == "23") {
			swal({
				title : "购卡失败",
				text : "2123" + "密码错误",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}
		if (fanhui == "30") {
			swal({
				title : "购卡失败",
				text : "2130" + "此卡已作废",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}
		if (fanhui == "32") {
			swal({
				title : "购卡失败",
				text : "2132" + "此卡未到开通日期",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;

		}
		if (fanhui == "40") {
			swal({
				title : "购卡失败",
				text : "2140" + "不支持商户模式消费",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;

		}
		if (fanhui == "41") {
			swal({
				title : "购卡失败",
				text : "2141" + "此卡不支持普通电影",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;

		}
		if (fanhui == "42") {
			swal({
				title : "购卡失败",
				text : "2142" + "此卡不支持3D电影",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});

			return;

		}
		if (fanhui == "43") {
			swal({
				title : "购卡失败",
				text : "2143" + "此卡不支持VIP电影",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});

			return;
		}
		if (fanhui == "44") {
			swal({
				title : "购卡失败",
				text : "2144" + "此卡不支持IMAX电影",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;

		}
		if (fanhui == "45") {
			swal({
				title : "购卡失败",
				text : "2145" + "此卡不支持白场优惠普通电影",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});

			return;

		}
		if (fanhui == "46") {
			swal({
				title : "购卡失败",
				text : "2146" + "此卡不支持白场优惠3D电影",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});

			return;
		}
		if (fanhui == "47") {
			swal({
				title : "购卡失败",
				text : "2147" + "此卡不支持白场优惠VIP电影",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;

		}
		if (fanhui == "48") {
			swal({
				title : "购卡失败",
				text : "2148" + "此卡不支持白场优惠IMAX电影",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;

		}
		if (fanhui == "49") {
			swal({
				title : "购卡失败",
				text : "2149" + "输入的单价太小",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});

			return;

		}
		if (fanhui == "50") {
			swal({
				title : "购卡失败",
				text : "2150" + "输入的单价太大",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;

		}
		if (fanhui == "51") {
			swal({
				title : "购卡失败",
				text : "2151" + "输入的单价错误",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});

			return;

		}
		if (fanhui == "52") {
			swal({
				title : "购卡失败",
				text : "2152" + "卡类型错误",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});

			return;

		}
		if (fanhui == "53") {
			swal({
				title : "购卡失败",
				text : "2153" + "票卡的单价太小",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;

		}
		if (fanhui == "6") {
			swal({
				title : "购卡失败",
				text : "2106" + "输入的单价错误",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}
	
		if (fanhui == "36") {
			swal({
				title : "购卡失败",
				text : "2136" + "消费记录已有",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}
		if (fanhui==""||fanhui==null) {
			swal({
				title : "购卡失败",
				text : "2136" + "错误，请重试",
				type : "error",
				closeOnConfirm : false,
				confirmButtonText : "返回",
				confirmButtonColor : "#ec6c62"
			}, function() {
				history.back();
			});
			return;
		}

	});
</script>
</html>