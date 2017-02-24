<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.pingplusplus.util.WxpubOAuth" %>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/chxt/config.css" />

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <title>芸备胎支付</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
  </head>
  <style>
	.row{margin:4rem 0.5rem 3rem 0.5rem;background-image:url("${ctx}/static/images/chxt/logo_s.png");
	background-repeat:no-repeat;background-color: #F6F7F4;background-position: center;}
	.top{margin-top:2rem;}
	.top>h5{font-weight: 700;}
	.order-table{
		margin:2rem 0.5rem;
		width:80%;
		font-size: 1.5rem;
		line-height: 1.6rem;
	}
	.order-table tr{
		height: 5rem;
	}
	.btn-block{
		width:60%;
		position:fixed; 
		left:20%;
		bottom:25%;
		height: 4rem;
		font-size: 1.5rem;
	}
</style>
<body>
	<div class="container-fluid" >
		<div class="row">
			<div class="col-xs-12  text-center top">
				<h5>订单号：${order.orderNo}</h5>
				<%-- <h1 style="color:green;">&yen;${order.payTotalPrice}</h1> --%>
			</div>
			<div class="col-xs-12">
				<table class="order-table">
			<!-- 	  <thead>
				  	<tr>
				  		<th colspan="2">商品信息</th>
				 	 </tr>
				  </thead> -->
				  <tbody>
				  	<tr>
				  		<th style="width: 30%">商品</th><td style="width:70%">${name}</td>
				 	 </tr>
				 	 <tr>
				  		<th>总金额</th><td id="goodNumber">&yen;  ${order.payTotalPrice}</td>
				 	 </tr>
				  </tbody>
				</table>
				<%-- <table class="table">
				  <thead>
				  	<tr>
				  		<th colspan="2">收件人</th>
				 	 </tr>
				  </thead>
				  <tbody>
				  	<tr>
				  		<td style="width: 20%">名称</td>
				  		<td>${order.buyerPerson}</td>
				 	 </tr>
				 	 <tr>
				  		<td>电话</td>
				  		<td>${order.buyerPhone}</td>
				 	 </tr>
				 	  <tr>
				  		<td>地址</td>
				  		<td>${order.buyerAddress}</td>
				 	 </tr>
				  </tbody>
				</table> --%>
			</div>
		</div><!-- 页面主题内容结束  row -->
		<button type="button"  id="payForWeiXinBut" class="btn btn-primary btn-block">确定支付  &yen; ${order.payTotalPrice}</button>
	</div><!-- 外包裹容器结束 container -->
</body>
  <script type="text/javascript" src="https://one.pingxx.com/lib/pingpp_one.js"></script>
<script src="${ctx}/static/js/chxt/ap.js"></script>
  <script type="text/javascript">
	var subject ="${name}";
	var metering = 0;
	var body="名称：${name}-数量：${number}"+"-由${sellBusiness.name}提供,下单时间"+'${order.createTime}';
//   	alert(" app_id:'app_jHaHuHzLuvj1D8Wf', order_no:'${order.orderNo}',  amount:(('${order.payTotalPrice}'/1).toFixed(2))*100,channel:[${channel}],charge_url:'${orderUrl}',charge_param:{subject:'${name}',body:body},open_id:'${openId}',debug:false");
	
	document.addEventListener('pingpp_one_ready',function(){
  	    document.getElementById('payForWeiXinBut').addEventListener('click',function(){
  	    	metering++;//计次，用于生产订单号
  	        pingpp_one.init({
  	            app_id:'app_bjbnjH9mzjTGrT0u',                     //该应用在 ping++ 的应用 ID
  	            order_no:'${order.orderNo}',                     //订单在商户系统中的订单号
  	            amount:(('${order.payTotalPrice}'/1).toFixed(2))*100,   //订单价格，单位：人民币 分
  	            // 壹收款页面上需要展示的渠道，数组，数组顺序即页面展示出的渠道的顺序
  	            channel:[${channel}],
  	            charge_url:'${orderUrl}',  //商户服务端创建订单的 url
  	            charge_param:{subject:'${name}',body:body,metering:metering},                      //(可选，用户自定义参数，若存在自定义参数则壹收款会通过 POST 方法透传给 charge_url)
  	            open_id:'${openId}',                      //(可选，使用微信公众号支付时必须传入)
  	            debug:false                                 //(可选，debug 模式下会将 charge_url 的返回结果透传回来)
  	        },function(res){
  	            //debug 模式下获取 charge_url 的返回结果
  	            if(res.debug&&res.chargeUrlOutput){
  	                console.log(res.chargeUrlOutput);
  	            }
  	            if(!res.status){
  	            	//调取微信接口失败
  	            	 alert(res.msg);
  	            }
  	            else{
  	                //debug 模式下调用 charge_url 后会暂停，可以调用 pingpp_one.resume 方法继续执行
  	                if(res.debug&&!res.wxSuccess){
  	                    if(confirm('当前为 debug 模式，是否继续支付？')){
  	                        pingpp_one.resume();
  	                    }
  	                }
  	                //若微信公众号渠道需要使用壹收款的支付成功页面，则在这里进行成功回调，
  	                //调用 pingpp_one.success 方法，你也可以自己定义回调函数
  	                //其他渠道的处理方法请见第 2 节
  	                else {
  	                	window.location.href='${ctx}/pay/weixinPaySucess?orderId=${order.id}';
  	                }
  	            }
  	        });
  	    });
  	});
 	</script>
</html>
