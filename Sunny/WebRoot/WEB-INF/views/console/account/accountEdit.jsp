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
		<div class="top_subnav">商户账户管理 ＞  添加/修改支付宝和银行账号 </div>
		<div class="title_h2">
			  添加/修改支付宝和银行账号
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
        <div >
        	<form action="${ctx}/console/admin/caiwu/saveAccount" method="post" id="myform">
				<table class="fromtable">
					<tr>
						<th class="top_th"><a href="#" class="tongji"><em> 添加/修改支付宝和银行账号</em><span></span></a></th>
					</tr>
					<tr >
						<td>
							<table class="contertable">
									<Tr>
										<td >
											商户信息：
										</td>
										<td >
											<input name="aid" value="${baccount.id}" type="hidden">
											店名：${baccount.business.name}- 联系人：${baccount.business.contactPerson}- 手机号：${baccount.business.phone}
										</td>
									</Tr>
									<Tr>
										<td >
											支付宝：
										</td>
										<td>
											<input name="alipayId" value="${baccount.alipayId}"type="text"  size="50">
										</td>
									</Tr>
									<Tr>
										<td >
											银行卡号：
										</td>
										<td>
											<input name="id" value="${bank.id}" type="hidden">
											<input name="accountId" value="${baccount.id}" type="hidden">
											<input name="deleted" value="${bank.deleted}" type="hidden">
											<input name="createTime" value="${bank.createTime}" type="hidden">
											<input name="cardNo" value="${bank.cardNo}" type="number"  size="50">
										</td>
									</Tr>
										<td >
											用户名：
										</td>
										<td>
											<input name="userName" value="${bank.userName}"type="text"  size="50">
										</td>
									</Tr>
										<td >
											银行：
										</td>
										<td>
											<input name="bankName" value="${bank.bankName}" type="text"  size="50">
										</td>
									</Tr>
										<td >
											开户行：
										</td>
										<td>
											<input name="openingBank" value="${bank.openingBank}" type="text"  size="50">
										</td>
									</Tr>
								</table>
							<div class="buttondiv"><button type="button" class="btn btn-primary" onclick="goToUrl('${ctx}/console/admin/caiwu/accountList')">返回</button>&nbsp;&nbsp;<button class="btn btn-primary" type="submit">提交</button> </div>
						</td>
					</tr>
				</table>
			</form>
        </div>
	</div>
 </body>
</html>
