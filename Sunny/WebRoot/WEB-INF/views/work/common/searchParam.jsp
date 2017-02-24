<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 选择规格 -->
	<div class="row triebrand">
	<div class="col-sm-12">
		<div class="specImg hidden-xs <c:if test ="${!empty wholesale and wholesale eq 1}"> hide</c:if>"></div>
		<label class="col-sm-1 brand hidden-xs p-x">轮胎规格:</label>
			<div class="col-sm-2 form-group groupbox hidden-xs p-l">
				<select class="form-control tireWidthSelect">
					<option selected value="">胎面宽</option>
				</select>
			</div>
			<div class="col-sm-2 form-group groupbox hidden-xs p-l">
				<select class="form-control tireRatioSelect">
					<option selected value="">扁平比</option>
				</select>
			</div>
			<div class="col-sm-2 form-group groupbox hidden-xs p-l">
				<select class="form-control tireRSelect">
					<option selected value="">直径</option>
				</select>
			</div>
		<!-- 搜索框 -->
		<div class="row">
			<div class="col-sm-5 col-xs-8 col-xs-offset-1 p-l">
				<input type="search" class="form-control" id="search" placeholder="手动输入规格查询" onkeypress="EnterPress(event)" onkeydown="EnterPress()">
			</div>
			<div class="col-xs-3 col-sm-1"><button type="button" class="btn btn-primary search">查询</button></div>
		</div>
	</div>
		<div class="col-sm-12  hotsearch <c:if test ="${!empty wholesale and wholesale eq 1}"> hide</c:if>">
				<div class="col-sm-11 col-sm-offset-1 hotsearch">
						<div class="col-sm-1 brand p-l">热门规格:</div>
						<ul class="list-inline hotmodal">
							<li class="active"><a href="javascript:;" onclick="searchByTireType('','','',this)">不限</a></li>
							<li><a href="javascript:;" onclick="searchByTireType('195','65','15',this)">195/65R15</a></li>
							<li><a href="javascript:;" onclick="searchByTireType('205','55','16',this)">205/55R16</a></li>
							<li><a href="javascript:;" onclick="searchByTireType('195','55','15',this)">195/55R15</a></li>
							<li><a href="javascript:;" onclick="searchByTireType('185','60','14',this)">185/60R14</a></li>
							<li><a href="javascript:;" onclick="searchByTireType('195','60','15',this)">195/60R15</a></li>
							<li><a href="javascript:;" onclick="searchByTireType('215','70','15',this)">215/70R15</a></li>
							<li><a href="javascript:;" onclick="searchByTireType('185','65','14',this)">185/65R14</a></li>
							<li><a href="javascript:;" onclick="searchByTireType('185','60','14',this)">195/60R14</a></li>
							<li><a href="javascript:;" onclick="searchByTireType('175','65','14',this)">175/65R14</a></li>
							<li><a href="javascript:;" onclick="searchByTireType('215','55','16',this)">215/55R16</a></li>
							<!-- <li><a href="javascript:;" onclick="searchByTireType('185','65','15',this)">185/65R15</li>
 -->
							
							<%-- <c:forEach items="${hotSearch}" var="hot" varStatus="status">
								<li><a href="javascript:;" onclick="searchByTireType('${hot.wide}','${hot.flat}','${hot.diameter}',this)">${hot.wide}/${hot.flat}<c:if test="${!empty hot.structure}">${hot.structure}</c:if><c:if test="${empty hot.structure}">R</c:if>${hot.diameter}</a></li>
							</c:forEach> --%>
						</ul>
						<div class="col-sm-1 brand p-l">热搜品牌:</div>
						<ul class="list-inline hotbrand">
							<li class=active><a href="javascript:;" onclick="searchByBrand('',this)">不限</a></li>
							<li><a href="javascript:;" onclick="searchByBrand('1001',this)">米其林</a></li>
							<li><a href="javascript:;" onclick="searchByBrand('1005',this)">固特异</a></li>
							<li><a href="javascript:;" onclick="searchByBrand('1006',this)">马牌</a></li>
							<li><a href="javascript:;" onclick="searchByBrand('1007',this)">倍耐力</a></li>
							<li><a href="javascript:;" onclick="searchByBrand('1004',this)">普利司通</a></li>
							<li><a href="javascript:;" onclick="searchByBrand('1023',this)">邓禄普</a></li>
							<li><a href="javascript:;" onclick="searchByBrand('1009',this)">韩泰</a></li>
							<li><a href="javascript:;" onclick="searchByBrand('1015',this)">佳通</a></li>
							<li><a href="javascript:;" onclick="searchByBrand('1008',this)">优科豪马（横滨）</a></li>
							<li><a href="javascript:;" onclick="searchByBrand('1017',this)">玲珑</a></li>
							<li><a href="javascript:;" onclick="searchByBrand('1087',this)">双丰</a></li>
							<%-- <c:forEach items="${hotBrand}" var="brand" varStatus="status" >
								<li><a href="javascript:;" onclick="searchByBrand('${brand.brandId}',this)">${brand.brand}</a></li>
							</c:forEach> --%>
						</ul>
				</div>
		</div>
		
		<div class="col-sm-12 brandlist">
			<div class="col-sm-1 p-x brand">轮胎品牌:</div>
			<ul class="list-inline col-sm-11 hotbrand">
				<li  id="brandbx" <c:if test="${empty brandId or brandId==''}">class=active</c:if>><a href="javascript:;" onclick="searchByBrand('',this)">不限</a></li>
				<li <c:if test="${empty brandList or brandList.size() lt 30}">class="hide" </c:if>>已选择：<a href="javascript:;" id="yxzbrand">无</a></li>
				<c:forEach items="${brandList}" var="brand" varStatus="status" >
				<c:if test="${brandStr ne brand.initial}">
				<br>
					<c:set var="brandStr" value="${brand.initial}"></c:set>
					<li class="prompt"><a href="#">${brandStr}</a></li>
				</c:if>
					<li <c:if test='${brandId eq brand.brandId}'>class=active</c:if>><a href="javascript:;" onclick="searchByBrand('${brand.brandId}',this)">
					${brand.brand}</a></li>
				</c:forEach>
			</ul>
			<c:if test="${brandList.size() gt 29}">
				<div class="propSwitch">
					<span class="in" style="opacity: 1;">更多选项 <b>${flag_}∨</b></span>
				</div>
				<br>
			</c:if>
		</div>
	</div>
	<script src="${ctx}/static/js/chxt/searchParam.js"></script>
	<script type="text/javascript">
	//保存热搜词
	var saveHotSearch = function(){
		/* if(isNull(searchType)){
			return;
		}
		if(!isEmpty(wide)&&!isEmpty(flat)&&!isEmpty(diameter)){
			getParamsData();
			$ybtCAjax({
				url:"${ctx}/work/chxt/admin/saveHotStandard",
				type:'post',
				data:searchParams+"&type="+searchType,
				dataType: "json",
				success:function(data){
				}
			});
		} */
	};
	</script>