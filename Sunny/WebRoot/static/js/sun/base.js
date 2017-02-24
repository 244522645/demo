//判断是否为空
function isNull(exp){
	if (!exp || typeof(exp)=="undefined" )
	{
	   return true;
	}
	return false;
}

//获取值
function $w(objStr){return document.getElementById(objStr);}

//回退键事件


/*$(document).on('click','.icon-left', function () {
	history.go(-1);
	});*/

// url encodeing
function encodeURI(){
	 var url = encodeURIComponent(location.href.split('#')[0]);
	 return url;
}
//微信 js 初始化
var wxjs = false;
//朋友分享 参数
var com_shareAppMessage_title=com_share_title,
com_shareAppMessage_content=com_share_content,
com_shareAppMessage_link=com_share_link,
com_shareAppMessage_imgUrl=com_shareAppMessage_imgUrl;


//分享 
var fshare = function(){ 
	share();
} 
var token;
function share(){
	if (!wxjs) {
		return false;
	}
	//分享朋友圈
	 wx.onMenuShareTimeline({
	    title: com_share_title, // 分享标题
	    link: com_share_link, // 分享链接
	    imgUrl: com_share_imgUrl, // 分享图标
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	        
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	}); 
	 wx.onMenuShareAppMessage({
		    desc: '', // 分享描述
		    title: com_share_title, // 分享标题
		    link: com_share_link, // 分享链接
		    imgUrl: com_share_imgUrl, // 分享图标
		    type: 'link', // 分享类型,music、video或link，不填默认为link
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    	
		    	 
		    },
		    cancel: function () { 
		    }
		});
	//分享给朋友
	/*wx.onMenuShareAppMessage({
	    title: com_shareAppMessage_title, // 分享标题
	    desc: com_shareAppMessage_content, // 分享描述
	    link: com_shareAppMessage_link, // 分享链接
	    imgUrl: com_shareAppMessage_imgUrl, // 分享图标
	    type: 'link', // 分享类型,music、video或link，不填默认为link
	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    	 $("#shareit").hide(); 
	    	 
	    	 if(!isNull(token)){
	    		 $.alert('恭喜你获得一次抽奖机会', function () {
	    			  window.location.href=ctx+'/wechat/my/card/guaguaka';
	    	        });
	    	 }
	    	 
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    	 $("#shareit").hide(); 
	    }
	});*/
	/*//分享到QQ
	wx.onMenuShareQQ({
		  title: com_share_title, // 分享标题
   	    desc: com_share_content, // 分享描述
   	    link: com_share_link, // 分享链接
   	    imgUrl: com_share_imgUrl, // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	       // 用户取消分享后执行的回调函数
	    }
	});
	//分享到QQ空间
	wx.onMenuShareQZone({
		  title: com_share_title, // 分享标题
   	    desc: com_share_content, // 分享描述
   	    link: com_share_link, // 分享链接
   	    imgUrl: com_share_imgUrl, // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});*/
}
//分享 朋友
function shareAppFunction(title,context,url,img){
		if(wxjs){
    	wx.checkJsApi({
    	    jsApiList: ["onMenuShareAppMessage","previewImage"
    	    ],
    	    success: function (res) {
    	    	//分享给朋友
    	    	wx.onMenuShareAppMessage({
    	    	    title: title, // 分享标题
    	    	    desc: context, // 分享描述
    	    	    link: url, // 分享链接
    	    	    imgUrl: img, // 分享图标
    	    	    type: 'link', // 分享类型,music、video或link，不填默认为link
    	    	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
    	    	    success: function () { 
    	    	        // 用户确认分享后执行的回调函数
    	    	    	// 判断祝福是否 已发送
    	    	    	// 活动抽奖
    	    	    	alert(0);
    	    	    	
    	    	    },
    	    	    cancel: function () { 
    	    	        // 用户取消分享后执行的回调函数
    	    	    }
    	    	});
    	    	//document.querySelector('#onMenuShareAppMessage').onclick = function(){
    	    		//分享给朋友
    	    	//} ;
    	    }
    	});
    	
    	$("#shareit").on("click", function(){
    		 
    	});
    }
}

var wxShare = {
		title:com_share_title,		//分享的标题
		link:com_share_link,		//分享的链接
		imgUrl:com_share_imgUrl,	//分享的图片
		content:com_share_content,  //分享的内容
		type:"link",			    // 分享类型,music、video或link，不填默认为link(只对分享给朋友起作用)
		dataUrl:"",			    // 如果type是music或video，则要提供数据链接，默认为空
		isOnlyApp:false,
		onlyApp:{},
		init:function(json){
			this.title = isNull(json.title)?com_share_title:json.title;
			this.link = isNull(json.link)?com_share_link:json.link;
			this.imgUrl = isNull(json.imgUrl)?com_share_imgUrl:json.imgUrl;
			this.content = isNull(json.content)?com_share_content:json.content;
			this.type = isNull(json.type)?this.type:json.type;
			this.dataUrl = isNull(json.dataUrl)?this.dataUrl:json.dataUrl;
			this.isOnlyApp = isNull(json.isOnlyApp)?this.isOnlyApp:json.isOnlyApp;
		},
		share:function(success,cancel){            //分享 
			 //分享朋友圈
			 wx.onMenuShareTimeline({
			    title: this.title, 
			    link: this.link, 
			    imgUrl: this.imgUrl,
			    success: this.success,
			    cancel: this.cancel
			});
			
			
			if(!this.isOnlyApp){
				//分享给朋友
				wx.onMenuShareAppMessage({
					title: this.title, 
				    desc: this.content, 
				    link: this.link, 
				    imgUrl: this.imgUrl, 
				    type: this.type, 
				    dataUrl: this.dataUrl, // 如果type是music或video，则要提供数据链接，默认为空
				    success:this.success,
				    cancel:this.cancel
				});
			}else{
				//分享给朋友
				wx.onMenuShareAppMessage({
					title: this.onlyApp.title, 
				    desc: this.onlyApp.content, 
				    link: this.onlyApp.link, 
				    imgUrl: this.onlyApp.imgUrl, 
				    type: this.type, 
				    dataUrl: this.type, // 如果type是music或video，则要提供数据链接，默认为空
				    success:this.onlyApp.success,
				    cancel:this.onlyApp.cancel
				});
				
			}
			if(this.isOnlyApp){
			 		wx.hideAllNonBaseMenuItem();
			     wx.hideOptionMenu();
			     //	wx.showOptionMenu();
			     	wx.showMenuItems({
			     	    menuList: ['menuItem:share:appMessage'] //
			     	});
			}else{
				wx.showAllNonBaseMenuItem();
			}
			
		},
		shareAppFunction:function(json,success,cancel){
					//分享给朋友
					wx.onMenuShareAppMessage({
						title: isNull(json.title)?this.title:json.title,
					    desc:  isNull(json.content)?this.content:json.content,
					    link: isNull(json.link)?this.link:json.link,
					    imgUrl: isNull(json.imgUrl)?this.imgUrl:json.imgUrl,
					    type: isNull(json.type)?this.type:json.type,
					    dataUrl:isNull(json.dataUrl)?this.dataUrl:json.dataUrl,
					    success: success,
					    cancel:cancel
					});
					
			 		wx.hideAllNonBaseMenuItem();
			     	wx.hideOptionMenu();
			     	wx.showMenuItems({
			     	    menuList: ['menuItem:share:appMessage'] //
			     	});
		},
		success: function () { 
	        // 用户确认分享后执行的回调函数
			console.log('success');
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    	console.log('success');
	    },
}

function wxConfigInit(){
	 $.ajax({  
       type:'POST',  
       url:ctx+"/wechat/core/jssdk",  
       data:{viewUrl:location.href.split('#')[0]},  
       success:function(result){
       	 
       	 eval("wx.config("+result+")");
       	wx.ready(function(){
       		wxjs=true;
       		
           		//fshare();
       			wxShare.init({});
       			wxShare.share();
		    	wx.error(function(res){
		    		console.log(res);
		    		});
       	});
       	
       }	
   });
}


//图片游览器 图片数组 初始化
var imgBrowArray = new Array();

//图片游览
var imgBrow = function(){ 
	if(wxjs){
		 wx.previewImage({
	  	    current: imgBrowArray[0], // 当前显示图片的http链接
	  	    urls: imgBrowArray // 需要预览的图片http链接列表
	  	});
	}
} 
function  weixinshow(img){
	 imgBrowArray[0]=img;
	 imgBrow();
}
//判断是否PC端
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}
function IsAndroid(){
	var userAgentInfo = navigator.userAgent;
	return userAgentInfo.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
}
function IsiOS(){
	var userAgentInfo = navigator.userAgent;
	return !!userAgentInfo.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);//ios终端
}

//过滤emoji
function filteremoji(emojireg){
    var ranges = [
        '\ud83c[\udf00-\udfff]', 
        '\ud83d[\udc00-\ude4f]', 
        '\ud83d[\ude80-\udeff]'
    ];
    emojireg = emojireg .replace(new RegExp(ranges.join('|'), 'g'), '');
    return emojireg;
}
//js时间格式化
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function getSunCard(){
	 $.ajax({  
       type:'POST',  
       url:ctx+"/wechat/my/card/getSunCard",  
     /*  data:{viewUrl:location.href.split('#')[0]},  */
       success:function(result){
       	console.log(result);
       }	
   });
}
function getOneSunCard(){
	 $.ajax({  
     type:'POST',  
     url:ctx+"/wechat/my/card/getOneSunCard",  
   /*  data:{viewUrl:location.href.split('#')[0]},  */
     success:function(result){
   	  if(result.state==1){
   		  $("#ygk-number").html(result.t.number);
   		  $.popup('.popup-cardselect');
   	  }else{
   		  $.popup('.popup-cardinput');
   	  }
     }	
 });
}

//返回顶部
function goTop(acceleration, time) {
	acceleration = acceleration || 0.1;
	time = time || 16;
	var x1 = 0;
	var y1 = 0;
	var x2 = 0;
	var y2 = 0;
	var x3 = 0;
	var y3 = 0;
	if (document.documentElement) {
	x1 = document.documentElement.scrollLeft || 0;
	y1 = document.documentElement.scrollTop || 0;
	}
	if (document.body) {
	x2 = document.body.scrollLeft || 0;
	y2 = document.body.scrollTop || 0;
	}
	var x3 = window.scrollX || 0;
	var y3 = window.scrollY || 0;
	// 滚动条到页面顶部的水平距离
	var x = Math.max(x1, Math.max(x2, x3));
	// 滚动条到页面顶部的垂直距离
	var y = Math.max(y1, Math.max(y2, y3));
	// 滚动距离 = 目前距离 / 速度, 因为距离原来越小, 速度是大于 1 的数, 所以滚动距离会越来越小
	var speed = 1 + acceleration;
	window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));
	// 如果距离不为零, 继续调用迭代本函数
	if (x > 0 || y > 0) {
	var invokeFunction = "goTop(" + acceleration + ", " + time + ")";
	window.setTimeout(invokeFunction, time);
	}
	}
//微信客户端判断
function isWechat() {  
    var ua = navigator.userAgent.toLowerCase();
    return /micromessenger/i.test(ua) || typeof navigator.wxuserAgent !== 'undefined';
}
//替换微信授权登录地址
function replaceUrl( url){
	$.ajax({
		type:'POST',  
		url:ctx+"/wechat/core/OAuthUrl",
		data:"url="+url,
			success:function(mydata){
				window.location.href=mydata;
			},
		});
}
//微信扫一扫二维码生成
function makeCode (url) {	

 if(url.length>42){
//链接太长了需要转换 短地址
	 $.ajax({
		type:'POST',  
		url:ctx+'/wechat/core/shortUrl',
		data:"url="+url,
		success:function(mydata){
			 var qrcode = new QRCode("qrcode", {
	    	    text: mydata,
	    	    width: 128,
	    	    height: 128,
	    	    colorDark : "#000000",
	    	    colorLight : "#ffffff",
	    	    correctLevel : QRCode.CorrectLevel.H
	    	});
		},
	}); 
 }else{
	 var qrcode = new QRCode("qrcode", {
  	    text: url,
  	    width: 128,
  	    height: 128,
  	    colorDark : "#000000",
  	    colorLight : "#ffffff",
  	    correctLevel : QRCode.CorrectLevel.H
  	});
 }
}

//发送朋友 回调
var OrderShareSuccess = {
		orderId:'',
		success:function(){
			if(isNull(OrderShareSuccess.orderId)){
				return;
			}
			$.ajax({  
			       type:'POST',  
			       url:ctx+"/wechat/bless/send",  
			       data:{orderId:OrderShareSuccess.orderId},  
			       success:function(result){
			       	 console.log(result);
			       	$("#shareit").hide(); 
			       }	
			   });
		},
		cancel:function(){
			   //$("#shareit").hide(); 
		}
}