<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/common/static.jsp"%>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>预订成功</title>
    	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	 <script type="text/javascript">
	 var com_share_title="我为你准备了一份礼物",
	 com_share_content="在生日当天打开它会有惊喜！",
	 com_share_link="${shareAppMessageUrl}"
	 domainName="${config.domainName}",
	 com_share_imgUrl="${bless.userId.wechatHeadUrl}";
	 var orderId="${order.id}";
	 wxShare.isOnlyApp=true;
	 OrderShareSuccess.orderId='${bless.order.id}';
	 wxShare.onlyApp={
					title:"我为你准备了一份礼物",
					imgUrl:"${bless.userId.wechatHeadUrl}",
					link:'${shareAppMessageUrl}',
					content:'在生日当天打开它会有惊喜！',
					success:OrderShareSuccess.success,	
					cancel:OrderShareSuccess.cancel,
	 }
	 </script> 
</head>
<body  class="v2">
	 <div class="page-group">
        <div class="page birthdaymain yuding-share v2">
            <div class="content">
				<div class="daojishi">
					<span class="songming">距离${bless.relation.name }生日还有</span><br />
					<span class="tianshu">
						<div class="heidi">
						</div>
					</span>
				</div>
				<div class="fenxiangtou">
					<img src="${bless.userId.wechatHeadUrl}">
				</div>
				<div class="liwu">
					<span>${bless.userId.wechatNickname}为您准备了一份神秘礼物，</span><br />
					<span id="liwu_shuoming">请生日当天来这里领取！</span><br />
					<img src="${ctx}/static/v2.0/img/dalibao.png">
				</div>	
				<div class="shenmi">神秘礼物，正在赶制中，敬请期待！</div>
				<div class="saoma">
					<div class="saoma1">
						<span>扫描关注二维码，<br />
						偷窥一下“神秘礼物”</span>
						<img src="${ctx}/static/v2.0/img/erweima.png">
					</div>
				</div>
				<div class="zhuanfa">
					发给朋友
				</div>
        	</div> 
   		</div>
    <!-- popup, panel 等放在这里 -->
    	<div class="popup popup-chenggong">
		<div class="content-block heikuai">
		  	<img src="${ctx}/static/v2.0/img/xiantiao.png" />
		</div>
		<div class="diandian">发</div>
		<div class="jiji">给</div>
		<div class="fenfen">朋</div>
		<div class="xiangxiang">友</div>
	</div>
</body>
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<script type="text/javascript">
$(function(){
	$.init();
});

</script>
 <script>
 /*
  *预订成功页面 js (倒计时、分享等操作)
  */
 ReserveSuccess={
    	that:this,
    		init : function(){
    			that=this;
    			var t = ${days};
				var t = that.shuzibuling(t,3);	
    			var arr = (t+'').split('');
    			var shijian='<div class="shubai">'+arr[0]+'</div>'+
							'<div class="shushi">'+arr[1]+'</div>'+
							'<div class="shuge">'+arr[2]+'</div>';
							$(".heidi").append(shijian);
				$(".zhuanfa").on("click",function(){
			  		$.popup('.popup-chenggong');
			  		that.shareApp();
				})
    		},
    	shuzibuling:function(num,n){
    		return (Array(n).join(0) + num).slice(-n);
    	},
    	shareApp :function (){
   	   	 $("#shareit").show();
   	     wx.ready(function(){
   	    		//朋友分享 参数
   	    	      com_shareAppMessage_title='${order.title}',
   	    	      com_shareAppMessage_content='${message}',
   	    	      com_shareAppMessage_link='${shareAppMessageUrl}',
   	    	      com_shareAppMessage_imgUrl='${bless.userId.wechatHeadUrl}';

   	    	      wxShare.init({
   	    				title:this.title,
   	    				imgUrl:this.imgUrl,
   	    				link:this.link,
   	    				content:this.content
   	    			});
   	    		  	var json = {
   	    		  			"title":com_shareAppMessage_title,
   	    		  			"content":com_shareAppMessage_content,
   	    		  			"link":com_shareAppMessage_link,
   	    		  			"imgUrl":com_shareAppMessage_imgUrl,
   	    		  			};
   	    			wxShare.shareAppFunction(json,OrderShareSuccess.success,OrderShareSuccess.cancel);
   	    	 	});
         }
    }
 ReserveSuccess.init();
    
    </script>
<script type="text/javascript">

/* 以下为过年临时活动代码【开始】*/
 	var time_jsp = '${bless.relation.birthday}';
 	//alert('time_jsp1=='+time_jsp);
 	time_jsp = time_jsp.substring(0,10);
 	//alert('time_jspSub=='+time_jsp);
 	var birthday_houtai = new Date(time_jsp).Format('yyyy-MM-dd');
 	//alert('birthday_houtai=='+birthday_houtai);
	var date = '2017-01-28';  //活动时间
	if(birthday_houtai == date){
		 com_share_content="在大年初一打开它会有惊喜！";
		 wxShare.onlyApp.content='在大年初一打开它会有惊喜！';
		 $(".songming").html("距离大年初一还有");
		 $("#liwu_shuoming").html("请大年初一来领取");
	}
	/* 以下为过年临时活动代码【结束】*/




//页面跳转
$(document).on("pageInit", function(e, pageId, $page) {
});
</script>
</html>