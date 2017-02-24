<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
   	 <script>
	 	
	 	 function delAll(){
     	    if(confirm("确定禁用吗？")){
     	 	     $("#adminFrom").submit();
     	 	     return true; 
     	 	 }else{
     	 	     return false; 
     	 	 } 
     	 	return false;
     	 }
     	 
     	
     </script>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav"> 阳光卡</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="title_h2">
			搜索
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
		<form action="${ctx}/console/zy/suncard" >
			<div class="filed fl">
				<label>卡号</label>
				<input name="filter_LIKES_number" value="${param.filter_LIKES_number}" type="text"  size="15">
			</div>
			
			<div class="filed fl">
				<label>卡类型</label>
				<input name="filter_LIKES_type" value="${param.filter_LIKES_type}" type="text"  size="15">
			</div>
			
			<div class="filed fl">
				<button class="btn btn-primary" type="submit">查询</button>
				<%--<button  class="btn btn-primary" type="button" onclick="goToUrl('${ctx}/console/zy/suncard/jump')">新增</button> --%>
			</div>
		</form>
		<div class="tablelist">
		 <form method="post" id="adminFrom" action="${ctx}/console/zy/suncard/batchDel">
			<table class="table">
				<tr>
				<th colspan="10" class="top_th"><a href="#" class="tongji"><em>阳光卡管理</em><span></span></a></th>
				</tr>
				<tr>
					<th style="width:30px"><input style="width:20px;" onclick="selectedCheckbox(this,'id')" type="checkbox" value=""/></th>
					
					<th>卡号</th>
                    <th>失效时间</th>
					<th>卡类型</th>
					<th>绑定人</th>
					<th>绑定时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>

				<c:forEach items="${page.result}" var="card" varStatus="status">
				<tr>
						<td>
							<input style="width: 20px;" name="cardIds" type="checkbox" value="${card.id}"/>
						</td>
						
						<td>
							${card.number}
						</td>
						<td>
							<fmt:formatDate value="${card.failureTime}"/>
						</td>
						<td>
							${card.type}
						</td>
						<td>
							${card.userId}
						</td>
						<td>
							<fmt:formatDate value="${card.bindingTime}"/>
						</td>
						
						<td>
							${card.status }
						</td>
						
						<td>
                         <a href="${ctx}/console/zy/suncard/jump?cardId=${card.id}&page=${page.pageNo }"><i class="tdico editico"></i></a></td>
				</tr>
				</c:forEach>
			</table>
				<div style="float:left;"><div onclick="return delAll();" class="btn btn-primary" >禁用</div>&nbsp;&nbsp;</div>
			
			<tags:pagination page="${page}" paginationSize="20"/>
		</form>
		</div>
		
	</div>
 </body>

</html>
