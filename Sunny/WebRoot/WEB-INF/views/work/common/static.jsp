<%@ page language="java" pageEncoding="UTF-8" %>
<script>
	var ctx = '${pageContext.request.contextPath}';
</script>
<%--  
<link rel="stylesheet" href="https://g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
 <link rel="stylesheet" href="https://g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
 <link rel="stylesheet" href="${ctx}/static/css/sun/custom.css">
 
 <script type='text/javascript' src='https://g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
 <script type='text/javascript' src='https://g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
 <script type='text/javascript' src='https://g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
 <script type="text/javascript" src="${ctx}/static/js/sun/base.js"></script> 
 
  <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script> 
    --%>
<link rel="stylesheet" href="${ctx}/static/public/sm.min.css">
 <link rel="stylesheet" href="${ctx}/static/public/sm-extend.min.css">
 <link rel="stylesheet" href="${ctx}/static/css/sun/custom.css?V=4.0">
 <link rel="stylesheet" href="${ctx}/static/css/sun/other.css?V=4.0">
  <link rel="stylesheet" href="${ctx}/static/v2.0/css/index.css?V=4.5" />
  <link rel="stylesheet" href="${ctx}/static/v3/css/index.css?V=4.4" />
<%--  <link rel="stylesheet" href="${ctx}/static/my/css/mycss.css?V=4.0" /> --%>
  <script type='text/javascript' src='${ctx}/static/public/zepto.min.js' charset='utf-8'></script>
  <script type='text/javascript' src='${ctx}/static/public/touch.js' charset='utf-8'></script>
 <script type="text/javascript">
//朋友圈 参数
 var com_share_title="${config.siteName}",
 com_share_content="${config.description}",
 com_share_link="",
 domainName="${config.domainName}",
 com_share_imgUrl="";
 </script> 
 <script type="text/javascript" src="${ctx}/static/js/sun/base.js?V=5.7"></script> 