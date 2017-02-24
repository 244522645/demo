<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>制作福卡-${config.siteName}</title>
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
            <!-- 标题栏 -->
            <header class="bar bar-nav">
                <button class="button button-link button-nav pull-left">
                    <span class="icon icon-left"></span>
                </button>
                <div class="buttons-tab">
                    <c:forEach items="${proList}" var="pro" varStatus="status" >
                    	<a href="#tab${status.index}" class="tab-link button cardType <c:if test="${status.index eq 0}">active</c:if>">
                    	${pro.pro_value}</a>
                    </c:forEach>
                </div>
            </header>
            <!-- 内容 -->
            <div class="content">
                <div class="content-block">
                    <div class="tabs">
                        <!-- 生日祝福_标签页1 -->
                        <c:forEach items="${proList}" var="pro" varStatus="status">
                        <c:if test="${status.index lt proList.size()-1}">
                       	 <div id="tab${status.index}" class="tab <c:if test="${status.index eq 0}">active</c:if>">
                            <div class="${pro.im_id}">
                                <div class="header">
                                    <span>${pro.pro_value}</span>
                                </div>
                                <div class="content-img">
                                    <span><a href="###">更换图片</a></span>
                                    <img src="${config.imgDomainName}/${photo.imgId.folder }/${photo.imgId.name}_284x290.jpg" alt="风景图片">
                                </div>
                                <div class="footer">
                                    <img class="user-icon" src="img/icon/user.png" alt="头像">
                                    <div class="list-block card-list-block">
                                        <ul class="clear-ba">
                                            <li>
                                                <div class="item-content">
                                                    <div class="item-inner clear-ba">
                                                        <div class="item-title label">祝：</div>
                                                        <div class="item-input">
                                                            <input type="text" placeholder="亲友姓名">
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="item-content">
                                                    <div class="item-inner">
                                                        <div class="item-input">
                                                            <textarea placeholder="${pro.childrenList.get(0).pro_value}" style="text-align:center;" id="greetings"></textarea>  
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                    <p class="footer-link open-about"><a href="#">更多祝福语模板</a></p>
                                    <button class="button-pay absolute ico recharge-ico">付款</button>
                                    <div class="bg-img"></div>
                                </div>
                            </div>
                        </div>
                        </c:if>
                        </c:forEach>
                        <div id="tab3" class="tab">
                            <div class="card-mobile">
                                <span><a href="###">付款</a></span>
                                <img src="${config.imgDomainName}/${photo.imgId.folder }/${photo.imgId.name}_284x290.jpg" alt="风景图片">
                            </div>
                        </div>
                       <%--  <div id="tab3" class="tab">
                            <div class="card-mobile">
                                 <img src="${config.imgDomainName}/${photo.imgId.folder }/${photo.imgId.name}_284x290.jpg" alt="风景图片">
                             <button class="button-pay">付款</button>
                            </div>
                        </div> --%>
                    </div>
                </div>
            </div>
        </div>

		<!-- About Popup -->
		<div class="popup popup-about" style="background-color: #EFEFF4">
            <!-- 标题栏 -->
            <header class="bar bar-nav">
                <button class="button button-link button-nav pull-left">
                    <span class="icon icon-left"></span>
                </button>
                <div class="buttons-tab">
                    <a href="#greet-1" class="tab-link button active">全部</a>
                    <a href="#greet0" class="tab-link button">生日语句</a>
                    <a href="#greet1" class="tab-link button">祝贺语句</a>
                    <a href="#greet2" class="tab-link button">开心语句</a>
                </div>
            </header>
            <!-- 内容 -->
            <div class="content">
                <div class="content-block">
                    <div class="tabs">
                        <!-- 全部_标签页0 -->
                        <div id="greet-1" class="tab active">
	                        <c:forEach items="${proList}" var="pro" varStatus="status">
		                       	<c:forEach items="${pro.childrenList}" var="children" varStatus="child">
			                      	 <div class="card close-popup">
			                           <div class="card-content">
			                               <div class="card-content-inner">
			                                   <p>${children.pro_value}</p>
			                               </div>
		                          		</div>
		                     		 </div>
		                     	</c:forEach>
	                        </c:forEach>
                        </div>
                        <c:forEach items="${proList}" var="pro" varStatus="status">
                        <div id="greet${status.index}" class="tab">
	                       	<c:forEach items="${pro.childrenList}" var="children" varStatus="child">
		                      	 <div class="card close-popup">
		                           <div class="card-content">
		                               <div class="card-content-inner">
		                                   <p>${children.pro_value}</p>
		                               </div>
	                          		</div>
	                     		 </div>
	                        </c:forEach>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
		</div>
	</div>

    <script type='text/javascript' src='https://g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='https://g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='https://g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.qrcode.min.js"></script> 
    <script type="text/javascript">
    $(document).on('click','.open-about', function () {
	  $.popup('.popup-about');
	});
    $(document).on('click','.close-popup', function () {
  	  $("#greetings").val(this.outerText);
  	});
    
    
    /* $(document).on('click','.button-pay', function () {
    	alert($(".cardType active").get(0).html);
    	
    	//贺卡模板ID
    	//图片ID
    	//祝福人
    	//祝福语
   		$ybtCAjax({
   			url:"${ctx}/console/admin/business/getStockListCount",
   			type:'post',
   			data:searchParams,
   			dataType: "json",
   			success:function(data){
   				createPaging(data,searchMyStocks);
   			}
   		});
   	}); */
	</script>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$(document).on('click','.button-pay', function () {
       				var WXPayButton = '   <button class="button-pay" onclick="createQrcode(true)">微信付款</button>';
       				var aliPayButton = '  <button class="button-pay" onclick="createQrcode(false)">支付宝付款</button>';
       			if(!IsPC()){
       				//判断是不是微信内置浏览器
       				var ua = navigator.userAgent.toLowerCase();
        			 if (ua.indexOf('micromessenger') != -1) {
        		        //微信内置浏览器，支持支付宝支付&微信支付
        				 $(this).after(WXPayButton).after(aliPayButton);
 	        			$(this).hide();
 	        		}else{
 	        			//非微信内置浏览器，支持支付宝支付
 	        			$(this).after(aliPayButton);
	        			$(this).hide();
 	        		}
       			}
       			//电脑版支持微信扫码支付+支付宝即时到账  2016-04-26 add by zhz (不需要微信直接把else去掉即可)
       			else{
       				//微信扫码支付
       				var WXScanPayButton = '	<button class="button-pay" onclick="createQrcodeWX()">微信付款</button>';
       				$(this).after(WXScanPayButton);
       				$(this).text("支付宝付款");
       			}
/*        			//初始化打开支付界面
       			indexOpenPay(); */
		});
	});

	/* /**初始化打开支付界面
	*/
	function indexOpenPay(){
		//初始化时自动打开支付
		var payTypeCookie = getCookieValue("jump_pay_defult");
		deleteCookie("jump_pay_defult","/");
		if(!isNull(payTypeCookie)){
			setTimeout(function(){
				if(IsPC()){
					if(payTypeCookie=="WX"){
						//PC端打开二维码
   	       				createQrcodeWX();
   	       			}else if(payTypeCookie=="ZFB"){
   	       				//打开支付宝支付页面
   	       				createQrcode(true);
   	       			}
				}else{
					if(payTypeCookie=="WX"){
						//打开支付宝支付页面
	      				createQrcode(true);
   	       			}else if(payTypeCookie=="ZFB"){
   	       				//打开微信支付页面
   	       				createQrcode(false);
   	       			}
				}
			},500);
		}
	} 

	var WXPayUrl = "${WXPayUrl}";
	var aliPayUrl = "${aliPayUrl}";
	//提交订单
	var metering = 0;//计次，用于生产订单号
	var orderId="${order.id}";
	function createQrcode(p){
		//判断是不是PC端
		if(IsPC()){
			metering++;
			window.open("${ctx}/work/pay/payAlipayPCOrder?orderId="+orderId+"&metering="+metering);
		}else{
			if(p){
				window.location.href=WXPayUrl;
			}else{
				window.location.href=aliPayUrl;
			}
		}
		
		
	}
	//倒计时
	var timer = null;
	function resetCode(){
		if(IsPC()){
			var second = 120;
			$('#J_getCode').hide();
			$('#J_second').html(second);
			$('#J_resetCode').show();
			timer = setInterval(function(){
				second -= 1;
				if(second >0 ){
					$('#J_second').html(second);
				}else{
					clearInterval(timer);
					$('#J_resetCode').hide();
					$('#J_getCode').show();
				}
			},1000);
		}
	}
	//--微信扫码支付
	var createQrcodeWX = function(){
		$('#code').html("");
		$ybtCAjax({
			url:"${ctx}/work/chxt/account/getPayUrl",
			type:'post',
			dataType: "json",
			data:"orderId="+orderId,
			success:function(data){
				if(!isEmpty(data)&&data.s==1){
					$('#code').qrcode(data.b); //任意字符串 
					$("#qrcode").click(); 
					resetCode();
				}else{
					alert("支付二维码加载失败！");
				}
			},error:function(){
				window.location.href="${ctx}/work/login";
			}
		});
	};
	//支付宝扫码支付
	var createQrcodeAliQr = function(){
		$('#code').html("");
		$ybtCAjax({
			url:"${ctx}/work/chxt/account/payAlipayQrOrder",
			type:'post',
			dataType: "json",
			data:"orderId="+orderId,
			success:function(data){
				if(!isEmpty(data)&&data.s==1){
					$('#code').qrcode(data.b); //二维码
					$("#qrcode").click(); 
					resetCode();
				}else{
					alert("支付二维码加载失败！");
				}
			},error:function(){
				window.location.href="${ctx}/work/login";
			}
		});
	};
	//支付宝手机网页支付
	var createQrcodeAli = function(){
		$('#code').html("");
		$ybtCAjax({
			url:"${ctx}/work/chxt/account/getAliPayUrl",
			type:'post',
			dataType: "json",
			data:"orderId="+orderId,
			success:function(data){
				if(!isEmpty(data)&&data.s==1){
					$('#code').qrcode(data.b); //二维码
					$("#qrcode").click(); 
					resetCode();
				}else{
					alert("支付二维码加载失败！");
				}
			},error:function(){
				window.location.href="${ctx}/work/login";
			}
		});
	};
	
	//支付宝PC支付
	var createQrcodeAliPC = function(){
		$('#code').html("");
		$ybtCAjax({
			url:"${ctx}/work/chxt/account/payAlipayPCOrder",
			type:'post',
			dataType: "json",
			data:"orderId="+orderId,
			success:function(data){
				if(!isEmpty(data)&&data.s==1){
					$('#code').qrcode(data.b); //二维码
					$("#qrcode").click(); 
					resetCode();
				}else{
					alert("支付二维码加载失败！");
				}
			},error:function(){
				window.location.href="${ctx}/work/login";
			}
		});
	};
	$(document).on("click","#subbtn",function(e){
		$('#qrcodeModal').modal('hide');
		history.go(0);
	});
	$('#qrcodeModal').on('hidden.bs.modal', function (e) {
		clearInterval(timer);
	});
	</script>
  </body>
</html>