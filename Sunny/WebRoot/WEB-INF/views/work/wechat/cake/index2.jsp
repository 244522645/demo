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
    <title>下单-选择照片</title>
   	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="">
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

    <div class="container" id="container"></div>

    <script type="text/html" id="tpl_select">
<div class="page">
    <div class="page__bd" style="height: 100%;">
		 <div class="tooler weui-cell tooler_cell_btns">
                        <div class="tooler_btn weui-cell__bd">
                            <a href="javascript:;"  class="weui-btn weui-btn_default "  id="btn_preview">预览</a>
                        </div>
                        
                        <div class="tooler_btn weui-cell__ft">
                           <a href="javascript:;" data-id="input"  class="weui-btn weui-btn_default js_item" id="btn_select">确定</a>
                        </div>
                         
                    </div>
        <div class="weui-tab">
            <div class="weui-navbar">

                <div class="weui-navbar__item weui-bar__item_on">
                    选择照片
                </div>
                <div class="weui-navbar__item">
                    摄影师
                </div>
            </div>
            <div class="weui-tab__panel ">
                <div  class="weui-tab__panel__item weui-tab__panel__item_on">
                    <div class="weui-cells__title">以下都是今天早晨日出照片</div>
                    <div class="weui-cells weui-cells_checkbox photolist">
						<c:forEach var="entity" items="${photos }" varStatus="status">
						<a class="weui-cell weui-cell_access photo_item" href="javascript:;">
						<label class="weui-cell weui-check__label img-mask" for="s${entity.id}">  
                            <div class="mask textshow">
                                <p><fmt:formatDate value="${entity.shootingTime }" pattern="MM/dd"/> </p>
                               <p>${entity.provinceF} • ${entity.title}</p>
                            </div>
                            <img  dt="${entity.imgUrl}_y1080.jpg" src="${entity.imgUrl}_348x232.jpg">
                               <div class="maskcheckbox">
                                        <input local="${entity.localName}" addr="${entity.title}" dt='<fmt:formatDate value="${entity.shootingTime }" pattern="yyyy/MM/dd"/>' type="radio" class="weui-check" name="checkbox1" id="s${entity.id}" value="${entity.id}" <c:if test="${status.index eq 0}"> checked="checked"</c:if>>
                                        <i class="weui-icon-checked"></i>
                                </div>
						 </label>
                        </a>

						</c:forEach>
                    </div>
                </div>

                <div class="weui-tab__panel__item">
                   <article class="weui-article" style=" text-align: center;">
                        <h1>幕后英雄</h1>
 								<p>
   在你清晨
躺床上在睡觉时
我们的摄影师英雄们在为你拍摄日出照片。
  </p>
摄影师是留住时光的诗人
他们用冰冷的机械定格住了最温暖的瞬间
用相机，留住美好</p>
 <p>
 
汗水不会背叛你的付出——致我们的摄影师团队。
                                </p>
                        <section>
<hr style="margin:10px 0;">
                            <h2 class="title" style="text-align: center;">摄影师介绍</h2>

						<c:forEach var="entity" items="${graphers }" varStatus="status">
						<section>
                                <p>
                                    <img src="${entity.head.localPath}_500.jpg" alt="">
                                </p>
                                <h3>摄影师：${entity.name} &nbsp;&nbsp;<span> 负责拍摄地：${entity.tags}</span></h3>
                                <p>
                                   ${entity.introduce}
                                </p>
                                <p>
                                     <img src="${entity.work.localPath}_500.jpg" alt="">
                                  
                                </p>
                            </section>
							<br>
							<hr>
							<br>
							<br>
						</c:forEach>
                        </section>
                    </article>
				<div class="weui-footer">
            		<p class="weui-footer__links">
                		<a href="http://www.geidianyg.com" class="weui-footer__link">官网</a>
           			 </p>
            		<p class="weui-footer__text">Copyright © 2016 geidianyg.com</p>
       			 </div>
                              
                </div>
               
            </div>
          
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $('.weui-navbar__item').on('click', function () {
            $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
            $(".weui-tab__panel__item").eq($(this).index()).addClass('weui-tab__panel__item_on').siblings('.weui-tab__panel__item_on').removeClass('weui-tab__panel__item_on');
        	if($(this).index()==0){
				$(".tooler_cell_btns").show();
			}else{
				$(".tooler_cell_btns").hide();
			}
		});
		$('#btn_select').on('click',function(){
			var cbc = $('input[name="checkbox1"]:checked');
			go_page="input";
			pageManager.go(go_page);
			var inputhidden = "<input type='hidden' id='input_photoInfoLocal' name='photoInfoLocal' value='"+cbc.attr("local")+"'><input type='hidden' id='input_photoInfo' name='photoInfo' value='"+cbc.attr("addr")+'|'+cbc.attr("dt")+"'><input type='hidden' id='input_photo' name='photo' value='"+cbc.val()+"'>";
			
			 setTimeout(function () {
				$("#photo").html(cbc.attr("addr")+' '+cbc.attr("dt")+inputhidden);
            }, 500);
		});
		$('#btn_preview').on('click',function(){
			var cbc = $('input[name="checkbox1"]:checked');
			var imgs=cbc.attr('dt');
					wx.ready(function(){
						 wx.previewImage({
					  	    current: imgs, // 当前显示图片的http链接
					  	    urls: new Array(imgs), // 需要预览的图片http链接列表
					  	});
					});
		});
        function loadPhoto(){
            var option = {
                title: 'WeUI, 为微信 Web 服务量身设计',
                desc: 'WeUI, 为微信 Web 服务量身设计',
                link: "https://weui.io",
                imgUrl: 'https://mmbiz.qpic.cn/mmemoticon/ajNVdqHZLLA16apETUPXh9Q5GLpSic7lGuiaic0jqMt4UY8P4KHSBpEWgM7uMlbxxnVR7596b3NPjUfwg7cFbfCtA/0'
            };

            $.get(ctx+'/wechat/index/search/photoPages?subject=%E6%97%A5%E5%87%BA&page=1&pageSize=50&date=2016-09-14' , function (res) {
                console.log(res);
            });
        }
      //   loadPhoto();
    });
</script>
</script>
 <script type="text/html" id="tpl_input">
<div class="page">
    <div class="page__hd">
        <h1 class="page__title">阳光祝福</h1>
        <p class="page__desc">订单编辑</p>
    </div>
    <div class="page__bd">
      
        <div class="weui-cells">
            <div class="weui-cell weui-cell_select weui-cell_select-after">
                <div class="weui-cell__hd">
                    <label for="" class="weui-label">店铺</label>
                </div>
                <div class="weui-cell__bd">
				<c:if test="${not empty shop}">
 					<div class="weui-select">${shop.name}</div>
				</c:if>
				<c:if test="${not empty shopList}">
  					<select id="input_select_shop"  class="weui-select" name="select2">
  					<c:forEach var="shop" items="${shopList }" varStatus="status">
	 					<option value="${shop.name}">${shop.name}</option>
					</c:forEach>
  					</select>
				</c:if>
                </div>
            </div>
		 </div>
		<div class="weui-cells">
			<div class="weui-cell weui-cell_select weui-cell_select-after" id="select_photo">
                <div class="weui-cell__hd">
                    <label for="" class="weui-label">照片</label>
                </div>
                <div class="weui-cell__bd">
                   <div class="weui-select" id="photo">
						<span style="color:#999;">选择照片</span>
						<input id="input_photoInfo"  type='hidden' name='photoInfo' value=''>
						<input id="input_photoInfoLocal"  type='hidden' name='photoInfoLocal' value=''>
						<input id="input_photo"  type='hidden' name='photo' value=''>
					</div>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">收货人信息</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">收货人</label></div>
                <div class="weui-cell__bd">
                    <input id="input_name" class="weui-input" type="text"  placeholder="请输入姓名"/>
                </div>
            </div>
            <div class="weui-cell weui-cell_vcod ">
                <div class="weui-cell__hd">
                    <label class="weui-label">手机号</label>
                </div>
                <div class="weui-cell__bd">
                    <input id="input_phone" class="weui-input" type="tel"  pattern="[0-9]*" placeholder="请输入手机号">
                </div>
            </div>
           
        </div>
		<div class="weui-cells__title">收货地址</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea id="input_addr" class="weui-textarea" placeholder="请输入详细地址" rows="1"></textarea>
                </div>
            </div>
        </div>
		<div class="weui-cells__title">祝福语(可选)</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea id="input_note" class="weui-textarea" placeholder="请输入祝福语或其他" rows="2"></textarea>
                    <div class="weui-textarea-counter">100</div>
                </div>
            </div>
        </div>
		

       <!-- <label for="weuiAgree" class="weui-agree">
            <input id="weuiAgree" type="checkbox" class="weui-agree__checkbox">
            <span class="weui-agree__text">
                阅读并同意<a href="javascript:void(0);">《相关条款》</a>
            </span>
        </label>-->

        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">提交订单</a>
        </div>
    </div>
    <div id="loadingToast" style="display:none;">
	<div class="weui-mask"></div>
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast" id="weui-toast">
            <i class="weui-loading weui-icon_toast"></i>
            <p class="weui-toast__content">提交中...</p>
        </div>
    </div>
 <div class="weui-cells__title"> &nbsp;</div>
   <div class="weui-footer">
            		<p class="weui-footer__links">
                		<a href="http://www.geidianyg.com" class="weui-footer__link">给点儿阳光</a>
           			 </p>
            		<p class="weui-footer__text">Copyright © 2016 geidianyg.com</p>
       			 </div>
</div>
<script type="text/javascript">

$(function(){
	$('#select_photo').on('click',function(){
		pageManager.go('select');
	});
	var $tooltips = $('.js_tooltips');
	function tooltipsShow(){
			if ($tooltips.css('display') != 'none') return;
            // toptips的fixed, 如果有`animation`, `position: fixed`不生效
            $('.page.cell').removeClass('slideIn');
		 $tooltips.css('display', 'block');
            setTimeout(function () {
			  $tooltips.css('display', 'none');
            }, 5000);
	}
        $('#showTooltips').on('click', function(){
			
			var input_select_shop=$('#input_select_shop').val();
			var input_photo=$('#input_photo').val();
			var input_photoInfo=$('#input_photoInfo').val();
			var input_photoInfoLocal=$('#input_photoInfoLocal').val();
			var input_name=$('#input_name').val();
			var input_phone=$('#input_phone').val();
			var input_addr=$('#input_addr').val();
			var input_note=$('#input_note').val();
			if(input_photo==''){
				$tooltips.html('请选择照片');
				tooltipsShow();
				return;
			}
			if(input_name==''){
				$tooltips.html('请输入收货人姓名');
				tooltipsShow();
				return;
			}
			if(input_phone==''){
				$tooltips.html('请输入手机号');
				
				tooltipsShow();
				return;
			}
			if(input_phone.length!=11){
				$tooltips.html('请输入正确输入11位手机号');
				
				tooltipsShow();
				return;
			}

			if(input_addr==''){
				$tooltips.html('请输入收货地址');
				tooltipsShow();
				return;
			}
            var putjson={
            		shop:input_select_shop,
            		photo:input_photo,
            		photoInfo:input_photoInfo+'|'+input_photoInfoLocal,
            		name:input_name,
            		phone:input_phone,
            		address:input_addr,
            		note:input_note
            }
			var $loadingToast = $('#loadingToast');
			if ($loadingToast.css('display') != 'none') return;
            $loadingToast.fadeIn(100);
          

			$.post(ctx+"/wechat/cake/put", putjson,function(result){
				if(result.state==1){
					$("#weui-toast").html('<br><br>提交成功');
					var orderInfoHtml="";
					orderInfoHtml+=' <div class="weui-form-preview__item"><label class="weui-form-preview__label">商品</label><span class="weui-form-preview__value">'+putjson.photoInfo+'</span> </div>';
					orderInfoHtml+=' <div class="weui-form-preview__item"><label class="weui-form-preview__label">收货人</label><span class="weui-form-preview__value">'+putjson.name+'</span> </div>';
					orderInfoHtml+=' <div class="weui-form-preview__item"><label class="weui-form-preview__label">联系电话</label><span class="weui-form-preview__value">'+putjson.phone+'</span> </div>';
					orderInfoHtml+=' <div class="weui-form-preview__item"><label class="weui-form-preview__label">收货地址</label><span class="weui-form-preview__value">'+putjson.address+'4</span> </div>';
					orderInfoHtml+=' <div class="weui-form-preview__item"><label class="weui-form-preview__label">祝福</label><span class="weui-form-preview__value">'+putjson.note+'4</span> </div>';
					
					setTimeout(function () {
						//$("#container").html("");
						go_page="preview";
						//pageManager.go(go_page);
 						setTimeout(function () {
							$("#orderTitle").html("订单提交成功");
							$("#orderMoney").html("￥19.00");
							$("#orderContent").html(orderInfoHtml);
							location.replace("${ctx}/wechat/cake/show");
            			}, 500);
           			 },1000);
				}else{
					$("#weui-toast").html('<br><br>提交失败');
					   setTimeout(function () {
                		$loadingToast.fadeOut(100);
           			 }, 2000);
				}
		   	}); 
});});

</script>
</script>
   <script type="text/html" id="tpl_preview">
<div class="page">
    <div class="page__hd">
        <h1 class="page__title" id="orderTitle">订单提交成功</h1>
        <p class="page__desc">有任何问题请致电 010-52234222</p>
    </div>
    <div class="page__bd">
        <div class="weui-form-preview">
            <div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">订单</label>
                    <em class="weui-form-preview__value" id="orderMoney"></em>
                </div>
            </div>
            <div class="weui-form-preview__bd" id="orderContent">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">商品</label>
                    <span class="weui-form-preview__value"> </span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">收货人</label>
                    <span class="weui-form-preview__value"> </span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">联系电话</label>
                    <span class="weui-form-preview__value"> </span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">收货地址</label>
                    <span class="weui-form-preview__value"> </span>
                </div>
 				<div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">祝福语/label>
                    <span class="weui-form-preview__value"> </span>
                </div>
            </div>
            <div class="weui-form-preview__ft">
                <a id="agian" class="weui-form-preview__btn weui-form-preview__btn_primary"  href="javascript:;" >再来一个</a>
            </div>
        </div>
		<div class="page__hd">
       		 <p class="page__desc">关注公众号“给点儿阳光” 欣赏每日阳光，获取更多服务！</p>
				<p class="page__desc">注：公众号回复下单手机号 即可查询订单信息</p>
   		 </div>
    </div>
 	<div class="weui-cells__title"> &nbsp;</div>
   		<div class="weui-footer weui-footer_fixed-bottom">
            		<p class="weui-footer__links">
                		<a href="http://www.geidianyg.com" class="weui-footer__link">给点儿阳光</a>
           			 </p>
            		<p class="weui-footer__text">Copyright © 2016 geidianyg.com</p>
       			 </div>
</div>
<script type="text/javascript">

$(function(){
	$('#agian').on('click',function(){
			location.replace("${ctx}/wechat/cake/index");
	});
});

</script>
</script>
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="${ctx}/static/weui/weui.min.js"></script>
    <script src="${ctx}/static/weui/test.js"></script>
</body>
	 <script type="text/javascript" src="${ctx}/static/js/sun/base.js?V=3.o"></script> 
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>	
</html>
