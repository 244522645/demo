<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ taglib prefix="ybt" uri="/sun-tags"%> --%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="lbsJsKey" value="e17ff0ececd561c55e3d1bad28234955"/>
<c:set var="currentUser" value="<%=org.apache.shiro.SecurityUtils.getSubject().getPrincipal() %>" ></c:set>
<c:set var="siteName">${config.siteName}</c:set>