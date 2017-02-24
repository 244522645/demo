<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.jpg" type="image/x-icon" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/easyui/themes/icon.css" />
<script type="text/javascript" src="<%=path%>/easyui/jquery-3.1.0.js"></script>
<script type="text/javascript"
	src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/card/openCard.css" />
<script type="text/javascript" src="<%=path%>/js/card/deferCard.js"
	charset="utf-8"></script>
<title>延期页</title>
</head>
<body class="easyui-layout" style="font: 16px Microsoft YaHei" oncontextmenu="return false" onselectstart="return false">
	<div data-options="region:'north',border:false,collapsible:false,"
		style="height: 98%; background: url(./images/b0f0c51d7e.jpg); background-size: cover;"
		id="TopTitle">
		<div
			style="background: rgb(236, 243, 255) none repeat scroll 0% 0%; height: 30px; width: 100%;"
			align="center">
			<span
				style="font-size: 20px; margin-left: 20px; color: rgb(255, 0, 0);">延期单填写</span>
		</div>
		<div id="dlgSave"
			style="padding: 5px; width: 98%; height: 90%; radius: 20px;"
			align="center">
			<table style="width: 50%; height: 10%">
				<tr align="center">
					<td>申请人：${sessionScope.username.username}</td>
					<td align="right">时间：<span id='systemtime'></span></td>
				</tr>
			</table>

			<div
				style="width: 80%; height: 600px;; border: 1px solid rgb(193, 203, 218); border-radius: 20px;"
				align="center">

				<div
					style="background: rgb(103, 203, 199) none repeat scroll 0% 0%; height: 30px; width: 100%;"
					align="left">
					<span style="font-size: 20px; margin-left: 20px;">一、延期卡、延期电子券：</span>
				</div>
				<div style="width: 90%; height: 79%;margin-top: 2%;" align="center">
					<form id="openCard" method="post">
						<table align="center">
							<tr>
								<td style="text-align: right; width: 200px">类型：</td>
								<td><input type="hidden" name="cardOrdersType" value="1" />
									<input id="add_cardKindsId" class="easyui-combobox"
									name="cardKindsId" /></td>
									
							</tr>
							<tr>
								<td style="text-align: right; width: 200px">号段：</td>
								<td><input type="text" id="add_startCardNum"
									name="startCardNum" maxlength="11" onblur="chengben(this)"
									onkeyup="this.value=this.value.replace(/\D/g,'')" />- <input
									type="text" id="add_endCardNum" name="endCardNum"
									maxlength="11" onblur="sum(this)"
									onkeyup="this.value=this.value.replace(/\D/g,'')" /></td>

							</tr>
							<tr>
								<td style="text-align: right; width: 200px">数量：</td>
								<td><input type="text" id="add_shuLiang" name="shuLiang"
									maxlength="6" readonly="readonly" autocomplete="off" />(张)</td>

							</tr>
							<tr>
								<td style="text-align: right; width: 200px">到期时间：</td>
								<td><input id="add_closingDate" name="beforeDate"
									style="width: 160px;" type="text" class="easyui-datetimebox"
									required="required" data-options="editable:false"></td>
							</tr>
							<tr>
								<td style="text-align: right; width: 200px">延长时间：</td>
								<td><input id="add_afterDate" name="afterDate"
									style="width: 160px;" type="text" class="easyui-datetimebox"
									required="required" data-options="editable:false"></td>
							</tr>							
							<tr>
								<td style="text-align: right; width: 200px">备注：</td>
								<td><textarea rows="3" style="width: 300px; resize: none;"
										name="memo"></textarea></td>
							</tr>
							<tr>
								<td style="text-align: right; width: 200px"><span
									style="color: rgb(245, 29, 29);">注：</span></td>
								<td><span style="color: rgb(245, 29, 29);">
										请仔细核对类型及卡号张数，一经提交 延期后不能修改! </span></td>
							</tr>


							<tr>

								<td colspan="2" align="center" style="padding-top: 10px;"><a
									id="btn" onclick="addInfo()"
									class="table_add_btn easyui-linkbutton"
									data-options="iconCls:'icon-save'"
									style="width: 160px; height: 30px;">开卡</a></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>

	</div>

</body>
</html>