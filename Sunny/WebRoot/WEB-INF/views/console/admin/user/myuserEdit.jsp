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
				onkeyup: true
			});
			
			var msg = '${msg}';
			if(msg=='true'){
				showMsg(true,'恭喜！修改成功！请重新登录。');
			}else if(msg=='false'){
				showMsg(false,'抱歉！修改失败！');
			}
		});
		
	</script>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav">账号管理 ＞ 修改密码 </div>
		<div class="title_h2">
			修改密码
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
        <div >
        	<form action="${ctx}/console/main/editMyUser" method="post" id="myform">
				<table class="fromtable">
					<tr>
						<th class="top_th"><a href="#" class="tongji"><em>修改密码</em><span></span></a></th>
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
									</Tr>
									<Tr>
										<td class="label">
											原密码：
										</td>
										<td>
											<input name="password" value="" type="password" id="password" class="text  {required:true,maxlength:8 ,minlength:3,messages:{required:'请输入密码'}}" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											新密码：
										</td>
										<td>
											<input name="newPassword" value="" type="password" id="newPassword"
												class="text {required:true,maxlength:8 ,minlength:3,messages:{required:'请输入新密码'}}" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											确认密码：
										</td>
										<td>
											<input name="truePassword" id="truePassword"  value=""
												type="password" class="text {equalTo:'#newPassword',required:true,maxlength:8 ,minlength:3,messages:{required:'请输入确认密码',equalTo:'两次输入的密码不一致'}}" size="50">
										</td>
									</Tr>
								</table>
							<div class="buttondiv"><button type="button" class="btn btn-primary" onclick="history.back()">返回</button>&nbsp;&nbsp;<button class="btn btn-primary" type="submit">提交</button> </div>
						</td>
					</tr>
				</table>
			</form>
        </div>
	</div>
 </body>
</html>
