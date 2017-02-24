<%@tag pageEncoding="UTF-8"%>
<%@tag import="com.ybt.common.util.TodayDateFormat;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="dateTime" type="java.util.Date" required="true"%>
<c:set var="relativeDate" scope="request" value="<%=TodayDateFormat.format(dateTime)%>" />
${relativeDate}