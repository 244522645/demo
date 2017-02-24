<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/console/common/static.jsp"%>
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
     	 	     location.href="${ctx}/console/cbirthday/del?id="+id;
     	 	     return true; 
     	 	 }else{
     	 	     return false; 
     	 	 } 
     	 }
     </script>
</head>
<body>
	<div class="bodyMain">
		<div class="top_subnav"> 名人生日管理</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="title_h2">
			搜索
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
		<form action="${ctx}/console/cbirthday" >
			<div class="filed fl">
				<label>名称：</label>
				<input name="filter_LIKES_name" value="${param.filter_LIKES_name}" type="text" size="20">
			</div>
			<div class="filed fl">
				<label>生日：</label>
				<input name="filter_LIKES_birthday" value="${param.filter_LIKES_birthday}"  type="text"  size="20">
			</div>
			<div class="filed fl">
				<button class="btn btn-primary" type="submit">查询</button>
				<button  class="btn btn-primary" type="button" onclick="goToUrl('${ctx}/console/cbirthday/edit')">新增</button>
			</div>
		</form>
		<div class="tablelist">
		 <form method="post" id="adminFrom" action="">
			<table class="table">
				<tr>
				<th colspan="6" class="top_th"><a href="#" class="tongji"><em>名人生日管理</em><span></span></a></th>
				</tr>
				<tr>
					<th style="width:50px"><input style="width:20px;" onclick="selectedCheckbox(this,'userIds')" type="checkbox" value=""/>
					</th><th>名称</th><th>生日</th><th>标题</th><th>操作</th>
				</tr>
				<c:forEach items="${page.result}" var="mingren" varStatus="status">
					<tr>
						<td>
							<input style="width: 20px;" name="userIds" type="checkbox" value="${mingren.id}" />
						</td>
						<td>${mingren.name}</td><td>${mingren.birthday}</td><td>${mingren.title}</td>
						<td>
							<a href="${ctx}/console/cbirthday/edit?id=${mingren.id }"><i class="tdico editico"></i></a>
							<a href="#"><i class="tdico delico" onclick="return del('${mingren.id}','${mingren.name}')"></i></a>
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