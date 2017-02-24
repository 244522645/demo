var pagenum;
var currentPage;
var Login = function() {

	return {
		// main function to initiate the module
		init : function() {
			pagenum = 1;
			currentPage = 10;
			$("#starttime").val(getNowFormatDate());
			$("#endtime").val(getNowFormatDate());
			
			changetime();
			findHistory();
		}

	};

}();

function changetime() {
	$("#time .btn").eq(0).click(function() {
		$("#starttime").val(getNowFormatDate());
		$("#endtime").val(getNowFormatDate());
	});
	$("#time .btn").eq(1).click(function() {
		$("#starttime").val(getyesterdayFormatDate());
		$("#endtime").val(getyesterdayFormatDate());
	});
	$("#time .btn").eq(2).click(function() {
		$("#starttime").val(getnowmouthFormatDate());
		$("#endtime").val(getNowFormatDate());
	});
	$("#time .btn").eq(3).click(function() {
		$("#starttime").val(getstartmonthdata());
		$("#endtime").val(getlastmonthdata());
	});
}

function findHistory() {
	page = (pagenum - 1) * currentPage;
	pagesize = (pagenum - 1) * currentPage + 10;
	$("#find-btn").click(function() {
		var starttime = $("#starttime").val();
		var endtime = $("#endtime").val();
		endtime=dateAddDays(endtime,1);
		var param = {
			starttime : "2013-11-15",
			endtime : "2013-11-16",
			page : page,
			pagesize : pagesize
		}
		$.post("PosOrders/fingByTime.do", param, function(data) {
			var cinemapricecount=0;
			var posordercount=0;
			$.each(data,function(i,p){
				
				$("#tabletbody").append("<tr><th style='text-align: center;'>"+p.cinemaprice+"</th><th style='text-align: center;'>"+p.posordercount+"</th><th style='text-align: center;'>"+(p.cinemaprice*p.posordercount)+"</th></tr>");
				cinemapricecount=cinemapricecount+p.posordercount;
				posordercount=posordercount+(p.cinemaprice*p.posordercount);
			});
			$("#tabletbody").append("<tr><th style='text-align: center;'>合计</th><th style='text-align: center;'>"+cinemapricecount+"</th><th style='text-align: center;'>"+posordercount+"</th></tr>");
		});
	});
}

// 获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

// 获取昨天时间，格式YYYY-MM-DD
function getyesterdayFormatDate() {
	var date = new Date();
	date.setDate(date.getDate() - 1);
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

// 获取本月时间时间，格式YYYY-MM-DD
function getnowmouthFormatDate() {
	var date = new Date();
	date.setDate(1);
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

// 获取上月最后时间，格式YYYY-MM-DD
function getlastmonthdata() {
	var date = new Date();
	date.setDate(0);
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

// 获取上月最后时间，格式YYYY-MM-DD
function getstartmonthdata() {
	var date = new Date();
	date.setMonth(date.getMonth() - 1);
	date.setDate(1);
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}
// 日期加天数的方法
function dateAddDays(dataStr,dayCount) {
    var strdate=dataStr; // 日期字符串
    var isdate = new Date(strdate.replace(/-/g,"/"));  // 把日期字符串转换成日期格式
    isdate = new Date((isdate/1000+(86400*dayCount))*1000);  // 日期加1天
    var pdate = isdate.getFullYear()+"-"+(isdate.getMonth()+1)+"-"+(isdate.getDate());   // 把日期格式转换成字符串
 
    return pdate;
}