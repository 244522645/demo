<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
 <form method="post" id="adminFrom">
	<table class="table">
		<thead>
			<tr>
			<th colspan="${tableHeader.size() }" class="top_th"><a href="#" class="tongji"><em>报表查询</em><span></span></a></th>
			</tr>
			
			<tr>
				<c:forEach items="${tableHeader }" var="item" varStatus="status">
					<th>
						<tags:sort name="${item.value}" sort="${item.key}" id="${item.key}"/>
					</th>
				</c:forEach>
			</tr>
		</thead>
		<c:forEach items="${page.result}" var="entity" varStatus="status">
		<tr>
			
			<c:forEach items="${tableHeader }" var="item" varStatus="statuss">
				<td>
				${entity.get(item.key)} 
				</td>
			</c:forEach>
			
		</tr>
		</c:forEach>
	</table>
	<tags:pagination page="${page}" paginationSize="20"/>
</form>
<script>
<!--
sdcsdfsdf
//-->
var e;

</script>
<style>
<!--

-->
.sdc{}

</style>
