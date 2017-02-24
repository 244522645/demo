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
<style>

</style>
   
  </head>
  <body>
    <div class="page-group">
        <div class="page">
            <!-- 标题栏 -->
           <header class="bar bar-nav">
            	<a class="icon icon-left pull-left"></a>
               <!--  <a class="icon icon-search pull-left"></a> -->
               	<h1 class="title">我的投稿</h1>
                <%--  <button class="button button-link button-nav pull-right">
                    <a href="${ctx}/wechat/my/myList"   class="button button-fill zhizuo button-success">我要投稿</a>
                </button>--%>
            </header> 
            <!-- 内容 -->
           <div class="content pull-to-refresh-content infinite-scroll infinite-scroll-bottom">
           		 <!-- 默认的下拉刷新层 -->
			    <div class="pull-to-refresh-layer">
			        <div class="preloader"></div>
			        <div class="pull-to-refresh-arrow"></div>
			    </div>
       		  	<div class="list-block" style="margin: .5rem 0; padding: 0 .5rem;">
		             <ul class="list-container" style="position: inherit;background: transparent;">
		              <%--  <c:forEach items="${page.result}" var="entity" varStatus="status">
		                  <!-- 时间分割 -->
			            <!-- <div class="hr-divider">
			              <p class="hr-divider-content hr-divider-heading">2015/01/10</p>
			            </div> -->
			            <!-- 卡片 -->
			            <div class="card polaroid-template">
			              <div class="template-top">
			              	   <img class="img-responsive" style="width:100%"  src="${config.imgDomainName}/${entity.imgId.folder}/${entity.imgId.name}_348x232.jpg">
			              </div>
			              <div class="card-content">
			                <h3>地点：<span>${entity.address}</span>拍摄时间：<span><tags:todayDateForamt dateTime="${entity.shootingTime }" /></span></h3>
			                <p class="z-text">${entity.story}</p>
			                <p class="color-gray footer-label">投稿日期<tags:todayDateForamt dateTime="${entity.createTime }"/></p>
			              </div>
			            </div>  
		                  </c:forEach> --%>
		             </ul>
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
		 			url:"${ctx}/wechat/my/up/myListPage",
		 			data:"page="+page,
		 			success:function(mydata){
		 				var pot = mydata.result;
		 				 // 生成新条目的HTML
				        var html = '';

				        for (var i = 0; i < pot.length; i++) {

					        html += "<li><div class='card polaroid-template'>";
					        html += "  <div class='template-top'>";
					        html += "  	   <a href='javascript:void(0);' onclick='weixinshow(\"${config.imgDomainName}/"+pot[i].imgId.folder+"/"+pot[i].imgId.name+"_y1080.jpg\")'><img class='img-responsive' style='width:100%'  src='${config.imgDomainName}/"+pot[i].imgId.folder+"/"+pot[i].imgId.name+"_348x232.jpg'></a>";
					        html += "   </div>";
					        html += " <div class='card-content'>";
					        html += "    <h3>地点：<span>"+pot[i].address+"</span>拍摄时间：<span>"+pot[i].shootingTimeF+"</span></h3>";
					        html += "    <p class='z-text'>"+pot[i].story+"</p>";
					        html += "    <p class='color-gray footer-label'>投稿时间："+new Date(pot[i].createTime).Format("yyyy-MM-dd hh:mm")+" ";
					        if(pot[i].released==1){
						        html += "   <span style='float:right;'>&nbsp;审核通过&nbsp;</span><span style='float:right;' class='icon icon-check'></span>  ";
					        }
					        html += "  </p></div></div></li>  ";
				        }
				        // 添加新条目
				        if(lastIndex == 0){
				        	  $('.list-container').html(html);
				        	  page =2;
				        }else{
				        	  $('.list-container').append(html);
				        	  page =page+1;
				        }
				      
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
  </body>

  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
  <script type="text/javascript">
  $.init()
  </script>
</html>
