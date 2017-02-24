<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-inverse col-sm-2 m-x p-x sidenav">
	    <div class="navbar-header">
	        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-menu" aria-expanded="false">
		        <span class="sr-only">切换导航条</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
	        </button>
	       <button class="visible-xs navbar-toggle" style="border: 0;font-size:16px; height:35px">
	       <a class="shopcart" href="${ctx}/work/chxt/wholesale/shoppingCart"><i class="fa fa-shopping-cart">&nbsp;</i>
	       <c:if test="${cartNum gt 0}"><span class="badge">${cartNum}</span></c:if></a></button>
	        <a class="navbar-brand visible-xs" href="#">芸备胎采购系统</a>
	    </div>
		<div class="collapse navbar-collapse p-x" id="navbar-menu">
	     <ul class="nav">
		     <li class="hidden-xs">
		     	<div  class="text-center">
			    	<img class="img-circle headimg" src="<c:if test="${!empty account.headPhotos}">${account.headPhotos.titleImgWebPath}</c:if>" onerror="this.src='${ctx}/static/images/chxt/headimg.jpg'"/>
				</div>
				<p class="text-center"><a class="userInfo">欢迎：<span><c:if test="${empty account.username}">${account.phone}</c:if>${account.username}</span></a></p>
			</li>
	        <li><a class="userInfo" href="${ctx}/work/chxt/admin/userinfo"><i class="fa fa-user"></i>&nbsp;个人资料</a></li>
	        <li><a class="account"  href="${ctx}/work/chxt/account/myAccount"><i class="fa fa-credit-card"></i>&nbsp;我的钱包</a></li>
	        <li><a class="shopcart" href="${ctx}/work/chxt/wholesale/shoppingCart"><i class="fa fa-cart-plus"></i>&nbsp; 购 物 车
	        	<c:if test="${cartNum gt 0}"><span class="badge">${cartNum}</span></c:if></a></li>
	        <li><a class="orderlist"><i class="fa fa-file-text-o"></i>&nbsp;订单列表
	        <c:if test="${buyNum+sellNum gt 0}"><span class="badge">${buyNum+sellNum}</span></c:if></a></li>
	        <ul class="orderUl">
	        	<li><a class="order" href="${ctx}/work/chxt/order/buyList">我的调货
	        	<c:if test="${buyNum gt 0}"><span class="badge">${buyNum}</span></c:if></a></li>
  				<li><a class="sell" href="${ctx}/work/chxt/order/sellList">我的出售
  				<c:if test="${sellNum gt 0}"><span class="badge">${sellNum}</span></c:if></a></li>
	        </ul>
	        <li><a class="pfcgList" href="${ctx}/work/chxt/wholesale/index"><i class="fa fa-truck"></i>&nbsp;采购平台</a></li>
	        <li><a class="tuanList" href="${ctx}/work/chxt/search/index"><i class="fa fa-circle-o-notch"></i>&nbsp;调货平台</a></li>
	        <li><a class="stock" href="${ctx}/work/chxt/admin/myStockList"><i class="fa fa-university"></i>&nbsp;库存管理</a></li>
	        <ul class="stockUl">
			  	<!-- <li><a class="band">固特异</a></li>
			  	<li><a class="">添加品牌名称分类</a></li> -->
			</ul>
			<%-- <li>
				<a class="activity" href="${ctx}/work/chxt/activity/activityApril">
					<i class="fa fa-child"></i>&nbsp;
					<span>优惠活动<i class="fa fa-circle text-danger"></i></span>
				</a>
			</li> --%>
	    </ul>
    </div>
</nav>
<script type="text/javascript">
	$("#navbar-menu a").bind("click",function(){
		setCookie("left_menu_defult",this.className,24,"/");
	});
	$(".navbar-toggle a").bind("click",function(){
		setCookie("left_menu_defult",this.className,24,"/");
	});
	$("."+getCookieValue("left_menu_defult")).parent().addClass("active");
	
	function slidel(A,B){  //函数封套
		$(A).click(function(){
			$(B).slideToggle();
		});
		//如果列表下菜单被选中，则打开列表
		if($(B+" .active").length>0){
			$(B).slideToggle();
		}
	};
	slidel('.orderlist','.orderUl');  //订单列表显示切换
	slidel('.stock','.stockUl');      //库存管理显示切换
</script>
