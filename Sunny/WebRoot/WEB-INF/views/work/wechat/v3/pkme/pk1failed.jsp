<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/wechat/v3/common/static.jsp"%>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>闻鸡起伍</title>
   	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
</head>
<body class="v3">
	 	 <div class="page-group">
        <!-- 单个page ,第一个.page默认被展示-->
        <div class="page">
            <!-- 标题栏 -->  

            <!-- 工具栏 -->
           

            <!-- 这里是页面内容区 -->
            <div class="content bcakcolor_l jieshoutiaoz">
            	<div class="datupian_l">
            		<img src="${ctx}/static/v3/img/dansuiel.png" />
            	</div>
				<p class="tishi_l">啊噢~挑战失败啦~</p>
				<div class="anniu_l">
					<a href="${ctx}/wechat/v3/pkpay" class="external"><div class="chongxin_l">重新挑战</div></a>
					<a href="${ctx}/wechat/v3/crow/punch/schedule" class="external"><div class="jindu_l">查看进度</div></a>
				</div>
				
			</div>
        </div>
   </div>


	  
    <div class="panel-overlay"></div>
 	<script type='text/javascript' src='${ctx}/static/public/sm.min.js' charset='utf-8'></script>
 <script type='text/javascript' src='${ctx}/static/public/sm-extend.min.js' charset='utf-8'></script>
 <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
 
    <!-- Left Panel with Reveal effect -->
   	<script>
   	$(function(){
   		$.init();
   	});
   	</script>

    <script>     
      $(document).on('click','.ruhedaka_l',function () {
        $.alert('每天坚持早起完成2.5km晨跑，并于当天早9点之前，将运动截图发送至公众号平台（可下载咕咚等运动软件记录），审核通过后，视为打卡成功。');
     });
     
    </script>
</body>
</html>