$(function() {
	$("#cardNum").blur(function() {
		if (!(/^\d{11}$/.test(this.value))) {
			swal("不是十一位数字");
			return;
		}
	});

});

function issubmit() {
	$("#submitBtn").button("disabled", "disabled");
	var cardNum = $("#cardNum").val();
	var cardPass = $("#cardPass").val();
	if (!(/^\d{11}$/.test(cardNum))) {
		$('#submitBtn').removeAttr("disabled");
		$("#submitBtn").blur();
		swal("不是十一位数字");
		return false;
	}
	if (!cardPass) {
		$('#submitBtn').removeAttr("disabled");
		$("#submitBtn").blur();
		swal("密码不能为空");

		return false;
	}
	var param = {
		cardNum : cardNum,
		cardPass : cardPass
	}
	$.post("ScanQRCode/prepaidCode.do", param, function(data) {
		if (data.messageid == '1900') {
			window.location.href = "./return.do?url=scanCode";
			return;
		} else {
			$('#submitBtn').removeAttr("disabled");
			$("#submitBtn").blur();
			swal(data.messageid + data.message);
			return;

		}

	});

	// $("#cardFrom").submit();
}
