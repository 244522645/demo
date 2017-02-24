<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/wechat/v3/common/static.jsp"%>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${config.siteName}</title>
    <meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
  </head>
  <body class="v2">
  	 <div class="page-group">
  	 	<div class="page">
			<div class="content">
		        <div class="content-block">
					<figure class="bg_icon">
						<img src="${ctx}/static/v3/img/sunqianji.png" alt="">
					</figure>
					<h3 class="title_G">钱包余额
						<span class="red"> ¥${balance }</span>
						<span style="display: none" id="balance">${balance }</span>
					</h3>
						
					<div class="num_G">
						<p class="flag">
							<label class="flag-item">提现金额</label>
							<input  class="flag-item red" type="number" id="withdrawCash" placeholder="请输入提现金额">
						</p>
						<!-- <p class="err">
							余额不足，已有5人应战，请选择挑战人数
						</p> -->
					</div>
					<div class="balance_art">
						<h4 style="padding-top:10px">提现说明</h4>
						<div class="balance_art_wrap">
							<div class="row no-gutter">
								  <div class="col-5">1.</div>
								  <div class="col-95">账户余额可提现到微信零钱，每日每笔提现限额：1~2000元。</div>
							</div>
							<div class="row no-gutter">
								  <div class="col-5">2.</div>
								  <div class="col-95">余额可直接用于抵扣挑战金，每次挑战优先扣除账户余额。</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<nav class="bar bar-tab">
				<a class="tab-item external" onclick="withdrawCash();">
					<span class="tab-label">提现</span>
				</a>
			</nav>
		</div>
  	 </div>
  </body>
 
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/v3/myAccount/js_myAccount.jsp"%>
<script>

</script> 
</html>
