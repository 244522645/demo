<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/common/static.jsp"%>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>免费领取价值99元的阳光卡</title>
    	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="免费领取价值99元的阳光卡">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
<script type="text/javascript">
var countt=10;
var countdown=countt; 
function settime(obj) { 
	if (countdown == 0) { 
	obj.removeAttribute("disabled");
	obj.innerHTML="重新获取";
	countdown = countt; 
	return;
	} else { 
	obj.setAttribute("disabled", true); 
	obj.innerHTML="剩余("+ countdown +")"; 
	countdown--;
	}
	setTimeout(function() { 
	settime(obj) }
	,1000)
}

</script>
  </head>
  <body>
	<div class="page-group">
			<div class="page page-current total-bg" style="overflow: scroll;">
				<!-- 你的html代码 -->

				<div class="tanchukuang" style="display: block;">
					<img src="${ctx }/static/images/sun/free-card-success.png" class="img-success" />
					<div class="anniuzu">
						<ul class="ul-anniu">
							<li class="li-anniu">
								<a class="a-szf external" href="${ctx}/wechat/index">
									送祝福
								</a>
							</li>
							<li class="li-anniu">
								<a class="a-chakan external" href="${ctx}/wechat/my/myList">
									查看阳光卡
								</a>
							</li>
						</ul>
					</div>
					<div class="shuoming" style="margin-left: 5%;width: 90%;margin-top: 0rem;margin-bottom: 1rem;">
						<ol>
							<li>
								首次领取阳光卡，需要先绑定手机号。
							</li>
							<li>
								每天都可领取一张阳光卡， 阳光卡可用于给朋友送出祝福。
							</li>
							<li>
								领取阳光卡后，可以到“我的”里面查看领取到的阳光卡。
							</li>
							<li>
								阳光卡仅限当天使用，过期作废。
							</li>
						</ol>

					</div>
				</div>

			</div>
</div>
</body>
<script type="text/javascript">
$(function(){
     $(document).on('click','.alert-text',function () {
        $.alert('请重新输入', '验证码错误!');
     });
     $(document).on('click','.a-get-sun-card',function () {
    		var phone=$("#phone").val();
    	 	var code=$("#code").val();
    	 	phoneBandding(phone,code);
      });
     
     com_shareAppMessage_title='免费领取价值99元的阳光卡',
    // com_shareAppMessage_content='免费领取价值99元的阳光卡',
     com_shareAppMessage_imgUrl='${config.domainName}/static/images/sun/logo-icon.png';
  
  })
  //发送验证码
     function sendShortMessage(phoneName,type){
	 	var tel = /^(13|15|17|18)\d{9}$/; 
	 	var phone=$("#phone").val();
	 	if(!tel.test(phone)){
	 		$.alert('请重新输入', '手机号码格式错误!');
	 		return;
	 	}
	 	var dd = document.getElementById("a-get-yzm");
	 	settime(dd);
	 	  $.ajax({
 			url:"${ctx}/wechat/sms/sendsms",
 			type:'POST',
 			data: 'phone='+phone+"&type="+type,
 			dataType: "json",
 			success:function(result){	
 				if(result.state==1){
 					alert("发送成功");
 					$(".a-get-sun-card").show();
 				}else{
 					alert("发送异常");   
 				}
 			}
 		});  
     };
   	//绑定手机
     function phoneBandding(phone,code){
	 	var tel = /^(13|15|17|18)\d{9}$/; 
	 	if(!tel.test(phone)){
	 		$.alert('请重新输入', '手机号码格式错误!');
	 		return;
	 	}
	 	if(isNull(code)){
	 		$.alert('请输入短息验证码!');
	 		return;
	 	}
	 	  $.ajax({
 			url:"${ctx}/wechat/activity/phoneBandding",
 			type:'POST',
 			data: 'phone='+phone+"&code="+code,
 			dataType: "json",
 			success:function(result){	
 				alert(result.message);
 				if(result.state==1){
 					window.location.href="${ctx}/wechat/activity/freeSunCard";
 				}else{
 					$.alert("绑定异常");   
 				}
 			}
 		});  
     };
</script>
 <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<%@include file="/WEB-INF/views/work/common/pageInit.jsp" %>
</html>
