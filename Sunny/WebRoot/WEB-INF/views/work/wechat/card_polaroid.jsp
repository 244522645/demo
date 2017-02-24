<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
 <script type="text/javascript" src="${ctx}/static/js/ping_pp/pingpp.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${config.siteName}</title>
    <meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
   <!--  border:1px solid #4CD964; border-radius: .5rem; box-shadow: 0 0 1px rgba(76,217,100,.3) -->
    <style>
	.inputname{
		width:40%; max-width:150px;height:1.5rem;color: #D01A28;border:0;
	}
	.area{
	        font-size: 12px !important;
	        line-height:2.5 !important;
	        background-image:linear-gradient(#E8E8E8 1px,transparent 0) !important;
	        background-size:30px 30px !important; 
	        padding-top:0  !important;
	        max-height:200px;
	        color: #000 !important;
      }
      .template-top img{
      	width: 100%;
      	height: auto;
      }
   .card{
	    margin: 0;
	    margin-bottom: .1rem;
	} 
	.card .card-content > .card-content-inner > p {
    color: #5f646e;
	}
	
	.login-screen, .popup-card{
	background: transparent;
	}
	
/*选择阳光卡支付后，还未绑定阳光卡*/

.select-sun-card-zhifu {
	background-color: #ffffff;
	margin: 0px auto;
	width: 80%;
	padding-bottom: 5%;
	margin-left: 8%;
	position: fixed;
	top: 30%;
	z-index: 2002;
	visibility: visible;
	border: solid 1px #FFFFFF;
	border-radius: 0.3rem;
	opacity: 1;
	-webkit-transition-duration: .4s;
	transition-duration: .4s;
	-webkit-transform: translate3d(0, 0, 0) scale(1);
	transform: translate3d(0, 0, 0) scale(1);
	transition-property: transform, opacity;
}

.title-bangding {
	text-align: center;
	font-family: "微软雅黑";
	margin-bottom: 0rem;
}

.input-card-info {
	text-align: center;
	padding-top: 0.3rem;
	padding-bottom: 0.3rem;
	width: 80%;
	margin-top: 5%;
	margin-left: 10%;
	color: #000000;
	font-size: 0.7rem;
	border-style: none;
	border-bottom-style: solid;
	border-bottom-width: 1px;
}

.tishi {
	margin-left: 5%;
	color: #FF0000;
	font-size: 0.55rem;
}

.input-confirm {
	padding-top: 0.3rem;
	padding-bottom: 0.3rem;
	width: 80%;
	margin-top: 1%;
	margin-left: 10%;
	color: white;
	font-size: 0.73rem;
	background-color: #ff0000;
	border: 1px solid;
	border-radius: 0.4rem;
	text-align: center;
}


/*选择已经绑定的阳光卡*/

.select-sun-card-zhifu-2 {
	background-color: #ffffff;
	margin: 0px auto;
	width: 70%;
	padding-bottom: 5%;
	margin-left: 15%;
	position: fixed;
	top: 30%;
	z-index: 2002;
	visibility: visible;
	border: solid 1px #FFFFFF;
	border-radius: 0.3rem;
	opacity: 1;
	-webkit-transition-duration: .4s;
	transition-duration: .4s;
	-webkit-transform: translate3d(0, 0, 0) scale(1);
	transform: translate3d(0, 0, 0) scale(1);
	transition-property: transform, opacity;
}

#card-icon {
	width: 1rem;
	margin-top: 5%;
	margin-left: 5%;
}

#fault{
	width: 1rem;
	position: relative ;
	float: right;
	margin-top: 3%;
	margin-right: 3%;
}

#fengexian {
	width: 90%;
	margin-left: 5%;
}

#ygk{

text-align: left;
	width: 80%;
padding-top: 1rem;
padding-bottom: 1rem;
padding-left: 0.5rem;
margin-left: 10%;
background-color: orange;
background: url(${ctx}/static/images/sun/card-background.png);
background-position: center;
background-size: 100%;
background-repeat: no-repeat;
margin-bottom: 0.5rem;
}
#ygk-name{
font-size: 0.7rem;

	
}
#ygk-number{
	/*margin-left: 15%;*/
font-size: 0.7rem;
	
	
}
</style>
  </head>
 
  <body>
    <div class="page-group">
        <div class="page" style="background-color:#fff;">

            <!--  <header class="bar bar-nav">
              <a href="javascript:void(0);" class="icon icon-left pull-left"></a>
              <a href="javascript:void(0);" class="icon icon-gift pull-right button-success"></a>
              <h1 class="title">制作福卡</h1>
            </header> -->
            <!-- 内容 -->
            <div class="content content-clear-top">
                	
				<form class="form-horizontal form-horizontal-border"  style="display:none"  id="orderInfo">
				<input type="hidden" name="photoId" value="${photo.id}"/>
				<input type="hidden" name="buyerName" id="savefromName" value="${user.wechatNickname}"/>
				<input type="hidden" name="sendeeName"  id="savetoName" />
				<input type="hidden" name="message"  id="savecreetings" />
				<input type="hidden" name="cardImgId"/>
				</form>
                <!-- 拍立得卡片效果 -->
                <div class="card polaroid-template">
                  <div class="template-top">
                    <div class="text">
                      <h3 style="font-size: 0.8rem"><span id="fromName">${user.wechatNickname}</span>&nbsp;&nbsp;祝&nbsp;&nbsp;<span id="toName">某某</span></h3>
                      <textarea id="creetings" disabled="disabled" style="overflow: hidden;opacity:1;height:70%">请填写祝福语</textarea>
                    </div>
                    <img src="${config.imgDomainName}/${photo.imgId.folder }/${photo.imgId.name}_348x232.jpg" alt="风景图片">
                  </div>
                  <div class="card-content">
                    <h3>
                    <input placeholder="某某" value="${user.wechatNickname}" class="inputname"
                     id="fromNameInput" />
                     <span style="width:10px;margin-right: 5px;margin-left: 5px">祝</span>
                    <input placeholder="某某" class="inputname" id="toNameInputMM" /></h3>
                    <div class="list-block">
                        <ul class="clear-ba">
                            <li>
                              <div class="item-inner" style="padding-right: 0px;">
                                  <div class="item-input" >
                                      <textarea id="creetingsshow" class="area" name="myText" cols="50" rows="10" 
                                       style="overflow-y:hidden;height:5rem"  onpropertychange="this.style.height=this.scrollHeight + 'px'" oninput="this.style.height=this.scrollHeight + 'px'"
                                       placeholder="请填写祝福语"></textarea>  
                                   	  <p class="open-about" style="font-size: 0.6rem;float: left;color: #808080;height: 20px;width:4rem;">
                                   	  选择祝福语<span class="icon icon-down"></span></p>
                                  </div>
                              </div>
                            </li>
                        </ul>
                    </div>
                    <div>
                    	<span class="color-gray footer-label">${photo.title}</span>
                    	<div style="width:5rem; float: right;"><a href="javascript:void(0);"  class="button button-big button-fill button-success create-actions">送祝福</a></div>
                    </div>
                  <%--   <div class="color-gray footer-label">${photo.title}</div> --%>
                    <!--  <div style="width:5rem; float: right;"><a href="javascript:void(0);"  class="button button-big button-fill button-success">1元送祝福</a></div> -->
                  </div>
                </div>
            </div>
		</div>
		<!--遮罩层，点击“送祝福”后显示-->
		<!-- <div class="zhezhao" id="zhezhao" style="display:block">
		</div> -->

	
	</div>
	<script type="text/javascript">
	function getLines(txtArea)
	{
	  var lineHeight = parseInt(txtArea.style.lineHeight.replace(/px/i,'')); 
	  var tr = txtArea.createTextRange();
	  return Math.ceil(tr.boundingHeight/lineHeight);
	}
	function checkLimits(txtArea)
	{
		var txtArea = document.getElementById("creetingsshow");
	  var maxLines = txtArea.rows;
	  var maxChars = txtArea.rows * txtArea.cols;
	  if((txtArea.value.length >= maxChars || getLines(txtArea) >= maxLines)
	    && (window.event.keyCode == 10 || window.event.keyCode == 13))
	  {
	    while(getLines(txtArea) > maxLines)
	      txtArea.value = txtArea.value.substr(0,txtArea.value.length-2);
	    while(txtArea.value.length > maxChars)
	      txtArea.value = txtArea.value.substr(0,txtArea.value.length-2);
	    alert("limits reached");
	  }
	  else if(txtArea.value.length > maxChars )
	  {
	    while(txtArea.value.length > maxChars)
	      txtArea.value =txtArea.value.substr(0,txtArea.value.length-1);
	    alert("chars limit reached");
	  }
	  else if (getLines(txtArea) > maxLines)
	  {
	    while(getLines(txtArea) > maxLines)
	      txtArea.value = txtArea.value.substr(0,txtArea.value.length-1);
	    alert("lines limit reached");
	  }
	}
	</script>
            <!-- About Popup -->
            <div class="popup popup-about popup-zhufu" style="background-color: #EFEFF4">
            <!-- 标题栏 -->
            <header class="bar bar-nav">
                 <h1 class="title">选择祝福语</h1>
                <button class="button button-link button-nav pull-right close-popup" style="padding: 0 10px;">
                    <span class="icon icon-edit"></span>
                </button>
            </header>
            <!-- 内容 -->
            <div class="content">
                <div class="content-block">
                <div class="buttons-tab">
                    <a href="#greet-1" class="tab-link button active">全部</a>
                    <a href="#greet0" class="tab-link button">生日</a>
                    <a href="#greet1" class="tab-link button">祝福</a>
                    <a href="#greet2" class="tab-link button">励志</a>
                    <a href="#greet3" class="tab-link button">爱情</a>
                    <a href="#greet4" class="tab-link button">宝宝</a>
                </div>
                
                    <div class="tabs">
                        <!-- 全部_标签页0 -->
                        <div id="greet-1" class="tab active">
	                        <c:forEach items="${proList}" var="pro" varStatus="status">
		                       	<c:forEach items="${pro.childrenList}" var="children" varStatus="child">
			                      	 <div class="card close-popup-item">
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
		                      	 <div class="card close-popup-item">
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
			<script type="text/javascript">
		//选择祝福语
	    $(document).on('click','.open-about', function () {
	  	  $.popup('.popup-zhufu');
	  	});
		//回显祝福语
	     $(document).on('click','.close-popup-item', function () {
	    	 $.closeModal('.popup-zhufu');
	    	 var message = this.outerText;
	    	 if(!isNull(message)){
	    	   	  $("#creetingsshow").val(message);
	    	   	  $("#savecreetings").val(message);
	    	   	  $("#creetings").get(0).innerHTML=message;
	    	   	var txtArea = document.getElementById("creetingsshow");
	    	   	txtArea.style.height=(txtArea.scrollHeight) + 'px';
	    	 }
	   	});
	    </script>
	   <!-- Services Popup -->
		<div class="popup popup-services popup-guanzhu" style="text-align: center;">
		  <div class="content-block">
		    <p>送给朋友一份有意义的祝福?</p>
		    <p>请先关注"给点儿阳光"公众号</p>
		    <p>
		       <img class="img-responsive" src="${ctx}/static/images/sun/qrcode_for_gh.jpg" alt="二维码">
               <small>↑↑长按识别关注</small>
		    </p>
		    <div class="qr-code">
              
             </div>
		  </div>
		</div>
		<!-- About Popup -->
	   <!-- Services Popup -->
		<div class="popup popup-services popup-card popup-cardinput" >
		  <div class="content-block">
		  	<!--输入阳光卡卡号、密码，点击“阳光卡支付”后显示-->
			<div class="select-sun-card-zhifu" id="select-sun-card-zhifu-bangding">
				<img id="fault" class="popup-card-close" src="${ctx}/static/images/sun/fault.png" />
				<p class="title-bangding">绑定阳光卡</p>
				<input type="number" class="input-card-info" id="card-number" placeholder="输入阳光卡卡号 " />
				<input type="password" class="input-card-info" id="card-password" placeholder="输入阳光卡密码 " />
				<p class="tishi"></p>
				<input type="button" class="input-confirm" id="input-confirm2" value="确定 " />
			</div>
	
		  </div>
		</div>
			   <!-- Services Popup -->
		<div class="popup popup-services popup-card popup-cardselect">
		  <div class="content-block">
		  
				<!--选择既有的阳光卡，点击“阳光卡支付”后显示-->
		
				<div class="select-sun-card-zhifu" id="select-sun-card-zhifu" >
		
					<img id="card-icon" class="popup-card-close" src="${ctx}/static/images/sun/card.png " />
					<img id="fault" class="popup-card-close" src="${ctx}/static/images/sun/fault.png" />
					
					<hr id="fengexian"/>
					<div id="ygk">
						<span id="ygk-name">阳光卡</span>
						<span id="ygk-number"></span>
					</div>
					
					<input type="button" class="input-confirm " id="input-confirm1" value="确定 " />
				</div>
		  </div>
		</div>
 </body>
 <style>
 .p_one_icon_wechat:before {
    color: #119911;
}
 </style>
<!-- 壹收款
 <script type="text/javascript" src="https://one.pingxx.com/lib/pingpp_one.js"></script>
 -->
<script src="${ctx}/static/js/ping_pp/ap.js"></script>
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<c:if test="${not empty login}">
<script type="text/javascript">
$.popup('.popup-guanzhu');
</script>
</c:if>
	<!-- 微信js 分享注册  -->
<script type="text/javascript">
                   com_share_title="制作福卡",
                   com_share_content="${photo.title }-${story}",
                   com_share_link=location.href.split('#')[0],
                   com_share_imgUrl="${config.imgDomainName}/${photo.imgId.folder}/${photo.imgId.name}_100x100.jpg";

                   com_shareAppMessage_title=com_share_title,
                   com_shareAppMessage_content=com_share_content,
                   com_shareAppMessage_link=com_share_link,
                   com_shareAppMessage_imgUrl=com_shareAppMessage_imgUrl;
                   //spa 注册
                   if (wxjs) {
                   	 share();
               	} 
                   //ajax 注册
               	fshare = function(){ 
               		share();
               	} 
      </script>
<script type="text/javascript">
var metering = 0;//计次，用于生产订单号
   document.body.addEventListener("touchstart",function(){});
   $(function(){
    $('#fromNameInput').bind('input', function(){
    	$("#fromName").get(0).innerHTML=$(this).val();
    	$("#savefromName").val($(this).val());
      });
      
      $('#toNameInputMM').bind('input', function(){
    	  $("#toName").get(0).innerHTML=$(this).val();
	    	$("#savetoName").val($(this).val());
      });
      
      $('#creetingsshow').bind('input', function(){
    	  $("#creetings").get(0).innerHTML=$(this).val();
	    	$("#savecreetings").val($(this).val());
      });
      
   });
   //编辑祝福信息
   function eidtGreet(obj,id){
   	$("#"+id).get(0).innerHTML=obj.value;
   	$("#save"+id).val(obj.value);
   }
   //提交订单
   function submitOrder(payType,number){
	  //$.showIndicator() 
       $.showPreloader('加载中')
       var ye=1.00;
    			
       var formdata=new FormData($w("orderInfo"));  
    $.ajax({  
        type:'POST',  
        url:"${ctx}/wechat/order/saveOrder?sunPayType="+payType,  
        data:formdata,  
        contentType:false,  
        processData:false,
        success:function(result){
        	 $.hidePreloader();
        	 
        	if(!isNull(result)&&result.s==1){
        		var order=result.b;
          			var subject =order.photo.title+'-'+order.photo.subject+"-贺卡";
          			var metering = 0;
          			var body=order.photo.title+'-'+order.photo.subject+"-贺卡";
          			var charge = result.m;
/* 
          			if('oGrGWs91_m2P9TZXcC72hojTmhd8' == '${userInfo.id}' ||'oWL5RuJoTvOo2ZyHwLfafhE3B3-M' == '${userInfo.id}'){
          			window.location.href="${ctx}/wechat/order/orderSucess?orderId="+order.id;

          			} */
          			
          			if(order.payment==1){
          				pingpp.createPayment(charge, function(result, err) {
	           			  if (result=="success") {
	      	                	window.location.href="${ctx}/wechat/order/orderSucess?orderId="+order.id;
	           			  } else {
	           			    console.log(result+" "+err.msg+" "+err.extra);
	           			  }
	           			});
          			}
          			if(order.payment==2){
          				$.ajax({  
          			        type:'POST',  
          			        url:"${ctx}/wechat/pay/paySunCardNotice?orderId="+order.id+"&cardNumber="+number,  
          			        success:function(result){
          			        	 
          			        	if(!isNull(result)&&result.state==1){
	      	                	window.location.href="${ctx}/wechat/order/orderSucess?orderId="+order.id;
          						}else{
          						 	console.log(result);
          						}
          			        }
          			    }); 
          			}
          			
        		 metering++;//计次，用于生产订单号
				//跳转到确认页面
			}else{
				$.alert(result.m); 
			}
        }
    }); 
   }
    $(document).on('click','.button-success', function () {
   	 
   	 var show = $("#creetingsshow").val();
   	 var fromNameInput = $("#fromNameInput").val();
   	 var toNameInputMM = $("#toNameInputMM").val();
   	 if('' == show){
   		 $.toast("请填写祝福语");
   		 return;
   	 }
   	 if('' == fromNameInput || '' == toNameInputMM ){
   		 $.toast("请填写姓名");
   		 return;
   	 }
   	 $.actions(groups);
   	 
   	 /* $.confirm('请确认生成贺卡?', function () {
      }); */ 
  	}); 
    
    var buttons1 = [
   	           {
   	             text: '请选择',
   	             label: true
   	           },
    	           {
    	             text: '微信支付',
    	             bold: true,
    	             color: 'danger',
    	             onClick: function() {
    	            	 submitOrder('weixin','');
    	            	// 
    	             }
    	           },
    	           {
    	             text: '阳光卡支付',
    	             onClick: function() {
    	            	// $.popup('.popup-cardselect');
    	            	 getOneSunCard();
    	               
    	             }
    	           }
   	         ];
   	         var buttons2 = [
   	           {
   	             text: '取消',
   	             bg: 'danger'
   	           }
   	         ];
   	         var groups = [buttons1, buttons2];
  /*   $(document).on('click','.create-actions', function () {
       
    }); */
   
    //关闭 card 窗口
    $(document).on('click','.popup-card-close', function () {
    	 $.closeModal(".popup-card");
    	 $(".popup-overlay").removeClass('modal-overlay-visible');
    	 $.actions(groups);
   	
    });
    
    $(document).on('click','#input-confirm1', function () {
   	 selectCard();
    });
    $(document).on('click','#input-confirm2', function () {
   	 banddding();
    });
    function banddding(){
   	 var number=$("#card-number").val()
   	var password = $("#card-password").val()
   	if(isNull(number)||isNull(password)){
   		$(".tishi").html("※ &nbsp;"+"卡号、密码不能为空");
   		return 
   	}
   		
   	  $.ajax({  
   	      type:'POST',  
   	      url:ctx+"/wechat/my/card/sunCardBandding",  
   	      data:{number:number,pwd:password},  
   	      success:function(result){
   	    	  if(result.state==1){
   	    		  $.closeModal(".popup-card");
   	    		  $("#ygk-number").html(result.t.number);
   	    		  $.toast("绑定成功！");
   	    		  setTimeout(function() {  
   	    			  $.popup('.popup-cardselect');
   	    			}, 1000);
   	    		 
   	    	  }else{
   	    		  $(".tishi").html("※ &nbsp;"+result.message);
   	    		 // $.popup('.popup-cardinput');
   	    	  }
   	      }	
   	 }); 
    }
    function selectCard(){
   	 var number=$("#ygk-number").html()
   	if(isNull(number)){
   		 $.toast("请选择卡！");
   		return 
   	}
   	 
   	 submitOrder('SUNCARD',number);
   	 
    }
  
   </script>
</html>

