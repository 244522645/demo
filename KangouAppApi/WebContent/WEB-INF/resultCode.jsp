<%@page import="com.model.UserInfo"%>
<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

<meta charset="utf-8" />

<title>Metronic | Login Page</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<meta content="" name="description" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="0">
<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="<%=path%>/media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />




<link href="<%=path%>/media/css/style-metro.css" rel="stylesheet"
	type="text/css" />

<link href="<%=path%>/media/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/media/css/sweetalert.css" rel="stylesheet"
	type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link href="<%=path%>/media/css/resultCode.css" rel="stylesheet"
	type="text/css" />

<!-- END PAGE LEVEL STYLES -->


</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">
	<!-- 
	<div class="logo" style="text-align: center;">
		<a class="more" href="javascript: window.history.go(-1)"
			style="float: left;"> <i class="m-icon-big-swapleft m-icon-white"></i>

		</a>
		<h4 class="form-title" style="color: #fff; margin-bottom: 0px;"
			onclick="javascript:window.open(index.html);">看购商户版</h4>

	</div>
	 -->
	<!-- BEGIN LOGIN -->
	<div class="portlet-body flip-scroll" style="display: block;">
		<ul class="button" style="margin-right: 25px; list-style-type: none;"
			id="submitbutton">
			<li style="height: 30px;"><button type="button" class="btn red"
					style="width: 100%; margin-top: 3px;" onclick="printTicket()">打印小票</button></li>
		</ul>
		<div class="content" style="padding: 0px;" align="center">
			<h3 style="margin: 0; text-align: center;">${sessionScope.cinemaName.name}</h3>
		</div>

		<table
			class="table-bordered table-striped table-condensed flip-content"
			style="border: 0px;">

			<thead class="flip-content">

				<tr>
					<th class="numeric">终端号:</th>

					<th class="numeric">批次号:</th>

					<th class="numeric">流水号:</th>

					<th class="numeric">操作员:</th>
					<th class="numeric">消费卡号:</th>
					<th class="numeric">交易类型:</th>
					<th class="numeric">消费张数:</th>
					<th class="numeric">消费点数:</th>
					<th class="numeric">剩余点数:</th>
					<th class="numeric">结算类型:</th>
					<th class="numeric">校验码:</th>
					<th class="numeric">时间:</th>
				</tr>

			</thead>
			<tbody>
				<tr style="border-left-width: 0px;">

					<td class="numeric">${sessionScope.Smallticket.posid}</td>
					<td class="numeric">${sessionScope.Smallticket.batch}</td>
					<td class="numeric">${sessionScope.Smallticket.serialnumber}</td>
					<td class="numeric">${sessionScope.Smallticket.userinfo}</td>
					<td class="numeric">${sessionScope.Smallticket.cardnum}</td>
					<td class="numeric">${sessionScope.Smallticket.type}</td>
					<td class="numeric">${sessionScope.Smallticket.num}</td>
					<td class="numeric">${sessionScope.Smallticket.posorderpurchasecount}</td>
					<td class="numeric">${sessionScope.Smallticket.posorderticketremaincount}</td>
					<td class="numeric">${sessionScope.Smallticket.price}</td>
					<td class="numeric">${sessionScope.Smallticket.baoxianresult}</td>
					<td class="numeric">${sessionScope.Smallticket.time}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!--  
	<div class="content">
		<ul class="button" id="printButton">



	
		</ul>
		<ul class="ver-inline-menu tabbable margin-bottom-10">
			<li><h3>${sessionScope.cinemaName.name}</h3></li>
			<li style="min-height: 1px; margin-bottom: 1px;"><p
					style="border-top: 1px dotted #000;"></p></li>
			<li><p>
					<span id="card">终端号:</span> ${sessionScope.Smallticket.posid}
				</p></li>
			<li><p>
					<span>批次号:</span> ${sessionScope.Smallticket.batch}
				</p></li>
			<li><p>
					<span>流水号:</span> ${sessionScope.Smallticket.serialnumber}
				</p></li>
			<li><p>
					<span>卡号:</span> ${sessionScope.Smallticket.cardnum}
				</p></li>
			<li><p>
					<span>交易类型:</span> ${sessionScope.Smallticket.type}
				</p></li>
			<li><p>
					<span>操作员ID:</span> ${sessionScope.Smallticket.userinfo}
				</p></li>
			<li><p>
					<span>消费张数:</span> ${sessionScope.Smallticket.num}
				</p></li>
			<li><p>
					<span>结算类型:</span> ${sessionScope.Smallticket.price}
				</p></li>
			<li><p>${sessionScope.Smallticket.time}</p></li>
			<li><p>
					<span>校验码:</span> ${sessionScope.Smallticket.baoxianresult}
				</p></li>
			<li style="min-height: 1px; margin-bottom: 1px;"><p
					style="border-top: 1px dotted #000"></p></li>

		</ul>

	</div>
-->
	<!-- END LOGIN -->



	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="<%=path%>/media/js/jquery-1.10.1.min.js"
		type="text/javascript"></script>

	
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="<%=path%>/media/js/bootstrap.min.js"
		type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="<%=path%>/media/js/excanvas.min.js"></script>

	<script src="<%=path%>/media/js/respond.min.js"></script>  

	<![endif]-->


	<!-- END CORE PLUGINS -->



	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="<%=path%>/media/js/sweetalert.min.js"
		type="text/javascript"></script>

	<script type="text/javascript">
		var mobileType = '${sessionScope.currentUser.mobileType}';

		function printTicket() {
			var title = '${sessionScope.cinemaName.name}' + '\n';
			var printText = "------------------\n" + '终端号:'
					+ '${sessionScope.Smallticket.posid}' + '\n' + '批次号:'
					+ '${sessionScope.Smallticket.batch}' + '\n' + '流水号:'
					+ '${sessionScope.Smallticket.serialnumber}' + '\n'
					+ '操作员:' + '${sessionScope.Smallticket.userinfo}' + '\n'
					+ "消费卡号:${sessionScope.Smallticket.cardnum}\n" + '交易类型:'
					+ '${sessionScope.Smallticket.type}' + '\n' + '消费张数:'
					+ '${sessionScope.Smallticket.num}' + '\n' + '消费点数:'
					+ '${sessionScope.Smallticket.posorderpurchasecount}'
					+ '\n' + '剩余点数:'
					+ '${sessionScope.Smallticket.posorderticketremaincount}'
					+ '\n' + '结算类型:' + '${sessionScope.Smallticket.price}'
					+ '\n' + '校 验 码:'
					+ '${sessionScope.Smallticket.baoxianresult}' + '\n'
					+ '${sessionScope.Smallticket.time}' + '\n'
					+ "---------------------\n" + "商户对账联\n" + "看购电影感谢您的支持\n"
					+ "看购网 www.kanggou.cn\n";

			var twoiosprint = "------------------\n" + '终端号:'
					+ '${sessionScope.Smallticket.posid}' + '\n' + '批次号:'
					+ '${sessionScope.Smallticket.batch}' + '\n' + '流水号:'
					+ '${sessionScope.Smallticket.serialnumber}' + '\n'
					+ '操作员:' + '${sessionScope.Smallticket.userinfo}' + '\n'
					+ "消费卡号:${sessionScope.Smallticket.cardnum}\n" + '交易类型:'
					+ '${sessionScope.Smallticket.type}' + '\n' + '消费张数:'
					+ '${sessionScope.Smallticket.num}' + '\n' + '消费点数:'
					+ '${sessionScope.Smallticket.posorderpurchasecount}'
					+ '\n' + '剩余点数:'
					+ '${sessionScope.Smallticket.posorderticketremaincount}'
					+ '\n' + '结算类型:' + '${sessionScope.Smallticket.price}'
					+ '\n' + '校 验 码:'
					+ '${sessionScope.Smallticket.baoxianresult}' + '\n'
					+ '${sessionScope.Smallticket.time}' + '\n'
					+ "---------------------\n" + "客户凭证联\n" + "看购电影感谢您的支持\n"
					+ "看购网 www.kanggou.cn\n";
			if (mobileType == 'ios') {
				kangouprint("1B/33/10/1D/21/11",'${sessionScope.cinemaName.name}');
						kangouprint("1B/33/25/1D/21/00",printText);
						kangouprint("1B/33/10/1D/21/11",'${sessionScope.cinemaName.name}');
						kangouprint("1B/33/25/1D/21/00",twoiosprint);
			} else {
				kangou.checkPrinter();
				kangou.print("29/33/17/27/51/15",
						'${sessionScope.cinemaName.name}' + '\n');
				kangou.print("27/97/0/27/51/15", "---------------------\n");
				kangou.print("27/97/0/27/51/15", '终端号:'
						+ '${sessionScope.Smallticket.posid}' + '\n');
				kangou.print("27/97/0/27/51/15", '批次号:'
						+ '${sessionScope.Smallticket.batch}' + '\n');
				kangou.print("27/97/0/27/51/15", '流水号:'
						+ '${sessionScope.Smallticket.serialnumber}' + '\n');
				kangou.print("27/97/0/27/51/15", '操作员:'
						+ '${sessionScope.Smallticket.userinfo}' + '\n');
				kangou.print("27/97/0/27/51/15", '消费卡号:'
						+ '${sessionScope.Smallticket.cardnum}' + '\n');
				kangou.print("27/97/0/27/51/15", '交易类型:'
						+ '${sessionScope.Smallticket.type}' + '\n');

				kangou.print("27/97/0/27/51/15", '消费张数:'
						+ '${sessionScope.Smallticket.num}' + '\n');
				kangou.print("27/97/0/27/51/15", '消费点数:'
						+ '${sessionScope.Smallticket.posorderpurchasecount}'
						+ ' \n');
				kangou.print("27/97/0/27/51/15",'剩余点数:'+ '${sessionScope.Smallticket.posorderticketremaincount}'+ ' \n');
				kangou.print("27/97/0/27/51/15", '结算类型:'
						+ '${sessionScope.Smallticket.price}' + '\n');
				kangou.print("27/97/0/27/51/15", '校 验 码:'
						+ '${sessionScope.Smallticket.baoxianresult}' + '\n');
				kangou.print("27/97/0/27/51/15",
						'${sessionScope.Smallticket.time}' + '\n');
				kangou.print("27/97/0/27/51/15", "---------------------\n");
				kangou.print("27/97/0/27/51/15", "商户对账联\n");
				kangou.print("27/97/0/27/51/15", "看购电影感谢您的支持\n");
				kangou.print("27/97/0/27/51/15", "看购网 www.kanggou.cn\n");

				kangou.print("27/97/0/27/51/15", '\n');
				kangou.print("27/97/0/27/51/15", '\n');
				kangou.print("27/97/0/27/51/15", '\n');
				kangou.print("27/97/0/27/51/15", '\n');
				kangou.print("29/33/17/27/51/15",
						'${sessionScope.cinemaName.name}' + '\n');
				kangou.print("27/97/0/27/51/15", "---------------------\n");
				kangou.print("27/97/0/27/51/15", '终端号:'
						+ '${sessionScope.Smallticket.posid}' + '\n');
				kangou.print("27/97/0/27/51/15", '批次号:'
						+ '${sessionScope.Smallticket.batch}' + '\n');
				kangou.print("27/97/0/27/51/15", '流水号:'
						+ '${sessionScope.Smallticket.serialnumber}' + '\n');
				kangou.print("27/97/0/27/51/15", '操作员:'
						+ '${sessionScope.Smallticket.userinfo}' + '\n');
				kangou.print("27/97/0/27/51/15", '消费卡号:'
						+ '${sessionScope.Smallticket.cardnum}' + '\n');
				kangou.print("27/97/0/27/51/15", '交易类型:'
						+ '${sessionScope.Smallticket.type}' + '\n');

				kangou.print("27/97/0/27/51/15", '消费张数:'
						+ '${sessionScope.Smallticket.num}' + ' \n');
				kangou.print("27/97/0/27/51/15", '消费点数:'
						+ '${sessionScope.Smallticket.posorderpurchasecount}'
						+ ' \n');
				kangou.print("27/97/0/27/51/15",'剩余点数:'+ '${sessionScope.Smallticket.posorderticketremaincount}'+'\n');
				kangou.print("27/97/0/27/51/15", '结算类型:'
						+ '${sessionScope.Smallticket.price}' + '\n');
				kangou.print("27/97/0/27/51/15", '校 验 码:'
						+ '${sessionScope.Smallticket.baoxianresult}' + '\n');
				kangou.print("27/97/0/27/51/15",
						'${sessionScope.Smallticket.time}' + '\n');
				kangou.print("27/97/0/27/51/15", "---------------------\n");
				kangou.print("27/97/0/27/51/15", "客户凭证联\n");
				kangou.print("27/97/0/27/51/15", "看购电影感谢您的支持\n");
				kangou.print("27/97/0/27/51/15", "看购网 www.kanggou.cn\n");
				kangou.print("27/97/0/27/51/15", '\n');
				kangou.print("27/97/0/27/51/15", '\n');
			}
		}
	</script>
	<!-- END JAVASCRIPTS -->


</body>

<!-- END BODY -->

</html>