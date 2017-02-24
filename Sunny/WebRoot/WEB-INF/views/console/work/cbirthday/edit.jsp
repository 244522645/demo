<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/console/common/static.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		form {
			margin: 0;
		}
		textarea {
			display: block;
		}
	</style>
	<script charset="utf-8"  src="${ctx}/static/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8"  src="${ctx}/static/kindeditor/zh_CN.js"></script>
	<script>
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="content"]', {
				uploadJson : '${ctx}/console/cbirthday/upImg?',
				//uploadJson : "${ctx}/console/upload/upImages?folder=CelebrityBirthday",
				allowFileManager : true
			});
		});
	</script>
</head>
<body>
	<div class="bodyMain">
		<div class="top_subnav">名人生日管理 ＞ 新增/编辑名人生日 </div>
		<div class="title_h2">
			新增/编辑名人生日
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
        <div >
        	<form action="${ctx}/console/cbirthday/saveCb" method="post" id="myform">
        		<input type="hidden" name="id" value="${birthday.id}" /> 
				<table class="fromtable">
					<tr>
						<th class="top_th"><a href="#" class="tongji"><em>新增/编辑名人生日</em><span></span></a></th>
					</tr>
					<tr >
						<td class="fromtd">
							
							<table class="contertable">
									<Tr>
										<td class="label">
											名称：
										</td>
										<td>
											<input name="name"  value="${birthday.name }" type="text" class="text {required:true,maxlength:20 ,minlength:1,messages:{required:'请输入userId'}}" size="50">
										</td>
									</tr>
									<tr>
										<td class="label">
											生日：
										</td>
										<td>
											<input name="birthday" value="${birthday.birthday }" onClick="WdatePicker()" type="text " class="text Wdate" size="50" id="createtime">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											标题：
										</td>
										<td >
											<input name="title" value="${birthday.title }" type="text" class="text {required:true}" size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											内容：
										</td>
										<td >
											<textarea name="content" style="width:800px;height:400px;visibility:hidden;">${birthday.content }</textarea>
										</td>
									</Tr>
								</table>
							<div class="buttondiv"><button type="button" class="btn btn-primary" onclick="goToUrl('${ctx}/console/cbirthday')">返回</button>&nbsp;&nbsp;<button class="btn btn-primary" type="submit">提交</button> </div>
						</td>
					</tr>
				</table>
			</form>
        </div>
	</div>
</body>
</html>