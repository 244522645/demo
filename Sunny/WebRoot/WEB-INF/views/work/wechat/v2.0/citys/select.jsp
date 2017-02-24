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
     <style type="text/css">
    .page, .page-group{
        display:block;
    }
    a{color:#333333}
    .AddressInformation .col-33{
    	margin-bottom: 0.8rem;
    }
    .details span{
    	color: #333333;
		font-family: "Microsoft YaHei";
		font-size: 0.8rem;
    }
    </style>
</head>
<body  class="v2">
	 <div class="page-group">
        <!-- 单个page ,第一个.page默认被展示-->
        <div class="page" id="selectCity">
            <!-- 标题栏 -->

            <!-- 工具栏 -->
           <nav  class="bar bar-tab bir select-city-btn">
           	确定
           </nav>
				<div class="sidebar">
					<a class="AAA" href="#AA" external>A</a><br />
					<a class="BBB" href="#BB" external>B</a><br />
					<a class="CCC" href="#CC" external>C</a><br />
					<a class="DDD" href="#DD" external>D</a><br />
					<a class="EEE" href="#EE" external>E</a><br />
					<a class="FFF" href="#FF" external>F</a><br />
					<a class="GGG" href="#GG" external>G</a><br />
					<a class="HHH" href="#HH" external>H</a><br />
					<a class="III" href="#II" external>I</a><br />
					<a class="JJJ" href="#JJ" external>J</a><br />
					<a class="KKK" href="#KK" external>K</a><br />
					<a class="LLL" href="#LL" external>L</a><br />
					<a class="MMM" href="#MM" external>M</a><br />
					<a class="NNN" href="#NN" external>N</a><br />
					<a class="OOO" href="#OO" external>O</a><br />
					<a class="PPP" href="#PP" external>P</a><br />
					<a class="QQQ" href="#QQ" external>Q</a><br />
					<a class="RRR" href="#RR" external>R</a><br />
					<a class="SSS" href="#SS" external>S</a><br />
					<a class="TTT" href="#TT" external>T</a><br />
					<a class="UUU" href="#UU" external>U</a><br />
					<a class="VVV" href="#VV" external>V</a><br />
					<a class="WWW" href="#WW" external>W</a><br />
					<a class="XXX" href="#XX" external>X</a><br />
					<a class="YYY" href="#YY" external>Y</a><br />
					<a class="ZZZ" href="#ZZ" external>Z</a><br />
				</div>
            <!-- 这里是页面内容区 -->
            <div class="content">
				<div class="TopWriting1">
					为了避免天气原因影响拍摄，您可为朋友预约5个城市的阳光，朋友可在当天任选一张下载高清照片
				</div>
				<c:set var="abc"   value="#"/>
				 <c:forEach items="${citys}" var="entity" varStatus="status">
                   	<div id="${entity.abc }${entity.abc }" class="external">
	                   
	                   	<c:if test="${abc != entity.abc}">
	                   	<p class="Letter">${entity.abc }</p>
	                   	</c:if>
						<p class="ShootingAddress" attraction="${entity.attraction }" city="${entity.city }" province="${entity.province }">${entity.city } [ ${entity.province } · ${entity.attraction } ]</p>
					</div>
						<c:set var="abc"   value="${entity.abc}"/>
                 </c:forEach>
				
            </div>
        </div> 
   </div>
    <!-- popup, panel 等放在这里 -->
    <div class="panel-overlay"></div>
    <!-- Left Panel with Reveal effect -->
	<script type="text/javascript" src="${ctx}/static/v2.0/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/v2.0/js/sm.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/v2.0/js/AddressSelection.js"></script>
    <script>
       //选择 城市
	   var SelectCity = {  
				citys: new Array(),
	    		that:this,
	            init: function(){  
	            	that=this;
	            	that.citys=new Array(),
	            	$(".ShootingAddress").on("click",function(){
	            		if($(this).hasClass("Selected")){
	            			$(this).removeClass("Selected")
	            		}else{
	            			if($(".Selected").length<5){
	            				$(this).addClass("Selected")
	            			}
	            		}
	            	});
	        	    $(".select-city-btn").on("click",function(){
	        	    		$(".Selected").each(function(i){
	        	    			var cit = $(this).attr("city");
	        	    			that.addCity(cit);
	        	    		});
	        	    		
	        	    		
	            	});
	            },  
	            addCity: function(city){  
	            	that.citys.push(city);
	            },
	            removeCity: function(city){  
	            } 
	    		
	        };  
	    $(function(){
	    	SelectCity.init();
	    });
    </script>
    
</body>
</html>