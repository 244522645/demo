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
		<div class="top_subnav">商户账户管理 ＞ 银行账号管理</div>
		<p class="mylinep" style="margin-top:0;"></p>
		
		<div class="tablelist">
		<div class="filed fl">
				<label>商户信息：</label>
				店名：${baccount.business.name}- 联系人：${baccount.business.contactPerson}- 手机号：${baccount.business.phone}
			</div>
		 <form method="post" id="adminFrom">
			<table class="table">
				<tr>
				<th colspan="9" class="top_th"><a href="#" class="tongji"><em>银行账号管理</em><span></span></a></th>
				</tr>
				<tr>
					<th>卡号</th><th>用户名</th><th>银行</th><th>开户行</th><th>创建时间</th><th>更新时间</th><th>添加打款记录</th>
				</tr>
				<c:forEach items="${baccount.banks}" var="bank" varStatus="status">
				<tr>
						<td>
							<input name="bank" value=""type="text"  size="50">
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
								<c:forEach items="${ba.banks}" var="bank" varStatus="status">
									${bank.cardNo}-${bank.userName}-${bank.bankName}<br>
								</c:forEach>
								<a href="${ctx}/console/admin/caiwu/addAli/${ba.id }"><i class="ui-icon ui-icon-pencil"></i></a>
							</c:if>
							<c:if test="${empty ba.banks}">
								<a href="${ctx}/console/admin/caiwu/addBank/${ba.id}"><i class="ui-icon ui-icon-circle-plus"></i></a>
							</c:if>
						</td>
						<td>
							${ba.money }
						</td>
						<td>
							${ba.totalMoney }
						</td>
						<td>
							<a href="${ctx}/console/admin/caiwu/addBook/${ba.id }"><i
								class="tdico editico"></i>
							</a>
						</td>
				</tr>
				</c:forEach>
			</table>
			
		</form>
		</div>
	</div>
 </body>
</html>
