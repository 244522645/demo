<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<script type="text/javascript">
/**********************阳光卡列表页【开始】**************************/

	var cardListData = {
		  	pageNumber : 1,     //当前页数
		  	pageSize : 10,		//一次加载条数
		  	isPage : true,      //是否还有下一页
		  	isLoad : false,     //是否已经加载过
		  	LoadData :this,
		  	init:function(){
		  		LoadData=this;

		  		 //多个标签页下的无限滚动
		  		var loading = false;
		  		$(document).on('infinite', function() {
		  		  if (loading) 
		  		  	return;
		  		  // 设置flag
		  		  loading = true;
		  		  // 模拟1s的加载过程
		  		  setTimeout(function(){
		  		    // 重置加载flag
		  		    loading = false;
		  		    if (LoadData.pageNumber>= 50) {
		  		      // 加载完毕，则注销无限加载事件，以防不必要的加载:$.detachInfiniteScroll($('.infinite-scroll').eq(tabIndex));多个无线滚动请自行根据自己代码逻辑判断注销时机
		  		      // 删除加载提示符
		  		      //$('.infinite-scroll-preloader').eq(tabIndex).hide();
		  		      return;
		  		    }
		  		    console.log(LoadData.pageNumber);
		  		 	  LoadData.isLoad = false;
		  		      LoadData.loadData();
		  		    $.refreshScroller();
		  		  }, 1000);
		  		});
		  	},
		  	//加载数据
			loadData : function(){
				if(!cardListData.isPage||cardListData.isLoad){
					 $('.infinite-scroll-preloader.shoudao').hide();
					 return;
				}
				$.post(ctx+"/wechat/v2/sunnyCard/cardListDetail", {page:this.pageNumber,pageSize:this.pageSize }, function(response){
					//alert("shoudao"+response);
					var html = "";
		  			if(!isNull(response)){
		  				var list = response.result;
		  				if(list.length==0){
		  					$('.infinite-scroll-preloader').hide();
		  					cardListData.isPage = false;
		  					if(cardListData.pageNumber==1){
		  						$('#cards_content').append("<div class='infinite-scroll-preloader' style='margin-top:3rem'>还没阳光卡</div>");
		  					}
		  					return;
		  				}
		  				if(cardListData.pageNumber==1&&list.length<cardListData.pageSize){
		  					$('.infinite-scroll-preloader').hide();
		  				}
		  				/*填充数据*/
		  				console.log(list);
		  				html = resultToHtml_cardList(list);
		  				
		  				if(cardListData.pageNumber==1){
		  					$('#cards_content').html("");
		  				}
		  	  			$('#cards_content').append(html);
		  	  			cardListData.pageNumber++;
		  	  			cardListData.isLoad = true;
					}else{ $('.infinite-scroll-preloader').hide();}
				});
			}
		};
	

	
function resultToHtml_cardList(list){
	var html = "";
	for(var i=0;i<list.length;i++){
			html += '<div class="card kapianppain">'+
						'<div class="card-content">'+
							'<div class="card-content-inner kaka">'+
								'<div class="huaduo">'+
									'<img src="'+ctx+'/static/v2.0/img/huaduo.png">'+
								'</div>'+
								'<div class="wenzi">'+
									'<span class="yanggk99">99元阳光卡</span><br />'+
									'<span class="youxiaorq">有效日期:'+list[i].failureTime_str+'</span>'+
								'</div>'+
								'<div class="yanggguangka">'+
									'<img src="'+ctx+'/static/v2.0/img/yanggka.png">'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</div>';
		}
		return html;
}



/**********************阳光卡列表页【结束】**************************/
/**********************使用明细【开始】**************************/
function loadData_usedDetails(){
		$.post(ctx+"/wechat/v2/sunnyCard/usedDetails", 
				function(response){
					var list = eval(response);
					console.log(response);
					var html = resultToHtml_usedDetails(list);
					$(".mingxi").html('');
					$(".mingxi").append(html);
				}
		);
	};
	function resultToHtml_usedDetails(list){
		var html = "";
		for(var i=0;i<list.length;i++){
			
			if(0 == list[i].inout){ //0：进   1：出 	
				if(0 == list[i].inoutType){ //0 ：系统 赠送给用户一张卡
					html += '<div class="mingxi1">'+
								'<img src="'+ctx+'/static/v2.0/img/jia.png">'+
								'<div class="zengyv">'
									+ new Date(list[i].updateTime).Format("yyyy-MM-dd")+'<br /> 获得了一张阳光卡'+
								'</div>'+
							'</div>';
				}else{  //1 ：他人送 给用户一张卡
					html += '<div class="mingxi1">'+
								'<img src="'+ctx+'/static/v2.0/img/jia.png">'+
								'<div class="zengyv">'
									+ new Date(list[i].updateTime).Format("yyyy-MM-dd")+'<br />获得了"'+list[i].fromUserId.wechatNickname+'"赠送的一张阳光卡'+
								'</div>'+
							'</div>';
				}
			}else{
				if(0 == list[i].inoutType){ //0:消费
					html += '<div class="mingxi1">'+
								'<img src="'+ctx+'/static/v2.0/img/jian.png">'+
								'<div class="zengyv">'
									+ new Date(list[i].updateTime).Format("yyyy-MM-dd")+'<br />消费了一张阳光卡'+
								'</div>'+
							'</div>';
				}else{  //1:赠送他人
					html += '<div class="mingxi1">'+
								'<img src="'+ctx+'/static/v2.0/img/jian.png">'+
								'<div class="zengyv">'
									+ new Date(list[i].updateTime).Format("yyyy-MM-dd")+'<br />赠送给"'+list[i].toUserId.wechatNickname+'"了一张阳光卡'+
								'</div>'+
							'</div>';
				}
			}
		}
		return html;
	}

/**********************使用明细【结束】**************************/
/**********************绑卡【开始】**************************/

  download = {
	   		that:this,
			init : function(){
					that=this;
					$(".tijiaoanniu").on("click",function(){
					   that.yanzheng();
					});
					},
			yanzheng : function(){
					  	var cardNoVal = $("#cardNo").val();
					  	var passVal = $("#password").val();
					  	var reg=/^[1-9]\d*$|^0$/; 
						if(passVal == ''||cardNoVal == '') {
							$.toast("请输入卡号或者密码");
						} else if(reg.test(cardNoVal)==false){
							$.toast("卡号为数字");
						}else{
							$.post(ctx+"/wechat/my/card/sunCardBandding",{number:cardNoVal,pwd:passVal},function(response){
								console.log(response);
									if(null == response.t){
										$.toast(response.message);
									}else if(response.message = "" || null != response.t){
										$.popup('.popup-chenggong');
										setTimeout(function(){
											$.closeModal(".popup-chenggong");
											window.history.back();
										},2000);
									}else{
										
									}
								} 
								
							);
							
							
						}
					}
			};



/**********************绑卡【结束】**************************/



//初始化sui框架的方法
$(function(){
	$.init();
});
//页面跳转
$(document).on("pageInit", function(e, pageId, $page) {
	if(pageId == "cardList") {
		cardListData.init();
		cardListData.loadData();
	}else if(pageId == "cardInstructions"){
		loadData_usedDetails();
	}else if(pageId == "cardBinding"){
		download.init();
	}
});
</script> 