<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
   	  <script>
   	  	$().ready(function(){
				$.ajaxSetup({ cache: false }); //解决ajax缓存结果问题 
				$("#myform").validate({
					onkeyup: false
				});
				
				
				//部门树
	  	 		var deptTree = null;
				if(deptTree==null){
					deptTree = new myBpmTree({
						url:'${ctx}/console/admin/dept/tree/dept',
						title:'选择部门',
						callback:function(data){
							if(data.legnth=0)
								return ;
							deptTree.setValues(data,'deptId','deptName');
						}
					});
				}
				//触发添加用户按钮
				$("#deptName").click(function(){
					deptTree.showTreeDialog({ids:$("#deptId").val()});
				});
				
				var msg = '${msg}';
				if(msg=='true'){
					showMsg(true,'恭喜！修改成功');
				}else if(msg=='false'){
					showMsg(false,'抱歉！修改失败');
				}
		});
   	  </script>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav">用户管理 ＞ 查看/修改用户 </div>
		<div class="title_h2">
			查看/修改用户
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
        <div >
        	<form action="${ctx}/console/admin/user/editUser" method="post" id="myform">
				<table class="fromtable">
					<tr>
						<th class="top_th"><a href="#" class="tongji"><em>查看/修改用户</em><span></span></a></th>
					</tr>
					<tr >
						<td class="fromtd">
							<table class="contertable">
									<Tr>
										<td class="label">
											用户名：
										</td>
										<td>
											${user.userId}
											<input name="userId" value="${user.userId}" type="hidden">
										</td>
										<td class="label">
											姓名：
										</td>
										<td>
											<input name="name" value="${user.name}" type="text"
												class="text {required:true,rangelength:[1,8] ,minlength:1,messages:{required:'请输入姓名'}}" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											密码：
										</td>
										<td>
											<input name="password" value="${user.password}" type="password" id="password"
												class="text {required:true,maxlength:50 ,minlength:3,messages:{required:'请输入密码'}}" size="50">
										</td>
										<td class="label">
											确认密码：
										</td>
										<td>
											<input name="truePassword" value="${user.password}"
												type="password" class="text {equalTo:'#password',required:true,maxlength:50 ,minlength:1,messages:{required:'请输入密码'}}" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											出生日期：
										</td>
										<td>
											<input onClick="WdatePicker()" name="birth"
												value="${user.birth}" type="text" class="text Wdate"
												size="50">
										</td>
										<td class="label">
											手机号：
										</td>
										<td>
											<input name="mobile" value="${user.mobile}" type="text"
												class=" text {mobile:true}" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											邮箱：
										</td>
										<td>
											<input name="mail" value="${user.mail}" type="text"
												class="text {email:true}" size="50">
										</td>
										<td class="label">
											职务：
										</td>
										<td>
											<input name="post" value="${user.post}" type="text"
												class="text" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											部门：
										</td>
										<td>
											<input id="deptId" name="deptId" value="${user.deptId}" type="hidden" class="text" size="50">
											<input id="deptName" name="deptName" value="${user.deptName}" type="text" class="text" size="50" readonly="true">
										</td>
										<td class="label">
											创建时间：
										</td>
										<td>
											<input onClick="WdatePicker()" name="createtime"
												value="${user.createtime}" type="text" class="text Wdate"
												size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											性别：
										</td>
										<td colspan="3">
											男:
											<input
												<c:if test="${user.gender=='男' }">checked="checked"</c:if>
												type="radio" class="myradio" name="gender" value="男" />
											女:
											<input class="myradio" type="radio"
												<c:if test="${user.gender=='女' }">checked="checked"</c:if>
												name="gender" value="女" />
										</td>

										<%-- <input name="password" value="${user.password}" type="hidden"> --%>
										<input name="salt" value="${user.salt}" type="hidden">
										<input name="deleted" value="${user.deleted}" type="hidden">
										<input name="disable" value="${user.disable}" type="hidden">
								</table>
							<div class="buttondiv"><button type="button" class="btn btn-primary" onclick="goToUrl('${ctx}/console/admin/user')">返回</button>&nbsp;&nbsp;<button class="btn btn-primary" type="submit">提交</button> </div>
						</td>
					</tr>
				</table>
			</form>
        </div>
	</div>
 </body>
</html>
