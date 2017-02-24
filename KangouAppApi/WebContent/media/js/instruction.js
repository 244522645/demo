$(function() {
	$(".content").hide();
	$(".content").eq(0).show();
	$("button").click(function() {
		$(".content").hide();
		$(".content").eq(0).show();
	});
});

function ul_onclick(value) {

	if (value == 1) {
		$(".content").hide();
		$(".content1").show();
		return;
	}
	if (value == 2) {
		$(".content").hide();
		$(".content2").show();
		return;
	}
	if (value == 3) {
		$(".content").hide();
		$(".content3").show();
		return;
	}
	if (value == 4) {
		$(".content").hide();
		$(".content4").show();
		return;
	}
	if (value == 5) {
		$(".content").hide();
		$(".content5").show();
		return;
	}
	if (value == 6) {
		$(".content").hide();
		$(".content6").show();
		return;
	}
	if (value == 7) {
		$(".content").hide();
		$(".content7").show();
		return;
	}
}