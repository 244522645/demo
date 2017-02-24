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
    	.card{
    		margin:10px 0px 10px 0px;
    	}
    	.content-block{
    		margin-top: 10px
    	}
    	.textshow{
    	text-shadow: 2px 2px 2px #000;
  		}
		 .bar .icon-left-mingren{
		    color: #B31B26;
		    font-size: 0.8rem;
		}
/*---------------------------------------首页---------------------------------------*/
.content-block{
	margin: 0;
}
.page, .page-group{
	background: #EFEFEF;
}
.content-block{
	padding: 0;
}
.bar{
	height: 2.85rem;
}
.searchbar{
	height: 2.85rem;
	padding: 0.55rem 0rem;
	border-bottom:0.11rem solid #dcdcdc ;
	background: #efefef;
	padding-left: 4%;
	padding-right: 4%;
}
.searchbar .search-input input{
	height: 1.75rem;
	border: 0;
}
.button.button-fill{
	height: 1.75rem;
	background: #FFFFFF;
	line-height: 1.75rem;
}
.yvdin img{
	line-height: 1.75rem;
	width: 1.07rem;
	height: 1.02rem;
}
.yvdin{
	float: left;
	    padding-top: 0.25rem;
}
.button.button-fill{
	color: #989898;
}

.content{
	float: left;
}
.swp{
	height: 4.9rem;
	padding: 0.65rem;
	overflow: hidden;
	    margin-bottom: 0.5rem;	background: #FFFFFF;
}
.swiper-container {
    width: 100%;
    height: 3.6rem;
}  
p{
	margin: 0;
}
.Title{
	color: #d30000;
	font-family: "微软雅黑";
	font-size: 0.85rem;
}
.body{
	color: #959595;
	font-size: 0.7rem;
	font-family: "微软雅黑";
	padding: 0.2rem;
}
.tabs{

	margin-top: 0.5rem;
	margin-bottom: 0.5rem;
}
.list{
	width: 100%;
	height: auto;
}
.list img{
	width: 100%;
}
.ThatDay{
	width: 50%;
	margin-top: -3rem;
	color:#FFFFFF ;
	margin-left: 0.6rem;
	font-size: 0.85rem;
	font-family: "微软雅黑";

}
.address{
	width: 50%;
	color:#FFFFFF;
	margin-left: 0.6rem;
	font-family: "微软雅黑";
	font-size: 0.8rem;
}
.data{
	margin-top: -1rem;
	width: 25%;
	color:#FFFFFF;
	font-family: "微软雅黑";
	font-size: 0.6rem;
	position: absolute;
	z-index: 1;
	right:4%
}
.swiper-container{
	padding: 0;
}
		
    </style>
   
  </head>
  <body>
    <div class="page-group">
        <div class="page v2" id='index'>
         <c:if test="${not empty userInfo.wechatHeadUrl}">
    	<style type="text/css">
		   	.icon-me-head{
				    width: 1.8rem;
				  height: 1.8rem;
				  margin: 0.15rem;
				  font-size: 1.2rem;
				  line-height: 2.5rem;
				  text-align: center;
				  background-color: #fff;
				  border: 1px solid #ddd;
				  border-radius: 1.25rem;
				  display: inline-block;
				  background:  url("${userInfo.wechatHeadUrl}");
			      background-size: cover;
			}
			.icon-me:before{
			    content:none; 
			}
			
    	</style>
    </c:if>
            <div class="content ">
            		<div class="searchbar row">
            		 
					    <div class="search-input col-60">
					     <a href="${ctx}/wechat/index/search">
					      <label class="icon icon-search" for="search"></label>
					      <input type="search" id='search' placeholder='一缕阳光，一份对白'/>
					  	 </a>
					    </div>
					   
					    <a class="yuding-btn button button-fill button-primary col-40 external"  href="${ctx}/wechat/v2/reserve#select-friend" >
					    	<div class="yvdin"><img src="${ctx }/static/v2.0/img/v2_yvding.png"></div>
					    	<span>预定日出</span>
					    </a>
            		</div>
            		 
            		<c:set var="search" value="" />
                	<div class="buttons-tab">
					    <a href="#tab1" class="tab-link active button">日出</a>
					    <a href="#tab2" class="tab-link button">升旗</a>
					</div>
					<div class="tabs">
						 <div id="tab1" class="tab active infinite-scroll">
						 
						   <div class="content-block">
					            <div class="swp">
					          		<div class="swiper-container">
								    	<div class="swiper-wrapper">
								    	    <c:forEach items="${blessRec}" var="entity" varStatus="status">
								        	<div class="swiper-slide">
								        	<p class="Title">${entity.title}</p>
								        	<p class="body">${entity.content}</p>
								        	</div>
								        	</c:forEach>
								   	 	</div>
								 	</div>
					        	</div>
					        </div>
		                    <div class="list-container" style="height: 100%;">
			                    <c:forEach items="${page.result}" var="entity" varStatus="status">
			                    <a class="img-mask"  href="${ctx}/wechat/index/photo/${entity.id}">
			                        <div class="mask textshow">
			                         	<p><tags:todayDateForamt dateTime="${entity.shootingTime }" /></p>
			                           
			                           <%-- <c:set value="${ fn:split(entity.province, '_') }" var="str1" />
			                           <c:choose>
			                           <c:when test="${ fn:split(entity.province, '_') > 0 }">
			                           			 <c:set value=" ${fn:substring(entity.province, 0,fn:split(entity.province, '_'))}" var="sunstr1" />
			                           			 <c:set var="string2" value="${fn:replace(sunstr1,  '省', '')}" />
			                    
    			                           		 <c:set var="string2" value="${fn:replace(sunstr1,  '市', '')}" />
			                          <p>${string2}.${entity.title}</p>
			                           </c:when>
			                           <c:otherwise>
			                           			<c:set var="string2" value="${fn:replace(str1,  '省', '')}" />
			                    
    			                           		 <c:set var="string2" value="${fn:replace(str1,  '市', '')}" />
			                          		
			                           </c:otherwise>
			                           </c:choose> --%>
			                           <p>${entity.provinceF} • ${entity.title}</p>
			                        </div>
			                         <img class="img-responsive" style="width:100%"  src="${config.imgDomainName}/${entity.imgId.folder}/${entity.imgId.name}_348x232.jpg">
			                    </a>
			                    </c:forEach>
		                    </div>
		                    <!-- 加载提示符 -->
					        <div class="infinite-scroll-preloader">
					            <div class="preloader"></div>
					        </div>
		                </div>
		                <div id="tab2" class="tab infinite-scroll">
				            <div class="card" >
		                        <div class="card-content">
		                            <div class="card-content-inner">
		                                 <h3>最特别的生日礼物--北京升旗</h3>
		                                  <p>温馨提示：每天8:00之前发布当天升旗照片，敬请关注！</p>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="list-container">
			                    <c:forEach items="${page1.result}" var="entity" varStatus="status">
				                     <a class="img-mask"  href="${ctx}/wechat/index/photo/${entity.id}">
				                        <div class="mask textshow">
				                         	<p><tags:todayDateForamt dateTime="${entity.shootingTime }" /></p>
				                            <p>${entity.provinceF} • ${entity.title}</p>
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
   <%@include file="/WEB-INF/views/work/common/pageInit.jsp" %>
   
    <script type="text/javascript" src="${ctx }/static/v2.0/js/swiper-3.4.1.jquery.min.js"></script>
  <script>
	var mySwiper = new Swiper ('.swiper-container', {
		    direction: 'vertical',
		    loop: true,
		    autoplay : 5000,    //可选选项，自动滑动
    		autoplayDisableOnInteraction : false,    //注意此参数，默认为true
		  }) 
</script>
</html>
