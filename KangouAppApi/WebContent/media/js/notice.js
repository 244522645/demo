var pagenum;
var currentPage;
var page;
var pagesize;
var notice = function() {

	return {
		// main function to initiate the module
		init : function() {
			pagenum = 1;
			currentPage = 10;
			$("#notice li").remove();
			getnotice();
		}

	};

}();

function clickBtn() {
	pagenum = pagenum + 1;
	getnotice();
}

function getnotice() {
	page = (pagenum - 1) * currentPage;
	pagesize = (pagenum - 1) * currentPage + 10;
	var param = {
		page : page,
		pagesize : pagesize
	};
	$
			.post(
					"notice/getNotices.do",
					param,
					function(data) {
						if (data == null || data == "") {
							swal("没有更多了");
						}

						$
								.each(
										data,
										function(i, p) {
											if (p.isread == 0) {
												$("#notice")
														.append(
																"<li style='background: #FFFFFF;margin-left: 30px;margin-right: 30px;'><div><a href='notice/findNotice.do?nid="+p.nid+"' style='border: 0px; border-left: 0px;'><i class='icon-file-text'></i>"
																		+ p.ntitle
																		+ " &nbsp;(未读)</a></div></li>");
											} else {
												$("#notice")
														.append(
																"<li style='background: #FFFFFF;margin-left: 30px;margin-right: 30px;'><div><a href='notice/findNotice.do?nid="+p.nid+"' style='border: 0px; border-left: 0px;'><i class='icon-file-text'></i>"
																		+ p.ntitle
																		+ "</a></div></li>");

											}

										});

					});
}

