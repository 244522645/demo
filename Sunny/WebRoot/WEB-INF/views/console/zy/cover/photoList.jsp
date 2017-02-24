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
		<div class="top_subnav"> 投稿管理</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="title_h2">
			搜索
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
		<form action="${ctx}/console/zy/photo" >
			<div class="filed fl">
				<label>标题：</label>
				<input name="filter_LIKES_title" value="${param.filter_LIKES_title}" type="text"  size="15">
			</div>
			<div class="filed fl">
				<label>日期：</label>
				<input name="filter_LIKES_shootingTime" value="${param.filter_LIKES_shootingTime}"  type="text"  size="15">
			</div>
<%-- 			<div class="filed fl">
				<label>摄影师：</label>
				<input name="filter_LIKES_cameristName" value="${param.filter_LIKES_cameristName}" type="text" size="15">
			</div> --%>
			<div class="filed fl">
				<button class="btn btn-primary" type="submit">查询</button>
			</div>
		</form>
		<div class="tablelist">
		 <form method="post" id="adminFrom" action="${ctx}/console/zy/photo/batchDel">
			<table class="table">
				<tr>
				<th colspan="9" class="top_th"><a href="#" class="tongji"><em>照片管理</em><span></span></a></th>
				</tr>
				<tr>
					<th style="width:30px"><input style="width:20px;" onclick="selectedCheckbox(this,'id')" type="checkbox" value=""/></th>
					<th>投稿者</th><th>地点</th><th>照片日期</th><th>介绍</th><th>审核状态</th><th>操作</th>
				</tr>

				<c:forEach items="${page.result}" var="img" varStatus="status">
				<tr>
						<td>
						<table>
							<tr>
							<td>
							    <input style="width: 20px;" name="photoIds" type="checkbox" value="${img.id}"/>
							</td>
							<td>
							<a style="float:left" href="${config.imgDomainName}/${img.imgId.folder}/${img.imgId.name}_y1080.jpg" target="_blank"><img alt="" src="${config.imgDomainName}/${img.imgId.folder}/${img.imgId.name}_90x90.jpg">点击下载</a>
							</td>
							<td>
							宽/高:${img.imgId.width}/${img.imgId.height}px
							<br>
							大小:${img.imgId.size}
							</td>
							</tr>
						</table>
						</td>
						<td>
						</td>
						<td>
							${img.title}
						</td>
						<td>
							<fmt:formatDate value="${img.shootingTime}"/>
						</td>
						<td>
							${img.story }
						</td>
						<td>
						<c:if test="${img.released eq 1}"><span style="color:green">审核通过</span></c:if>
						<c:if test="${img.released eq 0}">待审</c:if>
						<c:if test="${img.released eq 2}"><span style="color:red">未通过</span></c:if>
						</td>
						<td>
							<a href="${ctx}/console/zy/photoCover/jump?imgId=${img.id}&page=${page.pageNo }"><i class="tdico editico"></i></a>
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
