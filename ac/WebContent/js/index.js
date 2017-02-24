$(function() {
	// 查询用户下面的菜单
	left();
	$('#updatepassword').on('hide.bs.modal', function() {
		$("#txtNewPass").val("");
		$("#txtRePass").val("");
	});
});

function left() {
	$("#leftMenu li").remove();
	$("#leftMenu")
			.append(
					"<li ><div class='sidebar-toggler hidden-phone'></div></li><li class='title'><a href=''turnindex.do?url=index'> <i class='icon-home'></i> <span class=''title'>主页</span> <span class='selected'></span> </a></li>");
	$
			.post(
					"/ac/KUserRoles/findbyUser.do",
					function(data) {
						if (data) {
							console.log(data);
							$
									.each(
											data,
											function(i, n) {
												var menulist = "";
												if (!n.rightascription) {
													menulist += '<li class=""><a href="javascript:;"> <i class="'
															+ n.rightPic
															+ '"></i> <span class="title">'
															+ n.rightName
															+ '</span> <span class="arrow "></span> </a> <ul class="sub-menu">';
													$
															.each(
																	data,
																	function(j,
																			o) {
																		if (o.rightascription == n.rightId) {
																			menulist += '<li><a href="javascript:changeIfarm(\''
																					+ o.rightPage
																					+ '\')"> <i class="'
																					+ o.rightPic
																					+ '"></i>'
																					+ o.rightName
																					+ '</a></li>';

																		}

																	})
													menulist += '</ul></li>';

													$("#leftMenu").append(
															menulist);
												}

											})
						}

					});
}

function changeIfarm(src) {
	$("#mainFrame").attr("src", src);
}

// 打开修改密码框
function updatePassword() {
	$('#updatepassword').modal();
}
// 修改密码
function updateword() {
	var newpass = $('#txtNewPass').val();
	var repass = $('#txtRePass').val();
	if (!newpass && !repass) {
		swal('密码不能为空');
		return;
	} else if (newpass != repass) {
		swal('输入不一致，请重新输入');

		return;
	} else {
		$.post("./KUser/updatepassword.do", {
			newpass : newpass
		}, function(data) {
			if (data == 0) {
				swal("密码修改成功!"); 
			} else {
				swal("修改失败，请联系管理员");
			}

		});
	}
}

// 退出
function out() {
	swal({
		title : "操作提示", // 弹出框的title
		text : "确定退出吗？", // 弹出框里面的提示文本
		type : "warning", // 弹出框类型
		showCancelButton : true, // 是否显示取消按钮
		confirmButtonColor : "#DD6B55",// 确定按钮颜色
		cancelButtonText : "取消",// 取消按钮文本
		confirmButtonText : "退出！",// 确定按钮上面的文档
		closeOnConfirm : true
	}, function() {
		$.post("./KUser/out.do", function(data) {
			if (data == 0) {
				window.open("login.do", '_self');
			}
		});

	});

}
