<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${config.siteName}</title>
    <meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

  </head>
  <body>
    <div class="page-group">
      <div class="page">
        <header class="bar bar-nav">
          <a class="icon icon-left pull-left" href="javascript:void(0);"></a>
          <h1 class="title">图片上传</h1>
        </header>
        <div class="content content-white">
          <div class="f-shangchuan">
            <p class="heading">图片上传要求</p>
            <p class="text">图片不能存在任何文字或水印</p>
            <p class="text">照片必须真实、清晰</p>
            <p class="text">您投稿的图片，我们会有1天时间进行审核</p>
            <p class="text">审核通过后，您将获得阳光币一枚，用于平台消费</p>
            <img class="file-plus" src="${ctx}/static/images/sun/up-btn.png" alt="上传图标">
            
            <a class="mytg" href="${ctx }/wechat/my/up/list">查看我的投稿</a>
           
            <script type="text/javascript">
            	function chooseImg(){
            		 wx.chooseImage({
		                 count: 1, // 默认9
		                 sizeType: ['original'], //['original', 'compressed'] 可以指定是原图还是压缩图，默认二者都有
		                 sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
		                 success: function (res) {
		                     var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		                 	$.router.load("${ctx}/wechat/my/up/input?localIds="+localIds.toString()); 
		                 }
		             });
            	}
			     $(function(){
			     	 $(document).on('click','.file-plus',function () {
			     		chooseImg();
			      	  });
			     	 
			     });
			</script>  
			     <style type="text/css">
			    .mytg{
			        position: absolute;
			        bottom: 3.5em;
			        text-align: center;
			       margin-left: -53px;
			          }
			          .f-shangchuan > .file-plus {
			            bottom: 6.5em;
			          }
			    </style>
          </div>
        </div>
        
        
      </div>
        
      </div>
    </div>
  </body>
  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
  
</html>
