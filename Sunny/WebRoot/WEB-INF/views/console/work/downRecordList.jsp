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
		<div class="top_subnav"> 下载原图请求纪录</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="title_h2">
			搜索
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
		<form action="${ctx}/console/downRecord/list" >
			<div class="filed fl">
				<label>email：</label>
				<input name="filter_LIKES_title" value="${param.filter_LIKES_email}" type="text"  size="15">
			</div>
			<div class="filed fl">
				<label>处理状态：</label>
				<input name="filter_LIKES_title" value="${param.filter_LIKES_state}" type="text"  size="15">
			</div>
			<div class="filed fl">
				<button class="btn btn-primary" type="submit">查询</button>
			</div>
		</form>
		<div class="tablelist">
		 <form method="post" id="adminFrom" action="${ctx}/console/downRecord/list">
			<table class="table">
				<tr>
				<th colspan="9" class="top_th"><a href="#" class="tongji"><em>下载原图请求纪录</em><span></span></a></th>
				</tr>
				<tr>
					<th style="width:30px"><input style="width:20px;" onclick="selectedCheckbox(this,'id')" type="checkbox" value=""/></th>
					<th>用户</th><th>【&nbsp;文件名</th><th>照片编号</th><th>照片标题</th><th>照片日期&nbsp;】</th><th>下载邮箱</th><th>提交时间</th><th>操作</th>
				</tr>

				<c:forEach items="${page.result}" var="entivy" varStatus="status">
				<tr>
						<td>
							<input style="width: 20px;" name="photoIds" type="checkbox" value="${entivy.user.wechatHeadUrl}"/>
							<img alt="${entivy.user.wechatNickname}" src="${entivy.user.wechatHeadUrl}" width="90">
						</td>
						<td style="width: 20px;">
							&nbsp;${entivy.user.wechatNickname}
						</td>
						<td style="width: 20px;">
							&nbsp;${entivy.order.photo.localName}
						</td>
						<td>
							&nbsp;${entivy.order.photo.photoNo}
						</td>
						<td>
							&nbsp;${entivy.order.photo.title}
						</td>
						<td>
							<fmt:formatDate value="${entivy.order.photo.shootingTime}"/>
						</td>
						<td>
							&nbsp;${entivy.email}
						</td>
						<td>
							<fmt:formatDate value="${entivy.createTime}" pattern="MM月dd日 HH:mm"/>
						</td>
						<td>
							<a href="#">确定发送</a>
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