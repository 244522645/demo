<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <title>${config.siteName}</title>
    <script>
    	 $().ready(function(){
    	 	$(window.parent.document).find("#framesetMainId").attr("cols","0,*");
    	 });
    </script>
  </head>
 <body style="margin:auto;text-align:center;">
	<div style="width:1200px; margin:0 auto;">
		<br/><br/><br/>
		<h1>欢迎使用 ${config.siteName}-资源管理系统</h1>
	</div>
</body>
</html>
