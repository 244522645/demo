<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE>${config.siteName}</TITLE>
</HEAD>
<FRAMESET border=0 frameSpacing=0 rows="97, *" frameBorder=0 >
	<FRAME name=frameHeader src="${ctx}/console/menu/top?id=0&herf=0" frameBorder=0 noResize scrolling=no>
	<FRAMESET cols="210, *" id="framesetMainId">
		<FRAME name=frameLeft id="frameLef" src="" frameBorder=0 noResize>
		<FRAME name=frameMain src="${ctx}/console/main/main" frameBorder=0 noResize scrolling=yes>
	</FRAMESET>
</FRAMESET>
<noframes>
</noframes>
</HTML>
