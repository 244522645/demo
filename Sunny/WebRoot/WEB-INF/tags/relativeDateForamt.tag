<%@tag pageEncoding="UTF-8"%>
<%@tag import="com.ybt.common.util.RelativeDateFormat;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="dateTime" type="java.util.Date" required="true"%>
<c:set var="relativeDate" scope="request" value="<%=RelativeDateFormat.format(dateTime)%>" />
${relativeDate}