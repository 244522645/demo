<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>给点儿阳光</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	<%@include file="/WEB-INF/views/work/common/static.jsp"%>
	<link rel="stylesheet" href="${ctx }/static/letter/css/me.css?t=new Date()">
</head>
<body>
	<div class="page-group">
		<div class="page page-current" id="me_card">
			<header class="bar bar-nav">
				<a class="icon icon-left pull-left back " href="#"></a>
				<h1 class='title'>我的阳光卡</h1>
				<a  style="font-size: 0.8rem;" class="icon  pull-right open-panel" data-panel='#panel-shuoming' id="open_shuoming">使用帮助</a>
			</header>
			<div class="content">
				<ul class="gdyg_card">
					<div class="gdyg_card_list">
					</div>
					<!-- 加载提示符 -->
		            <div class="infinite-scroll-preloader card_list">
		              <div class="preloader"></div>
		            </div>
				</ul>
				  <div class="card_empty" style="
				  		display:none;
					    margin: 0 auto;
					    margin-top: 40%;
					    text-align: center;
					    color:#666;
					">
					  <p>╮(╯▽╰)╭</p>
					   <p>此地空空如也!</p>
					   <div style="
					    /* position: absolute; */
					    margin: 0 auto;
					    margin-top: 40%;
					    text-align: center;
					">
					
					   <a class=" open-panel" data-panel='#panel-shuoming' id="open_shuoming">如何获得阳光卡？</a>
					  
					  </div>
					  </div>
					 
			</div>
		</div>
		<div class="gdyg-panel panel panel-right panel-reveal theme-dark" id='panel-shuoming'>
			<header class="bar bar-nav">
			  <a class="icon icon-left  pull-left close-panel" ></a>
			  <h1 class="title">阳光卡详细</h1>
			</header>
			<div class="content">
			  <div class="content-block-title">使用说明：</div>
			  <div class="list-block">
			    <ul>
			      <li class="item-content">
			        <div class="item-inner" style="font-size: .7rem">
			      		 1、 阳光卡是《给点儿阳光》推出的通用卡<br/><br/>
			      		 2、阳光卡可用来制作日出明信片，也可以通过简信送给其他人<br/><br/>
			      		 3、可通过“我要代言”活动领取阳光卡<br/><br/>
			      		 4、使用方式：选择一张日出照片，点击进行明信片制作，最后支付环节选择“阳光卡支付”即可
			        </div>
			      </li>
			    </ul>
			  </div>
			  <div class="content-block-title">使用明细：</div>
			  <div class="list-block">
			    <ul>
			     <li class="item-content item-link">
			      	<div class="item-media"><i class="icon icon-cart"></i></div>
			        <div class="item-inner">
		         	 <div class="item-title">
		         	 	 <a href="#" class="open-panel" data-panel="#panel-yishiyong" id="card_yishiyong">已使用的阳光卡明细</a>
		         	 </div>
			        </div>
			      </li>
			      <li class="item-content item-link">
			      	<div class="item-media"><i class="icon icon-app"></i></div>
			        <div class="item-inner">
			          <div class="item-title">
			          	<a href="#" class="open-panel" data-panel="#panel-yishiyong" id="card_yisongren">已送人的阳光卡明细</a>
			          </div>
			        </div>
			      </li>
			    </ul>
			  </div>
			</div>
			
		</div>
		<div class="gdyg-panel panel panel-right panel-reveal theme-dark" id='panel-yishiyong'>
			<header class="bar bar-nav">
			  <a class="icon icon-left  pull-left close-panel" ></a>
			  <h1 class="title mingxi">阳光卡详细</h1>
			</header>
			<div class="content" >
				 <div class="list-block">
				    <ul id="mingxi_val">
				    </ul>
				 </div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
    <%@include file="/WEB-INF/views/work/wechat/me/routeJs.jsp" %>
</body>
</html>
