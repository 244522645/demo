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
          <div class="user-header-banner">
          	<c:if test="${empty fengmian }">
    	      <div class="banner" style="background-image: url('${ctx}/static/images/sun/002.jpg')">
          	</c:if>
          	<c:if test="${not empty fengmian }">
       	      <div class="banner" style="background-image: url('${config.imgDomainName}/${fengmian.imgId.folder}/${fengmian.imgId.name}_500.jpg')">
          	</c:if>
              <div class="banner-content">
                <a href="###"><img class="banner-img" src="${userInfo.wechatHeadUrl}" alt="头部图片"></a>
                <p class="banner-text">${userInfo.wechatNickname}</p>
              </div>
              <a class="feedback" href="${ctx}/wechat/my/feedback"><img class="banner-img" src="${ctx}/static/images/sun/yijian.png" style="width:2em;" alt="意见反馈"></a>
            </div>
            <div class="body" style="margin-top: 0;">
              <div class="summary-block">
               <%--  <a href="${ctx}/wechat/my/myList" class="summary-item">
                  <span class="number">${tj.send }</span>
                  <p class="description">送出</p>
                </a>
                <a href="${ctx}/wechat/my/myList" class="summary-item">
                  <span class="number">${tj.receive }</span>
                  <p class="description">收到</p>
                </a> --%>
                <%-- <a href="${ctx}/wechat/my/up" class="summary-item">
                  <span class="number">${tj.receive }</span>
                  <p class="description">投稿</p>
                </a> --%>
                <a href="${ctx}/wechat/my/mySunB" class="summary-item">
                  <span class="number">${tj.sunb }</span>
                  <p class="description">阳光币</p>
                </a>
                <a href="${ctx}/wechat/my/up" class="summary-item">
                  <span class="number">${tj.tougao }</span>
                  <p class="description">投稿</p>
                </a>
              </div>
            <%--   <a class="button-block" href="${ctx}/wechat/my/up">我的投稿</a> --%>
              <!-- <div class="img-group">
                <div class="horizontal">
                  <a class="link-img" href="图片详情.html"><img class="img-responsive" src="img/item/001.jpg" alt="图片"></a>
                  <a class="link-img" href="图片详情.html"><img class="img-responsive" src="img/item/001.jpg" alt="图片"></a>
                  <a class="link-img" href="图片详情.html"><img class="img-responsive" src="img/item/001.jpg" alt="图片"></a>
                </div>
                <div class="horizontal">
                  <a class="link-img" href="图片详情.html"><img class="img-responsive" src="img/item/001.jpg" alt="图片"></a>
                  <a class="link-img" href="图片详情.html"><img class="img-responsive" src="img/item/001.jpg" alt="图片"></a>
                  <a class="link-img" href="图片详情.html"><img class="img-responsive" src="img/item/001.jpg" alt="图片"></a>
                </div>
                <a class="footer-description" href="图片列表.html">我的投稿</a>
              </div> -->
            </div>
          </div>
          
         <%--  <a class="button-block" href="${ctx}/wechat/my/up">我要投稿</a> --%>
          
          	<div class="buttons-tab" style="margin-top:1em;">
				    <a href="#tab1" class="tab-link active button">收到</a>
				    <a href="#tab2" class="tab-link button">送出</a>
				</div>
				 <div class="content-block">
                   <div class="tabs">
                       <!-- 统一模板样式_标签页1 -->
                       <div id="tab1" class="infinite-scroll active tab">
                         <div class="tabTemp">
                            <c:forEach items="${page.result}" var="entity" varStatus="status">
                           <div class="card polaroid-template <c:if test="${entity.status eq 100}">grey</c:if>" onclick="window.location.href='${ctx}/wechat/order/sendeeinfo?orderId=${entity.id}'">
                             <div class="template-top <c:if test="${entity.status eq 100}">Iread</c:if>">
                          	<img alt="" width="100%" src="${config.imgDomainName}/${entity.cardImage.folder}/${entity.cardImage.name}_500.jpg">
                             </div>
                             <div class="card-content">
                               <h3>${entity.title }</h3>
                               <p class="z-text">${entity.message}</p>
                               <p class="color-gray footer-label f-6"><tags:todayDateForamt dateTime="${entity.createTime }"/></p>
                             </div>
                           </div>
                           </c:forEach>
                           </div>
                            <div class="infinite-scroll-preloader">
				            <div class="preloader"></div>
				        </div>
                       </div>
                       <!-- 统一模板样式_标签页2 -->
                       <div id="tab2" class="infinite-scroll tab">
	                       <div class="tabTemp">
	                            <c:forEach items="${page1.result}" var="entity" varStatus="status">
	                           <div class="card polaroid-template <c:if test="${entity.status eq 100}">grey</c:if>" onclick="window.location.href='${ctx}/wechat/order/sendeeinfo?orderId=${entity.id}'">
	                             <div class="template-top <c:if test="${entity.status eq 100}">read</c:if>">
	                               <img alt="" width="100%"  src="${config.imgDomainName}/${entity.cardImage.folder}/${entity.cardImage.name}_500.jpg">
	                             </div>
	                             <div class="card-content">
	                               <h3>祝<span>${entity.sendeeName}</span></h3>
	                               <p class="z-text">${entity.message}</p>
	                               <p class="color-gray footer-label f-6"><tags:todayDateForamt dateTime="${entity.createTime }"/></p>
	                             </div>
	                           </div>
	                           </c:forEach>
                           </div>
                           <!-- 时间分割 -->
                      <!--      <p class="color-gray">2015/01/10</p> -->
                            <!-- 加载提示符 -->
				        <div class="infinite-scroll-preloader">
				            <div class="preloader"></div>
				        </div>
                       </div>
                    </div>
                </div>
        <style>
    	.card{
    		margin:10px 0px 10px 0px;
    	}
    	.content-block{
    		margin-top: 10px
    	}
    	.f-6{
    	font-size: 0.6rem;
    	text-align: right;
    	}
    	.bar .zhizuo{
    	top: 0rem;
		}
		.summary-block > .summary-item{
		    width: 50%;
		}
    </style>
        </div>
      </div>
  </body>
  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
   <script>
	   $(document).on("pageInit", function() {
		   	  if($('.tabTemp').eq(0).find('.polaroid-template').length<10)
		   		 $('.infinite-scroll-preloader').eq(0).hide();
		   	  if($('.tabTemp').eq(1).find('.polaroid-template').length<10)
		   		 $('.infinite-scroll-preloader').eq(1).hide();
		      //多个标签页下的无限滚动
		      var loading = false;
		      //page
		      var page = 1;
		      // 每次加载添加多少条目
		      var itemsPerLoad = 10;
		      // 最多可加载的条目
		      var maxItems = 100;
		      var lastIndex = $('.tabTemp .polaroid-template').length;
		      function addItems(number, lastIndex,tabIndex,tab) {
		    	if(lastIndex%10!=0){
		    		$('.infinite-scroll-preloader').eq(tabIndex).hide();
		    		return;
		    	}
		    	page =lastIndex/number+1;
	    	    $.ajax({
		 			url:"${ctx}/wechat/my/myListPage",
		 			data:"subject="+tab+"&page="+(lastIndex/number+1),
		 			success:function(mydata){
		 				var pot = mydata.content;
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
				        $(".infinite-scroll.active .tabTemp").append(html);
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
		        lastIndex = $('.tab').eq(tabIndex).find('.polaroid-template').length;
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
		         	 addItems(itemsPerLoad,lastIndex,tabIndex,"收到");
		          if(tabIndex==1)
			         addItems(itemsPerLoad,lastIndex,tabIndex,"送出");
		          // 更新最后加载的序号
		          lastIndex =  $('.tabTemp').eq(tabIndex).find('.polaroid-template').length;
		          $.refreshScroller();
		        }, 1000);
		      });
		  });
		  $.init();
   </script>
</html>
