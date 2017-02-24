<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:if test="${!empty recordList}">
<c:forEach items="${recordList}" var="record" varStatus="status">
<blockquote style="<c:if test="${status.index %2>0}"> background-color: #f6fcff;</c:if>">
    <p style="font-size:14px;"><c:if test="${empty record.order}">
	   	 	<c:if test="${empty record.title and record.type eq 5}">芸备胎自动转账</c:if>${record.title}
	    </c:if>
   	  	<c:if test="${!empty record.order}">
			<a href="javascript:window.open('${ctx}/work/chxt/order/sellorderinfo?orderId=${ record.order.id}')" <c:if test="${record.type eq '4'}">style='color:#a94442;'</c:if>><span class="hidden-xs">${record.order.orderNo} -</span> ${record.title}
			</a>
		</c:if>
	</p>
     <footer><span style="font-size:12px;">${record.status}&nbsp;</span><fmt:formatDate value="${record.createTime}" pattern="yyyy.MM.dd HH:mm"/>
     <span style="font-size:15px;" class="pull-right text-danger"><c:if test="${empty record.moneyStr}">- ${record.money}</c:if>${record.moneyStr}</span>
     </footer>
</blockquote>
</c:forEach>
</c:if>
<c:if test="${empty recordList}">您还未使用在线支付进行交易</c:if>