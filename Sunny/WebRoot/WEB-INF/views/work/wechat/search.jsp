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
  </head>
  <body>
    <body>
		<div class="page-group">
			<div class="page">
				<!-- 标题栏 -->
				<header class="bar bar-nav">
					<a class="icon sun-icon-left pull-left "  href="${ctx}/wechat/index"></a>
					
					<!-- 搜索框 -->
						<div class="searchbar sun-sreach">
							<a class="searchbar-cancel back">取消</a>
							<div class="search-input">
								<label class="icon icon-search" for="search"></label>
								<form action="${ctx}/wechat/index/search/put" method="get">
								<input type="search" name="search" id='search' value="${search }" placeholder='请输入省份或景点名称' />
								</form>
							</div>
						</div>
					
					
				</header>
				
				<!-- 搜索内容选项 -->
				<div class="content">
					<div class="content-block">
						<h4 >热门景点</h4>
							<nav class="bar bar-tab" style="position: relative;">
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=太湖">

									<span class="tab-label" style="font-size: 0.9rem;">太湖</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=翡翠岛">

									<span class="tab-label">翡翠岛</span>

								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=黄山">

									<span class="tab-label">黄山</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=禾木">

									<span class="tab-label">禾木</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=抚远">

									<span class="tab-label">抚远</span>
								</a>
							</nav>
							
							
							
							<nav class="bar bar-tab" style="position: relative;">
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=笔架山">

									<span class="tab-label">笔架山</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=五指山">

									<span class="tab-label">五指山</span>

								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=泸沽湖">

									<span class="tab-label">泸沽湖</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=大理">

									<span class="tab-label">大理</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=汾河">

									<span class="tab-label">汾河</span>
								</a>
							</nav>
							
					</div>
					<div class="content-block">
						<h4 >热门省份</h4>
							<nav class="bar bar-tab" style="position: relative;">
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=北京">

									<span class="tab-label" style="font-size: 0.9rem;">北京</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=黑龙江">

									<span class="tab-label">黑龙江</span>

								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=河北">

									<span class="tab-label">河北</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=安徽">

									<span class="tab-label">安徽</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=江苏">

									<span class="tab-label">江苏</span>
								</a>
							</nav>
							
							
							
							<nav class="bar bar-tab" style="position: relative;">
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=山东">

									<span class="tab-label">山东</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=海南">

									<span class="tab-label">海南</span>

								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=福建">

									<span class="tab-label">福建</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=内蒙古">

									<span class="tab-label">内蒙古</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=山西">

									<span class="tab-label">山西</span>
								</a>
							</nav>
							
							
								<nav class="bar bar-tab" style="position: relative;">
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=新疆">

									<span class="tab-label">新疆</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=河南">

									<span class="tab-label">河南</span>

								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=云南">

									<span class="tab-label">云南</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=宁夏">

									<span class="tab-label">宁夏</span>
								</a>
								<a class="tab-item external" href="${ctx}/wechat/index/search/put?search=江西">

									<span class="tab-label">江西</span>
								</a>
							</nav>
					</div>
				</div>
			</div>
		</div>
  </body>
  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
    <script>
	  //多个标签页下的无限滚动
	    var loading = false;
	    //page
	    var page = 1;
	    var pagetab2 = 2;
	   $(document).on("pageInit", function() {
		   	  if($('.list-container').eq(0).find('.img-mask').length<10)
		   		 $('.infinite-scroll-preloader').eq(0).hide();
		   	  if($('.list-container').eq(1).find('.img-mask').length<10)
		   		 $('.infinite-scroll-preloader').eq(1).hide();
		      
		      // 每次加载添加多少条目
		      var itemsPerLoad = 10;
		      // 最多可加载的条目
		      var maxItems = 10000;
		      var lastIndex = $('.list-container .img-mask').length;
		      function addItems(number, lastIndex,tabIndex,tab,pageIndex) {
		    	if(lastIndex%10!=0){
		    		$('.infinite-scroll-preloader').eq(tabIndex).hide();
		    		return;
		    	}
		    	 if(tabIndex ==0){
		    		if(pageIndex==page){
			    		return;
			    	}
		    	}
		    	if(tabIndex ==1){
		    		if(pageIndex==pagetab2){
			    		return;
			    	}
		    	} 
		    	
	    	    $.ajax({
		 			url:"${ctx}/wechat/index/photoPages",
		 			data:"subject="+tab+"&page="+pageIndex,
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
				        // 添加新条目
				        $('.infinite-scroll.active .list-container').append(html);
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
		        if($(this).find('.infinite-scroll.active').attr('id') == "tab1"){
		          tabIndex = 0;
		        }
		        if($(this).find('.infinite-scroll.active').attr('id') == "tab2"){
		          tabIndex = 1;
		        }
		        lastIndex = $('.list-container').eq(tabIndex).find('.img-mask').length;
		        // 模拟1s的加载过程
		        setTimeout(function() {
		          // 重置加载flag
		          if (lastIndex >= maxItems) {
		            // 加载完毕，则注销无限加载事件，以防不必要的加载:$.detachInfiniteScroll($('.infinite-scroll').eq(tabIndex));多个无线滚动请自行根据自己代码逻辑判断注销时机
		            $.detachInfiniteScroll($('.infinite-scroll').eq(tabIndex));
		            // 删除加载提示符
		            $('.infinite-scroll-preloader').eq(tabIndex).hide();
		            return;
		          }
		          if(tabIndex==0)
		         	 addItems(itemsPerLoad,lastIndex,tabIndex,"日出",page);
		          if(tabIndex==1)
			         addItems(itemsPerLoad,lastIndex,tabIndex,"升旗",pagetab2);
		          // 更新最后加载的序号
		          lastIndex =  $('.list-container').eq(tabIndex).find('.img-mask').length;
		          
		          $.refreshScroller();
		        }, 1000);
		      });
		      
		      //获取名人生日
		      $(document).on('click','.popup-birthday', function () {
		    	  var popupHTML =   "<div class='popup'><header class='bar bar-nav'>"+
										  "<h1 id='birthday-title' class='title'></h1>"+
										  "<span class='icon-close close-popup'><a href='#' class='icon  pull-right '></a></span>"+
									"</header>"+
									"<div id='birthday-content' class='content'>"+
									"</div></div>";
				  $.popup(popupHTML);
				  $.post("${ctx}/wechat/cbirthday/day",null,function(data){
					  $("#birthday-content").html(data.content);
					  $("#birthday-title").html(data.title);
		    	  });
		      });
		      $(document).on('click','.close-popup', function () {
		    	  $.closeModal('.popup-birthday');
		      });
		  });
		  $.init();
   </script>
</html>
