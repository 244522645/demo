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
    <title>闻鸡起伍</title>
   	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
</head>
<body class="v3">

 <div class="page-group">
  	 	<div class="page" id="pk2zhifu">
		<div class="content">
					<div class="bg_wrap2">
		             </div>
	                <div class="content-block" id="balance">
				 		
				 		<div class="row no-gutter row_money_detail">
						      <div class="col-30"> 支付挑战金</div>
						      <div class="col-70">¥ <span id="payChallengeMoney"> ${money}</span>元 </div>
						</div>
						<div class="row no-gutter row_money_detail">
						      <div class="col-30"> 账户抵扣</div>
						      <div class="col-70">-¥ <span id="accountDeduc">${dikou}</span>元 </div>
						</div>
				 		<article class="balance_art">
				 			<h4>规则说明</h4>
				 			<div class="balance_art_wrap">
				 				<div class="row no-gutter">
								      <div class="col-5">1.</div>
								      <div class="col-95">交纳21元挑战金，连续21天打卡成功者，挑战成功，返还挑战金，否则扣除挑战金</div>
								</div>
								<div class="row no-gutter">
								      <div class="col-5">2.</div>
								      <div class="col-95">挑战成功后，会将鸡蛋孵化成斗鸡。</div>
								</div>
								<div class="row no-gutter">
								      <div class="col-5">3.</div>
								      <div class="col-95">每天坚持早起完成2.5km晨跑，并于当天早9点之前，将运动截图发送至公众号平台（可下载咕咚等运动软件记录），审核通过后，视为打卡成功。</div>
								</div>
				 			</div>
				 		</article>
	                </div>
	            </div>
	            <nav class="bar bar-tab bar_w">
			<a class="tab-item external" href="#">
			  实付款：¥ <span id="actualPay">${shifu}</span>元   
			 </a>
			<a class="tab-item external" href="#">
			</a>
			<a class="tab-item external alert-text pk1_btn_zhifu" id="tab_btn" href="javascript:void(0);">
			  立即支付
			</a>
		</nav>
	</div>
  	 </div>
	
 	<script type='text/javascript' src='${ctx}/static/public/sm.min.js' charset='utf-8'></script>
 <script type='text/javascript' src='${ctx}/static/public/sm-extend.min.js' charset='utf-8'></script>
 <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
 
    <!-- Left Panel with Reveal effect -->
   	<script>
   	$(function(){
   		$.init();
   	});
   	</script>

</body>
<script type="text/javascript">
function Crow() {
    var config = {};
    this.get = function(n) {
        return config[n];
    }

    this.set = function(n, v) {
        config[n] = v;
    }
    this.init();
}
//支付
Crow.payment = function(charge,callback) {
			pingpp.createPayment(charge, callback);
        }
        

var payChallengeMoney=${money};  //支付挑战金
var accountDeduc=${dikou};   //账户抵扣
var actualPay=${shifu};   //实际付款
//var actualPay=0.1;   //实际付款
//个人模式
Crow.pkme = {
		//获取当前状态
		init: function() {
        },
        //1.创建订单2.支付5元。3.开启模式
        createSendPkme: function() {
        	$.toast("暂时不能开启！");
        	return;
        	//创建挑战
				$.post(ctx+"/wechat/v3/crow/pkme/getFristPkme", {type:2},function(data,status){
	        		if(data.state==1){
	        			Crow.pkme.createPayment();
	        		}
	        		if(data.state==0){
	        			$.alert(data.message, '开启失败',function(){
   	        				window.location.href="${ctx}/wechat/v3/crow/index";
   	        			});
	        		}
	   		 	}) 
        },
        createPayment: function (){
        	
        	$.post("${ctx}/wechat/v3/myAccount/applyPay", 
    				{actualPayment:actualPay,accountDeduc:accountDeduc,payChallengeMoney:payChallengeMoney,title:"闻鸡起伍"}, 
    				function(result){
    					if(result.state == 0){
    						$.toast(result.message);
    					}else if(result.state == 1&& result.message == ""){  //实际付款为 0 的情况
    						$.toast("支付成功！");
    						//创建挑战
	           				$.post(ctx+"/wechat/v3/crow/pkme/createSecondPkme", {},function(data,status){
	           	        		if(data.state==0){
	           	        			$.alert('已开启挑战，请按时打卡', '已开启挑战',function(){
	           	        				window.location.href="${ctx}/wechat/v3/crow/index";
	           	        			});
	           	        			
	           	        		}
	           	        		if(data.state==1){
	           	        			$.alert('挑战将于明日开启请按时打卡', '挑战发起成功',function(){
	           	        				window.location.href="${ctx}/wechat/v3/crow/index";
	           	        			});
	           	        		}
	           	   		 	}) 
    					}else if(result.state == 1 && result.message!=""){
    						var charge = result.message;
    	          				pingpp.createPayment(charge, function(result, err) {
    		           			  if (result=="success") {
    		           				$.toast("支付成功！");
    		           				var orderNo = charge.order_no;
    		           				$.post("${ctx}/wechat/v3/myAccount/payResult", 
    	           						{orderNo:orderNo,weixinPayResult:"success"}, 
    	           						function(result){
    	           							
    	           						}
    		           				);
    		           				//创建挑战
    		           				$.post(ctx+"/wechat/v3/crow/pkme/createSecondPkme", {},function(data,status){
    		           	        		if(data.state==0){
    		           	        			$.alert('已开启挑战，请按时打卡', '已开启挑战',function(){
    		           	        				window.location.href="${ctx}/wechat/v3/crow/index";
    		           	        			});
    		           	        			
    		           	        		}
    		           	        		if(data.state==1){
    		           	        			$.alert('挑战将于明日开启请按时打卡', '挑战发起成功',function(){
    		           	        				window.location.href="${ctx}/wechat/v3/crow/index";
    		           	        			});
    		           	        		}
    		           	   		 	}) 
    		           			  } else {
    		           				$.toast("未支付！");
    		           			  }
    		           			});
    					}else{
    						$.toast("支付失败");
    					}
    				});
        	}
	}
</script>
<script type="text/javascript">
//页面跳转
$(document).on("pageInit", function(e, pageId, $page) {
	
	// 挑战自己
	if(pageId == 'pk2zhifu'){
		 Crow.pkme.init();
		 $(".pk1_btn_zhifu").unbind( "click" ).on("click",function(){
			 Crow.pkme.createSendPkme();
		 });
	}
	
});
</script>	
</html>