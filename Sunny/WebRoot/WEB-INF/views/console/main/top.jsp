<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <title>${config.siteName}</title>
  </head>
  
 <body >
		<div class="top">
		   <!-- <div class="topmain"> -->
			<div class="top_about">	
				<a href="#" class="help1" id="btn2" >使用帮助</a>
				<a href="${ctx}/console/logout" target="_top" class="help2">退出</a>
			</div>
			<div class="admin_logo">
			   <%--  <img src="${ctx}/static/images/admin_logo.png">  --%>
			     <br>
			     <h2 style="color:#fff;">&nbsp;&nbsp;&nbsp;&nbsp;${config.siteName} (^_^)</h2>
			     <h2 style="color:#fff;">&nbsp;&nbsp;&nbsp;&nbsp;——资源管理系统——</h2>
			</div>
			<div class="top_nav">
					<table  style="border-collapse:collapse">
						<Tr>
							<td class="left_nav"></td>
							<td class="main_nav">
								<ul id="menuUl">
									<li id="index" ><a onclick="setMenuSelected(this);" href="${ctx}/console/main/main" <c:if test="${menuId eq '0'}">class="selected"</c:if> target="frameMain">首页</a></li>
									<c:forEach items="${menu}" var="menu" >
										<li id="${menu.menuId}" ><a onclick="setMenuSelected(this);" href="${ctx}/console/leftMenu/${menu.menuId}" <c:if test="${menu.menuId eq menuId}">class="selected"</c:if> target="frameLeft">${menu.name}</a></li>
									</c:forEach>					
								</ul>
							</td>
							<td class="right_nav"></td>
						</Tr>
					</table>
			</div>
			<div class="top_member">
			欢迎您，<span id="name">${name}</span>  
			</div>
			<!-- </div> -->
		</div>
	</body>
</html>
