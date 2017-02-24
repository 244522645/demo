<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%><%	String path = request.getContextPath();			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()					+ path + "/";%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html><!--[if IE 8]> <html lang="en" class="ie8"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9"> <![endif]--><!--[if !IE]><!--><html lang="en"><!--<![endif]--><!-- BEGIN HEAD --><head><meta charset="utf-8" /><title>kangou | 扫描结果</title><meta content="width=device-width, initial-scale=1.0" name="viewport" /><meta content="" name="description" /><META HTTP-EQUIV="pragma" CONTENT="no-cache"><META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"><META HTTP-EQUIV="expires" CONTENT="0"><meta content="" name="author" /><!-- BEGIN GLOBAL MANDATORY STYLES --><link href="<%=path%>/media/css/bootstrap.min.css" rel="stylesheet"	type="text/css" /><link href="<%=path%>/media/css/style-metro.css" rel="stylesheet"	type="text/css" /><link href="<%=path%>/media/css/style.css" rel="stylesheet"	type="text/css" /><!-- END GLOBAL MANDATORY STYLES --><!-- BEGIN PAGE LEVEL STYLES --><link href="<%=path%>/media/css/scanCode.css" rel="stylesheet"	type="text/css" /><!-- END PAGE LEVEL STYLES --><link href="<%=path%>/media/css/sweetalert.css" rel="stylesheet"	type="text/css" /></head><!-- END HEAD --><!-- BEGIN BODY --><body class="login">	<!-- 	<div class="logo" style="text-align: center;">		<a class="more" href="javascript: window.history.go(-1)"			style="float: left;"> <i class="m-icon-big-swapleft m-icon-white"></i>		</a>		<h4 class="form-title" style="color: #fff; margin-bottom: 0px;"			onclick="javascript:window.open(index.html);">看购商户版</h4>	</div>	 -->	<!-- BEGIN LOGIN -->	<div class="portlet-body flip-scroll" style="display: block;">		<table			class="table-bordered table-striped table-condensed flip-content"			style="border: 0px;">			<thead class="flip-content" style="width: 30%;">				<tr>					<th class="numeric" style="margin-top: 20px;"><span>卡号:</span></th>					<th class="numeric" style="margin-top: 20px;">点数:</th>					<th class="numeric" style="margin-top: 20px;">有效期:</th>					<th class="numeric" style="margin-top: 28px;"><span						style="text-align: center;">消费张数:</span></th>					<th class="numeric" style="margin-top: 22px; border-bottom: 0px;">单价:</th>				</tr>			</thead>			<tbody>				<tr style="border-left-width: 0px;">					<td class="numeric" style="margin-top: 20px;"><span						id="form_card">${sessionScope.cardsTong.cardtnumber}</span>					<td class="n					umeric" style="margin-top: 20px;"><span						id="form_cardnum">							${sessionScope.cardsTong.ticketremaincount }</span></td>					<td class="numeric" style="margin-top: 20px;"><span						id="form_time"><fmt:formatDate								value="${sessionScope.cardsTong.cardtdateend}"								pattern="yyyy-MM-dd" /></span></td>					<td class="numeric" style="margin-top: 20px;"><div							id="cardnumselect">							<select class="form-control"								style="width: 60px; margin-bottom: 0px;">								<option value="1">1</option>								<option value="2">2</option>								<option value="3">3</option>								<option value="4">4</option>								<option value="more">其他</option>							</select>						</div>						<div id="cardnumtext">							<input type="number" style="margin-bottom: 0px; width: 60px;">						</div></td>					<td class="numeric"						style="margin-top: 20px; border-bottom-width: 0px;"><div							id="price_select">							<select class="form-control"								style="width: 60px; height: 28px; margin-bottom: 0px;">								<c:forEach var="p" items="${sessionScope.pricemap}">									<c:if test="${p.tick1 != null}">										<option value="${p. tick1}">${p. tick1}</option>									</c:if>									<c:if test="${p.tick2 != null}">										<option value="${p. tick2}">${p. tick2}</option>									</c:if>									<c:if test="${p.tick3 != null}">										<option value="${p. tick3}">${p. tick3}</option>									</c:if>									<c:if test="${p.tick4 != null}">										<option value="${p. tick4}">${p. tick4}</option>									</c:if>									<c:if test="${p.tick5 != null}">										<option value="${p. tick5}">${p. tick5}</option>									</c:if>									<c:if test="${p.tick6 != null}">										<option value="${p. tick6}">${p. tick6}</option>									</c:if>									<c:if test="${p.tick7 != null}">										<option value="${p. tick7}">${p. tick7}</option>									</c:if>									<c:if test="${p.tick8 != null}">										<option value="${p. tick8}">${p. tick8}</option>									</c:if>								</c:forEach>								<option value="more">其他</option>							</select>						</div>						<div id="price_input">							<input type="number" style="margin-bottom: 0px; width: 60px;">						</div></td>				</tr>			</tbody>		</table>		<ul class="button"			style="margin-right: 25px; malist-style-type: none; margin-top: 20px;"			id="submitbutton">			<li style="height: 30px; list-style-type: none;"><button					style="width: 100%; background-color: #f9625b;" id="submitBtn"					class="btn" onclick="issubmit()" type="button">					消费<i class="m-icon-swapright m-icon-white"></i>				</button></li>		</ul>	</div>	<!-- 	<div class="content">		<ul class="ver-inline-menu tabbable margin-bottom-25" id="form_ul">			<li><p>					<span style="width: 70px; text-align: right;">卡号:</span><span						id="form_card"> ${requestScope.cardsTong.cardtnumber}</span>				</p></li>			<li><p>					<span>点数:</span><span id="form_cardnum">						${requestScope.cardsTong.ticketremaincount }</span>				</p></li>			<li><p>					<span>有效期:</span> <span id="form_time"><fmt:formatDate							value="${requestScope.cardsTong.cardtdateend}"							pattern="yyyy-MM-dd" /></span>				</p></li>			<li>				<div class="form-group" id="cardnumselect">					<span>消费张数:</span> <select class="form-control"						style="width: 60px; height: 35px; margin-bottom: 0px;">						<option value="1">1</option>						<option value="2">2</option>						<option value="3">3</option>						<option value="4">4</option>						<option value="more">其他</option>					</select>				</div>				<div class="form-group" id="cardnumtext">					<span>消费张数:</span> <input class="m-wrap placeholder-no-fix "						type="number">				</div>			</li>			<li><div class="form-group" id="price_select">					<span>单价:</span> <select class="form-control"						style="width: 60px; height: 35px; margin-bottom: 0px;">						<c:forEach var="p" items="${requestScope.pricemap}">							<c:if test="${p.tick1 != null}">								<option value="${p. tick1}">${p. tick1}</option>							</c:if>							<c:if test="${p.tick2 != null}">								<option value="${p. tick2}">${p. tick2}</option>							</c:if>							<c:if test="${p.tick3 != null}">								<option value="${p. tick3}">${p. tick3}</option>							</c:if>							<c:if test="${p.tick4 != null}">								<option value="${p. tick4}">${p. tick4}</option>							</c:if>							<c:if test="${p.tick5 != null}">								<option value="${p. tick5}">${p. tick5}</option>							</c:if>							<c:if test="${p.tick6 != null}">								<option value="${p. tick6}">${p. tick6}</option>							</c:if>							<c:if test="${p.tick7 != null}">								<option value="${p. tick7}">${p. tick7}</option>							</c:if>							<c:if test="${p.tick8 != null}">								<option value="${p. tick8}">${p. tick8}</option>							</c:if>							<option value="more">其他</option>						</c:forEach>					</select>				</div>				<div class="form-group" id="price_input">					<span>消费张数:</span> <input class="m-wrap placeholder-no-fix "						type="number">				</div></li>		</ul>					</div> -->	<!-- END LOGIN -->	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->	<!-- BEGIN CORE PLUGINS -->	<script src="<%=path%>/media/js/jquery-1.10.1.min.js"		type="text/javascript"></script>	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->	<script src="<%=path%>/media/js/bootstrap.min.js"		type="text/javascript"></script>	<!--[if lt IE 9]>	<script src="<%=path%>/media/js/excanvas.min.js"></script>	<script src="<%=path%>/media/js/respond.min.js"></script>  	<![endif]-->		<!-- END CORE PLUGINS -->	<!-- BEGIN PAGE LEVEL PLUGINS -->	<script src="<%=path%>/media/js/sweetalert.min.js"		type="text/javascript"></script>	<!-- END PAGE LEVEL PLUGINS -->	<!-- BEGIN PAGE LEVEL SCRIPTS -->	<!-- END PAGE LEVEL SCRIPTS -->	<script>			</script>	<!-- END JAVASCRIPTS -->	<script type="text/javascript">		var form_card = '${sessionScope.cardsTong.cardtnumber}';		var form_cardnum = '${sessionScope.cardsTong.ticketremaincount }';		var form_time = '${sessionScope.cardsTong.cardtdateend}';		$(function() {			$("#cardnumtext").hide();			$("#cardnumselect select").change(function() {				if (this.value == 'more') {					$("#cardnumselect").hide();					$("#cardnumtext").show();				}			});			$("#price_input").hide();			$("#price_select select").change(function() {				if (this.value == 'more') {					$("#price_select").hide();					$("#price_input").show();				}			});			var time = $("#form_time").text();			var newdate = new Date();			var d1 = new Date(time.replace(/\-/g, "\/"));			if (d1 < newdate) {				$("#form_time").css("color", "red");				$("#form_ul li").eq(1).remove();				$("#form_ul li").eq(3).remove();				$("#submitbutton").eq(4).remove();				$("#form_ul").append("<li><p><span>卡已过期</span></p></li>");			}		});		function issubmit() {			var cardnumtext = $("#cardnumselect select").val();			if (cardnumtext == 'more') {				cardnumtext = $("#cardnumtext input").val();			}			if (!cardnumtext) {				swal("请输入张数");				return;			}			var price = $("#price_select select").val();			if (price == 'more') {				price = $("#price_input input").val();			}			if (!price) {				swal("请输入单价");				return;			}			var param = {				form_card : form_card,				form_cardnum : form_cardnum,				form_time : form_time,				cardnumtext : cardnumtext,				price : price			}			$.post("./buyCard/buyCard.do", param, function(data) {				if (data.messageid == '2100') {					var url = data.message;					window.location.href = "./return.do?url=resultCode",							"_self";					return;				}				swal(data.messageid + data.message);			});		}	</script></body><!-- END BODY --></html>