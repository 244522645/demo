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
		
    </style>
   
  </head>
  <body>
    <div class="page-group">
        <div class="page" id='index'>
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
            <!-- 标题栏 -->
            <header class="bar bar-nav">
              <!-- <a href="#" class="icon icon-birthday  pull-left popup-birthday"></a> -->
              <h1 class="title">
                <a href="${ctx}/wechat/index/search">
               	  <div class="searchbar sun-sreach sun-sreach-index ">
				   <!--  <a class="searchbar-cancel">取消</a> -->
				    <div class="search-input">
				      <label class="icon icon-search" for="search"></label>
				      <input type="search" id='search' readonly="readonly" placeholder='一缕阳光 ，一份祝福'/>
				    </div>
				  </div>
				  </a>
              </h1>
              <a href="${shareUrl }" class="icon icon-me icon-me-head pull-right"></a>
            </header>
            <!-- 内容 -->
            <div class="content ">
            		<c:set var="search" value="" />
                	<div class="buttons-tab">
					    <a href="#tab1" class="tab-link active button">日出</a>
					    <a href="#tab2" class="tab-link button">升旗</a>
					</div>
					<div class="tabs">
						 <div id="tab1" class="tab active infinite-scroll">
		                    <div class="card" >
		                        <div class="card-content">
		                            <div class="card-content-inner">
		                                  <h3>最特别的生日礼物--每日阳光</h3>
		                                  <p>《把你的脸迎向阳光，那就不会有阴影》--给点儿阳光
		                                	提示：每天8:00之前发布当天日出照片，敬请关注！</p>
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
  
</html>
