<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
    <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
    <script>
	var ctx = '${pageContext.request.contextPath}';
	//朋友圈 参数
	 var com_share_title="${config.siteName}",
	 com_share_content="${config.description}",
	 com_share_link="${config.domainName}/wechat/letter"
	 domainName="${config.domainName}",
	 com_share_imgUrl="${config.domainName}/static/images/sun/logo-icon.png";
</script>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>订单信息</title>
   	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="我为阳光代言，传递正能量！">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>WeUI</title>
    <link rel="stylesheet" href="${ctx}/static/weui/style/weui.css"/>
    <link rel="stylesheet" href="${ctx}/static/weui/test.css"/>
        <script src="${ctx}/static/weui/zepto.min.js"></script>

     <style type="text/css">
        .weui-tab__panel__item{
            display: none;
        }
        .weui-tab__panel__item.weui-tab__panel__item_on{
            display:block;
        }
        .photo_item{
	        margin:0;
	        padding:0;
	        display: block;
        }
        .img-mask > .mask {
            position: absolute;
            left: 0;
            right: 0;
            bottom: 0;
            z-index: 10;
            height: auto;
            padding: 0 .5rem;
            color: #FFF;
        }
        .textshow {
            text-shadow: 2px 2px 2px #000;
        }
        .img-mask{
            padding: 0;
            margin: 0;
            margin-bottom: 4px;
        }
        .img-mask img{width:100%;}
        .img-mask:before{
            border:none;
        }
        .maskcheckbox{
             position: absolute;
            right: 0;
            bottom: 4px;
            z-index: 10;
            height: auto;
            padding: 0 .5rem;
            color: #FFF;
        }
        .tooler{
            position: fixed;
            bottom: 0;
            background: #fff;
                width: 100%;
            z-index: 99;
        }
        .tooler:before{
                content: " ";
            position: absolute;
            left: 0;
            top: 0;
            right: 0;
            height: 1px;
            border-top: 1px solid #D9D9D9;
            color: #D9D9D9;
            -webkit-transform-origin: 0 0;
            transform-origin: 0 0;
            -webkit-transform: scaleY(0.5);
            transform: scaleY(0.5);
        }
        .tooler a{
            width: 5rem;
            margin: 0;
        }
        .tooler_btn{
            margin-right: 2rem;
        }
        .photolist{
            margin-bottom: 5rem;
        }
    </style>
</head>
<body ontouchstart>
    <div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>

    <div class="container" id="container">
    
	    <div class="page page preview js_show">
	    <div class="page__hd">
	        <h1 class="page__title" id="orderTitle">订单信息</h1>
	        <p class="page__desc">有任何问题请致电 010-57283847</p>
	    </div>
	    <div class="page__bd">
	    	<c:forEach var="entity" items="${orders }" varStatus="status">
                  <div class="weui-form-preview">
		            <div class="weui-form-preview__hd">
		                <div class="weui-form-preview__item">
		                    <label class="weui-form-preview__label">订单  520A99${entity.number}</label>
		                    <em class="weui-form-preview__value" id="orderMoney">${entity.statusF}</em>
		                </div>
		            </div>
		            <div class="weui-form-preview__bd" id="orderContent">
		                <div class="weui-form-preview__item">
		                    <label class="weui-form-preview__label">商品</label>
		                    <span class="weui-form-preview__value">${entity.photo.title}|<fmt:formatDate value="${entity.photo.shootingTime }" pattern="yyyy/MM/dd"/> </span>
		                </div>
		                <div class="weui-form-preview__item">
		                    <label class="weui-form-preview__label">收货人</label>
		                    <span class="weui-form-preview__value">${entity.name} </span>
		                </div>
		                <div class="weui-form-preview__item">
		                    <label class="weui-form-preview__label">联系电话</label>
		                    <span class="weui-form-preview__value">${entity.phone} </span>
		                </div>
		                <div class="weui-form-preview__item">
		                    <label class="weui-form-preview__label">收货地址</label>
		                    <span class="weui-form-preview__value"> ${entity.address} </span>
		                </div>
		 				<div class="weui-form-preview__item">
		                    <label class="weui-form-preview__label">祝福语</label>
		                    <span class="weui-form-preview__value">${entity.bless} </span>
		                </div>
		            </div>
		        </div>
			</c:forEach>
	            <div class="weui-form-preview__ft">
	                <a id="agian" class="weui-form-preview__btn weui-form-preview__btn_primary"  href="javascript:;" >再来一个</a>
	            </div>
	        </div>
	        <div class="page__hd">
	       		 <p class="page__desc" style="color: red;">强烈建议您关注公众号“给点儿阳光” 以获取后续服务</p>
					<p class="page__desc" style="color: red;">注：公众号回复“生日阳光” 即可查询订单信息</p>
					<p class="page__desc" style="text-align: center; ">
						<img style="width: 50%;" alt="" src="/Sunny/static/images/sun/qrcode_cake1001.png">
					</p>
					<p class="page__desc" style="text-align: center; ">
						↑↑长按二维码关注↑↑
					</p>
	   		 </div>
	   		 <div class="weui-cells__title"> &nbsp;</div>
	   				<div class="weui-footer ">
	            		<p class="weui-footer__links">
	                		<a href="http://www.geidianyg.com" class="weui-footer__link">给点儿阳光</a>
	           			 </p>
	            		<p class="weui-footer__text">Copyright © 2016 geidianyg.com</p>
	       			 </div>
			</div>
	    </div>
<script type="text/javascript">

$(function(){
	$('#agian').on('click',function(){
			location.replace("${ctx}/wechat/cake/index?shopName=");
	});
});
</script>
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="${ctx}/static/weui/weui.min.js"></script>
    <script src="${ctx}/static/weui/test.js"></script>
</body>
	 <script type="text/javascript" src="${ctx}/static/js/sun/base.js?V=3.o"></script> 
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>	
</html>
