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
				showMsg(true,'恭喜！新增成功');
			}else if(msg=='false'){
				showMsg(false,'抱歉！新增失败');
			}
		});
		
	</script>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav">用户管理 ＞ 查看/新增用户 </div>
		<div class="title_h2">
			查看/修改用户
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
        <div >
        	<form action="${ctx}/console/admin/user/saveUser" method="post" id="myform">
				<table class="fromtable">
					<tr>
						<th class="top_th"><a href="#" class="tongji"><em>查看/修改用户</em><span></span></a></th>
					</tr>
					<tr >
						<td class="fromtd">
							
							<table class="contertable">
								<Tr>
									<td class="label">userId：</td><td><input name="userId"  value="" type="text" class="text {required:true,maxlength:20 ,minlength:1,messages:{required:'请输入userId'}}" size="50"></td>
									<td class="label">姓名：</td><td><input name="name" id="name" value="" type="text" class="text {required:true,maxlength:8 ,minlength:1,messages:{required:'请输入姓名'}}" size="50"></td>
								</Tr>
										<Tr>
										<td class="label">
											密码：
										</td>
										<td>
											<input name="password" value="" type="password" id="password"
												class="text {required:true,maxlength:8 ,minlength:3,messages:{required:'请输入密码'}}" size="50">
										</td>
										<td class="label">
											确认密码：
										</td>
										<td>
											<input name="truePassword" value=""
												type="password" class="text {equalTo:'#password',required:true,maxlength:8 ,minlength:1,messages:{required:'请输入密码'}}" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											出生日期：
										</td>
										<td>
											<input name="birth" value="" type="text" onClick="WdatePicker()"
												class="text Wdate" size="50" id="birth">
										</td>
										<td class="label">
											手机号：
										</td>
										<td>
											<input name="mobile" value="" type="text"
												class="text {mobile:true}" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											邮箱：
										</td>
										<td>
											<input name="mail" value="" type="text"
												class="text {email:true}" size="50">
										</td>
										<td class="label">
											职务：
										</td>
										<td>
											<input name="post" value="" type="text"
												class="text " size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											部门：
										</td>
										<td>
											<input name="deptId" id="deptId"  type="hidden" readonly="true">
											<input name="deptName" id="deptName"  type="text" class="text" size="50" readonly="true">
										</td>
										<td class="label">
											创建时间：
										</td>
										<td>
											<input name="createtime" value="" onClick="WdatePicker()"
												type="text " class="text Wdate" size="50" id="createtime">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											性别：
										</td>
										<td colspan="3">
											男:
											<input type="radio" class="myradio" name="gender" checked="checked" value="男" />
											女:
											<input class="myradio" type="radio" name="gender" value="女" />
										</td>
										
									</Tr>
										<input name="deleted" value="0" type="hidden" >
										<input name="disable" value="0" type="hidden">
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
