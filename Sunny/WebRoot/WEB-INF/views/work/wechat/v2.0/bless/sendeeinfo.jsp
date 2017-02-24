<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/common/static.jsp"%>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>日出预订</title>
    	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="${ctx}/static/v2.0/css/index.css?t=new Date()"/>
	 <script type="text/javascript">
	 var com_share_title="我为你准备了一份礼物",
	 com_share_content="在生日当天打开它会有惊喜！",
	 com_share_link="${shareAppMessageUrl}"
	 domainName="${config.domainName}",
	 com_share_imgUrl="${bless.userId.wechatHeadUrl}";

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
	    
	    <div class="page birthdaymain " id="selectPhoto">
			<nav class="bar bar-tab">
			  <a class="tab-item external fenxianggeipengy" href="#">
			    <span class="tab-label">确定</span>
			  </a>
			</nav>
            <div class="content birthdaymain">
            	<div class="tishi">
            		*您可从下面的图片中选取一张，下载高清照片。
            	</div>
	      		<div class="photoList">
    	 	     </div>
	    	 	   
        	</div> 
   		</div>
   		
        <div class="page birthdaymain yuding-share  page-current"  id="showcard">
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
				<c:if test="${days > 1}">
					<div class="liwu">
						<span>${bless.userId.wechatNickname}为您准备了一份神秘礼物，<br />
						<span id="liwu_shuoming">请生日当天来这里领取！</span><br />
						<img src="${ctx}/static/v2.0/img/dalibao.png">
					</div>	
				</c:if>
				<c:if test="${days eq 1}">
				<div class="liwu">
					<span class="jiexiao">神秘礼物马上就要揭晓了<br />是不是很激动呀</span>
					<div class="geitishi">
						<img src="${ctx}/static/v2.0/img/xiaoren.png">
						<span class="tishi1">看在你绞尽脑汁的份上，<br />给你点提示吧！<br />这个礼物与五个地名有关系</span>
					</div>
					<div class="row no-gutter diliweiz">
						 <c:forEach items="${citys}" var="entity" varStatus="status">
			                  <span class="diliweiz-citys">${entity}</span> 
						</c:forEach>
					</div>
				 </div>
				 <div class="shenmi">神秘礼物，正在赶制中，敬请期待！</div>
				</c:if>
				<c:if test="${days <= 0}">
				<div class="liucheng">
					<div class="shengrkl">
						Happy Birthday！生日快乐！
					</div>
					<div class="liuc">
						<img src="${ctx}/static/v2.0/img/liuc1.png" />
						<img src="${ctx}/static/v2.0/img/liuc2.png" />
						<img src="${ctx}/static/v2.0/img/liuc3.png" />
						<img src="${ctx}/static/v2.0/img/liuc4.png" />
					</div>
					<div class="liuchengzi">
						 <c:forEach items="${timelist}" var="entity" varStatus="status">
			                    ${entity.time }---${entity.message }<br />
			                    <c:if test="${isPhoto eq 'xiaoyu_10'}">
									<span style="color: red">请于10:00点以后来领取礼物</span>
								</c:if>
						</c:forEach>
					</div>
					<c:if test="${empty isPhoto  }">
						<div class="daodal">生日阳光马上送达！请耐心等待！</div>
					</c:if>
					<c:if test="${isPhoto eq 'ok' and showGetGiftBtn eq 'ok'}">
						<div class="lingqvliwu" id="get-photo-btn">领取生日阳光</div>
					</c:if>
					
					
				</div>	
				</c:if>
					
				<div class="saoma">
					<div class="saoma1">
						<span>扫描关注二维码，<br />
						偷窥一下“神秘礼物”</span>
						<c:if test="${ empty bless.sunQrcode}">
						<img src="${ctx}/static/v2.0/img/erweima.png">
						</c:if>
						<c:if test="${not empty bless.sunQrcode}">
						<img src="data:image/gif;base64,${bless.sunQrcode.img}">
						</c:if>
					</div>
				</div>
				<c:if test="${not empty showSendBtn}">
				<div class="zhuanfa">
					发给朋友
				</div>
				</c:if>
				
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

/* 以下为过年临时活动代码【开始】*/
var time_jsp = '${bless.relation.birthday}';
time_jsp = time_jsp.substring(0,10);
var birthday_houtai = new Date(time_jsp).Format('yyyy-MM-dd');
var date = '2017-01-28';  //活动时间
if(birthday_houtai == date){
	 com_share_content="在大年初一打开它会有惊喜！";
	 wxShare.onlyApp.content='在大年初一打开它会有惊喜！';
	 $(".songming").html("距离大年初一还有");
	 $("#liwu_shuoming").html("请大年初一来领取");
	 $("#shengrkl").html("Happy NewYear！新年快乐！");
	 $("#daodal").html("阳光马上送达！请耐心等待！");
	 $("#lingqvliwu").html("领取新年阳光");
}
/* 以下为过年临时活动代码【结束】*/
</script>
 <script>
    /*
    *预订 祝福卡 js对象
    */
    var Bless = {
    		blessId:'${bless.id}',
    		message:'',
    		citys:'${bless.citys}',
    		date:'${blessBirth}',
    		form:'',
    		formHead:'',
    		to:'',
    		toHead:'',
    		photoUrl:'',
    		photoTitle:'',
    };
    /*
     *预订 制作页面 js (倒计时、分享等操作)
     */
    var Sendeeinfo={
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
				$(".zhuanfa").unbind( "click" );
				$(".zhuanfa").on("click",function(){
			  		$.popup('.popup-chenggong');
			  		that.shareApp();
				});
				$("#get-photo-btn").unbind( "click" );
				$("#get-photo-btn").on("click",function(){
					$.router.load("#selectPhoto");
				});
				$("#get-photo-btn-no").unbind( "click" ).on("click",function(){
					$.toast("阳光马上送达！请耐心等待！");
				});
				
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
    
    /*
     *选择 照片
     */
   var SelectPhotos={
       	that:this,
       	citys:Bless.citys,
       	date:Bless.date,
 		init : function(){
 			that=this;
 			//初始化
 			$(".bai").unbind( "click" );
 			$(".bai").on("click",function(){
	   			if($(this).hasClass("hong")){
	   				$(this).removeClass("hong")
	   			}else{
	   				$(".xiazaianniu").removeClass("hong");
	   				$(this).addClass("hong");
	   			} 
   			});
   			$(".fenxianggeipengy").unbind( "click" ).on("click",function(){
   				var photoId = $('.hong').attr("photoId");
   				var title = $('.hong').attr("photoTitle");
   				if( $(".hong").length>0){
   					  $.confirm('是否确定选择"'+title+'"',
					        function () {
					         that.selectPhoto(Bless.blessId,photoId);
					        },
					        function () {
					        }
					      );
   				}else{
   					  $.alert('必须选择其中一个');
   					    
  				}
   				
   			});
   			
   		    //大图预览
		    $(".wx-preview-image").unbind( "click" );
  			 $(".wx-preview-image").on("click",function(){
  				var urls = new Array();
  				urls.push($(this).attr('data'));
  					wx.previewImage({
  					    current: $(this).attr('data'), // 当前显示图片的http链接
  					    urls: urls // 需要预览的图片http链接列表
  					});
  			});
 		},
  		loadData:function (){
  			that=this;
  			$.post(ctx+"/wechat/v2/reserve/getPhotosByOrderId?orderId="+Bless.blessId, 
  				function(response){
  					var list = response.t;
  					var message = response.message;
  					if('5' == message){
  						SelectPhotos.photoListHtml(list);
  					}else if('10' == message){
  						$(".tishi").html("*您预约的城市由于天气等原因没能拍摄日出照片，为表歉意，我们为您特意从其他十个城市选了10张日出照片供您选择，您可从下面的图片中选取一张，下载高清照片。");
  						SelectPhotos.photoListHtml(list);
  					}else if('noPhoto' == message){
  						$(".tishi").html("*由于天气等原因未能拍摄日出照片，为表歉意我们给您和您的朋友各自赠送了一张阳光卡，非常抱歉！");
  						SelectPhotos.photoListHtml(list);
  					}else{
  						$(".tishi").html(message);
  					}
  				}
  		    );
        },
        photoListHtml:function(list){
        	if(list instanceof Array){
					if(list.length>0){
						var html='';
						console.log(list.length);
						for(var i=0;i<list.length;i++){
							html += that.loadHtml(list[i]);
							console.log(i);
						}
						$(".photoList").html(html);
						that.init();
					}
				}
        },
        loadHtml:function (obj){
 	 	   
        	var html ='<div class="rchu">'+
	    		'<div class="zhaopian">'+
	    		'	<img  class="wx-preview-image" data="'+obj.imgUrl+'_y1080.jpg" src="'+obj.imgUrl+'_500.jpg">'+
	    		'	<span class="dizhi">'+obj.provinceF+'.'+obj.title+'  '+obj.shootingTimeF+'</span>'+
	    		'	<div class="xiazaianniu bai" photoId="'+obj.id+'" photoTitle="'+obj.provinceF+'.'+obj.title+'  '+obj.shootingTimeF+'">'+
	    		'	</div>'+
		 	    '</div></div>';
   			return html;
    	},
    	selectPhoto:function (blessId,photoId){
  			that=this;
  			$.post(ctx+"/wechat/v2/bless/selectPhoto?blessId="+blessId+'&photoId='+photoId, 
  					function(response){
  						if(response instanceof Object){
  							if(response.state==1){
  								window.location.href=ctx+"/wechat/v2/bless/showcard?blessId="+blessId;
  							}
  						}
  					}
  			);
        }
   }
    
    Sendeeinfo.init();
    </script>
<script type="text/javascript">
//页面跳转
$(document).on("pageInit", function(e, pageId, $page) {
	if(pageId == 'selectPhoto'){
		SelectPhotos.init();
		SelectPhotos.loadData();
	}
});
</script>
</html>