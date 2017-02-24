var imsi;
var mobileType;
var isture;
var Login = function() {

	return {
		// main function to initiate the module
		init : function() {
			
			jQuery('.forget-form ').show();
			$("#phone_btn").click(function() {
				
					sendCode(this);
			});
			
		}

	};

}();
/*
 * 验证手机号
 */
function isphone(phone) {
	isture=0;
	if (phone.value.length != 11) {
		swal("手机号输入错误，请重新输入");
		return;
	}
	if (!(/^1[34578]\d{9}$/.test(phone.value))) {
		swal("手机号码有误，请重填");
		isture=2;
		return;
	}
	var param = {
		"phone" : phone.value
	}
	$.ajax({
		type : "POST",
		url : "../login/selectUserByPhone.do",
		data : {
			phone : phone.value,
			imsi:imsi
		},

		dataType : "JSON",
		success : function(data) {
			if (data == '0') {
				isture='1';
				swal("该手机号没被注册");
			}
		},
		error : function() {

		}

	});
}

/*
 * 验证码延时开始
 */
var clock = '';
var nums = 95;
var btn;
function sendCode(thisBtn) {
	var phone = $("#forgot_phone").val();
	if(!phone){
		swal("请输入手机号");
		return;
	}
	if(isture=='1'){
		swal("手机号没有注册");
		return;
	}
	if(isture=='2'){
		swal("手机号错误");
		return;
	}
	$.ajax({
		type : "POST",
		url : "../login/forgotPassCode.do",
		data : {
			phone : phone
		},

		dataType : "JSON",
		success : function(data) {
			if (data == '00') {
				swal("获取验证码成功");
			}
			if (data == '5') {
				swal("不要频繁获取验证码");
				return;
			}
			if (data == '6') {
				swal("手机号为空");
				return;
			}
			if (data == '10') {
				swal("获取验证码失败，请稍后再试");
				return;
			}
		},
		error : function() {

		}

	});
	btn = thisBtn;
	$("#phone_btn").button('loading'); // 将按钮置为不可点击
	btn.value = nums + '秒后可重新获取';
	clock = setInterval(doLoop, 1000); // 一秒执行一次
}
function doLoop() {
	nums--;
	if (nums > 0) {
		$("#phone_btn").text(nums + '秒后可重新获取');
	} else {
		clearInterval(clock); // 清除js定时器
		$("#phone_btn").button('reset');
		$("#phone_btn").text("获取验证码");
		nums = 10; // 重置时间
	}
}

/*
 * 验证码延时结束
 */

/*
 * 密码验证事件
 */
function regPassword() {
	var regPassword = $("#register_password").val();
	var twoPassword = $("#twoPassword").val();

	if (twoPassword) {
		if (regPassword != twoPassword) {
			swal("俩次输入密码不一致，请重新输入");
			return;
		}

	}
}

function istwoPassword() {
	var regPassword = $("#register_password").val();
	var twoPassword = $("#twoPassword").val();

	if (regPassword) {
		if (regPassword != twoPassword) {
			swal("俩次输入密码不一致，请重新输入");
			return;
		}

	}
}

/*
 * 表单验证
 */
function formBtn() {
	var phone = $("#forgot_phone").val();
	if (!phone) {
		swal("手机号不能为空");
		return;
	}
	var code = $("#code").val();
	if (!code) {
		swal("验证码不能为空");
		return;
	}
	var regPassword = $("#register_password").val();
	var twoPassword = $("#twoPassword").val();

	if (!regPassword && !twoPassword) {
		swal("密码不能为空");
		return;
	}
	if (regPassword.length < 6) {
		swal("密码不能小于六位");
		return;
	}
	if (regPassword != twoPassword) {
		swal("俩次输入密码不一致");
		return;
	}
	console.log(phone);
	console.log(code);
	console.log(regPassword);
	console.log(twoPassword);
	console.log(imsi);
	$.ajax({
		type : "POST",
		url : "../login/forgotPass.do",
		data : {
			phone : phone,
			code : code,
			regPassword : regPassword,
			twoPassword : twoPassword,
			imsi : imsi
		},

		dataType : "JSON",
		success : function(data) {
			if (data == 0) {
				swal("修改密码成功");
			}
			if (data == 1201) {
				swal("手机号不能为空");
			}
			if (data == 1202) {
				swal("俩次密码不一样");
			}
			if (data == 1203) {
				swal("手机号不是当前手机");
			}
			if (data == 1204) {
				swal("手机验证码不正确");
			}
			if (data == 1205) {
				swal("不要频繁点击提交按钮");
			}
			if (data == 1206) {
				swal("修改失败，请重新修改");
			}
			if (data == 1207) {
				swal("你已更换手机，请重新注册");
			}
			if (data == 1208) {
				swal("你已修改手机号，请重新获取");
			}
			if (data == 1209) {
				swal("手机号不正确");
			}
			if (data == 1210) {
				swal("密码不能低于6位");
			}
		},
		error : function() {

		}

	});

}
