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
  </head>
  <body>
    <div class="page-group">
	      <div class="page">
	        <header class="bar bar-nav">
		        <button class="button button-link button-nav pull-left">
		          <span class="icon icon-left"></span>
		          返回
		        </button>
		        <h1 class='title'>用户反馈</h1>
	      	</header>
			<div class="content">
			<div class="content-padded">
			
			  <h3>使用反馈</h3>
			  <p>这感谢您使用 给点儿阳光，如果您在使用过程中遇到任何不便，或有意见及建议都欢迎您反馈给我们。</p>
			</div>
			  <div class="list-block">
			   <form  class="form-horizontal form-horizontal-border" id="feedbackInfo"> 
			    <ul>
			      <li>
			        <div class="item-content">
			          <div class="item-media"><i class="icon icon-form-email"></i></div>
			          <div class="item-inner">
			            <div class="item-title label">联系方式</div>
			            <div class="item-input">
			              <input type="text" placeholder="E-mail or phone"   name="contact" id="contact">
			            </div>
			          </div>
			        </div>
			      </li>
			      <li>
			        <div class="item-content">
			          <div class="item-media"><i class="icon icon-form-gender"></i></div>
			          <div class="item-inner">
			            <div class="item-title label">反馈类型</div>
			            <div class="item-input">
			              <select  name="type" id="type">
			                <option>建议</option>
			                <option>意见</option>
			                <option>Bug</option>
			                <option>其他</option>
			              </select>
			            </div>
			          </div>
			        </div>
			      </li>
			     
			      <li class="align-top">
			        <div class="item-content">
			          <div class="item-media"><i class="icon icon-form-comment"></i></div>
			          <div class="item-inner">
			            <div class="item-title label">反馈内容</div>
			            <div class="item-input">
			              <textarea name="body" id="body"></textarea>
			            </div>
			          </div>
			        </div>
			      </li>
			    </ul>
			    </form>
			  </div>
			  <div class="content-block">
			      <p>
			        <a href="#" class="button button-big button-fill button-success">提交反馈</a></p>
			    </div>
			  </div>
			  
			  <script type="text/javascript">
			  var lock=0;
			  	$(function(){
		     	 $(document).on('click','.button-success',function () {
		     		var contact = $("#contact").val();
		     		var type = $("#type").val();
		     		var body = $("#body").val();
		     		
		     		var message='';
		     		if(isNull(contact))
		     			message='请填写任一联系方式';
		     		if(isNull(type))
		     			message='请填写拍摄地点';
		     		if(isNull(body))
		     			message='请填写反馈内容';
		     		if(!isNull(message)){
		     			$.toast(message);
		     			message ='';
		     			return;
		     		}
		     		
		     		 if(lock == 0)
		     			submitInfo(contact,type,body);
		      	  });
		     	 
		     });
          	function submitInfo(contact,type,body){
          		 	lock = 1;
            		 $.ajax({  
            	        type:'PUT',  
            	        url:ctx+"/wechat/my/feedback?contact="+contact+"&type="+type+"&body="+body,  
            	    	contentType:false,  
				        	processData:false,
            	        success:function(result){
            	        	lock = 0;
            	        	$.alert('感谢您的反馈！','提交成功', function () {
            	        		$.router.back();
            	            });
            	        	//$.router.load(ctx+'/wechat/my/index', true);
            	        },
          		 		fail: function (res) {
          		 			lock = 0;
          		 			alert('提交失败');
                       }	
            	    });
          	}
          </script>
		</div>
    </div>
  </body>
  <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
  
</html>
