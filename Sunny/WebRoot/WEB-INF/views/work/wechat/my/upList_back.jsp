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
 #shareit {
  -webkit-user-select: none;
  display: none;
  position: absolute;
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,0.85);
  text-align: center;
  top: 0;
  left: 0;
    z-index: 9999;
}
#shareit img {
  max-width: 100%;
}
.arrow {
  position: absolute;
  right: 10%;
  top: 5%;
}
#share-text {
  margin-top: 400px;
  font-size: 1.17em;
  color:#FFFFFF;
}
a:link{
text-decoration:none;
}
a:visited{
text-decoration:none;
}
a:hover{
text-decoration:none;
}
a:active{
text-decoration:none;
}
.bar .zhizuo {
    
    top: 0rem;

}

.content-block {
    margin: 0; 
}

.content-block {
    margin: 0;
    padding:0;
    color: #6d6d72;
} 
.card-mobile > .tip-modal{
    bottom: 0em;
    width: 100%;
        position: fixed;
}
.photo-browser .bar-nav{
	display: none;
}
.photo-browser .bar-tab{
	display: none;
}
.photo-browser .bar-tab~.content{
	top:0;
}
.photo-browser .bar-nav~.content{
	bottom:0;
}
.card-mobile {
    position: initial;
 }
</style>
   
  </head>
  <body>
    <div class="page-group">
        <div class="page">
            <!-- 标题栏 -->
          <%--   <header class="bar bar-nav">
            	<a class="icon icon-left pull-left"></a>
               <!--  <a class="icon icon-search pull-left"></a> -->
              	<h1 class="title">投稿</h1>
                 <button class="button button-link button-nav pull-right">
                    <a href="${ctx}/wechat/my/myList"   class="button button-fill zhizuo button-success">我要投稿</a>
                </button>
            </header> --%>
            <!-- 内容 -->
            <div class="content ">
                	<div class="buttons-tab">
					    <a href="#tab1" class="tab-link active button">我的投稿</a>
					    <a href="#tab2" class="tab-link button">审核通过</a>
					</div>
					<div class="tabs">
						 <div id="tab1" class="tab active infinite-scroll">
		                    <div class="list-container">
			                    <c:forEach items="${page.result}" var="entity" varStatus="status">
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
			                    </c:forEach>
		                    </div>
		                    <!-- 加载提示符 -->
					        <div class="infinite-scroll-preloader">
					            <div class="preloader"></div>
					        </div>
		                </div>
		                <div id="tab2" class="tab infinite-scroll">
		                    <div class="list-container">
			                    <c:forEach items="${page1.result}" var="entity" varStatus="status">
				                     <a class="img-mask"  href="${ctx}/wechat/index/photo/${entity.id}">
				                        <div class="mask textshow">
				                         	<p><tags:todayDateForamt dateTime="${entity.shootingTime }" /></p>
				                            <p>${entity.title}</p>
				                        </div>
				                         <img  class="img-responsive" style="width:100%" src="${config.imgDomainName}/${entity.imgId.folder}/${entity.imgId.name}_348x232.jpg">
				                    </a>
			                    </c:forEach>
		                    </div>
				            <!-- 加载提示符 -->
					        <div class="infinite-scroll-preloader">
					            <div class="preloader"></div>
					        </div>
				        </div>
	              </div>
           </div>
       </div>
   </div>
  </body>

  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
    <script>
	   $(document).on("pageInit", function() {
		   	  if($('.list-container').eq(0).find('.img-mask').length<10)
		   		 $('.infinite-scroll-preloader').eq(0).hide();
		   	  if($('.list-container').eq(1).find('.img-mask').length<10)
		   		 $('.infinite-scroll-preloader').eq(1).hide();
		      //多个标签页下的无限滚动
		      var loading = false;
		      //page
		      var page = 1;
		      // 每次加载添加多少条目
		      var itemsPerLoad = 10;
		      // 最多可加载的条目
		      var maxItems = 10000;
		      var lastIndex = $('.list-container .img-mask').length;
		      function addItems(number, lastIndex,tabIndex,tab) {
		    	if(lastIndex%10!=0){
		    		$('.infinite-scroll-preloader').eq(tabIndex).hide();
		    		return;
		    	}
		    	page =lastIndex/number+1;
	    	    $.ajax({
		 			url:"${ctx}/wechat/index/photoPages",
		 			data:"subject="+tab+"&page="+(lastIndex/number+1),
		 			success:function(mydata){
		 				var pot = mydata.content;
		 				 // 生成新条目的HTML
				        var html = '';
				        for (var i = 0; i < pot.length; i++) {
				          html += "<a class='img-mask'  href='${ctx}/wechat/index/photo/"+pot[i].id+"'><div class='mask textshow'>"+
	                              "<p>"+pot[i].shootingTimeF+"</p>"+
	                              "<p>"+pot[i].title+"</p>"+
			                       "</div>"+
			                      "<img  class='img-responsive' style='width:100%'  src='"+pot[i].imgId.localPath+"_348x232.jpg'>"+
				                  "</a>";
				        }
				        // 添加新条目
				        $('.infinite-scroll.active .list-container').append(html);
		 			}
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
		          loading = false;
		          if (lastIndex >= maxItems) {
		            // 加载完毕，则注销无限加载事件，以防不必要的加载:$.detachInfiniteScroll($('.infinite-scroll').eq(tabIndex));多个无线滚动请自行根据自己代码逻辑判断注销时机
		            $.detachInfiniteScroll($('.infinite-scroll').eq(tabIndex));
		            // 删除加载提示符
		            $('.infinite-scroll-preloader').eq(tabIndex).hide();
		            return;
		          }
		          if(tabIndex==0)
		         	 addItems(itemsPerLoad,lastIndex,tabIndex,"日出");
		          if(tabIndex==1)
			         addItems(itemsPerLoad,lastIndex,tabIndex,"升旗");
		          // 更新最后加载的序号
		          lastIndex =  $('.list-container').eq(tabIndex).find('.img-mask').length;
		          
		          $.refreshScroller();
		        }, 1000);
		      });
		  });
		  $.init();
   </script>
</html>
