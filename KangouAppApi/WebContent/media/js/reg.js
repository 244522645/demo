var imsi;
var mobileType;
var istrue;
var Login = function() {

	return {
		// main function to initiate the module
		init : function() {

			jQuery('.register-form').show();

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
	
	if (phone.value.length != 11) {
		return;
	}
	if (!(/^1[34578]\d{9}$/.test(phone.value))) {
		swal("手机号码有误，请重填");
		istrue='2';
		return;
	}
	var param = {
		"phone" : phone.value
	}
	$.ajax({
		type : "POST",
		url : "/KangouAppApi/login/selectUserByPhone.do",
		data : {
			phone : phone.value,
			imsi: imsi
		},

		dataType : "JSON",
		success : function(data) {
			if (data == '0') {
				swal("该手机号可以注册");
				istrue='0';
			} else {
				
				swal("该手机号已被注册");
				istrue='1';
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
	var phone = $("#phone").val();
	if(!phone){
		swal("请输入手机号");
		return;
	}
	if (!(/^1[34578]\d{9}$/.test(phone))) {
		swal("手机号码有误，请重填");
		return;
	}
	if(istrue=='1'){
		swal("手机号已经被注册");
		return;
	}
	if(istrue=='2'){
		swal("手机号错误");
		return;
	}
	$.ajax({
		type : "POST",
		url : "../login/gettingCode.do",
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
				swal("发送失败，请稍后再次发送");
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
	$("#phone_btn").button('loading');
	var phone = $("#phone").val();
	if (!phone) {
		swal("手机号不能为空");
		$("#phone_btn").button('reset');
		return;
	}
	var code = $("#code").val();
	if (!code) {
		swal("验证码不能为空");
		$("#phone_btn").button('reset');
		return;
	}
	var regPassword = $("#register_password").val();
	var twoPassword = $("#twoPassword").val();
	if (!regPassword && !twoPassword) {
		swal("密码不能为空");
		$("#phone_btn").button('reset');
		return;
	}
	if (regPassword != twoPassword) {
		swal("俩次输入密码不一致");
		$("#phone_btn").button('reset');
		return;
	}
	console.log(phone);
	console.log(code);
	console.log(regPassword);
	console.log(twoPassword);
	console.log(imsi);
	var param = {
		"phone" : phone,
		"code" : code,
		"regPassword" : regPassword,
		"twoPassword" : twoPassword,
		"imsi" : imsi,
		"mobileType" : mobileType
	};

	$.ajax({
		type : "POST",
		url : "../login/registerUser.do",
		data : {
			phone : phone,
			code : code,
			regPassword : regPassword,
			twoPassword : twoPassword,
			imsi : imsi,
			mobileType:mobileType
		},

		dataType : "JSON",
		success : function(data) {
			if (data == 0) {
				swal("注册成功，请联系资源部审核开通");
				$("#phone_btn").button('reset');
				kangoulogin();

				return;
			}
			if (data == 1101) {
				swal("手机号不能为空");
				return;
			}
			if (data == 1102) {
				swal("俩次密码不一样");
				return;
			}
			if (data == 1103) {
				swal("手机号不是当前手机");
				return;
			}
			if (data == 1104) {
				swal("手机验证码不正确");
				return;
			}
			if (data == 1105) {
				swal("不要频繁点击提交按钮");
				return;
			}
			if (data == 1106) {
				swal("手机号不符合规则");
				return;
			}
			if (data == 1107) {
				swal("手机串码错误");
				return;
			}
			if (data == 1108) {
				swal("手机类型错误");
				return;
			}

		},
		error : function() {

		}

	});

}
