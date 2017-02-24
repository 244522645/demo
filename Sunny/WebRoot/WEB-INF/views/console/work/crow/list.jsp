<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
  <style type="text/css">
    .nopass{
    
background: #ff0909;
    padding: 18px 25px;
    border-radius: 97px;
    color: #ffffff;
    font-size: 33px;
    /* font-style: oblique; */
    font-weight: bold;
    }
    .pass{
            background: #6fb331;
    padding: 14px 21px;
    border-radius: 97px;
    color: #ffffff;
    font-size: 33px;
    /* font-style: oblique; */
    font-weight: bold;
    }
  </style>
  </head>
 <body>
	<div class="bodyMain">
		<div class="top_subnav"> 闻鸡起五-打卡</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="title_h2">
			搜索
		</div>
		<p class="mylinep" style="margin-top:0;">
		</p>
		<form action="${ctx}/console/downRecord/list" >
			<div class="filed fl">
				<label>email：</label>
				<input name="filter_LIKES_title" value="${param.filter_LIKES_email}" type="text"  size="15">
			</div>
			<div class="filed fl">
				<label>处理状态：</label>
				<input name="filter_LIKES_title" value="${param.filter_LIKES_state}" type="text"  size="15">
			</div>
			<div class="filed fl">
				<button class="btn btn-primary" type="submit">查询</button>
			</div>
		</form>
		<div class="tablelist">
		 <form method="post" id="adminFrom" action="${ctx}/console/downRecord/list">
			<table class="table">
				<tr>
				<th colspan="9" class="top_th"><a href="#" class="tongji"><em>闻鸡起五-打卡</em><span></span></a></th>
				</tr>
				<tr>
					<th style="width:30px"><input style="width:20px;" onclick="selectedCheckbox(this,'id')" type="checkbox" value=""/></th>
					<th>打卡</th><th>提交时间</th><th>操作</th>
				</tr>

				<c:forEach items="${page.result}" var="entity" varStatus="status">
				<tr id="tr_${entity.id}">
						<td>
							<input style="width: 20px;" name="photoIds" type="checkbox" value="${entity.id}"/>
						</td>
						<td>
							<img alt="打卡" src="${entity.images.localPath}_500.jpg" width="400">
						</td>
						<td>
							<fmt:formatDate value="${entity.createTime}" pattern="MM月dd日 HH:mm"/>
						</td>
						<td>
							<a class="pass" href="javascript:pass('${entity.id}');">√</a>
							&nbsp;&nbsp;&nbsp;<span style="font-size:18px;color:#333;">OR</span>&nbsp;&nbsp;&nbsp;
							<a class="nopass" href="javascript:notPass('${entity.id}');">×</a>
						</td>
				</tr>
				</c:forEach>
			</table>
				<div style="float:left;"><div   onclick="return delAll();" class="btn btn-primary" >删除</div>&nbsp;&nbsp;</div>
			
			<tags:pagination page="${page}" paginationSize="20"/>
		</form>
		</div>
		
	</div>
 </body>
<script type="text/javascript">

function pass(punchId){
	$.post(ctx+"/console/punch/pass",{pid:punchId,pass:true},function(response){
		if(1 == response.state){
			$('#tr_'+punchId).remove();
		}
	});
}

function notPass(punchId){
	$.post(ctx+"/console/punch/pass",{pid:punchId,pass:false},function(response){
		if(1 == response.state){
			$('#tr_'+punchId).remove();
		}
	});
}

</script>
</html>