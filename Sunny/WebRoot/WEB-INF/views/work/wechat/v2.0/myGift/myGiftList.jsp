<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	 <%@include file="/WEB-INF/views/work/common/static.jsp"%>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${config.siteName}</title>
    <meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
     <style type="text/css">
     .page-relation{
      display:block;
     }
     </style>
  </head>
  <body class="v2">
  
  	 <div class="page-group">
        <!-- 单个page ,第一个.page默认被展示-->
        <div class="page page-current" id="myGift">
			<!-- 这里是页面内容区 -->
            <div class="content infinite-scroll infinite-scroll-bottom">
				<div class="buttons-tab xuanxiangk">
			      <a id="tab1_a" href="#tab1" class="tab-link button active">收到的礼物</a>
			      <a id="tab2_a" href="#tab2" class="tab-link button">送出的礼物</a>
			    </div>
			    <div class="tabs">
			      <div id="tab1" class="tab active">
			      </div>
				  <!-- 加载提示符 -->
		          <div class="infinite-scroll-preloader">
		              <div class="preloader"></div>
		          </div>
			      <div id="tab2" class="tab">
			      </div>
			    </div>
        	</div> 
        </div> 
  	 </div>
    <!-- popup, panel 等放在这里 -->
    <div class="panel-overlay"></div>
  </body>
 
  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
  
<script>
var status = '${status}';
if(status == ''){
	status = '1';
}
$(document).on("pageInit", function(e, pageId, $page) {
	//alert(pageId);
	if(pageId=='myGift'){
		var ShoudaoLoadData = {
		  	pageNumber : 1,     //当前页数
		  	pageSize : 10,		//一次加载条数
		  	isPage : true,      //是否还有下一页
		  	isLoad : false,     //是否已经加载过
		  	//加载数据
			loadData : function(){
				if(!ShoudaoLoadData.isPage||ShoudaoLoadData.isLoad){
					 $('.infinite-scroll-preloader.shoudao').hide();
					 return;
				}
				$.post(ctx+"/wechat/v2/me/myGiftList/data?status=1", {page:this.pageNumber,pageSize:this.pageSize,status:1 }, function(response){
					//alert("shoudao"+response);
					var html = "";
		  			if(!isNull(response)){
		  				var list = response.result;
		  				if(list.length==0){
		  					$('.infinite-scroll-preloader').hide();
		  					ShoudaoLoadData.isPage = false;
		  					if(ShoudaoLoadData.pageNumber==1){
		  						$('#tab1').append("<div class='infinite-scroll-preloader' style='margin-top:3rem'>还没有收到礼物</div>");
		  					}
		  					return;
		  				}
		  				if(ShoudaoLoadData.pageNumber==1&&list.length<ShoudaoLoadData.pageSize){
		  					$('.infinite-scroll-preloader').hide();
		  				}
		  				html = shoudaoBlessHtml(list);
		  				if(ShoudaoLoadData.pageNumber==1){
		  					$('#tab1').html("");
		  				}
		  	  			$('#tab1').append(html);
		  	  			ShoudaoLoadData.pageNumber++;
		  	  			ShoudaoLoadData.isLoad = true;
					}else{ $('.infinite-scroll-preloader').hide();}
				});
			}
		}
		function shoudaoBlessHtml(list){
			//alert("---"+list);
			var html = "";
			var href= "";
			for(var i=0;i<list.length;i++){
				if(list[i].type !=1){
					href = ctx+"/wechat/order/sendeeinfo?orderId="+list[i].order.id ;
				}else{
					href = ctx+"/wechat/v2/bless/sendeeinfo?orderId="+list[i].order.id ;
				}
				if(isNull(list[i].photo)){  //未选择照片
					html += 
						"<div class='content-block'>"+
							"<div class='rchu weisong'>"+
					  			    "<div class='zhaopian'>"+
					  			    	"<a href='"+href+"' class='external'>"+
					  			    		"<img src='"+ctx+"/static/v2.0/img/xiaoqic.png' width='100%'>"+
					  		    		"</a>"+
						  		    "</div>"+
						  		    "<div class='zhufuhua weisongdao'>"+
						  		    	"<p>"+list[i].receiver+"</p>"+
						  		     	"<div class='zhufu_hua'>"+list[i].bless+"</div>"+
						  		     	"<div class='shuming'>From："+list[i].sender+"</div>"+
						  		    "</div>"+
					  		   "</div>"+
			  		   	"</div>";
				}else{ //已选择照片
					html += 
						"<div class='content-block'>"+
							"<div class='rchu'>"+
					  			    "<div class='zhaopian'>"+
					  			    	"<a href='"+href+"' class='external'>"+
					  		    			"<img src='"+list[i].photo.imgUrl+"_500.jpg' width='100%'>"+
					  		    		"</a>"+
					  		    		"<div class='address_time'>"+
						  		    		"<span class='dizhi'>"+list[i].photo.title +"-"+list[i].photo.provinceF+"</span>"+
						  		    		"<span class='paisshijian'>"+new Date(list[i].photo.shootingTime).Format('yyyy.MM.dd')+"</span>"+
					  		    		"</div>"+
					  		    	"</div>"+
						  		    "<div class='zhufuhua'>"+
						  		    	"<p>"+list[i].receiver+"</p>"+
						  		     	"<div class='zhufu_hua'>"+list[i].bless+"</div>"+
						  		     	"<div class='shuming'>From："+list[i].sender+"</div>"+
						  		    "</div>"+
					  		   "</div>"+
			  		   	"</div>";
				}
				
					
				}
				return html;
		}
	//========================================	
		
		var SongchuLoadData = {
			  	pageNumber : 1,     //当前页数
			  	pageSize : 10,		//一次加载条数
			  	isPage : true,      //是否还有下一页
			  	isLoad : false,     //是否已经加载过
			  	//加载数据
				loadData : function(){
					if(!SongchuLoadData.isPage||SongchuLoadData.isLoad){
						$('.infinite-scroll-preloader').hide();
						return;
					}
					$.post(ctx+"/wechat/v2/me/myGiftList/data?status=2", {page:this.pageNumber,pageSize:this.pageSize,status:2}, function(response){
						var html = "";
			  			if(!isNull(response)){
			  				var list = response.result;
			  				if(list.length==0){
			  					$('.infinite-scroll-preloader').hide();
			  					SongchuLoadData.isPage = false;
			  					if(SongchuLoadData.pageNumber==1){
			  						$('#tab2').append("<div class='infinite-scroll-preloader' style='margin-top:3rem'>还没有送出礼物</div>");
			  					}
			  					return;
			  				}
			  				if(SongchuLoadData.pageNumber==1&&list.length<SongchuLoadData.pageSize){
			  					$('.infinite-scroll-preloader').hide();
			  				}
			  				html = songchuBlessHtml(list);
			  				if(SongchuLoadData.pageNumber==1){
			  					$('#tab2').html("");
		  					}
			  	  			$('#tab2').append(html);
			  	  			SongchuLoadData.pageNumber++;
			  	  			SongchuLoadData.isLoad = true;
						}else{$('.infinite-scroll-preloader').hide();}
					});
				}
			}
		function songchuBlessHtml(list){
			//alert("---"+list);
			var html = "";
			var href = "";
			for(var i=0;i<list.length;i++){
				if(list[i].type !=1){
					href = ctx+"/wechat/order/sendeeinfo?orderId="+list[i].order.id ;
				}else{
					href = ctx+"/wechat/v2/bless/sendeeinfo?orderId="+list[i].order.id ;
				}
				if('1' == list[i].isread && !isNull(list[i].photo)){
					html += "<div class='content-block'>"+
								"<div class='rchu'>"+
						  			    "<div class='zhaopian'>"+ 
						  			  		"<a href='"+href+"' class='external'>"+
						    					"<img src='"+list[i].photo.imgUrl+"_500.jpg' width='100%'>"+
						    				"</a>" +
						    				"<div class='address_time'>"+
						    					"<span class='dizhi'>"+list[i].photo.title +"-"+list[i].photo.provinceF+"</span>"+
							  		    		"<span class='paisshijian'>"+new Date(list[i].photo.shootingTime).Format('yyyy.MM.dd')+"</span>"+
							  		    	"</div>"+
							  		    "</div>"+
							  		    "<div class='zhufuhua'>"+
							  		    	"<p>"+list[i].receiver+"</p>"+
							  		     	"<div class='zhufu_hua'>"+list[i].bless+"</div>"+
							  		     	"<div class='shuming'>From："+list[i].sender+"</div>"+
							  		    "</div>"+
						  		   "</div>"+
				  		   	"</div>";
  			    }else{
  			    	html += "<div class='content-block'>"+
								"<div class='rchu weisong'>"+
						  			    "<div class='zhaopian'>"+ 
						  			  		"<a href='"+href+"' class='external'>"+
								  				"<img src='"+ctx+"/static/v2.0/img/xiaoqic.png' width='100%'>"+
						    				"</a>" +
								  		"</div>"+
								  		"<div class='zhufuhua weisongdao'>"+
							  		    	"<p>"+list[i].receiver+"</p>"+
							  		     	"<div class='zhufu_hua'>"+list[i].bless+"</div>"+
							  		     	"<div class='shuming'>From："+list[i].sender+"</div>"+
							  		    "</div>"+
						  		"</div>"+
				  		   	"</div>";
  			    }
				
			}
			return html;
		}
	   /**
		* 数据工厂类型
		*/
		var LoadClass = {
			 maxPageNumber :50, // 最多可加载的条目
			 type:1,  
			 init:function(){
				LoadClass.type=status;
				$("#tab1_a").unbind("click").click(function(){
					LoadClass.type='1';
					LoadData = LoadClass.createLoadData();
					LoadData.loadData();
					});
				$("#tab2_a").unbind("click").click(function(){
					LoadClass.type='2';
					LoadData = LoadClass.createLoadData();
					LoadData.loadData();
					});
			 },
			 createLoadData:function(){
				var loadData;
				switch(this.type){
						case '1': 
							loadData =  ShoudaoLoadData;  
							break;
						case '2': 
							loadData =  SongchuLoadData;
							break;
						default: 
							break;
				}
				return loadData;
			 }
		 };
	
		 var LoadData = null;
	 	  //多个标签页下的无限滚动
	      var loading = false;
	      $(document).on('infinite', function() {
	    	  console.log(LoadData.pageNumber);
	        if (loading) 
	        	return;
	        // 设置flag
	        loading = true;
	        // 模拟1s的加载过程
	        setTimeout(function() {
	          // 重置加载flag
	          loading = false;
	          if (LoadData.pageNumber>= LoadClass.maxPageNumber) {
	            // 加载完毕，则注销无限加载事件，以防不必要的加载:$.detachInfiniteScroll($('.infinite-scroll').eq(tabIndex));多个无线滚动请自行根据自己代码逻辑判断注销时机
	            // 删除加载提示符
	            //$('.infinite-scroll-preloader').eq(tabIndex).hide();
	            return;
	          }
	       	  LoadData.isLoad = false;
	  	      LoadData.loadData();
	          $.refreshScroller();
	        }, 1000);
	      });
	      
	      LoadClass.init();
	  	  LoadData = LoadClass.createLoadData();
	  	  LoadData.loadData();
     }
});
//初始化sui框架的方法
$(function(){
	$.init();
});
</script> 
</html>
