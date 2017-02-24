<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/wechat/v3/common/static.jsp"%>
	<script type="text/javascript" src="${ctx}/static/js/ping_pp/pingpp.js"></script>
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
  <body>
  	 <div class="page-group">
  	 	<div class="page">
		<div class="content">
		<div class="bg_wrap"></div>
	                <div class="content-block" id="balance">
				 		<div class="num_G">
				 			<div class="flag">
					 			<p class="flag-item"><label>挑战人数</label></p>
					 			<p class="flag-item"><input class="flag-item" type="number" id="chellangerCount" placeholder="请输入挑战人数"></p>
				 			</div>
				 		</div>
				 		<div class="row no-gutter row_money_detail">
						      <div class="col-30"> 应付挑战金</div>
						      <div class="col-70">¥ <span id="payChallengeMoney">0</span>元 </div>
						</div>
						<div class="row no-gutter row_money_detail">
						      <div class="col-30"> 账户余额</div>
						      <div class="col-70">¥ <span>${balance }</span>元 </div>
						      <div class="col-70" style="display: none">¥ <span id="accountDeduc">0</span>元 </div>
						</div>
				 		<article class="balance_art">
				 			<h4>规则说明</h4>
				 			<div class="balance_art_wrap">
				 				<div class="row no-gutter">
								      <div class="col-5">1.</div>
								      <div class="col-95">每挑战一人交纳5元挑战金，连续5天打卡成功者，挑战成功，返还挑战金，如果对手挑战失败，将赢取对手挑战金。挑战失败者，将扣除挑战金。</div>
								</div>
								<div class="row no-gutter">
								      <div class="col-5">2.</div>
								      <div class="col-95">首次对战成功会得到斗鸡蛋一枚，可解锁斗鸡初成挑战！</div>
								</div>
								<div class="row no-gutter">
								      <div class="col-5">3.</div>
								      <div class="col-95">每天坚持早起完成2.5km晨跑，并于当天早9点之前，将运动截图发送至公众号平台（可下载咕咚等运动软件记录），审核通过后，视为打卡成功。	</div>
								</div>
				 			</div>
				 		</article>
	                </div>
	            </div>
	            <nav class="bar bar-tab bar_w">
			<a class="tab-item external" href="#">
			  实付款：¥ <span id="actualPay">0</span>元   
			 </a>
			<a class="tab-item external" href="#">
			</a>
			<a class="tab-item external alert-text" id="tab_btn" href="#">
			  立即支付
			</a>
		</nav>
	</div>
  	 </div>
  </body>
 
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
 <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<script>

var payChallengeMoney="0";  //支付挑战金
var accountDeduc="0";   //账户抵扣
var actualPay="0";   //实际付款
var myBalance = "${balance }";   //我的余额
//var myBalance = 10;   //测试数据
$(function(){
	$.init();
	
	$("#chellangerCount").bind("change",function(){
		var count = $("#chellangerCount").val();
		payChallengeMoney = count * 5;//1
		if( myBalance - payChallengeMoney >= 0){
			accountDeduc = payChallengeMoney;
			actualPay = 0;
		}else{
			accountDeduc = myBalance;
			actualPay = payChallengeMoney - myBalance;
		}
		
		$("#payChallengeMoney").html(payChallengeMoney);
		
		if(accountDeduc > 0){
			$("#accountDeduc").html("-"+accountDeduc);
		}else{
			$("#accountDeduc").html(accountDeduc);
		}
		
		$("#actualPay").html(actualPay);
		
	});
	
	
	$("#tab_btn").bind("click",function(){
		
		if(actualPay == "0"){
			$.alert('邀请好友参与对战', '对战发起成功',function(){
   				window.location.href="${ctx}/wechat/v3/crow/index?shareindex=paySuccess";
   			});
		}else{
			$.post("${ctx}/wechat/v3/myAccount/applyPay", 
					{actualPayment:actualPay,accountDeduc:accountDeduc,payChallengeMoney:payChallengeMoney,title:"契约金_pk对战支付",pay_type:"pkChongZhi"}, 
					function(result){
						//console.log(result);
						if(result.state == 0){
							$.toast(result.message);
						}
						/* else if(result.state == 1&& result.message == ""){  //实际付款为 0 的情况
							var orderNo = result.t.orderId;
	           				$.post("${ctx}/wechat/v3/myAccount/payResult", 
	       						{orderNo:orderNo,weixinPayResult:"success",deduct:accountDeduc,actualPay:actualPay}, 
	       						function(result){
	       							if(result.state == 1){
	       								$.alert('邀请好友参与对战', '对战发起成功',function(){
		           	        				window.location.href="${ctx}/wechat/v3/crow/index?shareindex=paySuccess";
		           	        			});
	       							}
	       							
	       						}
	           				);
						} */
						else if(result.state == 1 && result.message!=""){
							var charge = result.message;
							var orderNo = result.t.orderId;
		          				pingpp.createPayment(charge, function(result, err) {
			           			  if (result=="success") {
			           				$.post("${ctx}/wechat/v3/myAccount/payResult", 
		           						{orderNo:orderNo,weixinPayResult:"success",deduct:accountDeduc,actualPay:actualPay}, 
		           						function(result){
		           							if(result.state == 1){
		           								$.alert('邀请好友参与对战', '对战发起成功',function(){
				           	        				window.location.href="${ctx}/wechat/v3/crow/index?shareindex=paySuccess";
				           	        			});
		           							}
		           							
		           						}
			           				);
			           			  } else {
			           			    console.log(result+" "+err.msg+" "+err.extra);
			           			  }
			           			});
						}else{
							$.toast("支付失败");
						}
							
					});
		}
		
		
		
		
	});
});
//页面跳转
$(document).on("pageInit", function(e, pageId, $page) {
	
	
});
</script> 
</html>
