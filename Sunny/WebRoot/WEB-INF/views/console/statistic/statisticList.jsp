<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<!DOCTYPE html>
<html>
  <head>
   	 <script>
     </script>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav">报表查询</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<form action="${ctx}/console/statistic/getStatistic" method="get">
			<div class="filed fl">
				<select id="selTest" name="filter_EQS_sqlCode">
					<c:forEach items="${sqlList}" var="sql" varStatus="status">
					  	<option value ="${sql.sqlCode}" <c:if test="${param.filter_EQS_sqlCode eq sql.sqlCode}"> selected</c:if>>${sql.title}</option>
					</c:forEach>
				</select>
			</div>
			<div class="filed fl">
				<button class="btn btn-primary" type="submit">查询</button>
			</div>
			<div class="filed fr">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${ctx}/console/statistic/exportExcel?filter_EQS_sqlCode=${param.filter_EQS_sqlCode}">
					<button class="btn btn-primary" type="button">导出</button>
				</a>
			</div>
		</form>
		<div class="tablelist">
				<%@include file="/WEB-INF/views/console/statistic/statisticItem.jsp"%>
		</div>
	</div>
 </body>
</html>
