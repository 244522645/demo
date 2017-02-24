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
		<div class="top_subnav"> 用户管理</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="title_h2">
			搜索
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
		<form action="#" >
			<div class="filed fl">
				<label>用户名：</label>
				<input type="text" size="20">
			</div>
			<div class="filed fl">
				<label>姓名：</label>
				<input type="text" class="text" size="20">
			</div>
			<div class="filed fl">
				<label>手机号：</label>
				<input type="text" class="text" size="20">
			</div>
			<div class="filed fl">
				<label>部门：</label>
				<select  name="category" id="category" >
					<option value="1">部门信息</option>
					<option value="2">公司信息</option>
				</select>
			</div>
			<div class="filed fl">
				<button class="button"></button>
			</div>
		</form>
		<div class="tablelist">
			<table class="table">
				<tr>
				<th colspan="5" class="top_th"><a href="#" class="tongji"><em>用户管理</em><span></span></a></th>
				</tr>
				<tr>
					<th>序号</th><th>用户名</th><th>姓名</th><th>手机号</th><th>邮箱</th>
				</tr>
				<c:forEach items="${page.result}" var="user" varStatus="status">
				<tr>
					<td>${status.index+1}</td><td>${user.userId }</td><td>${user.name }</td><td>${user.mobile }</td><td>${user.mail }</td>
				</tr>
				</c:forEach>
			</table>
			<tags:pagination page="${page}" paginationSize="20"/>
		</div>
	</div>
 </body>
</html>
