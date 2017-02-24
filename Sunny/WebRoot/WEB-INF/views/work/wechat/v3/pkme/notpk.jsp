<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/wechat/v3/common/static.jsp"%>
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
        <div class="page" id="notpk">
            <div class="content bcakcolor_l jieshoutiaoz">
            	<div class="datupian_l">
            		<img src="${ctx}/static/v3/img/cao.png">
            	</div>
				<p class="tishi_l">您还没有开启个人挑战</p>
				<div class="gerentiaozhan_l" id="pkmeid">
					<a href="${ctx}/wechat/v3/pkpay" class="external">开启个人挑战</a>
				</div>
				<div class="tiaozhanshuom_l">
					<div class="shang_l">
						<img src="${ctx}/static/v3/img/shang.png" />
					</div>
					<div class="zhon_l">
						<p><b>挑战规则：</b></p>
						<div class="dueizhan_l">
							连续五天完成打卡任务者，视为挑战成功，否则挑战失败。
挑战成功后，返还挑战金，失败后扣除挑战金。
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


	  
    <div class="panel-overlay"></div>
 	<script type='text/javascript' src='${ctx}/static/public/sm.min.js' charset='utf-8'></script>
 <script type='text/javascript' src='${ctx}/static/public/sm-extend.min.js' charset='utf-8'></script>
 <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
 
    <!-- Left Panel with Reveal effect -->
   	<script>
   	$(function(){
   		$.init();
   	});
   	</script>

    <script>     
      $(document).on('click','.ruhedaka_l',function () {
        $.alert('每天坚持早起完成2.5km晨跑，并于当天早9点之前，将运动截图发送至公众号平台（可下载咕咚等运动软件记录），审核通过后，视为打卡成功。');
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
//个人模式
Crow.pkme = {
		//获取当前状态
		init: function() {
        },
        //1.创建订单2.支付5元。3.开启模式
        createFristPkme: function() {
        	$.post(ctx+"/wechat/v3/crow/pkme/createFristPkme", {},function(data,status){
   			 $.toast("创建成功");
   			 console.log(data);
   		 }) 
        },
        createPayment: function (charge){
        	pingpp.createPayment(charge, function(result, err) {
     			  if (result=="success") {
     					//Reserve.sendNoice(order.id);
	                	window.location.href="${ctx}/wechat/v2/reserve/showOrder?orderId="+order.id;
     			  } else {
     			    console.log(result+" "+err.msg+" "+err.extra);
     			  }
     			});
        }
        
	}
</script>
<script type="text/javascript">
//页面跳转
$(document).on("pageInit", function(e, pageId, $page) {
	
	// 挑战自己
	if(pageId == 'notpk'){
		/*  $("#pkmeid").unbind( "click" ).on("click",function(){
			 window.location.href="${ctx}/wechat/v3/crowpk/me/pkpay";
		 }); */
	}
	
});
</script>	
</html>