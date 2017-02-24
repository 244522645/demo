<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="imgSum" type="java.lang.String" required="true"%>
<%@ attribute name="imgData" type="java.lang.Object" required="true"%>

<script>
	var ctx = '${pageContext.request.contextPath}';
	//imgSumArray["${id}"]=${imgSum};
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowSum" value="0"/>

<!-- 图片 -->
<div class="row">
	<div class="col-xs-12 col-sm-12"  id="${id}imageShowID" >
		<!-- 顶部用户退出行 -->
		<c:if test="${!empty imgData and imgSum<=1}">
			<c:set var="nowSum" value="1"/>
			<div class="shop-img showimg">
				<span class="fa fa-remove" imgSum="${imgSum}" imgId="${id}" id="${imgData.id}"></span>
				<img src="${imgData.imgWebPath}" class="img-index" onerror="this.src='${ctx}/static/images/chxt/headimg.jpg'"/>
			</div>
		</c:if>
		<c:if test="${!empty imgData and imgSum>1}">
			<c:forEach items="${imgData}" var="up" varStatus="upload">
				<c:set var="nowSum" value="${nowSum/1+1}"/>
				<div class="shop-img showimg">
					<span class="fa fa-remove" imgSum="${imgSum}" imgId="${id}" id="${up.id}"></span>
					<img src="${up.imgWebPath}" alt="用户头像" class="img-index" onerror="this.src='${ctx}/static/images/chxt/headimg.jpg'">
				</div>
			</c:forEach>
		</c:if>
		<div id="upimgbut"  style="margin-top:5px">
			<input type="file" multiple="multiple" imgSum="${imgSum}"  id="${id}imageButID" style="display:none;" onChange="javascript:setImagePreview('${imgSum}');"> 
			<input type="button" <c:if test="${nowSum/1 >= imgSum/1}"> style="display:none;"</c:if> value="上传照片" onclick="uploadClick('${id}')" id="${id}showImageButID">
		</div>
	</div>
</div>


