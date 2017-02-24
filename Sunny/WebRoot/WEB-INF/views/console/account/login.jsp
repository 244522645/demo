<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="siteName">${config.siteName}</c:set>
<!DOCTYPE html>
<html>
  <head>
  	<title>${config.siteName}</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   <script src="${ctx}/static/js/jquery-1.10.2.js"></script>
   <link href="${ctx}/static/css/login_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function(){
		$("#login_sub").click(function(){
			$("#submitForm").submit();
		});
	});
	
	/*回车事件*/
	function EnterPress(e){ //传入 event 
		var e = e || window.event; 
		if(e.keyCode == 13){ 
			$("#submitForm").submit();
		} 
	} 
</script>
  </head>
  
  <body>
     <div id="login_center">
		<div id="login_area">
			<div id="login_box">
				<div style="position:absolute;top:10px;left:20px;width: 370px;height: 320px;background:url(${ctx}/static/images/login/logo.png)"></div>
				<div id="login_form">
					<form id="submitForm" action="${ctx}/console/login" method="post">
						<div id="login_tip">
							<span id="login_err" class="sty_txt2">
							<c:if test="${!empty message}">${message}</c:if>
							</span>
						</div>
						<div>
							 用户名：<input type="text" id="username" name="name" class="username" value="${username}">
						</div>
						<div>
							密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" class="pwd"  id="password" name="password"  onkeypress="EnterPress(event)" onkeydown="EnterPress()">
						</div>
						<div style="  margin-top: 10px;margin-left: 42px;">
							<input type="checkbox" class=""  id="" name="rememberMe" > 记住我
						</div>
						<div id="btn_area">
							<input type="button" class="login_btn" id="login_sub"  value="登  录">
							<input type="reset" class="login_btn" id="login_ret" value="重 置">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
