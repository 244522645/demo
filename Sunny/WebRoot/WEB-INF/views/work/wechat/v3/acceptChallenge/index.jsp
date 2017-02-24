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
  	 		<div class="content bcakcolor_l jieshoutiaoz">
            	<div class="datu_l">
            		<img src="${ctx}/static/v3/img/datu.png">
            	</div>
				<div class="tiaozhan_l row">
					<div class="touxiangzj_l col-33">
						<img src="${userInfo.wechatHeadUrl }">
					</div>
					<div class="vs_l col-33">
						<img src="${ctx}/static/v3/img/vs.png" />
					</div>
					<div class="touxianggtr_l col-33">
						<img src="${otherUser.wechatHeadUrl }"/>
					</div>
				</div>
				<div class="jieshou_l">
					接受挑战
				</div>
				<div class="tiaozhanshuom_l">
					<div class="shang_l">
						<img src="${ctx}/static/v3/img/shang.png" />
					</div>
					<div class="zhon_l">
						<p><b>挑战规则：</b></p>
						<div class="dueizhan_l">
							1. 交纳5元挑战金参与对战，连续5天打卡成功者，挑战成功，返还挑战金，如果对手挑战失败，将赢取对手挑战金。挑战失败者，将扣除挑战金。<br />
							2. 首次对战成功会得到鸡蛋一枚，鸡蛋会带来惊喜哟！
							<a class="ruhedaka_l">如何打卡</a>
						</div>
					</div>
					<div class="xia_l">
						<img src="${ctx}/static/v3/img/xia.png" />
					</div>
				</div>
			</div>
		</div>
  	 </div>
  	 <div class="popup popup-jieshou_l" id="pay">
	  <div class="content-block close-popup heikuang_l"></div>
		  <div class="zhifu_l">
		  	 <div class="zhifutianzhan_l row">
		  	 	<div class="tianzhanjin_l col-50">
		  	 		支付挑战金
		  	 	</div>
		  	 	<div class="tianzhanjine_l col-50">
		  	 	￥ <span id="payChallengeMoney">0</span>元
		  	 	</div>
		  	 </div>
		  	 <div class="zhanghudikou_l row">
		  	 	<div class="tianzhanjin_l col-50">
		  	 		账户余额
		  	 	</div>
		  	 	<div class="tianzhanjine_l col-50">
		  	 	￥ <span>${myBalance }</span>元
		  	 	</div>
		  	 	<div class="tianzhanjine_l col-50" style="display: none">
		  	 	￥ <span id="accountDeduc">0</span>元
		  	 	</div>
		  	 </div>
		  	 <div class="dianzhifu_l">
		  	 	<div class="xvzhifu_l">实付款：¥ <span id="actualPay">0</span>元   </div>
		  	 	<div class="querenzhif_l" id="pay_btn">立即支付</div>
		  	 </div>
		  </div>
	</div>
  	 <div class="popup popup-bujieshou_l" id="openChallenge">
	    <div class="content-block close-popup heikuang_l"></div>
		<div class="faqitiao_l">
		  	<p>发起人挑战名额不足</p>
			<p>您可重新发起挑战</p>
			<div class="kaiqi_l" id="go_faqitiaozhan">开启挑战</div>
		</div>
	</div>
	
	<div class="popup popup-bujieshou_l" id="gerentiaozhan">
	    <div class="content-block close-popup heikuang_l"></div>
		<div class="faqitiao_l">
		  	<p>发起人挑战名额不足</p>
			<p>您可发起个人挑战</p>
			<div class="kaiqi_l" id="go_geren">个人挑战</div>
		</div>
	</div>
	
	
	<div class="popup popup-bujieshou_l" id="zhiNengGeRen">
	    <div class="content-block close-popup heikuang_l"></div>
		<div class="faqitiao_l">
		  	<p>您必须完成个人挑战，才能接受别人的挑战</p>
			<div class="kaiqi_l">确定</div>
		</div>
	</div>
	
	<div class="popup popup-bujieshou_l" id="zhiNengPk">
	    <div class="content-block close-popup heikuang_l"></div>
		<div class="faqitiao_l">
		  	<p>您已经参加了对战，不能开启个人挑战</p>
			<div class="kaiqi_l">确定</div>
		</div>
	</div>
	
  </body>
 
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<script>

var payChallengeMoney;  //支付挑战金
var accountDeduc;   //账户抵扣
var actualPay;   //实际付款
var myBalance = "${myBalance }";
var otherBalance = "${otherBalance }";
var myLevel = "${myLevel }";
var pkOneListSize = "${pkOneListSize }";
var pkMeListSize = "${pkMeListSize }";
//myBalance = "10";   //测试数据
//otherBalance = "0";    //测试数据

//测试代码【开始】
var myId = "${userInfo.id}";


if(myId == 'o3qhbv47EcyggsNNXBDjOjnpq06c'){
	payChallengeMoney = 0.01;
	actualPay = 0.01;
	alert("payChallengeMoney = 0.01;");
}

//测试代码【结束】


$(function(){
	$.init();

	$(document).on('click','#go_geren',function () {
		window.location.href="${ctx}/wechat/v3/pkpay";
	 });

	$(document).on('click','#go_faqitiaozhan',function () {
		window.location.href="${ctx}/wechat/v3/pkChongZhi";
	 });
	$(document).on('click','.ruhedaka_l',function () {
	    $.alert('每天坚持早起完成2.5km晨跑，并于当天早9点之前，将运动截图发送至公众号平台（可下载咕咚等运动软件记录），审核通过后，视为打卡成功。');
	});
	
	$(document).on('click','.jieshou_l', function () {
		
		
		$.post("${ctx}/wechat/v3/checkIsFirstChallenge", 
				{userId:myId,otherUserId:'${otherUser.id}'}, 
				function(result){
					if(result.state == 0){ //返回状态 0 失败 ，1 成功 
						    $.alert(result.message);
					}else{
						//判定对方的余额够不够:
						//1.对方够：弹出充值框
						//2.对方不够：a.我如果完成5天个人挑战==>重新发起挑战；b. 我没有完成5天个人挑战，可以开启“个人挑战”模式
						if(otherBalance >= 5){
							payChallengeMoney = 5;
							if( myBalance - payChallengeMoney >= 0){
								accountDeduc = payChallengeMoney;
								actualPay = 0;
							}else{
								accountDeduc = 0;
								actualPay = payChallengeMoney;
							}
							$("#payChallengeMoney").html(payChallengeMoney);
							if(accountDeduc > 0){
								$("#accountDeduc").html("-"+accountDeduc);
							}else{
								$("#accountDeduc").html(accountDeduc);
							}
							$("#actualPay").html(actualPay);
							$.popup('#pay');
						}else{
							if(myLevel == '1'){
								$.popup('#openChallenge');
							}else{
								$.popup('#gerentiaozhan');
							}
						}
					}
				}
		);
		
	 });
	// 立即支付
	$("#pay_btn").bind("click",function(){

		//测试代码【开始】

		if(myId == 'o3qhbv47EcyggsNNXBDjOjnpq06c'){
			payChallengeMoney = 0.01;
			actualPay = 0.01;
			alert("立即支付payChallengeMoney = 0.01;");
		}

		//测试代码【结束】
		$.post("${ctx}/wechat/v3/myAccount/applyPay", 
				{actualPayment:actualPay,accountDeduc:accountDeduc,payChallengeMoney:payChallengeMoney,title:"应战支付",pay_type:"yzZhiFu",otherUserId:'${otherUser.id}'}, 
				function(result){
					if(result.state == 0){
						$.alert(result.message);
					}else if(result.state == 1&& result.message == ""){  //实际付款为 0 的情况
						var orderNo = result.t.orderId;
           				$.post("${ctx}/wechat/v3/acceptChallenge/payResult", 
       						{orderNo:orderNo,weixinPayResult:"success",otherUserId:'${otherUser.id}',deduct:accountDeduc,actualPay:actualPay,payType:"yue"}, //yue:余额抵扣
       						function(result){
       							if(result.state ==1){
       								$.alert('对战将于明日开启，请按时打卡', '应战成功',function(){
	           	        				window.location.href="${ctx}/wechat/v3/crow/index";
	           	        			});
       							}else{
       								$.alert(result.message);
       							}
       						}
           				);
					}else if(result.state == 1 && result.message!=""){
						var charge = result.message;
						var orderNo = result.t.orderId;
	          				pingpp.createPayment(charge, function(result, err) {
		           			  if (result=="success") {
		           				$.post("${ctx}/wechat/v3/acceptChallenge/payResult", 
	           						{orderNo:orderNo,weixinPayResult:"success",otherUserId:'${otherUser.id}',deduct:accountDeduc,actualPay:actualPay,payType:"weixin"}, //weixin：微信支付
	           						function(result){
	           							if(result.state ==1){
	           								$.alert('对战将于明日开启，请按时打卡', '应战成功',function(){
			           	        				window.location.href="${ctx}/wechat/v3/crow/index";
			           	        			});
	           							}else{
	           								$.alert(result.message);
	           							}
	           						}
		           				);
		           			  } else {
		           			    console.log(result+" "+err.msg+" "+err.extra);
		           			  }
		           			});
					}else{
						$.alert("充值失败");
					}
						
				});
		
	});
	
	
});
//页面跳转
$(document).on("pageInit", function(e, pageId, $page) {
	
	
});
</script> 
</html>
