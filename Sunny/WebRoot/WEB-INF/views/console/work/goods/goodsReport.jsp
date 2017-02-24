<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
  </head>
 <body>
	<div class="bodyMain">
		<div class="top_subnav">商品报表</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="title_h2">
			搜索
			<div class="filed fl">
				<label>查询</label>
				<input name="search" value="${page.search}" type="text" placeholder="输入商铺名、联系人、手机号、银行账户" size="45">
				<select id="selTest">
					<c:forEach items="${sqlList}" var="sql" varStatus="status">
					  	<option value ="${sql.id}">${sql.title}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="tablelist">
			<form method="post" id="adminFrom">
				<table class="table">
					<tr>
					<th colspan="4" class="top_th"><a href="#" class="tongji"><em>商户账户流水记录</em><span></span></a></th>
					</tr>
					<tr>
						<th>操作金额</th><th>操作前可提现金额</th><th>操作流水</th><th>操作时间</th>
					</tr>
					<c:forEach items="${goodsReport}" var="entity" varStatus="status">
					<tr>
						<td>${entity.FROM_NAME }</td>
					</tr>
					</c:forEach>
				</table>
			</form>
		</div>
	</div>
 </body>
</html>