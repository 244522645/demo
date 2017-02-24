<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
   	 <script>
	 	
	 	 function delAll(){
     	    if(confirm("确定要删除吗？")){
     	 	     $("#adminFrom").submit();
     	 	     return true; 
     	 	 }else{
     	 	     return false; 
     	 	 } 
     	 	return false;
     	 }
     	 
     	 function del(id,name){
     	 	 if(confirm("确实要删除"+name+"吗？")){
     	 	     location.href="${ctx}/console/admin/user/del/"+id;
     	 	     return true; 
     	 	 }else{
     	 	     return false; 
     	 	 } 
     	 }
     </script>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav"> 用户管理</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="title_h2">
			搜索
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
		<form action="${ctx}/console/admin/user" >
			<div class="filed fl">
				<label>用户名：</label>
				<input name="filter_LIKES_userId" value="${param.filter_LIKES_userId}" type="text" size="20">
			</div>
			<div class="filed fl">
				<label>姓名：</label>
				<input name="filter_LIKES_name" value="${param.filter_LIKES_name}"  type="text"  size="20">
			</div>
			<div class="filed fl">
				<label>手机号：</label>
				<input name="filter_LIKES_mobile" value="${param.filter_LIKES_mobile}" type="text"  size="20">
			</div>
			<div class="filed fl">
				<button class="btn btn-primary" type="submit">查询</button>
				<button  class="btn btn-primary" type="button" onclick="goToUrl('${ctx}/console/admin/user/add')">新增</button>
			</div>
		</form>
		<div class="tablelist">
		 <form method="post" id="adminFrom" action="${ctx}/console/admin/user/batchDel">
			<table class="table">
				<tr>
				<th colspan="6" class="top_th"><a href="#" class="tongji"><em>用户管理</em><span></span></a></th>
				</tr>
				<tr>
					<th style="width:50px"><input style="width:20px;" onclick="selectedCheckbox(this,'userIds')" type="checkbox" value=""/></th><th>用户名</th><th>姓名</th><th>手机号</th><th>邮箱</th><th>操作</th>
				</tr>
				<c:forEach items="${page.result}" var="user" varStatus="status">
				<tr>
						<td>
							<c:if test="${user.userId!='admin'}">
								<input style="width: 20px;" name="userIds" type="checkbox"value="${user.userId}" />
							</c:if>
						</td>
						<td>
							${user.userId }
						</td>
						<td>
							${user.name }
						</td>
						<td>
							${user.mobile }
						</td>
						<td>
							${user.mail }
						</td>
						<td>
							<c:if test="${user.userId!='admin'}">
								<a href="${ctx}/console/admin/user/edit/${user.userId }"><i
									class="tdico editico"></i>
								</a><a href="#"><i class="tdico delico"
									onclick="return del('${user.userId}','${user.name}')"></i>
								</a>
							</c:if>
						</td>
				</tr>
				</c:forEach>
			</table>
				<div style="float:left;"><div   onclick="return delAll();" class="btn btn-primary" >删除</div>&nbsp;&nbsp;</div>
			
			<tags:pagination page="${page}" paginationSize="20"/>
		</form>
		</div>
	</div>
 </body>
</html>
