<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<!DOCTYPE html>
<html>
 <head>
 	<script>
 		$().ready(function(){
    	 	$("#menuUl").children().each(function(e){
    	 		if(e==0){
    	 			window.open($(this).children().attr("href"),"frameMain");
    	 		}
    	 	});
    	 	<c:if test="${herf ne null}">
    	 	window.open("${herf}","frameMain");
    	 	</c:if>
    	 });
 	</script>
 	 <script>
    	 $().ready(function(){
    	 	$(window.parent.document).find("#framesetMainId").attr("cols","210,*");
    	 });
    </script>
 </head>
 <body >
	<div class="side_switch" id="side_switch">
	</div>
	<div class="side_switchl" id="side_switchl">
	</div>
	<div class="left">
		<div class="left_title">${parentMenu.name}</div>
		<ul class="side " id="menuUl">
			<c:forEach items="${menu}" var="menu" varStatus="status">
				<li id="${menu.menuId}"><a onclick="setMenuSelected(this)" href="${ctx}/console/${menu.url}" target="frameMain" <c:if test="${status.index==0}">class="selected"</c:if> >${menu.name}</a></li>
			</c:forEach>
		</ul>
	</div>
	</body>
</html>
