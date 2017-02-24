<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<script>
var status = '${status}';
$(document).on("pageInit", function(e, pageId, $page) {
	//alert(pageId);
	/**
 	* 明信片列表
 	*/
 	if(pageId=='me_bless'){
		function shoudaoBlessHtml(list){
			//alert("---"+list);
			var html = "";
			for(var i=0;i<list.length;i++){
					html += "<div class='card facebook-card'>"+
			  			    "<div class='card-header no-border'>"+
				  		      	"<div class='facebook-avatar'><img src='"+list[i].userId.wechatHeadUrl+"' /></div>"+
				  		      	"<div class='facebook-name'>来自："+list[i].sender+"</div>"+
				  		        "<div class='facebook-date'>"+new Date(list[i].updateTime).Format('yyyy-MM-dd')+"</div>"+
			  		    	"</div>"+
			  		    	"<div class='card-content'>"+
				  		    	"<a class='external'  href='"+ctx+"/wechat/order/sendeeinfo?orderId="+list[i].order.id+"'>"+
				  		    		"<img src='"+list[i].cardImage.localPath+"_500.jpg' width='100%'>"+
				  		    	"</a>"+
				  		    "</div>"+
				  		    "<div class='card-footer no-border'>"+
				  		     	"<span>"+new Date(list[i].photo.shootingTime).Format('yyyy年MM月dd日')+list[i].photo.provinceF+"的第一缕阳光</span>"+
				  		    "</div>"+
			  		    "</div>";
				}
				return html;
		}
	
		function songchuBlessHtml(list){
			var html = "";
			for(var i=0;i<list.length;i++){
				var status = "<span class='ico_wck mxp_wock'>对方未收</span>";
				var head = "";
				if(list[i].isread==1){
					status = "<span class='ico_yck mxp_wock'>对方已收</span>";
					if(isNull(list[i].toUserId.wechatHeadUrl)){
						head = "<div class='facebook-avatar'><img src='"+ctx+"/static/letter/img/def_head.png' /></div>";
					}else{
						head = "<div class='facebook-avatar'><img src='"+list[i].toUserId.wechatHeadUrl+"' /></div>";
					}
				}
					html += "<div class='card facebook-card'>"+
			  			    "<div class='card-header no-border'>"+head+
				  		      	"<div class='facebook-name'>收信人："+list[i].receiver+"</div>"+
				  		        "<div class='facebook-date'>"+new Date(list[i].updateTime).Format('yyyy-MM-dd')+status+"</div>"+
			  		    	"</div>"+
			  		    	"<div class='card-content'>"+
				  		    	"<a class='external' href='"+ctx+"/wechat/order/sendeeinfo?orderId="+list[i].order.id+"'>"+
				  		    		"<img src='"+list[i].cardImage.localPath+"_500.jpg' width='100%'>"+
				  		    	"</a>"+
				  		    "</div>"+
				  		    "<div class='card-footer no-border'>"+
				  		     	"<span>"+new Date(list[i].photo.shootingTime).Format('yyyy年MM月dd日')+list[i].photo.provinceF+"的第一缕阳光</span>"+
				  		    "</div>"+
			  		    "</div>";
				}
				return html;
		}
	
		function weisongchuBlessHtml(list){
			var html = "";
			for(var i=0;i<list.length;i++){
					html += "<div class='card facebook-card'>"+
			  		    	"<div class='card-content'>"+
				  		    	"<a class='external'  href='"+ctx+"/wechat/order/showCard?orderId="+list[i].order.id+"'>"+
				  		    		"<img src='"+list[i].cardImage.localPath+"_500.jpg' width='100%'>"+
				  		    	"</a>"+
				  		    "</div>"+
				  		    "<div class='card-footer no-border'>"+
				  		     	"<span>"+new Date(list[i].photo.shootingTime).Format('yyyy年MM月dd日')+list[i].photo.provinceF+"的第一缕阳光</span>"+
				  		    "</div>"+
			  		    "</div>";
				}
				return html;
		}
	
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
				$.post(ctx+"/wechat/me/blessList", {page:this.pageNumber,pageSize:this.pageSize,status:1 }, function(response){
					//alert("shoudao"+response);
					var html = "";
		  			if(!isNull(response)){
		  				var list = response.result;
		  				if(list.length==0){
		  					$('.infinite-scroll-preloader.shoudao').hide();
		  					ShoudaoLoadData.isPage = false;
		  					if(ShoudaoLoadData.pageNumber==1){
		  						$('.infinite-scroll.active .shoudaoList').append("<div class='infinite-scroll-preloader' style='margin-top:3rem'>还没有收到明信片</div>");
		  					}
		  					return;
		  				}
		  				if(ShoudaoLoadData.pageNumber==1&&list.length<ShoudaoLoadData.pageSize){
		  					$('.infinite-scroll-preloader.shoudao').hide();
		  				}
		  				html = shoudaoBlessHtml(list);
		  				if(ShoudaoLoadData.pageNumber==1){
		  					$('.infinite-scroll.active .shoudaoList').html("");
		  				}
		  	  			$('.infinite-scroll.active .shoudaoList').append(html);
		  	  			ShoudaoLoadData.pageNumber++;
		  	  			ShoudaoLoadData.isLoad = true;
					}else{ $('.infinite-scroll-preloader.shoudao').hide();}
				});
			}
		}
	
		var SongchuLoadData = {
		  	pageNumber : 1,     //当前页数
		  	pageSize : 10,		//一次加载条数
		  	isPage : true,      //是否还有下一页
		  	isLoad : false,     //是否已经加载过
		  	//加载数据
			loadData : function(){
				if(!SongchuLoadData.isPage||SongchuLoadData.isLoad){
					$('.infinite-scroll-preloader.songchu').hide();
					return;
				}
				$.post(ctx+"/wechat/me/blessList", {page:this.pageNumber,pageSize:this.pageSize,status:2}, function(response){
					var html = "";
		  			if(!isNull(response)){
		  				var list = response.result;
		  				if(list.length==0){
		  					$('.infinite-scroll-preloader.songchu').hide();
		  					SongchuLoadData.isPage = false;
		  					if(SongchuLoadData.pageNumber==1){
		  						$('.infinite-scroll.active .songchuList').append("<div class='infinite-scroll-preloader' style='margin-top:3rem'>还没有送出明信片</div>");
		  					}
		  					return;
		  				}
		  				if(SongchuLoadData.pageNumber==1&&list.length<SongchuLoadData.pageSize){
		  					$('.infinite-scroll-preloader.songchu').hide();
		  				}
		  				html = songchuBlessHtml(list);
		  				if(SongchuLoadData.pageNumber==1){
		  					$('.infinite-scroll.active .songchuList').html("");
	  					}
		  	  			$('.infinite-scroll.active .songchuList').append(html);
		  	  			SongchuLoadData.pageNumber++;
		  	  			SongchuLoadData.isLoad = true;
					}else{$('.infinite-scroll-preloader.songchu').hide();}
				});
			}
		}
	
	    var WeiSongchuLoadData = {
		  	pageNumber : 1,     //当前页数
		  	pageSize : 10,		//一次加载条数
		  	isPage : true,      //是否还有下一页
		  	isLoad : false,     //是否已经加载过
		  	//加载数据
			loadData : function(){
				if(!WeiSongchuLoadData.isPage||WeiSongchuLoadData.isLoad){
					$('.infinite-scroll-preloader.weisongchu').hide();
					return;
				}
				$.post(ctx+"/wechat/me/blessList", {page:this.pageNumber,pageSize:this.pageSize,status:3 }, function(response){
					var html = "";
		  			if(!isNull(response)){
		  				var list = response.result;
		  				if(list.length==0){
		  					$('.infinite-scroll-preloader.weisongchu').hide();
		  					WeiSongchuLoadData.isPage = false;
		  					if(WeiSongchuLoadData.pageNumber==1){
		  						$('.infinite-scroll.active .weisongchuList').append("<div class='infinite-scroll-preloader' style='margin-top:3rem'>马上去制作一张明信片吧！</div>");
		  					}
		  					return;
		  				}
		  				if(WeiSongchuLoadData.pageNumber==1&&list.length<WeiSongchuLoadData.pageSize){
		  					$('.infinite-scroll-preloader.weisongchu').hide();
		  				}
		  				html = weisongchuBlessHtml(list);
		  				if(WeiSongchuLoadData.pageNumber==1){
		  					$('.infinite-scroll.active .weisongchuList').html("");
	  					}
		  	  			$('.infinite-scroll.active .weisongchuList').append(html);
		  	  			WeiSongchuLoadData.pageNumber++;
		  	  			WeiSongchuLoadData.isLoad = true;
					}else{$('.infinite-scroll-preloader.weisongchu').hide();}
				});
			}
		}
	
	   /**
		* 数据工厂类型
		*/
		var LoadClass = {
			 maxPageNumber :50, // 最多可加载的条目
			 type:1,  
			 init:function(){
				LoadClass.type=status;
				$("#shoudao").unbind("click").click(function(){
					LoadClass.type='1';
					LoadData = LoadClass.createLoadData();
					LoadData.loadData();
					});
				$("#songchu").unbind("click").click(function(){
					LoadClass.type='2';
					LoadData = LoadClass.createLoadData();
					LoadData.loadData();
					});
				$("#weisongchu").unbind("click").click(function(){
					LoadClass.type='3';
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
						case '3': 
							loadData =  WeiSongchuLoadData;
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
 	
 	/**
 	* 简信列表
 	*/
 	if(pageId=='me_letter'){
		function shoudaoLetterHtml(list){
			var html = "";
			for(var i=0;i<list.length;i++){
				html += "<div class='front'   onclick='window.location.href=\""+ctx+"/wechat/letter/show?letterId="+list[i].id+"#content\"'>"+
					        "<div class='front-head'>"+
				        			"<div class='head_img'>"+
				        				"<img  src='"+list[i].userId.wechatHeadUrl+"' />"+
				        			"</div>"+
				        			"<div class='srh'>"+
				        				"<div>"+
											"<label for='name'>发信人 :</label>"+
											"<label for='name'>"+list[i].sender+"</label>"+
										"</div>"+
										"<div>"+
											"<label for='mail'>收信人 :</label>"+
											"<label for='name'>"+list[i].receiver+" </label>"+
										"</div>"+
				        			"</div>"+
				    		"</div>"+
				    		"<div class='open'>打开</div>"+
						"</div>";
				}
				return html;
		}
	
		function songchuLetterHtml(list){
			var html = "";
			for(var i=0;i<list.length;i++){
				var head='';
				
				if(list[i].isread==1){
					if(isNull(list[i].toUserId.wechatHeadUrl)){
						head = "<div class='facebook-avatar'><img src='"+ctx+"/static/letter/img/def_head.png' /></div>";
					}else{
						head = "<div class='facebook-avatar'><img src='"+list[i].toUserId.wechatHeadUrl+"' /></div>";
					}
				}else{
					head ="<span class='ico_wck'>未收到</span>";
				}
				
				html += "<div class='front' onclick='window.location.href=\""+ctx+"/wechat/letter/show?letterId="+list[i].id+"#content\"'>"+
				        	"<div class='front-head' >"+
			        			"<div class='head_img'>"+
			        			head+
			        			"</div>"+
			        			"<div class='srh'>"+
			        				"<div>"+
										"<label for='name'>发信人 :</label>"+
										"<label for='name'>"+list[i].sender+"</label>"+
									"</div>"+
									"<div>"+
										"<label for='mail'>收信人 :</label>"+
										"<label for='name'>"+list[i].receiver+" </label>"+
									"</div>"+
			        			"</div>"+
			    			"</div>"+
			    			"<div class='open'>打开</div>"+
					  "</div>";
			}
			return html;
		}
	
		function weisongchuLetterHtml(list){
			var html = "";
			for(var i=0;i<list.length;i++){
				html += "<div class='front'  onclick='window.location.href=\""+ctx+"/wechat/letter/show?letterId="+list[i].id+"#content\"'>"+
					        "<div class='front-head'>"+
			        			"<div class='srh'>"+
			        				"<div>"+
										"<label for='name'>发信人 :</label>"+
										"<label for='name'>"+list[i].sender+"</label>"+
									"</div>"+
									"<div>"+
										"<label for='mail'>收信人 :</label>"+
										"<label for='name'>"+list[i].receiver+" </label>"+
									"</div>"+
			        			"</div>"+
			    			"</div>"+
			    			"<div class='open'>打开</div>"+
					  "</div>";
			}
			return html;
		}
	
		var ShoudaoLoadData = {
		  	pageNumber : 1,     //当前页数
		  	pageSize : 10,		//一次加载条数
		  	isPage : true,      //是否还有下一页
		  	isLoad : false,     //是否已经加载过
		  	//加载数据
			loadData : function(){
				if(!ShoudaoLoadData.isPage||ShoudaoLoadData.isLoad){
					 $('.infinite-scroll-preloader.shoudao_jianxin').hide();
					 return;
				}
				$.post(ctx+"/wechat/me/letterList", {page:this.pageNumber,pageSize:this.pageSize,status:1 }, function(response){
					var html = "";
		  			if(!isNull(response)){
		  				var list = response.result;
		  				if(list.length==0){
		  					$('.infinite-scroll-preloader.shoudao_jianxin').hide();
		  					ShoudaoLoadData.isPage = false;
		  					if(ShoudaoLoadData.pageNumber==1){
		  						$('.infinite-scroll.active .shoudaoList_jianxin').append("<div class='infinite-scroll-preloader' style='margin-top:3rem'>还没有收到简信</div>");
		  					}
		  					return;
		  				}
		  				if(ShoudaoLoadData.pageNumber==1&&list.length<ShoudaoLoadData.pageSize){
		  					$('.infinite-scroll-preloader.shoudao_jianxin').hide();
		  				}
		  				html = shoudaoLetterHtml(list);
		  				if(ShoudaoLoadData.pageNumber==1){
		  					$('.infinite-scroll.active .shoudaoList_jianxin').html("");
	  					}
		  	  			$('.infinite-scroll.active .shoudaoList_jianxin').append(html);
		  	  			ShoudaoLoadData.pageNumber++;
		  	  			ShoudaoLoadData.isLoad = true;
					}else{ $('.infinite-scroll-preloader.shoudao_jianxin').hide();}
				});
			}
		}
	
		var SongchuLoadData = {
		  	pageNumber : 1,     //当前页数
		  	pageSize : 10,		//一次加载条数
		  	isPage : true,      //是否还有下一页
		  	isLoad : false,     //是否已经加载过
		  	//加载数据
			loadData : function(){
				if(!SongchuLoadData.isPage||SongchuLoadData.isLoad){
					$('.infinite-scroll-preloader.songchu_jianxin').hide();
					return;
				}
				$.post(ctx+"/wechat/me/letterList", {page:this.pageNumber,pageSize:this.pageSize,status:2}, function(response){
					var html = "";
		  			if(!isNull(response)){
		  				var list = response.result;
		  				if(list.length==0){
		  					$('.infinite-scroll-preloader.songchu_jianxin').hide();
		  					SongchuLoadData.isPage = false;
		  					if(SongchuLoadData.pageNumber==1){
		  						$('.infinite-scroll.active .songchuList_jianxin').append("<div class='infinite-scroll-preloader' style='margin-top:3rem'>还没有送出简信</div>");
		  					}
		  					return;
		  				}
		  				if(SongchuLoadData.pageNumber==1&&list.length<SongchuLoadData.pageSize){
		  					$('.infinite-scroll-preloader.songchu_jianxin').hide();
		  				}
		  				html = songchuLetterHtml(list);
		  				if(SongchuLoadData.pageNumber==1){
		  					$('.infinite-scroll.active .songchuList_jianxin').html("");
	  					}
		  	  			$('.infinite-scroll.active .songchuList_jianxin').append(html);
		  	  			SongchuLoadData.pageNumber++;
		  	  			SongchuLoadData.isLoad = true;
					}else{$('.infinite-scroll-preloader.songchu_jianxin').hide();}
				});
			}
		}
	
	    var WeiSongchuLoadData = {
		  	pageNumber : 1,     //当前页数
		  	pageSize : 10,		//一次加载条数
		  	isPage : true,      //是否还有下一页
		  	isLoad : false,     //是否已经加载过
		  	//加载数据
			loadData : function(){
				if(!WeiSongchuLoadData.isPage||WeiSongchuLoadData.isLoad){
					$('.infinite-scroll-preloader.weisongchu_jianxin').hide();
					return;
				}
				$.post(ctx+"/wechat/me/letterList", {page:this.pageNumber,pageSize:this.pageSize,status:3 }, function(response){
					var html = "";
		  			if(!isNull(response)){
		  				var list = response.result;
		  				if(list.length==0){
		  					$('.infinite-scroll-preloader.weisongchu_jianxin').hide();
		  					WeiSongchuLoadData.isPage = false;
		  					if(WeiSongchuLoadData.pageNumber==1){
		  						$('.infinite-scroll.active .weisongchuList_jianxin').append("<div class='infinite-scroll-preloader' style='margin-top:3rem'>马上去写一封简信吧！</div>");
		  					}
		  					return;
		  				}
		  				if(WeiSongchuLoadData.pageNumber==1&&list.length<WeiSongchuLoadData.pageSize){
		  					$('.infinite-scroll-preloader.weisongchu_jianxin').hide();
		  				}
		  				html = weisongchuLetterHtml(list);
		  				if(WeiSongchuLoadData.pageNumber==1){
		  					$('.infinite-scroll.active .weisongchuList_jianxin').html("");
	  					}
		  	  			$('.infinite-scroll.active .weisongchuList_jianxin').append(html);
		  	  			WeiSongchuLoadData.pageNumber++;
		  	  			WeiSongchuLoadData.isLoad = true;
					}else{$('.infinite-scroll-preloader.weisongchu_jianxin').hide();}
				});
			}
		}
	
	   /**
		* 数据工厂类型
		*/
		var LoadClass = {
			 maxPageNumber :50, // 最多可加载的条目
			 type:1,  
			 init:function(){
				LoadClass.type=status;
				$("#shoudao_jianxin").unbind("click").click(function(){
					LoadClass.type='1';
					LoadData = LoadClass.createLoadData();
					LoadData.loadData();
				});
				$("#songchu_jianxin").unbind("click").click(function(){
					LoadClass.type='2';
					LoadData = LoadClass.createLoadData();
					LoadData.loadData();
				});
				$("#weisongchu_jianxin").unbind("click").click(function(){
					LoadClass.type='3';
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
						case '3': 
							loadData =  WeiSongchuLoadData;
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
 	
 	 /**
 	 * 阳光卡列表
 	 */
 	 if(pageId=='me_card'){
 		 
 		 function cardListhtml(list){
 			var html = "";
			for(var i=0;i<list.length;i++){
				html += "<li><img class='card-cover' src='"+ctx+"/static/letter/img/yg_card.png' /></li>";
			}
			return html;
		 }
 		 
 		 var CardData = {
		  	pageNumber : 1,     //当前页数
		  	pageSize : 10,		//一次加载条数
		  	isPage : true,      //是否还有下一页
		  	isLoad : false,     //是否已经加载过
		  	//加载数据
			loadData : function(){
				if(!CardData.isPage||CardData.isLoad){
					$('.infinite-scroll-preloader.card_list').hide();
					return;
				}
				$.post(ctx+"/wechat/my/card/getSunCard", {page:this.pageNumber,pageSize:this.pageSize}, function(response){
					var html = "";
		  			if(!isNull(response)){
		  				var list = response.result;
		  				if(list.length==0){
		  					$('.infinite-scroll-preloader.card_list').hide();
		  					CardData.isPage = false;
		  					if(CardData.pageNumber==1){
		  					//	$('.gdyg_card_list').prepend("<div class='infinite-scroll-preloader' style='margin-top:3rem'>还没有阳光卡！</div>");
		  						$('.card_empty').show();
		  					}
		  					return;
		  				}
		  				if(CardData.pageNumber==1&&list.length<CardData.pageSize){
		  					$('.infinite-scroll-preloader.card_list').hide();
		  				}
		  	  			$('.gdyg_card_list').prepend(cardListhtml(list));
		  	  			CardData.pageNumber++;
		  	  			CardData.isLoad = true;
					}else{$('.infinite-scroll-preloader.card_list').hide();}
				});
			}
		 }
 		 CardData.loadData();
 		 
 		 $("#card_yishiyong").click(function(){
 			$("#mingxi_val").html("");
 			$.post(ctx+"/wechat/my/card/getSunCardByUsed", {}, function(list){
 				if(!isNull(list)){
 					var html ="";
 					for(var i=0;i<list.length;i++){
 						html += "<li class='item-content'>"+
			 				        "<div class='item-inner'>"+
			 				          "<div class='item-title'>购买日出明信片</div>"+
			 				          "<div class='item-after'>"+new Date(list[i].updateTime).Format('yyyy-MM-dd')+"</div>"+
			 				        "</div>"+
			 				     "</li>";
	 				}
 					$(".title.mingxi").html("阳光卡使用明细");
 		 			$("#mingxi_val").html(html);
 				}
 			});
 		 });
 		 
 		$("#card_yisongren").click(function(){
 			$("#mingxi_val").html("");
 			$.post(ctx+"/wechat/my/card/getSunCardByMySend", {}, function(list){
 				console.log(list);
 				if(!isNull(list)){
 					var html ="";
 					for(var i=0;i<list.length;i++){
 						html += "<li class='item-content'>"+
			 				        "<div class='item-inner'>"+
			 				          "<div class='item-title'>送给："+list[i].toUserName+"</div>"+
			 				          "<div class='item-after'>"+new Date(list[i].updateTime).Format('yyyy-MM-dd')+"</div>"+
			 				        "</div>"+
			 				     "</li>";
	 				}
 					$(".title.mingxi").html("阳光卡送出用明细");
 		 			$("#mingxi_val").html(html);
 				}
 			});
 		 });
 	 }
});
$.init();
</script>