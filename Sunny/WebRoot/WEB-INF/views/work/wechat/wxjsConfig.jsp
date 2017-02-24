<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
<!--
.qrcode{
	background: transparent;
	text-align: center;
}
.qrcode .qrbg{
    width: 10rem;
    height: 10rem;
    text-align: center;
    margin-top: 20%;
    margin-bottom: auto;
    padding: 1.4rem;
    margin-left: auto;
    margin-right: auto;
    background: #fff;
    border: 2px solid #000000;
    -moz-border-radius: 15px; 
    -webkit-border-radius: 15px; 
    border-radius:15px;  
}
.qrcode .qrbg img{
 border: 6px solid #DDD;
 -moz-border-radius: 15px; 
    -webkit-border-radius: 15px; 
    border-radius:15px;
    margin: auto;
     width: 5rem;
}
-->
</style>
<div  class="open-about popup popup-about qrcode" style="display:none;">
	<div id="qrcode" class="qrbg" >
	<div style="font-size: 0.5rem;margin-bottom: 20px">「请使用微信打开」</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/static/js/qrcode.js"></script>
<script type="text/javascript">
     wxConfigInit();
     /**
     *微信登录逻辑 2.0
     **/
     var wxlogin=false;
     var wxinfo=false;
     
     <c:if test="${wxlogin}">
     	wxlogin=true;
	 </c:if>
	 <c:if test="${wxinfo}">
	 wxinfo=true;
	 </c:if>
	// 延迟判断
	setTimeout(function() {  
		var thisUrl=location.href.split('#')[0];
	    if(isWechat()) {
	    	 
	    	/*  if(!wxlogin||!wxinfo){
			    	replaceUrl( encodeURIComponent(thisUrl));
			  } */
	    	 if(!wxlogin){
			    	replaceUrl( encodeURIComponent(thisUrl));
			  }
	    } else {
	    	 makeCode (thisUrl);
	    	 
	    	$.popup('.qrcode');
	    }
	   
	}, 800);
     $(function(){
     	/*  
     	  var myPhotoBrowserPopup = $.photoBrowser({
     	      photos : imgBrowArray,
     	     type: 'popup'
     	  });
     	  $(document).on('click','.pb-popup',function () {
     	    myPhotoBrowserPopup.open();
     	  });
		 
     	 $(document).on('click','.wx-img-brow',function () {
     		imgBrow();
      	  }); */
     	 
     });
</script>  