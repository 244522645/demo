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
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
</head>
<div class="page">
		<div class="content" id="sign">
			<div class="header">
		 		<!-- 成功 -->
				<c:if test="${mode eq 1 and result eq 1}">
 				<div class="sign_title">
					<h3><small>${title}第</small>${completeday}<small>天</small></h3>
					<div class="sign_pro">
					
						<div class="pro_con pro_con_center" style="width:${baifenbi }">${baifenbi }&nbsp;</div> 
					</div>
					<img src="${ctx}/static/v3/img/bad.png" alt="">
					
				</div>
				<div class="schedule_con">
					<div class="sche_mo_w">
					
						<img src="${ctx}/static/v3/img/medal.png" alt="">
						<h3>恭喜您已经完成了${title}挑战</h3> 
					</div>
					<c:if test="${level eq 1}">
					<a href="${ctx}/wechat/v3/pkpay" class="external btn">开启斗鸡初成挑战</a> 
					</c:if>
					<h4 class="open-vertical-modal">查看挑战规则</h4>
				</div>
 				</c:if>
 				<c:if test="${mode eq 1 and result eq 0}">
 				<div class="sign_title">
					<h3><small>${title}第</small> ${completeday} <small>天</small></h3>
					<div class="sign_pro">
						<div class="pro_con" style="width:${baifenbi }">${baifenbi }&nbsp;</div>
		
					</div>
					<img src="${ctx}/static/v3/img/bad.png" alt="">
					
					<img src="${ctx}/static/v3/img/good.png" alt="">
				</div>
				<div class="schedule_con">
					<div class="sche_mo_w">
						<img src="${ctx}/static/v3/img/medal_bad.png" alt="">
						<h3 class="err">${title}挑战失败</h3> 

					</div>
					<a href="${ctx}/wechat/v3/pkpay" class="btn external">重新挑战</a>
					<h4 class="open-vertical-modal">查看挑战规则</h4>
				</div>
 				</c:if>
				<c:if test="${mode eq 0}">
 				<div class="sign_title">
					<h3><small>${title}第</small> ${completeday } <small>天</small></h3>
					<div class="sign_pro">
						<div class="pro_con" style="width:${baifenbi }">${baifenbi }&nbsp;</div>
					</div>	
				</div>
				<h4>规则说明</h4>
				<p>${title}，连续${day}天早晨禅跑，<br>并于早9点之前，将禅跑记录发送到平台，<br>即为挑战成功！并获赠鸡蛋一枚！<br>鸡蛋会带来惊喜哟！</p>
				 
 				</c:if>
			</div>
			<div class="date">
				<div class="wrap">
					<div class="mask_G"></div>
					<div class="cal_header">
						<div class="icon last_month"></div>
						<div></div>
						<div class="icon next_month"></div>
					</div>
					
				</div>

				<div class="date_con">
					<p class="flag">
						<span class="flag-item">一共坚持天数：</span>
						<span class="flag-item"><strong>${crowUserInfo.alldays }</strong>天</span>
					</p>
					<p class="flag">
						<span class="flag-item">连续坚持天数：</span>
						<span class="flag-item"><strong>${crowUserInfo.continuousDay }</strong>天</span>
					</p>
					<!-- <p class="flag">
						<span class="flag-item">参与人数：</span>
						<span class="flag-item"><strong>1740</strong>天</span>
					</p> -->
				</div>
			</div>
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
   		<script>
   		
   		//生成日历
		Calendar = {
	  			shang:'',
	   			xia:'',
	   			month:'',
	   			data:'',
 				init: function(url, date) {
 					$.post(url, date,function(data,status){
 		   				if(data.state==0){
 		   					$.toast("加载失败");
 		   				}
 		   			Calendar.shang=data.shang;
 		   			Calendar.xia=data.xia;
 		   			Calendar.month=data.month;
 			   			var dateValues=[];
 			   			
 			   			var days=data.data;
 			   			if(days==null){days=[];}
 			   			var datas=""
 			   			for(var i=0; i<days.length; i++){
 			   				dateValues.push(Calendar.month+'-'+days[i])

 			   			}
 			   			if($("#calendar").size()){
 			   				$("#calendar").remove()
 			   			}
 			   			$(".wrap").append("<div id='calendar'></div>");
 			   			var maxDate=new Date();
 			   			var cssset=false;
 			   			if(dateValues.length<1){
 			   			var cssset=true;
	 			   			dateValues=[Calendar.month+'-20'];
	 			   			
 			   			}
 				   		$("#calendar").calendar({
 				   			value: dateValues,
 				   			minDate: '2016-12-05',
 				   			maxDate: maxDate
 				   		});
	 				   	var ii=0;
	 				   	if(cssset){
	 				   	$("div[data-day^='20']").find("span").each(function(){
	 			   	   		$(this).css('background','none');
	 			   	 	 });
	 				   	}
			   			
 			   			//var spanhtml=$("div[data-day^='1']").find("span");
 			   			
 				   		$(".current-month-value, .current-year-value").css("color","#f7172d")
 				   		var str=$(".current-month-value").text()+" "+$(".current-year-value").text();
 				   	//	$(".cal_header").find("div").eq(1).text(str);
 				   	$(".cal_header").find("div").eq(1).text(Calendar.month);
 				   		
 			   		})
 		        },
   		}
		Calendar.init(ctx+'/wechat/v3/crow/punch/data', {})
   		
   		$(document).on('click','.open-vertical-modal', function () {
			    $.modal({
			      title:  '斗鸡初成挑战规则',
			      extraClass: 'bg-red',
			      text: '连续<strong>21天</strong>早晨禅跑，<br /> 并于<strong>早9点</strong>之前，将禅跑记录发送到平台,<br /> 即为<strong>挑战成功</strong>！<br /> 鸡蛋孵化成功后将得到一只<strong>小斗鸡</strong>！<br /> 小斗鸡的成长会给你带来无限乐趣！',
			      verticalButtons: true,
			      buttons: [
			        {
			          text: '确 定'
			        }
			      ]
			    })
		});
   	</script>
   	
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
    			console.log(0);
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
	 $(".last_month").unbind( "click" ).on("click",function(){
		 Calendar.init(ctx+'/wechat/v3/crow/punch/data', {newDate:Calendar.shang})
	 });
	 $(".next_month").unbind( "click" ).on("click",function(){
		 Calendar.init(ctx+'/wechat/v3/crow/punch/data', {newDate:Calendar.xia})
	 });
	// 挑战自己
	 $("#pkmeid").unbind( "click" ).on("click",function(){
		 
		// Crow.pkme.init();
		/*  $.get(ctx+"/wechat/v3/crowpk/me/createFristPkme", {},function(data,status){
			 $.toast("创建成功");
			 console.log(data);
		 }) */
	 });
});
</script>	
</body>
</html>