<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>芸备胎</title>
	</head>
	<body>
	<!-- 密码找回模态框 -->
		<div class="modal fade" id="mypassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<!-- 模态框标题 -->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">密码找回</h4>
					</div>
					<!-- 内容表单 -->
					<div class="modal-body row csmm_sjyz">
						<form class="form-horizontal" id="findCodeForm">
							<div class="form-group">
<!-- 								<label for="tel" class="col-sm-2 col-sm-offset-1 control-label">手机号</label> -->
								<div class="col-sm-8">
									<input type="tel" class="form-control" id="findphone" placeholder="手机号" rule="must_noaccountphone">
								</div>
							</div>
							<div class="form-group">
<!-- 								<label for="number" class="col-sm-2 col-sm-offset-1 control-label">验证码</label> -->
								<div class="col-sm-4">
									<input type="text" class="form-control" id="findcode" placeholder="输入6位短信验证码" rule="must_code">
								</div>
								<div class="col-sm-4">
									<button type="button" class="btn btn-default" onclick="sendShortMessage('findphone',1)">发送验证码</button>
								</div>
							</div>
						</form>
					</div>

					<!-- 模态框底部 -->
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="rpassword">重置密码</button>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="${ctx}/static/js/chxt/validate.js"></script>
<script type="text/javascript">


//发送验证码
function sendShortMessage(phoneName,type){
	if(!checkText(phoneName)){
		return false;
	}
	var phone = $w(phoneName).value;
    $ybtCAjax({
		url:"${ctx}/work/login/sendsms",
		type:'POST',
		data: 'phone='+phone+"&type="+type,
		dataType: "json",
		success:function(result){	
			if(!isNull(result)&&result.s==1){
				alert("发送成功");
			}else{
				alert("发送异常");   
			}
		}
	});
};
</script>

</html>