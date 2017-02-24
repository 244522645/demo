<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="id" type="java.lang.String" required="false"%>

<%@ attribute name="sort" type="java.lang.String" required="true"%>
<%@ attribute name="name" type="java.lang.String" required="true"%>

<script>
	$().ready(function(){
		$("#sortList${id}").click(function(){
			if('${order}'=='desc')
				location.href = "?order=asc&sortType=${sort}&${searchParams}";
			else
				location.href = "?order=desc&sortType=${sort}&${searchParams}";
		});
	});
		
</script>

<span style="cursor: pointer;" id="sortList${id}">
${name}&nbsp;
<c:choose>
<c:when test="${sort eq  sortType}">
<img width="5px;" style="vertical-align: middle;" <c:if test="${order=='desc'}">src="${pageContext.request.contextPath}/static/images/sort_ico_r.png"</c:if> <c:if test="${order=='asc'||order==''}">src="${pageContext.request.contextPath}/static/images/sort_ico_l.png"</c:if>>
</c:when>
<c:otherwise>
<img  style="vertical-align: middle;" src="${pageContext.request.contextPath}/static/images/sort_ico.jpg">
</c:otherwise>
</c:choose>
</span>


