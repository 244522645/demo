<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<html>
<head>
<title>sorry! 你访问的页没找到！</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type=text/css>
td {
	font-size: 9pt;
	font-family: "arial", "helvetica", "sans-serif"
}

body {
	font-size: 9pt;
	font-family: "arial", "helvetica", "sans-serif"
}

.tbl1 {
	border-right: #3f5294 1px solid;
	border-top: #3f5294 1px solid;
	font-size: 9pt;
	border-left: #3f5294 1px solid;
	border-bottom: #3f5294 1px solid
}

.td1 {
	border-right: #ffffff 0px solid;
	border-top: #ffffff 1px solid;
	border-left: #ffffff 1px solid;
	border-bottom: #ffffff 0px solid
}
</style>

<style type=text/css>
a {
	color: #000000;
	text-decoration: none
}

a:hover {
	color: #ff0000;
	text-decoration: none
}
</style>

<style type=text/css>
.style6 {
	font-family: "courier new", courier, mono
}
</style>

<meta content="mshtml 6.00.2900.2180" name=generator>

</head>
<body bgcolor=#ffffff>
	<p></p>
	<div align=center>
		<table height=257 cellspacing=0 cellpadding=0 width=306
			background="<%=path%>/images/404.jpg" border=0>
			<tbody>
				<tr>
					<td valign=top background=""><br>
						<table cellspacing=0 cellpadding=0 width="100%" border=0>
							<tbody>
								<tr>
									<td width="14%"></td>
									<td width="86%">
										<table style="filter: glow(color = #ffffff, strength = 5)"
											width="100%" align=center>
											<tbody>
												<tr>
													<td  height=14><b><span class=style6><font
																color=#ff0000 size=4>sorry!</font></span></b></td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<div align=center>
											<table cellspacing=2 cellpadding=0 width="100%" align=center
												border=0>
												<tbody>
													<tr>
														<td height=22>5555~~~，不知道哪个缺德的把这个页面删除了。</td>
													</tr>

													<tr>
														<td style="padding-right: 8px">
															<p>我们定于5秒钟之后自动引爆炸弹，你不回首页还呆着啊！</p>
														</td>
													</tr>
												</tbody>
											</table>
											<br>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<p></p></td>
				</tr>
				<tr>
					<td valign=bottom align=right><a href="#"
						onClick='javascript :history.back(-1);'><font color=#ff0000>[由此返回上一页]</font></a></td>

				</tr>

				<tr>
					<td valign=bottom align=right><a href="login.do" target="_top"><font
							color=#ff0000>[重新登录]</font></a></td>

				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>
