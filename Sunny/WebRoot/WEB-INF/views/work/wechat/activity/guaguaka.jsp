<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <title>刮刮乐</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1,maximum-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="${ctx }/static/gua/js/jquery.js" ></script>
	 <style type="text/css">
	 	#bg1{ display: none; position:fixed; top: 0%; left: 0%; width: 100%; height: 100%; background-color: black; z-index:1001; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=70);}
	 	#show,#zhong{display: none; position: fixed; background-color: #fff; z-index:1002; overflow: auto;top:0px}
	</style>
  </head>
  <body>
  <body style="overflow:hidden">
	<div id="bg" style="position:absolute;top:0;left:0;"><img src="${ctx}/static/gua/img/guaguaka_bg.png" width="100%" height="100%"></div>
	<div id="bg2" style="width:295px;height:195px;margin:0 auto;">
		<img id="bg2_img" src="${ctx}/static/gua/img/guaguaka_word.png" width="295" height="195" style="position: absolute;"/>
	</div>
	<div>
		<%-- <div id="re" style="position:absolute;top:20px;left:20px;">
			<img src="${ctx}/static/gua/img/rebi_logo.png" width="100%" height="100%" / >
			<div id="re_div" style="color:white;font-family: '黑体';overflow:hidden;">x3</div>
		</div> --%>
		
		<div id="gua" style="position:absolute;top:20px;left:80%;" align="center">
			<img src="${ctx}/static/gua/img/gua_logo.png" width="100%" height="100%" / >
			<div id="gua_div" style="color:white;font-family: '黑体';overflow:hidden; ">x3</div>
		</div>
	</div>
	<div id="gua1" style="width:295px;;margin:0 auto;" >
		<img id="gua1_img" src="${ctx}/static/gua/img/gua_image.png" style="position: absolute;"/>
	</div>
	
	<div id="notify" style="width:295px;height:101px;margin:0 auto;display: none;">
		<img id="nImg" src="${ctx}/static/gua/img/notice_bg.png" style="position: absolute;"/>
		<div id="nImg_div" style="position: absolute;color:white;font-size: 17px;font-family: '黑体'" align="center">
			<div id="nImg_div_ok" style="width:245px;height:101px;padding:20px 0px 0px 50px;" align="left">送出祝福并发送朋友后获得3张刮刮卡，刮刮卡有机会刮出神秘大奖！</div>
		</div>
	</div>
	<!-- <div  style="width:295px;height:101px;margin:0 auto;">
	<div  style="position: absolute;color:white;font-size: 17px;font-family: '黑体'" align="center">
			<div id="nImg_div_ok" style="width:245px;height:101px;padding:20px 0px 0px 50px;" align="left"></div>
		</div>
	</div> -->
	<div id="di" style="width:295px;margin:0 auto;" >
<!--		<img id="di_img" src="${ctx}/static/gua/img/detail_declare_bg.png" style="position: absolute;"/>-->
	</div>
	
	<div id="bg1"></div>
	<div id="show" style="position:absolute;" align="center">
		<img id="show_img" src="${ctx}/static/gua/img/dialog_bg.png" width="100%" height="100%"/>
		<div  id="tishitext" style="margin-top:-145px;margin-left:40px;padding-right: 40px;line-height:1.5;color:#000000;font-size: 17px;font-family: '黑体'" >
			亲，您今天的机会已经用完了~明天再接再励！
		</div>
		<img id="show_btn" src="${ctx}/static/gua/img/btn_sure.png" style="margin-top: 30px;" onclick="hidediv()"/>
	</div>
	<input type="hidden" name="token" id="token" value="${cardToken}">
	

<script type="text/javascript">   
var flag=false;
$(function(){
$.ajax({  
    type:'POST',  
    url:ctx+"/wechat/my/card/verifySunCard",  
  /*  data:{viewUrl:location.href.split('#')[0]},  */
    success:function(result){
  	  if(result.state==1){
  		flag=true;
  		//没有token 凭证 没有资格抽奖
  		 var tokens =  $("#token").val();
		 if(isNull(tokens)){
			 gua=0; 
			 
		 }else{
			 /* if(tokens !=result.t ){
				 tokens ='';
         		 gua=0; 
			 } */
		 }
  	  }else{
  		flag=false;
  		// zhong=100;
  		gua=0; 
  	  }
  	loadImg(setRandImg());
    }	
  
});
});
	var gua = 3;
	var re = 2;
	var zhong = getRandNum();//中奖的次第
	var count = 1; //累计刮的次数
	
	function showdiv(text) { 
		 document.getElementById("bg1").style.display ="block";
		 document.getElementById("show").style.display ="block";
		 document.getElementById("tishitext").innerHTML=text;
	}
	
	function hidediv() {
		 document.getElementById("bg1").style.display ='none';
		 document.getElementById("show").style.display ='none';
		 loadImg(setRandImg());
	}

	$(function(){
		var width = $("#show_img").width();
	    var height = $("#show_img").height();
		var winheight=$(window).height();
		var winwidth=$(window).width();
		$("#show").css({"width":300+"px","height":160+"px","overflow":"hidden","margin-left":(winwidth-320)/2+"px","margin-top":winheight/3+"px"});
		$("#show_btn").css({"width":176*0.5+"px","height":76*0.5+"px"});
		
		$("#re_div").html("x"+re);
		
		if(gua == 0){
			showdiv();
		}
	//	loadImg(setRandImg());
	})
	 
	function loadImg(imgSrc){  
			$("#front").remove();
		    $("#gua1_img").after("<canvas id='front' style='position:absolute;no-repeat' />");
			$("#gua_div").html("x"+gua);
			var body_width = $(window).width();
			var body_height = $(window).height(); 
			$("#gua1_img").width(300).height(160);
			var height = 141;
			var width  = 285;
			var bg2_width = $("#bg2_img").width();
			var bg2_height = $("#bg2_img").height();
			 
			$("#gua1").css({"margin-top":"20px"});
	
			$("#notify").css({"margin-top":"200px"});
			$("#nImg").width(300).height(101);
			
			$("#di").css({"margin-top":"50px"});
			$("#di_img").width(414*0.7).height(24*0.7);
	
	
			$("#gua").width(width/10).height(width/10);
			$("#gua_div").css({"line-height":width/10+"px","width":width/10+"px","height":width/10+"px","margin-top":"-"+($("#gua").height())+"px","margin-left":$("#gua").height()+5+"px","font-size": $("#gua").height()/1.6+"px"});
			$("#re").width(width/10).height(width/10);
			$("#re_div").css({ "line-height":width/10+"px","width":width/10+"px","height":width/10+"px","margin-top":"-"+($("#gua").height())+"px","margin-left":$("#gua").height()+5+"px","font-size": $("#gua").height()/1.6+"px"});
			var gua1_img_width = $("#gua1_img").width();
			$("#front").css({"margin-top":9.3+"px","margin-left":7.5+"px"});
			$("#bg").width("100%").height($(window).height()-1);
			if(gua > 0){
				bodys(height,width,imgSrc);
			}
	}
	function bodys(height,width,imgSrc){
		gua_count = 0;
		var img = new Image();     
		var canvas = document.querySelector('canvas');         
		canvas.style.position = 'absolute';           
		img.addEventListener('load',function(e){  
			var ctx;
			var w = width, h = height;             
			var offsetX = canvas.offsetLeft, offsetY = canvas.offsetTop;             
			var mousedown = false;               
			function layer(ctx){    
				ctx.fillStyle = 'gray';                 
				ctx.fillRect(0, 0, w, h);             
			}   
			function eventDown(e){     
				e.preventDefault();                 
				mousedown=true;    
			}   
			function eventUp(e){            
				e.preventDefault();                 
				mousedown=false;   
			}            
			var falg = true;
			function eventMove(e){
				e.preventDefault();                 
				if(mousedown){                     
					if(e.changedTouches){                         
						e=e.changedTouches[e.changedTouches.length-1];                     
					}                     
					var x = (e.clientX + document.body.scrollLeft || e.pageX) - offsetX || 0,                         
					y = (e.clientY + document.body.scrollTop || e.pageY) - offsetY || 0;                     
					with(ctx){   
						if(falg){
							falg = complete(x,y);
						}
						beginPath()                     
						arc(x, y, 15, 0, Math.PI * 2);                         
						fill();   
					}                
				}             
			}               
			canvas.width=w;             
			canvas.height=h; 
			
			canvas.style.backgroundImage='url('+img.src+')';              
			ctx=canvas.getContext('2d');         
			ctx.fillStyle='b9b9b9';             
			ctx.fillRect(0, 0, w, h);

			layer(ctx);               
			ctx.globalCompositeOperation = 'destination-out';   
			
			
			canvas.addEventListener('touchstart', eventDown);             
			canvas.addEventListener('touchend', eventUp);             
			canvas.addEventListener('touchmove', eventMove);             
			canvas.addEventListener('mousedown', eventDown);             
			canvas.addEventListener('mouseup', eventUp);             
			canvas.addEventListener('mousemove', eventMove);       
		});
		img.src = imgSrc;
		(document.body.style); 
	}
	
		
	//判断是否挂完
	var gua_count = 0; //刮的次数 
	function complete(x,y){
//		alert("xx==="+x);
		//手机适配
		if(100>y&&y>45&&250>x&&x>30){ 
			gua_count++;
		}
		if(gua_count>(70/($(window).width()/320))){    
			console.log(gua_count);
			gua--;
			if(count==zhong){
				if(flag){
					showdiv("恭喜！中奖啦！");
					gua_count = 0;
					count++;
					sunECardBandding();
				}else{
					showdiv("再接再厉，还有"+gua+"次机会");
					gua_count = 0;
					count++;
				}
				
			
				return false;
			}
			if(gua>0){
				showdiv("再接再厉，还有"+gua+"次机会");
				gua_count = 0;
				count++;
				return true;
			}
			if(gua == 0){
				showdiv("本次机会用完啦，下次再来吧");
				gua_count = 0; 
				count++;
				return false;
			}
		}
		return true;
	}
	
	//gua 的概率
	function getRandNum(){
		var randNum = Math.floor(Math.random()*gua+1);
		return randNum;
	}
	
	//根据概率显示刮后结果图
	function setRandImg(){
		if(count==zhong) {
			if(flag){
				return '${ctx}/static/gua/img/zhong.png';
			}else{
				return  '${ctx}/static/gua/img/xiexie.png';
			}
		}else if(gua>0){
			return  '${ctx}/static/gua/img/xiexie.png';
		}else {

			return  '${ctx}/static/gua/img/wan.png';
		}
	}
			
	function verifySunCard(){
		 $.ajax({  
	    type:'POST',  
	    url:ctx+"/wechat/my/card/verifySunCard",  
	  /*  data:{viewUrl:location.href.split('#')[0]},  */
	    success:function(result){
	  	  if(result.state==1){
	  		  window.location.href=ctx+"/wechat/my/card/guaguaka";
	  	  }else{
	  	  }
	    }	
	});
	}
	//获取ECard
	 function sunECardBandding(){
		 var token =  $("#token").val();
		 if(isNull(token)){
			 return;
		 }
		 
		 $.ajax({  
		    type:'POST',  
		    url:ctx+"/wechat/my/card/sunECardBandding?token="+token,  
		  /*  data:{viewUrl:location.href.split('#')[0]},  */
		    success:function(result){
		  	  if(result.state==1){
		  		var html="恭喜你获得一张电子阳光卡！<a href=\"${ctx}/wechat/my/myList?tab=3\">点击查看</a>";
		  		$("#notify").show();
		  		$("#token").val('');
		  		$("#nImg_div_ok").html(html);
		  	  }else{
		  		var html="电子阳光卡绑定失败！<a href=\"${ctx}/wechat/my/myList?tab=3\">点击查看</a>";
				$("#nImg_div_ok").html(html);
		  	  }
		    }	
		});
	}
	</script>
</body>
</html>
