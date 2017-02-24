$(function() {
	$("#cardnumtext").hide();
	$("#cardnumselect select").change(function() {
		if (this.value == 'more') {
			$("#cardnumselect").hide();
			$("#cardnumtext").show();
		}
	});

	$("#price_input").hide();

	$("#price_select select").change(function() {
		if (this.value == 'more') {
			$("#price_select").hide();
			$("#price_input").show();
		}
	});
	var time = $("#form_time").text();
	var newdate = new Date();
	var d1 = new Date(time.replace(/\-/g, "\/"));
	if (d1 < newdate) {
		$("#form_time").css("color", "red");
		$("#form_ul li").eq(1).remove();
		$("#form_ul li").eq(3).remove();
		$("#submitbutton").eq(4).remove();
		$("#form_ul").append("<li><p><span>卡已过期</span></p></li>");
	}
});

function issubmit() {
	var cardnumtext = $("#cardnumselect select").val();
	if (cardnumtext == 'more') {
		cardnumtext = $("#cardnumtext input").val();
	}
	if (!cardnumtext) {
		swal("请输入张数");
		return;
	}
	var price = $("#price_select select").val();
	if (price == 'more') {
		price = $("#price_input input").val();
	}
	if (!price) {
		swal("请输入单价");
		return;
	}

	var param = {
		form_card : form_card,
		form_cardnum : form_cardnum,
		form_time : form_time,
		cardnumtext : cardnumtext,
		price : price
	}
	$.post("../ScanQRCode/qrCodeByUrl.do", param, function(data) {
		if(data.messageid=='2199'){
			var url=data.message;
			window.location.href="../return.do?url=qrCardUrl", "_self";;
			return;
		}
		swal(data.messageid + data.message);
	});

}