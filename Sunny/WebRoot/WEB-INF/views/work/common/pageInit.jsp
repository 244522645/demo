<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript">
//sui pageInit
//选择日历
var selectDate;
var  selectText;
$(document).on("pageInit", function(e, pageId, $page) {
	 function searchPhoto(selectText,sdate, lastIndex,pageIndex,news) {
		 	if(isNull(sdate)){
		 		sdate='';
		 	}
		 	if(isNull(selectText)){
		 		selectText='';
		 	}
   	 		 $('.search-infinite-scroll-preloader').show();
   	 		loading = true;
   	 		$(".search-infinite-scroll-nodata").hide();
		    $.ajax({
		 			url:"${ctx}/wechat/index/search/photoPages",
		 			data:"&page="+pageIndex+"&date="+sdate+"&search="+selectText,
		 			success:function(mydata){
		 				var pot = mydata.content;
		 				 // 生成新条目的HTML
				        var html = '';
				        for (var i = 0; i < pot.length; i++) {
				          html += "<a class='img-mask'  href='${ctx}/wechat/index/photo/"+pot[i].id+"'><div class='mask textshow'>"+
	                          "<p>"+pot[i].shootingTimeF+"</p>"+
	                          "<p>"+pot[i].provinceF+" • "+pot[i].title+"</p>"+
			                       "</div>"+
			                      "<img  class='img-responsive' style='width:100%'  src='"+pot[i].imgId.localPath+"_348x232.jpg'>"+
				                  "</a>";
				        }
				        if(pot.length === 0 && pageIndex ==1){
				        	$(".sun-no-data").show();
				        }else{
				        	$(".sun-no-data").hide();
				        }
				        if( pot.length === 0 && pageIndex >1){
			        		$(".search-infinite-scroll-nodata").show();
			        	}
				        // 添加新条目
				        if(news){
				        	 $('.infinite-scroll.active .search-list-container').html(html);
				        }else{
				           $('.infinite-scroll.active .search-list-container').append(html);
				        }
				      
				        searchpage =pageIndex+1;
			          		if(isNull(sdate)){
			 				   sdate = '';
			 			   }
		          		$("#search").val(selectText +' '+ sdate);
			          	loading = false;
			          	$('.search-infinite-scroll-preloader').hide();
		 			},
		 			
		 		});
     }
	
   //公共部分
   if(pageId == "index"||pageId == "searchResult") {
	   
	   
	   $(".sun-select-date").remove();
	   $('body').prepend("<div class=\"sun-select-date sun-select-calendar\"  style='display:none;'><span class=\"icon sun-select-icon\"> <input type=\"text\" style='display:none' id='sun-date' /></span></div>");
	   $('body').swipeDown(function(e){
	    	$(".sun-select-date").removeClass("sun-select-calendar").addClass("sun-select-top");
   		});
   		$('body').swipeUp(function(e){
   		 $('.sun-select-date').show();
   			$(".sun-select-date").removeClass("sun-select-top").addClass("sun-select-calendar");
   		});
   		 $(document).on('click','.sun-select-top', function () {
   			$(".native-scroll").scrollTop(0);
   		}); 
   		 /*  $(document).on('click','.sun-select-calendar', function () {
   			
		   	 });  */
   		$(document).on('click','.sun-select-calendar', function () {
			  var popupHTML = '<div class="popup">'+
			                    '<div class="content-block">'+
			                      '<a href="#" class="close-popup">请选择日期</a>'+
			                      '<div id="my-input" data-toggle="date" />'+
			                    '</div>'+
			                   ' <div class="content-block">'+
			                   ' <div class="row">'+
			                     ' <p><a href="#" class="date-selected"></a></p>'+
			                     ' <div class="col-50"><a href="#" class="button button-big button-fill button-danger close-popup">取消</a></div>'+
			                    '  <div class="col-50"><a href="#" class="button button-big button-fill button-success button-confirm-date">确定</a></div>'+
			                    '</div>'+
			                  '</div>'+
			                  '</div>';
			  $('.sun-select-date').hide();
			  $(".popup").remove();
			  $.popup(popupHTML);
			                  
			  $("#my-input").calendar();
			  //
			  
			  $(document).on('click','.picker-calendar-day', function () {
				  var datayear = $("#my-input").find(".picker-calendar-day-selected").attr("data-year");
				  var datamonth = $("#my-input").find(".picker-calendar-day-selected").attr("data-month");
				   var dataday = $("#my-input").find(".picker-calendar-day-selected").attr("data-day");
					var sundate = datayear+'-'+(parseInt(datamonth)+1)+'-'+dataday;
				   if(!isNull(sundate))
					 $(".date-selected").html('当前选择日期：'+sundate);
			  });
			  $(document).on('click','.button-confirm-date', function () {
				  var datayear = $("#my-input").find(".picker-calendar-day-selected").attr("data-year");
				  var datamonth = $("#my-input").find(".picker-calendar-day-selected").attr("data-month");
				   var dataday = $("#my-input").find(".picker-calendar-day-selected").attr("data-day");
				  if(isNull(dataday)){
					   datayear = $("#my-input").find(".picker-calendar-day-today").attr("data-year");
					   datamonth = $("#my-input").find(".picker-calendar-day-today").attr("data-month");
					    dataday = $("#my-input").find(".picker-calendar-day-today").attr("data-day");
					
				  }
				   
				  
				   var sundate = datayear+'-'+(parseInt(datamonth)+1)+'-'+dataday;
					$.closeModal(".popup");
				  selectDate=sundate;
				  if(pageId == "index") {
					  selectText='';
				     // $.router.load("${ctx}/wechat/index/search/put?search=&selectDate="+selectDate, true);
				      window.location.href="${ctx}/wechat/index/search/put?search=&selectDate="+selectDate;
				      selectText='';
				  }
				  if(pageId == "searchResult") {
					  $('.infinite-scroll.active .search-list-container').html("");
					  searchPhoto(selectText,selectDate, lastIndex,1,true);
					  $('.sun-select-date').show();
				  }
			  });
			});
   }else{
	   $(".sun-select-date").remove();
   }
   if(pageId == "show") {
	   $(".popup").remove();
	   $(".popup-overlay").remove();
   }
   if(pageId == "index") {
		  //多个标签页下的无限滚动
	    var loading = false;
	    //page
	    var page = 2;
	    var pagetab2 = 2;
   	  if($('#index .list-container').eq(0).find('.img-mask').length<10)
   		 $('#index .infinite-scroll-preloader').eq(0).hide();
   	  if($('#index .list-container').eq(1).find('.img-mask').length<10)
   		 $('#index .infinite-scroll-preloader').eq(1).hide();
      
   	  
      // 每次加载添加多少条目
      var itemsPerLoad = 10;
      // 最多可加载的条目
      var maxItems = 10000;
      var lastIndex = $('#index .list-container .img-mask').length;
      function addIndexItems(number, lastIndex,tabIndex,tab,pageIndex) {
    	  $('#index .infinite-scroll-preloader').eq(tabIndex).show();
	    	if(lastIndex%10!=0){
	    		$('#index .infinite-scroll-preloader').eq(tabIndex).hide();
	    		return;
	    	}
    	    $.ajax({
	 			url:"${ctx}/wechat/index/photoPages",
	 			data:"subject="+tab+"&page="+pageIndex,
	 			success:function(mydata){
	 				var pot = mydata.content;
	 				 // 生成新条目的HTML
	 				 
			        var html = '';
			        for (var i = 0; i < pot.length; i++) {
			          html = "<a class='img-mask'  href='${ctx}/wechat/index/photo/"+pot[i].id+"'><div class='mask textshow'>"+
                              "<p>"+pot[i].shootingTimeF+"</p>"+
                              "<p>"+pot[i].provinceF+" • "+pot[i].title+"</p>"+
		                       "</div>"+
		                      "<img  class='img-responsive' style='width:100%'  src='"+pot[i].imgId.localPath+"_348x232.jpg'>"+
			                  "</a>";
			          $('#index .infinite-scroll.active .list-container').append(html);
			        }
			        // 添加新条目
			      
			        if(tabIndex==0)
			        	 page =pageIndex+1;
		          	if(tabIndex==1)
		          		pagetab2 =pageIndex+1;
		          	
		          	loading = false;
	 			},
	 			
	 		});
      }
      $(document).on('infinite', function() {
	        // 如果正在加载，则退出
	        if (loading) return;
	        // 设置flag
	        loading = true;
	        var tabIndex = 0;
	        if($(this).find('#index .infinite-scroll.active').attr('id') == "tab1"){
	          tabIndex = 0;
	        }
	        if($(this).find('#index .infinite-scroll.active').attr('id') == "tab2"){
	          tabIndex = 1;
	        }
	        lastIndex = $('#index .list-container').eq(tabIndex).find('.img-mask').length;
	        // 模拟1s的加载过程
	        setTimeout(function() {
		          // 重置加载flag
		          if (lastIndex >= maxItems) {
		            // 加载完毕，则注销无限加载事件，以防不必要的加载:$.detachInfiniteScroll($('.infinite-scroll').eq(tabIndex));多个无线滚动请自行根据自己代码逻辑判断注销时机
		            $.detachInfiniteScroll($('#index .infinite-scroll').eq(tabIndex));
		            // 删除加载提示符
		            $('.infinite-scroll-preloader').hide();
		            return;
		          }
		          if(tabIndex==0)
		        	  addIndexItems(itemsPerLoad,lastIndex,tabIndex,"日出",page);
		          if(tabIndex==1)
		        	  addIndexItems(itemsPerLoad,lastIndex,tabIndex,"升旗",pagetab2);
		          // 更新最后加载的序号
		          lastIndex =  $('#index .list-container').eq(tabIndex).find('.img-mask').length;
		          
		          $.refreshScroller();
	        }, 1000);
      });
      
      //获取名人生日
      $(document).on('click','.popup-birthday', function () {
    	  var popupHTML =   "<div class='popup sun-birthday'><header class='bar bar-nav'>"+
								//  "<h1 id='birthday-title' class='title'><a href='#' class='icon  pull-right '></a></h1>"+
								  "<span class='icon-close close-popup'></span>"+
							"</header>"+
							"<div id='birthday-content' class='content'>"+
							"</div></div>";
		  $.popup(popupHTML);
		  $.post("${ctx}/wechat/cbirthday/day",null,function(data){
			  $("#birthday-content").html(data.content);
			 // $("#birthday-title").html(data.title);
			  $(document).on('click','.sun-birthday', function () {
		    	  $.closeModal('.popup');
		    	  $(".popup-overlay").remove();
		      });
    	  });
      });
      $(document).on('click','.close-popup', function () {
    	  $.closeModal('.popup-birthday');
      });
     
   }
   if(pageId == "searchResult") {
	   var sdate = '${selectDate}';//选择日期
	   $('.sun-select-date').show();
	    selectText = '${search}';    //搜索
	   
	   if(isNull(sdate)){
		   sdate = selectDate;
	   }
	   if(isNull(sdate)){
		   sdate = '';
	   }
	   $("#search").val(selectText +' '+ sdate);
		  //多个标签页下的无限滚动
	    var loading = false;
	    //page
	    var searchpage = 1;
	    var pagetab2 = 1;
      if($('.search-list-container').eq(0).find('.img-mask').length<10)
	   		 $('.search-infinite-scroll-preloader').eq(0).hide();
   	  if($('.search-list-container').eq(1).find('.img-mask').length<10)
   		 $('.search-infinite-scroll-preloader').hide();
	      
	      // 每次加载添加多少条目
	      var itemsPerLoad = 10;
	      // 最多可加载的条目
	      var maxItems = 10000;
	      var lastIndex = $('.list-container .img-mask').length;
	      searchPhoto(selectText,sdate, lastIndex,searchpage);
	      $(document).on('infinite', function() {
	        // 如果正在加载;，则退出
	        if (loading) return;
	        // 设置flag
	        loading = true;
	        lastIndex = $('.search-list-container').find('.img-mask').length;
	        // 模拟1s的加载过程
	        setTimeout(function() {
	          // 重置加载flag
	          if (lastIndex >= maxItems) {
	            // 删除加载提示符
	            $('.search-infinite-scroll-preloader').hide();
	            return;
	          }
	          searchPhoto(selectText,sdate, lastIndex,searchpage,false);
	          // 更新最后加载的序号
	          lastIndex =  $('.search-list-container').find('.img-mask').length;
	          
	          $.refreshScroller();
	        }, 1000);
	      });
	      
   }
   
   //我的
    if(pageId == "myList") {
    	  //多个标签页下的无限滚动
	      var loading = false;
	      //page
	      var page = 1;
	      // 每次加载添加多少条目
	      var itemsPerLoad = 10;
	      // 最多可加载的条目
	      var maxItems = 100;
	      var lastIndex = $('#myList .tabTemp .polaroid-template').length;
	      function addItems(number, lastIndex,tabIndex,tab) {
	    	if(lastIndex%10!=0){
	    		$('#myList .infinite-scroll-preloader').eq(tabIndex).hide();
	    		return;
	    	}
	    	page =lastIndex/number+1;
    	    $.ajax({
	 			url:"${ctx}/wechat/my/myListPage",
	 			data:"subject="+tab+"&page="+(lastIndex/number+1),
	 			success:function(mydata){
	 				var pot = mydata.result;
	 				 // 生成新条目的HTML
			        var html = '';
			        for (var i = 0; i < pot.length; i++) {
			        	if(!isNull(pot[i].cardImage)){
				          html +="<div class='card polaroid-template ";
				          if(pot[i].status == 100){
				        	  html +="grey";
				        	}
				          html +="' onclick='window.location.href=\"${ctx}/wechat/order/sendeeinfo?orderId="+pot[i].id+"\"'><div class='template-top ";
				          if(pot[i].status == 100&&tabIndex==0){
				        	  html +="Igread";
				        	}else{
				        		 html +="gread";
				        	}
				         	 html +="'><img alt='' width='100%' src='${config.imgDomainName}/"+pot[i].cardImage.folder+"/"+pot[i].cardImage.name+"_500.jpg'>"
                         	+ "</div><div class='card-content'>";
                         	if(tabIndex==0){
                         		 html +="<h3>"+pot[i].title+"</h3>";
                         	}else{
                         		html +="<h3>祝<span>"+pot[i].sendeeName+"</span></h3>";
                         	}
                         	 html += "<p class='z-text'>"+pot[i].message+"</p>"
                         	 +"<p class='color-gray footer-label f-6'>"+pot[i].createTimeF+"</p> </div> </div>";
				        }
			       	 }
			        // 添加新条目
			        $("#myList .infinite-scroll.active .tabTemp").append(html);
			        $('#myList .infinite-scroll-preloader').eq(tabIndex).hide();
			        loading = true;
	 			}
	 		});
    	    
    	    
	      }
	      
	      $(document).on('infinite', function() {
	        // 如果正在加载，则退出
	        if (loading) return;
	        // 设置flag
	        loading = true;
	        var tabIndex = 0;
	        if($(this).find('#myList .infinite-scroll.active').attr('id') == "tab1"){
	          tabIndex = 0;
	        }
	        if($(this).find('#myList .infinite-scroll.active').attr('id') == "tab2"){
	          tabIndex = 1;
	        }
	        lastIndex = $('#myList .tab').eq(tabIndex).find('.polaroid-template').length;
	        // 模拟1s的加载过程
	        setTimeout(function() {
	          // 重置加载flag
	          loading = false;
	          if (lastIndex >= maxItems) {
	            // 加载完毕，则注销无限加载事件，以防不必要的加载:$.detachInfiniteScroll($('.infinite-scroll').eq(tabIndex));多个无线滚动请自行根据自己代码逻辑判断注销时机
	            $.detachInfiniteScroll($('#myList .infinite-scroll').eq(tabIndex));
	            // 删除加载提示符
	            $('#myList .infinite-scroll-preloader').eq(tabIndex).hide();
	            return;
	          }
	          if(tabIndex==0)
	         	 addItems(itemsPerLoad,lastIndex,tabIndex,"收到");
	          if(tabIndex==1)
		         addItems(itemsPerLoad,lastIndex,tabIndex,"送出");
	          // 更新最后加载的序号
	          lastIndex =  $('#myList .tabTemp').eq(tabIndex).find('.polaroid-template').length;
	          $.refreshScroller();
	        }, 1000);
	      });
	      //关闭 card 窗口
	      $(document).on('click','.popup-card-close', function () {
	      	 $.closeModal(".popup-mail");
	      	 //$(".popup-overlay").removeClass('modal-overlay-visible');
	     	
	      });
	      $('#wode-email-confirm').click(function () {
	    	  var email = $("#email-input").val();
	    	  if(isNull(email)){
	    		  $.toast("请填写邮箱");
	    		  return;
	    	  }
	    	  var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    	  if (!filter.test(email)){
	    		  $.toast("邮箱格式不正确");
	    		  return;
	    	  }
	    		 
	    		  
	    	  $.ajax({
		 			url:"${ctx}/wechat/my/submitDownRequest",
		 			type: 'post',//提交的方式
		 			data:{orderId:selectOrderid,email:email},
		 			dataType:'json',
		 				//请求成功
		 			success:function(result){
		 				//
		 				if(result.state==1){ //绑定成功
		 					 $.toast("提交成功");
		 					$.closeModal(".popup-mail");
		 				} else
		 				{
		 					 $.toast("提交失败");
		 					$.closeModal(".popup-mail");
		 				} 
		 			}
		 		}); 
	    	  
	      });
	      getSunCardList();
		  //绑定
		  $('#wode-input-confirm').click(function () {
			  var id_number_check = document.getElementById("wode-card-number").value;
			  var id_password_check = document.getElementById("wode-card-password").value;
			//先验证卡号和密码再请求  
				if(checkNumberAndPass(id_number_check,id_password_check)) {
					$.ajax({
			 			url:"${ctx}/wechat/my/card/sunCardBandding",
			 			type: 'post',//提交的方式
			 			data:{
			 				number:id_number_check,
			 				pwd:id_password_check},
			 			dataType:'json',
			 				//请求成功
			 			success:function(result){
			 				//
			 				if(result.state==1){ //绑定成功
			 					 $.toast("绑定成功");
			 					//$("#wode-select-sun-card-zhifu1").show();		 					
			 					$("#wode-select-sun-card-zhifu").hide();
			 					getSunCardList();
			 				} else
			 				{
			 					alert(result.message);
			 					//未绑定时显示绑定列表
			 				//$("#wode-select-sun-card-zhifu").show();
			 				//$("#wode-select-sun-card-zhifu1").hide();
			 				} 
			 			}
			 		}); 
				}
				
			}); 
		//显示卡列表
		  function getSunCardList(){
			 	$(".buttons-tab .active").click();
				 $.ajax({  
			      type:'POST',  
			      url:ctx+"/wechat/my/card/getSunCard",  
			      success:function(result){
			    	  var list=result.result;
			    	  if(list.length>0){
			    		  var html="";
				    	  for (var i = 0; i < list.length; i++) {
				    		 var wodeygkuserd = 'wode-ygk';//已激活
				    		  if(list[i].status==2){
				    			  wodeygkuserd = 'wode-ygk-userd'; //已使用
					    		  }
			    			  html+="<li><div class='wode-select-sun-card-zhifu-2' >"
					    		  +"<img id='wode-card-icon'  src='${ctx}/static/images/sun/card.png ' />"	
					    		  +" <hr id='wode-fengexian'/>"
					    		  +"<div class='"+wodeygkuserd+"'>"
					    		  +"<span id='wode-ygk-name'>卡号：</span>"
					              +"<span id='wode-ygk-number'>" 
					              +list[i].number
					              +"</span></div></li>";  
						}
				    	  $(".sun-card-list").html(html); //html()返回#XX的内容html
			    	  }
			      }	
			  });
			}
		//验证卡号 密码
			 function checkNumberAndPass(id_number_check,id_password_check) {
		        var str1_number_check = /^\d+$/.test(id_number_check); //卡号为数字
		        var str_password_check = /^\d+$/.test(id_password_check);//密码为数字
		        if(!str1_number_check){
		        	 document.getElementById("check-message").innerHTML = "※  卡号必须为数字";
		        	 return false;
		         }
		         if (id_number_check.length<6||id_number_check.length>16) {
		 	            document.getElementById("check-message").innerHTML = "※  卡号必须为8位";
		 	            return false;
		        }
		         document.getElementById("check-message").innerHTML = "";
		         return true;
			}
    }
    //个人中心 页面
   if(pageId == "myIndex") {
	     $.ajax({  
		      type:'get',  
		      url:ctx+"/wechat/my/getMemberTj",  
		      success:function(r){
		    	  console.log(r);
		    	  if(r.state==1){
		    		  $("#zhufu").html(r.t.receive+"/"+r.t.send);
		    		  $("#letters").html(r.t.letterReceive+"/"+r.t.letterSend);
		    		  $("#cards").html(r.t.card);
		    	  }
		      }	
		  });
   }
 	//个人中心 卡列表页面
   if(pageId == "myCards") {
	   $(document).on('refresh', '.pull-to-refresh-content',function(e) {
	        // 模拟2s的加载过程
	        setTimeout(function() {
	        	 lastIndex = 0;
	        	 page=1;
	            // addItems(itemsPerLoad, lastIndex);
	            // 加载完毕需要重置
	            $.pullToRefreshDone('.pull-to-refresh-content');
	        }, 2000);
   	   }); 
	 //多个标签页下的无限滚动
	      var loading = false;
	      //page
	      var page = 1;
	      var pageSize = 10;
	      // 每次加载添加多少条目
	      var itemsPerLoad = pageSize;
	      // 最多可加载的条目
	      var maxItems = 10000;
	      lastIndex = $('.list-container li').length;
	      function getCardHtml(obj){
	    	    var hl="";
	    	    hl+="<li>";
	    	    hl+="<img src=\"${ctx}/static/letter/img/yg_card.png\" style=\"width: 100%;\">";
	    	    hl+="<div style=\"position: absolute;z-index: 1233;font-size: 0.5rem;bottom: 1rem;left:0.5rem;color: #333;\"><span>卡号："+obj.number+"</span> <span>有效期至："+new Date(obj.failureTime).Format('yyyy-MM-dd')+"</span></div>";
	    	    hl+="</li>";
	    	    return hl;
	      }
	      function addItemsmyCards(number, lastIndex) {
	    	     if(lastIndex%itemsPerLoad!=0){
		    		$('.infinite-scroll-preloader').hide();
		    		return;
	    	    }  
	    	     $('.infinite-scroll-preloader').show();
	 	      	$.ajax({
	 	    		type:'POST',  
		 			url:"${ctx}/wechat/my/card/getSunCard",
		 			data:"page="+page+"&pageSize="+pageSize,
		 			success:function(mydata){
		 				 // 生成新条目的HTML
				        var html = '';
				        var list=mydata.result;
				    	  if(list.length>0){
					    	  for (var i = 0; i < list.length; i++) {
					    		 var wodeygkuserd = 'wode-ygk';//已激活
					    		 var shiyong='<a href=\"${ctx}/wechat/index\">立即使用</a>';
					    		  if(list[i].status==2){
					    			  wodeygkuserd = 'wode-ygk-userd'; //已使用
					    			  shiyong='已使用';
						    		  }
					    		 /*
					    		  html+="<li><div class=\"my-card-item "+wodeygkuserd+"\"><div class=\"card-right\"><div>有效期至</div>"+
									"	<div>"+new Date(list[i].failureTime).Format('yyyy-MM-dd')+"</div>"+
									"<div>"+shiyong+"</div></div><div class=\"card-left\"><img class=\"liwu\" src=\"${ctx }/static/my/img/liwu.png\">"+
			 						"	<div class=\"card-number-i\">"+
									"			<b><span class=\"card-number-tip\">卡号：</span></b>"+
									"			<b><span class=\"card-number-count\">"+list[i].number+
									"</span></b>"+
									"	</div><div class=\"shuxian\"></div></div></div></li>";
							  		*/ 
							  		 html+= getCardHtml(list[i]);
					    	  }
					    	// 添加新条目
						        if(lastIndex == 0){
						        	  $('.list-container').html(html);
						        	  page =2;
						        }else{
						        	  $('.list-container').append(html);
						        	  page =page+1;
						        }
				    	     }else{
				    	    	 if(page==1){
				    	    		 html+='<div class="sun-no-data" style="display:block;"><div class="content-padded">';
					    	    	 html+=' <img alt="" class="sun-no-data-img" src="${ctx }/static/images/sun/sun-no-data.png">';
					    	    	 html+='	  <p>抱歉，您还没有阳光卡</p>';
					    	    	 html+='	</div></div>';
					    	    	 $(".page").html(html);
				    	    	 }
				    	     }
				    	
					        $('.infinite-scroll-preloader').hide();
		 			}
		 		});
	      }
	      //预先加载20条
	      addItemsmyCards(itemsPerLoad, 0);
	      $(document).on('infinite', '.infinite-scroll-bottom',function() {
	        // 如果正在加载，则退出
	        if (loading) return;
	        // 设置flag
	        loading = true;
	        // 模拟1s的加载过程
	        setTimeout(function() {
	          // 重置加载flag
		          loading = false;
		          
		          if (lastIndex >= maxItems) {
	                  // 加载完毕，则注销无限加载事件，以防不必要的加载
	                  $.detachInfiniteScroll($('.infinite-scroll'));
	                  // 删除加载提示符
	                  $('.infinite-scroll-preloader').remove();
	                  return;
	              }
		          // 更新最后加载的序号
	              lastIndex = $('.list-container li').length;
		          // 添加新条目
	              addItemsmyCards(itemsPerLoad, lastIndex);
	              //容器发生改变,如果是js滚动，需要刷新滚动
	              $.refreshScroller();
	          }, 1000);
	      });
   }
	//个人中心 祝福列表页面
   if(pageId == "myZhufu") {
 	   
function   myZhufu(){
	//多个标签页下的无限滚动
    this.loading = false;
    this.that = this;
    //page
    this.page = 1;
    this.pageSize = 5;
    // 每次加载添加多少条目
    this.itemsPerLoad = pageSize;
    // 最多可加载的条目
    this.maxItems = 10000;
    this.lastIndex = $('.list-container li').length;
    this.init = function(){
    	
    	  $(document).on('refresh', '.myZhufu',function(e) {
   		   $('.infinite-scroll-preloader').remove();
   	        // 模拟2s的加载过程
   	        setTimeout(function() {
   	        	zhufu.lastIndex = 0;
   	        	zhufu.addItemsmyZhufu(zhufu.pageSize, zhufu.lastIndex);
   	            // 加载完毕需要重置
   	            $.pullToRefreshDone('.myZhufu');
   	        }, 2000);
      	   }); 
    }
    this.getHtmlZhufuShou = function(obj){
  	  var html='';
  	  html+='<div class="item-zhufu"> <div class="item-zhufu-shoudao"  onclick="window.location.href=\'${ctx}/wechat/order/sendeeinfo?orderId='+obj.order.id+'\'">';
  	  html+='<div class="letter-tx-fudiv">';
  	  html+='	<div class="letter-tx">';
  	  html+='		<img alt="" src="'+obj.userId.wechatHeadUrl+'">';
  	  html+='	</div>';
  	  html+='</div>';
  	  html+='<div class="letter-item-top">';
  	  html+='	<div class="xiaohongdian"></div>';
  	  html+='	<div class="div-zhufuA2B" style="margin-left: 0.3rem;">';
  	  html+='		<span>'+obj.sender+'</span>';
  	  html+='			<span>祝：</span>';
  	  html+='		<span>'+obj.receiver+'</span>';
  	  html+='	</div>';
  	  html+='	<div class="zhufuhengxian"></div>';
  	  html+='</div>';
  	  html+='<div class="zhufu-item-bottom">';
  	  html+='	<div class="div-zhufuyu">';
  	  html+='		'+obj.bless+'';
  	  html+='	</div>';
  	  if(!isNull(obj.photo)){
  		  html+='	<img alt="" src="'+obj.cardImage.localPath+'_90x90.jpg" class="icon-pic" >';
  	  }
  	  html+='</div>';
  	  html+='</div></div>';
  	  return html;
    }
    this.getHtmlZhufuFa = function(obj){
  	  var html='';
  	  html+='<div class="item-zhufu"> <div class="item-zhufu-fachude"  onclick="window.location.href=\'${ctx}/wechat/order/sendeeinfo?orderId='+obj.order.id+'\'">';
  	  html+='<div class="letter-duifang-du"></div>';
  	  html+='<div class="letter-tx-fudiv-fachude">';
  	  html+='  <div class="letter-tx-fachude">';
  	  html+='		<img alt="" src="'+obj.userId.wechatHeadUrl+'">';
  	  html+='	</div>';
  	  html+='</div>';
  	  html+='<div class="letter-item-top">';
  	  html+='	<div class="div-zhufuB2A" style="margin-left: 0.3rem;">';
  	  html+='		<span>'+obj.sender+'</span>';
  	  html+='			<span>祝：</span>';
  	  html+='		<span>'+obj.receiver+'</span>';
  	  html+='	</div>';
  	  html+='	<div class="zhufuhengxian"></div>';
  	  html+='</div>';
  	  html+='<div class="zhufu-item-bottom-fachude ">';
  	  html+='   <div class="div-zhufuyu-fachude">';
  	  html+='		'+obj.bless+'';
  	  html+='	</div>';
  	  if(!isNull(obj.cardImage)){
  		  html+='	<img alt="" src="'+obj.cardImage.localPath+'_90x90.jpg" class="icon-pic" >';
  	  }
  	  html+='</div>';
  	  html+='</div></div>';
  	  return html;
    }
    this.addItemsmyZhufu = function(number, lastIndex){
	     if(lastIndex%number!=0){
   		$('.infinite-scroll-preloader').hide();
   		return;
	    }  
	     $('.infinite-scroll-preloader').show();
      	$.ajax({
    		type:'GET',  
			url:"${ctx}/wechat/bless/data",
			data:"page="+zhufu.page+"&pageSize="+number,
			success:function(mydata){
				 // 生成新条目的HTML
		        var html = '';
				var item='';
		        var list=mydata.result;
		    	  if(list.length>0){
		    		  var html="";
		    		  var tempTime='';
		    		  if(zhufu.page==1){
		    			  $(".list-container").html("");
		    		  }
			    	  for (var i = 0; i < list.length; i++) {
			    		  var obj=list[i];
			    		 
			    		  if((tempTime!='' && tempTime!=obj.shootingTimeF )|| (i+1)==list.length){
			    			  html+="<div><p  class=\"letter-time\"> "+obj.shootingTimeF+"</p></div>";
			    		  }
			    		  if(obj.role==0){
			    			  html+= zhufu.getHtmlZhufuFa(obj);
				    	  }else{
				    		  html+= zhufu.getHtmlZhufuShou(obj);
				    	  }
			    		    tempTime=obj.shootingTimeF;
			    		 
			    		  $(".list-container").prepend('<li>'+html+'</li>'); //html()返回#XX的内容html
			    		  html="";
			    	  }
			    	  //if(pageSize==list.length)
				        	 zhufu.page =zhufu.page+1;
			    	  $('.infinite-scroll-preloader').hide();
		    	  }else{
		    		  if(page===1){
		    	    		 html+='<div class="sun-no-data" style="display:block;"><div class="content-padded">';
			    	    	 html+=' <img alt="" class="sun-no-data-img" src="${ctx }/static/images/sun/sun-no-data.png">';
			    	    	 html+='	  <p>抱歉，您还没有明信片</p>';
			    	    	 html+='	</div></div>';
			    	    	 $(".page").html(html);
		    	    	 }
		    	  }
		       
		       
			}
		});
 }
}    
	var zhufu = new myZhufu();
	zhufu.init();
	zhufu.addItemsmyZhufu(zhufu.pageSize, 0);
   }
	
 //个人中心 祝福列表页面
   if(pageId == "myLetter") {
	   
function 	myLetter(){
	//多个标签页下的无限滚动
    this.loading = false;
    this.that = this;
    //page
    this.page = 1;
    this.pageSize = 5;
    // 每次加载添加多少条目
    this.itemsPerLoad = pageSize;
    // 最多可加载的条目
    this.maxItems = 10000;
    this.lastIndex = $('.list-container li').length;
    this.init = function(){
    	$(document).on('refresh', '.myLetter',function(e) {
 		   $('.infinite-scroll-preloader').remove();
 	        // 模拟2s的加载过程
 	        setTimeout(function() {
 	        	letter.lastIndex = 0;
 	        	letter.addItemsmyLetter(letter.pageSize, letter.lastIndex);
 	            // 加载完毕需要重置
 	            $.pullToRefreshDone('.myLetter');
 	        }, 2000);
    	   }); 
    }
    this.getHtmlShou = function(obj){
  	  var du='';
  	  if(obj.isread==0){
  		  du='xiaohongdian';
  	  }
  	  var html='';
  	  html+='<div class="item-zhufu"> <div class="item-letter-shoudao" onclick="window.location.href=\'${ctx}/wechat/letter/show?letterId='+obj.id+'\'">';
  	  html+='<div class="letter-tx-fudiv">';
  	  html+='	<div class="letter-tx">';
  	  html+='		<img alt="" src="'+obj.userId.wechatHeadUrl+'">';
  	  html+='	</div>';
  	  html+='</div>';
  	  html+='<div class="letter-item-top">';
  	  html+='	<div class="'+du+'"></div>';
  	  html+='		<div class="div-shoufaxinren" style="">';
  	  html+='			<span>发信人：</span>';
  	  html+='			<span>'+obj.sender+'</span>';
  	  html+='	    </div>';
  	  html+='	    <div class="div-shoufaxinren">';
  	  html+='		    <span>收信人：</span>';
  	  html+='	        <span>'+obj.receiver+'</span>';
		  html+='	    </div>';
		  html+='		<div class="hengxian"></div>';
  	  html+='</div>';
  	  html+='<div class="letter-item-bottom">';
  	  if(obj.isorder==1){
  		  html+='	<img src="${ctx}/static/my/img/pic.png" class="icon-pic" />';
  	  }
			if(obj.isisvoice==1){
				 html+='	<img src="${ctx}/static/my/img/yuyin.png" class="icon-yuyin" />';
	 		 }
			if(obj.iscard==1){
				 html+='	<img src="${ctx}/static/my/img/yinyue.png" class="icon-yinyue" />';
			}
		 
  	  html+='</div>';
  	  html+='</div></div>';
  	  return html;
    }
    this.getHtmlFa = function(obj){
  	  var html='';
  	  var du='';
  	  if(obj.isread==0){
  		  du='letter-duifang-weidu'
  	  }
  	  html+='<div class="item-zhufu"> <div class="item-letter-fachude"  onclick="window.location.href=\'${ctx}/wechat/letter/show?letterId='+obj.id+'\'">';
  	  html+='<div class="'+du+'"></div>';
  	  html+='<div class="letter-tx-fudiv-fachude">';
  	  html+='  <div class="letter-tx-fachude">';
  	  html+='		<img alt="" src="'+obj.userId.wechatHeadUrl+'">';
  	  html+='	</div>';
  	  html+='</div>';
  	  html+='<div class="letter-item-top">';
  	  html+='	<div class="'+du+'"></div>';
  	  html+='		<div class="div-shoufaxinren" style="">';
  	  html+='			<span>发信人：</span>';
  	  html+='			<span>'+obj.sender+'</span>';
  	  html+='	    </div>';
  	  html+='	    <div class="div-shoufaxinren">';
  	  html+='		    <span>收信人：</span>';
  	  html+='	        <span>'+obj.receiver+'</span>';
		  html+='	    </div>';
		  html+='		<div class="hengxian"></div>';
  	  html+='</div>';
  	  html+='<div class="letter-item-bottom-fachude">';
  	  if(obj.isorder==1){
  		  html+='	<img src="${ctx}/static/my/img/pic.png" class="icon-pic" />';
  	  }
		  if(obj.isisvoice==1){
				 html+='	<img src="${ctx}/static/my/img/yuyin.png" class="icon-yuyin" />';
	 		  }
		  if(obj.iscard==1){
				 html+='	<img src="${ctx}/static/my/img/yinyue.png" class="icon-yinyue" />';
		  }
  	  html+='</div>';
  	  html+='</div></div>';
  	  return html;
    }
    this.addItemsmyLetter = function(number, lastIndex){
  	     if(lastIndex%number!=0){
	    		$('.infinite-scroll-preloader').hide();
	    		return;
  	    }  
  	     $('.infinite-scroll-preloader').show();
	      	$.ajax({
	    		type:'GET',  
	 			url:"${ctx}/wechat/letter/data",
	 			data:"page="+letter.page+"&pageSize="+letter.pageSize,
	 			success:function(mydata){
	 				 // 生成新条目的HTML
			        var html = '';
	 				var item='';
			        var list=mydata.result;
			    	  if(list.length>0){
			    		  var html="";
			    		  var tempTime='';
			    		  if(letter.page===1){
			    			  $(".list-container").html("");
			    		  }
				    	  for (var i = 0; i < list.length; i++) {
				    		  var obj=list[i];
				    		 
				    		  if((tempTime!='' && tempTime!=obj.shootingTimeF )|| (i+1)==list.length){
				    			  html+="<div><p  class=\"letter-time\"> "+obj.shootingTimeF+"</p></div>";
				    		  }
				    		  if(obj.role==0){
				    			  html+= letter.getHtmlFa(obj);
					    	  }else{
					    		  html+= letter.getHtmlShou(obj);
					    	  }
				    		    tempTime=obj.shootingTimeF;
				    		  $(".list-container").prepend('<li>'+html+'</li>'); //html()返回#XX的内容html
				    		  html="";
				    	  }
				    	  //if(pageSize==list.length)
					        	 letter.page =letter.page+1;
				    	  $('.infinite-scroll-preloader').hide();
			    	  }else{
			    		  if(letter.page===1){
			    	    		 html+='<div class="sun-no-data" style="display:block;"><div class="content-padded">';
				    	    	 html+=' <img alt="" class="sun-no-data-img" src="${ctx }/static/images/sun/sun-no-data.png">';
				    	    	 html+='	  <p>抱歉，您还没有简信</p>';
				    	    	 html+='	</div></div>';
				    	    	 $(".page").html(html);
			    	    	 }
			    	  }
			       
			       
	 			}
	 		});
    }
    
}   
var letter = new myLetter();

letter.init();
letter.addItemsmyLetter(letter.pageSize, 0);
	   
   }
      
});
$.init();
</script> 
