<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>芸备胎采购系统-支付</title>
	</head>
	<body>
	</body>
<!-- 	<script type="text/javascript" src="${ctx}/static/js/ping_pp/pingpp.js"></script>  -->
	<script type="text/javascript" src="${ctx}/static/js/ping_pp/pingpp-pc.js"></script> 
<script>

	pingppPc.createPayment(${alipay_pc_charge}, function(result, err) {
		console.log(result);
		console.log(err);
	});
</script>
</html>