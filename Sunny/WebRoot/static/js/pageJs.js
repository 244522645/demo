/*
JS分页组件 
 */

var  Pagination = function Pagination(arg){
	this.p = 0;
	this.ps = 10;
	this.currentPage = 1;
	this.maxPageMun = 0;
	
    this.callback = arg.callback;//回调
	
	if(arg.pagination)
		this.pagination = arg.pagination;
}


function myGoPage(thisp, thisps,fun) {
	var p = thisp;
	var ps = thisps;
	fun(p,ps);
	$("#pagination").children().attr("class", "disabled");
	$("#pagination").children().eq(thisp).attr("class", "active");
}

Pagination.prototype.setPagination = function(page){
	//console.log(page);
	var paginationSize = page.pageSize;
	var current = page.pageNo;
	var begin = Math.max(1, current - paginationSize / 2);
	var end = Math.min(begin + (paginationSize - 1), page.totalPages);

	$("#"+this.pagination).empty();

	var pageHTML = '<div class="page">' + '	<ul>'
			+ '		<li class="disabled"><a>共' + page.totalCount + '条数据</a></li>';
	if ((page.hasNext && current != 1) || (current == end && current != 1)) {

		pageHTML = pageHTML + '<li><a href="javascript:myGoPage(1,'
				+ page.pageSize + ','+this.callback+');">&lt;&lt;</a></li>'
				+ '<li><a href="javascript:myGoPage(' + (current - 1) + ','
				+ page.pageSize + ','+this.callback+');">&lt;</a></li>';
	} else {
		pageHTML = pageHTML
				+ '<li class="disabled"><a href="#">&lt;&lt;</a></li>'
				+ '<li class="disabled"><a href="#">&lt;</a></li>';
	}

	for (i = begin; i <= end; i++) {
		if (i == current) {
			pageHTML = pageHTML
					+ '<li class="active"><a class="pg_selected" href="javascript:myGoPage(' + i
					+ ',' + page.pageSize + ','+this.callback+');">' + i + '</a></li>';
		} else {
			pageHTML = pageHTML + '<li><a href="javascript:myGoPage(' + i + ','
					+ page.pageSize + ','+this.callback+');">' + i + '</a></li>';
		}
	}

	if (page.hasNext) {
		pageHTML = pageHTML + '<li><a href="javascript:myGoPage('
				+ (current + 1) + ',' + page.pageSize + ','+this.callback+');">&gt;</a></li>'
				+ '<li><a href="javascript:myGoPage(' + page.totalPages + ','+ page.pageSize + ','+this.callback+');">&gt;&gt;</a></li>';
	} else {
		pageHTML = pageHTML + '<li class="disabled"><a href="#">&gt;</a></li>'
				+ '<li class="disabled"><a href="#">&gt;&gt;</a></li>';
	}
	pageHTML = pageHTML + '	</ul>' + '</div>';

	$("#"+this.pagination).append(pageHTML);

}