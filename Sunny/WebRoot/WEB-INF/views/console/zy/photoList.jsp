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
		<form action="${ctx}/console/zy/photo" >
			<div class="filed fl">
				<label>分类</label>
				<input name="filter_LIKES_subject" value="${param.filter_LIKES_subject}" type="text"  size="15">
			</div>
			<div class="filed fl">
				<label>文件名：</label>
				<input name="filter_LIKES_localName" value="${param.filter_LIKES_localName}" type="text"  size="15">
			</div>
			<div class="filed fl">
				<label>标题：</label>
				<input name="filter_LIKES_title" value="${param.filter_LIKES_title}" type="text"  size="15">
			</div>
			<div class="filed fl">
				<label>日期：</label>
				<input name="filter_LIKES_shootingTime" value="${param.filter_LIKES_shootingTime}"  type="text"  size="15">
			</div>
			<div class="filed fl">
				<label>省：</label>
				<input name="filter_LIKES_province" value="${param.filter_LIKES_province}" type="text"  size="15">
			</div>
<%-- 			<div class="filed fl">
				<label>摄影师：</label>
				<input name="filter_LIKES_cameristName" value="${param.filter_LIKES_cameristName}" type="text" size="15">
			</div> --%>
			<div class="filed fl">
				<button class="btn btn-primary" type="submit">查询</button>
				<button  class="btn btn-primary" type="button" onclick="goToUrl('${ctx}/console/zy/photo/jump')">新增</button>
			</div>
		</form>
		<div class="tablelist">
		 <form method="post" id="adminFrom" action="${ctx}/console/zy/photo/batchDel">
			<table class="table">
				<tr>
				<th colspan="10" class="top_th"><a href="#" class="tongji"><em>照片管理</em><span></span></a></th>
				</tr>
				<tr>
					<th style="width:30px"><input style="width:20px;" onclick="selectedCheckbox(this,'id')" type="checkbox" value=""/></th>
					<th>分类</th><th>文件名</th><th>编号</th><th>标题</th><th>照片日期</th><th>省市</th><th>地点</th><th>上传时间</th><th>操作</th>
				</tr>

				<c:forEach items="${page.result}" var="img" varStatus="status">
				<tr>
						<td>
							<input style="width: 20px;" name="photoIds" type="checkbox" value="${img.id}"/>
							<a href="${config.imgDomainName}/${img.imgId.folder}/${img.imgId.name}_y1080.jpg" target="_blank"><img alt="" src="${config.imgDomainName}/${img.imgId.folder}/${img.imgId.name}_90x90.jpg"></a>
						</td>
						<td>
							${img.subject}
						</td>
						<td style="width: 20px;">
							${img.localName}
						</td>
						<td>
							${img.photoNo}
						</td>
						<td>
							${img.title}
						</td>
						<td>
							<fmt:formatDate value="${img.shootingTime}"/>
						</td>
						<td>
							<c:set var="province" value="${fn:substringBefore(img.province,'_')}"/>
							<c:set var="city" value="${fn:substringBefore(img.city,'_')}"/>
							<%-- <c:set var="province" value="${fn:replace(province, '省', '')}"/>
							<c:set var="province" value="${fn:replace(province, '市', '')}"/>
							<c:set var="city" value="${fn:replace(city, '市', '')}"/> --%>
							${province } 
							<c:if test="${province eq city} ">
								 <c:set var="city" value=""/>
							</c:if>
							- ${city }
						</td>
						<td>
							${img.address }
						</td>
						<td>
							<fmt:formatDate value="${img.createTime}" pattern="MM月dd日 HH:mm"/>
						</td>
						<td>
							<a href="${ctx}/console/zy/photo/jump?imgId=${img.id}&page=${page.pageNo }"><i class="tdico editico"></i></a>
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
