<%@ page language="java" pageEncoding="UTF-8" %>
<script>
	var ctx = '${pageContext.request.contextPath}';
</script>
<link rel="stylesheet" href="${ctx}/static/public/sm.min.css">
 <link rel="stylesheet" href="${ctx}/static/public/sm-extend.min.css">
 <link rel="stylesheet" href="${ctx}/static/v3/css/index.css?V=new Date()">
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