<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
   	 <script>
     </script>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav">商户账户流水记录</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="buttondiv"><button type="button" class="btn btn-primary" onclick="goToUrl('${ctx}/console/admin/caiwu/accountList')">返回</button> </div>
		<div class="tablelist">
		 <form method="post" id="adminFrom">
			<table class="table">
				<tr>
				<th colspan="4" class="top_th"><a href="#" class="tongji"><em>商户账户流水记录</em><span></span></a></th>
				</tr>
				<tr>
					<th>操作</th><th>操作金额</th><th>操作前金额</th><th>操作时间</th>
				</tr>
				<c:forEach items="${recordlist}" var="record" varStatus="status">
				<tr>
						<td>
							${record.title}
						</td>
						<td>
							<c:if test="${empty record.moneyStr}">- ${record.money}</c:if>${record.moneyStr}
						</td>
						<td>
							${record.totalMoney}
						</td>
						<td>
							<fmt:formatDate value="${record.createTime}" pattern="yyyy.MM.dd HH:mm"/>
						</td>
						
				</tr>
				</c:forEach>
			</table>
			
			<tags:pagination page="${page}" paginationSize="20"/>
		</form>
		</div>
	</div>
 </body>
</html>
