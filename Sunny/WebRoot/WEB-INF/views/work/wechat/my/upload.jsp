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
    <style>
    	.card{
    		margin:10px 0px 10px 0px;
    	}
    	.content-block{
    		margin-top: 10px
    	}
    	.f-6{
    	font-size: 0.6rem;
    	text-align: right;
    	}
    	.bar .zhizuo{
    	top: 0rem;
		}
    </style>
  </head>
  <body>
    <div class="page-group">
      <div class="page">
        <header class="bar bar-nav">
          <a class="icon icon-left pull-left" href="javascript:void(0);"></a>
          <h1 class="title">图片上传</h1>
        </header>
        <div class="content content-bg-img" style="background-image: url('${localIds}')">
          <div class="body">
            <div class="status-write">
              <div class="list-block bg-transparent">
               <form  class="form-horizontal form-horizontal-border" id="imagesInfo"> 
                <ul>
                  <li>
                    <div class="item-content">
                      <div class="item-inner borderline">
                        <div class="item-title label">拍摄时间：</div>
                        <div class="item-input">
                          <input type="date" name="imgDate" id="imgDate">
                          <input type="hidden" name="serverId" id="serverId">
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="item-content">
                      <div class="item-inner borderline" >
                        <div class="item-title label">拍摄地点：</div>
                        <div class="item-input">
                          <input type="text" name="imgAddress" id="imgAddress">
                        </div>
                      </div>
                    </div>
                  </li>
                  <li class="align-top">
                    <div class="item-content">
                      <div class="item-inner borderline"">
                        <div class="item-title label">描述：</div>
                        <div class="item-input">
                          <textarea name="imgStory" id="imgStory"></textarea>
                        </div>
                      </div>
                    </div>
                  </li>
                </ul>
                <style type="text/css">
                .borderline{border-bottom: 1px solid rgba(249, 0, 0, 0.25);}
                </style>
                  </form>
              </div>
            
              <div class="status-footer">
                <a href="javascript:void(0);" class="fill-button">确认上传</a>
              </div>
            </div>
          </div>
          <script type="text/javascript">
          	var lock=0;
          	$(function(){
		     	 $(document).on('click','.fill-button',function () {
		     		var imgDate = $("#imgDate").val();
		     		var imgAddress = $("#imgAddress").val();
		     		var imgStory = $("#imgStory").val();
		     		
		     		var message='';
		     		if(isNull(imgDate))
		     			message='请填写拍摄日期';
		     		if(isNull(imgAddress))
		     			message='请填写拍摄地点';
		     		if(isNull(imgStory))
		     			message='请填写照片说明';
		     		if(!isNull(message)){
		     			$.toast(message);
		     			message ='';
		     			return;
		     		}
		     		 if(lock == 0)
		     		 submitImageInfo();
		      	  });
		     	 
		     });
          	function submitImageInfo(){
          		 lock = 1;
          		//var serverId = uploadImg('${localIds}');
          		wx.uploadImage({
        		    localId: '${localIds}' , // 需要上传的图片的本地ID，由chooseImage接口获得
        		    isShowProgressTips: 1, // 默认为1，显示进度提示
        		    success: function (res) {
        		        var serverId = res.serverId; // 返回图片的服务器端ID
        		        $("#serverId").val(serverId);
        		        var formdata=new FormData($w("imagesInfo"));  
                 		 $.ajax({  
                 	        type:'POST',  
                 	        url:ctx+"/wechat/my/up/input",  
                 	        data:formdata,  
                 	       	contentType:false,  
   				        	processData:false,
                 	        success:function(result){
                 	        	lock = 0;
                 	        	alert('上传成功');
                 	        	//$.router.load(ctx+'/wechat/my/index', true);
                 	        	$.router.back();
                 	        },
               		 		fail: function (res) {
               		 			lock = 0;
               		 			alert('上传失败');
                            }	
                 	    });
        		        return serverId;
        		    },
        		 	fail: function (res) {
        		 		lock = 0;
                     	alert('上传失败');
                     	return '';
                     }
        		});
          		
          	}
          </script>
        </div>
      </div>
     </div>
  </body>
  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
  
</html>
