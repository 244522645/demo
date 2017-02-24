<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/common/static.jsp"%>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${config.siteName}</title>
    	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <link rel="stylesheet" href="${ctx}/static/v2.0/css/swiper-3.4.1.min.css" />
   
  </head>
<body  class="v2">
  	 <div class="page-group" id="addRelation">
        <!-- 单个page ,第一个.page默认被展示-->
        <div class="page shengrxinxi qvchuhueixian">
            <!-- 标题栏 -->

            <!-- 工具栏 -->
           <nav class="bar bar-tab bir">
           	 <a class="tab-item external active" id="save" onclick="saveRelation();">
			    <span class="tab-label" >保存</span>
			  </a>
			  <a class="tab-item external shanchu" >
			    <span class="tab-label" >删除</span>
			  </a>
           </nav>

            <!-- 这里是页面内容区 -->
            <div class="content birthdaymain content_addRelation">
				<div class="TopWri">
					填写朋友信息，为Ta预约一缕阳光
				</div>
				<div class="birthday">
				<div class="content-block ">
			      <div class="list-block">
			        <ul class="birthday1">
			          <!-- Text inputs -->
			          <li>
			            <div class="item-content">
			              <div class="item-inner work">
			                <div class="item-title label word1">
			                	<div class="Sign"><img src="${ctx}/static/v2.0/img/name.png"></div>
			                	<div class="word1_name">姓名</div>
			                </div>
			                <div class="item-input">
			                  <input type="text"  placeholder="请输入姓名" value="" id="name" />
			                  <input type="text" value="" id="id" style="display: none"/>
			                </div>
			              </div>
			            </div>
			          </li>
			          <li>
			            <div class="item-content">
			              <div class="item-inner work">
			                <div class="item-title label word1">
			                	<div class="Sign"><img src="${ctx}/static/v2.0/img/shengr.png"></div>
			                	<div class="word1_name">生日</div>
			                </div>
			                <div class="item-input">
			                  <input type="text"  placeholder="请选择生日" id="birthday" readonly="readonly">
			                </div>
			              </div>
			            </div>
			          </li><li>
			            <div class="item-content" style="display: none">
			              <div class="item-inner work">
			                <div class="item-title label word1">
			                	<div class="Sign"><img src="${ctx}/static/v2.0/img/name.png"></div>
			                	<div class="word1_name">手机号</div>
			                </div>
			                <div class="item-input">
			                  <input type="number"  placeholder="输入手机号（选填 ）" id="mobileNo">
			                </div>
			              </div>
			            </div>
			          </li><li>
			            <div class="item-content">
			              <div class="item-inner work1 weibu1">
			                <div class="item-title label word1">
			                	<div class="Sign"><img src="${ctx}/static/v2.0/img/guanxi.png"></div>
			                	<div class="word1_name">关系</div>
			                </div>
			                <div class="item-input">
			                  <select id="relation" class="duiqi" name="selse" >
								<option value="朋友">朋友</option>
								<option value="父亲">父亲</option>
								<option value="母亲">母亲</option>
								<option value="孩子">孩子</option>
								<option value="爱人">爱人</option>
								<option value="恋人">恋人</option>
								<option value="亲人">亲人</option>
			                  </select>
			                </div>
			              </div>
			            </div>
			          </li>
			        </ul>
			      </div>
			    </div>
			   </div>
            </div>
        </div> 
   </div>
    <!-- popup, panel 等放在这里 -->
    <div class="panel-overlay"></div>
  </body>
 <script type='text/javascript' src='https://g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
 <script type='text/javascript' src='https://g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
 <script type="text/javascript" src="${ctx}/static/v2.0/js/index.js"></script>
<script> 

$("#birthday").calendar({
	onChange:function(p, values, displayValues){
		var nowDate = new Date().Format('yyyy-MM-dd');
		
		if(nowDate == displayValues){
			$.toast("不能预约当日的阳光！");
		}
	},
	onClose:function(){
		var nowDate = new Date().Format('yyyy-MM-dd');
		var inputValue = $("#birthday").val();
		if(nowDate == inputValue){
			$("#birthday").val("");
		}
	}
});


function saveRelation(){
	var name = $("#name").val();
	var birthday = $("#birthday").val();
	var mobileNo = $("#mobileNo").val();
	var relation = $("#relation").val();
	var id = $("#id").val();
	
	if(isNull(name)){
		$.toast("请输入姓名");
		return;
	}else if(isNull(birthday)){
		$.toast("请选择生日");
		return;
	}else if(isNull(relation)){
		$.toast("请选择关系");
		return;
	}
	if(!isNull(mobileNo)){
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(!myreg.test(mobileNo)) 
		{ 
			$.toast('请输入有效的手机号码！'); 
			return; 
		} 
	}
	
	$.post(ctx+"/wechat/v2/relation/addRelationSave",{id:id,name:name,birthday:birthday,mobileNo:mobileNo,relation:relation},function(response){
				if("success" == response.message){
					$.toast("保存成功");
					setTimeout("window.history.back();",600);
				}else if("empty" == response.message){
					$.toast("数据不全，请重新填写");
				}else{
					$.toast("操作失败");
				}
			}
	);
}

$(document).on('click', '.shanchu',function () {
    $.confirm('确定删除吗?',
      function () {
    	var id = $("#id").val();
    	$.post(ctx+"/wechat/v2/relation/deleteRelication",{id:id},function(response){
    			if("success" == response){
    				$.toast("删除成功");
    				setTimeout("window.history.back();",600);
    				
    			}else{
    				$.toast("操作失败");
    			}
    		}
    	);
      },
      function () {
        
      }
    );
});



function deleteRelation(){
	
}
$(function(){
	 //编辑状态，回显数据
	 var memberRelation = "${mb }";
	 if(null != memberRelation && '' != memberRelation){
		$("#name").val("${mb.name }");
		var birthday = "${mb.birthday }";
		birhtday = birthday.substring(0,10);
		$("#birthday").val(birhtday);
		$("#mobileNo").val("${mb.mobileNo }");
		$("#relation").val("${mb.relation }");
		$("#id").val("${mb.id }");
	 }else{
		$(".shanchu").hide(); 
	 }
});

//初始化sui框架的方法
$(function(){
	$.init();
});
//页面跳转
$(document).on("pageInit", function(e, pageId, $page) {
	
});

</script>
</html>
