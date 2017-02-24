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
		<div class="top_subnav">商户账户管理</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="title_h2">
			搜索
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
		<form action="${ctx}/console/admin/caiwu/accountList" >
			<div class="filed fl">
				<label>查询</label>
				<input name="search" value="${page.search}" type="text" placeholder="输入商铺名、联系人、手机号、银行账户" size="45">
			</div>
			<div class="filed fl">
				<button class="btn btn-primary" type="submit">查询</button>
				<button  class="btn btn-primary" type="button" onclick="goToUrl('${ctx}/console/admin/caiwu/recordsList')">全部打款记录</button>
			</div>
		</form>
		<div class="tablelist">
		 <form method="post" id="adminFrom">
			<table class="table">
				<tr>
				<th colspan="9" class="top_th"><a href="#" class="tongji"><em>商户账户管理</em><span></span></a></th>
				</tr>
				<tr>
					<th>商铺名</th><th>地址</th><th>联系人</th><th>手机号</th><th>支付宝账号</th><th>银行账号</th><th>账户总金额</th><th>可提现金额</th><th>打款记录</th>
				</tr>
				<c:forEach items="${page.result}" var="ba" varStatus="status">
				<tr>
						<td>
							${ba.business.name }
						</td>
						<td>
							${ba.business.address }
						</td>
						<td>
							${ba.business.contactPerson }
						</td>
						<td>
							${ba.business.phone }
						</td>
<!-- 						<td > -->
<!-- 							${ba.openId } -->
<!-- 						</td> -->
						<td>
							<c:if test="${!empty ba.alipayId}">
								${ba.alipayId }
								<a href="${ctx}/console/admin/caiwu/editAccount/${ba.id }"><i class="ui-icon ui-icon-pencil"></i></a>
							</c:if>
							<c:if test="${empty ba.alipayId}">
								<a href="${ctx}/console/admin/caiwu/editAccount/${ba.id }"><i class="ui-icon ui-icon-circle-plus"></i></a>
							</c:if>
						</td>
						<td>
							<c:if test="${!empty ba.banks}">
									${ba.banks.cardNo}-${ba.banks.userName}-${ba.banks.bankName}<br>
								<a href="${ctx}/console/admin/caiwu/editAccount/${ba.id }"><i class="ui-icon ui-icon-pencil"></i></a>
							</c:if>
							<c:if test="${empty ba.banks}">
								<a href="${ctx}/console/admin/caiwu/editAccount/${ba.id}"><i class="ui-icon ui-icon-circle-plus"></i></a>
							</c:if>
						</td>
						<td>
							${ba.money }
						</td>
						<td>
							${ba.totalMoney }
						</td>
						<td>
							<a href="${ctx}/console/admin/caiwu/addRecord?aid=${ba.id }" title="添加记录"><i
								class="ui-icon ui-icon-plusthick"></i>
							</a>
							<a href="${ctx}/console/admin/caiwu/recordsList?bid=${ba.businessId }" title="查看记录"><i
								class="ui-icon ui-icon-search"></i>
							</a>
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
