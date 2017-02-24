<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
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
  <div class="page-group">
      <div class="page">
        <header class="bar bar-nav">
          <h1 class="title">个人中心</h1>
        </header>
        <div class="content">
        	<style type="text/css">
        	.sunb-count{
        	    width: 60px;
			    border-radius: 50%;
			    border: 3px solid rgba(255, 255, 255, 0.3);
        	}
        	</style>
          	<div class="user-header-banner">
		        <div class="banner" style="height:10rem;background-image: url('${ctx}/static/images/sun/002.jpg')">
		            <div class="banner-content bg" style="top:3.5rem;">
	                     <img alt="" src="${ctx }/static/images/sun/circle.png" style="width:6em;">
		                 <p class="banner-text">阳光币</p>
		            </div>
		            <div class="banner-content num"  style="top:5.2rem;">
		                   <span style="font-size:1.5em;">2</span> 
		                   <span style="font-size:0.5em;">个</span>
		            </div>
	           </div>
	        </div>
            <div class="body" style="margin-top: 0;">
               <div class="list-block contacts-block" style="margin:0">
				   <div class="list-group">
				      <ul class="list-container">
				        <li>
				            <div class="item-inner">
				              <div class="item-subtitle">阳光币+1</div>
				             <!--  <div class="item-text">此处是文本内容...</div> -->
				              <div class="item-title-row" style="text-align:right;">
				                <div class="item-title">17:14</div>
				                <div class="item-after item-text">上传一张“青岛”照片，审核通过</div>
				              </div>
				            </div>
				        </li>
				      </ul>
				    </div>
				</div>
                         <!-- 加载提示符 -->
		         <div class="infinite-scroll-preloader">
		             <div class="preloader"></div>
		         </div>
 <script>
    $(document).on("pageInit", function() {
       $(document).on('refresh', '.pull-to-refresh-content',function(e) {
        // 模拟2s的加载过程
        setTimeout(function() {
        	 lastIndex = 0;
        	 page=1;
             addItems(itemsPerLoad, lastIndex);
            // 加载完毕需要重置
            $.pullToRefreshDone('.pull-to-refresh-content');
        }, 2000);
   		 }); 
		   	 
		      //多个标签页下的无限滚动
		      var loading = false;
		      //page
		      var page = 1;
		      // 每次加载添加多少条目
		      var itemsPerLoad = 10;
		      // 最多可加载的条目
		      var maxItems = 10000;
		      lastIndex = $('.list-container li').length;
		      function addItems(number, lastIndex) {
		    	 if(lastIndex%10!=0){
		    		$('.infinite-scroll-preloader').hide();
		    		return;
		    	} 
		    	
	    	    $.ajax({
		 			url:"${ctx}/wechat/my/mySunB/list",
		 			data:"page="+page,
		 			success:function(mydata){
		 				var pot = mydata.result;
		 				 // 生成新条目的HTML
				        var html = '';

				        for (var i = 0; i < pot.length; i++) {

				        	html += "<li>";
			        		html += "    <div class='item-inner'>";
			        		html += "<div class='item-subtitle'>阳光币+1</div>";
				            html += "<div class='item-title-row' style='text-align:right;'>";
			             	html += "<div class='item-title'>"+new Date(pot[i].createTime).Format("yyyy-MM-dd")+"</div>";
			             	html += "        <div class='item-after item-text'>"+pot[i].message+"</div>";
			             	html += "      </div>";
		             		html += "    </div>";
	             			html += "</li>";
	             			
				        }
				        // 添加新条目
				        if(lastIndex == 0){
				        	  $('.list-container').html(html);
				        	  page =2;
				        }else{
				        	  $('.list-container').append(html);
				        	  page =page+1;
				        }
				        $('.infinite-scroll-preloader').hide();
		 			}
		 		});
		      }
		      //预先加载20条
		      addItems(itemsPerLoad, 0);
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
		              addItems(itemsPerLoad, lastIndex);
		              //容器发生改变,如果是js滚动，需要刷新滚动
		              $.refreshScroller();
		          }, 1000);
		      });
    	});
   </script>		                 
		        </div>      			   
           </div>
       </div>
   </div>
  </body>

  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
  <script type="text/javascript">
  $.init()
  </script>
</html>