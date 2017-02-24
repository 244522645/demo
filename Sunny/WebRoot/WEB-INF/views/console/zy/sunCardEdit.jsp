<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
  <%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
   	  <script>
	   	var imgId;
		var imgUrl;
		var imgName;
   	  	$().ready(function(){
   	  		
				$.ajaxSetup({ cache: false }); //解决ajax缓存结果问题 
				$("#myform").validate({
					onkeyup: false
				});
				var msg = '${msg}';
				if(msg=='true'){
					showMsg(true,'恭喜！修改成功');
				}else if(msg=='false'){
					showMsg(false,'抱歉！修改失败');
				}
		});
   

     
   	  </script>

<%-- <script>
	$(function() {
		$howWeek : true,
			changeMonth : true,
			changeYear : true,
			showOtherMonths : true,
			selectOtherMonths : true,
			firstDay : 1
		});
	});
</script>
--%>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav">阳光卡管理</div>
		<div class="title_h2">
			修改阳光卡
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
        <div >
        	<form action="${ctx}/console/zy/suncard/put" method="post" id="myform">
        		<input type="hidden" name="page" value="${page}">
        		<!--编辑  -->
				<table class="fromtable">
					<tr>
						<th class="top_th"><a href="#" class="tongji"><em>修改</em><span></span></a></th>
					</tr>
					<tr >
						<td class="fromtd">
							<table class="contertable">
									<Tr>
										<td class="label">
											卡号<font color="red" size="4">*</font>：
										</td>
										<td>
										<input type="hidden" name="id" value="${card.id}">
											<input name="title" value="${card.number}" type="text"
												class=" text" size="50" readonly="readonly">
										</td>
										<td class="label">
											失效时间<font color="red" size="4">*</font>:
										</td>
										<td>
											<input onClick="WdatePicker()" name="failureTime"
												value="<fmt:formatDate value="${card.failureTime}"/>" type="text" class="text Wdate required"
												size="50">
										</td>
									</Tr>
									<Tr>
										<td class="label">
											卡类型<font color="red" size="4">*</font>：
										</td>
										<td>
											<input name="title" value="${card.type}" type="text" readonly="readonly"
												class=" text" size="50">
										</td>
										<td class="label">
											绑定人<font color="red" size="4">*</font>：
										</td>
										<td>
											<input name="address" value="${card.userId}" type="text"
												class=" text required" size="50">
										</td>
									</Tr>	
									
									<Tr>
										<td class="label">
											状态<font color="red" size="4">*</font>：
										</td>
										<td>
											<input name="title" value="${card.status}" type="text" readonly="readonly
												class=" text" size="50">
										</td>
										<td class="label">
											绑定时间<font color="red" size="4">*</font>：
										</td>
										<td>
											<input onClick="WdatePicker()" name="bindingTime"
												value="<fmt:formatDate value="${card.bindingTime}"/>" type="text" class="text Wdate required"
												size="50">
										</td>
									</Tr>	
																	
								</table>
							<div class="buttondiv"><button type="button" class="btn btn-primary" onclick="goToUrl('${ctx}/console/zy/suncard')">返回</button>&nbsp;&nbsp;
							                       <button id="submit-btn" class="btn btn-primary" type="button ">提交</button> </div>
						</td>
						
					</tr>
					
				</table>
			</form>
        </div>
	</div>

 </body>

</html>
