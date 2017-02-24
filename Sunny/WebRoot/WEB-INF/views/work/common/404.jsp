<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${config.siteName}</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
  </head>
  <body>
    <div class="page-group">
        <div class="page">
            <!-- 标题栏 -->
            <header class="bar bar-nav">
              <a class="icon icon-left pull-left" href="javascript:void(0);"></a>
              <h1 class="title">服务器异常</h1>
            </header>
            <!-- 内容 -->
            <div class="content">
                <div class="content-block">
                    <div class="card-mobile">
                   <center>
			               抱歉！服务器当前发生异常情况，请您稍后再试...<br /> 
			               [<a href="javascript:history.back()">返回</a>]具体原因：
			  <%--                ${exception }
			               ${exception.localizedMessage }
			               
				<% Exception ex = (Exception)request.getAttribute("exception"); %> 
					<H2>Exception: <%= ex.getMessage();%></H2> 
					<P/>  --%>
				
				</center>
				<%-- <%
					exception.printStackTrace();
				%> --%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type='text/javascript' src='https://g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='https://g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='https://g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
  </body>
</html>
