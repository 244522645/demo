<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" type="com.ybt.common.util.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>
<%
int current =  page.getPageNo();
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

//========这段代码未测试======
int total = end;
begin = current-4;
end   = current+4;

if(begin<1){
	begin = 1;
	end =9;
}
if(end>total){
	end = total;
}
if(end-begin <4 ){
	begin =1;
	end = total;
}
//========这段代码未测试======

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
%>
<div class="page">
	<ul>
		 <% if (page.isHasPre()){%>
               	<li><a class="pg_index" href="?page=1&order=${order}&sortType=${sortType}&${searchParams}">首页</a></li>
                <li><a class="pg_index" href="?page=${current-1}&order=${order}&sortType=${sortType}&${searchParams}">上一页</a></li>
         <%}else{%>
                <li class="disabled"><a class="pg_index" href="javascript:void(0);">首页</a></li>
                <li class="disabled"><a class="pg_index" href="javascript:void(0);">上一页</a></li>
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <li class="active"><a class="pg_selected" href="?page=${i}&order=${order}&sortType=${sortType}&${searchParams}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="?page=${i}&order=${order}&sortType=${sortType}&${searchParams}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if (page.isHasNext()){%>
               	<li><a class="pg_index" href="?page=${current+1}&order=${order}&sortType=${sortType}&${searchParams}">下一页</a></li>
                <li><a class="pg_index" href="?page=${page.totalPages}&order=${order}&sortType=${sortType}&${searchParams}">末页</a></li>
         <%}else{%>
                <li class="disabled"><a  class="pg_index" href="javascript:void(0);">下一页</a></li>
                <li class="disabled"><a  class="pg_index" href="javascript:void(0);">末页</a></li>
         <%} %>

	</ul>
	<p>共有 ${page.totalCount } 条数据，当前第 ${page.pageNo }  页</p>
</div>

