<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
 <body>
	<div class="bodyMain">
		<div class="top_subnav">商户账户管理 ＞ 芸备胎提现 </div>
		<div class="title_h2">
			添加芸备胎提现记录
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
        <div >
        	<form action="${ctx}/console/admin/caiwu/saveTixian" method="post" id="myform">
				<table class="fromtable">
					<tr>
						<th class="top_th"><a href="#" class="tongji"><em>添加芸备胎提现记录</em><span></span></a></th>
					</tr>
					<tr >
						<td>
							<table class="contertable">
									<Tr>
										<td >
											提现金额：
										</td>
										<td>
											<input name="money"  type="number" id="money" size="50" value="${record.money}">
										</td>
									</Tr>
									<Tr>
										<td>
											&nbsp;&nbsp; 手续费：
										</td>
										<td>
											<input name="handerMoney"   type="text" id="handerMoney" size="50" value="${record.money}">
										</td>
									</Tr>
									<Tr>
										<td>
											&nbsp;&nbsp;流水号：
										</td>
										<td>
											<input name="pingppId" type="text" id="pingppId" size="50" value="${record.pingppId}">
										</td>
									</Tr>
									
								</table>
							<div class="buttondiv"><button type="button" class="btn btn-primary" onclick="goToUrl('${ctx}/console/admin/caiwu/booksList')">返回</button>&nbsp;&nbsp;<button class="btn btn-primary" type="submit">提交</button> </div>
						</td>
					</tr>
				</table>
			</form>
        </div>
	</div>
 </body>
 <script type="text/javascript">
 
 </script>
</html>
