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
		<div class="top_subnav"> 摄影师管理</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="title_h2">
			搜索
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
		<form action="${ctx}/console/zy/photo" >
			<div class="filed fl">
				<label>名称：</label>
				<input name="filter_LIKES_name" value="${param.filter_LIKES_name}" type="text"  size="15">
			</div>
			<div class="filed fl">
				<label>地点：</label>
				<input name="filter_LIKES_tags" value="${param.filter_LIKES_tags}"  type="text"  size="15">
			</div>
			<div class="filed fl">
				<button class="btn btn-primary" type="submit">查询</button>
			</div>
			<div class="filed fl"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${ctx}/console/zy/photoGrapher/jump?imgId=${img.id}&page=${page.pageNo }"><button class="btn btn-primary" type="button">添加</button></a>
			</div>
		</form>
		<div class="tablelist">
		 <form method="post" id="adminFrom" action="${ctx}/console/zy/photo/batchDel">
			<table class="table">
				<tr>
				<th colspan="9" class="top_th"><a href="#" class="tongji"><em>摄影师管理</em><span></span></a></th>
				</tr>
				<tr>
					<th style="width:30px"><input style="width:20px;" onclick="selectedCheckbox(this,'id')" type="checkbox" value=""/></th>
					<th>名称</th><th>地点</th><th>介绍</th><th>天数</th><th>操作</th>
				</tr>

				<c:forEach items="${page.result}" var="img" varStatus="status">
				<tr>
					<td>
					    <input style="width: 20px;" name="photoIds" type="checkbox" value="${img.id}"/>
					</td>
					<td>
					<a style="float:left" href="${config.imgDomainName}/${img.head.folder}/${img.head.name}_y1080.jpg" target="_blank"><img alt="" src="${config.imgDomainName}/${img.head.folder}/${img.head.name}_90x90.jpg">${img.name}</a>
					</td>
					<td>
					${img.tags}
					</td>
					<td>
					${img.introduce}
					</td>
					<td>
					${img.dayCount}
					</td>
					<td>
						<a href="${ctx}/console/zy/photoGrapher/jump?id=${img.id}&page=${page.pageNo }"><i class="tdico editico"></i></a>
						<%-- <a href="#"><i class="tdico delico" onclick="return del('${user.userId}','${user.name}')"></i></a> --%>
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
